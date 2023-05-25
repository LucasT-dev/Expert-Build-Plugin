package fr.Marodeur.ExpertBuild.Brush.GUI;

import fr.Marodeur.ExpertBuild.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Switch_Armure implements Listener {

	public static int RED = 2;
	public static int GREEN = 2;
	public static int BLUE = 2;

	@EventHandler
	public void InventoryEvent(InventoryClickEvent event) {

		if (event.getView().getTitle().equalsIgnoreCase("§8[§5§oEXP-Build§8] §l>§l§8 Armure")) {

			Player p = (Player) event.getWhoClicked();
			ItemStack current = event.getCurrentItem();
			// Inventory open = event.getClickedInventory();
			if (current == null)
				return;

			switch (current.getType()) {
			case RED_DYE:
				event.setCancelled(true);
				if (current != null && event.getClick().equals(ClickType.LEFT)) {
					RED += 1;
					if (RED < 0)
						RED = 0;
				}
				if (current != null && event.getClick().equals(ClickType.RIGHT)) {
					RED -= 1;
					if (RED > 255)
						RED = 255;
				}

				Main.getInstance().getGUI_Manager().open(p, GUI_Armure.class);
				break;
			case GREEN_DYE:
				event.setCancelled(true);
				if (current != null && event.getClick().equals(ClickType.LEFT)) {
					GREEN += 1;
					if (GREEN > 255)
						BLUE = 255;
				}
				if (current != null && event.getClick().equals(ClickType.RIGHT)) {
					GREEN -= 1;
					if (GREEN < 0)
						GREEN = 0;
				}
				Main.getInstance().getGUI_Manager().open(p, GUI_Armure.class);
				break;
			case BLUE_DYE:
				event.setCancelled(true);
				if (current != null && event.getClick().equals(ClickType.LEFT)) {
					BLUE += 1;
					if (BLUE > 255)
						BLUE = 255;
				}
				if (current != null && event.getClick().equals(ClickType.RIGHT)) {
					BLUE -= 1;
					if (BLUE < 0)
						BLUE = 0;
				}
				Main.getInstance().getGUI_Manager().open(p, GUI_Armure.class);
				break;
			case LEATHER_HELMET:
				event.setCancelled(true);
				p.getInventory().setItem(5, event.getCurrentItem());
				break;
			case LEATHER_CHESTPLATE:
				event.setCancelled(true);
				p.getInventory().setItem(6, event.getCurrentItem());
				break;
			case LEATHER_LEGGINGS:
				event.setCancelled(true);
				p.getInventory().setItem(7, event.getCurrentItem());
				break;
			case LEATHER_BOOTS:
				event.setCancelled(true);
				p.getInventory().setItem(8, event.getCurrentItem());
				break;
			default:
				break;
			}
		}
	}

	@EventHandler
	public void shiftClickArmure(InventoryClickEvent event) {

		if (!event.getView().getTitle().equalsIgnoreCase("§8[§5§oEXP-Build§8] §l>§l§8 Armure")) {
			return;
		}
		ItemStack it = event.getCurrentItem();
		Player p = (Player) event.getWhoClicked();
		if (it != null && it.getType() == Material.RED_DYE && event.getClick().equals(ClickType.SHIFT_LEFT)) {
			RED += 10;
			if (RED > 255)
				RED = 255;
		}
		if (it != null && it.getType() == Material.RED_DYE && event.getClick().equals(ClickType.SHIFT_RIGHT)) {
			RED -= 10;
			if (RED < 0)
				RED = 0;
		}

		if (it != null && it.getType() == Material.GREEN_DYE && event.getClick().equals(ClickType.SHIFT_LEFT)) {
			GREEN += 10;
			if (GREEN > 255)
				GREEN = 255;

		}
		if (it != null && it.getType() == Material.GREEN_DYE && event.getClick().equals(ClickType.SHIFT_RIGHT)) {
			GREEN -= 10;
			if (GREEN < 0)
				GREEN = 0;
		}

		if (it != null && it.getType() == Material.BLUE_DYE && event.getClick().equals(ClickType.SHIFT_LEFT)) {
			BLUE += 10;
			if (BLUE > 255)
				BLUE = 255;
		}
		if (it != null && it.getType() == Material.BLUE_DYE && event.getClick().equals(ClickType.SHIFT_RIGHT)) {
			BLUE -= 10;
			if (BLUE < 0)
				BLUE = 0;
		}
		Main.getInstance().getGUI_Manager().open(p, GUI_Armure.class);
	}
}
