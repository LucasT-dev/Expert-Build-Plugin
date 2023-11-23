package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.enums.BrushEnum;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.BrushOperation;
import fr.marodeur.expertbuild.object.MessageBuilder;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NoneBrush implements BrushOperation {

    private MessageBuilder message = Main.getInstance().getMessageConfig();

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission("exp.brush.none");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.NONE;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return BrushOperation.super.hasEnabelingBrush(brushBuilder);
    }

    @Override
    public void ExecuteBrushOnHoney(Player p, Object obj1) {
        if (hasPermission(p)) {
            BrushBuilder.getBrushBuilderPlayer(p, true).sendMessage(message.getBrushDisable());
        }
    }

    @Override
    public void ExecuteBrushOnArrow(Player p, Object obj1, Object loc) {
        if (hasPermission(p)) {
            BrushBuilder.getBrushBuilderPlayer(p, true).sendMessage(message.getBrushDisable());
        }
    }

    @Override
    public void ExecuteBrushOnGunpowder(Player p, Object obj1, Object loc) {
        if (hasPermission(p)) {
            BrushBuilder.getBrushBuilderPlayer(p, true).sendMessage(message.getBrushDisable());
        }
    }
}
