package fr.Marodeur.ExpertBuild.Listeners;

import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.Configuration;
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
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

        if (it == null || brushBuilder.getBrushType().getBclass() == null) return;

        loc = p.getTargetBlock(null, conf.getMax_brush_distance()).getLocation().clone();

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {

            if (it.getType() == Material.HONEYCOMB) {

                brushBuilder.executeHoneyBrush(brushBuilder, loc);
            }

            if (it.getType() == conf.getTerraforming_tool_1()) {

                brushBuilder.executeArrowBrush(brushBuilder, loc, p.getLocation());
            }

            if (it.getType() == conf.getTerraforming_tool_2()) {

                brushBuilder.executeGunPowderBrush(brushBuilder, loc, p.getLocation());
            }
        }
    }
}

