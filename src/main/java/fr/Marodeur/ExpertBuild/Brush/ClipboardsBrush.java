package fr.Marodeur.ExpertBuild.Brush;

import com.sk89q.worldedit.world.block.BaseBlock;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.API.GlueList;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ClipboardsBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission("exp.brush.clipboard");
    }

    @Override
    public BrushEnum getTypeOfBrush() { return BrushEnum.CLIPBOARD; }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return BrushOperation.super.hasEnabelingBrush(brushBuilder);
    }

    @Override
    public void ExecuteBrushOnHoney(Player p, Object obj1) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p, true)) ||
                !BrushBuilder.getBrushBuilderPlayer(p, true).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location l = (Location) obj1;
        Random r = new Random();
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);

        if (brushBuilder.getClipboards().size() == 0) {
            p.sendMessage(Main.prefix + "Any selection register");
            return;
        }

        int index = r.nextInt(brushBuilder.getClipboards().size());

        GlueList<BlockVec4> tempbv4 = new GlueList<>();
        GlueList<BlockVec4> bv4 = new GlueList<>();

        brushBuilder.getClipboards().get(index)
                .iterator()
                .forEachRemaining(bv3 -> {

                    tempbv4.add(new BlockVec4(l.clone().add(bv3.getX(), bv3.getY(), bv3.getZ()), bv3.getBaseblock()));

                });

        tempbv4.iterator().forEachRemaining(blockVec4 -> {

            bv4.add(transformPoints(l.getBlockX(), l.getBlockY(), l.getBlockZ(), blockVec4.getX(), blockVec4.getY(), blockVec4.getZ(), 180, 0, 0, 1, blockVec4.getBaseblock()));

        });

        new UtilsFAWE(p).setBlockList(p, bv4, false);
    }


    @Contract("_, _, _, _, _, _, _, _, _, _, _ -> new")
    private static @NotNull BlockVec4 transformPoints(int Xcenter, int Ycenter, int Zcenter, int Xpoint, int Ypoint, int Zpoint, double yaw, double pitch, double roll, double scale, BaseBlock baseBlock) {

        // Convert to radians
        yaw = Math.toRadians(yaw);
        pitch = Math.toRadians(pitch);
        roll = Math.toRadians(roll);
        // Store the values so we don't have to calculate them again for every single point.
        double cp = Math.cos(pitch);
        double sp = Math.sin(pitch);
        double cy = Math.cos(yaw);
        double sy = Math.sin(yaw);
        double cr = Math.cos(roll);
        double sr = Math.sin(roll);

        double x, bx, y, by, z, bz;

        x = Xpoint;
        bx = x;

        y = Ypoint;
        by = y;

        z = Zpoint;
        bz = z;

        x = ((x*cy-bz*sy)*cr+by*sr)*scale;
        y = ((y*cp+bz*sp)*cr-bx*sr)*scale;
        z = ((z*cp-by*sp)*cy+bx*sy)*scale;


        System.out.println("x = " + Math.round(Xcenter+x) + " | " +  Math.round(Ycenter+y) + " | " +  Math.round(Zcenter+z));

        return new BlockVec4((int) Math.round(Xcenter+x), (int) Math.round(Ycenter+y), (int) Math.round(Zcenter+z), baseBlock);
    }

}
