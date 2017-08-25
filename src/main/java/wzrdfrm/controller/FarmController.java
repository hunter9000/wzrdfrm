package wzrdfrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wzrdfrm.model.farm.Farm;
import wzrdfrm.model.farm.FarmPlot;
import wzrdfrm.model.user.User;
import wzrdfrm.repository.FarmRepository;
import wzrdfrm.repository.UserRepository;
import wzrdfrm.util.AuthUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

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

    @RequestMapping(name = "/api/farm/", method = RequestMethod.GET)
    public Farm getFarmOwnedByUser() {
        User user = AuthUtils.getLoggedInUser(request);
        return user.getFarm();
    }

    @RequestMapping(name = "/api/farm/", method = RequestMethod.DELETE)
//    @FarmOwnerRequired
    public void deleteFarm() {
        User user = AuthUtils.getLoggedInUser(request);
        user.setFarm(null);
        userRepository.save(user);
    }

    @RequestMapping(name = "/api/farm/", method = RequestMethod.POST)
    public Long createFarm() {
        User user = AuthUtils.getLoggedInUser(request);

        Farm farm = new Farm();
        user.setFarm(farm);
        Set<FarmPlot> farmPlots = new HashSet<>();
        FarmPlot plot = new FarmPlot();
        plot.setFarm(farm);
        farmPlots.add(plot);
        farm.setFarmPlots(farmPlots);

        farmRepository.save(farm);
        return farm.getId();
    }

}
