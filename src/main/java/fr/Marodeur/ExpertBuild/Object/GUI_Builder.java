package fr.Marodeur.ExpertBuild.Object;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface GUI_Builder {

	String name();

	int getSize();

	void contents(Player player, Inventory inv);

	void onClick(Player player, Inventory inv, ItemStack current, int slot);
}
