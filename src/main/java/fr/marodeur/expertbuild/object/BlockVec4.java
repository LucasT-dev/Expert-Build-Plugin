package fr.marodeur.expertbuild.object;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;

import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.api.GlueList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

public class BlockVec4 {

    private final Logger logger = Logger.getLogger("Expert-Build");

    private int x;
    private int y;
    private int z;

    private Material mat;
    private BaseBlock baseblock;
    private Location loc;
    private Pattern pattern;

    public BlockVec4() {}

    /**
     * Set the coordinate x, y, z
     *
     * @param x the new X coordinate of this {@code BlockVec4}
     * @param y the new Y coordinate of this {@code BlockVec4}
     * @param z the new Z coordinate of this {@code BlockVec4}
     */
    public BlockVec4(int x, int y, int z) {
        this(x,y,z, null, null, null, null);
    }

    public BlockVec4(int x, int y, int z, Material mat) {
        this(x,y,z, null, mat, null, null);
    }

    public BlockVec4(int x, int y, int z, Pattern pattern) {
        this(x, y ,z, null, null, null, pattern);
    }

    public BlockVec4(@NotNull Location loc) {
        this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc, null, null, null);
    }

    public BlockVec4(@NotNull Location loc, Material mat) {
        this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc, mat, null, null);
    }

    public BlockVec4(@NotNull Location loc, Material mat, Player p) {
        this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc, mat, null, new UtilsFAWE(p).getPattern(mat.toString()));
    }

    public BlockVec4(@NotNull Location loc, Pattern pattern) {
        this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc, null, null, pattern);
    }

    public BlockVec4(@NotNull Location loc, @NotNull BlockState blockState) {
        this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc, BukkitAdapter.adapt(blockState.getBlockType()), blockState.toBaseBlock(), null);
    }

    //Principal
    public BlockVec4(int x, int y, int z, Location loc, Material mat, BaseBlock blockState, Pattern pattern) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.loc = loc;
        this.baseblock = blockState;
        this.mat = mat;
        this.pattern = pattern;
    }

    public BlockVec4(int x, int y, int z, @NotNull BaseBlock blockState) {
        this(x, y, z, null, BukkitAdapter.adapt(blockState.getBlockType()), blockState.toBaseBlock(), null);
    }

    public BlockVec4(@NotNull BlockVector3 bv3) {
        this(bv3.getX(), bv3.getY(), bv3.getZ(), null, null, null, null);
    }

    public BlockVec4(@NotNull BlockVector3 bv3, BaseBlock baseBlock) {
        this(bv3.getX(), bv3.getY(), bv3.getZ(), null, BukkitAdapter.adapt(baseBlock.getBlockType()), baseBlock.toBaseBlock(), null);
    }

    public BlockVec4(Material mat) {
        this.mat = mat;
    }

    public BlockVec4(@NotNull Vector v, Material mat, World w) {
        this.loc = v.toLocation(w);
        this.x = loc.getBlockX();
        this.y = loc.getBlockY();
        this.z = loc.getBlockZ();
        this.mat = mat;
    }

    public BlockVec4(@NotNull BlockVector3 bv3, Material mat) {
        this(bv3.getX(), bv3.getY(), bv3.getZ(), null, mat, null, null);
    }

    public BlockVec4(@NotNull Location loc, BaseBlock baseBlock) {
        this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc, BukkitAdapter.adapt(baseBlock.getBlockType()), baseBlock, null);
    }


    public int getX() {
        return x;
    }

    public BlockVec4 setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public BlockVec4 setY(int y) {
        this.y = y;
        return this;
    }

    public int getZ() {
        return z;
    }

    public BlockVec4 setZ(int z) {
        this.z = z;
        return this;
    }

    public Material getMat() {
        if (mat == null) {
            logger.warning("BlockVec4 : mat is null");
            return null;
        }
        return mat;
    }

    public void setMat(Material mat) {
        this.mat = mat;
    }

    public Location getLoc() {
        if (loc == null) {
            logger.warning("BlockVec4 : loc is null");
            return null;
        }
        return loc;
    }

    public void setLoc(@NotNull Location loc) {
        this.loc = loc;
        this.x = loc.getBlockX();
        this.y = loc.getBlockY();
        this.z = loc.getBlockZ();
    }

    public BaseBlock getBaseblock() {
        if (baseblock == null) {
            logger.warning("BlockVec4 : baseblock is null");
            return null;
        }
        return this.baseblock;
    }

    public Pattern getPattern() {
        if (pattern == null) {
            logger.warning("BlockVec4 : pattern is null");
            return null;
        }
        return this.pattern;
    }

    public BlockVector3 toBlockVector3() {
        return BlockVector3.at(this.x, this.y, this.z);
    }

    public Vector toVector() {
        return new Vector(this.x, this.y, this.z);
    }

    public<T> BlockVec4 toBlockVector4(T value) {

        if (value instanceof Location) {
            return new BlockVec4(((Location) value).getBlockX(), ((Location) value).getBlockY(), ((Location) value).getBlockZ());
        }

        if (value instanceof BlockVector3) {
            return new BlockVec4(((BlockVector3) value).getBlockX(), ((BlockVector3) value).getBlockY(), ((BlockVector3) value).getBlockZ());
        }
        else return null;
    }

    public Location toLocation(World world) {

        if (this.loc != null) {
            return new Location(world, this.loc.getX(), this.loc.getY(), this.loc.getZ());
        }
        else {
            return new Location(world, this.getX(), this.getY(), this.getZ());
        }
    }

    public Block getBlock(World world, int x, int y, int z) {
        return new Location(world, x, y, z).getBlock();
    }

    public Block getBlock(World world) {
        return getBlock(world, this.x, this.y, this.z);
    }

    public Block getBlock() {
        if (loc == null) {
            logger.warning("BlockVec4 : loc is null");
            return null;
        }
        return getBlock(this.loc.getWorld(), this.x, this.y, this.z);
    }

    public Material getMaterial(World world, int x, int y, int z) {
        return new Location(world, x, y, z).getBlock().getType();
    }

    public Location getPointAngle(float pitch, float yaw, double distance, World world) {

        double pitch1 = ((pitch + 90) * Math.PI) / 180;
        double yaw1 = ((yaw + 90) * Math.PI) / 180;

        double x = Math.sin(pitch1) * Math.cos(yaw1);
        double y = Math.sin(pitch1) * Math.sin(yaw1);
        double z = Math.cos(pitch1);

        Vector vector = new Vector(x, z, y);

        vector.multiply(distance);

        this.loc = vector.toLocation(world).add(this.x, this.y, this.z);

        return vector.toLocation(world).add(this.x, this.y, this.z);
    }

    public ArrayList<BlockVec4> getPointInto2Point(@NotNull Location loc1, Location loc2, int space, Object... mat) {

        ArrayList<BlockVec4> bv4 = new ArrayList<>();

        double distance = loc1.distance(loc2);
        Vector p1 = loc1.toVector();
        Vector p2 = loc2.toVector();
        Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
        double length = 0;

        for (; length < distance; p1.add(vector)) {

            if (mat.length == 0) {
                bv4.add(new BlockVec4(x, y, z, (Material) null));
            }
            if (mat[0] instanceof Material material) {
                bv4.add(new BlockVec4((int) p1.getX(), (int) p1.getY(), (int) p1.getZ(), material));
            }
            if (mat[0] instanceof Pattern pattern) {
                bv4.add(new BlockVec4((int) p1.getX(), (int) p1.getY(), (int) p1.getZ(), pattern));
            }
            length += space;
        }
        return bv4;
    }

    public GlueList<BlockVec4> getPointInSphere(@NotNull Location center, int radius, Object... mat) {

        GlueList<BlockVec4> bv4 = new GlueList<>();
        for (int x = center.getBlockX() - radius; x <= center.getBlockX() + radius; x++) {
            for (int y = center.getBlockY() - radius; y <= center.getBlockY() + radius; y++) {
                for (int z = center.getBlockZ() - radius; z <= center.getBlockZ() + radius; z++) {
                    if (new BlockVec4().toBlockVector4(center).distance(new BlockVec4(x,y,z)) < radius) {

                        if (mat.length == 0) {
                            bv4.add(new BlockVec4(x, y, z, (Material) null));
                        }
                        if (mat[0] instanceof Material material) {
                            bv4.add(new BlockVec4(x, y, z, material));
                        }
                        if (mat[0] instanceof Pattern pattern) {
                            bv4.add(new BlockVec4(x, y, z, pattern));
                        }
                    }
                }
            }
        }
        return bv4;
    }

    public GlueList<BlockVec4> getPointInCylinder(@NotNull Location center, int radius, Object... mat) {

        GlueList<BlockVec4> bv4 = new GlueList<>();
        for (int x = center.getBlockX() - radius; x <= center.getBlockX() + radius; x++) {
            for (int z = center.getBlockZ() - radius; z <= center.getBlockZ() + radius; z++) {

                if (mat.length == 0) {
                    bv4.add(new BlockVec4(x, center.getBlockY(), z, (Material) null));
                }
                if (mat[0] instanceof Material material) {
                    bv4.add(new BlockVec4(x, center.getBlockY(), z, material));
                }
                if (mat[0] instanceof Pattern pattern) {
                    bv4.add(new BlockVec4(x, center.getBlockY(), z, pattern));
                }
            }
        }
        return bv4;
    }

    public GlueList<BlockVec4> getPointInCube(@NotNull Location center, int radius, Object... mat) {

        GlueList<BlockVec4> bv4 = new GlueList<>();
        for (int x = center.getBlockX() - radius; x <= center.getBlockX() + radius; x++) {
            for (int y = center.getBlockY() - radius; y <= center.getBlockY() + radius; y++) {
                for (int z = center.getBlockZ() - radius; z <= center.getBlockZ() + radius; z++) {

                    if (mat.length == 0) {
                        bv4.add(new BlockVec4(x, y, z, (Material) null));
                    }
                    if (mat[0] instanceof Material material) {
                        bv4.add(new BlockVec4(x, y, z, material));
                    }
                    if (mat[0] instanceof Pattern pattern) {
                        bv4.add(new BlockVec4(x, y, z, pattern));
                    }
                }
            }
        }
        return bv4;
    }

    public GlueList<BlockVec4> getPointInRectangle(Location point, int length, int width, int heigth, Object... mat) {

        GlueList<BlockVec4> bv4 = new GlueList<>();

        for (int x = point.getBlockX(); x <= point.getBlockX() + length; x++) {
            for (int z = point.getBlockZ(); z <= point.getBlockZ() + width; z++) {
                for (int y = point.getBlockY(); y <= point.getBlockY() + heigth; y++) {

                    if (mat.length == 0) {
                        bv4.add(new BlockVec4(x, y, z, (Material) null));
                    }
                    if (mat[0] instanceof Material material) {
                        bv4.add(new BlockVec4(x, y, z, material));
                    }
                    if (mat[0] instanceof Pattern pattern) {
                        bv4.add(new BlockVec4(x, y, z, pattern));
                    }
                }
            }
        }
        return bv4;
    }

    public GlueList<BlockVec4> getPointRectangleWall(Location point, int length, int width, int heigth, Object... mat) {

        GlueList<BlockVec4> bv4 = new GlueList<>();

        for (int x = point.getBlockX(); x <= point.getBlockX() + length; x++) {
            for (int z = point.getBlockZ(); z <= point.getBlockZ() + width; z++) {
                for (int y = point.getBlockY(); y <= point.getBlockY() + heigth; y++) {

                    if (x == point.getBlockX() | x == point.getBlockX() + length | z == point.getBlockZ() | z == point.getBlockZ() + width) {

                        if (mat.length == 0) {
                            bv4.add(new BlockVec4(x, y, z, (Material) null));
                        }
                        if (mat[0] instanceof Material material) {
                            bv4.add(new BlockVec4(x, y, z, material));
                        }
                        if (mat[0] instanceof Pattern pattern) {
                            bv4.add(new BlockVec4(x, y, z, pattern));
                        }
                    }
                }
            }
        }
        return bv4;
    }

    public boolean pointIsInsidePolygon(BlockVec4[] polygon, int n, BlockVec4 p) {

        {
            int INF = 10000;
            if (n < 3)
                return false;

            BlockVec4 extreme = new BlockVec4(INF, 0, p.getZ());

            int count = 0, i = 0;

            do {
                int next = (i + 1) % n;
                if (doIntersect(polygon[i], polygon[next], p, extreme)) {
                    if (orientation(polygon[i], p, polygon[next]) == 0)
                        return onSegment(polygon[i], p, polygon[next]);

                    count++;
                }
                i = next;
            } while (i != 0);

            return (count & 1) == 1 ? true : false;
        }
    }

    public boolean onSegment(BlockVec4 p, BlockVec4 q, BlockVec4 r)
    {
        if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
                && q.getZ() <= Math.max(p.getZ(), r.getZ()) && q.getZ() >= Math.min(p.getZ(), r.getZ()))
            return true;
        return false;
    }

    public int orientation(BlockVec4 p, BlockVec4 q, BlockVec4 r)
    {
        int val = (q.getZ() - p.getZ()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getZ() - q.getZ());

        if (val == 0)
            return 0;
        return (val > 0) ? 1 : 2;
    }

    public boolean doIntersect(BlockVec4 p1, BlockVec4 q1, BlockVec4 p2, BlockVec4 q2)
    {

        int o1 = new BlockVec4().orientation(p1, q1, p2);
        int o2 = new BlockVec4().orientation(p1, q1, q2);
        int o3 = new BlockVec4().orientation(p2, q2, p1);
        int o4 = new BlockVec4().orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;

        if (o1 == 0 && onSegment(p1, p2, q1))
            return true;

        if (o2 == 0 && onSegment(p1, q2, q1))
            return true;

        if (o3 == 0 && onSegment(p2, p1, q2))
            return true;

        if (o4 == 0 && onSegment(p2, q1, q2))
            return true;

        return false;
    }

    public BlockVec4 getRandomLocation(@NotNull BlockVec4 p1, @NotNull BlockVec4 p2) {

        int xMin = Math.min(p1.getX(), p2.getX());
        int xMax = Math.max(p1.getX(), p2.getX());
        int yMin = Math.min(p1.getY(), p2.getY());
        int yMax = Math.max(p1.getY(), p2.getY());
        int zMin = Math.min(p1.getZ(), p2.getZ());
        int zMax = Math.max(p1.getZ(), p2.getZ());

        final Random rand = new Random();
        final int x = rand.nextInt(Math.abs(xMax - xMin) + 1) + xMin;
        final int y = rand.nextInt(Math.abs(yMax - yMin) + 1) + yMin;
        final int z = rand.nextInt(Math.abs(zMax - zMin) + 1) + zMin;
        return new BlockVec4(x, y, z);
    }

    public BlockVec4 getRandomLocation(@NotNull BlockVec4 p1) {

        int xMin = Math.min(p1.getX(), this.x);
        int xMax = Math.max(p1.getX(), this.x);
        int yMin = Math.min(p1.getY(), this.y);
        int yMax = Math.max(p1.getY(), this.y);
        int zMin = Math.min(p1.getZ(), this.z);
        int zMax = Math.max(p1.getZ(), this.z);

        final Random rand = new Random();
        final int x = rand.nextInt(Math.abs(xMax - xMin) + 1) + xMin;
        final int y = rand.nextInt(Math.abs(yMax - yMin) + 1) + yMin;
        final int z = rand.nextInt(Math.abs(zMax - zMin) + 1) + zMin;
        return new BlockVec4(x, y, z);
    }

    public double distance(@NotNull BlockVec4 bv4) {
        return  Math.sqrt(NumberConversions.square(x - bv4.getX()) + NumberConversions.square(y - bv4.getY()) + NumberConversions.square(z - bv4.getZ()));
    }

    public BlockVec4 rotateAroundY(int Xcenter, int Zcenter, int Xpoint, int Zpoint, double degreeAngle, BaseBlock baseBlock, int y) {

        double angle = Math.toRadians(degreeAngle);

        double newX = Xcenter + (Xpoint - Xcenter) * Math.cos(angle) - (Zpoint - Zcenter) * Math.sin(angle);
        double newZ = Zcenter + (Xpoint - Xcenter) * Math.sin(angle) + (Zpoint - Zcenter) * Math.cos(angle);

        return new BlockVec4((int) newX, y, (int) newZ, baseBlock);
    }

    @Override
    public String toString() {
        return "BlockVec4{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", loc=" + loc +
                ", mat=" + mat +
                ", pattern=" + pattern +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BlockVec4 bv4)) { return false; }

        return bv4.getX() == this.x && bv4.getY() == this.y && bv4.getZ() == this.z && bv4.getMat() == this.mat;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean isEmpty(Object obj) {
        return obj == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, mat, baseblock, loc, pattern);
    }

    public boolean getMaterialEquals(@NotNull Material mat) {
        return mat.equals(this.mat);
    }

    public boolean getLocationEquals(@NotNull Location loc) {
        return loc.equals(this.loc);
    }

    public boolean getCoordinateEquals(int x, int y, int z) {
        return this.x == x && this.y == y && this.z == z;
    }

    public boolean getCoordinateEquals(@NotNull BlockVec4 bv4) {
        return this.x == bv4.x && this.y == bv4.y && this.z == bv4.z;
    }

    public BlockVec4 add(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }
}




