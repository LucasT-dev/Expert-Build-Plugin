package fr.marodeur.expertbuild.object;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.block.BaseBlock;
import org.bukkit.Material;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

public class BlockVectorMaterial {

    // Deux Deque pour les positions et les matériaux
    private final Deque<BlockVectorTool> positionDeque = new ArrayDeque<>();
    private final Deque<BaseBlock> baseBlockDeque = new ArrayDeque<>();

    // Ajouter une position et un matériau
    public void addPositionMaterial(BlockVectorTool position, Material material) {
        positionDeque.add(position);   // Ajouter à la queue
        baseBlockDeque.add(BukkitAdapter.asBlockType(material).getDefaultState().toBaseBlock());   // Ajouter à la queue
    }

    // Ajouter une position et un matériau
    public void addPositionMaterial(BlockVectorTool position, BaseBlock material) {
        positionDeque.add(position);   // Ajouter à la queue
        baseBlockDeque.add(material);   // Ajouter à la queue
    }

    // Supprimer la dernière position et le matériau
    public void removeLastPositionMaterial() {
        if (!positionDeque.isEmpty() && !baseBlockDeque.isEmpty()) {
            positionDeque.removeLast();  // Supprimer de la queue
            baseBlockDeque.removeLast();      // Supprimer de la queue
        }
    }

    // Méthode pour retourner la dernière paire et la supprimer des deux Deque
    public PositionMaterialPair getLastPositionMaterial() {
        if (!positionDeque.isEmpty() && !baseBlockDeque.isEmpty()) {
            BlockVectorTool lastPosition = positionDeque.removeLast();  // Supprimer la dernière position
            BaseBlock lastMaterial = baseBlockDeque.removeLast();     // Supprimer le dernier matériau
            return new PositionMaterialPair(lastPosition, lastMaterial);  // Retourner la paire
        } else {
            return null;  // Si les deux Deque sont vides, retourner null
        }
    }

    // Utilisation d'un Stream pour itérer sur les paires synchronisées
    public Stream<PositionMaterialPair> streamPairs() {
        return Stream.iterate(0, i -> i + 1)
                .limit(Math.min(positionDeque.size(), baseBlockDeque.size()))
                .map(i -> new PositionMaterialPair(
                        positionDeque.toArray(new BlockVectorTool[0])[i],
                        baseBlockDeque.toArray(new BaseBlock[0])[i]
                ));
    }


    // Getter
    public Deque<BlockVectorTool> positionDeque() {
        return positionDeque;
    }

    public Deque<BaseBlock> baseBlockDeque() {
        return baseBlockDeque;
    }

    public void clear() {
        positionDeque.clear();
        baseBlockDeque.clear();
    }


    public static class PositionMaterialPair {

        private BlockVectorTool position;
        private BaseBlock material;

        public PositionMaterialPair(BlockVectorTool lastPosition, BaseBlock lastMaterial) {
            this.position = lastPosition;
            this.material = lastMaterial;
        }

        public BlockVectorTool position() {
            return position;
        }

        public BaseBlock material() {
            return material;
        }

        @Override
        public String toString() {
            return "BlockVectorTool: " + position.toString() + ", BaseBlock: " + material;
        }
    }
}

