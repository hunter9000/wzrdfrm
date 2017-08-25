package avalon.manager;

import avalon.model.character.Character;
import avalon.model.items.CountableMaterial;
import avalon.model.items.InventoryMaterial;
import avalon.model.items.equipment.EquipmentSlot;
import avalon.model.items.equipment.ItemEffect;
import avalon.model.items.material.MaterialEffect;
import avalon.model.items.recipe.Recipe;

import java.util.*;

public class CraftingManager {

    private Character character;
    private Recipe selectedRecipe;
    private List<CountableMaterial> extraMats;

    // outputs
    private List<String> errors = new ArrayList<>();
    private List<ItemEffect> itemEffects = new ArrayList<>();
    private Collection<InventoryMaterial> materialsToRemove;

    public CraftingManager(Character character, Recipe selectedRecipe, List<CountableMaterial> extraMats) {
        this.character = character;
        this.selectedRecipe = selectedRecipe;
        this.extraMats = extraMats;
    }

    /* Verify that the char has all the req and extra mats in inv */
    public boolean validateCrafting() {
        // combine the req and extra mats, then check if the inventory contains enough
        Map<Long, InventoryMaterial> combinedIngredients = new HashMap<>();

        combine(combinedIngredients, selectedRecipe.getRecipeReqs());
        combine(combinedIngredients, extraMats);

        // check that the inventory has all the right mats
        for (InventoryMaterial mat : combinedIngredients.values()) {
            InventoryMaterial invMat = character.getInventoryMaterialMap().get(mat.getMaterial().getId());
            if (invMat == null || invMat.getQuantity() < mat.getQuantity()) {
                errors.add("Not enough materials");
                return false;
            }
        }

        // check the capacity of the recipe
        // get all the material effects from the extra mats
        EquipmentSlot relevantSlot = selectedRecipe.getItem().getBodySlot();

        Integer capacitySum = 0;
        for (CountableMaterial extraMat : extraMats) {
            for (MaterialEffect effect : extraMat.getMaterial().getEffectList()) {
                if (effect.getSlot().equals(relevantSlot)) {
                    ItemEffect itemEffect = new ItemEffect();
                    itemEffect.setMaterialEffect(effect);
                    itemEffects.add(itemEffect);
                    capacitySum += extraMat.getMaterial().getCapacityRequirement();
                }
            }
        }

        // if over capacity for the item
        if (capacitySum > selectedRecipe.getExtraCapacity()) {
            errors.add("Not enough capacity for extra materials");
            return false;
        }

        materialsToRemove = combinedIngredients.values();

        return true;
    }

    /** Puts all of the CountableMaterials into the map as new instances of InventoryMaterials, mapped by material id, with total quantity */
    private void combine(Map<Long, InventoryMaterial> map, Collection<? extends CountableMaterial> mats) {
        for (CountableMaterial mat : mats) {
            if (map.containsKey(mat.getMaterial().getMaterialType())) {
                CountableMaterial curr = map.get(mat.getMaterial().getId());
                curr.setQuantity(curr.getQuantity() + mat.getQuantity());
            }
            else {
                InventoryMaterial invMat = new InventoryMaterial();
                invMat.setMaterial(mat.getMaterial());
                invMat.setQuantity(mat.getQuantity());
                map.put(mat.getMaterial().getId(), invMat);
            }
        }
    }

    public List<String> getErrors() {
        return errors;
    }
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<ItemEffect> getItemEffects() {
        return itemEffects;
    }
    public void setItemEffects(List<ItemEffect> itemEffects) {
        this.itemEffects = itemEffects;
    }

    public Collection<InventoryMaterial> getMaterialsToRemove() {
        return materialsToRemove;
    }
    public void setMaterialsToRemove(Collection<InventoryMaterial> materialsToRemove) {
        this.materialsToRemove = materialsToRemove;
    }
}
