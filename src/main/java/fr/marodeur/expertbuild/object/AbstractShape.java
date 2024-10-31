package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.api.GlueList;

import java.util.Random;
import java.util.stream.Stream;

public abstract class AbstractShape {

    private final GlueList<BlockVectorTool> toolGlueList;


    public AbstractShape(GlueList<BlockVectorTool> toolGlueList) {
        this.toolGlueList = toolGlueList;
    }

    public AbstractShape(int capacity) {
        toolGlueList = new GlueList<>(capacity);
    }

    public AbstractShape() {
        toolGlueList = new GlueList<>();
    }

    public void add(BlockVectorTool blockVectorTool) {
        toolGlueList.add(blockVectorTool);
    }

    public int size() {
        return toolGlueList.size();
    }

    public GlueList<BlockVectorTool> getBlockVectorList() {
        return toolGlueList;
    }

    public BlockVectorTool getRandomPoint() {

        if (this.toolGlueList.isEmpty()) {
            return BlockVectorTool.ZERO;
        }

        return this.toolGlueList.get(new Random().nextInt(this.toolGlueList.size()));
    }

    // Method to return a stream of BlockVectorTool
    public Stream<BlockVectorTool> streamToolGlueList() {
        return toolGlueList.stream();
    }

    public abstract void generateShape(BrushBuilder brushBuilder, BlockVectorTool center);

}
