
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.BrushBuilder;

import org.bukkit.Location;

import java.util.ArrayList;

public class SpikeBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "spike";
    }

    @Override
    public String getPermission() {
        return "exp.brush.spike";
    }

    @Override
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        return spectralToolBrush(brushBuilder, loc, ploc);
    }

    @Override
    public boolean spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        Location pl = (Location) ploc;
        int radius = brushBuilder.getRadius();

        this.setBrushBuilder(brushBuilder);
        this.setPattern(brushBuilder.getPattern());

        GlueList<BlockVectorTool> sphere = new GlueList<>();

        Location loc1 = l.clone()
                .add(-radius, -radius, -radius);
        Location loc2 = l.clone()
                .add(+radius, +radius, +radius);

        for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
            for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++) {
                for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {

                    Location block = new Location(l.getWorld(), x, y, z);

                    if (l.distance(block) <= radius) {
                        sphere.add(new BlockVectorTool().toBlockVectorTool(block));
                    }
                }
            }
        }

        sphere.forEach(bvt ->
                this.addBlock(bvt.getBlockVectorBetweenTwoPoint(new BlockVectorTool().toBlockVectorTool(pl), 1)));

        return true;
    }

    @Override
    public boolean clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        Location pl = (Location) ploc;
        int radius = brushBuilder.getRadius();

        this.setBrushBuilder(brushBuilder);
        this.setPattern(brushBuilder.getPattern());

        ArrayList<BlockVectorTool> sphere = new ArrayList<>();

        Location loc1 = pl.clone()
                .add(-radius, -radius, -radius);
        Location loc2 = pl.clone()
                .add(+radius, +radius, +radius);

        for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
            for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++) {
                for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {

                    Location block = new Location(l.getWorld(), x, y, z);

                    if (pl.distance(block) <= radius) {
                        sphere.add(new BlockVectorTool().toBlockVectorTool(block));
                    }
                }
            }
        }

        sphere.forEach(bvt ->
                this.addBlock(bvt.getBlockVectorBetweenTwoPoint(new BlockVectorTool().toBlockVectorTool(l), 1)));

        return true;
    }
}
