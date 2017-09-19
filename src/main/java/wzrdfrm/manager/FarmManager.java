package wzrdfrm.manager;

import org.apache.commons.collections4.IterableUtils;
import wzrdfrm.model.farm.*;
import wzrdfrm.model.user.User;
import wzrdfrm.security.BadRequestException;

import java.util.*;

public class FarmManager {

    private static final int NUM_PLOT_ROWS = 2;
    private static final int NUM_PLOT_COLS = 2;

    public static final String STARTING_SEED_PLANT_NAME = "Weeds";
    public static final String STARTING_USABLE_ITEM_NAME = "Small Fertilizer";

    private Farm farm;

    public FarmManager(Farm farm) {
        this.farm = farm;
    }

    public static Farm createFarm(User user, Plant startingPlant, UsableItem startingUsableItem) {
        Farm farm = new Farm();
        farm.setOwner(user);

        // create plots
        Set<FarmPlot> farmPlots = new HashSet<>();
        for (int row=0; row<NUM_PLOT_ROWS; row++) {
            for (int col=0; col<NUM_PLOT_COLS; col++) {
                FarmPlot plot = new FarmPlot();
                plot.setFarm(farm);
                plot.setRow(row);
                plot.setCol(col);
                // set top right plot to unlocked by default
                if (row == 0 && col == 0) {
                    plot.setUnlocked(true);
                }
                farmPlots.add(plot);
            }
        }
        farm.setFarmPlots(farmPlots);

        // create default seeds
        Set<Seed> seeds = new HashSet<>();
        Seed seed = new Seed();
        seed.setPlant(startingPlant);
        seed.setQuantity(5);
        seed.setFarm(farm);
        seeds.add(seed);
        farm.setSeedInventory(seeds);

        // create default consumable inventory
        Set<Consumable> consumables = new HashSet<>();
        Consumable consumable = new Consumable();
        consumable.setFarm(farm);
        consumable.setQuantity(2);
        consumable.setUsableItem(startingUsableItem);
        consumables.add(consumable);
        farm.setConsumableInventory(consumables);

        return farm;
    }

    /** Consumes 1 seed of the matching plant type and plants it in the plot */
    public FarmPlot plantCrop(Long plotId, Plant plant) {
        // TODO check seed inventory for quantity
        Seed plantingSeed = IterableUtils.find(farm.getSeedInventory(), (Seed seed) -> seed.getPlant().getId().equals(plant.getId()) );
        if (plantingSeed == null) {
            throw new BadRequestException();
        }
        if (plantingSeed.getQuantity() < 1) {
            throw new BadRequestException();
        }

        FarmPlot farmPlot = getFarmPlotById(plotId);
        if (farmPlot == null) {
            throw new BadRequestException();
        }
        if (farmPlot.getPlant() != null) {
            throw new BadRequestException();
        }

        farmPlot.setPlant(plant);
        farmPlot.setPlantDate(new Date());

        this.removeSeedFromInventory(plantingSeed, 1);

        return farmPlot;
    }

    public List<HarvestReward> harvestCrop(Long plotId, UsableItem usableItem) {
        FarmPlot farmPlot = getFarmPlotById(plotId);
        if (farmPlot == null) {
            throw new BadRequestException();
        }

        if (farmPlot.getPlant() == null) {
            throw new BadRequestException();
        }

        List<HarvestReward> harvestedMaterials = new ArrayList<>();

        // get seed rewards
        // TODO make 1 seed of the current plant type for now, make this more random later
        Plant currentPlant = farmPlot.getPlant();
        Seed seed = new Seed();
        seed.setPlant(currentPlant);
        seed.setFarm(farm);
        seed.setQuantity(1);
        this.addSeedToInventory(seed);
        harvestedMaterials.add(seed);

        // get consumable rewards
        Consumable consumable = new Consumable();
        consumable.setFarm(farm);
        // TODO how to get the usable items to choose from?
        consumable.setUsableItem(usableItem);
        consumable.setQuantity(1);
        this.addConsumableToInventory(consumable);
        harvestedMaterials.add(consumable);

        // xp orbs
        // TODO randomize this based on range and crop harvested
        Integer numXPOrbs = 2;
        ClassLevelManager classLevelManager = new ClassLevelManager(farm);
        classLevelManager.addXPToCurrentClass(numXPOrbs);
        harvestedMaterials.add(new XPHarvestReward(numXPOrbs));

        Integer numClassOrbs = 1;
        farm.setNumUnlockOrbs(farm.getNumUnlockOrbs() + numClassOrbs);
        harvestedMaterials.add(new ClassOrbHarvestReward(numClassOrbs));

        farmPlot.setPlantDate(null);
        farmPlot.setPlant(null);

        return harvestedMaterials;
    }

    private FarmPlot getFarmPlotById(Long plotId) {
        return IterableUtils.find(farm.getFarmPlots(), (FarmPlot plot) -> plot.getId().equals(plotId));
    }

    /** Takes the given seed and either combines it's quantity to an existing seed, or adds it to the list */
    private void addSeedToInventory(Seed newSeed) {
        Seed matchingSeed = IterableUtils.find(farm.getSeedInventory(), (Seed seed) -> newSeed.getPlant().getId().equals(newSeed.getPlant().getId()) );
        if (matchingSeed == null) {
            farm.getSeedInventory().add(newSeed);
        }
        else {
            matchingSeed.setQuantity(matchingSeed.getQuantity() + newSeed.getQuantity());
        }
    }

    /** Takes the given quantity from the given seed, removing the seed from inventory if new quantity is 0 */
    private void removeSeedFromInventory(Seed seed, Integer quantity) {
        if (seed.getQuantity() < quantity) {
            throw new BadRequestException();
        }
        Integer newQuantity = seed.getQuantity() - quantity;
        if (newQuantity == 0) {
            farm.getSeedInventory().remove(seed);
        }
        else {
            seed.setQuantity(newQuantity);
        }
    }

    /** Takes the given consumable and either combines it's quantity to an existing consumable, or adds it to the list */
    private void addConsumableToInventory(Consumable newConsumable) {
        Consumable matchingConsumable = IterableUtils.find(farm.getConsumableInventory(), (Consumable consumable) -> newConsumable.getUsableItem().getId().equals(newConsumable.getUsableItem().getId()) );
        if (matchingConsumable == null) {
            farm.getConsumableInventory().add(newConsumable);
        }
        else {
            matchingConsumable.setQuantity(matchingConsumable.getQuantity() + newConsumable.getQuantity());
        }
    }

}
