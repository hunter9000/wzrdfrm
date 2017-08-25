package avalon.model.items.material;

import avalon.model.items.equipment.EquipmentSlot;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "material_effect")
@JsonIgnoreProperties(value = { "material" })
public class MaterialEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne      // many mateffects reference one material
    @JoinColumn(name="material_id")     // material_id is the fk column pointing to material table
    private Material material;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private EffectType effectType;

    @Column(name = "val")
    private int value;

    @Column(name = "slot")
    @Enumerated(value = EnumType.STRING)
    private EquipmentSlot slot;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }

    public EffectType getEffectType() {
        return effectType;
    }
    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public EquipmentSlot getSlot() {
        return slot;
    }
    public void setSlot(EquipmentSlot slot) {
        this.slot = slot;
    }
}
