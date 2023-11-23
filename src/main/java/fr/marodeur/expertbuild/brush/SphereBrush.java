package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.enums.BrushEnum;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.BrushOperation;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.function.pattern.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SphereBrush implements BrushOperation {

    private static final GlueList<BlockVec4> bv4 = new GlueList<>();

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission("exp.brush.sphere");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.SPHERE;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return BrushOperation.super.hasEnabelingBrush(brushBuilder);
    }

    @SuppressWarnings({"deprecation"})
    @Override
    public void ExecuteBrushOnHoney(Player p, Object obj1) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p, true)) ||
                !BrushBuilder.getBrushBuilderPlayer(p, true).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location middlePoint = (Location) obj1;
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    apply(middlePoint, radius, brushBuilder.getPattern());

                } finally {
                    new UtilsFAWE(p).setBlockListSimple(p, bv4, false);
                }
            }
        });
    }

    @Override
    public void ExecuteBrushOnArrow(Player p, Object obj1, Object loc) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p, true)) ||
                !BrushBuilder.getBrushBuilderPlayer(p, true).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location middlePoint = (Location) obj1;
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    apply(middlePoint, radius, brushBuilder.getPattern());

                } finally {
                    new UtilsFAWE(p).setBlockListSimple(p, (List<BlockVec4>) bv4.clone(), false);
                }
            }
            bv4.clear();
        });
    }

    @Override
    public void ExecuteBrushOnGunpowder(Player p, Object obj1, Object loc) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p, true)) ||
                !BrushBuilder.getBrushBuilderPlayer(p, true).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location middlePoint = (Location) obj1;
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    apply(middlePoint, radius, brushBuilder.getPattern());

                } finally {
                    new UtilsFAWE(p).setBlockListSimple(p, (List<BlockVec4>) bv4.clone(), false);
                }
            }
            bv4.clear();
        });
    }

    private static void apply(@NotNull Location middlePoint, int radius, Pattern pattern) {

        Location loc1 = middlePoint.clone()
                .add(-radius, -radius, -radius)
                .getBlock().getLocation();
        Location loc2 = middlePoint.clone()
                .add(+radius, +radius, +radius)
                .getBlock().getLocation();

        for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
            for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++) {
                for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {

                    Location loc = new Location(middlePoint.getWorld(), x, y, z);

                    if (middlePoint.distance(loc) <= radius) {
                        bv4.add(new BlockVec4(loc, pattern));
                    }
                }
            }
        }
    }
}
