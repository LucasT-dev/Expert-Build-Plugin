package fr.Marodeur.ExpertBuild.API.Exception;

import com.sk89q.worldedit.regions.selector.RegionSelectorType;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.MsgEnum;
import fr.Marodeur.ExpertBuild.Main;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class IncompleteSelectionException extends Exception{

    public IncompleteSelectionException() {
        super("Error");
    }

    public IncompleteSelectionException(String error) {
        Main.getInstance().getLogger().info(Main.prefix + error);
    }

    public IncompleteSelectionException (@NotNull Player p, String error, @NotNull RegionSelectorType regionSelectorType) {
        new UtilsFAWE(p).sendMessage(MsgEnum.ERROR_SELECTION, "<selection>", regionSelectorType.name());
        Main.getInstance().getLogger().info(Main.prefix + error);
    }
}
