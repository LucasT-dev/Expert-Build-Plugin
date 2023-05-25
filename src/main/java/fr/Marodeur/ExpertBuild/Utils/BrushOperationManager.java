package fr.Marodeur.ExpertBuild.Utils;

import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;

import java.util.logging.Logger;

public class BrushOperationManager {

    private final Logger logger = Logger.getLogger("Expert-Build");

    public void addBrush(BrushOperation m) {
        Main.getInstance().getRegisteredBrush().put(m.getClass(), m);
        logger.info("Brush " + m.getTypeOfBrush().toString().toLowerCase() + " registered");
    }
}
