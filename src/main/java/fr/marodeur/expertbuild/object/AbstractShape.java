package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.api.GlueList;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Stream;

public abstract class AbstractShape {
    private final Map<String, Object> parameters;

    public AbstractShape() {
        this.parameters = new HashMap<>();
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

    public abstract String getShapeName();

    public abstract Stream<BlockVectorTool> generateShape(BrushBuilder brushBuilder, BlockVectorTool center);


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
