
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
import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class EraserBrush extends AbstractBrush {

    private static final GlueList<BlockVec4> bv4 = new GlueList<>();

    private static final Set<Material> EXCLUSIVE_MATERIALS = EnumSet.of(
            Material.AIR, Material.WATER, Material.STONE, Material.GRASS_BLOCK, Material.DIRT, Material.SAND, Material.DEEPSLATE, Material.SANDSTONE);


    @Override
    public String getBrushName() {
        return "eraser";
    }

    @Override
    public String getPermission() {
        return "exp.brush.eraser";
    }

    @Override
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

    }

    @Override
    public void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location middlePoint = (Location) loc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    apply(middlePoint, radius, true);

                } finally {
                    new UtilsFAWE(brushBuilder.getPlayer()).setBlockAnyPattern(brushBuilder.getPlayer(), (List<BlockVec4>) bv4.clone(), false);
                }
            }
            bv4.clear();
        });
    }

    @Override
    public void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location middlePoint = (Location) loc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    apply(middlePoint, radius, false);

                } finally {
                    new UtilsFAWE(brushBuilder.getPlayer()).setBlockAnyPattern(brushBuilder.getPlayer(), (List<BlockVec4>) bv4.clone(), false);
                }
            }
            bv4.clear();
        });
    }

    private static void apply(@NotNull Location middlePoint, int radius, boolean deleteFluid) {

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

                        if (!EXCLUSIVE_MATERIALS.contains(loc.getBlock().getType())) {
                            bv4.add(new BlockVec4(loc, Material.AIR));
                        }

                        if (loc.getBlock().isLiquid() && deleteFluid) {
                            bv4.add(new BlockVec4(loc, Material.AIR));
                        }
                    }
                }
            }
        }
    }
}
