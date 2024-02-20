
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;

import org.bukkit.Location;

public class Clipboard3DBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "clipboard3D";
    }

    @Override
    public String getAliases() {
        return null;
    }

    @Override
    public String getPermission() {
        return "exp.brush.clipboard3d";
    }

    @Override
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;

        assert brushBuilder != null;

        GlueList<BlockVec4> bv4 = new GlueList<>();
        GlueList<BlockVec4> tempbv4 = new GlueList<>();

        float pitch = brushBuilder.getPlayer().getLocation().getPitch();
        float yaw = brushBuilder.getPlayer().getLocation().getYaw();

        // Y rotation -> Yaw
        // X rotation -> Pitch

        brushBuilder.getClipboardBrush().getClipboardsBrush().iterator()
                .forEachRemaining(bv3 ->
                        tempbv4.add(
                                bv3.rotateAroundX(
                                        l.getBlockY(),
                                        l.getBlockZ(),
                                        l.getBlockY() + bv3.getY(),
                                        l.getBlockZ() + bv3.getZ(),
                                        pitch + 90,
                                        bv3.getBaseblock(),
                                        l.getBlockX() + bv3.getX())));

        tempbv4.forEach(blv4 ->
                bv4.add(
                        blv4.rotateAroundY(
                                l.getBlockX(),
                                l.getBlockZ(),
                                blv4.getX(),
                                blv4.getZ(),
                                yaw,
                                blv4.getBaseblock(),
                                blv4.getY() + 1)
                ));

        new UtilsFAWE(brushBuilder.getPlayer()).setBlockList(brushBuilder.getPlayer(), bv4, false);
    }

    @Override
    public void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

    }

    @Override
    public void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

    }
}
