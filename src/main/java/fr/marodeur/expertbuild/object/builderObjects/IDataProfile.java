package fr.marodeur.expertbuild.object.builderObjects;

import fr.marodeur.expertbuild.object.Message;

import org.bukkit.Bukkit;

import java.util.UUID;

public abstract class IDataProfile {

    private UUID IProfileID;

    protected IDataProfile(UUID ProfileID) {
        IProfileID = ProfileID;
    }

    public UUID getProfileID() {
        return IProfileID;
    }

    void setProfileID(UUID ProfileID) {
        IProfileID = ProfileID;
    }


    public void sendMessage(String path, boolean prefix, String[]... var) {

        if (var.length == 0) {
            new Message.MessageSender(path, prefix).send(Bukkit.getPlayer(getProfileID()));
        } else {
            new Message.MessageSender(path, prefix, var[0]).send(Bukkit.getPlayer(getProfileID()));
        }
    }
}