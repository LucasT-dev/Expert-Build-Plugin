
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
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.object.*;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class LineBrush extends AbstractBrush {

    private final HashMap<UUID,ArrayList<BlockVec4>> point = new HashMap<>();
    Configuration conf = Main.getInstance().getConfig();
    MessageBuilder msg = Main.getInstance().getMessageConfig();


    @Override
    public String getBrushName() {
        return "line";
    }

    @Override
    public String getAliases() {
        return null;
    }

    @Override
    public String getPermission() {
        return "exp.brush.line";
    }

    @Override
    public void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

    }

    @Override
    public void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;

        if (!point.containsKey(brushBuilder.getUUID())) {

            ArrayList<BlockVec4> pointList = new ArrayList<>();
            pointList.add(new BlockVec4(l));

            point.put(brushBuilder.getUUID(), pointList);
            brushBuilder.sendMessage(msg.getPointAdd(l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ()));
            //p.sendMessage(Main.prefix + "Point add at (" + l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ() + ")");
            return;
        } else {

            ArrayList<BlockVec4> pointList = new ArrayList<>();
            pointList.addAll(point.get(brushBuilder.getUUID()));

            //en config
            if (pointList.size() >= conf.getMax_point_saved()) {
                point.replace(brushBuilder.getUUID(), pointList);
                brushBuilder.sendMessage(msg.getPointNotSave());
                //p.sendMessage(Main.prefix + "limite size, point not save");

                return;
            }

            pointList.add(new BlockVec4(l));
            point.replace(brushBuilder.getUUID(), pointList);
            brushBuilder.sendMessage(msg.getPointAdd(l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ()));

        }
    }

    @Override
    public void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc) {

        Location l = (Location) loc;
        Location pl = (Location) ploc;
        BukkitPlayer actor = BukkitAdapter.adapt(brushBuilder.getPlayer());
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        GlueList<BlockVec4> bv4 = new GlueList<>();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    spectralToolBrush(brushBuilder, loc, ploc);

                    if (point.containsKey(brushBuilder.getUUID())) {

                        ArrayList<BlockVec4> bv41 = point.get(brushBuilder.getUUID());

                        for (int i = 0; i <= bv41.size(); i++) {

                            if (i == bv41.size() - 1) {

                                point.get(brushBuilder.getUUID()).stream().forEach(blockVec4 -> {

                                    blockVec4.setMat(brushBuilder.getMaterial());
                                    bv4.add(blockVec4);

                                    new UtilsFAWE(brushBuilder.getPlayer()).setBlockListSimple(brushBuilder.getPlayer(), bv4, false);

                                    point.remove(brushBuilder.getUUID());

                                });
                                return;
                            }

                            BlockVec4 bv42 = bv41.get(i);

                            BlockVec4 bv421 = bv41.get(i + 1);
                            bv4.addAll(new BlockVec4()
                                    .getPointInto2Point(new Location(pl.getWorld(), bv42.getX(), bv42.getY(), bv42.getZ()),
                                            new Location(pl.getWorld(), bv421.getX(), bv421.getY(), bv421.getZ()),
                                            1,
                                            brushBuilder.getMaterial()
                                    ));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
