package wzrdfrm.model.farm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonIgnore
    private Farm farm;

    // the coordinates in the farm of this plot
    @Column(name = "row", nullable = false, updatable = false)
    private Integer row;
    @Column(name = "col", nullable = false, updatable = false)
    private Integer col;

    // serializes in json as epoch time in milliseconds
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "plant_date")
    private Date plantDate = null;

//    @Column(name = "plant_type")
//    @Enumerated(EnumType.STRING)
//    private PlantType plantType;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "plant")
    private Plant plant;

    @Column(name = "unlocked", nullable = false)
    private Boolean unlocked = false;


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

    public Integer getRow() {
        return row;
    }
    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }
    public void setCol(Integer col) {
        this.col = col;
    }

    public Boolean getUnlocked() {
        return unlocked;
    }
    public void setUnlocked(Boolean unlocked) {
        this.unlocked = unlocked;
    }

    public Date getPlantDate() {
        return plantDate;
    }
    public void setPlantDate(Date plantDate) {
        this.plantDate = plantDate;
    }

    @Transient
    @JsonProperty(value = "endDate")
    public Date getEndDate() {
        if (getPlantDate() != null) {
            return new Date(getPlantDate().toInstant().plusSeconds(100).toEpochMilli());
        }
        return null;
    }

//    public PlantType getPlantType() {
//        return plantType;
//    }
//    public void setPlantType(PlantType plantType) {
//        this.plantType = plantType;
//    }

    public Plant getPlant() {
        return plant;
    }
    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
