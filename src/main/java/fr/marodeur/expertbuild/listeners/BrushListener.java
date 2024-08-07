package fr.marodeur.expertbuild.listeners;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.gui.MainGUI;
import fr.marodeur.expertbuild.object.Configuration;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.NotNull;

public class BrushListener implements Listener {

    @EventHandler
    public void onInteract(@NotNull PlayerInteractEvent e) {

        Player p = e.getPlayer();
        Action action = e.getAction();
        ItemStack it = e.getItem();
        Location loc;
        Configuration conf = Main.configuration();
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);

        if (brushBuilder == null) {
            return;
        }

        if (it == null) return;

        loc = p.getTargetBlock(null, conf.getMax_brush_distance()).getLocation().clone();

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {

            if (it.getType() == Material.HONEYCOMB) {

                if (!brushBuilder.getEnable()) {
                    brushBuilder.sendMessage("expbuild.message.brush.brush_disable", true);
                    return;
                }
                brushBuilder.executeBrush(brushBuilder, Material.HONEYCOMB, loc, p.getLocation());
            }
            if (it.getType() == conf.getTerraforming_tool_1()) {

                brushBuilder.executeBrush(brushBuilder, conf.getTerraforming_tool_1(), loc, p.getLocation());
            }

            if (it.getType() == conf.getTerraforming_tool_2()) {

                brushBuilder.executeBrush(brushBuilder, conf.getTerraforming_tool_2(), loc, p.getLocation());
            }
        }

        if (action == Action.LEFT_CLICK_AIR && it.getType() == Material.HONEYCOMB) {

            if (!Main.containsBrushBuilder(p)) {
                brushBuilder.sendMessage("expbuild.message.brush.player_not_registered", true);
                return;
            }

            if (!p.hasPermission("exp.gui.main")) return;

            new MainGUI().openMainInventory(p);
        }
    }
}