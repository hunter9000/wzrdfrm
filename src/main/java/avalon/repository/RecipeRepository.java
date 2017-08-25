package avalon.repository;

import avalon.model.items.recipe.Recipe;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "recipeRepository")
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
//    public Recipe findByUserId(long id);

}

