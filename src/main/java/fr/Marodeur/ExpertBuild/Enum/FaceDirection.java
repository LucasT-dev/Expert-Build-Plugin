package fr.Marodeur.ExpertBuild.Enum;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Stairs;

public enum FaceDirection {

    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West");

    private final String face;

    FaceDirection(String face) {
        this.face = face;
    }

    public final String getFace() {
        return face;
    }

    public static BlockFace getOppositeFaceDirection(BlockFace blockFace) {

        if (blockFace == BlockFace.NORTH) {
            return BlockFace.SOUTH;
        }
        if (blockFace == BlockFace.SOUTH) {
            return BlockFace.NORTH;
        }
        if (blockFace == BlockFace.EAST) {
            return BlockFace.WEST;
        }
        if (blockFace == BlockFace.WEST) {
            return BlockFace.EAST;
        }
        return blockFace;
    }

    public static Stairs.Shape getOppositeFaceAngle(Stairs.Shape shape) {

        if (shape == Stairs.Shape.INNER_LEFT) {
            return Stairs.Shape.INNER_RIGHT;
        }
        if (shape == Stairs.Shape.INNER_RIGHT) {
            return Stairs.Shape.INNER_LEFT;
        }
        if (shape == Stairs.Shape.OUTER_LEFT) {
            return Stairs.Shape.OUTER_RIGHT;
        }
        if (shape == Stairs.Shape.OUTER_RIGHT) {
            return Stairs.Shape.OUTER_LEFT;
        }
        return shape;
    }
}
