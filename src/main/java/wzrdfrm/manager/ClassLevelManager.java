package wzrdfrm.manager;

import org.apache.commons.collections4.IterableUtils;
import wzrdfrm.model.classes.AbilityType;
import wzrdfrm.model.classes.CharClass;
import wzrdfrm.model.classes.ClassLevel;
import wzrdfrm.model.farm.Farm;
import wzrdfrm.model.farm.FarmPlot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassLevelManager {

    private static final int MAX_LEVEL = 10;

    private Farm farm;

    public ClassLevelManager(Farm farm) {
        this.farm = farm;
    }

    /** Looks at all the class abilites, and collects the ones that the farm currently has active. */
    public void collectCurrentAbilities(Iterable<CharClass> charClasses) {
        List<AbilityType> abilityTypes = new ArrayList<>();
        for (CharClass charClass : charClasses) {
            // if this is the current class, get all abilites
            if (charClass.equals(farm.getCurrCharClass())) {
                // for each class level below or at the current level, if it has an ability, add to list
                for (Map.Entry<Integer, ClassLevel> entry : charClass.getCharClassDefinition().getClassLevels().entrySet()) {
                    if (entry.getKey() <= charClass.getCurrentLevel() && entry.getValue().getAbilityType() != null) {
                        abilityTypes.add(entry.getValue().getAbilityType());
                    }
                }
            }
            // else, get the permanent abilities that are under the current level
            else {
                for (Map.Entry<Integer, ClassLevel> entry : charClass.getCharClassDefinition().getClassLevels().entrySet()) {
                    if (entry.getKey() <= charClass.getCurrentLevel() && entry.getValue().getAbilityType() != null) {
                        abilityTypes.add(entry.getValue().getAbilityType());
                    }
                }
            }
        }

        farm.setAbilities(abilityTypes);
    }

    public void addXPToCurrentClass(Integer xp) {
        CharClass charClass = this.farm.getCurrCharClass();

        if (charClass.getCurrentLevel() == MAX_LEVEL) {
            return;     // nothing to do, already maxed
        }

        Integer newXP = charClass.getCurrentXP() + xp;
        ClassLevel nextClassLevel = getNextClassLevel(charClass);
        if (newXP >= nextClassLevel.getXpRequired()) {       // level up!
            newXP -= nextClassLevel.getXpRequired();     // remove this level's xp req from the new value
            charClass.setCurrentLevel(charClass.getCurrentLevel() + 1);
            this.handleNewAbilityType(nextClassLevel.getAbilityType());
        }

        charClass.setCurrentXP(newXP);
    }

    /** Calculates and sets the xp needed to level for the current class only. */
    public void setCurrentClassXpLevels() {
        setXpNeededToLevel(farm.getCurrCharClass());
    }

    /** Calculates and sets the xp needed to level for all the classes. */
    public void setAllClassXpLevels(Iterable<CharClass> charClasses) {
        for (CharClass charClass : charClasses) {
            setXpNeededToLevel(charClass);
        }
    }

    private void setXpNeededToLevel(CharClass charClass) {
        if (charClass.getCurrentLevel() != MAX_LEVEL) {
            Integer xpNeeded = getNextClassLevel(charClass).getXpRequired();
            charClass.setXpNeededToLevel(xpNeeded);
        }
    }

    private ClassLevel getNextClassLevel(CharClass charClass) {
        return charClass.getCharClassDefinition().getClassLevels().get(charClass.getCurrentLevel() + 1);
    }

    /** When leveling, handle any actions associated with the new ability type that was just gotten. */
    private void handleNewAbilityType(AbilityType abilityType) {
        if (abilityType == null) {
            return;     // this level didn't provide an ability
        }
        switch (abilityType) {
            case UNLOCK_PLOT_2:
                unlockPlot(0, 1);
                break;
            case UNLOCK_PLOT_3:
                unlockPlot(1, 0);
                break;
            case UNLOCK_PLOT_4:
                unlockPlot(1, 1);
                break;
        }
    }

    private void unlockPlot(int row, int col) {
        FarmPlot farmPlot = IterableUtils.find(farm.getFarmPlots(), (FarmPlot fp) -> fp.getRow().equals(row) && fp.getCol().equals(col) );
        farmPlot.setUnlocked(true);
    }

}
