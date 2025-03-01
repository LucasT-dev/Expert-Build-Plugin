package fr.marodeur.expertbuild.listeners;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
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
        Configuration conf = Main.getConfiguration();
        BrushBuilder brushBuilder;

        if (it == null) return;

        if (! BrushBuilder.containsPlayerBrush(p)) {
            return;
        }

        brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

        loc = p.getTargetBlock(null, conf.getMax_brush_distance()).getLocation().clone();


        if (action == Action.LEFT_CLICK_AIR && it.getType() == Material.HONEYCOMB) {

            if (!p.hasPermission("exp.gui.main")) return;

            new MainGUI().openMainInventory(p);

            return;
        }

        if (it.getType() == Material.HONEYCOMB) {

            if (!brushBuilder.getEnable()) {
                brushBuilder.sendMessage("expbuild.message.brush.brush_disable", true);
                return;
            }
            brushBuilder.executeBrush(brushBuilder, Material.HONEYCOMB, loc, p.getLocation());
        }

        if (it.getType() == conf.getTerraforming_tool_1()) {

            if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
                brushBuilder.executeBrush(brushBuilder, conf.getTerraforming_tool_1(), loc, p.getLocation());

            } else if (action.equals(Action.LEFT_CLICK_AIR)) {

                // défini un mask

                FaweAPI faweAPI = new FaweAPI(p);
                String blockMaskString = loc.getBlock().getType().toString();

                faweAPI.setMask(faweAPI.getMask(blockMaskString));
                brushBuilder.sendMessage("expbuild.message.brush.set_mask", true,new String[]{blockMaskString});
            }
        }


        if (it.getType() == conf.getTerraforming_tool_2()) {

            if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
                brushBuilder.executeBrush(brushBuilder, conf.getTerraforming_tool_2(), loc, p.getLocation());

            } else if (action.equals(Action.LEFT_CLICK_AIR)) {

                // défini un mask

                FaweAPI faweAPI = new FaweAPI(p);
                String blockMaskString = loc.getBlock().getType().toString();

                faweAPI.setMask(faweAPI.getMask(blockMaskString));
                brushBuilder.sendMessage("expbuild.message.brush.set_mask", true,new String[]{blockMaskString});
            }
        }
    }
}