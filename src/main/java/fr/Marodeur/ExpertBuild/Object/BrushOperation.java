package fr.Marodeur.ExpertBuild.Object;

import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface BrushOperation {

    boolean hasPermission(@NotNull Player p);

    BrushEnum getTypeOfBrush();

    boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder);

    default void ExecuteBrushOnHoney(Player p, Object obj1) {}

    default void ExecuteBrushOnArrow(Player p, Object obj1, Object loc) {}

    default void ExecuteBrushOnGunpowder(Player p, Object obj1, Object loc) {}
}
