package avalon.model.items.equipment;

import avalon.model.character.Character;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "equipped_item")
public class EquippedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false, updatable = false)
    @JsonIgnore
    private Character character;

    @OneToOne
    @JoinColumn(name = "equipment", nullable = false, updatable = false)
    private Equipment equipment;

    @Column(name="equipment_slot", nullable = false, updatable = false)
    @Enumerated(value = EnumType.STRING)
    private EquipmentSlot equipmentSlot;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Character getCharacter() {
        return character;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }

    public Equipment getEquipment() {
        return equipment;
    }
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }
    public void setEquipmentSlot(EquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }
}
