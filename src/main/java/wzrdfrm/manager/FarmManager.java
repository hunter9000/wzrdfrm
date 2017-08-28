package wzrdfrm.manager;

import org.apache.commons.collections4.IterableUtils;
import wzrdfrm.model.farm.Farm;
import wzrdfrm.model.farm.FarmPlot;
import wzrdfrm.model.farm.PlantType;
import wzrdfrm.model.user.User;
import wzrdfrm.security.BadRequestException;

import java.util.*;

public class FarmManager {

    private static final int NUM_PLOT_ROWS = 2;
    private static final int NUM_PLOT_COLS = 2;

    private Farm farm;

    public FarmManager(Farm farm) {
        this.farm = farm;
    }

    public static Farm createFarm(User user) {
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

        return farm;
    }

    public FarmPlot plantCrop(Long plotId, PlantType plantType) {
        FarmPlot farmPlot = getFarmPlotById(plotId);
        if (farmPlot == null) {
            throw new BadRequestException();
        }

        if (farmPlot.getPlantType() != null) {
            throw new BadRequestException();
        }
        farmPlot.setPlantType(plantType);
        farmPlot.setPlantDate(new Date());
        return farmPlot;
    }

    public List<Object> harvestCrop(Long plotId) {
        FarmPlot farmPlot = getFarmPlotById(plotId);
        if (farmPlot == null) {
            throw new BadRequestException();
        }

        if (farmPlot.getPlantType() == null) {
            throw new BadRequestException();
        }

        List<Object> harvestedMaterials = new ArrayList<>();

        harvestedMaterials.add(PlantType.WEEDS);

        farmPlot.setPlantDate(null);
        farmPlot.setPlantType(null);

        return harvestedMaterials;
    }

    private FarmPlot getFarmPlotById(Long plotId) {
        return IterableUtils.find(farm.getFarmPlots(), (FarmPlot plot) -> plot.getId().equals(plotId));
    }
}
