package fr.marodeur.expertbuild.utils;

import fr.marodeur.expertbuild.object.BrushOperation;
import fr.marodeur.expertbuild.object.MessageBuilder;
import fr.marodeur.expertbuild.Main;

import java.util.logging.Logger;

public class BrushOperationManager {

    private final Logger logger = Logger.getLogger("Expert-Build");
    private final MessageBuilder message = Main.getInstance().getMessageConfig();

    public void addBrush(BrushOperation m) {
        Main.getInstance().getRegisteredBrush().put(m.getClass(), m);
        logger.info(message.getBrushRegistered(m.getTypeOfBrush().toString().toLowerCase()));
    }
}
