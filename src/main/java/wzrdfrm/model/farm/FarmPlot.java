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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "plant")
    private Plant plant;

    // consumable item that was applied to the plot when planted
//    @Column()
//    private UsableItem usableItem;

    @Column(name = "unlocked", nullable = false)
    private Boolean unlocked = false;

    @Transient
    @JsonProperty(value = "endDate")
    public Date getEndDate() {
        if (getPlantDate() != null && getPlant() != null) {
            return new Date(getPlantDate().toInstant().plusMillis(this.getPlant().getGrowTimeMilliSeconds()).toEpochMilli());
        }
        return null;
    }

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

    public Plant getPlant() {
        return plant;
    }
    public void setPlant(Plant plant) {
        this.plant = plant;
    }

}
