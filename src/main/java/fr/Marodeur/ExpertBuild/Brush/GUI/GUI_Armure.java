package fr.Marodeur.ExpertBuild.Brush.GUI;

import fr.Marodeur.ExpertBuild.Object.GUI_Builder;
import fr.Marodeur.ExpertBuild.Object.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class GUI_Armure implements GUI_Builder {

	@Override
	public String name() {
		return "§8[§5§oEXP-Build§8] §l>§l§8 Armure";
	}

	@Override
	public int getSize() {
		return 6 * 9;
	}

	@Override
	public void contents(Player p, Inventory GUI_Armure) {

		ItemStack LeatherHelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta metaHelmet = (LeatherArmorMeta) LeatherHelmet.getItemMeta();
		metaHelmet.setColor(Color.fromRGB(Switch_Armure.RED, Switch_Armure.GREEN, Switch_Armure.BLUE));
		LeatherHelmet.setItemMeta(metaHelmet);
		GUI_Armure.setItem(10, LeatherHelmet);

		/*
		 * GUI_Armure.setItem(10, new ItemBuilder("§8RGB Color",
		 * Material.LEATHER_HELMET, 1)
		 * .setLeatherArmorColor(Color.fromBGR(Switch_Armure.RED,Switch_Armure.GREEN,
		 * Switch_Armure.BLUE)) .addLore("" + Switch_Armure.RED) .build());
		 */

		ItemStack LeatherChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta metaChestplate = (LeatherArmorMeta) LeatherChestplate.getItemMeta();
		metaChestplate.setColor(Color.fromRGB(Switch_Armure.RED, Switch_Armure.GREEN, Switch_Armure.BLUE));
		LeatherChestplate.setItemMeta(metaChestplate);
		GUI_Armure.setItem(19, LeatherChestplate);

		ItemStack LeatherLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta metaLeggings = (LeatherArmorMeta) LeatherLeggings.getItemMeta();
		metaLeggings.setColor(Color.fromRGB(Switch_Armure.RED, Switch_Armure.GREEN, Switch_Armure.BLUE));
		LeatherLeggings.setItemMeta(metaLeggings);
		GUI_Armure.setItem(28, LeatherLeggings);

		ItemStack LeatherBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta metaBoots = (LeatherArmorMeta) LeatherBoots.getItemMeta();
		metaBoots.setColor(Color.fromRGB(Switch_Armure.RED, Switch_Armure.GREEN, Switch_Armure.BLUE));
		LeatherBoots.setItemMeta(metaBoots);
		GUI_Armure.setItem(37, LeatherBoots);

		/*
		 * GUI_Armure.setItem(10, new ItemBuilder("ENABLE / DISABLE",
		 * Material.LEATHER_HELMET, 1) .build());
		 * 
		 * GUI_Armure.setItem(19, new ItemBuilder("ENABLE / DISABLE",
		 * Material.LEATHER_CHESTPLATE, 1) .build());
		 * 
		 * GUI_Armure.setItem(28, new ItemBuilder("ENABLE / DISABLE",
		 * Material.LEATHER_LEGGINGS, 1) .build());
		 * 
		 * GUI_Armure.setItem(37, new ItemBuilder("ENABLE / DISABLE",
		 * Material.LEATHER_BOOTS, 1) .build());
		 */

		GUI_Armure.setItem(40,
				new ItemBuilder("§8RGB Color", Material.RED_DYE, 1).addLore("" + Switch_Armure.RED).build());

		GUI_Armure.setItem(41,
				new ItemBuilder("§8RGB Color", Material.GREEN_DYE, 1).addLore("" + Switch_Armure.GREEN).build());

		GUI_Armure.setItem(42,
				new ItemBuilder("§8RGB Color", Material.BLUE_DYE, 1).addLore("" + Switch_Armure.BLUE).build());
	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
	}
}
