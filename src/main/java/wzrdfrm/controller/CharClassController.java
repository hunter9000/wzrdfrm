package wzrdfrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/api/farm/classes/")
    public CharClassInfoResponse getCharClassInfo() {
        User user = AuthUtils.getLoggedInUser(request);
        Farm farm = farmRepository.findAllByOwner(user);

        Iterable<CharClass> charClasses = charClassRepository.findAllByFarm(farm);

        ClassLevelManager classLevelManager = new ClassLevelManager(farm);
        CharClassManager charClassManager = new CharClassManager(farm, charClasses);

        // calculate xp needed for each class to level up
        classLevelManager.setAllClassXpLevels(charClasses);

        // calculate if each class can be unlocked
        charClassManager.setAllClassUnlockable();

        CharClassInfoResponse charClassInfoResponse = new CharClassInfoResponse(farm.getCurrCharClass(), charClasses, farm.getNumUnlockOrbs());

        return charClassInfoResponse;
    }

    @PutMapping(value = "/api/farm/classes/")
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

    @PostMapping(value = "/api/farm/classes/")
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

        if (!charClassManager.isCharClassUnlockable(charClass)) {
            throw new BadRequestException();
        }

        // unlock the class
        charClass.setUnlocked(true);
        farm.setNumUnlockOrbs(farm.getNumUnlockOrbs() - charClass.getCharClassDefinition().getOrbsToUnlock());

        charClassRepository.save(charClass);

        return charClass;
    }
}
