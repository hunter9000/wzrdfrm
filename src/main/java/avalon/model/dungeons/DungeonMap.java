package avalon.model.dungeons;

import avalon.model.character.Character;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

// container for a single map. linked to cells
@Entity
@Table(name="map")
public class DungeonMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="char_id")      // owner
    @JsonIgnore
    private Character character;

    @Column(name="boss_level")
    private boolean isBoss;

//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinTable(name="map_edge",
//            joinColumns={@JoinColumn(name="enter_map_id", referencedColumnName="id")},
//            inverseJoinColumns={@JoinColumn(name="exit_map_id", referencedColumnName="id")})
//    private List<DungeonMap> linkedMaps;

    @OneToMany(mappedBy = "map", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DungeonCell> cells;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Character getCharacter() {
        return character;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }

    public boolean isBoss() {
        return isBoss;
    }
    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public Set<DungeonCell> getCells() {
        return cells;
    }
    public void setCells(Set<DungeonCell> cells) {
        this.cells = cells;
    }
}
