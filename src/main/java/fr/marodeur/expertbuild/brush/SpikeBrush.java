package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.enums.BrushEnum;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.BrushOperation;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SpikeBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission("exp.brush.spike");

    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.SPIKE;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return BrushOperation.super.hasEnabelingBrush(brushBuilder);
    }

    @Deprecated
    @Override
    public void ExecuteBrushOnArrow(Player p, Object obj1, Object loc) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p, true)) ||
                !BrushBuilder.getBrushBuilderPlayer(p, true).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location l = (Location) obj1;
        Location ploc = (Location) loc;
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p, true);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = bb.getRadius();
        GlueList<BlockVec4> bv4 = new GlueList<>();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    ArrayList<BlockVec4> sphere = new ArrayList<>();

                    sphere.addAll(new BlockVec4().getPointInSphere(ploc, radius, bb.getMaterial()));

                    sphere.forEach(blockVec4 ->
                        bv4.addAll(blockVec4.getPointInto2Point(blockVec4.toLocation(l.getWorld()), l, 1, bb.getMaterial())));

                    new UtilsFAWE(p).setBlockListSimple(p, bv4, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Deprecated
    @Override
    public void ExecuteBrushOnGunpowder(Player p, Object obj1, Object loc) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p, true)) ||
                !BrushBuilder.getBrushBuilderPlayer(p, true).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location l = (Location) obj1;
        Location ploc = (Location) loc;
        //Location loc = p.getLocation();
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p, true);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = bb.getRadius();
        GlueList<BlockVec4> bv4 = new GlueList<>();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    ArrayList<BlockVec4> sphere = new ArrayList<>();

                    sphere.addAll(new BlockVec4().getPointInSphere(l, radius, bb.getPattern()));

                    sphere.stream().forEach(blockVec4 ->
                            bv4.addAll(blockVec4.getPointInto2Point(blockVec4.toLocation(l.getWorld()), ploc, 1, bb.getPattern())));

                    new UtilsFAWE(p).setBlockListSimple(p, bv4, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
