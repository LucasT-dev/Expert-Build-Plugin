package fr.Marodeur.ExpertBuild.Listeners;

import fr.Marodeur.ExpertBuild.GUI.MainGUI;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.Configuration;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;
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
        Configuration conf = Main.getInstance().getConfig();
        MessageBuilder msg = Main.getInstance().getMessageConfig();
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);

        if (brushBuilder == null) {
            return;
        }

        if (it == null || brushBuilder.getBrushType().getBclass() == null) return;

        loc = p.getTargetBlock(null, conf.getMax_brush_distance()).getLocation().clone();

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {

            if (it.getType() == Material.HONEYCOMB) {

                if (!brushBuilder.getEnable()) {
                    brushBuilder.sendMessage(msg.getBrushDisable());
                    return;
                }
                brushBuilder.executeHoneyBrush(brushBuilder, loc);
            }
            if (it.getType() == conf.getTerraforming_tool_1()) {

                brushBuilder.executeArrowBrush(brushBuilder, loc, p.getLocation());
            }

            if (it.getType() == conf.getTerraforming_tool_2()) {

                brushBuilder.executeGunPowderBrush(brushBuilder, loc, p.getLocation());
            }
        }

        if (action == Action.LEFT_CLICK_AIR && it.getType() == Material.HONEYCOMB) {

            if (!Main.containsBrushBuilder(p)) {
                p.sendMessage(Main.prefix + "You are not registered, execute: /fw register");
                return;
            }

            if (!p.hasPermission("exp.gui.main")) return;

            new MainGUI().openMainInventory(p);
        }
    }
}

