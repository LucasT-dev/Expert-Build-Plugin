package fr.Marodeur.ExpertBuild.Brush;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.API.GlueList;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BlendBallBrush implements BrushOperation {

    public static int seed;
    //[Range (2, 8)]
    public static int erosionRadius = 3;
    //[Range (0, 1)]
    public static float inertia = .05f; // At zero, water will instantly change direction to flow downhill. At 1, water will never change direction.
    public static float sedimentCapacityFactor = 4; // Multiplier for how much sediment a droplet can carry
    public static float minSedimentCapacity = .01f; // Used to prevent carry capacity getting too close to zero on flatter terrain
    //[Range (0, 1)]
    public static float erodeSpeed = .3f;
    //[Range (0, 1)]
    public static float depositSpeed = .3f;
    //[Range (0, 1)]
    public static float evaporateSpeed = .01f;
    public static float gravity = 4;
    public static int maxDropletLifetime = 30;

    public static float initialWaterVolume = 1;
    public static float initialSpeed = 1;

    // Indices and weights of erosion brush precomputed for every node
    public static int[][] erosionBrushIndices = new int[20][];
    public static float[][] erosionBrushWeights = new float[20][];
    public static Random prng = new Random(seed);

    public static int currentSeed;
    public static int currentErosionRadius = 5;
    public static int currentMapSize = 10;


    private static final GlueList<BlockVec4> bv4 = new GlueList<>();

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission("exp.brush.blendball");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.BLENDBALL;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return BrushOperation.super.hasEnabelingBrush(brushBuilder);
    }

    @Override
    public void ExecuteBrushOnArrow(Player p, Object obj1, Object loc) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p, true)) ||
                !BrushBuilder.getBrushBuilderPlayer(p, true).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        bv4.clear();
        Location l = (Location) obj1;
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    blend(l, false, radius);

                } finally {
                    new UtilsFAWE(p).setBlockAnyPattern(p, bv4, false);
                }
            }
        });
    }

    @Override
    public void ExecuteBrushOnGunpowder(Player p, Object obj1, Object loc) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p, true)) ||
                !BrushBuilder.getBrushBuilderPlayer(p, true).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        bv4.clear();
        Location l = (Location) obj1;
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    blend(l, true, radius);

                } finally {
                    new UtilsFAWE(p).setBlockAnyPattern(p, bv4, false);
                }
            }
        });

    }

    public void blend(Location l, boolean excludeAir, int radius) {

        boolean excludeWater = false;

        final int brushSizeDoubled = 2 * radius;
        // Array that holds the original materials plus a buffer
        final Material[][][] oldMaterials = new Material[2 * (radius + 1) + 1][2 * (radius + 1) + 1][2 * (radius + 1) + 1];
        // Array that holds the blended materials
        final Material[][][] newMaterials = new Material[brushSizeDoubled + 1][brushSizeDoubled + 1][brushSizeDoubled + 1];

        // Log current materials into oldmats
        for (int x = 0; x <= 2 * (radius + 1); x++) {
            for (int y = 0; y <= 2 * (radius + 1); y++) {
                for (int z = 0; z <= 2 * (radius + 1); z++) {
                    oldMaterials[x][y][z] = new BlockVec4().getMaterial(l.getWorld(), l.getBlockX() - radius - 1 + x, l.getBlockY() - radius - 1 + y, l.getBlockZ() - radius - 1 + z);
                }
            }
        }

        // Log current materials into newmats
        for (int x = 0; x <= brushSizeDoubled; x++) {
            for (int y = 0; y <= brushSizeDoubled; y++) {
                for (int z = 0; z <= brushSizeDoubled; z++) {
                    newMaterials[x][y][z] = oldMaterials[x + 1][y + 1][z + 1];
                }
            }
        }

        // Blend materials
        for (int x = 0; x <= brushSizeDoubled; x++) {
            for (int y = 0; y <= brushSizeDoubled; y++) {
                for (int z = 0; z <= brushSizeDoubled; z++) {
                    Map<Material, Integer> materialFrequency = new HashMap<>();

                    boolean tiecheck = true;

                    for (int m = -1; m <= 1; m++) {
                        for (int n = -1; n <= 1; n++) {
                            for (int o = -1; o <= 1; o++) {
                                if (!(m == 0 && n == 0 && o == 0)) {
                                    Material currentMaterial = oldMaterials[x + 1 + m][y + 1 + n][z + 1 + o];
                                    int currentFrequency = materialFrequency.getOrDefault(currentMaterial, 0) + 1;

                                    materialFrequency.put(currentMaterial, currentFrequency);
                                }
                            }
                        }
                    }

                    int highestMaterialCount = 0;
                    Material highestMaterial = Material.AIR;

                    // Find most common neighbouring material
                    for (Map.Entry<Material, Integer> e : materialFrequency.entrySet()) {
                        if (e.getValue() > highestMaterialCount && !(excludeAir && e.getKey() == Material.AIR) && !(excludeWater && e.getKey() == Material.WATER)) {
                            highestMaterialCount = e.getValue();
                            highestMaterial = e.getKey();
                        }
                    }

                    // Make sure that there's no tie in highest material
                    for (Map.Entry<Material, Integer> e : materialFrequency.entrySet()) {
                        if (e.getValue() == highestMaterialCount && !(excludeAir && e.getKey() == Material.AIR) && !(excludeWater && e.getKey() == Material.WATER)) {
                            if (e.getKey() == highestMaterial) {
                                continue;
                            }
                            tiecheck = false;
                        }
                    }

                    // Record most common neighbor material for this block
                    if (tiecheck) {
                        newMaterials[x][y][z] = highestMaterial;
                    }
                }
            }
        }

        final double rSquared = Math.pow(radius + 1, 2);

        // Make the changes
        for (int x = brushSizeDoubled; x >= 0; x--) {
            final double xSquared = Math.pow(x - radius - 1, 2);

            for (int y = 0; y <= brushSizeDoubled; y++) {
                final double ySquared = Math.pow(y - radius - 1, 2);

                for (int z = brushSizeDoubled; z >= 0; z--) {
                    if (xSquared + ySquared + Math.pow(z - radius - 1, 2) <= rSquared) {

                        if (!(excludeAir && newMaterials[x][y][z] == Material.AIR) && !(excludeWater && (newMaterials[x][y][z] == Material.WATER))) {

                            bv4.add(new BlockVec4(l.getBlockX() - radius + x, l.getBlockY() - radius + y, l.getBlockZ() - radius + z, newMaterials[x][y][z]));

                        }
                    }
                }
            }
        }
    }

    public static void Initialize(int mapSize, boolean resetSeed, World world) {

        seed = (int) world.getSeed();

        // Initialization creates a System.Random object and precomputes indices and weights of erosion brush

        //void Initialize (int mapSize, boolean resetSeed) {
        if (resetSeed || prng == null || currentSeed != seed) {
            prng = new Random(seed);
            currentSeed = seed;
        }

        if (erosionBrushIndices == null || currentErosionRadius != erosionRadius || currentMapSize != mapSize) {

            InitializeBrushIndices(mapSize, erosionRadius);
            currentErosionRadius = erosionRadius;
            currentMapSize = mapSize;
        }
    }


    public void Erode(float[] map, int mapSize, World world, Location loc) {

        int numIterations = 1;
        boolean resetSeed = false;

        Initialize(mapSize, resetSeed, world);

        for (int iteration = 0; iteration < numIterations; iteration++) {
            // Create water droplet at random point on map
            float posX = loc.getBlockX();
            float posY = loc.getBlockZ();
            float dirX = 0;
            float dirY = 0;
            float speed = initialSpeed;
            float water = initialWaterVolume;
            float sediment = 0;

            for (int lifetime = 0; lifetime < maxDropletLifetime; lifetime++) {
                int nodeX = (int) posX;
                int nodeY = (int) posY;
                int dropletIndex = nodeY * mapSize + nodeX;
                // Calculate droplet's offset inside the cell (0,0) = at NW node, (1,1) = at SE node
                float cellOffsetX = posX - nodeX;
                float cellOffsetY = posY - nodeY;

                // Calculate droplet's height and direction of flow with bilinear interpolation of surrounding heights
                HeightAndGradient heightAndGradient = new HeightAndGradient().CalculateHeightAndGradient(map, mapSize, posX, posY);

                // Update the droplet's direction and position (move position 1 unit regardless of speed)
                dirX = (dirX * inertia - heightAndGradient.gradientX * (1 - inertia));
                dirY = (dirY * inertia - heightAndGradient.gradientY * (1 - inertia));
                // Normalize direction
                float len = (float) Math.sqrt(dirX * dirX + dirY * dirY);
                if (len != 0) {
                    dirX /= len;
                    dirY /= len;
                }
                posX += dirX;
                posY += dirY;

                // Stop simulating droplet if it's not moving or has flowed over edge of map
                if ((dirX == 0 && dirY == 0) || posX < 0 || posX >= mapSize - 1 || posY < 0 || posY >= mapSize - 1) {
                    break;
                }

                // Find the droplet's new height and calculate the deltaHeight
                float newHeight = new HeightAndGradient().CalculateHeightAndGradient(map, mapSize, posX, posY).height;

                System.out.println("newHeight = " + newHeight);
                
                float deltaHeight = newHeight - heightAndGradient.height;

                // Calculate the droplet's sediment capacity (higher when moving fast down a slope and contains lots of water)
                float sedimentCapacity = Math.max(-deltaHeight * speed * water * sedimentCapacityFactor, minSedimentCapacity);

                // If carrying more sediment than capacity, or if flowing uphill:
                if (sediment > sedimentCapacity || deltaHeight > 0) {
                    // If moving uphill (deltaHeight > 0) try fill up to the current height, otherwise deposit a fraction of the excess sediment
                    float amountToDeposit = (deltaHeight > 0) ? Math.min(deltaHeight, sediment) : (sediment - sedimentCapacity) * depositSpeed;
                    sediment -= amountToDeposit;

                    // Add the sediment to the four nodes of the current cell using bilinear interpolation
                    // Deposition is not distributed over a radius (like erosion) so that it can fill small pits
                    map[dropletIndex] += amountToDeposit * (1 - cellOffsetX) * (1 - cellOffsetY);
                    map[dropletIndex + 1] += amountToDeposit * cellOffsetX * (1 - cellOffsetY);
                    map[dropletIndex + mapSize] += amountToDeposit * (1 - cellOffsetX) * cellOffsetY;
                    map[dropletIndex + mapSize + 1] += amountToDeposit * cellOffsetX * cellOffsetY;

                } else {
                    // Erode a fraction of the droplet's current carry capacity.
                    // Clamp the erosion to the change in height so that it doesn't dig a hole in the terrain behind the droplet
                    float amountToErode = Math.min((sedimentCapacity - sediment) * erodeSpeed, -deltaHeight);

                    // Use erosion brush to erode from all nodes inside the droplet's erosion radius
                    for (int brushPointIndex = 0; brushPointIndex < erosionBrushIndices[dropletIndex].length; brushPointIndex++) {
                        int nodeIndex = erosionBrushIndices[dropletIndex][brushPointIndex];
                        float weighedErodeAmount = amountToErode * erosionBrushWeights[dropletIndex][brushPointIndex];
                        float deltaSediment = (map[nodeIndex] < weighedErodeAmount) ? map[nodeIndex] : weighedErodeAmount;
                        map[nodeIndex] -= deltaSediment;
                        sediment += deltaSediment;
                    }
                }

                // Update droplet's speed and water content
                speed = (float) Math.sqrt(speed * speed + deltaHeight * gravity);
                water *= (1 - evaporateSpeed);
            }
        }
    }

    public static class HeightAndGradient {

        public float gradientX;
        public float gradientY;
        public float height;

        public HeightAndGradient() {
        }

        public HeightAndGradient CalculateHeightAndGradient(float @NotNull [] nodes, int mapSize, float posX, float posY) {
            int coordX = (int) posX;
            int coordY = (int) posY;

            // Calculate droplet's offset inside the cell (0,0) = at NW node, (1,1) = at SE node
            float x = posX - coordX;
            float y = posY - coordY;

            // Calculate heights of the four nodes of the droplet's cell
            int nodeIndexNW = coordY * mapSize + coordX;
            float heightNW = nodes[nodeIndexNW];
            float heightNE = nodes[nodeIndexNW + 1];
            float heightSW = nodes[nodeIndexNW + mapSize];
            float heightSE = nodes[nodeIndexNW + mapSize + 1];

            // Calculate droplet's direction of flow with bilinear interpolation of height difference along the edges
            this.gradientX = (heightNE - heightNW) * (1 - y) + (heightSE - heightSW) * y;
            this.gradientY = (heightSW - heightNW) * (1 - x) + (heightSE - heightNE) * x;

            // Calculate height with bilinear interpolation of the heights of the nodes of the cell
            this.height = heightNW * (1 - x) * (1 - y) + heightNE * x * (1 - y) + heightSW * (1 - x) * y + heightSE * x * y;

            return this;
        }
    }

    static void InitializeBrushIndices(int mapSize, int radius) {
        erosionBrushIndices = new int[mapSize * mapSize][];
        erosionBrushWeights = new float[mapSize * mapSize][];

        int[] xOffsets = new int[radius * radius * 4];
        int[] yOffsets = new int[radius * radius * 4];
        float[] weights = new float[radius * radius * 4];
        float weightSum = 0;
        int addIndex = 0;

        for (int i = 0; i < erosionBrushIndices.length; i++) {
            int centreX = i % mapSize;
            int centreY = i / mapSize;

            if (centreY <= radius || centreY >= mapSize - radius || centreX <= radius + 1 || centreX >= mapSize - radius) {
                weightSum = 0;
                addIndex = 0;
                for (int y = -radius; y <= radius; y++) {
                    for (int x = -radius; x <= radius; x++) {
                        float sqrDst = x * x + y * y;
                        if (sqrDst < radius * radius) {
                            int coordX = centreX + x;
                            int coordY = centreY + y;

                            if (coordX >= 0 && coordX < mapSize && coordY >= 0 && coordY < mapSize) {
                                float weight = (float) (1 - Math.sqrt(sqrDst) / radius);
                                weightSum += weight;
                                weights[addIndex] = weight;
                                xOffsets[addIndex] = x;
                                yOffsets[addIndex] = y;
                                addIndex++;
                            }
                        }
                    }
                }
            }

            int numEntries = addIndex;
            erosionBrushIndices[i] = new int[numEntries];
            erosionBrushWeights[i] = new float[numEntries];

            for (int j = 0; j < numEntries; j++) {
                erosionBrushIndices[i][j] = (yOffsets[j] + centreY) * mapSize + xOffsets[j] + centreX;
                erosionBrushWeights[i][j] = weights[j] / weightSum;
            }
        }
    }

    /**
     * Let a snowball erode the height map
     * @param {Number} x The X coordinate to start at
     * @param {Number} y The Y coordinate to start at
     */
    public static void trace(Player p, int x, int y, int radius, Location l, int resolution) {

        double dropsPerCell = 0.4;
        double erosionRate = 0.04;
        double depositionRate = 0.03;
        double speed = 0.1;
        double friction = 0.7;
        double maxIterations = 10;
        double iterationScale = 0.04;

        int ox = (int) ((new Random().nextFloat() * 2 - 1) * radius); // The X offset
        int oy = (int) ((new Random().nextFloat() * 2 - 1) * radius); // The Y offset

        int sediment = 0; // The amount of carried sediment
        int xp = x; // The previous X position
        int yp = y; // The previous Y position
        double vx = 0; // The horizontal velocity
        double vy = 0; // The vertical velocity


        for (int i = 0; i < maxIterations; ++i) {

            // Get the surface normal of the terrain at the current location
            int surfaceNormal = new UtilsFAWE(p).getHeight(x + ox, y + oy, l);
            System.out.println("surfaceNormal = " + surfaceNormal);

            // If the terrain is flat, stop simulating, the snowball cannot roll any further
            if (surfaceNormal == 999) break;

            // Calculate the deposition and erosion rate
            double deposit = sediment * depositionRate * surfaceNormal;
            double erosion = erosionRate * (1 - surfaceNormal) * Math.min(1, i * iterationScale);

            System.out.println("deposit = " + deposit);
            System.out.println("erosion = " + erosion);
            System.out.println("(deposit - erosion) = " + (deposit - erosion) * 60);

            // Change the sediment on the place this snowball came from
            bv4.add(new BlockVec4(xp, (int) (deposit - erosion) * 60, yp, Material.RED_WOOL));
            //heightMap.change(xp, yp, deposit - erosion);
            sediment += erosion - deposit;

            vx = friction * vx + x * speed * resolution;
            vy = friction * vy + y * speed * resolution;

            xp = x;
            yp = y;
            x += vx;
            y += vy;
        }
    }

    public static void apply (Player p,int x, int y, int radius, Location l,int resolution) {

        // Simulate 50000 snowballs
        int snowballs = 5;

        for (int i = 0; i < snowballs; ++i) {
            trace(p, x, y, radius, l, resolution);
            //random.getFloat() * width,
            //random.getFloat() * height);

            // Blur the height map to smooth out the effects
            //heightMap.blur();
        }
    }



}


