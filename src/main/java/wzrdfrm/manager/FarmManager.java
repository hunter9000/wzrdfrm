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

    private Farm farm;

    public FarmManager(Farm farm) {
        this.farm = farm;
    }

    public static Farm createFarm(User user, Plant startingPlant) {
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

        return farm;
    }

    public FarmPlot plantCrop(Long plotId, Plant plant) {
        // TODO check seed inventory for quantity

        FarmPlot farmPlot = getFarmPlotById(plotId);
        if (farmPlot == null) {
            throw new BadRequestException();
        }

        if (farmPlot.getPlant() != null) {
            throw new BadRequestException();
        }
        farmPlot.setPlant(plant);
        farmPlot.setPlantDate(new Date());

        // TODO decrement the inventory here

        return farmPlot;
    }

    public List<Object> harvestCrop(Long plotId) {
        FarmPlot farmPlot = getFarmPlotById(plotId);
        if (farmPlot == null) {
            throw new BadRequestException();
        }

        if (farmPlot.getPlant() == null) {
            throw new BadRequestException();
        }

        List<Object> harvestedMaterials = new ArrayList<>();

        harvestedMaterials.add(PlantType.WEEDS);

        farmPlot.setPlantDate(null);
        farmPlot.setPlant(null);

        return harvestedMaterials;
    }

    private FarmPlot getFarmPlotById(Long plotId) {
        return IterableUtils.find(farm.getFarmPlots(), (FarmPlot plot) -> plot.getId().equals(plotId));
    }
}
