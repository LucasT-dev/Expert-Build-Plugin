
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

public class FlowerBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "flower";
    }
    @Override
    public String getAliases() {
        return "fw";
    }

    @Override
    public String getPermission() {
        return "exp.brush.flower";
    }

    @Override
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;

        this.setBrushBuilder(brushBuilder);
        this.setPattern(brushBuilder.getPattern());

        BrushParameter brushParameter = brushBuilder.getBrushParameter();
        brushParameter.getShape()
                .generateShape(brushBuilder, new BlockVectorTool().toBlockVectorTool(l))
                .forEach(this::addBlock);

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
