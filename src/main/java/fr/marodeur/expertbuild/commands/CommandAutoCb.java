package fr.marodeur.expertbuild.commands;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.Message;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandAutoCb implements CommandExecutor {

    public static List<String> list = new ArrayList<>();
    public static int state;

    public static UUID uuid;
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param s  Source of the command
     * @param cmd Command which was executed
     * @param msg   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, @NotNull String[] args) {

        if (! (s instanceof Player p)) {
            s.sendMessage(new Message.MessageSender("expbuild.message.permission.console_not_execute_cmd", true).getMessage());
            return false;
        }

        if (!p.hasPermission("exp.command.autocb")) {
            s.sendMessage(new Message.MessageSender("expbuild.message.permission.no_permission_node", true, new String[]{"exp.command.autocb"}).getMessage());
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("autocb")) {

            try {
                if (uuid.equals(p.getUniqueId())) {
                    p.sendMessage(new Message.MessageSender("expbuild.message.commands.disable", true, new String[]{"Autocb"}).getMessage());
                    uuid = null;
                }
            } catch (NullPointerException e) {

                Location loc = p.getTargetBlock(null, 250).getLocation().clone();
                Block block = loc.getBlock();

                if (block.getType() != Material.COMMAND_BLOCK) {
                    p.sendMessage(Main.prefix + "get command_block for execute");
                    return false;
                }

                CommandBlock commandBlock = (CommandBlock) block.getState();

                //split

                list = List.of(commandBlock.getCommand().split(";"));

                p.sendMessage(Main.prefix + "Autocb enable: nmb commande :" + list.size());

                uuid = p.getUniqueId();
                state = 0;
            }
        }
        return false;
    }
}
