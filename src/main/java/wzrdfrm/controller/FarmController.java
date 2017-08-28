package wzrdfrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wzrdfrm.manager.FarmManager;
import wzrdfrm.model.farm.Farm;
import wzrdfrm.model.farm.FarmPlot;
import wzrdfrm.model.user.User;
import wzrdfrm.repository.FarmRepository;
import wzrdfrm.repository.UserRepository;
import wzrdfrm.request.PlantCropRequest;
import wzrdfrm.util.AuthUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class FarmController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private HttpServletRequest request;

//    @RequestMapping(name = "/api/farm/{farmId}/", method = RequestMethod.GET)
//    @FarmOwnerRequired
//    public Farm getFarm() {
//        Farm farm = AuthUtils.getFarm(request);
//
//        return farm;
//    }

    @RequestMapping(value = "/api/farm/", method = RequestMethod.GET)
    public Farm getFarmOwnedByUser() {
        User user = AuthUtils.getLoggedInUser(request);
        return farmRepository.findAllByOwner(user);
    }

    @RequestMapping(value = "/api/farm/", method = RequestMethod.DELETE)
//    @FarmOwnerRequired
    public void deleteFarm() {
        User user = AuthUtils.getLoggedInUser(request);

        Farm farm = farmRepository.findAllByOwner(user);

        farmRepository.delete(farm);
    }

    @RequestMapping(value = "/api/farm/", method = RequestMethod.POST)
    public Long createFarm() {
        User user = AuthUtils.getLoggedInUser(request);

        Farm farm = FarmManager.createFarm(user);

        farmRepository.save(farm);
        return farm.getId();
    }

    @RequestMapping(value = "/api/farm/plot/{plotId}/plant/", method = RequestMethod.PUT)
    public FarmPlot plantCrop(@RequestBody PlantCropRequest plantCropRequest, @PathVariable Long plotId) {
        User user = AuthUtils.getLoggedInUser(request);
        Farm farm = farmRepository.findAllByOwner(user);

        FarmManager farmManager = new FarmManager(farm);

//        if (plantCropRequest.requestType == PlantCropRequest.RequestType.PLANT) {
        FarmPlot farmPlot = farmManager.plantCrop(plotId, plantCropRequest.plantType);
//        }
//        else if (plantCropRequest.requestType == PlantCropRequest.RequestType.HARVEST) {
//            farmPlot = farmManager.harvestCrop(plotId);
//        }

        farmRepository.save(farm);

        return farmPlot;
    }


    @RequestMapping(value = "/api/farm/plot/{plotId}/harvest/", method = RequestMethod.PUT)
    public List<Object> harvestCrop(@PathVariable Long plotId) {
        User user = AuthUtils.getLoggedInUser(request);
        Farm farm = farmRepository.findAllByOwner(user);

        FarmManager farmManager = new FarmManager(farm);

//        if (plantCropRequest.requestType == PlantCropRequest.RequestType.PLANT) {
//        FarmPlot farmPlot = farmManager.plantCrop(plotId, plantCropRequest.plantType);
//        }
//        else if (plantCropRequest.requestType == PlantCropRequest.RequestType.HARVEST) {
        List<Object> harvestedMaterials = farmManager.harvestCrop(plotId);
//        }

        farmRepository.save(farm);

        return harvestedMaterials;
    }

}
