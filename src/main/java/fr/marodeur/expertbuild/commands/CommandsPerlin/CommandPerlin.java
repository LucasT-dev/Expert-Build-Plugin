package fr.marodeur.expertbuild.commands.CommandsPerlin;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.api.exception.IncompleteSelectionException;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;

import fr.marodeur.expertbuild.object.MessageBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.bukkit.util.Vector;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class CommandPerlin implements CommandExecutor, TabCompleter {

    private static final MessageBuilder message = Main.getInstance().getMessageConfig();
    private static List<BlockVec4> bv4 = new ArrayList<>();

    private final List<String> list = Arrays
            .asList("<length> <height> <seed> <rayon>",
                    "<min-length> <max-length> <min-height> <max-height> <start-rayon> <end-rayon> <[o-1]",
                    "exemple : /perlin 20 80 50 70 1 1 0.9");


    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param s  Source of the command.  For players tab-completing a
     *                command inside of a command block, this will be the player, not
     *                the command block.
     * @param cmd Command which was executed
     * @param label   Alias of the command which was used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (args.length <= 1) {
            List<String> l = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], this.list, l);
            return l;
        }
        return null;
    }

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

        if (!(s instanceof Player p)) {
            s.sendMessage(Main.prefix + message.getConsoleNotExecuteCmd());
            return false;
        }

        if (!p.hasPermission("exp.register")) {
            p.sendMessage(Main.prefix + message.getNoPermissionNode("exp.register"));
            return false;
        }

        if (!p.hasPermission("exp.command.perlin")) {
            p.sendMessage(Main.prefix + message.getNoPermissionNode("exp.command.perlin"));
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("perlin")) {

            //perlin length height
            //perlin min_length max_length min_height max_height probabilitéeStartPoint

            if (args.length == 4) {

                try {

                    bv4.clear();

                    genVeine(p.getWorld(),
                            (int) p.getLocation().getX(),
                            (int) p.getLocation().getY(),
                            (int) p.getLocation().getZ(),
                            Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                            Integer.parseInt(args[3]), p);

                    new UtilsFAWE(p).setBlockListSimple(p, bv4, true);

                } catch (NumberFormatException e) {
                    p.sendMessage(message.getInvalidNumberIntegerUpper0().toString());
                }

                p.sendMessage(message.getBlockModified(String.valueOf(bv4.size())));
                return false;
            }

            else if (args.length == 7) {

                BukkitPlayer actor = BukkitAdapter.adapt(p);
                bv4.clear();

                try {
                    if (!new UtilsFAWE(p).isValidSelection(RegionSelectorType.CUBOID)) throw
                            new IncompleteSelectionException(p, RegionSelectorType.CUBOID);
                } catch (IncompleteSelectionException e) {
                    return false;
                }

                int Xmin = actor.getSession().getSelection().getMinimumPoint().getX();
                int Xmax = actor.getSession().getSelection().getMaximumPoint().getX();

                int Ymin = actor.getSession().getSelection().getMinimumPoint().getY();
                int Ymax = actor.getSession().getSelection().getMaximumPoint().getY();

                int Zmin = actor.getSession().getSelection().getMinimumPoint().getZ();
                int Zmax = actor.getSession().getSelection().getMaximumPoint().getZ();

                ArrayList<BlockVector3> arrays = new ArrayList<>();

                for (int x = Xmin; x <= Xmax; x++) {
                    for (int y = Ymin; y <= Ymax; y++) {
                        for (int z = Zmin; z <= Zmax; z++) {
                            arrays.add(BlockVector3.at(x, y, z));
                        }
                    }
                }

                arrays.forEach(array1 -> {

                    int minLength = Integer.parseInt(args[0]);
                    int maxLength = Integer.parseInt(args[1]);

                    int minHeight = Integer.parseInt(args[2]);
                    int maxHeight = Integer.parseInt(args[3]);

                    int startRayon = Integer.parseInt(args[4]);
                    //int endRayon   =  Integer.parseInt(args[5]);

                    float proba = Float.parseFloat(args[6]);

                    int r1 = (int) (Math.random() * (maxLength - minLength) + minLength);

                    int r2 = (int) (Math.random() * (maxHeight - minHeight) + minHeight);

                    //proba to generate perlin

                    float t = (float) new Random().nextInt(10) / 10;

                    if (t >= proba) {

                        genVeine(p.getWorld(),
                                array1.getX(), array1.getY(), array1.getZ(),
                                r1, //length
                                r2, //heigth
                                p.getWorld().getSeed(), //seed
                                startRayon, p); //rayon
                    }

                });
                new UtilsFAWE(p).setBlockAnyPattern(p, bv4, true);

                return false;
            } else {
                p.sendMessage(message.getUse("/perlin <length> <height> <rayon> or /perlin <min-length> <max-length> <min-height> <max-height> <start-rayon> <end-rayon> <[o-1], proba to start point>"));
            }
        }
        return false;
    }
    //  return false;
    //}

    public static void genVeine(World world, int x0, int y0, int z0, float length, float height, long seed, int rayon, Player p){

        Random rand = new Random();
        rand.setSeed(seed);

        int xx = rand.nextInt(1000);
        int zz = rand.nextInt(1000);

        float x = x0;
        float y = y0;
        float z = z0;
        for(int i = 0; i < length/5; i++){
            float degrees = getNoise(xx, zz, 10, 360f, seed);
            xx += 5;

            float addX = (float) getDirection(degrees).getX();
            float addZ = (float) getDirection(degrees).getZ();

            for(int h = 0; h < 3; h++){
                x += addX;
                z += addZ;
                y -= height/length;

                dig((int) x, (int) y, (int) z, world, rayon, p);
            }
        }
    }

    private static void dig(int x0, int y0, int z0, World world, int rayon, Player p){

        Location middlePoint = new Location(world, x0, y0, z0);

        Location loc1 = middlePoint.clone()
                .add(-rayon, -rayon, -rayon)
                .getBlock().getLocation();
        Location loc2 = middlePoint.clone()
                .add(+rayon, +rayon, +rayon)
                .getBlock().getLocation();

        for (double x = loc1.getX(); x <= loc2.getX(); x++) {
            for (double y = loc1.getY(); y <= loc2.getY(); y++) {
                for (double z = loc1.getZ(); z <= loc2.getZ(); z++) {
                    Location loc = new Location(loc1.getWorld(), x, y, z);

                    if (loc.distance(middlePoint) < (rayon)) {

                        bv4.add(new BlockVec4(loc, Material.DIAMOND_BLOCK, p));

                    }
                }
            }
        }


        //sphere

        /*for(int ix = 0; ix < 5; ix++){
            for(int iy = 0; iy < 5; iy++){
                for(int iz = 0; iz < 5; iz++){

                    float x = ix-2.5f;
                    float y = iy-2.5f;
                    float z = iz-2.5f;

                    float x1 = x0+ix;
                    float y1 = y0+iy;
                    float z1 = z0+iz;

                    float length = (float) Math.sqrt(x*x + y*y + z*z);

                    if(length <= 2.5f ) {//&& !(world.getBlock((int) x1, (int) y1, (int) z1) instanceof Block)) {

                        new Location(p.getWorld(), x1, y1, z1).getBlock().setType(Material.DIAMOND_BLOCK);
                        //world.removeBlock((int) x1, (int) y1, (int) z1);
                    }
                }
            }
        }*/
    }

    @Contract("_ -> new")
    private static @NotNull Vector getDirection(float degrees){

        float cosY = (float) Math.cos(Math.toRadians(degrees-90));
        float sinY = (float) Math.sin(Math.toRadians(degrees-90));

        return new Vector(cosY, 0, sinY);

        //return new Vec2f(cosY, sinY);

    }

    private static float getNoise(float x, float z, int octave, float amplitude, long seed){
        int xmin = (int) x / octave;
        int xmax = (int) xmin + 1;
        int zmin = (int) z / octave;
        int zmax = (int) zmin + 1;

        Vector a = new Vector(xmin, 0, zmin);
        Vector b = new Vector(xmax, 0, zmin);
        Vector c = new Vector(xmax, 0, zmax);
        Vector d = new Vector(xmin, 0, zmax);

        float ra = (float) noise(a, seed);
        float rb = (float) noise(b, seed);
        float rc = (float) noise(c, seed);
        float rd = (float) noise(d, seed);

        float t = x/octave - (int)x/octave;
        float t2 = z/octave - (int)z/octave;

        float i1 = interpolate(ra, rb, t);
        float i2 = interpolate(rd, rc, t);

        float h = interpolate(i1, i2, t2);

        return h * amplitude;
    }

    private static float interpolate(float a, float b, float t){
        float ft = (float) (t * Math.PI);
        float f = (float) ((1f - Math.cos(ft)) * 0.5f);
        float ret = a * (1f - f) + b * f;

        return ret;

    }

    private static double noise(@NotNull Vector coord, long seed){

        double var = 10000.0 * (Math.sin(coord.getX() + Math.cos(coord.getZ())) + Math.tan(seed));
        Random random = new Random();
        random.setSeed((long) var);

        return random.nextDouble();
    }
}

