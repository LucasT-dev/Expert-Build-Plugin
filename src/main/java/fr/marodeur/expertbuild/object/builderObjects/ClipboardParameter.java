package fr.marodeur.expertbuild.object.builderObjects;

import com.sk89q.worldedit.session.ClipboardHolder;
import fr.marodeur.expertbuild.object.Message;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class ClipboardParameter {

    private UUID profileID;

    private ArrayList<ClipboardHolder> clipboardHolders;
    private ArrayList<String> clipboardsName;
    private boolean isRandomRotation;

    public ClipboardParameter(UUID profileID, ArrayList<ClipboardHolder> clipboardsBlock, ArrayList<String> clipboardsName, boolean isRandomRotation) {
        this.profileID = profileID;
        this.clipboardHolders = clipboardsBlock;
        this.clipboardsName = clipboardsName;
        this.isRandomRotation = isRandomRotation;
    }

    public ClipboardParameter setClipboardHolders(ArrayList<ClipboardHolder> clipboardHolders) {
        this.clipboardHolders = clipboardHolders;
        return this;
    }

    public ClipboardParameter setClipboardsName(ArrayList<String> clipboardsName) {
        this.clipboardsName = clipboardsName;
        return this;
    }

    public ClipboardParameter addClipboards(ClipboardHolder clipboardsBlock, String clipboardsName) {
        this.clipboardHolders.add(clipboardsBlock);
        this.clipboardsName.add(clipboardsName);
        return this;
    }

    public ClipboardParameter removeClipboards(String clipboardsName) {

        int index = this.clipboardsName.indexOf(clipboardsName);
        this.clipboardsName.remove(index);
        this.clipboardHolders.remove(index);

        return this;
    }

    public boolean isRandomRotation() {
        return isRandomRotation;
    }

    public ClipboardParameter setRandomRotation(boolean randomRotation) {
        isRandomRotation = randomRotation;
        return this;
    }

    public ArrayList<ClipboardHolder> getClipboardHolders() {
        return this.clipboardHolders;
    }

    public ArrayList<String> getClipboardsName() {
        return this.clipboardsName;
    }

    public boolean getClipboardsNameExist(String clipboardsName) {
        return this.clipboardsName.contains(clipboardsName);
    }

    public ClipboardParameter clearAll() {
        this.clipboardsName.clear();
        this.clipboardHolders.clear();

        return this;
    }

    public <T> void sendMessage(String path, boolean prefix, String[]... var) {

        if (var.length == 0) {
            new Message.MessageSender(path, prefix).send(Bukkit.getPlayer(profileID));
        } else {
            new Message.MessageSender(path, prefix, var[0]).send(Bukkit.getPlayer(profileID));
        }
    }

    @Override
    public String toString() {

        AtomicReference<String> name = new AtomicReference<>("");

        clipboardsName.forEach(s -> name.set(name + "{" + s + "},"));

        return "ClipboardParameter{" +
                "clipboardsBlock.size=" + clipboardHolders.size() +
                ", clipboardsName=" + name +
                ", isRandomRotation=" + isRandomRotation +
                '}';
    }
}
