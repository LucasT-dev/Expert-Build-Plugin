package fr.marodeur.expertbuild.object;

import com.sk89q.worldedit.math.BlockVector3;

import fr.marodeur.expertbuild.api.GlueList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class BlockVectorTool implements Cloneable {

    // int | 4 byte | -2 147 483 648 to 2 147 483 648
    // double | 8 byte | 15 significant decimal digit

    public static final BlockVectorTool ZERO;
    public static final BlockVectorTool ONE;

    public static final double PI;
    public static final double E;

    static {

        ZERO = new BlockVectorTool(0, 0, 0);
        ONE = new BlockVectorTool(1, 1, 1);

        PI = Math.PI;
        E = Math.E;

    }

    private double x;
    private double y;
    private double z;

    public BlockVectorTool(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BlockVectorTool() {
        this(0, 0, 0);
    }

    // Using for chunk location, and 2d position
    public BlockVectorTool(double x, double z) {
        this(x, 0, z);
    }


    // Set double value

    public BlockVectorTool setX(double x) {
        this.x = x;
        return this;
    }

    public BlockVectorTool setY(double y) {
        this.y = y;
        return this;
    }

    public BlockVectorTool setZ(double z) {
        this.z = z;
        return this;
    }


    // Set int value

    public BlockVectorTool setX(int x) {
        this.x = x;
        return this;
    }

    public BlockVectorTool setY(int y) {
        this.y = y;
        return this;
    }

    public BlockVectorTool setZ(int z) {
        this.z = z;
        return this;
    }


    // Get double value

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }


    // Get int value

    public int getBlockX() {
        return (int) x;
    }

    public int getBlockY() {
        return (int) y;
    }

    public int getBlockZ() {
        return (int) z;
    }


    // Copy

    public BlockVectorTool copy(BlockVectorTool bvt) {
        this.x = bvt.getX();
        this.y = bvt.getY();
        this.z = bvt.getZ();

        return this;
    }


    // Mathematics operation

    public BlockVectorTool add(BlockVectorTool blockVectorTool) {
        return this.add(blockVectorTool.x, blockVectorTool.y, blockVectorTool.z);
    }

    public BlockVectorTool add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public BlockVectorTool subtract(BlockVectorTool blockVectorTool) {
        return this.subtract(blockVectorTool.x, blockVectorTool.y, blockVectorTool.z);
    }

    public BlockVectorTool subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    public BlockVectorTool multiply(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    public BlockVectorTool zero() {
        this.x = 0;
        this.y = 0;
        this.z = 0;

        return this;
    }

    public BlockVectorTool around() {
        this.x = (int) x;
        this.y = (int) y;
        this.z = (int) z;

        return this;
    }

    // length

    public double length() {
        return Math.sqrt(NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z));
    }

    // length squared

    public double lengthSquared() {
        return NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z);
    }

    // distance

    public double distance(BlockVectorTool blockVectorTool) {
        return Math.sqrt(distanceSquared(blockVectorTool));
    }

    // distance squared

    public double distanceSquared(BlockVectorTool blockVectorTool) {
        return NumberConversions.square(x - blockVectorTool.x) + NumberConversions.square(y - blockVectorTool.y) + NumberConversions.square(z - blockVectorTool.z);
    }

    // distance squared XZ
    public double distanceXZ(BlockVectorTool other) {
        int dx = this.getBlockX() - other.getBlockX();
        int dz = this.getBlockZ() - other.getBlockZ();
        return Math.sqrt(dx * dx + dz * dz);
    }


    // rotate around X axis


    //
    // Rotation autour d'une position sur l'axe X
    public BlockVectorTool rotateAroundPositionX(BlockVectorTool point, BlockVectorTool center, double angle) {
        double radians = Math.toRadians(angle);
        double y = (point.getY() - center.getY()) * Math.cos(radians) - (point.getZ() - center.getZ()) * Math.sin(radians) + center.getY();
        double z = (point.getY() - center.getY()) * Math.sin(radians) + (point.getZ() - center.getZ()) * Math.cos(radians) + center.getZ();
        return new BlockVectorTool(point.getX(), y, z);
    }

    // Rotation autour d'une position sur l'axe Y
    public BlockVectorTool rotateAroundPositionY(BlockVectorTool point, BlockVectorTool center, double angle) {
        double radians = Math.toRadians(angle);
        double x = (point.getX() - center.getX()) * Math.cos(radians) + (point.getZ() - center.getZ()) * Math.sin(radians) + center.getX();
        double z = -(point.getX() - center.getX()) * Math.sin(radians) + (point.getZ() - center.getZ()) * Math.cos(radians) + center.getZ();
        return new BlockVectorTool(x, point.getY(), z);
    }

    // Rotation autour d'une position sur l'axe Z
    public BlockVectorTool rotateAroundPositionZ(BlockVectorTool point, BlockVectorTool center, double angle) {
        double radians = Math.toRadians(angle);
        double x = (point.getX() - center.getX()) * Math.cos(radians) - (point.getY() - center.getY()) * Math.sin(radians) + center.getX();
        double y = (point.getX() - center.getX()) * Math.sin(radians) + (point.getY() - center.getY()) * Math.cos(radians) + center.getY();
        return new BlockVectorTool(x, y, point.getZ());
    }


    // edition

    public GlueList<BlockVectorTool> getBlockBetweenTwoPoint(BlockVectorTool secondPoint) {
        return getBlockBetweenTwoPoint(this, secondPoint);
    }

    // Algorithme de Bresenham 3D
    public static GlueList<BlockVectorTool> getBlockBetweenTwoPoint(BlockVectorTool start, BlockVectorTool end) {

        GlueList<BlockVectorTool> points = new GlueList<>();

        int x1 = start.getBlockX(), y1 = start.getBlockY(), z1 = start.getBlockZ();
        int x2 = end.getBlockX(), y2 = end.getBlockY(), z2 = end.getBlockZ();

        int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1), dz = Math.abs(z2 - z1);
        int xs = Integer.compare(x2, x1), ys = Integer.compare(y2, y1), zs = Integer.compare(z2, z1);

        int p1, p2;
        int x = x1, y = y1, z = z1;

        // Déterminer l'axe dominant et ajuster les étapes
        if (dx >= dy && dx >= dz) {
            p1 = 2 * dy - dx;
            p2 = 2 * dz - dx;
            for (int i = 0; i <= dx; i++) {
                points.add(new BlockVectorTool(x, y, z));
                x += xs;
                if (p1 >= 0) {
                    y += ys;
                    p1 -= 2 * dx;
                }
                if (p2 >= 0) {
                    z += zs;
                    p2 -= 2 * dx;
                }
                p1 += 2 * dy;
                p2 += 2 * dz;
            }
        } else if (dy >= dx && dy >= dz) {
            p1 = 2 * dx - dy;
            p2 = 2 * dz - dy;
            for (int i = 0; i <= dy; i++) {
                points.add(new BlockVectorTool(x, y, z));
                y += ys;
                if (p1 >= 0) {
                    x += xs;
                    p1 -= 2 * dy;
                }
                if (p2 >= 0) {
                    z += zs;
                    p2 -= 2 * dy;
                }
                p1 += 2 * dx;
                p2 += 2 * dz;
            }
        } else {
            p1 = 2 * dy - dz;
            p2 = 2 * dx - dz;
            for (int i = 0; i <= dz; i++) {
                points.add(new BlockVectorTool(x, y, z));
                z += zs;
                if (p1 >= 0) {
                    y += ys;
                    p1 -= 2 * dz;
                }
                if (p2 >= 0) {
                    x += xs;
                    p2 -= 2 * dz;
                }
                p1 += 2 * dy;
                p2 += 2 * dx;
            }
        }

        return points;
    }

    public BlockVectorTool getBlockVectorAngle(float pitch, float yaw, double distance) {

        double pitch1 = ((pitch + 90) * PI) / 180;
        double yaw1 = ((yaw + 90) * PI) / 180;

        double x = Math.sin(pitch1) * Math.cos(yaw1);
        double y = Math.sin(pitch1) * Math.sin(yaw1);
        double z = Math.cos(pitch1);

        Vector vector = new Vector(x, z, y).multiply(distance);
        BlockVectorTool bvt = new BlockVectorTool(vector.getX(), vector.getY(), vector.getZ());

        bvt.add(this.x, this.y, this.z);

        return bvt;
    }


    // equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockVectorTool that = (BlockVectorTool) o;
        return Double.compare(x, that.x) == 0 && Double.compare(y, that.y) == 0 && Double.compare(z, that.z) == 0;
    }

    // hash

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    // toString

    @Override
    public String toString() {
        return "BlockVectorTool{x=" + x + ", y=" + y + ", z=" + z + '}';
    }

    // clone

    public BlockVectorTool clone() {
        try {
            return (BlockVectorTool) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    // serialize

    public Map<Character, Double> serialize() {

        Map<Character, Double> data = new HashMap<>();

        data.put('x', this.x);
        data.put('y', this.y);
        data.put('z', this.z);

        return data;
    }

    // deserialize

    public BlockVectorTool deserialize(Map<Character, Double> map) {
        return new BlockVectorTool(map.get('x'), map.get('y'), map.get('z'));
    }

    // Order

    public BlockVectorTool getMinimum(BlockVectorTool bvt) {
        return new BlockVectorTool(
                Math.min(this.x, bvt.getX()),
                Math.min(this.x, bvt.getX()),
                Math.min(this.x, bvt.getX())
        );
    }

    public BlockVectorTool getMaximum(BlockVectorTool bvt) {
        return new BlockVectorTool(
                Math.max(this.x, bvt.getX()),
                Math.max(this.x, bvt.getX()),
                Math.max(this.x, bvt.getX())
        );
    }

    // Trier une liste de BlockVectorTool

    public Deque<BlockVectorTool> XZcreasing(GlueList<BlockVectorTool> blockVectorTools) {

        // Trier la Deque en utilisant l'API Stream
        return blockVectorTools.stream()
                .sorted(Comparator.comparing(BlockVectorTool::getBlockX).reversed()
                        .thenComparing(BlockVectorTool::getBlockZ).reversed())
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public Deque<BlockVectorTool> XZDiagonal(GlueList<BlockVectorTool> blockVectorTools) {

        // Trier la Deque en utilisant l'API Stream
        return blockVectorTools.stream()
                .sorted(Comparator.comparingInt(block -> block.getBlockX() + block.getBlockZ()))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public Deque<BlockVectorTool> XZRandomDiagonal(GlueList<BlockVectorTool> blockVectorTools) {

        Random random = new Random();
        int corner = random.nextInt(4);

        System.out.println("corner = " + corner);

        // Ajuster le tri en fonction du coin sélectionné
        Comparator<BlockVectorTool> comparator;
        switch (corner) {
            case 0:
                // Diagonale depuis le coin bas-gauche (x + z)
                comparator = Comparator.comparingInt(block -> block.getBlockX() + block.getBlockZ());
                break;
            case 1:
                // Diagonale depuis le coin bas-droit (x - z)
                comparator = Comparator.comparingInt(block -> block.getBlockX() - block.getBlockZ());
                break;
            case 2:
                // Diagonale depuis le coin haut-gauche (-x + z)
                comparator = Comparator.comparingInt(block -> -block.getBlockX() + block.getBlockZ());
                break;
            case 3:
                // Diagonale depuis le coin haut-droit (-x - z)
                comparator = Comparator.comparingInt(block -> -block.getBlockX() - block.getBlockZ());
                break;
            default:
                comparator = Comparator.comparingInt(block -> block.getBlockX() + block.getBlockZ());
                break;
        }

        // Trier la GlueList en utilisant l'API Stream
        return blockVectorTools.stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public Deque<BlockVectorTool> XZCylinder(GlueList<BlockVectorTool> blockVectorTools) {

        // Définir un point central (par exemple, la moyenne des positions)
        double centerX = blockVectorTools.stream().mapToInt(BlockVectorTool::getBlockX).average().orElse(0);
        double centerZ = blockVectorTools.stream().mapToInt(BlockVectorTool::getBlockZ).average().orElse(0);

        // Trier la Deque en utilisant l'API Stream
        return blockVectorTools.stream()
                .sorted(Comparator.comparingDouble(bv -> {
                    double deltaX = bv.getX() - centerX;
                    double deltaZ = bv.getZ() - centerZ;
                    double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
                    return distance;
                }))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public Deque<BlockVectorTool> XZSpiral(GlueList<BlockVectorTool> blockVectorTools) {

        // Trier la Deque en utilisant l'API Stream
        double centerX = blockVectorTools.stream().mapToInt(BlockVectorTool::getBlockX).average().orElse(0);
        double centerZ = blockVectorTools.stream().mapToInt(BlockVectorTool::getBlockZ).average().orElse(0);

        // Trier la Deque en utilisant l'API Stream

        return blockVectorTools.stream()
                .sorted(Comparator.comparingDouble(bv -> {
                    double deltaX = bv.getBlockX() - centerX;
                    double deltaZ = bv.getBlockZ() - centerZ;
                    double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
                    double angle = Math.atan2(deltaZ, deltaX);
                    return distance + angle; // Trier par distance et angle pour effet spirale
                }))

                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public Deque<BlockVectorTool> XZRandom(GlueList<BlockVectorTool> blockVectorTools) {

        // Trier la Deque en utilisant l'API Stream
        Collections.shuffle(blockVectorTools);

        return new ArrayDeque<>(blockVectorTools);
    }

    public <T> Deque<T> reverseDeque(Deque<T> originalDeque) {
        Deque<T> reversedDeque = new ArrayDeque<>();

        // Utiliser un itérateur pour parcourir la deque originale
        Iterator<T> iterator = originalDeque.descendingIterator();
        while (iterator.hasNext()) {
            reversedDeque.add(iterator.next());
        }

        return reversedDeque;
    }


    // conversion

    public Location toLocation(World world) {
        return new Location(world, this.x, this.y, this.z);
    }

    public BlockVector3 toBlockVector3() {
        return BlockVector3.at(this.x, this.y, this.z);
    }

    public Vector toVector() {
        return new Vector(this.x, this.y, this.z);
    }

    public Block getBlock(World world) {
        return this.toLocation(world).getBlock();
    }

    public Material getMaterial(World world) {
        return this.toLocation(world).getBlock().getType();
    }

    public BlockVectorTool toBlockVectorTool(BlockVector3 blockVector3) {

        return new BlockVectorTool(blockVector3.x(), blockVector3.y(), blockVector3.z());
    }

    public BlockVectorTool toBlockVectorTool(Location location) {
        return new BlockVectorTool(location.getX(), location.getY(), location.getZ());
    }

    public BlockVectorTool toBlockVectorTool(Vector vector) {
        return new BlockVectorTool(vector.getX(), vector.getY(), vector.getZ());
    }
}


