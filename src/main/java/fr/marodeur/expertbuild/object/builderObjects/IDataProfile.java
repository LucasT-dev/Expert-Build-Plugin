package fr.marodeur.expertbuild.object.builderObjects;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.Configuration;
import fr.marodeur.expertbuild.object.Message;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.logging.Logger;

public abstract class IDataProfile {

    private static final Configuration CONFIG;
    private static final Logger LOG;

    static {
        CONFIG = Main.getConfiguration();
        LOG = Main.getInstance().getLogger();
    }

    private final UUID IProfileID;

    protected IDataProfile(UUID ProfileID) { this.IProfileID = ProfileID; }
    public UUID getProfileID() { return IProfileID; }

    public Player getPlayer() { return Bukkit.getPlayer(IProfileID); }

    public Configuration getConfig() { return CONFIG; }

    public Logger getLogger() { return LOG; }

    public void sendMessage(String path, boolean prefix, String[]... var) {

        if (var.length == 0) {
            new Message.MessageSender(path, prefix).send(Bukkit.getPlayer(getProfileID()));
        } else {
            new Message.MessageSender(path, prefix, var[0]).send(Bukkit.getPlayer(getProfileID()));
        }
    }
}