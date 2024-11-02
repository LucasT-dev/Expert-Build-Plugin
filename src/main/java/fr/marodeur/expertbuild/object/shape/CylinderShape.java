package fr.marodeur.expertbuild.object.shape;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.object.AbstractShape;
import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.BrushBuilder;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CylinderShape extends AbstractShape {

    @Override
    public String getShapeName() {
        return "cylinder";
    }

    @Override
    public Stream<BlockVectorTool> generateShape(BrushBuilder brushBuilder, BlockVectorTool center) {

        // Récupère le rayon depuis la carte des paramètres, sinon utilise celui de brushBuilder par défaut
        double radius = containsParameter("radius") ? getParameter("radius", Double.class) : brushBuilder.getRadius();
        int height = containsParameter("height") ? getParameter("height", Integer.class) : brushBuilder.getRadius();

        // Calculer les limites du cylindre
        int minY = center.getBlockY();
        int maxY = minY + height - 1;

        // Liste pour stocker les blocs
        GlueList<BlockVectorTool> blocks = new GlueList<>();

        // Parcourir chaque couche du cylindre sur la hauteur
        IntStream.rangeClosed(minY, maxY).forEach(y -> {
            for (double x = center.getBlockX() - radius; x <= center.getBlockX() + radius; x++) {
                for (double z = center.getBlockZ() - radius; z <= center.getBlockZ() + radius; z++) {
                    BlockVectorTool loc = new BlockVectorTool(x, y, z);

                    // Vérifie si le point (x, z) est dans le rayon du cylindre
                    if (center.distanceXZ(loc) <= radius) {
                        blocks.add(loc); // Ajoute le bloc à la liste
                    }
                }
            }
        });
        return blocks.stream();
    }
}
