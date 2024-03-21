package fr.marodeur.expertbuild.object.builderObjects;

import com.sk89q.worldedit.math.BlockVector3;
import fr.marodeur.expertbuild.object.BlockVec4;

import java.util.UUID;


public class TimelapseBuilder {

    private UUID profileID;

    private boolean hasTimelapseRunning;

    private boolean stopTimelapse;

    private BlockVector3[] selection;


    public TimelapseBuilder(UUID profileID, boolean hasTimelapseRunning, boolean stopTimelapse) {
        this.profileID = profileID;
        this.hasTimelapseRunning = hasTimelapseRunning;
        this.stopTimelapse = stopTimelapse;
        this.selection = new BlockVector3[2];
    }

    public UUID profileID() {
        return profileID;
    }

    public TimelapseBuilder setProfileID(UUID profileID) {
        this.profileID = profileID;
        return this;
    }

    public boolean hasTimelapseRunning() {
        return hasTimelapseRunning;
    }

    public TimelapseBuilder setHasTimelapseRunning(boolean hasTimelapseRunning) {
        this.hasTimelapseRunning = hasTimelapseRunning;
        return this;
    }

    public boolean stopTimelapse() {
        return stopTimelapse;
    }

    public TimelapseBuilder setStopTimelapse(boolean stopTimelapse) {
        this.stopTimelapse = stopTimelapse;
        return this;
    }

    public BlockVector3[] selection() {
        return selection;
    }

    public void setSelection(BlockVector3 corner1, BlockVector3 corner2) {
        this.selection[0] = corner1;
        this.selection[1] = corner2;
    }
}
