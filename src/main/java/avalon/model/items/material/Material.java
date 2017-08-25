package avalon.model.items.material;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "material_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType materialType;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MaterialEffect> effectList;

    @Column(name = "capacity_requirement", nullable = false, updatable = false)
    private Integer capacityRequirement;

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

    public MaterialType getMaterialType() {
        return materialType;
    }
    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public List<MaterialEffect> getEffectList() {
        return effectList;
    }
    public void setEffectList(List<MaterialEffect> effectList) {
        this.effectList = effectList;
    }

    public Integer getCapacityRequirement() {
        return capacityRequirement;
    }
    public void setCapacityRequirement(Integer capacityRequirement) {
        this.capacityRequirement = capacityRequirement;
    }
}
