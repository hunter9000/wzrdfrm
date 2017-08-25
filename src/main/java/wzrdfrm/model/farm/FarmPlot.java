package wzrdfrm.model.farm;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "farm_plot")
public class FarmPlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "farm", nullable = false, updatable = false)
    private Farm farm;

//    ADD COLUMN `plant_date` TIMESTAMP NULL;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "plant_date")
    private Date plantDate = null;

    @Column(name = "plant_type")
    @Enumerated(EnumType.STRING)
    private PlantType plantType;

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

    public Date getPlantDate() {
        return plantDate;
    }
    public void setPlantDate(Date plantDate) {
        this.plantDate = plantDate;
    }

    public PlantType getPlantType() {
        return plantType;
    }
    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }
}
