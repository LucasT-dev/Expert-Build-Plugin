package fr.Marodeur.ExpertBuild.Brush.Brush;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.API.GlueList;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DrainBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.isOp() || p.hasPermission("expdrain.use");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.DRAIN;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return brushBuilder.getEnable();
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

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p)) ||
                !BrushBuilder.getBrushBuilderPlayer(p).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location l = (Location) obj1;
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = bb.getRayon();
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
