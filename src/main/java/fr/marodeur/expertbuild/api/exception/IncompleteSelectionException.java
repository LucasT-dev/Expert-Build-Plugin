package fr.marodeur.expertbuild.api.exception;

import fr.marodeur.expertbuild.Main;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;
import fr.marodeur.expertbuild.object.MessageBuilder;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class IncompleteSelectionException extends Exception{

    private static final MessageBuilder message = Main.getInstance().getMessageConfig();
    private static Logger log = Logger.getLogger("Expert-Build");

    public IncompleteSelectionException() {
        super("Error");
    }

    public IncompleteSelectionException(String error) {
        log.info(Main.prefix + error);
    }

    public IncompleteSelectionException (@NotNull Player p, @NotNull RegionSelectorType regionSelectorType) {
        p.sendMessage(Main.prefix + message.getErrorSelection(regionSelectorType.name()));
    }
}
