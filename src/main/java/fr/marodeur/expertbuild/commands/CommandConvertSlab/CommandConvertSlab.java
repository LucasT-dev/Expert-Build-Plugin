package fr.marodeur.expertbuild.commands.CommandConvertSlab;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.world.block.BlockState;

import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.enums.ExecutorType;
import fr.marodeur.expertbuild.object.AbstractCommand;
import fr.marodeur.expertbuild.object.BlockVectorMaterial;

import fr.marodeur.expertbuild.object.BlockVectorTool;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandConvertSlab extends AbstractCommand {

    private static final BlockVectorMaterial bvm = new BlockVectorMaterial();

    private static final List<String> WOODEN_MATERIAL = Arrays.asList(
            "OAK_SLAB", "DARK_OAK_SLAB", "SPRUCE_SLAB", "BIRCH_SLAB", "JUNGLE_SLAB", "ACACIA_SLAB", "MANGROVE_SLAB",
            "BAMBOO_SLAB", "CHERRY_SLAB", "WARPED_SLAB", "CRIMSON_SLAB", "PALE_OAK_SLAB");
    private static final List<String> SPECIAL_BLOCK = List.of("PURPUR_SLAB", "QUARTZ_SLAB");

    private static final List<String> SPECIAL_BLOCKS = List.of("STONE_BRICK_SLAB", "MOSSY_STONE_BRICK_SLAB",
            "BRICK_SLAB", "TUFF_BRICK_SLAB", "DEEPSLATE_BRICK_SLAB", "PRISMARINE_BRICK_SLAB", "MUD_BRICK_SLAB",
            "DEEPSLATE_TILE_SLAB", "NETHER_BRICK_SLAB", "RED_NETHER_BRICK_SLAB", "END_STONE_BRICK_SLAB",
            "POLISHED_BLACKSTONE_BRICK_SLAB", "RESIN_BRICK_SLAB");

    private static final List<String> EXCLUDE_BLOCK = List.of("PETRIFIED_OAK_SLAB");


    @Override
    public String getCommand() {
        return "/convertslab";
    }

    @Override
    public String getSyntax() {
        return "/convertslab";
    }

    @Override
    public Integer getMinimumArgsLength() {
        return 0;
    }

    @Override
    public String getPermission() {
        return "exp.command.convertslab";
    }

    @Override
    public List<ExecutorType> getExecutorType() {
        return Collections.singletonList(ExecutorType.PLAYER);
    }

    /**
     * Command execution writing method
     *
     * @param executor CommandSender
     * @param command  Command
     * @param label    String
     * @param args     String[]
     */
    @Override
    public void execute(CommandSender executor, Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) executor;

        BukkitPlayer actor = BukkitAdapter.adapt(p);
        LocalSession session = actor.getSession();
        EditSession editSession = session.createEditSession(actor);


        new FaweAPI(p).clipboardIterator().forEachRemaining(bv3 -> editSession.getFullBlock(bv3).getStates().values().forEach(o -> {

            if (o.equals("double")) {

                Material materialBase = Material.matchMaterial(editSession.getBlock(bv3).getBlockType().toString());
                Material material;


                assert materialBase != null;

                if (!EXCLUDE_BLOCK.contains(materialBase.toString())) {

                    if (WOODEN_MATERIAL.contains(materialBase.toString())) {

                        material = Material.matchMaterial(editSession.getBlock(bv3).getBlockType().toString().replace("slab", "planks"));

                    } else if (SPECIAL_BLOCKS.contains(materialBase.toString())) {

                        material = Material.matchMaterial(editSession.getBlock(bv3).getBlockType().toString().replace("_slab", "s"));

                    } else if (SPECIAL_BLOCK.contains(materialBase.toString())) {

                        material = Material.matchMaterial(editSession.getBlock(bv3).getBlockType().toString().replace("slab", "block"));

                    } else {

                        material = Material.matchMaterial(editSession.getBlock(bv3).getBlockType().toString().replace("_slab", ""));

                    }

                    assert material != null;

                    BlockState b = BukkitAdapter.adapt(material.createBlockData());
                    bvm.addPositionMaterial(new BlockVectorTool().toBlockVectorTool(bv3), b.toBaseBlock());
                }
            }
        }));

        new FaweAPI(p).setBlock(bvm, true);

        bvm.clear();
    }

    @Override
    protected OptionalConditionExecution optionalConditionExecution(CommandSender sender) {
        return new OptionalConditionExecution(sender).AddConditionSelection();
    }

    @Override
    protected ArgumentLengthList getArgumentLengthList() {
        return new ArgumentLengthList(true);
    }

    @Override
    protected SubCommandSender getSubCommand(CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        SubCommandSender subCommandSender = new SubCommandSender();

        return subCommandSender;
    }
}