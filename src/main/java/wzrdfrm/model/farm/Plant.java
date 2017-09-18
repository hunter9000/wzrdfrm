package wzrdfrm.model.farm;

import javax.persistence.*;

/** Defines the plant types that can be grown. Used to represent planted crops and seeds that have been harvested. */
@Entity
@Table(name = "plant")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "grow_time_milliseconds", nullable = false, updatable = false)
    private Long growTimeMilliSeconds;


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

    public Long getGrowTimeMilliSeconds() {
        return growTimeMilliSeconds;
    }
    public void setGrowTimeMilliSeconds(Long growTimeMilliSeconds) {
        this.growTimeMilliSeconds = growTimeMilliSeconds;
    }
}
