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

    public BlockVectorTool add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
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


    // rotate

    public BlockVectorTool rotateAroundX(BlockVectorTool bvt, double degreeAngle) {
        return rotateAroundZ(bvt.getBlockY(), bvt.getBlockZ(), degreeAngle, bvt.getBlockX());
    }

    public BlockVectorTool rotateAroundX(int Ypoint, int Zpoint, double degreeAngle, int x) {

        // this = center

        double angle = Math.toRadians(degreeAngle);

        double newY = this.y + (Ypoint - this.y) * Math.cos(angle) - (Zpoint - this.z) * Math.sin(angle);
        double newZ = this.z + (Ypoint - this.y) * Math.sin(angle) + (Zpoint - this.z) * Math.cos(angle);

        return new BlockVectorTool(x, (int) newY, (int) newZ);
    }

    public BlockVectorTool rotateAroundY(BlockVectorTool bvt, double degreeAngle) {
        return rotateAroundZ(bvt.getBlockX(), bvt.getBlockZ(), degreeAngle, bvt.getBlockY());
    }

    public BlockVectorTool rotateAroundY(int Xpoint, int Zpoint, double degreeAngle, int y) {

        // this = center

        double angle = Math.toRadians(degreeAngle);

        double newX = this.x + (Xpoint - this.x) * Math.cos(angle) - (Zpoint - this.z) * Math.sin(angle);
        double newZ = this.z + (Xpoint - this.x) * Math.sin(angle) + (Zpoint - this.z) * Math.cos(angle);

        return new BlockVectorTool((int) newX, y, (int) newZ);
    }

    public BlockVectorTool rotateAroundZ(BlockVectorTool bvt, double degreeAngle) {
        return rotateAroundZ(bvt.getBlockX(), bvt.getBlockY(), degreeAngle, bvt.getBlockZ());
    }

    public BlockVectorTool rotateAroundZ(int Xpoint, int Ypoint, double degreeAngle, int z) {

        // this = center

        double angle = Math.toRadians(degreeAngle);

        double newX = this.x + (Xpoint - this.x) * Math.cos(angle) - (Ypoint - this.y) * Math.sin(angle);
        double newY = this.y + (Xpoint - this.x) * Math.sin(angle) + (Ypoint - this.y) * Math.cos(angle);

        return new BlockVectorTool((int) newX, (int) newY, z);
    }


    // edition

    public GlueList<BlockVectorTool> getBlockVectorBetweenTwoPoint(BlockVectorTool secondPoint, int space) {
        return this.getBlockVectorBetweenTwoPoint(this, secondPoint, space);
    }
    public GlueList<BlockVectorTool> getBlockVectorBetweenTwoPoint(BlockVectorTool firstPoint, BlockVectorTool secondPoint, int space) {

        GlueList<BlockVectorTool> bvtArray = new GlueList<>();

        double distance = this.distance(secondPoint);
        Vector p1 = firstPoint.toVector();
        Vector p2 = secondPoint.toVector();
        Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
        double length = 0;

        for (; length < distance; p1.add(vector)) {

            bvtArray.add(new BlockVectorTool(p1.getBlockX(), p1.getBlockY(), p1.getBlockZ()));
            length += space;

        }
        return bvtArray;
    }

    public BlockVectorTool getBlockVectorAngle(float pitch, float yaw, double distance, World world) {

        double pitch1 = ((pitch + 90) * PI) / 180;
        double yaw1 = ((yaw + 90) * PI) / 180;

        double x = Math.sin(pitch1) * Math.cos(yaw1);
        double y = Math.sin(pitch1) * Math.sin(yaw1);
        double z = Math.cos(pitch1);

        Vector vector = new Vector(x, z, y).multiply(distance);

        return new BlockVectorTool(vector.getX(), vector.getY(), vector.getZ()).add(this.x, this.y, this.z);
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
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

    // Shape

    public ShapeTool getSpherePoint(int radius) {

        ShapeTool shapeTool = new ShapeTool((2 * radius) * (2 * radius));

        for (int x = this.getBlockX() - radius; x <= this.getBlockX() + radius; x++) {
            for (int y = this.getBlockY() - radius; y <= this.getBlockY() + radius; y++) {
                for (int z = this.getBlockZ() - radius; z <= this.getBlockZ() + radius; z++) {

                    if (new BlockVectorTool(x, y, z).distance(this) < radius) {

                        shapeTool.add(new BlockVectorTool(x, y, z));

                    }
                }
            }
        }
        return shapeTool;
    }

    public ShapeTool getCubePoint(int radius) {

        ShapeTool shapeTool = new ShapeTool((2 * radius) * (2 * radius));

        for (int x = this.getBlockX() - radius; x <= this.getBlockX() + radius; x++) {
            for (int y = this.getBlockY() - radius; y <= this.getBlockY() + radius; y++) {
                for (int z = this.getBlockZ() - radius; z <= this.getBlockZ() + radius; z++) {

                    shapeTool.add(new BlockVectorTool(x, y, z));

                }
            }
        }
        return shapeTool;
    }
}

// Shape
class ShapeTool {

    private final GlueList<BlockVectorTool> toolGlueList;


    public ShapeTool(int capacity) {
        toolGlueList = new GlueList<>(capacity);
    }

    public ShapeTool() {
        toolGlueList = new GlueList<>();
    }


    public void add(BlockVectorTool blockVectorTool) {
        toolGlueList.add(blockVectorTool);
    }

    public int size() {
        return toolGlueList.size();
    }


    public BlockVectorTool getRandomPoint() {

        if (this.toolGlueList.isEmpty()) {
            return BlockVectorTool.ZERO;
        }

        return this.toolGlueList.get(new Random().nextInt(this.toolGlueList.size()));
    }
}


