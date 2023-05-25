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

public class GUI_Nether implements GUI_Builder {

	@Override
	public String name() {

		return "§8[§5§oEXP-Build§8] §l>§l§8 4";
	}

	@Override
	public int getSize() {

		return 6 * 9;
	}

	public void contents(@NotNull Player p, @NotNull Inventory guinether) {

		BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

		// Partie item page suivante
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta sk = (SkullMeta) item.getItemMeta();
		Executor.applyTextures(sk, UUID.randomUUID(), Gestion_GUI.RightArrow);
		item.setItemMeta(sk);
		ItemMeta item_meta = item.getItemMeta();
		item_meta.setDisplayName("Next page");
		item.setItemMeta(item_meta);
		guinether.setItem(53, item);

//Partie item  page precedente			
		ItemStack page_precedente = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta page_precedenteM = (SkullMeta) page_precedente.getItemMeta();
		Executor.applyTextures(page_precedenteM, UUID.randomUUID(), Gestion_GUI.LeftArrow);
		page_precedente.setItemMeta(page_precedenteM);
		ItemMeta item_meta1 = page_precedente.getItemMeta();
		item_meta1.setDisplayName("Previous page");
		page_precedente.setItemMeta(item_meta1);
		guinether.setItem(45, page_precedente);

//Partie item  RAYON			
		guinether.setItem(49,
				new ItemBuilder(Main.prefix, Material.HONEYCOMB, 1).addLore("Build the brush").build());
//Partie item  page suivante			
		guinether.setItem(48, new ItemBuilder("§o§l§7 Clear le brush", Material.SPECTRAL_ARROW, 1).build());
//Partie item  % air in brush 				
		guinether.setItem(52,
				new ItemBuilder("§7§l§o > " + brushBuilder.getAirBrush() + " %",
						Material.POTION, 1).build());
		// Partie increment RAYON
		guinether.setItem(50,
				new ItemBuilder("§l Rayon : " + brushBuilder.getRayon(),
						Material.BROWN_MUSHROOM, 1).build());

//Partie slot

		guinether.setItem(0, new ItemBuilder("Weeping_vines", Material.WEEPING_VINES, 1).build());
		guinether.setItem(1, new ItemBuilder("Crimson_fungus", Material.CRIMSON_FUNGUS, 1).build());
		guinether.setItem(2, new ItemBuilder("Crimson_roots", Material.CRIMSON_ROOTS, 1).build());
		guinether.setItem(18, new ItemBuilder("Twisting_vines", Material.TWISTING_VINES, 1).build());
		guinether.setItem(19, new ItemBuilder("Warped_fungus", Material.WARPED_FUNGUS, 1).build());
		guinether.setItem(20, new ItemBuilder("Warped_roots", Material.WARPED_ROOTS, 1).build());
		guinether.setItem(21, new ItemBuilder("Nether_sprouts", Material.NETHER_SPROUTS, 1).build());
		guinether.setItem(36, new ItemBuilder("Wither_rose", Material.WITHER_ROSE, 1).build());
	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack current, int slot) {

	}
}