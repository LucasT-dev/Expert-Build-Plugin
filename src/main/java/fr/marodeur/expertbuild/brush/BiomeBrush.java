
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
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        int radius = brushBuilder.getRadius();

        this.setBrushBuilder(brushBuilder);
        this.setPattern("#biome["+ brushBuilder.getBiome().toString().toLowerCase() +"]");

        GlueList<BlockVectorTool> chunks = new GlueList<>();

        Location loc1 = l.clone()
                .add(-radius, -radius, -radius);

        Location loc2 = l.clone()
                .add(+radius, +radius, +radius);

        for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
            for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++) {
                for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {

                    Location blockloc = new Location(l.getWorld(), x, y, z);

                    if (l.distance(blockloc) <= radius) {

                        this.addBlock(new BlockVectorTool().toBlockVectorTool(blockloc));

                        if (!chunks.contains(new BlockVectorTool(blockloc.getChunk().getX(), 0, blockloc.getChunk().getZ()))) {
                            chunks.add(new BlockVectorTool(blockloc.getChunk().getX(), 0, blockloc.getChunk().getZ()));
                        }
                    }
                }
            }
        }
        // Update chunk now automatically
        //chunks.forEach(chunk -> new UtilsFAWE(brushBuilder.getPlayer()).refreshChunk(chunk.getBlockX(), chunk.getBlockZ()));

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
