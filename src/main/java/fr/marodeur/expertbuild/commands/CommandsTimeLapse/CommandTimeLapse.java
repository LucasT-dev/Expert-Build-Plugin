package fr.marodeur.expertbuild.commands.CommandsTimeLapse;

import com.plotsquared.core.location.Location;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.fawe.BlockChanger;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.enums.ExecutorType;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.builderObjects.TimelapseBuilder;

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

    private static final Configuration conf = Main.configuration();

    @Override
    public String getCommand() {
        return "/timelapse";
    }

    @Override
    public String getSyntax() {
        return "/timelapse <start-stop>";
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
        TimelapseBuilder timelapseBuilder = BrushBuilder.getBrushBuilderPlayer(p, false).getTimeLapseProfile();

        if (args[0].equalsIgnoreCase("start")) {

            World world = p.getWorld();
            BukkitPlayer actor = BukkitAdapter.adapt(p);

            LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
            EditSession editsession = localSession.createEditSession(actor);

            int Ymin = actor.getSelection().getMinimumPoint().y();
            int Ymax = actor.getSelection().getMaximumPoint().y();

            long volumeBlock = 0;

            long startTime = System.currentTimeMillis();

            final int[] loopNumber = new int[1];
            if (args.length == 2 ) {

                if (this.getValidArgument().isInteger(args[1], 1, conf.getTimelapse_max_block_per_tick())) {
                    loopNumber[0] = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 1, conf.getTimelapse_max_block_per_tick());
                    return;
                }
            } else {
                loopNumber[0] = 1;
            }

            // Has already timelapse running
            if (timelapseBuilder.hasTimelapseRunning()) {
                timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_already_running", true);
                return;
            }

            // Max timelapse on server
            if (Main.getDataProfile().getTimelapseHashMap().stream().filter(TimelapseBuilder::hasTimelapseRunning).count() > conf.getTimelapse_max_block_per_tick()) {
                timelapseBuilder.sendMessage("expbuild.message.commands.too_many_timelapses", true);
                return;
            }

            Region r = actor.getSelection();
            HashMap<Integer, Deque<BlockVector3>> hashMap = new HashMap<>();

            // FAWE LIMIT
            if (actor.getLimit().MAX_CHANGES()) {
                if (r.getVolume() > actor.getLimit().MAX_CHANGES & actor.getLimit().MAX_CHANGES != -1) {
                    timelapseBuilder.sendMessage("expbuild.message.commands.too_many_timelapses", true,new String[]{Long.toString(actor.getLimit().MAX_CHANGES)});
                    return;
                }
            }


            new FaweAPI(p).copySelection(true, true, true, true);

            timelapseBuilder.setHasTimelapseRunning(true).setSelection(r.getMinimumPoint(), r.getMaximumPoint());

            //Loading block
            if (Ymax == Ymin) {

                int finalY = Ymax;
                Deque<BlockVector3> layer = new ArrayDeque<>();

                r.forEach(blockVector3 -> {

                    if (blockVector3.y() == finalY && !editsession.getFullBlock(blockVector3).getMaterial().isAir()) {
                        BlockVector3 b =  BlockVector3.at(blockVector3.x(), blockVector3.y(), blockVector3.z());

                        // PlotSquared condition
                        if (world.getGenerator() != null) {
                            if (world.getGenerator().toString().equals("PlotSquared")) {
                                Location l = Location.at(world.getName(), b);
                                if (l.isPlotArea()) {
                                    if (!l.isUnownedPlotArea()) {
                                        if (l.getOwnedPlot().getOwner().equals(p.getUniqueId())) {
                                            layer.add(b);
                                        }
                                    }
                                }
                            }
                            else {
                                layer.add(b);
                            }
                        } else {
                            layer.add(b);
                        }
                    }

                });

                hashMap.put(Ymax, layer);
                volumeBlock += layer.size();

            } else {

                for (int y = Ymax; y >= Ymin; y--) {

                    int finalY = y;
                    Deque<BlockVector3> layer = new ArrayDeque<>();

                    r.forEach(blockVector3 -> {

                        if (blockVector3.y() == finalY && !editsession.getFullBlock(blockVector3).getMaterial().isAir()) {
                            BlockVector3 b = BlockVector3.at(blockVector3.x(), blockVector3.y(), blockVector3.z());

                            // PlotSquared condition
                            if (world.getGenerator() != null) {
                                if (world.getGenerator().toString().equals("PlotSquared")) {
                                    Location l = Location.at(world.getName(), b);
                                    if (l.isPlotArea()) {
                                        if (!l.isUnownedPlotArea()) {
                                            if (l.getOwnedPlot().getOwner().equals(p.getUniqueId())) {
                                                layer.add(b);
                                            }
                                        }
                                    }
                                }
                                else {
                                    layer.add(b);
                                }
                            } else {
                                layer.add(b);
                            }
                        }
                    });

                    hashMap.put(y, layer);
                    volumeBlock += layer.size();
                }
            }

            // Loop execution

            long finalVolumeBlock = volumeBlock;
            new BukkitRunnable() {

                @Override
                public void run() {

                    // Stop timelapse
                    if (timelapseBuilder.stopTimelapse()) {
                        cancel();
                        timelapseBuilder.setStopTimelapse(false).setHasTimelapseRunning(false);
                        return;
                    }

                    int y = Collections.max(hashMap.keySet());

                    if (hashMap.get(y).isEmpty()) {
                        hashMap.remove(y);

                        if (hashMap.isEmpty()) {

                            float timeEnd = 0;

                            int sec = (int) ((System.currentTimeMillis() - startTime) / 1000);

                            if (sec < 60) {
                                timeEnd = sec;
                                timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_finish_recap", true, new String[]{String.valueOf(timeEnd), new Message.MessageSender("expbuild.message.main.second", false).getMessage(), String.valueOf(finalVolumeBlock)});
                            }

                            if (sec >= 60 && sec < 3600) {
                                timeEnd = sec / 60;
                                timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_finish_recap", true, new String[]{String.valueOf(timeEnd), new Message.MessageSender("expbuild.message.main.minute", false).getMessage(), String.valueOf(finalVolumeBlock)});
                            }

                            if (sec >= 3600) {
                                timeEnd = sec / 60 / 60;
                                timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_finish_recap", true, new String[]{String.valueOf(timeEnd), new Message.MessageSender("expbuild.message.main.hour", false).getMessage(), String.valueOf(finalVolumeBlock)});
                            }

                            p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 10, 10);
                            timelapseBuilder.setHasTimelapseRunning(false);

                            cancel();
                            return;
                        }
                        y = Collections.max(hashMap.keySet());
                    }

                    int forValue = loopNumber[0];

                    if (hashMap.get(y).size() < loopNumber[0]) {
                        forValue = hashMap.get(y).size();
                    }

                    for (int i = 0; i < forValue; i++) {

                        if (!hashMap.get(y).isEmpty()) {

                            BlockVector3 bv3 = hashMap.get(y).poll();
                            BlockChanger.setBlock(p.getWorld(), bv3.x(), bv3.y(), bv3.z(), Material.AIR, false);
                        }
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0, 0);

            // Estimate timing

            float time = 0;

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
                time = sec / 60;
                timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_estimate_time", true, new String[]{String.valueOf(volumeBlock), String.valueOf(time), new Message.MessageSender("expbuild.message.main.minute", false).getMessage()});
            }

            if (sec >= 3600) {
                time = sec / 60 / 60;
                timelapseBuilder.sendMessage("expbuild.message.commands.timelapse_estimate_time", true, new String[]{String.valueOf(volumeBlock), String.valueOf(time), new Message.MessageSender("expbuild.message.main.hour", false).getMessage()});
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
        return new OptionalConditionExecution(sender).AddConditionSelection();
    }

    @Override
    protected ArgumentLengthList getArgumentLengthList() {

        return new ArgumentLengthList(Arrays.asList(

                new ArgumentLength(1, "start", 0, "/timelapse start [integer, block remove per tick]", 2),
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

        return subCommandSender;
    }
}
