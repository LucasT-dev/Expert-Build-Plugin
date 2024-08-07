
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

public class ErodeBlendBrush extends AbstractBrush {

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

    @Override
    public String getBrushName() {
        return "erodeblend";
    }

    @Override
    public String getAliases() {
        return "eb";
    }

    @Override
    public String getPermission() {
        return "exp.brush.erodeblend";
    }

    @Override
    public boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {
        return false;
    }

    @Override
    public boolean spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        bv4.clear();
        Location l = (Location) loc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();
        TerraParameter terraParameter = BrushBuilder.getBrushBuilderPlayer(brushBuilder.getPlayer(), false).getTerraParameterProfile();

        try (EditSession editsession = localSession.createEditSession(actor)) {

            try {
                editsession.setFastMode(false);

                blend(l, false, false, radius);
                new UtilsFAWE(brushBuilder.getPlayer()).setBlockAnyPattern(brushBuilder.getPlayer(), bv4, false);
                bv4.clear();


                erorionParameter = new int[]{terraParameter.getErosionFaces(), terraParameter.getErosionRecursion(), terraParameter.getFillFaces(), terraParameter.getFillRecursion()};
                final IterationBlockManager iterationBlockManager = new IterationBlockManager(l.getWorld());
                final Vector v = l.toVector();

                // cylinder
                for (int x = v.getBlockX() - radius; x <= v.getBlockX() + radius; ++x) {
                    for (int z = v.getBlockZ() - radius; z <= v.getBlockZ() + radius; ++z) {
                        if (new Vector(x, v.getBlockY(), z).distanceSquared(v) <= radius * radius) {
                            for (int y = v.getBlockY()-radius; y <= v.getBlockY() + radius; ++y) {
                                spherePoint.add(new Vector(x, y, z));
                            }
                        }
                    }
                }

                /*for (int x = v.getBlockX() - radius; x <= v.getBlockX() + radius; ++x) {
                    for (int z = v.getBlockZ() - radius; z <= v.getBlockZ() + radius; ++z) {
                        for (int y = v.getBlockY() - radius; y <= v.getBlockY() + radius; ++y) {
                            if (new Vector(x, y, z).isInSphere(v, radius)) {
                                spherePoint.add(new Vector(x, y, z));
                            }
                        }
                    }
                }*/

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
        return false;
    }

    @Override
    public boolean clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        TerraParameter terraParameter = BrushBuilder.getBrushBuilderPlayer(brushBuilder.getPlayer(), false).getTerraParameterProfile();


        bv4.clear();
        Location l = (Location) loc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();

        try (EditSession editsession = localSession.createEditSession(actor)) {
            try {
                editsession.setFastMode(false);

                blend(l, false, false, radius);
                new UtilsFAWE(brushBuilder.getPlayer()).setBlockAnyPattern(brushBuilder.getPlayer(), bv4, false);
                bv4.clear();


                erorionParameter = new int[]{terraParameter.getFillFaces(), terraParameter.getFillRecursion(), terraParameter.getErosionFaces(), terraParameter.getErosionRecursion()};
                final IterationBlockManager blockChangeTracker = new IterationBlockManager(l.getWorld());
                final Vector v = l.toVector();

                // cylinder
                for (int x = v.getBlockX() - radius; x <= v.getBlockX() + radius; ++x) {
                    for (int z = v.getBlockZ() - radius; z <= v.getBlockZ() + radius; ++z) {
                        if (new Vector(x, v.getBlockY(), z).distanceSquared(v) <= radius * radius) {
                            for (int y = v.getBlockY()-radius; y <= v.getBlockY() + radius; ++y) {
                                spherePoint.add(new Vector(x, y, z));
                            }
                        }
                    }
                }

                /*for (int x = v.getBlockX() - radius; x <= v.getBlockX() + radius; ++x) {
                    for (int z = v.getBlockZ() - radius; z <= v.getBlockZ() + radius; ++z) {
                        for (int y = v.getBlockY() - radius; y <= v.getBlockY() + radius; ++y) {
                            if (new Vector(x, y, z).isInSphere(v, radius)) {
                                spherePoint.add(new Vector(x, y, z));
                            }
                        }
                    }
                }*/

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
        return false;
    }

    private void erosionIteration(final @NotNull ErodeBlendBrush.IterationBlockManager iterationBlockManager) {

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

    private void fillIteration(final @NotNull ErodeBlendBrush.IterationBlockManager iterationBlockManager) {

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

                /*if (!currentPosition.toLocation(iterationBlockManager.world).getBlock().getType().equals(currentMaterial.getMat())) {

                    iterationBlockManager.put(currentPosition, new BlockVec4(currentBlock.getBlock().getLocation(), currentMaterial.getMat()), currentIteration);

                }*/
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

    public void blend(Location l, boolean excludeAir, boolean excludeWater, int radius) {


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
}