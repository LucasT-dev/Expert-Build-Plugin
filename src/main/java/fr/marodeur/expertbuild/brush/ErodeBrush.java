
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;

import fr.marodeur.expertbuild.object.builderObjects.TerraParameter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ErodeBrush extends AbstractBrush {

    private static final GlueList<BlockVec4> bv4 = new GlueList<>();

    private final GlueList<Vector> spherePoint = new GlueList<>();

    private final Vector[] CHECK_FACES = {
            new Vector(0, 0, 1),
            new Vector(0, 0, -1),
            new Vector(0, 1, 0),
            new Vector(0, -1, 0),
            new Vector(1, 0, 0),
            new Vector(-1, 0, 0)};

    private int[] erorionParameter;

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
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

    }

    @Override
    public void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        bv4.clear();
        Location l = (Location) loc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();
        TerraParameter terraParameter = BrushBuilder.getBrushBuilderPlayer(brushBuilder.getPlayer(), false).getTerraparameterProfile();

        try (EditSession editsession = localSession.createEditSession(actor)) {

            try {
                editsession.setFastMode(false);

                erorionParameter = new int[]{terraParameter.getErosionFaces(), terraParameter.getErosionRecursion(), terraParameter.getFillFaces(), terraParameter.getFillRecursion()};

                final IterationBlockManager iterationBlockManager = new IterationBlockManager(l.getWorld());
                final Vector v = l.toVector();

                for (int x = v.getBlockX() - radius; x <= v.getBlockX() + radius; ++x) {
                    for (int z = v.getBlockZ() - radius; z <= v.getBlockZ() + radius; ++z) {
                        for (int y = v.getBlockY() - radius; y <= v.getBlockY() + radius; ++y) {
                            if (new Vector(x, y, z).isInSphere(v, radius)) {
                                spherePoint.add(new Vector(x, y, z));
                            }
                        }
                    }
                }

                for (int i = 0; i < erorionParameter[1]; ++i) {
                    erosionIteration(iterationBlockManager);
                }

                for (int i = 0; i < erorionParameter[3]; ++i) {
                    fillIteration(iterationBlockManager);
                }

                new UtilsFAWE(brushBuilder.getPlayer()).setBlockAnyPattern(brushBuilder.getPlayer(), bv4, false);

                spherePoint.clear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        bv4.clear();
        Location l = (Location) loc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();
        TerraParameter terraParameter = BrushBuilder.getBrushBuilderPlayer(brushBuilder.getPlayer(), false).getTerraparameterProfile();

        try (EditSession editsession = localSession.createEditSession(actor)) {
            try {
                editsession.setFastMode(false);

                erorionParameter = new int[]{terraParameter.getFillFaces(), terraParameter.getFillRecursion(), terraParameter.getErosionFaces(), terraParameter.getErosionRecursion()};

                final IterationBlockManager blockChangeTracker = new IterationBlockManager(l.getWorld());
                final Vector v = l.toVector();

                for (int x = v.getBlockX() - radius; x <= v.getBlockX() + radius; ++x) {
                    for (int z = v.getBlockZ() - radius; z <= v.getBlockZ() + radius; ++z) {
                        for (int y = v.getBlockY() - radius; y <= v.getBlockY() + radius; ++y) {
                            if (new Vector(x, y, z).isInSphere(v, radius)) {
                                spherePoint.add(new Vector(x, y, z));
                            }
                        }
                    }
                }

                for (int i = 0; i < erorionParameter[1]; ++i) {
                    erosionIteration(blockChangeTracker);
                }

                for (int i = 0; i < erorionParameter[3]; ++i) {
                    fillIteration(blockChangeTracker);
                }

                new UtilsFAWE(brushBuilder.getPlayer()).setBlockAnyPattern(brushBuilder.getPlayer(), bv4, false);

                spherePoint.clear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void erosionIteration(final @NotNull IterationBlockManager iterationBlockManager) {

        final int currentIteration = iterationBlockManager.nextIteration();

        spherePoint.forEach(currentPosition -> {

            final BlockVec4 currentBlock = iterationBlockManager.get(currentPosition, currentIteration);

            int count = 0;

            for (final Vector vector : CHECK_FACES) {

                final Vector relativePosition = currentPosition.clone().add(vector);
                final BlockVec4 relativeBlock = iterationBlockManager.get(relativePosition, currentIteration);

                if (relativeBlock.getBlock().isEmpty() || relativeBlock.getBlock().isLiquid()) {
                    count++;
                }
            }

            if (count >= erorionParameter[0]) {

                if (!currentPosition.toLocation(iterationBlockManager.world).getBlock().getType().equals(Material.AIR)) {

                    iterationBlockManager.put(currentPosition, new BlockVec4(currentBlock.getLoc(), Material.AIR), currentIteration);
                }
            }
        });
    }

    private void fillIteration(final @NotNull IterationBlockManager iterationBlockManager) {

        final int currentIteration = iterationBlockManager.nextIteration();

        spherePoint.forEach(currentPosition -> {

            final BlockVec4 currentBlock = iterationBlockManager.get(currentPosition, currentIteration);

            int count = 0;

            final Map<BlockVec4, Integer> blockCount = new HashMap<>();

            for (final Vector vector : CHECK_FACES) {

                final Vector relativePosition = currentPosition.clone().add(vector);
                final BlockVec4 relativeBlock = iterationBlockManager.get(relativePosition, currentIteration);

                if (!(relativeBlock.getBlock().isEmpty() || relativeBlock.getBlock().isLiquid())) {

                    count++;

                    final BlockVec4 typeBlock = new BlockVec4(relativeBlock.getBlock().getType());

                    if (blockCount.containsKey(typeBlock)) {
                        blockCount.put(typeBlock, blockCount.get(typeBlock) + 1);
                    } else {
                        blockCount.put(typeBlock, 1);
                    }
                }
            }
            //Loc null
            BlockVec4 currentMaterial = new BlockVec4(Material.AIR);
            int amount = 0;

            for (final BlockVec4 wrapper : blockCount.keySet()) {
                final Integer currentCount = blockCount.get(wrapper);
                if (amount <= currentCount) {
                    currentMaterial = wrapper;
                    amount = currentCount;
                }
            }

            if (count >= erorionParameter[2]) {

                if (!currentPosition.toLocation(iterationBlockManager.world).getBlock().getType().equals(currentMaterial.getMat())) {

                    iterationBlockManager.put(currentPosition, new BlockVec4(currentBlock.getBlock().getLocation(), currentMaterial.getMat()), currentIteration);

                }
            }
        });
    }

    private static final class IterationBlockManager {

        private final Map<Integer, Map<Vector, BlockVec4>> blockChanges;
        private final World world;
        private int nextIterationId = 0;

        public IterationBlockManager(final World world) {
            this.blockChanges = new HashMap<>();
            this.world = world;
        }

        public BlockVec4 get(final Vector position, final int iteration) {
            BlockVec4 changedBlock;

            for (int i = iteration - 1; i >= 0; --i) {
                if (this.blockChanges.containsKey(i) && this.blockChanges.get(i).containsKey(position)) {
                    changedBlock = this.blockChanges.get(i).get(position);
                    return changedBlock;
                }
            }

            return new BlockVec4(position.toLocation(this.world));

        }

        public int nextIteration() {
            return this.nextIterationId++;
        }

        public void put(final Vector position, final BlockVec4 changedBlock, final int iteration) {
            if (!this.blockChanges.containsKey(iteration)) {
                this.blockChanges.put(iteration, new HashMap<>());
            }

            this.blockChanges.get(iteration).put(position, changedBlock);

            bv4.add(new BlockVec4(position.toLocation(this.world), changedBlock.getMat()));

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
