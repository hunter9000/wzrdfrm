package wzrdfrm.response;

import wzrdfrm.model.classes.CharClass;

public class CharClassInfoResponse {

    public CharClass currCharClass;
    public Iterable<CharClass> allCharClasses;
    public Integer numClassUnlockOrbs;

    public CharClassInfoResponse(CharClass currCharClass, Iterable<CharClass> allCharClasses, Integer numClassUnlockOrbs) {
        this.currCharClass = currCharClass;
        this.allCharClasses = allCharClasses;
        this.numClassUnlockOrbs = numClassUnlockOrbs;
    }

    public CharClass getCurrCharClass() {
        return currCharClass;
    }
    public void setCurrCharClass(CharClass currCharClass) {
        this.currCharClass = currCharClass;
    }

    public Iterable<CharClass> getAllCharClasses() {
        return allCharClasses;
    }
    public void setAllCharClasses(Iterable<CharClass> allCharClasses) {
        this.allCharClasses = allCharClasses;
    }
}
