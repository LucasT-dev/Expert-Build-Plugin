package fr.Marodeur.ExpertBuild.Brush;


import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OverlayBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.isOp() | p.hasPermission("expoverlay.use");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.OVERLAY;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return BrushOperation.super.hasEnabelingBrush(brushBuilder);
    }

    @Override
    public void ExecuteBrushOnArrow(Player p, Object obj1, Object loc) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p)) ||
                !BrushBuilder.getBrushBuilderPlayer(p).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);
        int brushSize = brushBuilder.getRadius();
        Location l = (Location) obj1;
        List<BlockVec4> bv4 = new ArrayList<>();

        for (int x = l.getBlockX() + brushSize; x >= l.getBlockX() - brushSize; x--) {

            for (int z = l.getBlockZ() + brushSize; z >= l.getBlockZ() - brushSize; z--) {

                if (l.distance(new Location(l.getWorld(), x, l.getY(), z)) <= brushSize) {

                    for (int y = l.getBlockY() + brushSize; l.getBlockY() + brushSize >= l.getBlockY() - brushSize; y--) {

                        Location floc = new Location(l.getWorld(), x, y, z);

                        if (!new UtilsFAWE(p).ignoredBlock(floc.getBlock())) {
                            bv4.add(new BlockVec4(floc, brushBuilder.getPattern()));
                            break;
                        }
                    }
                }
            }
        }
        new UtilsFAWE(p).setBlockListSimple(p, bv4, false);
    }

    @Override
    public void ExecuteBrushOnGunpowder(Player p, Object obj1, Object loc) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p)) ||
                !BrushBuilder.getBrushBuilderPlayer(p).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);
        int brushSize = brushBuilder.getRadius();
        Location l = (Location) obj1;
        List<BlockVec4> bv4 = new ArrayList<>();

        for (int x = l.getBlockX() + brushSize; x >= l.getBlockX() - brushSize; x--) {

            for (int z = l.getBlockZ() + brushSize; z >= l.getBlockZ() - brushSize; z--) {

                if (l.distance(new Location(l.getWorld(), x, l.getY(), z)) <= brushSize) {

                    for (int y = l.getBlockY() + brushSize; l.getBlockY() + brushSize >= l.getBlockY() - brushSize; y--) {

                        Location floc = new Location(l.getWorld(), x, y, z);

                        if (!new UtilsFAWE(p).ignoredBlock(floc.getBlock())) {
                            floc.setY(y+1);
                            bv4.add(new BlockVec4(floc, brushBuilder.getPattern()));
                            break;
                        }
                    }
                }
            }
        }
        new UtilsFAWE(p).setBlockListSimple(p, bv4, false);
    }
}
