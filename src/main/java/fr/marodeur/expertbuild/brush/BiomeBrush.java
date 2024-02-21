
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BrushBuilder;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;

public class BiomeBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "biome";
    }

    @Override
    public String getPermission() {
        return "exp.brush.biome";
    }

    @Override
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
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

                                Location blockloc = new Location(l.getWorld(), x, y, z);

                                if (l.distance(blockloc) <= radius) {
                                    blockloc.getBlock().setBiome(brushBuilder.getBiome());
                                    if (!chunks.contains(blockloc.getChunk())) chunks.add(blockloc.getChunk());

                                }
                            }
                        }
                    }
                } finally {
                    chunks.forEach(chunk -> new UtilsFAWE(brushBuilder.getPlayer()).refreshChunk(chunk));
                }
            }
        });
    }

    @Override
    public void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

    }

    @Override
    public void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

    }
}
