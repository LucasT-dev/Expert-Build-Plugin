package fr.Marodeur.ExpertBuild.Brush.Brush;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CubeBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.isOp() | p.hasPermission("expcube.use");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.CUBE;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return brushBuilder.getEnable();
    }

    @Override
    public void ExecuteBrushOnArrow(Player p, Object obj1, Object loc) {
        ExecuteBrushOnGunpowder(p, obj1, loc);
    }

    @Deprecated
    @Override
    public void ExecuteBrushOnGunpowder(Player p, Object obj1, Object loc) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p)) ||
                !BrushBuilder.getBrushBuilderPlayer(p).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location l = (Location) obj1;
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = bb.getRayon();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    CuboidRegion cuboidRegion = new CuboidRegion(BlockVector3.at(l.getBlockX(), l.getBlockY(), l.getBlockZ()).add(radius, 0, radius),
                            BlockVector3.at(l.getBlockX(), l.getBlockY(), l.getBlockZ()).add(-radius, 2*radius, -radius));

                    new UtilsFAWE().setCuboidRegion(p, cuboidRegion);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
