package wzrdfrm.model.classes;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AbilityType {
    UNLOCK_PLOT_2("Unlock Second Plot", "Makes a second farm plot available to plant"),
    UNLOCK_PLOT_3("Unlock Third Plot", "Makes a third farm plot available to plant"),
    UNLOCK_PLOT_4("Unlock Fourth Plot", "Makes a fourth farm plot available to plant");

    private String name;
    private String description;

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    AbilityType(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
