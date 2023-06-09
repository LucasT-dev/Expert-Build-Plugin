package fr.Marodeur.ExpertBuild.Commands.CommandConvertSlab;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;
import com.sk89q.worldedit.world.block.BlockState;
import fr.Marodeur.ExpertBuild.API.Exception.IncompleteSelectionException;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;
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
    private static final MessageBuilder message = Main.getInstance().getMessageConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, @NotNull String[] args) {

        if (!(s instanceof Player p)) {
            s.sendMessage(Main.prefix + message.getConsoleNotExecuteCmd());
            return false;
        }

        if (!p.isOp() || !p.hasPermission("expperlin.use")) {
            p.sendMessage(message.getDontPerm());
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("convertslab")) {

            try {
                if (!new UtilsFAWE(p).isValidSelection(RegionSelectorType.CUBOID)) throw
                        new IncompleteSelectionException(p, RegionSelectorType.CUBOID);
            } catch (IncompleteSelectionException e) {
                return false;
            }

            BukkitPlayer actor = BukkitAdapter.adapt(p);
            LocalSession session = actor.getSession();
            EditSession editSession = session.createEditSession(actor);
            BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p);
            Region r = actor.getSelection();

            r.spliterator().forEachRemaining(bv3 -> editSession.getFullBlock(bv3).getStates().values().forEach(o -> {

                if (o.equals("double")) {

                    Material mat = Material.matchMaterial(editSession.getBlock(bv3).getBlockType().toString().replace("slab", "planks"));
                    BlockState b = BukkitAdapter.adapt(mat.createBlockData());
                    bv4.add(new BlockVec4(bv3, b.toBaseBlock()));

                }
            }));

            if (bv4.size() != 0) {
                new UtilsFAWE(p).setBlockList(p, bv4, true);
            } else {
                bb.sendMessage(message.getBlockModified(String.valueOf(bv4.size())));
            }

            bv4.clear();
        }
        return false;
    }
}
