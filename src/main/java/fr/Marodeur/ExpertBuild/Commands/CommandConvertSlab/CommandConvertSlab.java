package fr.Marodeur.ExpertBuild.Commands.CommandConvertSlab;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;
import fr.Marodeur.ExpertBuild.API.Exception.IncompleteSelectionException;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.MsgEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandConvertSlab implements CommandExecutor {

    private static final List<BlockVec4> bv4 = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, @NotNull String[] args) {

        if (!(s instanceof Player p)) {
            new UtilsFAWE().sendMessage(s, MsgEnum.CONSOLE_NOT_EXECUTE_CMD);
            return false;
        }

        if (!p.isOp() || !p.hasPermission("expperlin.use")) {
            new UtilsFAWE(p).sendMessage(MsgEnum.NOT_PERM);
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("convertslab")) {

            try {
                if (!new UtilsFAWE(p).isValidSelection(RegionSelectorType.CUBOID)) throw
                        new IncompleteSelectionException(p, MsgEnum.ERROR_SELECTION.getPrefix(), RegionSelectorType.CUBOID);
            } catch (IncompleteSelectionException e) {
                return false;
            }

            BukkitPlayer actor = BukkitAdapter.adapt(p);
            LocalSession session = actor.getSession();
            EditSession editSession = session.createEditSession(actor);
            Region r = actor.getSelection();

            r.spliterator().forEachRemaining(bv3 -> editSession.getFullBlock(bv3).getStates().values().forEach(o -> {

                if (o.equals("double")) {
                    bv4.add(new BlockVec4(bv3, Material.matchMaterial(editSession.getBlock(bv3).getBlockType().toString().replace("slab", "planks"))));
                }
            }));

            new UtilsFAWE(p).setBlockListSimple(p, bv4);
            p.sendMessage(Main.prefix + bv4.size() + " blocks have been modified");
            bv4.clear();
        }
        return false;
    }
}
