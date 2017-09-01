package wzrdfrm.model.farm;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "consumable_inventory")
public class Consumable implements HarvestReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "farm", nullable = false, updatable = false)
    @JsonIgnore
    private Farm farm;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usable_item", nullable = false, updatable = false)
    private UsableItem usableItem;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Farm getFarm() {
        return farm;
    }
    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public UsableItem getUsableItem() {
        return usableItem;
    }
    public void setUsableItem(UsableItem usableItem) {
        this.usableItem = usableItem;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /* HarvestReward */
    @Override
    public String getName() {
        return usableItem.getName();
    }
}
