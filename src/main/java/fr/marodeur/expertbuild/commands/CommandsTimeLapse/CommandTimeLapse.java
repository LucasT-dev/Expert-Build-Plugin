package fr.marodeur.expertbuild.commands.CommandsTimeLapse;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.fawe.BlockChanger;
import fr.marodeur.expertbuild.enums.ExecutorType;
import fr.marodeur.expertbuild.object.AbstractCommand;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.Configuration;
import fr.marodeur.expertbuild.object.MessageBuilder;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CommandTimelapse extends AbstractCommand {

    private static final MessageBuilder msg = Main.getInstance().getMessageConfig();
    private static final Configuration conf = Main.configuration();

    private final Vector[] CHECK_FACES = {
            new Vector(0, 0, 1),
            new Vector(0, 0, -1),
            new Vector(0, 1, 0),
            new Vector(0, -1, 0),
            new Vector(1, 0, 0),
            new Vector(-1, 0, 0)};

    private final Vector[] CHECK_HORIZONTAL_FACES = {
            new Vector(0, 0, 1),
            new Vector(0, 0, -1),
            new Vector(1, 0, 0),
            new Vector(-1, 0, 0)};


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
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);


        if (args[0].equalsIgnoreCase("start")) {

            BukkitPlayer actor = BukkitAdapter.adapt(p);

            LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
            EditSession editsession = localSession.createEditSession(actor);

            int Ymin = actor.getSelection().getMinimumPoint().getY();
            int Ymax = actor.getSelection().getMaximumPoint().getY();

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

            if (new BrushBuilder(p.getUniqueId()).getTimeLapseProfile().hasTimelapseRunning()) {
                brushBuilder.sendMessage(msg.getTimelapseAlreadyRunning());
                return;
            }

            Region r = actor.getSelection();
            HashMap<Integer, Deque<BlockVector3>> hashMap = new HashMap<>();

            new BrushBuilder(p.getUniqueId()).getTimeLapseProfile().setHasTimelapseRunning(true).setSelection(r.getMinimumPoint(), r.getMaximumPoint());

            //Loading block

            if (Ymax == Ymin) {

                int finalY = Ymax;
                Deque<BlockVector3> layer = new ArrayDeque<>();

                r.forEach(blockVector3 -> {

                    if (blockVector3.getBlockY() == finalY && !editsession.getFullBlock(blockVector3).getMaterial().isAir()) {
                        layer.add(BlockVector3.at(blockVector3.getX(), blockVector3.getY(), blockVector3.getZ()));
                    }
                });

                hashMap.put(Ymax, layer);
                volumeBlock += layer.size();

            } else {

                for (int y = Ymax; y >= Ymin; y--) {

                    int finalY = y;
                    Deque<BlockVector3> layer = new ArrayDeque<>();

                    r.forEach(blockVector3 -> {

                        if (blockVector3.getBlockY() == finalY && !editsession.getFullBlock(blockVector3).getMaterial().isAir()) {
                            BlockVector3 b = BlockVector3.at(blockVector3.getX(), blockVector3.getY(), blockVector3.getZ());
                            layer.add(b);
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
                    if (new BrushBuilder(p.getUniqueId()).getTimeLapseProfile().stopTimelapse()) {
                        cancel();
                        new BrushBuilder(p.getUniqueId()).getTimeLapseProfile().setStopTimelapse(false).setHasTimelapseRunning(false);
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
                                brushBuilder.sendMessage(msg.getTimelapseFinishRecap( String.valueOf(finalVolumeBlock), String.valueOf(timeEnd), msg.second().toString() ));
                            }

                            if (sec >= 60 && sec < 3600) {
                                timeEnd = sec / 60;
                                brushBuilder.sendMessage(msg.getTimelapseFinishRecap( String.valueOf(finalVolumeBlock), String.valueOf(timeEnd), msg.minute().toString() ));
                            }

                            if (sec >= 3600) {
                                timeEnd = sec / 60 / 60;
                                brushBuilder.sendMessage(msg.getTimelapseFinishRecap( String.valueOf(finalVolumeBlock), String.valueOf(timeEnd), msg.hour().toString() ));
                            }

                            p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 10, 10);
                            new BrushBuilder(p.getUniqueId()).getTimeLapseProfile().setHasTimelapseRunning(false);

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
                            BlockChanger.setBlock(p.getWorld(), bv3.getX(), bv3.getY(), bv3.getZ(), Material.AIR, false);
                        }
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0, 0);


            // Estimate timing

            float time = 0;
            String unit = "s";

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
                brushBuilder.sendMessage(msg.getTimelapseEstimateTime(String.valueOf(volumeBlock), String.valueOf(time), msg.second().toString()));

            }

            if (sec >= 60 && sec < 3600) {
                time = sec / 60;
                brushBuilder.sendMessage(msg.getTimelapseEstimateTime(String.valueOf(volumeBlock), String.valueOf(time), msg.minute().toString()));

            }

            if (sec >= 3600) {
                time = sec / 60 / 60;
                brushBuilder.sendMessage(msg.getTimelapseEstimateTime(String.valueOf(volumeBlock), String.valueOf(time), msg.hour().toString()));
            }
            return;
        }

        if (args[0].equalsIgnoreCase("stop")) {

            if (new BrushBuilder(p.getUniqueId()).getTimeLapseProfile().hasTimelapseRunning()) {

                new BrushBuilder(p.getUniqueId()).getTimeLapseProfile().setStopTimelapse(true);

                brushBuilder.sendMessage(msg.getTimelapseStopped());

            } else {
                brushBuilder.sendMessage(msg.getTimelapseNoInProgress());
            }
        } else {

            brushBuilder.sendMessage(msg.getUse(getSyntax()));

        }
    }

    @Override
    protected OptionalConditionExecution getArgumentLengthList(CommandSender sender) {
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
