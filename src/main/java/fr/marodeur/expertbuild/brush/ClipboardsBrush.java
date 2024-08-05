
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import com.sk89q.worldedit.session.ClipboardHolder;

import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.Flag;
import fr.marodeur.expertbuild.object.builderObjects.ClipboardParameter;

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
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        ClipboardParameter clipboardParameter = brushBuilder.getClipboardParameter();

        Location l = (Location) loc;

        if (clipboardParameter.getClipboardHolders().isEmpty()) {

            brushBuilder.sendMessage("expbuild.message.selection.no_selection_save", true);
            return false;
        }

        //Clipboard
        int index = new Random().nextInt(clipboardParameter.getClipboardsName().size());
        //Y rotation
        int randomRotation = 0;

        ClipboardHolder clipboardHolder = clipboardParameter.getClipboardHolder(index);
        Flag flag = clipboardParameter.getFlag(index);


        if (clipboardParameter.isRandomRotation()) {
            randomRotation = rotation.get(new Random().nextInt(rotation.size()));
        }

        // paste clipboard holder
        new FaweAPI(brushBuilder.getPlayer()).pasteClipboard(clipboardHolder, flag.get('a'), flag.get('e'), flag.get('b'), new BlockVectorTool().toBlockVectorTool(l), false, 0, randomRotation, 0);

        return false;
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
