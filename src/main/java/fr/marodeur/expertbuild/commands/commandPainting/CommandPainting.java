package fr.marodeur.expertbuild.commands.commandPainting;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BlockState;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.enums.BlocksDataColor;
import fr.marodeur.expertbuild.enums.ExecutorType;
import fr.marodeur.expertbuild.object.AbstractCommand;
import fr.marodeur.expertbuild.object.BlockVec4;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandPainting extends AbstractCommand {

    @Override
    public String getCommand() {
        return "/painting";
    }

    @Override
    public String getSyntax() {
        return "/painting <block> <block> [length] [through block]";
    }

    @Override
    public Integer getMinimumArgsLength() {
        return 2;
    }

    @Override
    public String getPermission() {
        return "exp.command.painting";
    }

    @Override
    public List<ExecutorType> getExecutorType() {
        return List.of(ExecutorType.PLAYER);
    }

    @Override
    public void execute(CommandSender executor, Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        Player p = (Player) executor;

        BlocksDataColor block1;
        BlocksDataColor block2;
        BlocksDataColor block3 = null;

        if (this.getValidArgument().isBlockDataColor(args[0])) {
            block1 = this.getValidArgument().getBlockDataColor(args[0]);
        } else {
            this.getValidArgument().sendMessageInvalidBlockDataColor(executor, args[0]);
           return;
        }

        if (this.getValidArgument().isBlockDataColor(args[1])) {
            block2 = this.getValidArgument().getBlockDataColor(args[1]);
        } else {
            this.getValidArgument().sendMessageInvalidBlockDataColor(executor, args[1]);
            return;
        }

        if (args.length == 4) {

            if (this.getValidArgument().isBlockDataColor(args[3])) {
                block3 = this.getValidArgument().getBlockDataColor(args[3]);
            } else {
                this.getValidArgument().sendMessageInvalidBlockDataColor(executor, args[3]);
                return;
            }
        }

        List<BlocksDataColor> l = new ArrayList<>();
        l.add(block1);

        int x = 15;
        if (args.length > 2) {

            if (this.getValidArgument().isInteger(args[2], 0, 15)) {
                x = this.getValidArgument().getInteger(args[2]);
            } else {
                this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, 15);
                return;
            }
        }

        List<BlockVec4> bv4 = new ArrayList<>();

        if (args.length <= 3) {
            getBlockList(block1, block2, x, l);
        }

        if (args.length >= 4) {
            assert block3 != null;
            getBlockList(block1, block3, x/2, l);
            l.add(block3);
            getBlockList(block3, block2, x/2, l);
        }

        // Light system

        float LightStep = 15f / (x - 1);

        BukkitPlayer actor = BukkitAdapter.adapt(p);
        LocalSession session = actor.getSession();
        EditSession editSession = session.createEditSession(actor);
        Region r = actor.getSelection();


        r.spliterator().forEachRemaining(bv3 -> {

            if (!editSession.getFullBlock(bv3).getMaterial().isAir() && !Material.matchMaterial(editSession.getBlock(bv3).getBlockType().toString()).toString().equalsIgnoreCase("LIGHT")) {

                int level = 0;

                if (isFaceAdjacentToAir(bv3, BlockFace.UP, editSession)) {
                    level = p.getWorld().getBlockAt(bv3.getBlockX(), bv3.getBlockY(), bv3.getBlockZ()).getRelative(BlockFace.UP).getLightFromBlocks();

                } else if (isAirFace(bv3, editSession)) {
                    level = p.getWorld().getBlockAt(bv3.getBlockX(), bv3.getBlockY(), bv3.getBlockZ()).getRelative(getAirFace(bv3, editSession)).getLightFromBlocks();
                }

                int index = Math.round(level / LightStep);
                if (index == l.size()) index--;

                Material mat = Material.getMaterial(l.get(index).getName().toUpperCase());
                BlockState b = BukkitAdapter.adapt(mat.createBlockData());

                bv4.add(new BlockVec4(bv3, b.toBaseBlock()));
            }
        });

        new UtilsFAWE(p).setBlockList(p, bv4, true);

        bv4.clear();
    }

    @Override
    protected OptionalConditionExecution getArgumentLengthList(CommandSender sender) {
        return new OptionalConditionExecution(sender).AddConditionSelection();
    }

    @Override
    protected ArgumentLengthList getArgumentLengthList() {
        return new ArgumentLengthList(true);
    }

    @Override
    protected SubCommandSender getSubCommand(CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        SubCommandSender subCommandSender = new SubCommandSender();

        subCommandSender.addSubCommand(new SubCommandSelector().getList(0, BlocksDataColor.getStreamArray().map(BlocksDataColor::getName).toList()).toSubCommand("none"));
        subCommandSender.addSubCommand(new SubCommandSelector().getList(1, BlocksDataColor.getStreamArray().map(BlocksDataColor::getName).toList()).toSubCommand("none"));
        subCommandSender.addSubCommand(new SubCommandSelector().getList(3, BlocksDataColor.getStreamArray().map(BlocksDataColor::getName).toList()).toSubCommand("none"));

        subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("none"));

        return subCommandSender;
    }

    public static BlocksDataColor findClosestColor2(int r, int g, int b) {
        BlocksDataColor closestColor = null;
        double minDistance = Integer.MAX_VALUE;

        for (BlocksDataColor color : BlocksDataColor.values()) {
            double distance = Math.sqrt(
                    Math.pow(color.getR() - r, 2) +
                            Math.pow(color.getG() - g, 2) +
                            Math.pow(color.getB() - b, 2)
            );

            if (distance < minDistance) {
                minDistance = distance;
                closestColor = color;
            }
        }
        return closestColor;
    }


    // Méthode pour trouver la couleur la plus proche
    public static BlocksDataColor findClosestColor(int targetR, int targetG, int targetB) {
        BlocksDataColor closestColor = null;
        double minDistance = Double.MAX_VALUE;

        for (BlocksDataColor color : BlocksDataColor.getStreamArray().toList()) {
            double distance = weightedEuclideanDistance(color.getR(), color.getG(), color.getB(), targetR, targetG, targetB);

            if (distance < minDistance) {
                minDistance = distance;
                closestColor = color;
            }
        }

        return closestColor;
    }

    // Calcul de la distance euclidienne pondérée
    private static double weightedEuclideanDistance(int r1, int g1, int b1, int r2, int g2, int b2) {

        double redWeight = 2.0;
        double greenWeight = 4.0;
        double blueWeight = 3.0;

        double distance = Math.sqrt(
                Math.pow(redWeight * (r1 - r2), 2) +
                        Math.pow(greenWeight * (g1 - g2), 2) +
                        Math.pow(blueWeight * (b1 - b2), 2)
        );

        return distance;
    }
    private static void getBlockList(@NotNull BlocksDataColor block1, @NotNull BlocksDataColor block2, int x, List<BlocksDataColor> l) {

        int r1 = block1.getR();
        int g1 = block1.getG();
        int b1 = block1.getB();

        int r2 = block2.getR();
        int g2 = block2.getG();
        int b2 = block2.getB();

        //if (x-1 == 0) x++;

        int redStep = (r2 - r1) / (x - 1);
        int greenStep = (g2 - g1) / (x - 1);
        int blueStep = (b2 - b1) / (x - 1);

        for (int i = 1; i < x - 1; i++) {

            int r = r1 + (redStep * i);
            int g = g1 + (greenStep * i);
            int b = b1 + (blueStep * i);

            BlocksDataColor blocksDataColor = findClosestColor(r, g, b);
            l.add(blocksDataColor);
        }

        l.add(block2);
    }


    /*public static boolean isFaceAdjacentToAir(@NotNull BlockVector3 bv3, @NotNull BlockFace faceToCheck, @NotNull EditSession editSession) {
        BlockVector3 adjacentPosition = bv3.add(faceToCheck.getModX(), faceToCheck.getModY(), faceToCheck.getModZ());
        return editSession.getFullBlock(adjacentPosition).getMaterial().isAir();
    }*/

    public static boolean isFaceAdjacentToAir(@NotNull BlockVector3 bv3, @NotNull BlockFace faceToCheck, @NotNull EditSession editSession) {
        BlockVector3 adjacentPosition = bv3.add(faceToCheck.getModX(), faceToCheck.getModY(), faceToCheck.getModZ());
        //BlockState blockState = editSession.getFullBlock(adjacentPosition);

        Material material = Material.matchMaterial(editSession.getBlock(bv3).getBlockType().toString());

        //Material material = blockState.;
        // Vérifier si le bloc n'est pas de type "LIGHT"
        return editSession.getFullBlock(adjacentPosition).getMaterial().isAir() || material.toString().equalsIgnoreCase("LIGHT");
    }

    public static boolean isAirFace(BlockVector3 bv3, EditSession editSession) {
        for (BlockFace face : BlockFace.values()) {
            BlockVector3 adjacentPosition = bv3.add(face.getModX(), face.getModY(), face.getModZ());
            if (editSession.getFullBlock(adjacentPosition).getMaterial().isAir()) {
                return true;
            }
        }
        return false;
    }

    public static @Nullable BlockFace getAirFace(BlockVector3 bv3, EditSession editSession) {
        for (BlockFace face : BlockFace.values()) {
            BlockVector3 adjacentPosition = bv3.add(face.getModX(), face.getModY(), face.getModZ());
            if (editSession.getFullBlock(adjacentPosition).getMaterial().isAir()) {
                return face;
            }
        }
        return null;
    }
}
