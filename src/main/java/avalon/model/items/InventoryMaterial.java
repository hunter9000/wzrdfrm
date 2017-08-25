package avalon.model.items;

import avalon.model.character.Character;
import avalon.model.items.material.Material;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="inventory_material")
public class InventoryMaterial implements CountableMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name="char_id")
    @JsonIgnore
    private Character character;

    @ManyToOne
    @JoinColumn(name="material_id")
    private Material material;

    @Column(name = "quantity")
    private Integer quantity;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Character getCharacter() {
        return character;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }

    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
