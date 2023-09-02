package fr.Marodeur.ExpertBuild.Commands.CommandsAutoFlips;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandAutoFlip implements TabCompleter, CommandExecutor {


    private static final MessageBuilder message = Main.getInstance().getMessageConfig();
    private final List<String> args1 = Arrays.asList("disable", "enable");

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, @NotNull String[] args) {

        if (args.length <= 1) {
            List<String> l = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], this.args1, l);
            return l;
        }
        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, @NotNull String[] args) {

        if (! (s instanceof Player p)) {
            s.sendMessage(Main.prefix + message.getConsoleNotExecuteCmd());
            return false;
        }
        if (!p.isOp() | !p.hasPermission("expautoflip.use")) {
            p.sendMessage(Main.prefix + message.getDontPerm());
            return false;
        }

        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

        if (cmd.getName().equalsIgnoreCase("autoflip")) {

            if (args.length < 1) {
                brushBuilder.sendMessage(message.getUse("/autoflip <enable | disable>"));
                return false;
            }

            if (args[0].equalsIgnoreCase("disable")) {
                if (brushBuilder.getBrushType().equals(BrushEnum.AUTOFLIP)) {

                    brushBuilder.setBrushType(BrushEnum.NONE)
                            .setEnable(false)
                            .setRegion(null)
                            .setBlockFace(null)
                            .sendMessage(message.getBrushDisable());
                }
            }
            if (args[0].equalsIgnoreCase("enable")) {

                if (new UtilsFAWE(p).isValidSelection(RegionSelectorType.CUBOID)) {

                    BukkitPlayer bukkitPlayer = BukkitAdapter.adapt(p);

                    brushBuilder.setRegion(bukkitPlayer.getSelection());

                    if (brushBuilder.getRegion().getMaximumPoint().getY() == brushBuilder.getRegion().getMinimumPoint().getY()) {

                        brushBuilder.setBlockFace(BlockFace.UP)
                                .setBrushType(BrushEnum.AUTOFLIP)
                                .setEnable(true)
                                .setBlockVec4(Arrays.asList(
                                        new BlockVec4(brushBuilder.getRegion().getMaximumPoint()),
                                        new BlockVec4(brushBuilder.getRegion().getMinimumPoint())))
                                .sendMessage(message.getBrushEnable("autoflip"));
                        return false;
                    }
                    if (brushBuilder.getRegion().getMaximumPoint().getX() == brushBuilder.getRegion().getMinimumPoint().getX()) {

                        brushBuilder.setBlockFace(BlockFace.WEST)
                                .setBrushType(BrushEnum.AUTOFLIP)
                                .setEnable(true)
                                .setBlockVec4(Arrays.asList(
                                        new BlockVec4(brushBuilder.getRegion().getMaximumPoint()),
                                        new BlockVec4(brushBuilder.getRegion().getMinimumPoint())))
                                .sendMessage(message.getBrushEnable("autoflip"));
                        return false;
                    }
                    if (brushBuilder.getRegion().getMaximumPoint().getZ() == brushBuilder.getRegion().getMinimumPoint().getZ()) {

                        brushBuilder.setBlockFace(BlockFace.NORTH)
                                .setBrushType(BrushEnum.AUTOFLIP)
                                .setEnable(true)
                                .setBlockVec4(Arrays.asList(
                                        new BlockVec4(brushBuilder.getRegion().getMaximumPoint()),
                                        new BlockVec4(brushBuilder.getRegion().getMinimumPoint())))
                                .sendMessage(message.getBrushEnable("autoflip"));
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
