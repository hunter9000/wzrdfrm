package avalon.model.items.recipe;

import avalon.model.items.Item;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name="extra_capacity")
    private int extraCapacity;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
    // the required mats and quantities
    private Set<RecipeRequirement> recipeReqs;

    // the resulting object
    @OneToOne
    @JoinColumn(name="item_id")
    private Item item;

    // if this recipe is currently craftable given the character's inventory
    @Transient
    private boolean craftable;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Set<RecipeRequirement> getRecipeReqs() {
        return recipeReqs;
    }
    public void setRecipeReqs(Set<RecipeRequirement> recipeReqs) {
        this.recipeReqs = recipeReqs;
    }

    public int getExtraCapacity() {
        return extraCapacity;
    }
    public void setExtraCapacity(int extraCapacity) {
        this.extraCapacity = extraCapacity;
    }

    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isCraftable() {
        return craftable;
    }
    public void setCraftable(boolean craftable) {
        this.craftable = craftable;
    }

    @Override
    public String toString() {
        return item.getName();
    }
}
