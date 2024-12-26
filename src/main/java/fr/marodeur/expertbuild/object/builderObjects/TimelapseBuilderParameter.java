package fr.marodeur.expertbuild.object.builderObjects;

import com.sk89q.worldedit.math.BlockVector3;

import java.util.UUID;


public class TimelapseBuilderParameter extends IDataProfile {

    private boolean hasTimelapseRunning;

    private boolean stopTimelapse;

    private final BlockVector3[] selection;


    public TimelapseBuilderParameter(UUID profileID, boolean hasTimelapseRunning, boolean stopTimelapse) {
        super(profileID);

        //this.profileID = profileID;
        this.hasTimelapseRunning = hasTimelapseRunning;
        this.stopTimelapse = stopTimelapse;
        this.selection = new BlockVector3[2];
    }

    public boolean hasTimelapseRunning() {
        return hasTimelapseRunning;
    }

    public TimelapseBuilderParameter setHasTimelapseRunning(boolean hasTimelapseRunning) {
        this.hasTimelapseRunning = hasTimelapseRunning;
        return this;
    }

    public boolean stopTimelapse() {
        return stopTimelapse;
    }

    public TimelapseBuilderParameter setStopTimelapse(boolean stopTimelapse) {
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



    @Override
    public String toString() {
        return "TimelapseBuilder{" +
                "profileID=" + getProfileID().toString() +
                ", hasTimelapseRunning=" + hasTimelapseRunning +
                ", stopTimelapse=" + stopTimelapse +
                ", Corner 1 =" +selection[0] +
                ", Corner 2 =" +selection[1] +
                '}';
    }
}
