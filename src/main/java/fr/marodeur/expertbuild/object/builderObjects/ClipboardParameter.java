package fr.marodeur.expertbuild.object.builderObjects;

import com.sk89q.worldedit.session.ClipboardHolder;

import fr.marodeur.expertbuild.object.Flag;
import fr.marodeur.expertbuild.object.Message;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class ClipboardParameter {

    private UUID profileID;

    private ArrayList<ClipboardHolder> clipboardHolders;
    private ArrayList<String> clipboardsName;

    private ArrayList<Flag> flags;
    private boolean isRandomRotation;

    public ClipboardParameter(UUID profileID, ArrayList<ClipboardHolder> clipboardsBlock, ArrayList<String> clipboardsName, ArrayList<Flag> flags, boolean isRandomRotation) {
        this.profileID = profileID;
        this.clipboardHolders = clipboardsBlock;
        this.clipboardsName = clipboardsName;
        this.flags = flags;
        this.isRandomRotation = isRandomRotation;
    }

    public ClipboardParameter addClipboards(ClipboardHolder clipboardsBlock, String clipboardsName, Flag flag) {
        this.clipboardHolders.add(clipboardsBlock);
        this.clipboardsName.add(clipboardsName);
        this.flags.add(flag);
        return this;
    }

    public ClipboardParameter removeClipboards(String clipboardsName) {

        int index = this.clipboardsName.indexOf(clipboardsName);
        this.clipboardsName.remove(index);
        this.clipboardHolders.remove(index);
        this.flags.remove(index);

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

    public ClipboardHolder getClipboardHolder(int index) {
        return this.clipboardHolders.get(index);
    }

    public ArrayList<String> getClipboardsName() {
        return this.clipboardsName;
    }

    public String getClipboardName(int index) {
        return this.clipboardsName.get(index);
    }

    public boolean getClipboardsNameExist(String clipboardsName) {
        return this.clipboardsName.contains(clipboardsName);
    }

    public ClipboardParameter clearAll() {
        this.clipboardsName.clear();
        this.clipboardHolders.clear();
        this.flags.clear();

        return this;
    }

    public Flag getFlag(int index) {
        return flags.get(index);
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
