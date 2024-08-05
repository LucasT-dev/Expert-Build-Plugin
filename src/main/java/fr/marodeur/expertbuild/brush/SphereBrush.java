
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

public class SphereBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "sphere";
    }

    @Override
    public String getAliases() {
        return "s";
    }

    @Override
    public String getPermission() {
        return "exp.brush.sphere";
    }

    @Override
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location middlePoint = (Location) loc;
        int radius = brushBuilder.getRadius();

        this.setBrushBuilder(brushBuilder);
        this.setPattern(brushBuilder.getPattern());

        apply(middlePoint, radius);

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

    private void apply(Location middlePoint, int radius) {

        Location loc1 = middlePoint.clone()
                .add(-radius, -radius, -radius);

        Location loc2 = middlePoint.clone()
                .add(+radius, +radius, +radius);

        for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
            for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++) {
                for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {

                    Location loc = new Location(middlePoint.getWorld(), x, y, z);

                    if (middlePoint.distance(loc) <= radius) {
                        this.addBlock(new BlockVectorTool().toBlockVectorTool(loc));
                    }
                }
            }
        }
    }
}
