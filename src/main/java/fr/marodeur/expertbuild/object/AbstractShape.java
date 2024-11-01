package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.api.GlueList;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Stream;

public abstract class AbstractShape {

    private final GlueList<BlockVectorTool> toolGlueList;
    private final Map<String, Object> parameters;

    public AbstractShape(GlueList<BlockVectorTool> toolGlueList) {
        this.toolGlueList = toolGlueList;
        this.parameters = new HashMap<>();
    }

    public AbstractShape(int capacity) {
        toolGlueList = new GlueList<>(capacity);
        this.parameters = new HashMap<>();
    }

    public AbstractShape() {
        toolGlueList = new GlueList<>();
        this.parameters = new HashMap<>();
    }

    public void add(BlockVectorTool blockVectorTool) {
        toolGlueList.add(blockVectorTool);
    }

    public int size() {
        return toolGlueList.size();
    }

    //Start Parameter

    public void addParameter(String key, Object value) {
        parameters.put(key.toLowerCase(), value);
    }

    public Object getParameter(String key) {
        return parameters.get(key.toLowerCase());
    }

    public <T> T getParameter(String key, Class<T> type) {
        return type.cast(parameters.get(key.toLowerCase()));
    }

    public boolean containsParameter(String key) {
        return parameters.containsKey(key.toLowerCase());
    }

    //End Parameter


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

    public void clearBlockVector() {
        this.toolGlueList.clear();
    }

    public abstract String getShapeName();

    public abstract void generateShape(BrushBuilder brushBuilder, BlockVectorTool center);


    public static class RegisterShape {

        private final GlueList<AbstractShape> brushShape;
        private final Logger logger = Logger.getLogger("Expert-Build");

        public RegisterShape() {
            brushShape = new GlueList<>();
        }

        public GlueList<AbstractShape> getBrushes() {
            return brushShape;
        }

        public void registerShape(AbstractShape aClass) {
            this.save(aClass);
        }

        private void save(AbstractShape aClass) {
            brushShape.add(aClass);
        }
    }
}
