
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.builderObjects.BrushParameter;
import fr.marodeur.expertbuild.object.builderObjects.TerraParameter;

import com.sk89q.worldedit.bukkit.BukkitAdapter;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ErodeBrush extends AbstractBrush {

    private static final BlockVectorMaterial bvm = new BlockVectorMaterial();

    private final GlueList<Vector> shapePoints = new GlueList<>();

    private final Vector[] CHECK_FACES = {
            new Vector(0, 0, 1),
            new Vector(0, 0, -1),
            new Vector(0, 1, 0),
            new Vector(0, -1, 0),
            new Vector(1, 0, 0),
            new Vector(-1, 0, 0)};

    private int[] erorionParameter;

    private World world;

    /*NONE(new ErosionPreset(0, 1, 0, 1)),
    MELT(new ErosionPreset(2, 1, 5, 1)),
    FILL(new ErosionPreset(5, 1, 2, 1)),
    SMOOTH(new ErosionPreset(3, 1, 3, 1)),
    LIFT(new ErosionPreset(6, 0, 1, 1)),
    FLOATCLEAN(new ErosionPreset(6, 1, 6, 1));

    0 > Erosion face
    1 > Erosion erosionRecursion
    2 > fill Faces
    3 > fill Recursion

    */


    @Override
    public String getBrushName() {
        return "erode";
    }

    @Override
    public String getAliases() {
        return "e";
    }

    @Override
    public String getPermission() {
        return "exp.brush.erode";
    }

    @Override
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        return false;
    }

    @Override
    public boolean spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        this.world = l.getWorld();

        TerraParameter terraParameter = brushBuilder.getTerraParameterProfile();
        BrushParameter brushParameter = brushBuilder.getBrushParameter();
        IterationBlockManager iterationBlockManager = new IterationBlockManager(l.getWorld());

        erorionParameter = new int[]{terraParameter.getErosionFaces(), terraParameter.getErosionRecursion(), terraParameter.getFillFaces(), terraParameter.getFillRecursion()};

        brushParameter.getShape()
                .generateShape(brushBuilder, new BlockVectorTool().toBlockVectorTool(l))
                .forEach(bvt -> shapePoints.add(bvt.toVector()) );


        for (int i = 0; i < erorionParameter[1]; ++i) {
            erosionIteration(iterationBlockManager);
        }

        for (int i = 0; i < erorionParameter[3]; ++i) {
            fillIteration(iterationBlockManager);
        }

        new FaweAPI(brushBuilder.getPlayer()).setBlock(bvm, false);

        shapePoints.clear();
        bvm.clear();

        return false;
    }

    @Override
    public boolean clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        this.world = l.getWorld();

        TerraParameter terraParameter = brushBuilder.getTerraParameterProfile();
        BrushParameter brushParameter = brushBuilder.getBrushParameter();
        IterationBlockManager blockChangeTracker = new IterationBlockManager(l.getWorld());

        erorionParameter = new int[]{terraParameter.getFillFaces(), terraParameter.getFillRecursion(), terraParameter.getErosionFaces(), terraParameter.getErosionRecursion()};

        brushParameter.getShape()
                .generateShape(brushBuilder, new BlockVectorTool().toBlockVectorTool(l))
                .forEach(bvt -> shapePoints.add(bvt.toVector()) );


        for (int i = 0; i < erorionParameter[1]; ++i) {
            erosionIteration(blockChangeTracker);
        }

        for (int i = 0; i < erorionParameter[3]; ++i) {
            fillIteration(blockChangeTracker);
        }

        new FaweAPI(brushBuilder.getPlayer()).setBlock(bvm, false);

        shapePoints.clear();
        bvm.clear();

        return false;
    }

    private void erosionIteration(final @NotNull IterationBlockManager iterationBlockManager) {

        final int currentIteration = iterationBlockManager.nextIteration();

        shapePoints.forEach(currentPosition -> {

            final BlockVectorTool currentBlock = iterationBlockManager.get(currentPosition, currentIteration);

            int count = 0;

            for (final Vector vector : CHECK_FACES) {

                final Vector relativePosition = currentPosition.clone().add(vector);
                final BlockVectorTool relativeBlock = iterationBlockManager.get(relativePosition, currentIteration);

                if (relativeBlock.getBlock(this.world).isEmpty() || relativeBlock.getBlock(this.world).isLiquid()) {
                    count++;
                }
            }

            if (count >= erorionParameter[0]) {

                if (!currentPosition.toLocation(this.world).getBlock().getType().equals(Material.AIR)) {

                    iterationBlockManager.put(currentPosition, currentBlock, Material.AIR, currentIteration);
                }
            }
        });
    }

    private void fillIteration(final @NotNull IterationBlockManager iterationBlockManager) {

        final int currentIteration = iterationBlockManager.nextIteration();

        shapePoints.forEach(currentPosition -> {

            final BlockVectorTool currentBlock = iterationBlockManager.get(currentPosition, currentIteration);
            int count = 0;
            final Map<Material, Integer> blockCount = new HashMap<>();

            for (final Vector vector : CHECK_FACES) {

                final Vector relativePosition = currentPosition.clone().add(vector);
                final BlockVectorTool relativeBlock = iterationBlockManager.get(relativePosition, currentIteration);

                if (!(relativeBlock.getBlock(this.world).isEmpty() || relativeBlock.getBlock(this.world).isLiquid())) {

                    count++;

                    final Material typeBlock = relativeBlock.getMaterial(this.world);

                    if (blockCount.containsKey(typeBlock)) {
                        blockCount.put(typeBlock, blockCount.get(typeBlock) + 1);
                    } else {
                        blockCount.put(typeBlock, 1);
                    }
                }
            }
            //Loc null
            Material currentMaterial = Material.AIR;
            int amount = 0;

            for (final Material wrapper : blockCount.keySet()) {
                final Integer currentCount = blockCount.get(wrapper);
                if (amount <= currentCount) {
                    currentMaterial = wrapper;
                    amount = currentCount;
                }
            }

            if (count >= erorionParameter[2]) {
                if (!currentPosition.toLocation(iterationBlockManager.world).getBlock().getType().equals(currentMaterial)) {

                    iterationBlockManager.put(currentPosition, currentBlock, currentMaterial,  currentIteration);

                }
            }
        });
    }

    private static final class IterationBlockManager {

        private final Map<Integer, Map<Vector, BlockVectorTool>> blockChanges;
        private final World world;
        private int nextIterationId = 0;

        public IterationBlockManager(final World world) {
            this.blockChanges = new HashMap<>();
            this.world = world;
        }

        public BlockVectorTool get(final Vector position, final int iteration) {
            BlockVectorTool changedBlock;

            for (int i = iteration - 1; i >= 0; --i) {
                if (this.blockChanges.containsKey(i) && this.blockChanges.get(i).containsKey(position)) {
                    changedBlock = this.blockChanges.get(i).get(position);
                    return changedBlock;
                }
            }

            return new BlockVectorTool().toBlockVectorTool(position.toLocation(this.world));

        }

        public int nextIteration() {
            return this.nextIterationId++;
        }

        public void put(final Vector position, final BlockVectorTool changedBlock, Material materialChangedBlock, final int iteration) {
            if (!this.blockChanges.containsKey(iteration)) {
                this.blockChanges.put(iteration, new HashMap<>());
            }

            this.blockChanges.get(iteration).put(position, changedBlock);

            bvm.addPositionMaterial(new BlockVectorTool().toBlockVectorTool(position), BukkitAdapter.asBlockType(materialChangedBlock).getDefaultState().toBaseBlock());

        }
    }


    /*public void blend(Location l, boolean excludeAir, int radius) {

        boolean excludeWater = false;

        int size = 2 * (radius + 1) + 1;
        final int brushSizeDoubled = 2 * radius;
        // Array that holds the original materials plus a buffer
        final Material[][][] oldMaterials = new Material[size][size][size];
        // Array that holds the blended materials
        final Material[][][] newMaterials = new Material[brushSizeDoubled + 1][brushSizeDoubled + 1][brushSizeDoubled + 1];

        spherePoint.forEach(pos -> {

            bv4.stream().forEach(blockVec4 -> {

                if (blockVec4.getcoordonateEquals(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ())) {
                    oldMaterials[pos.getBlockX()][pos.getBlockY()][pos.getBlockZ()] = blockVec4.getMat();

                } else {
                    oldMaterials[pos.getBlockX()][pos.getBlockY()][pos.getBlockZ()] = new BlockVec4()
                            .getMaterial(l.getWorld(),
                                    l.getBlockX() - radius - 1 + pos.getBlockX(),
                                    l.getBlockY() - radius - 1 + pos.getBlockY(),
                                    l.getBlockZ() - radius - 1 + pos.getBlockZ());
                }
            });
        });


        //Log current materials into oldmats
        for (int x = 0; x <= 2 * (radius + 1); x++) {
            for (int y = 0; y <= 2 * (radius + 1); y++) {
                for (int z = 0; z <= 2 * (radius + 1); z++) {

                    int finalX = x;
                    int finalY = y;
                    int finalZ = z;

                    bv4.stream().forEach(blockVec4 -> {

                        if (blockVec4.getcoordonateEquals(l.getBlockX() - radius - 1 + finalX,
                                l.getBlockY() - radius - 1 + finalY,
                                l.getBlockZ() - radius - 1 + finalZ)) {

                            oldMaterials[finalX][finalY][finalZ] = blockVec4.getMat();

                        } else {
                            oldMaterials[finalX][finalY][finalZ] = new BlockVec4()
                                    .getMaterial(l.getWorld(),
                                            l.getBlockX() - radius - 1 + finalX,
                                            l.getBlockY() - radius - 1 + finalY,
                                            l.getBlockZ() - radius - 1 + finalZ);

                            //System.out.println("NOT use espion block ");
                        }
                    });

                    //Calc

                    //oldMaterials[x][y][z] = new BlockVec4().getMaterial(l.getWorld(), l.getBlockX() - radius - 1 + x, l.getBlockY() - radius - 1 + y, l.getBlockZ() - radius - 1 + z);

                }
            }
        }

        // Log current materials into newmats
        for (int x = 0; x <= brushSizeDoubled; x++) {
            for (int y = 0; y <= brushSizeDoubled; y++) {
                System.arraycopy(oldMaterials[x + 1][y + 1], 1, newMaterials[x][y], 0, brushSizeDoubled + 1);
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
                        if (e.getValue() == highestMaterialCount && !(excludeAir && e.getKey() == Material.AIR) && !(excludeWater && e.getKey() == Material.WATER)) {                                        if (e.getKey() == highestMaterial) {
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

                        if (!(excludeAir && newMaterials[x][y][z] == Material.STONE) && !(excludeWater && (newMaterials[x][y][z] == Material.WATER))) {

                            if (!bv4.contains(new BlockVec4(l.getBlockX() - radius + x, l.getBlockY() - radius + y, l.getBlockZ() - radius + z, oldMaterials[x][y][z]))) {

                                bv4.add(new BlockVec4(l.getBlockX() - radius + x, l.getBlockY() - radius + y, l.getBlockZ() - radius + z, newMaterials[x][y][z]));
                            }
                        }
                    }
                }
            }
        }
    }*/
}
