package wzrdfrm.model.farm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import wzrdfrm.model.user.User;

import javax.persistence.*;
import java.util.Set;

/** The toplevel game object. Only one per user at a time, contains all game state info */
@Entity
@Table(name = "farm")
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "owner", nullable = false, updatable = false)
    @JsonIgnore
    private User owner;

    @OneToMany(mappedBy = "farm", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FarmPlot> farmPlots;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<FarmPlot> getFarmPlots() {
        return farmPlots;
    }
    public void setFarmPlots(Set<FarmPlot> farmPlots) {
        this.farmPlots = farmPlots;
    }
}
