package fr.Marodeur.ExpertBuild.Brush.GUI;

import fr.Marodeur.ExpertBuild.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Switch_Main implements Listener {

	@EventHandler
	public void InteractInv(@NotNull InventoryClickEvent event) {

		if (event.getView().getTitle().equalsIgnoreCase("§8[§5§oEXP-Build§8] §l>§l§8 Main")) {

			Player p = (Player) event.getWhoClicked();
			ItemStack current = event.getCurrentItem();
			if (current == null)
				return;

			switch (current.getType()) {
			case HONEYCOMB:
				event.setCancelled(true);
				break;
			case BONE:
				event.setCancelled(true);
				Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
			case FIRE_CORAL:
				event.setCancelled(true);
				break;
			case SPONGE:
				//Main.getInstance().getGUI_Manager().open(p, GUI_Gmask.class);
				event.setCancelled(true);
				break;
			case SUNFLOWER:
				event.setCancelled(true);
				Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
				break;
			case LEATHER_CHESTPLATE:
				event.setCancelled(true);
				Main.getInstance().getGUI_Manager().open(p, GUI_Armure.class);
			default:
				break;
			}
		}
	}
}
