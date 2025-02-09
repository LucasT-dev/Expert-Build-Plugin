package fr.marodeur.expertbuild.object.lison;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.object.BlockVectorTool;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class AdvancedBlockOperation implements ScheduledWorkload {

    private final Player p;
    private BlockVectorTool position;
    private World world;
    private Material material;


    public AdvancedBlockOperation(Player p) {
        this.p = p;
    }

    private AdvancedBlockOperation(Player p, World world, BlockVectorTool position, Material material) {

        this.p = p;
        this.position = position;
        this.world = world;
        this.material = material;

    }

    private AdvancedBlockOperation blockOperation(World world, BlockVectorTool position, Material material) {
        return new AdvancedBlockOperation(this.p, world, position, material);
    }

    public void setBlock(World world, GlueList<BlockVectorTool> positions, Material material) {

        positions.stream().forEach(blockVectorTool ->
                new LightweightInteractiveSystemforOptimizedParticleNavigation()
                .addScheduledWorkloadRunnable(
                        new AdvancedBlockOperation(this.p)
                                .blockOperation(world, blockVectorTool, material)));
    }

    public void setBlock(World world, BlockVectorTool position, Material material) {

        new LightweightInteractiveSystemforOptimizedParticleNavigation()
                .addScheduledWorkloadRunnable(
                        new AdvancedBlockOperation(this.p)
                                .blockOperation(world, position, material));

    }

    @Override
    public void compute() {

        this.world.getBlockAt(
                this.position.getBlockX(),
                this.position.getBlockY(),
                this.position.getBlockZ()
        ).setType(this.material);
    }

    @Override
    public boolean shouldBeRescheduled() {
        return false;
    }
}