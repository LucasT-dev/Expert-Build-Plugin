package fr.marodeur.expertbuild.brush;

/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.object.*;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;

public class DegradeBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "degrade";
    }

    @Override
    public String getPermission() {
        return "exp.brush.degrade";
    }

    @Override
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        int radius = brushBuilder.getRadius();
        BlockVectorMaterial bvmtemp = new BlockVectorMaterial();
        BlockVectorMaterial bvm = new BlockVectorMaterial();


        for (int x = l.getBlockX() + radius; x >= l.getBlockX() - radius; x--) {
            for (int z = l.getBlockZ() + radius; z >= l.getBlockZ() - radius; z--) {

                Location locsphere = new Location(l.getWorld(), x, l.getY(), z);

                if (l.distance(locsphere) <= radius) {
                    int y = getHeight(x, z, l.getBlockY(), l.getWorld(), radius);
                    if (y != 999) {
                        int r = new Random().nextInt(11);
                        if (r < 5) {
                            bvmtemp.addPositionMaterial(new BlockVectorTool(x, y, z), l.getWorld().getType(x, y, z));
                            //bv4block.add(new BlockVec4(x, y, z, editsession.getFullBlock(BlockVector3.at(x, y, z))));
                        }
                    }
                }
            }
        }

        bvmtemp.streamPairs().forEach(blockVec4 -> {

            int x1 = new Random().nextInt(radius + 1);
            int z1 = new Random().nextInt(radius + 1);

            Location loc1 = new Location(l.getWorld(), l.getBlockX() - radius + x1, l.getY() + radius, l.getBlockZ() - radius + z1);
            int y1 = getHeight(loc1.getBlockX(), loc1.getBlockZ(), l.getBlockY(), l.getWorld(), radius);

            bvm.addPositionMaterial(new BlockVectorTool(loc1.getBlockX(), y1 + 1, loc1.getBlockZ()), blockVec4.material());
            bvm.addPositionMaterial(new BlockVectorTool(loc1.getBlockX(), y1 + 1, loc1.getBlockZ()), Material.AIR);

        });

        new FaweAPI(brushBuilder.getPlayer()).setBlock(bvm, false);

        return false;
    }

    @Override
    public boolean spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        return false;
    }

    @Override
    public boolean clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        return false;
    }

    public int getHeight(int x, int z, int y, World world, int radius) {

        for (int i = y+radius; i >= 0; i--) {
            if (!new Location(world, x, i, z).getBlock().getType().isAir()) {
                return i;
            }
        }
        return 0;
        //return y-b.getRayon();
    }
}