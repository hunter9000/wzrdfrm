package avalon.controller;

import avalon.model.Skill;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class SkillsController {

    @RequestMapping(value="/api/skills", method=RequestMethod.GET)
    // get all the skills
    public List<Skill> getAllSkills() {
        return null;
    }

    @RequestMapping(value="/api/skills/{charId}", method=RequestMethod.GET)
    // get this char's owned skills
    public List<Skill> getCharSkills() {
        return null;
    }

    @RequestMapping(value="/api/skills", method=RequestMethod.POST)
    // purchase the given skill
    public void purchaseSkill() {

    }

}
