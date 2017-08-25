package avalon.model.character;

import javax.persistence.*;

// join table b/w recipe and char
@Entity
@Table(name="character_recipe")
public class CharacterRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="char_id")
    private long charId;

    @Column(name="recipe_id")
    private long recipeId;


    public long getCharId() {
        return charId;
    }
    public void setCharId(long charId) {
        this.charId = charId;
    }

    public long getRecipeId() {
        return recipeId;
    }
    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
