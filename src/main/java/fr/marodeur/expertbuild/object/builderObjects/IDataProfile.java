package fr.marodeur.expertbuild.object.builderObjects;

import fr.marodeur.expertbuild.object.Message;

import org.bukkit.Bukkit;

import java.util.UUID;

public abstract class IDataProfile {

    private final UUID IProfileID;

    protected IDataProfile(UUID ProfileID) {
        this.IProfileID = ProfileID;
    }

    public UUID getProfileID() {
        return IProfileID;
    }

    public void sendMessage(String path, boolean prefix, String[]... var) {

        if (var.length == 0) {
            new Message.MessageSender(path, prefix).send(Bukkit.getPlayer(getProfileID()));
        } else {
            new Message.MessageSender(path, prefix, var[0]).send(Bukkit.getPlayer(getProfileID()));
        }
    }
}