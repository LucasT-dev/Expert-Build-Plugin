
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import com.sk89q.worldedit.math.BlockVector2;
import com.sk89q.worldedit.regions.selector.Polygonal2DRegionSelector;
import com.sk89q.worldedit.bukkit.BukkitAdapter;

import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.BrushBuilder;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

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
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        Location pl = (Location) ploc;
        int radius = brushBuilder.getRadius();

        this.setBrushBuilder(brushBuilder);
        this.setPattern(brushBuilder.getPattern());

        int radiusCube = (int) Math.sqrt(radius * radius + radius * radius);

        Location ldir = new BlockVectorTool().toBlockVectorTool(l).getBlockVectorAngle(0, pl.getYaw(), radiusCube).toLocation(l.getWorld());

        int dx1 = ldir.getBlockX() - l.getBlockX();
        int dz1 = ldir.getBlockZ() - l.getBlockZ();

        //Les 4 coins du carré
        Location l1 = ldir.add(dz1, l.getBlockY(), -dx1);

        int dx2 = l1.getBlockX() - l.getBlockX();
        int dz2 = l1.getBlockZ() - l.getBlockZ();

        Location l2 = l.clone().add(dz2, l.getBlockY(), -dx2);
        Location l3 = l.clone().add(-dz2, l.getBlockY(), dx2);
        Location l4 = l.clone().add(-dx2, l.getBlockY(), -dz2);


        List<BlockVector2> point = new ArrayList<>(4);
        point.add(BlockVector2.at(l1.getBlockX(), l1.getBlockZ()));
        point.add(BlockVector2.at(l2.getBlockX(), l2.getBlockZ()));
        point.add(BlockVector2.at(l4.getBlockX(), l4.getBlockZ()));
        point.add(BlockVector2.at(l3.getBlockX(), l3.getBlockZ()));


        Polygonal2DRegionSelector polygonal2DRegionSelector = new Polygonal2DRegionSelector(BukkitAdapter.adapt(l.getWorld()), point, l.getBlockY(), l.getBlockY() + radiusCube*2);

        polygonal2DRegionSelector.getRegion().forEach(bv3 ->  this.addBlock(new BlockVectorTool(bv3.x(), bv3.y(), bv3.z())));

        return true;
    }

    @Override
    public boolean spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        return honeycombToolBrush(brushBuilder, loc, ploc);
    }

    @Override
    public boolean clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        return honeycombToolBrush(brushBuilder, loc, ploc);
    }
}