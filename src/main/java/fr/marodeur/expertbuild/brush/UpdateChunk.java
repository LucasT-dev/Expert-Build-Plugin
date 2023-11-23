package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.enums.BrushEnum;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.BrushOperation;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UpdateChunk implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission("exp.brush.updatechunk");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.UPDATECHUNK;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return BrushOperation.super.hasEnabelingBrush(brushBuilder);
    }

    @Override
    public void ExecuteBrushOnHoney(Player p, Object obj1) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p, true)) ||
                !BrushBuilder.getBrushBuilderPlayer(p, true).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        new UtilsFAWE(p).refreshChunk(((Location) obj1).getChunk());
    }
}
