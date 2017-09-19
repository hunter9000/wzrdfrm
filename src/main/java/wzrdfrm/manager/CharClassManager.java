package wzrdfrm.manager;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.MapUtils;
import wzrdfrm.model.classes.CharClass;
import wzrdfrm.model.classes.CharClassDefinition;
import wzrdfrm.model.farm.Farm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CharClassManager {

    private static final String DEFAULT_CHAR_CLASS_NAME = "Farm Hand";

    private Farm farm;
    private Iterable<CharClass> charClasses;

    public CharClassManager(Farm farm, Iterable<CharClass> charClasses) {
        this.farm = farm;
        this.charClasses = charClasses;
    }

    public static Set<CharClass> createDefaultCharClasses(Farm farm, Iterable<CharClassDefinition> classDefs) {
        Set<CharClass> charClasses = new HashSet<>();
        for (CharClassDefinition classDef : classDefs) {
            CharClass charClass = new CharClass();
            charClass.setCharClassDefinition(classDef);
            charClass.setFarm(farm);
            if (classDef.getName().equals(DEFAULT_CHAR_CLASS_NAME)) {
                charClass.setUnlocked(true);
                farm.setCurrCharClass(charClass);
            }
            charClasses.add(charClass);
        }
        return charClasses;
    }

    public CharClass getCharClassById(Long charClassId) {
        return IterableUtils.find(this.charClasses, (CharClass charClass) -> charClass.getId().equals(charClassId));
    }

    private Map<CharClassDefinition, CharClass> map;

    private Map<CharClassDefinition, CharClass> getCharClassMap() {
        if (map == null) {
            map = new HashMap<>();
            MapUtils.populateMap(map, charClasses, (CharClass c) -> c.getCharClassDefinition() );
        }
        return map;
    }

    public void setAllClassUnlockable() {
        for (CharClass charClass : charClasses) {
            // all validations passed, class is unlockable
            charClass.setCanBeUnlocked(isCharClassUnlockable(charClass));
        }
    }

    /** Returns true if the class is not already unlocked, farm has enough unlock orbs, and all prereqs are unlocked. */
    public boolean isCharClassUnlockable(CharClass charClass) {
        // if already unlocked, skip
        if (charClass.getUnlocked()) {
            return false;
        }
        // check if has enough unlock orbs
        if (charClass.getCharClassDefinition().getOrbsToUnlock() > farm.getNumUnlockOrbs()) {      // not enough orbs
            return false;
        }

        // check if class prereqs met
        for (CharClassDefinition prereq : charClass.getCharClassDefinition().getPrereqClasses()) {
            CharClass prereqClass = getCharClassMap().get(prereq);
            if (!prereqClass.getUnlocked()) {
                return false;
            }
        }

        return true;
    }
}
