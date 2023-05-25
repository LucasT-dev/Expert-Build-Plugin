package fr.Marodeur.ExpertBuild.Brush.GUI;

import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.GUI_Builder;
import fr.Marodeur.ExpertBuild.Object.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GUI_Food implements GUI_Builder {

	@Override
	public String name() {

		return "§8[§5§oEXP-Build§8] §l>§l§8 3";
	}

	@Override
	public int getSize() {
		return 6 * 9;
	}

	public void contents(Player p, @NotNull Inventory gui_food) {

		BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

		// Partie item page suivante
		// --------------------------------------------------------------------
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta sk = (SkullMeta) item.getItemMeta();
		Executor.applyTextures(sk, UUID.randomUUID(), Gestion_GUI.RightArrow);
		item.setItemMeta(sk);
		ItemMeta item_meta = item.getItemMeta();
		item_meta.setDisplayName("Next page");
		item.setItemMeta(item_meta);
		gui_food.setItem(53, item);

//Partie item  page precedente --------------------------------------------------------------------				
		ItemStack page_precedente = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta page_precedenteM = (SkullMeta) page_precedente.getItemMeta();
		Executor.applyTextures(page_precedenteM, UUID.randomUUID(), Gestion_GUI.LeftArrow);
		page_precedente.setItemMeta(page_precedenteM);
		ItemMeta item_meta1 = page_precedente.getItemMeta();
		item_meta1.setDisplayName("Previous page");
		page_precedente.setItemMeta(item_meta1);
		gui_food.setItem(45, page_precedente);

		// Partie item RAYON
		gui_food.setItem(49,
				new ItemBuilder(Main.prefix, Material.HONEYCOMB, 1).addLore("Build the brush").build());

		// Partie item page suivante
		gui_food.setItem(48, new ItemBuilder("§o§l§7 Clear le brush", Material.SPECTRAL_ARROW, 1).build());

		// Partie item % air in brush
		gui_food.setItem(52,
				new ItemBuilder("§7§l§o > " + brushBuilder.getAirBrush() + " %",
						Material.POTION, 1).build());

		// Partie increment RAYON
		gui_food.setItem(50, new ItemBuilder("§l Rayon : " + brushBuilder.getRayon(),
				Material.BROWN_MUSHROOM, 1).build());

		//partie age
		gui_food.setItem(46, new ItemBuilder("§l Age : " + brushBuilder.getYears(),
				Material.LEATHER, 1).build());

//partie slot--------------------------------------------------------------------------------------
		gui_food.setItem(0, new ItemBuilder("Wheat", Material.WHEAT, 1).addLore("§1 Max Year = 7").build());

		gui_food.setItem(2, new ItemBuilder("Carrot", Material.CARROT, 1).addLore("§1 Max Year = 7").build());

		gui_food.setItem(4, new ItemBuilder("Potato", Material.POTATO, 1).addLore("§1 Max Year = 7").build());

		gui_food.setItem(6, new ItemBuilder("Beetroot", Material.BEETROOT, 1).addLore("§1 Max Year = 3").build());

		gui_food.setItem(8,
				new ItemBuilder("Sweet_berries", Material.SWEET_BERRIES, 1).addLore("§1 Max Year = 3").build());

		gui_food.setItem(18,
				new ItemBuilder("Pumpkin_seeds", Material.PUMPKIN_SEEDS, 1).addLore("§1 Max Year = 7").build());

		gui_food.setItem(20,
				new ItemBuilder("Melon_seeds", Material.MELON_SEEDS, 1).addLore("§1 Max Year = 7").build());

		gui_food.setItem(27, new ItemBuilder("Pumpkin", Material.PUMPKIN, 1).build());

		gui_food.setItem(29, new ItemBuilder("Melon", Material.MELON, 1).build());

		gui_food.setItem(22,
				new ItemBuilder("Nether_wart", Material.NETHER_WART, 1).addLore("§1 Max Year = 3").build());
	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
	}
}