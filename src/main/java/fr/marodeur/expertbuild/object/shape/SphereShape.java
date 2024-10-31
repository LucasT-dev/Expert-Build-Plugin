package fr.marodeur.expertbuild.object.shape;

import fr.marodeur.expertbuild.object.AbstractShape;
import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.BrushBuilder;

public class SphereShape extends AbstractShape {

    @Override
    public void generateShape(BrushBuilder brushBuilder, BlockVectorTool center) {

        int radius = brushBuilder.getRadius();

        BlockVectorTool loc1 = center.clone()
                .add(-radius, -radius, -radius);

        BlockVectorTool loc2 = center.clone()
                .add(+radius, +radius, +radius);

        for (int x = loc1.getBlockX(); x <= loc2.getBlockX(); x++) {
            for (int y = loc1.getBlockY(); y <= loc2.getBlockY(); y++) {
                for (int z = loc1.getBlockZ(); z <= loc2.getBlockZ(); z++) {

                    BlockVectorTool loc = new BlockVectorTool(x, y, z);

                    if (center.distance(loc) <= radius) {
                        this.add(loc);
                    }
                }
            }
        }
    }
}
