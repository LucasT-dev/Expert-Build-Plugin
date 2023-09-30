package fr.Marodeur.ExpertBuild.Brush;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.API.GlueList;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlowerBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission("exp.brush.flower");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.FLOWER;
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

        Location l = (Location) obj1;
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p, true);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        GlueList<BlockVec4> bv4 = new GlueList<>();
        int radius = bb.getRadius();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                
                try {
                    editsession.setFastMode(false);

                    bv4.addAll(new BlockVec4().getPointInSphere(l, radius, Material.STONE));

                    new UtilsFAWE().setBlockListSimple(p, bv4, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
