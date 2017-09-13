package wzrdfrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wzrdfrm.manager.CharClassManager;
import wzrdfrm.manager.ClassLevelManager;
import wzrdfrm.model.classes.CharClass;
import wzrdfrm.model.farm.Farm;
import wzrdfrm.model.user.User;
import wzrdfrm.repository.CharClassRepository;
import wzrdfrm.repository.FarmRepository;
import wzrdfrm.repository.UserRepository;
import wzrdfrm.request.CharClassRequest;
import wzrdfrm.response.CharClassInfoResponse;
import wzrdfrm.security.BadRequestException;
import wzrdfrm.util.AuthUtils;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CharClassController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private CharClassRepository charClassRepository;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/api/farm/classes/", method = RequestMethod.GET)
    public CharClassInfoResponse getCharClassInfo() {
        User user = AuthUtils.getLoggedInUser(request);
        Farm farm = farmRepository.findAllByOwner(user);

        Iterable<CharClass> charClasses = charClassRepository.findAllByFarm(farm);

        ClassLevelManager classLevelManager = new ClassLevelManager(farm);
        classLevelManager.setAllClassXpLevels(charClasses);

        CharClassInfoResponse charClassInfoResponse = new CharClassInfoResponse(farm.getCurrCharClass(), charClasses);

        return charClassInfoResponse;
    }

    @RequestMapping(value = "/api/farm/classes/", method = RequestMethod.PUT)
    public CharClass changeCharClass(@RequestBody CharClassRequest charClassRequest) {
        User user = AuthUtils.getLoggedInUser(request);
        Farm farm = farmRepository.findAllByOwner(user);

        // get the class requested
        Iterable<CharClass> charClasses = charClassRepository.findAllByFarm(farm);

        CharClassManager charClassManager = new CharClassManager(farm, charClasses);
        CharClass charClass = charClassManager.getCharClassById(charClassRequest.charClassId);

        if (charClass == null) {
            throw new BadRequestException();
        }

        // check to see if it can be switched to
        if (!charClass.getUnlocked()) {
            throw new BadRequestException();
        }

        farm.setCurrCharClass(charClass);

        farmRepository.save(farm);

        return charClass;
    }

    @RequestMapping(value = "/api/farm/classes/", method = RequestMethod.POST)
    public CharClass unlockCharClass(@RequestBody CharClassRequest charClassRequest) {
        User user = AuthUtils.getLoggedInUser(request);
        Farm farm = farmRepository.findAllByOwner(user);

        // get the class requested, check if it can be purchased
        Iterable<CharClass> charClasses = charClassRepository.findAllByFarm(farm);

        CharClassManager charClassManager = new CharClassManager(farm, charClasses);
        CharClass charClass = charClassManager.getCharClassById(charClassRequest.charClassId);

        if (charClass == null) {
            throw new BadRequestException();
        }

        // TODO can this class be unlocked?

        // unlock the class
        charClass.setUnlocked(true);

        charClassRepository.save(charClass);

        return charClass;
    }
}
