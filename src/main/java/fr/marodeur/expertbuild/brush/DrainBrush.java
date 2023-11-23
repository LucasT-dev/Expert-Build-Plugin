package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.enums.BrushEnum;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.BrushOperation;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DrainBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission("exp.brush.drain");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.DRAIN;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return BrushOperation.super.hasEnabelingBrush(brushBuilder);
    }

    @Override
    public void ExecuteBrushOnGunpowder(Player p, Object obj1, Object loc) {
        ExecuteBrushOnArrow(p, obj1, loc);
    }

    public DrainBrush() {
    }

    @Deprecated
    @Override
    public void ExecuteBrushOnArrow(Player p, Object obj1, Object loc) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p, true)) ||
                !BrushBuilder.getBrushBuilderPlayer(p, true).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location l = (Location) obj1;
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p, true);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = bb.getRadius();
        GlueList<BlockVec4> bv4 = new GlueList<>();

        Location loc1 = l.clone()
                .add(-radius, -radius, -radius)
                .getBlock().getLocation();
        Location loc2 = l.clone()
                .add(+radius, +radius, +radius)
                .getBlock().getLocation();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
                        for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++) {
                            for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {

                                Location bloc = new Location(p.getWorld(), x, y, z);

                                if (l.distance(bloc) <= radius) {

                                    if (bloc.getBlock().isLiquid()) {
                                        bv4.add(new BlockVec4(bloc, Material.AIR, p));
                                    }
                                }
                            }
                        }
                    }

                    new UtilsFAWE(p).setBlockAnyPattern(p, bv4, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
