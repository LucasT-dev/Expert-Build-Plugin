package fr.marodeur.expertbuild.object.builderObjects;

import java.util.UUID;

public class LeatherParameter extends IDataProfile {

    public short RED;
    public short GREEN;
    public short BLUE;

    public LeatherParameter(UUID profileID, short RED, short GREEN, short BLUE) {
        super(profileID);

        this.RED = RED;
        this.GREEN = GREEN;
        this.BLUE = BLUE;
    }

    public short getRed() {
        return RED;
    }

    public void setRed(short RED) {
        this.RED = RED;
    }

    public short getGreen() {
        return GREEN;
    }

    public void setGreen(short GREEN) {
        this.GREEN = GREEN;
    }

    public short getBlue() {
        return BLUE;
    }

    public void setBlue (short BLUE) {
        this.BLUE = BLUE;
    }
}
