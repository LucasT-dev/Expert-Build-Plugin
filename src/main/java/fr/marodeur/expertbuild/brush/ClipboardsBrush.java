
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
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ClipboardsBrush extends AbstractBrush {

    private static final List<Integer> rotation = Arrays.asList(0, 90, 180, 270);

    @Override
    public String getBrushName() {
        return "clipboard";
    }

    @Override
    public String getPermission() {
        return "exp.brush.clipboard";
    }

    @Override
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        Random r = new Random();
        GlueList<BlockVec4> bv4 = new GlueList<>();

        if (brushBuilder.getClipboardsParameter().getClipboardsBlock().size() == 0) {

            brushBuilder.sendMessage("expbuild.message.selection.no_selection_save", true);
            return;
        }

        int randomRotation = 0;
        int index = r.nextInt(brushBuilder.getClipboardsParameter().getClipboardsName().size());

        if (brushBuilder.getClipboardsParameter().isRandomRotation()) {
            randomRotation = rotation.get(r.nextInt(rotation.size()));
        }

        int finalRandomRotation = randomRotation;
        brushBuilder.getClipboardsParameter().getClipboardsBlock().get(index)
                .iterator()
                .forEachRemaining(bv3 ->
                        bv4.add(bv3.rotateAroundY(l.getBlockX(), l.getBlockZ(),
                                l.getBlockX() + bv3.getX(),
                                l.getBlockZ() + bv3.getZ(),
                                finalRandomRotation, bv3.getBaseblock(),
                                l.getBlockY() + bv3.getY() + 1)));

        new UtilsFAWE(brushBuilder.getPlayer()).setBlockList(brushBuilder.getPlayer(), bv4, false);
    }

    @Override
    public void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

    }

    @Override
    public void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

    }
}
