package fr.marodeur.expertbuild.object.shape;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.object.AbstractShape;
import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.BrushBuilder;

import java.util.stream.Stream;

public class SphereShape extends AbstractShape {

    @Override
    public String getShapeName() {
        return "sphere";
    }

    @Override
    public Stream<BlockVectorTool> generateShape(BrushBuilder brushBuilder, BlockVectorTool center) {

        int radius = containsParameter("radius") ? getParameter("radius", Integer.class) : brushBuilder.getRadius();

        // Liste pour stocker les blocs
        GlueList<BlockVectorTool> blocks = new GlueList<>();

        // Limites de la boucle pour parcourir tous les blocs dans le cube englobant
        int minX = center.getBlockX() - radius;
        int maxX = center.getBlockX() + radius;
        int minY = center.getBlockY() - radius;
        int maxY = center.getBlockY() + radius;
        int minZ = center.getBlockZ() - radius;
        int maxZ = center.getBlockZ() + radius;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {

                    BlockVectorTool loc = new BlockVectorTool(x, y, z);

                    // Vérifie si le bloc est dans le rayon de la sphère
                    if (center.distance(loc) <= radius) {
                        blocks.add(loc);
                    }
                }
            }
        }

        // Retourne les blocs sous forme de Stream
        return blocks.stream();
    }
}
