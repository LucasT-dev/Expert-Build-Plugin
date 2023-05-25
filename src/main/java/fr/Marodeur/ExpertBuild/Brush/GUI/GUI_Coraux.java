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

public class GUI_Coraux implements GUI_Builder {

	@Override
	public String name() {
		return "§8[§5§oEXP-Build§8] §l>§l§8 2";
	}

	@Override
	public int getSize() {
		return 6 * 9;
	}

	public void contents(Player p, @NotNull Inventory guicoral) {

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
		guicoral.setItem(53, item);

		// Partie item page precedente
		// --------------------------------------------------------------------
		ItemStack page_precedente = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta page_precedenteM = (SkullMeta) page_precedente.getItemMeta();
		Executor.applyTextures(page_precedenteM, UUID.randomUUID(), Gestion_GUI.LeftArrow);
		page_precedente.setItemMeta(page_precedenteM);
		ItemMeta item_meta1 = page_precedente.getItemMeta();
		item_meta1.setDisplayName("Previous page");
		page_precedente.setItemMeta(item_meta1);
		guicoral.setItem(45, page_precedente);

		// Partie item RAYON
		guicoral.setItem(49,
				new ItemBuilder(Main.prefix, Material.HONEYCOMB, 1).addLore("Build the brush").build());
		// Partie item page suivante
		guicoral.setItem(48, new ItemBuilder("§o§l§7 Clear le brush", Material.SPECTRAL_ARROW, 1).build());
		// Partie item % air in brush
		guicoral.setItem(52,
				new ItemBuilder("§7§l§o > " + brushBuilder.getAirBrush() + " %",
						Material.POTION, 1).build());
		// Partie increment RAYON
		guicoral.setItem(50, new ItemBuilder("§l Rayon : " + brushBuilder.getRayon(),
				Material.BROWN_MUSHROOM, 1).build());

		guicoral.setItem(46,
				new ItemBuilder("§7§l§o Waterlog : " + brushBuilder.getWaterlog(),
						Material.WATER_BUCKET, 1).build());
		// Partie
		// slot----------------------------------------------------------------------------------

		guicoral.setItem(0, new ItemBuilder("Tube_coral", Material.TUBE_CORAL, 1).build());

		guicoral.setItem(1, new ItemBuilder("Fire_coral", Material.FIRE_CORAL, 1).build());

		guicoral.setItem(2, new ItemBuilder("Brain_coral", Material.BRAIN_CORAL, 1).build());

		guicoral.setItem(3, new ItemBuilder("Bubble_coral", Material.BUBBLE_CORAL, 1).build());

		guicoral.setItem(4, new ItemBuilder("Horn_coral", Material.HORN_CORAL, 1).build());

		guicoral.setItem(6, new ItemBuilder("Seagrass", Material.SEAGRASS, 1).build());

		guicoral.setItem(7, new ItemBuilder("Sea_pickle", Material.SEA_PICKLE, 1).build());

		guicoral.setItem(9, new ItemBuilder("Fire_coral", Material.DEAD_TUBE_CORAL, 1).build());

		guicoral.setItem(10, new ItemBuilder("Dead_fire_coral", Material.DEAD_FIRE_CORAL, 1).build());

		guicoral.setItem(11, new ItemBuilder("Dead_brain_coral", Material.DEAD_BRAIN_CORAL, 1).build());

		guicoral.setItem(12, new ItemBuilder("Dead_bubble_coral", Material.DEAD_BUBBLE_CORAL, 1).build());

		guicoral.setItem(13, new ItemBuilder("Dead_horn_coral", Material.DEAD_HORN_CORAL, 1).build());

		guicoral.setItem(15, new ItemBuilder("Kelp", Material.KELP, 1).build());

		guicoral.setItem(18, new ItemBuilder("Tube_coral_fan", Material.TUBE_CORAL_FAN, 1).build());

		guicoral.setItem(19, new ItemBuilder("Fire_coral_fan", Material.FIRE_CORAL_FAN, 1).build());

		guicoral.setItem(20, new ItemBuilder("Brain_coral_fan", Material.BRAIN_CORAL_FAN, 1).build());

		guicoral.setItem(21, new ItemBuilder("Bubble_coral_fan", Material.BUBBLE_CORAL_FAN, 1).build());

		guicoral.setItem(22, new ItemBuilder("Horn_coral_fan", Material.HORN_CORAL_FAN, 1).build());

		guicoral.setItem(27, new ItemBuilder("Fire_coral_fan", Material.DEAD_TUBE_CORAL_FAN, 1).build());

		guicoral.setItem(28, new ItemBuilder("Dead_fire_coral_fan", Material.DEAD_FIRE_CORAL_FAN, 1).build());

		guicoral.setItem(29, new ItemBuilder("Dead_brain_coral_fan", Material.DEAD_BRAIN_CORAL_FAN, 1).build());

		guicoral.setItem(30, new ItemBuilder("Dead_bubble_coral_fan", Material.DEAD_BUBBLE_CORAL_FAN, 1).build());

		guicoral.setItem(31, new ItemBuilder("Dead_horn_coral_fan", Material.DEAD_HORN_CORAL_FAN, 1).build());
	}

	public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
	}
}