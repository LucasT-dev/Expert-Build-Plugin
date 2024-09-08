
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import com.sk89q.worldedit.bukkit.BukkitAdapter;

import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.builderObjects.Clipboard3DParameter;

import org.bukkit.Location;

public class Clipboard3DBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "clipboard3D";
    }

    @Override
    public String getPermission() {
        return "exp.brush.clipboard3d";
    }

    @Override
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        Clipboard3DParameter cb3d = brushBuilder.getClipboard3dParameter();
        BlockVectorTool center = new BlockVectorTool().toBlockVectorTool(l);
        BlockVectorTool origin = new BlockVectorTool().toBlockVectorTool(cb3d.getClipboardHolder().getClipboard().getOrigin());
        BlockVectorMaterial bvm = new BlockVectorMaterial();

        // Y rotation -> Yaw
        // X rotation -> Pitch
        float pitch = brushBuilder.getPlayer().getLocation().getPitch();
        float yaw = brushBuilder.getPlayer().getLocation().getYaw();


        cb3d.getClipboardHolder().getClipboard().iterator().forEachRemaining(blockVector3 -> {

            BlockVectorTool origineBlock = new BlockVectorTool().toBlockVectorTool(blockVector3);
            BlockVectorTool difference = origineBlock.clone();
            difference.subtract(origin);

            BlockVectorTool rotatedBlock = center.clone();
            rotatedBlock.add(difference);

            rotatedBlock = rotatedBlock.rotateAroundPositionX(rotatedBlock, center, pitch + 90);
            rotatedBlock = rotatedBlock.rotateAroundPositionY(rotatedBlock, center, -yaw);

            bvm.addPositionMaterial(rotatedBlock, blockVector3.getFullBlock(BukkitAdapter.adapt(brushBuilder.getPlayer()).getExtent()));

        });

        new FaweAPI(brushBuilder.getPlayer()).setBlock(bvm, false);

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
