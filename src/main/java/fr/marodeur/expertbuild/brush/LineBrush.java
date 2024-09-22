
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.*;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class LineBrush extends AbstractBrush {

    private final HashMap<UUID,ArrayList<BlockVectorTool>> point = new HashMap<>();
    Configuration conf = Main.getConfiguration();


    @Override
    public String getBrushName() {
        return "line";
    }

    @Override
    public String getPermission() {
        return "exp.brush.line";
    }

    @Override
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        return false;
    }

    @Override
    public boolean spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;

        ArrayList<BlockVectorTool> pointList;
        if (!point.containsKey(brushBuilder.getUUID())) {

            pointList = new ArrayList<>();
            pointList.add(new BlockVectorTool().toBlockVectorTool(l));

            point.put(brushBuilder.getUUID(), pointList);

        } else {

            pointList = new ArrayList<>(point.get(brushBuilder.getUUID()));

            //en config
            if (pointList.size() >= conf.getMax_point_saved()) {
                point.replace(brushBuilder.getUUID(), pointList);
                brushBuilder.sendMessage("expbuild.message.brush.point_not_save", true);

                return false;
            }

            pointList.add(new BlockVectorTool().toBlockVectorTool(l));
            point.replace(brushBuilder.getUUID(), pointList);

        }
        brushBuilder.sendMessage("expbuild.message.brush.point_add", true, new String[]{l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ() });
        return false;
    }

    @Override
    public boolean clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        this.setBrushBuilder(brushBuilder);
        this.setPattern(brushBuilder.getPattern());

        if (point.containsKey(brushBuilder.getUUID())) {

            ArrayList<BlockVectorTool> points = point.get(brushBuilder.getUUID());

            for (int i = 0; i < points.size() - 1; i++) {

                BlockVectorTool start = points.get(i);
                BlockVectorTool end = points.get(i + 1);

                this.addBlock(start.getBlockBetweenTwoPoint(end));

            }
            point.remove(brushBuilder.getUUID());
            return true;
        } else {

            //Todo Mettre en config et recup spectral arrow en config
            brushBuilder.getPlayer().sendMessage(Main.prefix + " Use the spectral arrow for add new point");
        }
        return false;
    }
}
