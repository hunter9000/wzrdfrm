package avalon.model.items.recipe;

import avalon.model.items.CountableMaterial;
import avalon.model.items.material.Material;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

// links a material to a recipe as a required mat, and gives the quant
@Entity
@Table(name="recipe_requirement")
public class RecipeRequirement implements CountableMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne      // many mateffects reference one material
    @JoinColumn(name="recipe_id", nullable = false, updatable = false)     // material_id is the fk column pointing to material table
    @JsonIgnore
    private Recipe recipe;

    @OneToOne
    @JoinColumn(name="material_id", nullable = false)
    private Material material;

    @Column(name="quantity", nullable = false)
    private Integer quantity;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material mat) {
        this.material = mat;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
