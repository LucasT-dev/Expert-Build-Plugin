package fr.marodeur.expertbuild.api.fawe.function.pattern;

import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.function.pattern.AbstractExtentPattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SquarePattern extends AbstractExtentPattern {

    private int length = 5;  // Longueur du carré
    private int width = 3;   // Largeur du carré
    private int offset = length/2;  // Décalage entre chaque carré
    private int borderWidth = 1;// Largeur de la bordure
    private final List<BlockState> material = new ArrayList<>();  // Matériau pour le carré

    public SquarePattern(Extent extent, String[] args) {
        super(extent);

        if (args.length >= 2 ) this.length = Integer.parseInt(args[1]);
        if (args.length >= 3 ) this.width = Integer.parseInt(args[2]);
        if (args.length >= 4 ) this.borderWidth = Integer.parseInt(args[3]);
        if (args.length >= 5 ) this.offset = Integer.parseInt(args[4]);

        Arrays.stream(args[0].split(",")).forEach(string -> this.material.add(BlockState.get(string)));

    }


    @Override
    public BaseBlock applyBlock(BlockVector3 position) {

        // Calculer la position relative dans la grille des carrés
        int xPos = position.x();
        int zPos = position.z();

        // Calculer la rangée actuelle (ligne)
        int row = zPos / (width + borderWidth);

        // Décalage : les rangées impaires sont décalées de l'offset spécifié
        int xEffective = (row % 2 == 0) ? xPos : xPos + offset;

        // Calcul de la position relative dans le carré
        int relativeX = Math.abs(xEffective % (length + borderWidth));
        int relativeZ = Math.abs(zPos % (width + borderWidth));

        // Vérifier si nous sommes sur la bordure, en excluant le côté droit et bas pour éviter la superposition
        if ((relativeX < borderWidth && relativeX < length - 1) || (relativeZ < borderWidth && relativeZ < width - 1)) {
            // Si la position est sur la bordure, appliquer le matériau de bordure
            return material.getFirst().toBaseBlock();
        } else {

            if (material.size() < 2) {
                return material.getFirst().toBaseBlock();
            } else {
                return material.get(1).toBaseBlock();
            }
        }
    }
}