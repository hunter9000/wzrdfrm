package wzrdfrm.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "char_class_prereq")
public class CharClassPrereq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "child_class_definition", nullable = false, updatable = false)
    @JsonIgnore
    private CharClassDefinition childClass;

    @OneToOne
    @JoinColumn(name = "prereq_class_definition", nullable = false, updatable = false)
    private CharClassDefinition prereqClass;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public CharClassDefinition getChildClass() {
        return childClass;
    }
    public void setChildClass(CharClassDefinition childClass) {
        this.childClass = childClass;
    }

    public CharClassDefinition getPrereqClass() {
        return prereqClass;
    }
    public void setPrereqClass(CharClassDefinition prereqClass) {
        this.prereqClass = prereqClass;
    }
}
