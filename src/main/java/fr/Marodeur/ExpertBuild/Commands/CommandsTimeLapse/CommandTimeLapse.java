package fr.Marodeur.ExpertBuild.Commands.CommandsTimeLapse;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;
import fr.Marodeur.ExpertBuild.API.Exception.IncompleteSelectionException;
import fr.Marodeur.ExpertBuild.API.FAWE.BlockChanger;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.API.GlueList;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandTimeLapse extends Thread implements CommandExecutor {

    private static final MessageBuilder message = Main.getInstance().getMessageConfig();
    public static List<String> BrokenBlock = new ArrayList<>();

    private static int Xmin;
    private static int Xmax;

    private static int Ymin;

    private static int Zmin;
    private static int Zmax;

    private static int x;
    private static int y;
    private static int z;

    boolean stopGen = false;

    private static Location l;

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, @NotNull String[] args) {

        if (!(s instanceof Player p)) {
            s.sendMessage(Main.prefix + message.getConsoleNotExecuteCmd());
            return false;
        }

        if (!p.isOp() || !p.hasPermission("exptimelapse.use")) {
            p.sendMessage(Main.prefix + message.getDontPerm());
            return false;
        }

        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

        if (cmd.getName().equalsIgnoreCase("timelapse")) {

            try {
                if (!new UtilsFAWE(p).isValidSelection(RegionSelectorType.CUBOID)) throw
                        new IncompleteSelectionException(p, RegionSelectorType.CUBOID);
            } catch (IncompleteSelectionException e) {
                return false;
            }

            if (args.length < 1)
            {
                brushBuilder.sendMessage(message.getUse("/timelapse <delay> <iteration>"));
            }

            if (args[0].equalsIgnoreCase("stop")) {
                stopGen = true;
            }

            if (args.length >= 2)
            {
                try {
                    ExecuteTimeLapse(p, Long.parseLong(args[0]), Integer.parseInt(args[1]));
                } catch (NumberFormatException e) {
                    brushBuilder.sendMessage(message.getUse("/timelapse <delay> <iteration>"));
                }
            }
        }
        return false;
    }

    private static void ExecuteTimeLapse(final Player p, long delay, int iter) {

        BukkitPlayer actor = BukkitAdapter.adapt(p);

        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        EditSession editsession = localSession.createEditSession(actor);


        p.sendMessage(Main.prefix + "Timelapse started...");

        Xmin = actor.getSelection().getMinimumPoint().getX();
        Xmax = actor.getSelection().getMaximumPoint().getX();

        Ymin = actor.getSelection().getMinimumPoint().getY();
        int ymax = actor.getSelection().getMaximumPoint().getY();

        Zmin = actor.getSelection().getMinimumPoint().getZ();
        Zmax = actor.getSelection().getMaximumPoint().getZ();

        long volume = actor.getSelection().getVolume();

        long startTime = System.currentTimeMillis();

        x = Xmax;
        y = ymax;
        z = Zmax;

        /*
         *
         * replace :
         *  for (int i = 0; i < s.length; i++) {
         *             BrokenBlock.add(s[i]);
         *         }
         *
         * Par :
         * BrokenBlock.addAll(Arrays.asList(s));
         */

        l = new Location(p.getWorld(), Xmax, ymax, Zmax);

        new BukkitRunnable() {

            @Override
            public void run() {

                Location loc = new Location(p.getWorld(), x, y, z);

                //layer contains block
                if (x>= Xmax && z >= Zmax) {

                    while (!containsBlock(y, editsession) && y >= Ymin) {
                        y--;
                    }
                }

                //layer contains get proportion block / air
                if (x >= Xmax && z >= Zmax) {

                    int blockProportion = containsBlockQ(y, editsession);

                    if (blockProportion <= 10) {

                        CuboidRegion cuboidRegion = new CuboidRegion(BlockVector3.at(Xmax, y, Zmax), BlockVector3.at(Xmin, y, Zmin));

                        cuboidRegion.stream()
                                .filter(bv4 -> !editsession.getFullBlock(BlockVector3.at(bv4.getX(), bv4.getY(), bv4.getZ())).getBlockType().getMaterial().isAir())
                                .forEach(bv4-> BlockChanger.setBlock(l.getWorld(), bv4.getX(), y, bv4.getZ(), Material.AIR, false));

                        x = Xmin;
                        z = Zmin;

                    }

                    if (blockProportion <= 80 ) {

                        GlueList<BlockVec4> bv4 = new GlueList<>();

                        CuboidRegion cuboidRegion = new CuboidRegion(BlockVector3.at(Xmax, y, Zmax), BlockVector3.at(Xmin, y, Zmin));

                        cuboidRegion.stream()
                                .filter(blockVector3 -> !editsession.getFullBlock(BlockVector3.at(blockVector3.getX(), blockVector3.getY(), blockVector3.getZ())).getBlockType().getMaterial().isAir())
                                .forEach(blockVector3-> {
                                    bv4.add(new BlockVec4(blockVector3.getX(), y, blockVector3.getZ()));
                                    //BlockChanger.setBlock(l.getWorld(), blockVector3.getX(), y, blockVector3.getZ(), Material.AIR, false);
                                });

                        final int[] ite = {0};

                        new BukkitRunnable() {

                            @Override
                            public void run() {

                                BlockVec4 blv3 = bv4.get(ite[0]);
                                BlockChanger.setBlock(l.getWorld(), blv3.getX(), blv3.getY(), blv3.getZ(), Material.AIR, false);

                                //bv4.remove(ite[0]);

                                ite[0]++;

                                if (ite[0] >= bv4.size() ) cancel();

                                if (bv4.size() == 0) cancel();

                            }

                        }.runTaskTimer(Main.getInstance(), 20, 0);


                        x = Xmin;
                        z = Zmin;
                    }
                }

                if (loc.getBlock().getType().equals(Material.AIR)) {

                    while ((x > Xmin) && loc.getBlock().getType().equals(Material.AIR)) {
                        x--;
                        loc.setX(x);
                    }


                    l = new Location(p.getWorld(), x-1, y, z);
                    BlockChanger.setBlock(l, Material.AIR, false);

                    l.setX(x);
                }

                    //int zs = l.getBlockZ();
                if (x != Xmin) {
                    for (int i = 0; i < iter; i++) {

                        if (loc.getZ() -i > Zmin) {

                            Location l2 = loc.clone().add(0, 0, -i);

                            BlockChanger.setBlock(l2, Material.AIR, false);
                            //BlockChanger.setBlock(l3, Material.AIR, false);
                        }
                    }
                }

                if (!l.getBlock().getType().equals(Material.AIR)) {

                    BlockChanger.setBlock(l, Material.AIR, false);

                }

                if (x <= Xmin && y <= Ymin && z <= Zmin) {

                    long endTime = System.currentTimeMillis();
                    p.sendMessage(Main.prefix + "Timelapse finish in " + (endTime-startTime) + "ms, " + volume + " block distroyed");
                    p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 10, 10);

                    BrokenBlock.clear();

                    cancel();
                    return;
                }

                if (x == Xmin && z == Zmin) {
                    y--;
                    x = Xmax + 1;
                    z = Zmax;
                }

                if (x == Xmin) {
                    z--;
                    x = Xmax + 1;
                }

                x--;

                l = new Location(p.getWorld(), x, y, z);

            }

        }.runTaskTimer(Main.getInstance(), 0, delay);
    }

    public static boolean containsBlock(int y, EditSession editsession) {

        CuboidRegion cuboidRegion = new CuboidRegion(BlockVector3.at(Xmax, y, Zmax), BlockVector3.at(Xmin, y, Zmin));

        final boolean[] b = {false};
        final int[] n = {0};

        cuboidRegion.stream()
                .filter(bv3 -> !editsession.getFullBlock(BlockVector3.at(bv3.getX(), bv3.getY(), bv3.getZ())).getBlockType().getMaterial().isAir())
                .forEach(blockVector3 -> {
                    b[0] = true;
                    n[0]++;
                });


        //System.out.println("n = " + n[0]);

        return b[0];
    }

    public static int containsBlockQ(int y, EditSession editsession) {

        CuboidRegion cuboidRegion = new CuboidRegion(BlockVector3.at(Xmax, y, Zmax), BlockVector3.at(Xmin, y, Zmin));

        int size = cuboidRegion.getArea();

        GlueList<BlockVector3> bv = new GlueList<>(cuboidRegion.getArea()+1);

        cuboidRegion.stream()
                .filter(bv3 -> !editsession.getFullBlock(BlockVector3.at(bv3.getX(), bv3.getY(), bv3.getZ())).getBlockType().getMaterial().isAir())
                .forEach(bv::add);

        //n[0] = block
        //size = numbre de block total

        //total
        //100% = size
        //x = n[0]

        //n[0]*100/size

        //System.out.println("(n[0]*100)/size = " + (bv.size()*100)/size + "% de block");

        return (bv.size()*100)/size;

    }
}