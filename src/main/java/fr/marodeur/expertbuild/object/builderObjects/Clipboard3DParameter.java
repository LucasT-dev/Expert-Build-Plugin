package fr.marodeur.expertbuild.object.builderObjects;

import com.sk89q.worldedit.session.ClipboardHolder;
import fr.marodeur.expertbuild.object.Flag;

import java.util.UUID;

public class Clipboard3DParameter extends IDataProfile {

    private ClipboardHolder clipboardHolder;
    private  Flag flag;

    public Clipboard3DParameter(UUID ProfileID, ClipboardHolder clipboardHolder, Flag flag) {
        super(ProfileID);

        this.clipboardHolder = clipboardHolder;
        this.flag = flag;
    }

    public Clipboard3DParameter setClipboardHolder(ClipboardHolder clipboardHolder) {
        this.clipboardHolder = clipboardHolder;
        return this;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public ClipboardHolder getClipboardHolder() {
        return this.clipboardHolder;
    }

    public Flag getFlag() {
        return this.flag;
    }
}

