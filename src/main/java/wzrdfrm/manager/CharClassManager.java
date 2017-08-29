package wzrdfrm.manager;

import org.apache.commons.collections4.IterableUtils;
import wzrdfrm.model.classes.CharClass;
import wzrdfrm.model.classes.CharClassDefinition;
import wzrdfrm.model.farm.Farm;

import java.util.HashSet;
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

}
