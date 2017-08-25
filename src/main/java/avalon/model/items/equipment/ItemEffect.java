package avalon.model.items.equipment;

import avalon.model.items.material.MaterialEffect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

// join table linking effects to an item
@Entity
@Table(name="item_effect")
public class ItemEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "equipment_id")
    @JsonIgnore
    private Equipment equipment;

    @OneToOne()
    @JoinColumn(name = "material_effect_id")
    private MaterialEffect materialEffect;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Equipment getEquipment() {
        return equipment;
    }
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public MaterialEffect getMaterialEffect() {
        return materialEffect;
    }
    public void setMaterialEffect(MaterialEffect materialEffect) {
        this.materialEffect = materialEffect;
    }

    //    private long effectId;
//    private int value;

}
