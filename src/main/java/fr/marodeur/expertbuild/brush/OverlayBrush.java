
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

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
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

    }

    @Override
    public void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        int brushSize = brushBuilder.getRadius();
        Location l = (Location) loc;
        List<BlockVec4> bv4 = new ArrayList<>();

        for (int x = l.getBlockX() + brushSize; x >= l.getBlockX() - brushSize; x--) {

            for (int z = l.getBlockZ() + brushSize; z >= l.getBlockZ() - brushSize; z--) {

                if (l.distance(new Location(l.getWorld(), x, l.getY(), z)) <= brushSize) {

                    for (int y = l.getBlockY() + brushSize; l.getBlockY() + brushSize >= l.getBlockY() - brushSize; y--) {

                        Location floc = new Location(l.getWorld(), x, y, z);

                        if (!new UtilsFAWE(brushBuilder.getPlayer()).ignoredBlock(floc.getBlock())) {
                            bv4.add(new BlockVec4(floc, brushBuilder.getPattern()));
                            break;
                        }
                    }
                }
            }
        }
        new UtilsFAWE(brushBuilder.getPlayer()).setBlockListSimple(brushBuilder.getPlayer(), bv4, false);
    }

    @Override
    public void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        int brushSize = brushBuilder.getRadius();
        Location l = (Location) loc;
        List<BlockVec4> bv4 = new ArrayList<>();

        for (int x = l.getBlockX() + brushSize; x >= l.getBlockX() - brushSize; x--) {

            for (int z = l.getBlockZ() + brushSize; z >= l.getBlockZ() - brushSize; z--) {

                if (l.distance(new Location(l.getWorld(), x, l.getY(), z)) <= brushSize) {

                    for (int y = l.getBlockY() + brushSize; l.getBlockY() + brushSize >= l.getBlockY() - brushSize; y--) {

                        Location floc = new Location(l.getWorld(), x, y, z);

                        if (!new UtilsFAWE(brushBuilder.getPlayer()).ignoredBlock(floc.getBlock())) {
                            floc.setY(y+1);
                            bv4.add(new BlockVec4(floc, brushBuilder.getPattern()));
                            break;
                        }
                    }
                }
            }
        }
        new UtilsFAWE(brushBuilder.getPlayer()).setBlockListSimple(brushBuilder.getPlayer(), bv4, false);
    }
}
