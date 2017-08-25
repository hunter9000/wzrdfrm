package avalon.request;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Encapsulates the request to craft an item. the id is the recipe's id, extraMats are all the extra materials to be added. */
public class RecipeCraftRequest {

    private Long recipeId;
    private List<CountableMaterialRequest> extraMats;

    public Long getRecipeId() {
        return recipeId;
    }
    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public List<CountableMaterialRequest> getExtraMats() {
        return extraMats;
    }
    public void setExtraMats(List<CountableMaterialRequest> extraMats) {
        this.extraMats = extraMats;
    }


    public boolean validate() {
        if (recipeId == null || extraMats == null) {
            return false;
        }

        // check for duplicate materials
        Set<CountableMaterialRequest> set = new HashSet<>(this.extraMats);
        if (set.size() != this.extraMats.size()) {
            return false;
        }

        return true;
    }
}
