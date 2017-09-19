package wzrdfrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wzrdfrm.manager.CharClassManager;
import wzrdfrm.manager.ClassLevelManager;
import wzrdfrm.manager.FarmManager;
import wzrdfrm.model.classes.CharClass;
import wzrdfrm.model.classes.CharClassDefinition;
import wzrdfrm.model.farm.*;
import wzrdfrm.model.user.User;
import wzrdfrm.repository.*;
import wzrdfrm.request.PlantCropRequest;
import wzrdfrm.security.BadRequestException;
import wzrdfrm.util.AuthUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api")
public class FarmController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private CharClassDefinitionRepository charClassDefinitionRepository;

    @Autowired
    private CharClassRepository charClassRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private UsableItemRepository usableItemRepository;

    @Autowired
    private HttpServletRequest request;

    @GetMapping(value = "/farm/")
    public Farm getFarmOwnedByUser() {
        User user = AuthUtils.getLoggedInUser(request);

        Farm farm = farmRepository.findAllByOwner(user);

        if (farm != null) {
            ClassLevelManager classLevelManager = new ClassLevelManager(farm);
            classLevelManager.setCurrentClassXpLevels();

            Iterable<CharClass> charClasses = charClassRepository.findAllByFarm(farm);
            classLevelManager.collectCurrentAbilities(charClasses);
        }

        return farm;
    }

    @DeleteMapping(value = "/farm/")
    public void deleteFarm() {
        User user = AuthUtils.getLoggedInUser(request);

        Farm farm = farmRepository.findAllByOwner(user);

        farm.setCurrCharClass(null);

        charClassRepository.deleteByFarm(farm);

        farmRepository.delete(farm);
    }

    @PostMapping(value = "/farm/")
    public Long createFarm() {
        User user = AuthUtils.getLoggedInUser(request);

        Plant startingPlant = plantRepository.findByName(FarmManager.STARTING_SEED_PLANT_NAME);
        UsableItem usableItem = usableItemRepository.findByName(FarmManager.STARTING_USABLE_ITEM_NAME);

        Farm farm = FarmManager.createFarm(user, startingPlant, usableItem);

        farmRepository.save(farm);

        Iterable<CharClassDefinition> classDefs = charClassDefinitionRepository.findAll();
        Set<CharClass> newCharClasses = CharClassManager.createDefaultCharClasses(farm, classDefs);

        charClassRepository.save(newCharClasses);

        farmRepository.save(farm);
        return farm.getId();
    }

    @PutMapping(value = "/farm/plot/{plotId}/plant/")
    public FarmPlot plantCrop(@RequestBody PlantCropRequest plantCropRequest, @PathVariable Long plotId) {
        User user = AuthUtils.getLoggedInUser(request);
        Farm farm = farmRepository.findAllByOwner(user);

        FarmManager farmManager = new FarmManager(farm);

        Plant plant = plantRepository.findOne(plantCropRequest.plantId);
        if (plant == null) {
            throw new BadRequestException();
        }

        FarmPlot farmPlot = farmManager.plantCrop(plotId, plant);

        farmRepository.save(farm);

        return farmPlot;
    }

    @PutMapping(value = "/farm/plot/{plotId}/harvest/")
    public List<HarvestReward> harvestCrop(@PathVariable Long plotId) {
        User user = AuthUtils.getLoggedInUser(request);
        Farm farm = farmRepository.findAllByOwner(user);

        FarmManager farmManager = new FarmManager(farm);

        UsableItem usableItem = usableItemRepository.findByName(FarmManager.STARTING_USABLE_ITEM_NAME);
        List<HarvestReward> harvestedMaterials = farmManager.harvestCrop(plotId, usableItem);

        farmRepository.save(farm);

        return harvestedMaterials;
    }

}
