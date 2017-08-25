package avalon.model.character;

import avalon.model.dungeons.DungeonMap;
import avalon.model.items.InventoryMaterial;
import avalon.model.items.equipment.Equipment;
import avalon.model.items.equipment.EquipmentSlot;
import avalon.model.items.equipment.EquippedItem;
import avalon.model.items.recipe.Recipe;
import avalon.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "characters")
@JsonIgnoreProperties(value="user")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne      // many chars reference one user
    @JoinColumn(name="user_id", nullable = false, updatable = false)     // user_id is the fk column pointing to user table
    private User user;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="character_recipe",
            joinColumns={@JoinColumn(name="char_id", referencedColumnName="id")},		// column that points to this table
            inverseJoinColumns={@JoinColumn(name="recipe_id", referencedColumnName="id")})		// column that points to other table
    private Set<Recipe> recipes;

    @OneToMany(mappedBy = "character", fetch = FetchType.EAGER)
    private Set<DungeonMap> maps;

    @OneToOne
    @JoinColumn(name="curr_map_id")     // current map this char is in, or null
    private DungeonMap currentMap;

    @Column(name = "map_x")
    private Integer mapX;

    @Column(name = "map_y")
    private Integer mapY;

    // Inventory mats and equipment
    @OneToMany(mappedBy = "character", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<InventoryMaterial> inventoryMaterials;

    @OneToMany(mappedBy = "character", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Equipment> inventoryEquipment;

    @OneToMany(mappedBy = "character", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "equipmentSlot")
    private Map<EquipmentSlot, EquippedItem> equippedItems;

    // map of material id to inventory material object
    @Transient
    private Map<Long, InventoryMaterial> inventoryMaterialMap;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }
    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public DungeonMap getCurrentMap() {
        return currentMap;
    }
    public void setCurrentMap(DungeonMap currentMap) {
        this.currentMap = currentMap;
    }

    public Set<DungeonMap> getMaps() {
        return maps;
    }
    public void setMaps(Set<DungeonMap> maps) {
        this.maps = maps;
    }

    public Integer getMapX() {
        return mapX;
    }
    public void setMapX(Integer mapX) {
        this.mapX = mapX;
    }

    public Integer getMapY() {
        return mapY;
    }
    public void setMapY(Integer mapY) {
        this.mapY = mapY;
    }

    public Set<InventoryMaterial> getInventoryMaterials() {
        return inventoryMaterials;
    }
    public void setInventoryMaterials(Set<InventoryMaterial> inventoryMaterials) {
        this.inventoryMaterials = inventoryMaterials;
    }

    public Set<Equipment> getInventoryEquipment() {
        return inventoryEquipment;
    }
    public void setInventoryEquipment(Set<Equipment> inventoryEquipment) {
        this.inventoryEquipment = inventoryEquipment;
    }

    public Map<EquipmentSlot, EquippedItem> getEquippedItems() {
        return equippedItems;
    }
    public void setEquippedItems(Map<EquipmentSlot, EquippedItem> equippedItems) {
        this.equippedItems = equippedItems;
    }

    public Map<Long, InventoryMaterial> getInventoryMaterialMap() {
        if (inventoryMaterialMap == null) {
            inventoryMaterialMap = new HashMap<>();
            for (InventoryMaterial mat : inventoryMaterials) {
                inventoryMaterialMap.put(mat.getMaterial().getId(), mat);
            }
        }
        return inventoryMaterialMap;
    }

}
