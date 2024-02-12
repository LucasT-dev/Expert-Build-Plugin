package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.enums.BrushEnum;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.BrushOperation;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BiomeBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission("exp.brush.biome");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.BIOME;
    }

    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return BrushOperation.super.hasEnabelingBrush(brushBuilder);
    }

    @Deprecated
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
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();
        GlueList<Chunk> chunks = new GlueList<>();

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

                                Location loc = new Location(p.getWorld(), x, y, z);

                                if (l.distance(loc) <= radius) {
                                    loc.getBlock().setBiome(brushBuilder.getBiome());
                                    if (!chunks.contains(loc.getChunk())) chunks.add(loc.getChunk());

                                }
                            }
                        }
                    }
                } finally {
                    chunks.forEach(chunk -> new UtilsFAWE(p).refreshChunk(chunk));
                }
            }
        });
    }
}
