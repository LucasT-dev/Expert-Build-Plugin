
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.MessageBuilder;

public class NoneBrush extends AbstractBrush {

    private MessageBuilder message = Main.getInstance().getMessageConfig();

    @Override
    public String getBrushName() {
        return "none";
    }

    @Override
    public String getAliases() {
        return null;
    }

    @Override
    public String getPermission() {
        return "exp.brush.none";
    }

    @Override
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        brushBuilder.sendMessage(message.getBrushDisable());
    }

    @Override
    public void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        brushBuilder.sendMessage(message.getBrushDisable());
    }

    @Override
    public void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        brushBuilder.sendMessage(message.getBrushDisable());
    }
}
