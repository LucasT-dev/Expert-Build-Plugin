
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
import fr.marodeur.expertbuild.object.builderObjects.BrushParameter;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class OverlayBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "overlay";
    }

    @Override
    public String getPermission() {
        return "exp.brush.overlay";
    }

    @Override
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        return false;
    }

    @Override
    public boolean spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;

        this.setBrushBuilder(brushBuilder);
        this.setPattern(brushBuilder.getPattern());

       BrushParameter brushParameter = brushBuilder.getBrushParameter();
       brushParameter.getShape().generateShape(brushBuilder, new BlockVectorTool().toBlockVectorTool(l));

       brushParameter.getShape().getBlockVectorList().forEach(bvt -> {

           Location floc = new Location(l.getWorld(), bvt.getBlockX(), bvt.getBlockY(), bvt.getBlockZ());

           if (!this.ignoredBlock(floc.getBlock()) && floc.clone().add(0,1,0).getBlock().getType().isAir()) {
               this.addBlock(new BlockVectorTool().toBlockVectorTool(floc));
           }
       });

       brushParameter.getShape().clearBlockVector();
       return true;
    }

    @Override
    public boolean clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;

        this.setBrushBuilder(brushBuilder);
        this.setPattern(brushBuilder.getPattern());

        BrushParameter brushParameter = brushBuilder.getBrushParameter();
        brushParameter.getShape().generateShape(brushBuilder, new BlockVectorTool().toBlockVectorTool(l));

        brushParameter.getShape().getBlockVectorList().forEach(bvt -> {

            Location floc = new Location(l.getWorld(), bvt.getBlockX(), bvt.getBlockY(), bvt.getBlockZ());

            if (!this.ignoredBlock(floc.getBlock()) && floc.clone().add(0,1,0).getBlock().getType().isAir()) {
                this.addBlock(new BlockVectorTool().toBlockVectorTool(floc).add(0, 1, 0));
            }

        });

        brushParameter.getShape().clearBlockVector();

        return true;
    }

    private boolean ignoredBlock(@NotNull Block b) {
        return b.isLiquid() || b.isPassable() || b.getType().isAir() || b.toString().contains("leaves");
    }
}
