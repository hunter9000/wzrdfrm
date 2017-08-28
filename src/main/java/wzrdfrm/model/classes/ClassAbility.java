package wzrdfrm.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "char_class_ability")
public class ClassAbility {

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
    @Column(name = "ability_type", nullable = false, updatable = false)
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

    public AbilityType getAbilityType() {
        return abilityType;
    }
    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }
}
