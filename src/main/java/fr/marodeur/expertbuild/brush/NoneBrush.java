
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

public class NoneBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "none";
    }

    @Override
    public String getPermission() {
        return "exp.brush.none";
    }

    @Override
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        brushBuilder.sendMessage("expbuild.message.brush.brush_disable", true);
    }

    @Override
    public void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        brushBuilder.sendMessage("expbuild.message.brush.brush_disable", true);
    }

    @Override
    public void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        brushBuilder.sendMessage("expbuild.message.brush.brush_disable", true);
    }
}
