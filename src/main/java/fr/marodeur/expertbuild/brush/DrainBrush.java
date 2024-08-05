
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

import org.bukkit.Location;

public class DrainBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "drain";
    }

    @Override
    public String getPermission() {
        return "exp.brush.drain";
    }

    @Override
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        return false;
    }

    @Override
    public boolean spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        Location pl = (Location) ploc;
        int radius = brushBuilder.getRadius();

        this.setBrushBuilder(brushBuilder);
        this.setPattern("0");

        Location loc1 = l.clone()
                .add(-radius, -radius, -radius);

        Location loc2 = l.clone()
                .add(+radius, +radius, +radius);

        for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
            for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++) {
                for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {

                    Location bloc = new Location(pl.getWorld(), x, y, z);

                    if (l.distance(bloc) <= radius) {

                        if (bloc.getBlock().isLiquid()) {
                            this.addBlock(new BlockVectorTool().toBlockVectorTool(bloc));
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        return spectralToolBrush(brushBuilder, loc, ploc);
    }
}