
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DegradeBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "degrade";
    }

    @Override
    public String getAliases() {
        return null;
    }

    @Override
    public String getPermission() {
        return "exp.brush.degrade";
    }

    @Override
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();
        List<BlockVec4> bv4 = new ArrayList<>();
        List<BlockVec4> bv4block = new ArrayList<>();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    for (int x = l.getBlockX() + radius; x >= l.getBlockX() - radius; x--) {
                        for (int z = l.getBlockZ() + radius; z >= l.getBlockZ() - radius; z--) {

                            Location locsphere = new Location(l.getWorld(), x, l.getY(), z);

                            if (l.distance(locsphere) <= radius) {
                                int y = new UtilsFAWE(brushBuilder.getPlayer()).getHeight(x, z, l);
                                if (y != 999) {
                                    int r = new Random().nextInt(11);
                                    if (r < 5) {
                                        bv4block.add(new BlockVec4(x, y, z, editsession.getFullBlock(BlockVector3.at(x, y, z))));
                                    }
                                }
                            }
                        }
                    }

                    bv4block.forEach(blockVec4 -> {

                        int x1 = new Random().nextInt(radius + 1);
                        int z1 = new Random().nextInt(radius + 1);

                        Location loc1 = new Location(l.getWorld(), l.getBlockX() - radius + x1, l.getY() + radius, l.getBlockZ() - radius + z1);
                        int y1 = new UtilsFAWE(brushBuilder.getPlayer()).getHeight(loc1.getBlockX(), loc1.getBlockZ(), l);

                        bv4.add(new BlockVec4(loc1.getBlockX(), y1+1, loc1.getBlockZ(), blockVec4.getBaseblock()));
                        bv4.add(new BlockVec4(blockVec4.getX(), blockVec4.getY(), blockVec4.getZ(), Material.AIR));

                    });

                    new UtilsFAWE(brushBuilder.getPlayer()).setBlockAnyPattern(brushBuilder.getPlayer(), bv4, false);
                } finally {
                    localSession.remember(editsession);
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