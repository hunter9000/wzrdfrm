package wzrdfrm.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import wzrdfrm.model.farm.Farm;

import javax.persistence.*;

/** The farm's current state for each class */
@Entity
@Table(name = "char_class")
public class CharClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "farm", nullable = false, updatable = false)
    @JsonIgnore
    private Farm farm;

    @OneToOne
    @JoinColumn(name = "char_class_definition", nullable = false, updatable = false)
    private CharClassDefinition charClassDefinition;

    @Column(name = "curr_xp", nullable = false)
    private Integer currentXP = 0;

    @Column(name = "curr_level", nullable = false)
    private Integer currentLevel = 0;

    @Column(name = "unlocked", nullable = false)
    private Boolean unlocked = false;

    @Transient
    @JsonProperty
    private Integer xpNeededToLevel;

//    public Integer xpNeededToLevel() {
//
//    }

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

    public CharClassDefinition getCharClassDefinition() {
        return charClassDefinition;
    }
    public void setCharClassDefinition(CharClassDefinition charClassDefinition) {
        this.charClassDefinition = charClassDefinition;
    }

    public Integer getCurrentXP() {
        return currentXP;
    }
    public void setCurrentXP(Integer currentXP) {
        this.currentXP = currentXP;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }
    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Boolean getUnlocked() {
        return unlocked;
    }
    public void setUnlocked(Boolean unlocked) {
        this.unlocked = unlocked;
    }

    public Integer getXpNeededToLevel() {
        return xpNeededToLevel;
    }
    public void setXpNeededToLevel(Integer xpNeededToLevel) {
        this.xpNeededToLevel = xpNeededToLevel;
    }

    @Override
    public boolean equals(Object obj) {
        CharClass other = (CharClass)obj;
//        if (this.id != null && other.id != null) {
//            return this.id.equals(other.id);
//        }
//        else {
            return this.charClassDefinition.equals(other.charClassDefinition);
//        }
    }

    @Override
    public int hashCode() {
        if (this.id == null) {
            return 0;
        }
        return this.id.hashCode();
    }
}
