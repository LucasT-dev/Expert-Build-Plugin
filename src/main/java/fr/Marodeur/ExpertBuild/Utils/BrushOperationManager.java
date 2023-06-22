package fr.Marodeur.ExpertBuild.Utils;

import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;

import java.util.logging.Logger;

public class BrushOperationManager {

    private final Logger logger = Logger.getLogger("Expert-Build");
    private final MessageBuilder message = Main.getInstance().getMessageConfig();

    public void addBrush(BrushOperation m) {
        Main.getInstance().getRegisteredBrush().put(m.getClass(), m);
        logger.info(message.getBrushRegistered(m.getTypeOfBrush().toString().toLowerCase()));
    }
}
