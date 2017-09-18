package wzrdfrm.model.farm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import wzrdfrm.model.classes.AbilityType;
import wzrdfrm.model.classes.CharClass;
import wzrdfrm.model.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/** The toplevel game object. Only one per user at a time, contains all game state info */
@Entity
@Table(name = "farm")
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "owner", nullable = false, updatable = false)
    @JsonIgnore
    private User owner;

    @OneToMany(mappedBy = "farm", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FarmPlot> farmPlots;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "curr_char_class")       // allowed to be nullable because of cycle in farm <-> charclass mapping
    private CharClass currCharClass;

//    @OneToMany(mappedBy = "farm", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<CharClass> charClasses;

    @OneToMany(mappedBy = "farm", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Seed> seedInventory;

    @OneToMany(mappedBy = "farm", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Consumable> consumableInventory;

    @Column(name = "num_unlock_orbs", nullable = false)
    private Integer numUnlockOrbs = 0;

    /* All the abilities that this farm has access to, based on current class, and permanent abilities from all classes */
    @Transient
    private List<AbilityType> abilities;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<FarmPlot> getFarmPlots() {
        return farmPlots;
    }
    public void setFarmPlots(Set<FarmPlot> farmPlots) {
        this.farmPlots = farmPlots;
    }

    public CharClass getCurrCharClass() {
        return currCharClass;
    }
    public void setCurrCharClass(CharClass currCharClass) {
        this.currCharClass = currCharClass;
    }

    public Set<Seed> getSeedInventory() {
        return seedInventory;
    }
    public void setSeedInventory(Set<Seed> seedInventory) {
        this.seedInventory = seedInventory;
    }

    public Set<Consumable> getConsumableInventory() {
        return consumableInventory;
    }
    public void setConsumableInventory(Set<Consumable> consumableInventory) {
        this.consumableInventory = consumableInventory;
    }

    public List<AbilityType> getAbilities() {
        return abilities;
    }
    public void setAbilities(List<AbilityType> abilities) {
        this.abilities = abilities;
    }

    public Integer getNumUnlockOrbs() {
        return numUnlockOrbs;
    }
    public void setNumUnlockOrbs(Integer numUnlockOrbs) {
        this.numUnlockOrbs = numUnlockOrbs;
    }

    //    public Set<CharClass> getCharClasses() {
//        return charClasses;
//    }
//    public void setCharClasses(Set<CharClass> charClasses) {
//        this.charClasses = charClasses;
//    }
}
