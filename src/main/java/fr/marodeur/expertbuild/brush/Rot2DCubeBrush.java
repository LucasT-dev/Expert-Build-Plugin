
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
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Polygonal2DRegion;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Rot2DCubeBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "2dcube";
    }

    @Override
    public String getPermission() {
        return "exp.brush.2dcube";
    }

    @Override
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        Location pl = (Location) ploc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();
        GlueList<BlockVec4> bv4 = new GlueList<>();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    int radiusCube = (int) Math.sqrt(radius * radius + radius * radius);

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

                        for (int i = l.getBlockY(); i < l.getBlockY() + (2 * radiusCube); i++) {
                            bv4.add(new BlockVec4(blockVector3.getBlockX(), i, blockVector3.getBlockZ(), brushBuilder.getPattern()));
                        }
                    });

                    new UtilsFAWE(brushBuilder.getPlayer()).setBlockListSimple(brushBuilder.getPlayer(), bv4, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        honeycombToolBrush(brushBuilder, loc, ploc);
    }

    @Override
    public void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        honeycombToolBrush(brushBuilder, loc, ploc);
    }
}
