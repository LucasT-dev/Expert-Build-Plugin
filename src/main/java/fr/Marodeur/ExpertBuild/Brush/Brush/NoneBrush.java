package fr.Marodeur.ExpertBuild.Brush.Brush;

import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Enum.MsgEnum;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NoneBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.isOp() | p.hasPermission("expnone.use");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.NONE;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return false;
    }

    @Override
    public void ExecuteBrushOnHoney(Player p, Object obj1) {
        if (hasPermission(p)) {
            new UtilsFAWE(p).sendMessage(MsgEnum.BRUSH_DISABLE);
        }
    }

    @Override
    public void ExecuteBrushOnArrow(Player p, Object obj1) {
        if (hasPermission(p)) {
            //new UtilsFAWE(p).sendMessage(MsgEnum.BRUSH_DISABLE);
        }
    }

    @Override
    public void ExecuteBrushOnGunpowder(Player p, Object obj1) {
        if (hasPermission(p)) {
            //new UtilsFAWE(p).sendMessage(MsgEnum.BRUSH_DISABLE);
        }
    }
}
