package wzrdfrm.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

/** The static definition of each class */
@Entity
@Table(name = "char_class_definition")
public class CharClassDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "orbs_to_unlock", nullable = false, updatable = false)
    private Integer orbsToUnlock;

    // TODO remove this, use the class level instead to define what the class gets?
//    @OneToMany(mappedBy = "charClassDefinition", fetch = FetchType.EAGER)
//    private Set<ClassAbility> classAbilities;

    @OneToMany(mappedBy = "charClassDefinition", fetch = FetchType.EAGER)
    @MapKey(name = "level")
    @JsonIgnore
    private Map<Integer, ClassLevel> classLevels;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "char_class_prereq",
            joinColumns = {@JoinColumn(name = "child_class_definition", nullable = false, updatable = false) },		// column that points to this table
            inverseJoinColumns = { @JoinColumn(name = "prereq_class_definition", nullable = false, updatable = false) })		// column that points to the other table
    @JsonIgnore
    private Set<CharClassDefinition> prereqClasses;

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

    public Integer getOrbsToUnlock() {
        return orbsToUnlock;
    }
    public void setOrbsToUnlock(Integer orbsToUnlock) {
        this.orbsToUnlock = orbsToUnlock;
    }

//    public Set<ClassAbility> getClassAbilities() {
//        return classAbilities;
//    }
//    public void setClassAbilities(Set<ClassAbility> classAbilities) {
//        this.classAbilities = classAbilities;
//    }

    public Map<Integer, ClassLevel> getClassLevels() {
        return classLevels;
    }
    public void setClassLevels(Map<Integer, ClassLevel> classLevels) {
        this.classLevels = classLevels;
    }

    public Set<CharClassDefinition> getPrereqClasses() {
        return prereqClasses;
    }
    public void setPrereqClasses(Set<CharClassDefinition> prereqClasses) {
        this.prereqClasses = prereqClasses;
    }

    @Override
    public boolean equals(Object obj) {
        CharClassDefinition other = (CharClassDefinition)obj;
        if (this.id != null && other.id != null) {
            return this.id.equals(other.id);
        }
        else {
            return this.name.equals(other.name);
        }
    }

    @Override
    public int hashCode() {
        if (this.id == null) {
            return 0;
        }
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }
}
