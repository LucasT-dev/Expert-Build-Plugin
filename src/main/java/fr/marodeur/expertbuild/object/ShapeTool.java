package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.api.GlueList;

import java.util.Random;

// Shape
public class ShapeTool {

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

    public GlueList<BlockVectorTool> getBlockVectorList() {
        return toolGlueList;
    }

    public BlockVectorTool getRandomPoint() {

        if (this.toolGlueList.isEmpty()) {
            return BlockVectorTool.ZERO;
        }

        return this.toolGlueList.get(new Random().nextInt(this.toolGlueList.size()));
    }
}
