package fr.marodeur.expertbuild.commands.CommandsTimeLapse;

import com.plotsquared.core.location.Location;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.regions.Region;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.enums.ExecutorType;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.builderObjects.TimelapseBuilderParameter;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CommandTimelapse extends AbstractCommand {

    private static final Configuration conf = Main.getConfiguration();

    @Override
    public String getCommand() {
        return "/timelapse";
    }

    @Override
    public String getSyntax() {
        return "/timelapse <start-stop> [block/tick = 1] [shape destroy block] [mask]";
    }

    @Override
    public Integer getMinimumArgsLength() {
        return 1;
    }

    @Override
    public String getPermission() {
        return "exp.command.timelapse";
    }

    @Override
    public List<ExecutorType> getExecutorType() {
        return Collections.singletonList(ExecutorType.PLAYER);
    }

    @Override
    public void execute(CommandSender executor, Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) executor;
        TimelapseBuilderParameter timelapseBuilder = BrushBuilder.getBrushBuilderPlayer(p, false).getTimeLapseProfile();

        if (args[0].equalsIgnoreCase("start")) {

            World world = p.getWorld();
            BukkitPlayer actor = BukkitAdapter.adapt(p);
            LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);

            try (EditSession editsession = localSession.createEditSession(actor)) {

                int Ymin = actor.getSelection().getMinimumPoint().y();
                int Ymax = actor.getSelection().getMaximumPoint().y();

                long volumeBlock;

                long startTime = System.currentTimeMillis();

                final int[] loopNumber = new int[1];
                Mask mask;

                if (args.length >= 2) {

                    if (this.getValidArgument().isInteger(args[1], 1, conf.getTimelapse_max_block_per_tick())) {
                        loopNumber[0] = this.getValidArgument().getInteger(args[1]);
                    } else {
                        this.getValidArgument().sendMessageInvalidInteger(p, args[1], 1, conf.getTimelapse_max_block_per_tick());
                        return;
                    }
                } else {
                    loopNumber[0] = 1;
                }

                if (args.length == 4) {

                    if (this.getValidArgument().isMask(p, args[3])) {
                        mask = this.getValidArgument().getMask(p, args[3]);
                    } else {
                        this.getValidArgument().sendMessageInvalidMask(p, args[3]);
                        return;
                    }
                } else {
                    mask = new FaweAPI(p).getMask("!air");
                }


                // Has already timelapse running
                if (timelapseBuilder.hasTimelapseRunning()) {
                    timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_already_running", true);
                    return;
                }

                // Max timelapse on server
                if (Main.getDataProfile().getTimelapseHashMap().stream().filter(TimelapseBuilderParameter::hasTimelapseRunning).count() > conf.getMaxTimelapseInSameTime()) {
                    timelapseBuilder.sendMessage("expbuild.message.commands.too_many_timelapses", true);
                    return;
                }

                Region r = actor.getSelection();
                HashMap<Integer, Deque<BlockVectorTool>> hashMap;

                // FAWE LIMIT
                if (actor.getLimit().MAX_CHANGES()) {
                    if (r.getVolume() > actor.getLimit().MAX_CHANGES & actor.getLimit().MAX_CHANGES != -1) {
                        timelapseBuilder.sendMessage("expbuild.message.commands.too_many_timelapses", true, new String[]{Long.toString(actor.getLimit().MAX_CHANGES)});
                        return;
                    }
                }

                if (Ymin == Ymax) {
                    timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_region_height_too_low", true);
                    return;
                }

                new FaweAPI(p).copySelection(false, true, true, true);

                timelapseBuilder
                        .setHasTimelapseRunning(true)
                        .setSelection(r.getMinimumPoint(), r.getMaximumPoint());

                Timelapse timelapse = new Timelapse();
                timelapse.registerBlock(editsession, world, r, p, mask, args);


                hashMap = timelapse.hashMap();
                volumeBlock = timelapse.volumeBlock();

                // Loop execution

                long finalVolumeBlock = volumeBlock;

                HashMap<Integer, Deque<BlockVectorTool>> finalHashMap = hashMap;

                new BukkitRunnable() {

                    @Override
                    public void run() {

                        // Stop timelapse
                        if (timelapseBuilder.stopTimelapse()) {
                            cancel();
                            timelapseBuilder.setStopTimelapse(false).setHasTimelapseRunning(false);
                            return;
                        }

                        int y = Collections.max(finalHashMap.keySet());

                        if (finalHashMap.get(y).isEmpty()) {
                            finalHashMap.remove(y);

                            if (finalHashMap.isEmpty()) {

                                float timeEnd;

                                int sec = (int) ((System.currentTimeMillis() - startTime) / 1000);

                                if (sec < 60) {
                                    timeEnd = sec;
                                    timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_finish_recap", true, new String[]{String.valueOf(timeEnd), new Message.MessageSender("expbuild.message.main.second", false).getMessage(), String.valueOf(finalVolumeBlock)});
                                }

                                if (sec >= 60 && sec < 3600) {
                                    timeEnd = (float) sec / 60;
                                    timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_finish_recap", true, new String[]{String.valueOf(timeEnd), new Message.MessageSender("expbuild.message.main.minute", false).getMessage(), String.valueOf(finalVolumeBlock)});
                                }

                                if (sec >= 3600) {
                                    timeEnd = (float) sec / 60 / 60;
                                    timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_finish_recap", true, new String[]{String.valueOf(timeEnd), new Message.MessageSender("expbuild.message.main.hour", false).getMessage(), String.valueOf(finalVolumeBlock)});
                                }

                                p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 10, 10);
                                timelapseBuilder.setHasTimelapseRunning(false);

                                cancel();
                                return;
                            }
                            y = Collections.max(finalHashMap.keySet());
                        }

                        int forValue = loopNumber[0];

                        if (finalHashMap.get(y).size() < loopNumber[0]) {
                            forValue = finalHashMap.get(y).size();
                        }

                        for (int i = 0; i < forValue; i++) {

                            if (!finalHashMap.get(y).isEmpty()) {

                                BlockVectorTool bv3 = finalHashMap.get(y).poll();

                                world.setType(bv3.getBlockX(), bv3.getBlockY(), bv3.getBlockZ(), Material.AIR);

                                // fake remove block
                                //p.sendBlockChange(bv3.toLocation(world), Material.AIR.createBlockData());

                            /*new AdvancedBlockOperation(p)
                                    .setBlock(world,
                                            new BlockVectorTool().toBlockVectorTool(bv3),
                                            Material.AIR);*/

                                //BlockChanger.setBlock(p.getWorld(), bv3.x(), bv3.y(), bv3.z(), Material.AIR, false);
                            }
                        }
                    }
                }.runTaskTimer(Main.getInstance(), 0, 0);

                // Estimate timing

                float time;

                // Limite a 5 heure a mettre en config
                if ((int) ((volumeBlock / loopNumber[0]) / 20) > 18000) {

                    for (int i = 0; i < 50; i++) {

                        if ((int) ((volumeBlock / loopNumber[0] + i) / 20) < 18000) {

                            loopNumber[0] = loopNumber[0] + i;

                        }
                    }
                }
                int sec = (int) ((volumeBlock / loopNumber[0]) / 20);


                if (sec < 60) {
                    time = sec;
                    timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_estimate_time", true, new String[]{String.valueOf(volumeBlock), String.valueOf(time), new Message.MessageSender("expbuild.message.main.second", false).getMessage()});
                }

                if (sec >= 60 && sec < 3600) {
                    time = (float) sec / 60;
                    timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_estimate_time", true, new String[]{String.valueOf(volumeBlock), String.valueOf(time), new Message.MessageSender("expbuild.message.main.minute", false).getMessage()});
                }

                if (sec >= 3600) {
                    time = (float) sec / 60 / 60;
                    timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_estimate_time", true, new String[]{String.valueOf(volumeBlock), String.valueOf(time), new Message.MessageSender("expbuild.message.main.hour", false).getMessage()});
                }
            }
            return;
        }


        if (args[0].equalsIgnoreCase("stop")) {

            if (timelapseBuilder.hasTimelapseRunning()) {

                timelapseBuilder
                        .setStopTimelapse(true)
                        .sendMessage("expbuild.message.commands.timelapse_stopped", true);

            } else {
                timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_no_in_progress", true);
            }
        } else {
            timelapseBuilder.sendMessage("expbuild.message.commands.use", true, new String[]{getSyntax()});
        }
    }


    @Override
    protected OptionalConditionExecution optionalConditionExecution(CommandSender sender) {
        return new OptionalConditionExecution(sender).AddConditionSelection().AddBrushBuilderProfile();
    }

    @Override
    protected ArgumentLengthList getArgumentLengthList() {

        return new ArgumentLengthList(Arrays.asList(

                new ArgumentLength(1, "start", 0, "/timelapse start [block remove per tick : 1] [shape : no] [mask]", 2),
                new ArgumentLength(1, "stop", 0, "/timelapse stop", 2)
        ));
    }

    @Override
    protected SubCommandSender getSubCommand(CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        SubCommandSender subCommandSender = new SubCommandSender();

        // START
        subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "start")
                .toSubCommand("None"));
        // STOP
        subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "stop")
                .toSubCommand("None"));

        // START int
        subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1)
                .toSubCommand("None", new ConditionArgumentBefore("start", 0)));

        // START int trier
        subCommandSender.addSubCommand(new SubCommandSelector().getList(2, Arrays.asList("creasing", "diagonal", "random_diagonal", "cylinder", "spiral", "square_sweep", "inverse_diagonal", "inverse_cylinder", "inverse_spiral", "random", "circle", "inverse_circle"))
                .toSubCommand("None", new ConditionArgumentBefore("start", 0)));

        // /timelapse start 1 shape mask
        subCommandSender.addSubCommand(new SubCommandSelector().getMaskFactoryList(args, 3).toSubCommand("None"));

        // START - flag
        //subCommandSender.addSubCommand(new SubCommandSelector().getFlag(args, 1, "m").toSubCommand("None", new ConditionArgumentBefore("start", 0)));
        //subCommandSender.addSubCommand(new SubCommandSelector().getFlag(args, 2, "m").toSubCommand("None", new ConditionArgumentBefore("start", 0)));
        //subCommandSender.addSubCommand(new SubCommandSelector().getFlag(args, 3, "m").toSubCommand("None", new ConditionArgumentBefore("start", 0)));

        return subCommandSender;
    }
}


class Timelapse {

    private HashMap<Integer, Deque<BlockVectorTool>> hashMap;
    private long volumeBlock = 0;

    public Timelapse() {
    }

    void registerBlock(EditSession editsession, World world, Region r, Player p, Mask mask, String[] args) {

        HashMap<Integer, Deque<BlockVectorTool>> hashMap = new HashMap<>();

        r.forEach(blockVector3 -> {

            if (!editsession.getFullBlock(blockVector3).getMaterial().isAir()) {

                BlockVectorTool b = new BlockVectorTool(blockVector3.x(), blockVector3.y(), blockVector3.z());

                // PlotSquared condition
                if (world.getGenerator() != null) {

                    // Map de plot !!
                    if (world.getGenerator().toString().equals("PlotSquared")) {

                        // Object location custom de plotsquared
                        Location l = Location.at(world.getName(), b.toBlockVector3());

                        if (l.isPlotArea()) { // N'est pas un chemin
                            if (!l.isUnownedPlotArea()) { // Est un plot atribué a un joueur
                                if (l.getOwnedPlot().getOwner().equals(p.getUniqueId())) { // Le owner du plot est celui qui execute la commande

                                    // Test le mask
                                    if (mask.test(blockVector3)) {
                                        hashMap.putIfAbsent(b.getBlockY(), new ArrayDeque<>());
                                        hashMap.get(b.getBlockY()).add(b);
                                        //finalLayer.add(b);
                                    }
                                }
                            }
                        }
                    }

                    // Map genrerer par une autre pl que plotsquared !
                    else {

                        // Test du mask
                        if (mask.test(blockVector3)) {

                            hashMap.putIfAbsent(b.getBlockY(), new ArrayDeque<>());
                            hashMap.get(b.getBlockY()).add(b);
                            //finalLayer.add(b);
                        }
                    }

                }

                // Map classique sans plot !
                else {

                    //System.out.println(blockVector3);

                    // Test du mask
                    if (mask.test(blockVector3)) {

                        hashMap.putIfAbsent(b.getBlockY(), new ArrayDeque<>());
                        hashMap.get(b.getBlockY()).add(b);
                        //finalLayer.add(b);
                    }
                }
            }
        });


        hashMap.forEach((integer, blockVectorTools) -> {

            // Trier la Queue de bloc a retirer
            if (args.length >= 3) {

                GlueList<BlockVectorTool> sortList = new GlueList<>();
                sortList.addAll(blockVectorTools.stream().toList());

                if (args[2].equalsIgnoreCase("creasing")) {

                    blockVectorTools = new BlockVectorTool().XZcreasing(sortList);
                }
                if (args[2].equalsIgnoreCase("diagonal")) {

                    blockVectorTools = new BlockVectorTool().XZDiagonal(sortList);
                }
                if (args[2].equalsIgnoreCase("random_diagonal")) {

                    blockVectorTools = new BlockVectorTool().XZRandomDiagonal(sortList);
                }
                if (args[2].equalsIgnoreCase("cylinder")) {

                    blockVectorTools = new BlockVectorTool().XZCylinder(sortList);
                }
                if (args[2].equalsIgnoreCase("spiral")) {

                    blockVectorTools = new BlockVectorTool().XZSpiral(sortList);
                }
                if (args[2].equalsIgnoreCase("circle")) {

                    blockVectorTools = new BlockVectorTool().XZCircleSweep(sortList);
                }
                if (args[2].equalsIgnoreCase("random")) {

                    blockVectorTools = new BlockVectorTool().XZRandom(sortList);
                }

                if (args[2].equalsIgnoreCase("square_sweep")) {

                    blockVectorTools = new BlockVectorTool().XZcreasing(sortList);

                    if (integer % 2 == 0) {
                        blockVectorTools = new BlockVectorTool().reverseDeque(blockVectorTools);
                    }
                }

                // Inverse la Queue de block a retirer
                if (args[2].startsWith("inverse_")) {
                    blockVectorTools = new BlockVectorTool().reverseDeque(blockVectorTools);

                }
            }

            hashMap.replace(integer, blockVectorTools);

        });

        this.hashMap = hashMap;
        this.volumeBlock = hashMap.values().stream()
                .mapToInt(Deque::size) // Obtenir la taille de chaque Deque
                .sum();;
    }

    public HashMap<Integer, Deque<BlockVectorTool>> hashMap() {
        return hashMap;
    }

    public long volumeBlock() {
        return volumeBlock;
    }
}
