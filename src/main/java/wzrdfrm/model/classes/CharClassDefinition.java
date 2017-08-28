package wzrdfrm.model.classes;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "charClassDefinition", fetch = FetchType.EAGER)
    private Set<ClassAbility> classAbilities;

    @ManyToMany()
    @JoinTable(name = "char_class_prereq",
            joinColumns = {@JoinColumn(name = "childClass", nullable = false, updatable = false) },		// column that points to this table
            inverseJoinColumns = { @JoinColumn(name = "prereqClass", nullable = false, updatable = false) })		// column that points to the other table
    private Set<CharClassDefinition> prereqClasses;




}
