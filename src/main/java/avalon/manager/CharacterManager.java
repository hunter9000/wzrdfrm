package avalon.manager;

import avalon.model.character.Character;
import avalon.model.items.InventoryMaterial;
import avalon.model.items.material.Material;
import avalon.model.items.recipe.Recipe;
import avalon.model.items.recipe.RecipeRequirement;
import avalon.model.user.User;
import avalon.repository.MaterialRepository;
import avalon.repository.RecipeRepository;
import avalon.request.NewCharacterRequest;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CharacterManager {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public Character createCharacter(User user, NewCharacterRequest newCharacterRequest) {
        Character character = new Character();
        character.setUser(user);
        character.setName(newCharacterRequest.name);

        // create inventory and shit for debugging
        Set<InventoryMaterial> invMats = new HashSet<>();
        Iterable<Material> mats = materialRepository.findAll();
        for (Material mat : mats) {
            InventoryMaterial invMat = new InventoryMaterial();
            invMat.setMaterial(mat);
            invMat.setCharacter(character);
            invMat.setQuantity(5);
            invMats.add(invMat);
        }
        character.setInventoryMaterials(invMats);

        // add recipes
        Iterable<Recipe> recipes = recipeRepository.findAll();
        character.setRecipes(new HashSet<Recipe>(IterableUtils.toList(recipes)));

        return character;
    }

    public void processRecipes(Character character) {
        for (Recipe recipe : character.getRecipes()) {
            recipe.setCraftable(isRecipeCraftable(character, recipe));
        }
    }

    private boolean isRecipeCraftable(Character character, Recipe recipe) {
        for (RecipeRequirement req : recipe.getRecipeReqs()) {
            // get inventory material by name
            InventoryMaterial mat = character.getInventoryMaterialMap().get(req.getMaterial().getId());
            if (mat != null && mat.getQuantity() < req.getQuantity()) {
                return false;       // if character doesn't have enough of mat, can't craft
            }
        }
        return true;
    }

}
