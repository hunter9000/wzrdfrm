package avalon.controller;

import avalon.interceptor.CharacterSheetOwnerRequired;
import avalon.manager.CharacterManager;
import avalon.model.character.Character;
import avalon.model.user.User;
import avalon.repository.CharRepository;
import avalon.repository.UserRepository;
import avalon.request.EditCharRequest;
import avalon.request.NewCharacterRequest;
import avalon.response.SuccessResponse;
import avalon.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CharsController {

    @Autowired
    private CharRepository charRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CharacterManager characterManager;

    /** get all chars for the user */
    @RequestMapping(value="/api/char/", method=RequestMethod.GET)
    public List<Character> getChars() {
        long userId = AuthUtils.getUserId(request);

        List<Character> r = charRepository.findByUserId(userId);
        return r;
    }

    /** get the char w/ equipment, inv, etc. add charId to jwt for reuse throughout angular app */
    @RequestMapping(value="/api/char/{charId}/", method=RequestMethod.GET)
    @CharacterSheetOwnerRequired
    public Character getChar(@PathVariable long charId) {
//        JwtSubject token = (JwtSubject)request.getAttribute("jwtToken");
//        User user = userRepository.findOne(AuthUtils.getUserId(request));
//        Character character = (Character)request.getAttribute(AuthUtils.CHARACTER_NAME);

        Character character = AuthUtils.getCharacter(request);
        characterManager.processRecipes(character);

//        Character character = charRepository.findById(charId);
        return character;
    }

    /** Create a character */
    @RequestMapping(value="/api/char/", method=RequestMethod.POST)
    public SuccessResponse createChar(@RequestBody NewCharacterRequest newCharacterRequest) {
        User user = AuthUtils.getLoggedInUser(request);

//        long userId = AuthUtils.getUserId(request);
//
//        User user = userRepository.findOne(userId);

        Character character = characterManager.createCharacter(user, newCharacterRequest);

        charRepository.save(character);
        return new SuccessResponse(true, "created character");
    }

    @RequestMapping(value = "/api/char/{charId}/", method = RequestMethod.PATCH)
    @CharacterSheetOwnerRequired
    public Character editCharacter(@RequestBody EditCharRequest editCharRequest, @PathVariable Long charId) {
        return getChar(charId);
    }

    /** delete char */
    @RequestMapping(value="/api/char/{charId}/", method=RequestMethod.DELETE)
    @CharacterSheetOwnerRequired
    public SuccessResponse deleteChar(@PathVariable long charId) {
//        Character character = (Character)request.getAttribute(AuthUtils.CHARACTER_NAME);
        Character character = AuthUtils.getCharacter(request);

        return new SuccessResponse(false, "not implemented");
    }

}
