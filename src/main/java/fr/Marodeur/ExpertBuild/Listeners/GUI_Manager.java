package fr.Marodeur.ExpertBuild.Listeners;

import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.GUI_Builder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class GUI_Manager implements Listener {

	@EventHandler
	public void onClick(@NotNull InventoryClickEvent event) {

		if (event.getCurrentItem() == null)
			return;
		Main.getInstance().getRegisteredMenus().values().stream()
				.filter(menu -> event.getView().getTitle().contains("EXP-Build")).forEach(menu -> {
					Inventory inv = null;
					menu.onClick((Player) event.getWhoClicked(), inv, event.getCurrentItem(), event.getSlot());
					event.setCancelled(true);
				});
	}

	public void addMenu(GUI_Builder m) {
		Main.getInstance().getRegisteredMenus().put(m.getClass(), m);
	}

	public void open(Player p, Class<? extends GUI_Builder> gClass) {

		if (!Main.getInstance().getRegisteredMenus().containsKey(gClass))
			return;
		GUI_Builder menu = Main.getInstance().getRegisteredMenus().get(gClass);
		Inventory inv = Bukkit.createInventory(null, menu.getSize(), menu.name());
		menu.contents(p, inv);

		new BukkitRunnable() {

			@Override
			public void run() {
				p.openInventory(inv);
			}
		}.runTaskLater(Main.getInstance(), 1);
	}
}