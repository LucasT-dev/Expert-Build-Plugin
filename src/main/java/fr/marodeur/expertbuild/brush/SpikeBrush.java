
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.object.AbstractBrush;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;

public class SpikeBrush extends AbstractBrush {

    @Override
    public String getBrushName() {
        return "spike";
    }

    @Override
    public String getPermission() {
        return "exp.brush.spike";
    }

    @Override
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

    }

    @Override
    public void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        Location pl = (Location) ploc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();
        GlueList<BlockVec4> bv4 = new GlueList<>();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    ArrayList<BlockVec4> sphere = new ArrayList<>();

                    sphere.addAll(new BlockVec4().getPointInSphere(pl, radius, brushBuilder.getMaterial()));

                    sphere.forEach(blockVec4 ->
                            bv4.addAll(blockVec4.getPointInto2Point(blockVec4.toLocation(l.getWorld()), l, 1, brushBuilder.getMaterial())));

                    new UtilsFAWE(brushBuilder.getPlayer()).setBlockListSimple(brushBuilder.getPlayer(), bv4, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        Location pl = (Location) ploc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = brushBuilder.getRadius();
        GlueList<BlockVec4> bv4 = new GlueList<>();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    ArrayList<BlockVec4> sphere = new ArrayList<>();

                    sphere.addAll(new BlockVec4().getPointInSphere(l, radius, brushBuilder.getPattern()));

                    sphere.stream().forEach(blockVec4 ->
                            bv4.addAll(blockVec4.getPointInto2Point(blockVec4.toLocation(l.getWorld()), pl, 1, brushBuilder.getPattern())));

                    new UtilsFAWE(brushBuilder.getPlayer()).setBlockListSimple(brushBuilder.getPlayer(), bv4, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
