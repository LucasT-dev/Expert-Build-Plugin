package fr.Marodeur.ExpertBuild.Brush.Brush;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Polygonal2DRegion;

import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.API.GlueList;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Rot2DCubeBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.isOp() | p.hasPermission("exp2dcube.use");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.ROT2DCUBE;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return brushBuilder.getEnable();
    }

    @Deprecated
    @Override
    public void ExecuteBrushOnHoney(Player p, Object obj1) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p)) ||
                !BrushBuilder.getBrushBuilderPlayer(p).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location l = (Location) obj1;
        Location pl = p.getLocation();
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRayon();
        GlueList<BlockVec4> bv4 = new GlueList<>();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    int radiusCube = (int) Math.sqrt(radius*radius + radius*radius);

                    Location ldir = new BlockVec4(l).getPointAngle(0, pl.getYaw(), radiusCube, l.getWorld());

                    int dx1 = ldir.getBlockX() - l.getBlockX();
                    int dz1 = ldir.getBlockZ() - l.getBlockZ();

                    //Les 4 coins du carré
                    Location l1 = ldir.add(dz1, l.getBlockY(), -dx1);
    
                    int dx2 = l1.getBlockX() - l.getBlockX();
                    int dz2 = l1.getBlockZ() - l.getBlockZ();

                    Location l2 = l.clone().add(dz2, l.getBlockY(), -dx2);
                    Location l3 = l.clone().add(-dz2, l.getBlockY(), dx2);
                    Location l4 = l.clone().add(-dx2, l.getBlockY(), -dz2);

                    Polygonal2DRegion polygonal2DRegion = new Polygonal2DRegion(BukkitAdapter.adapt(l.getWorld()));

                    polygonal2DRegion.addPoint(BlockVector3.at(l1.getBlockX(), l.getBlockY(), l1.getBlockZ()));
                    polygonal2DRegion.addPoint(BlockVector3.at(l2.getBlockX(), l.getBlockY(), l2.getBlockZ()));
                    polygonal2DRegion.addPoint(BlockVector3.at(l4.getBlockX(), l.getBlockY(), l4.getBlockZ()));
                    polygonal2DRegion.addPoint(BlockVector3.at(l3.getBlockX(), l.getBlockY(), l3.getBlockZ()));

                    polygonal2DRegion.forEach(blockVector3 -> {

                        for (int i = l.getBlockY(); i < l.getBlockY()+(2*radiusCube); i++) {
                            bv4.add(new BlockVec4(blockVector3.getBlockX(), i, blockVector3.getBlockZ()));
                        }
                    });

                    new UtilsFAWE(p).setBlockListSimple(p, bv4);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void ExecuteBrushOnArrow(Player p, Object obj1) {
        ExecuteBrushOnHoney(p, obj1);
    }

    @Override
    public void ExecuteBrushOnGunpowder(Player p, Object obj1) {
        ExecuteBrushOnHoney(p, obj1);
    }
}
