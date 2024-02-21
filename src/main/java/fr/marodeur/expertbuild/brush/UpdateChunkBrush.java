
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import org.bukkit.Location;

public class UpdateChunkBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "updateChunk";
    }

    @Override
    public String getPermission() {
        return "exp.brush.updatechunk";
    }

    @Override
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        new UtilsFAWE(brushBuilder.getPlayer()).refreshChunk(((Location) loc).getChunk());
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
