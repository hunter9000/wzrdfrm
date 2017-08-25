package avalon.model.items;

import avalon.model.items.equipment.EquipmentSlot;

import javax.persistence.*;

// the base definition of a craftable item. can be equipment, consumable
@Entity
@Table(name="item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name="name", nullable = false, updatable = false)
    private String name;

    @Column(name="body_slot", nullable = false, updatable = false)
    @Enumerated(value = EnumType.STRING)
    private EquipmentSlot bodySlot;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public EquipmentSlot getBodySlot() {
        return bodySlot;
    }
    public void setBodySlot(EquipmentSlot bodySlot) {
        this.bodySlot = bodySlot;
    }
}
