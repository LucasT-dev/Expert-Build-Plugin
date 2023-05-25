package fr.Marodeur.ExpertBuild.Brush.GUI;

import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.GOHA_Builder;
import fr.Marodeur.ExpertBuild.Object.GUI_Builder;
import fr.Marodeur.ExpertBuild.Object.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class GUI_GOHA implements GUI_Builder {

	private final String X = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzM4YWIxNDU3NDdiNGJkMDljZTAzNTQzNTQ5NDhjZTY5ZmY2ZjQxZDllMDk4YzY4NDhiODBlMTg3ZTkxOSJ9fX0=";
	private final String Y = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTcxMDcxYmVmNzMzZjQ3NzAyMWIzMjkxZGMzZDQ3ZjBiZGYwYmUyZGExYjE2NWExMTlhOGZmMTU5NDU2NyJ9fX0=";

	@Override
	public String name() {
		return "§8[§5§oEXP-Build§8] §l>§l§8 Organic";
	}

	@Override
	public int getSize() {
		return 6 * 9;
	}

	@Override
	public void contents(@NotNull Player p, @NotNull Inventory Goha) {

		GOHA_Builder goha_builder = GOHA_Builder.getGOHABuilder(p);

		Goha.setItem(0, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(1, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(3, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(4, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(6, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(7, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(8, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(9, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(13, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(19, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(21, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(26, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(27, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(28, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(30, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(31, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(33, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(34, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(35, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(36, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(38, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(40, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(45, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(47, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(49, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(51, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(52, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(53, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());

		Goha.setItem(5, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(14, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(23, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(32, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(41, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1).build());
		Goha.setItem(50, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1).build());

		// Hauteur Orga
		Goha.setItem(42, new ItemBuilder("Height", Material.BROWN_MUSHROOM, 1)
				.addLore("Organic Height : " + goha_builder.getHeight())
				.build());

		// material
		Goha.setItem(43,
				new ItemBuilder("Material",
						Material.getMaterial(goha_builder.getMaterialOrga()), 1)
								.addLore("Material : " + goha_builder.getMaterialOrga())
								.build());

		//tete
		Goha.setItem(2,
				new ItemBuilder(Main.prefix, Material.PLAYER_HEAD, 1)
						.addEnchant(Enchantment.LUCK, 1)
						.addLore("Right Click, Left click or Shift Click")
						.addLore("Head : §7" + goha_builder.getHead())
						.addLore("Pitch : §7" + goha_builder.getHeadXAngle())
						.addLore("Yaw : §7" + goha_builder.getHeadYAngle())
						.build());

		//bras
		Goha.setItem(10,
				new ItemBuilder(Main.prefix, Material.BONE, 1)
						.addEnchant(Enchantment.LUCK, 1)
						.addLore("Right Click, Left click or Shift Click")
						.addLore("Right Arm : §7" + goha_builder.getArmD())
						.addLore("Pitch : §7" + goha_builder.getArmDXAngle())
						.addLore("Yaw : §7" + goha_builder.getArmDYAngle())
						.build());

		Goha.setItem(11, new ItemBuilder(Main.prefix, Material.BONE, 1).build());

		Goha.setItem(12,
				new ItemBuilder(Main.prefix, Material.BONE, 1)
						.addEnchant(Enchantment.LUCK, 1)
						.addLore("Right Click, Left click or Shift Click")
						.addLore("Left Arm : §7" + goha_builder.getArmG())
						.addLore("Pitch : §7" + goha_builder.getArmGXAngle())
						.addLore("Yaw : §7" + goha_builder.getArmGYAngle())
						.build());
		// Avant bras
		Goha.setItem(18,
				new ItemBuilder(Main.prefix, Material.BONE, 1)
						.addEnchant(Enchantment.LUCK, 1)
						.addLore("Right Click, Left click or Shift Click")
						.addLore("Right Fore Arm : §7" + goha_builder.getForeArmD())
						.addLore("Pitch : §7" + goha_builder.getForeArmDXAngle())
						.addLore("Yaw : §7" + goha_builder.getForeArmDYAngle())
						.build());

		Goha.setItem(22,
				new ItemBuilder(Main.prefix, Material.BONE, 1)
						.addEnchant(Enchantment.LUCK, 1)
						.addLore("Right Click, Left click or Shift Click")
						.addLore("Left Fore Arm : §7" + goha_builder.getForeArmG())
						.addLore("Pitch : §7" + goha_builder.getForeArmGXAngle())
						.addLore("Yaw : §7" + goha_builder.getForeArmGYAngle())
						.build());

		// torse
		Goha.setItem(20,
				new ItemBuilder(Main.prefix, Material.BONE, 1)
						.addEnchant(Enchantment.LUCK, 1)
						.addLore("Right Click, Left click or Shift Click")
						.addLore("Torso : §7" + goha_builder.getTorso())
						.addLore("Pitch : §7" + goha_builder.getTorsoXAngle())
						.addLore("Yaw : §7" + goha_builder.getTorsoYAngle())
						.build());

		Goha.setItem(29, new ItemBuilder(Main.prefix, Material.BONE, 1).build());
		// cuisse
		Goha.setItem(37,
				new ItemBuilder(Main.prefix, Material.BONE, 1)
						.addEnchant(Enchantment.LUCK, 1)
						.addLore("Right Click, Left click or Shift Click")
						.addLore("Right Leg : §7" + goha_builder.getLegD())
						.addLore("Pitch : §7" + goha_builder.getLegDXAngle())
						.addLore("Yaw : §7" + goha_builder.getLegDYAngle())
						.build());

		Goha.setItem(39,
				new ItemBuilder(Main.prefix, Material.BONE, 1)
						.addEnchant(Enchantment.LUCK, 1)
						.addLore("Right Click, Left click or Shift Click")
						.addLore("Left Leg : §7" + goha_builder.getLegG())
						.addLore("Pitch : §7" + goha_builder.getLegGXAngle())
						.addLore("Yaw : §7" + goha_builder.getLegGYAngle())
						.build());
		// tibia
		Goha.setItem(46,
				new ItemBuilder(Main.prefix, Material.BONE, 1)
						.addEnchant(Enchantment.LUCK, 1)
						.addLore("Right Click, Left click or Shift Click")
						.addLore("Right Tibia : §7" + goha_builder.getTibiaD())
						.addLore("Pitch : §7" + goha_builder.getTibiaDXAngle())
						.addLore("Yaw : §7" + goha_builder.getTibiaDYAngle())
						.build());

		Goha.setItem(48,
				new ItemBuilder(Main.prefix, Material.BONE, 1)
						.addEnchant(Enchantment.LUCK, 1)
						.addLore("Right Click, Left click or Shift Click")
						.addLore("Left Tibia : §7" + goha_builder.getTibiaG())
						.addLore("Pitch : §7" + goha_builder.getTibiaGXAngle())
						.addLore("Yaw : §7" + goha_builder.getTibiaGYAngle())
						.build());
		// XY
		if (goha_builder.getCommutateur() == 0) {
			Goha.setItem(15, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
			Goha.setItem(16, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
			Goha.setItem(17, new ItemBuilder("", Material.GREEN_STAINED_GLASS_PANE, 1).build());
		}
		if (goha_builder.getCommutateur() == 1) {
			Goha.setItem(15, new ItemBuilder("", Material.GREEN_STAINED_GLASS_PANE, 1).build());
			Goha.setItem(16, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
			Goha.setItem(17, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
		}

		if (goha_builder.getCommutateur() == 2) {
			Goha.setItem(15, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
			Goha.setItem(16, new ItemBuilder("", Material.GREEN_STAINED_GLASS_PANE, 1).build());
			Goha.setItem(17, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
		}

		Goha.setItem(26, new ItemBuilder("Member configuration", Material.BARRIER, 1)
						.addLore("click for enable / disable member")
						.build());

		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta sk = (SkullMeta) item.getItemMeta();
		Executor.applyTextures(sk, UUID.randomUUID(), X);
		item.setItemMeta(sk);
		ItemMeta item_meta = item.getItemMeta();
		item_meta.setDisplayName("Pitch angle configuration");
		item_meta.setLore(List.of("Click for change pitch angle"));
		item.setItemMeta(item_meta);
		Goha.setItem(24, item);

		ItemStack item1 = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta sk1 = (SkullMeta) item1.getItemMeta();
		Executor.applyTextures(sk1, UUID.randomUUID(), Y);
		item1.setItemMeta(sk1);
		ItemMeta item_meta1 = item1.getItemMeta();
		item_meta1.setDisplayName("Yaw angle configuration");
		item_meta1.setLore(List.of("Click for change yaw angle"));
		item1.setItemMeta(item_meta1);
		Goha.setItem(25, item1);
	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack current, int slot) {

	}
}
