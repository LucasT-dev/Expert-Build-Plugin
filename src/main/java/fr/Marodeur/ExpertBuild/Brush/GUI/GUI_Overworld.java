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

public class GUI_Overworld implements GUI_Builder {

	@Override
	public String name() {
		return "§8[§5§oEXP-Build§8] §l>§l§8 1";
	}

	@Override
	public int getSize() {
		return 6 * 9;
	}

	public void contents(@NotNull Player p, @NotNull Inventory gui_Overworld) {

		BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

//Partie item  page suivante --------------------------------------------------------------------				
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta sk = (SkullMeta) item.getItemMeta();
		Executor.applyTextures(sk, UUID.randomUUID(), Gestion_GUI.RightArrow);
		item.setItemMeta(sk);
		ItemMeta item_meta = item.getItemMeta();
		item_meta.setDisplayName("Next page");
		item.setItemMeta(item_meta);
		gui_Overworld.setItem(53, item);

//Partie item  page precedente --------------------------------------------------------------------				
		ItemStack page_precedente = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta page_precedenteM = (SkullMeta) page_precedente.getItemMeta();
		Executor.applyTextures(page_precedenteM, UUID.randomUUID(), Gestion_GUI.LeftArrow);
		page_precedente.setItemMeta(page_precedenteM);
		ItemMeta item_meta1 = page_precedente.getItemMeta();
		item_meta1.setDisplayName("Previous page");
		page_precedente.setItemMeta(item_meta1);
		gui_Overworld.setItem(45, page_precedente);

		// Partie item RAYON
		gui_Overworld.setItem(49,
				new ItemBuilder(Main.prefix, Material.HONEYCOMB, 1).addLore("Build the brush").build());

		// Partie item page suivante
		gui_Overworld.setItem(48, new ItemBuilder("§o§l§7 Clear le brush", Material.SPECTRAL_ARROW, 1).build());

		// Partie item % air in brush
		gui_Overworld.setItem(52,
				new ItemBuilder("§7§l§o > " + brushBuilder.getAirBrush() + " %",
						Material.POTION, 1).build());

		// Partie increment RAYON
		gui_Overworld.setItem(50,
				new ItemBuilder("§l Rayon : " + brushBuilder.getRayon(),
						Material.BROWN_MUSHROOM, 1).build());

		// Partie slot

//Partie item  1/2block  --------------------------------------------------------------------					
		ItemStack shears = new ItemStack(Material.SHEARS);
		ItemMeta shearsM = shears.getItemMeta();
		shearsM.setDisplayName("§7§l§o Half : " + brushBuilder.getUpperLower());
		shears.setItemMeta(shearsM);
		gui_Overworld.setItem(46, shears);

//Partie slot----------------------------------------------------------------------------------

		gui_Overworld.setItem(0, new ItemBuilder("Oak_sapling", Material.OAK_SAPLING, 1).build());

		gui_Overworld.setItem(1, new ItemBuilder("Spruce_sapling", Material.SPRUCE_SAPLING, 1).build());

		gui_Overworld.setItem(2, new ItemBuilder("Birch_sapling", Material.BIRCH_SAPLING, 1).build());

		gui_Overworld.setItem(3, new ItemBuilder("Jungle_sapling", Material.JUNGLE_SAPLING, 1).build());

		gui_Overworld.setItem(4, new ItemBuilder("Acacia_sapling", Material.ACACIA_SAPLING, 1).build());

		gui_Overworld.setItem(5, new ItemBuilder("Dark_oak_sapling", Material.DARK_OAK_SAPLING, 1).build());

		gui_Overworld.setItem(9, new ItemBuilder("Oak_leaves", Material.OAK_LEAVES, 1).build());

		gui_Overworld.setItem(10, new ItemBuilder("Spruce_leaves", Material.SPRUCE_LEAVES, 1).build());

		gui_Overworld.setItem(11, new ItemBuilder("Birch_leaves", Material.BIRCH_LEAVES, 1).build());

		gui_Overworld.setItem(12, new ItemBuilder("Jungle_leaves", Material.JUNGLE_LEAVES, 1).build());

		gui_Overworld.setItem(13, new ItemBuilder("Acacia_leaves", Material.ACACIA_LEAVES, 1).build());

		gui_Overworld.setItem(14, new ItemBuilder("Dark_oak_leaves", Material.DARK_OAK_LEAVES, 1).build());

		gui_Overworld.setItem(18, new ItemBuilder("Grass", Material.GRASS, 1).build());

		gui_Overworld.setItem(19, new ItemBuilder("Tall_grass", Material.TALL_GRASS, 1).build());

		gui_Overworld.setItem(20, new ItemBuilder("Fern", Material.FERN, 1).build());

		gui_Overworld.setItem(21, new ItemBuilder("Large_fern", Material.LARGE_FERN, 1).build());

		gui_Overworld.setItem(23, new ItemBuilder("Dead_bush", Material.DEAD_BUSH, 1).build());

		gui_Overworld.setItem(24, new ItemBuilder("Brown_mushroom", Material.BROWN_MUSHROOM, 1).build());

		gui_Overworld.setItem(25, new ItemBuilder("Red_mushroom", Material.RED_MUSHROOM, 1).build());

		gui_Overworld.setItem(26, new ItemBuilder("Cobweb", Material.COBWEB, 1).build());

		gui_Overworld.setItem(27, new ItemBuilder("Poppy", Material.POPPY, 1).build());

		gui_Overworld.setItem(28, new ItemBuilder("Dandelion", Material.DANDELION, 1).build());

		gui_Overworld.setItem(29, new ItemBuilder("Azure_bluet", Material.AZURE_BLUET, 1).build());

		gui_Overworld.setItem(30, new ItemBuilder("Blue_orchid", Material.BLUE_ORCHID, 1).build());

		gui_Overworld.setItem(31, new ItemBuilder("Cornflower", Material.CORNFLOWER, 1).build());

		gui_Overworld.setItem(32, new ItemBuilder("Allium", Material.ALLIUM, 1).build());

		gui_Overworld.setItem(33, new ItemBuilder("Oxeye_daisy", Material.OXEYE_DAISY, 1).build());

		gui_Overworld.setItem(34, new ItemBuilder("Lily_of_the_valley", Material.LILY_OF_THE_VALLEY, 1).build());

		gui_Overworld.setItem(36, new ItemBuilder("White_tulip", Material.WHITE_TULIP, 1).build());

		gui_Overworld.setItem(37, new ItemBuilder("Pink_tulip", Material.PINK_TULIP, 1).build());

		gui_Overworld.setItem(38, new ItemBuilder("Orange_tulip", Material.ORANGE_TULIP, 1).build());

		gui_Overworld.setItem(39, new ItemBuilder("Red_tulip", Material.RED_TULIP, 1).build());

		gui_Overworld.setItem(41, new ItemBuilder("Peony", Material.PEONY, 1).build());

		gui_Overworld.setItem(42, new ItemBuilder("Lilac", Material.LILAC, 1).build());

		gui_Overworld.setItem(43, new ItemBuilder("Rose_bush", Material.ROSE_BUSH, 1).build());

		gui_Overworld.setItem(44, new ItemBuilder("Sunflower", Material.SUNFLOWER, 1).build());
	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
	}
}