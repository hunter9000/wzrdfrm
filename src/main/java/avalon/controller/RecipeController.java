package avalon.controller;

import avalon.interceptor.CharacterSheetOwnerRequired;
import avalon.manager.CraftingManager;
import avalon.model.character.Character;
import avalon.model.items.CountableMaterial;
import avalon.model.items.InventoryMaterial;
import avalon.model.items.equipment.Equipment;
import avalon.model.items.equipment.ItemEffect;
import avalon.model.items.material.Material;
import avalon.model.items.recipe.Recipe;
import avalon.repository.CharRepository;
import avalon.repository.MaterialRepository;
import avalon.repository.RecipeRepository;
import avalon.request.CountableMaterialRequest;
import avalon.request.RecipeCraftRequest;
import avalon.response.SuccessResponse;
import avalon.security.BadRequestException;
import avalon.util.AuthUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private CharRepository charRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private HttpServletRequest request;

    private Logger logger = Logger.getLogger(RecipeController.class);

/*    @RequestMapping(value="/api/char/{charId}/recipes/", method=RequestMethod.GET)
    public List<Recipe> getRecipes(@PathVariable long charId) {
        // return all recipes this user has unlocked
        JwtSubject token = (JwtSubject)request.getAttribute("jwtToken");
//        long charId = token.getCharId();
        // todo validate that charId belongs to user

        Character charModel = charRepository.findById(charId);

        List<Recipe> recipes = charModel.getRecipes();

        return recipes;
    }*/

    @RequestMapping(value="/api/char/{charId}/recipes", method=RequestMethod.POST)
    @CharacterSheetOwnerRequired
    public SuccessResponse craftRecipe(@RequestBody RecipeCraftRequest recipeRequest) {
        logger.debug(recipeRequest);

        Character character = AuthUtils.getCharacter(request);

        if (!recipeRequest.validate()) {
            throw new BadRequestException("Invalid request");
        }

        // load the materials for the extra mats from the db
        List<CountableMaterial> extraMats = new ArrayList<>();
        for (CountableMaterialRequest extraMatRequest : recipeRequest.getExtraMats()) {
            Material dbMat = materialRepository.findOne(extraMatRequest.getMaterialId());
            if (dbMat == null) {
                throw new BadRequestException("Material invalid");
            }
            CountableMaterial newInvMat = new InventoryMaterial();
            newInvMat.setMaterial(dbMat);
            newInvMat.setQuantity(extraMatRequest.getQuantity());
            extraMats.add(newInvMat);
        }

        // verify that the char has the recipe unlocked
        Recipe selectedRecipe = IterableUtils.find(character.getRecipes(), (Recipe recipe) -> recipe.getId().equals(recipeRequest.getRecipeId()));
        if (selectedRecipe == null) {
            throw new BadRequestException("Given recipe is not accessible to character");
        }

        CraftingManager craftingManager = new CraftingManager(character, selectedRecipe, extraMats);
        if (!craftingManager.validateCrafting()) {
            throw new BadRequestException(StringUtils.join(craftingManager.getErrors(), ','));
        }

        // remove the materials from the character's inventory
        for (InventoryMaterial ingredientInvMat : craftingManager.getMaterialsToRemove()) {
            InventoryMaterial characterInvMat = character.getInventoryMaterialMap().get(ingredientInvMat.getMaterial().getId());
            // reduce quantity
            characterInvMat.setQuantity(characterInvMat.getQuantity() - ingredientInvMat.getQuantity());
            // if quantity is now 0, remove the material from inventory
            if (characterInvMat.getQuantity() == 0) {
                character.getInventoryMaterials().remove(characterInvMat);
            }
        }

        // remove mats from inv, add crafted item w/ attached effects to inv
        Equipment equipment = new Equipment();
        equipment.setCharacter(character);
        equipment.setItem(selectedRecipe.getItem());
        equipment.setItemEffects(new HashSet<>(craftingManager.getItemEffects()));

        character.getInventoryEquipment().add(equipment);
        for (ItemEffect itemEffect : craftingManager.getItemEffects()) {
            itemEffect.setEquipment(equipment);
        }

        charRepository.save(character);

        return new SuccessResponse(true, "");
    }



}
