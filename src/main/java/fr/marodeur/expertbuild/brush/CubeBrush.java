
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.BrushBuilder;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;

import org.bukkit.Location;

public class CubeBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "cube";
    }

    @Override
    public String getPermission() {
        return "exp.brush.cube";
    }

    @Override
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        return spectralToolBrush(brushBuilder, loc, ploc);
    }

    @Override
    public boolean spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        int radius = brushBuilder.getRadius();

        this.setBrushBuilder(brushBuilder);
        this.setPattern(brushBuilder.getPattern());

        CuboidRegion cuboidRegion = new CuboidRegion(BlockVector3.at(l.getBlockX(), l.getBlockY(), l.getBlockZ()).add(radius, 0, radius),
                BlockVector3.at(l.getBlockX(), l.getBlockY(), l.getBlockZ()).add(-radius, 2*radius, -radius));

        cuboidRegion
                .stream()
                .iterator()
                .forEachRemaining(bv3 -> this.addBlock(new BlockVectorTool(bv3.x(), bv3.y(), bv3.z())));

        return true;
    }

    @Override
    public boolean clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        return spectralToolBrush(brushBuilder, loc, ploc);
    }
}