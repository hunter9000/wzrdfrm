package wzrdfrm.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "char_class_level")
public class ClassLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    // the class this belongs to
    @OneToOne
    @JoinColumn(name = "char_class_definition", nullable = false, updatable = false)
    @JsonIgnore
    private CharClassDefinition charClassDefinition;

    // abilities defined in code
    @Column(name = "class_level", nullable = false, updatable = false)
    private Integer level;

    // xp required to gain this level over the last level (the diff, not the cumulative total)
	@Column(name = "xp_required", nullable = false, updatable = false)
	private Integer xpRequired;

	// reference to ability granted at this level
//	@Column(name = "class_ability", updatable = false)
//    private ClassAbility classAbility;
    @Column(name = "ability_type", updatable = false)
    @Enumerated(EnumType.STRING)
    private AbilityType abilityType;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public CharClassDefinition getCharClassDefinition() {
        return charClassDefinition;
    }
    public void setCharClassDefinition(CharClassDefinition charClassDefinition) {
        this.charClassDefinition = charClassDefinition;
    }

    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getXpRequired() {
        return xpRequired;
    }
    public void setXpRequired(Integer xpRequired) {
        this.xpRequired = xpRequired;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }
    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }

    //    public ClassAbility getClassAbility() {
//        return classAbility;
//    }
//    public void setClassAbility(ClassAbility classAbility) {
//        this.classAbility = classAbility;
//    }
}
