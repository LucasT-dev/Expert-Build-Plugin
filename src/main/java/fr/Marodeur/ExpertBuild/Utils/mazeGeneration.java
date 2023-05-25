package fr.Marodeur.ExpertBuild.Utils;

import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.math.BlockVector3;
import fr.Marodeur.ExpertBuild.API.FAWE.BlockChanger;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.MazeBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class mazeGeneration {

    private static double Xmin;
    private static double Xmax;

    private static double Ymin;

    private static double Zmin;
    private static double Zmax;

    private static int mMax = 0;
    private static int nMax = 0;

    public static int pointValue;

    private static final int correcteur = 0;

    private static ArrayList<Location> loc = new ArrayList<>();

    @Deprecated
    public static void GenerateMaze(@NotNull Actor actor, Player p, int length, double coef) throws IOException, ExecutionException, InterruptedException {

        Xmin = actor.getSession().getSelection().getMinimumPoint().getX();
        Xmax = actor.getSession().getSelection().getMaximumPoint().getX();

        Ymin = actor.getSession().getSelection().getMinimumPoint().getY();

        Zmin = actor.getSession().getSelection().getMinimumPoint().getZ();
        Zmax = actor.getSession().getSelection().getMaximumPoint().getZ();

        List<Integer> list = new ArrayList<>();
        // add 4 element in ArrayList
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(8);

        mMax = (int) ((Xmax - Xmin) / length);

        nMax = (int) ((Zmax - Zmin) / length);

        if ((Xmax - Xmin) / (mMax * length) != 1) {

            Xmax = Xmax - ((Xmax - Xmin) - (length * mMax));
        }

        if ((Zmax - Zmin) / (nMax * length) != 1) {

            Zmax = Zmax - ((Zmax - Zmin) - length * nMax);
        }

        p.sendMessage(Main.prefix + "Running...");

        for (int i = (int) Xmin; i < Xmax + 1; i = i + length) {

            p.sendMessage(Main.prefix + (100 * i) / (Xmax-Xmin)+" %");
            /*
             *
             *   ( Ecart )         ( max )
             * xmax - xmin == 10 >> 100 %
             * i =  x %
             *  (i = state boucle)   ( x = result)
             *
             */

            for (int j = (int) Zmin; j < Zmax + 1; j = j + length) {

                Location location = new Location(p.getWorld(), i, Ymin, j);

                //BlockVector3 bv3 = new UtilsFAWE(p).toBlockVector3(i, Ymin + 1, j);
                BlockVector3 bv3 = BlockVector3.at(i, Ymin + 1, j);

                BlockChanger.setBlock(location, Material.DIAMOND_BLOCK, false);

                loc.add(location);

                Random random = new Random();
                pointValue = random.nextInt(15);

                /*
                 *
                 * N = 1
                 * S = 4
                 * E = 2
                 * W = 8
                 *
                 *
                 * Pour augmenter les probabilitées d'avoir des chemins dans un labyyrinthe,
                 * générer un chiffres peu utlisé (1,2,4,8)
                 *
                 */

                if (pointValue == 0) pointValue++;

                if (pointValue > 8 || pointValue == 3 || pointValue == 5 || pointValue == 7) {
                    Random rn = new Random();

                    double d = rn.nextDouble();     // random value in range 0.0 - 1.0

                    if (d <= coef) {
                        //pointValue = random.nextInt(8)+1;

                        Random rand = new Random();
                        pointValue = list.get(rand.nextInt(list.size()));
                    }
                }

                /*if (pointValue > 10) {
                    pointValue = random.nextInt(8);
                }*/


                if (i == Xmin) {
                    pointValue = 1;
                }

                if (j == Zmin) {
                    pointValue = 2;
                }


                if (i == Xmax) {
                    pointValue = 1;
                }

                if (j == Zmax) {
                    pointValue = 2;
                }


                if (i == Xmin && j == Zmin) {
                    pointValue = 5;
                }

                if (i == Xmax && j == Zmax) {
                    pointValue = 5;
                }

                /*
                 *
                 * Paste schem
                 * Condition : pointValue
                 *
                 */

                //North schem
                if (pointValue == 1 || pointValue == 3 || pointValue == 5 || pointValue == 7 || pointValue == 9 ||
                        pointValue == 11 || pointValue == 13 || pointValue == 15) {
                    new UtilsFAWE(p).playerPasteSchem22(p, getRandomFile(p, BlockFace.NORTH), bv3);

                }

                //East schem
                if (pointValue == 2 || pointValue == 3 || pointValue == 6 || pointValue == 7 || pointValue == 10 ||
                        pointValue == 11 || pointValue == 14 || pointValue == 15) {
                    new UtilsFAWE(p).playerPasteSchem22(p, getRandomFile(p, BlockFace.EAST), bv3);
                }

                //South schem
                if (pointValue == 4 || pointValue == 5 || pointValue == 6 || pointValue == 7 || pointValue == 12 ||
                        pointValue == 13 || pointValue == 14 || pointValue == 15) {
                    new UtilsFAWE(p).playerPasteSchem22(p, getRandomFile(p, BlockFace.SOUTH), bv3);
                }

                //West schem
                if (pointValue == 8 || pointValue == 9 || pointValue == 10 || pointValue == 11 || pointValue == 12 ||
                        pointValue == 13 || pointValue == 14 || pointValue == 15) {
                    new UtilsFAWE(p).playerPasteSchem22(p, getRandomFile(p, BlockFace.WEST), bv3);
                }
            }
        }

        mMax = (int) ((Xmax - Xmin) / length);
        nMax = (int) ((Zmax - Zmin) / length);

        p.sendMessage(Main.prefix + "Finish, deco reco");

    }
    public static @Nullable File getRandomFile(Player p, BlockFace blockFace) {

        List<String> str = new ArrayList<>();

        for (Map.Entry<String, MazeBuilder> mapentry : Main.Maze.entrySet()) {
            if (mapentry.getValue().getBlockFace().equals(blockFace)) {
                str.add(mapentry.getKey());

            }
        }

        int strSize = str.size();

        if (strSize == 0 ) strSize++;

        Random r = new Random();

            //System.out.println("str.get int = " + str.get(r.nextInt(str.size())));

        int index = r.nextInt(strSize);

        String nameFile = str.get(index);

        File communFile = new File("plugins/FastAsyncWorldEdit/schematics/" + nameFile);
        File personnelFile = new File("plugins/FastAsyncWorldEdit/schematics/" + p.getUniqueId() + "/" + nameFile);

        if (communFile.exists()) {
               return communFile;
        }

        if (personnelFile.exists()) {
            return personnelFile;

        } else {
            p.sendMessage(Main.prefix + "Can't find the schematic, Operation stopped, see console");

            return null;
        }
    }
}