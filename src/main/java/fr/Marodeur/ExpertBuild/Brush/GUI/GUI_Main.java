package fr.Marodeur.ExpertBuild.Brush.GUI;

import fr.Marodeur.ExpertBuild.Object.GUI_Builder;
import fr.Marodeur.ExpertBuild.Object.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GUI_Main implements GUI_Builder {

	@Override
	public String name() {
		return "§8[§5§oEXP-Build§8] §l>§l§8 Main";
	}

	@Override
	public int getSize() {
		return 3 * 9;
	}

	@Override
	public void contents(Player p, @NotNull Inventory EXP_Main) {

		EXP_Main.setItem(13, new ItemBuilder("EXP_Build Main", Material.HONEYCOMB, 1)
				.addLore("Welcome to Exp_Build x)")
				.addLore("Click on the items to discover all")
				.addLore("the possibilities")
				.build());

		EXP_Main.setItem(19,
				new ItemBuilder("EXP_Build Gmask", Material.SPONGE, 1)
						.addLore("Easily configure my gmask")
						.build());

		EXP_Main.setItem(15, new ItemBuilder("EXP_Build Organic", Material.BONE, 1)
				.addLore("humanoid organic generator")
				.build());

		EXP_Main.setItem(7,
				new ItemBuilder("EXP_Build Custom-Flower", Material.FIRE_CORAL, 1)
						.addLore("Work in progres")
						.build());

		EXP_Main.setItem(25,
				new ItemBuilder("EXP_Build Flower", Material.SUNFLOWER, 1)
						.addLore("Create your flower brush")
						.build());

		EXP_Main.setItem(1, new ItemBuilder("EXP_Build Leather", Material.LEATHER_CHESTPLATE, 1)
				.addLore("Custom Leather")
				.build());
	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
	}
}
