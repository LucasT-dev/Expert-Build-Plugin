package fr.Marodeur.ExpertBuild.Brush.GUI;

import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Switch_Overworld implements Listener {

	@EventHandler
	public void onClick(@NotNull InventoryClickEvent event) {

		if (event.getView().getTitle().equalsIgnoreCase("§8[§5§oEXP-Build§8] §l>§l§8 1")) {

			Player p = (Player) event.getWhoClicked();
			ItemStack current = event.getCurrentItem();
			BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

			if (current == null) return;

			switch (event.getSlot()) {
				case 53 -> Main.getInstance().getGUI_Manager().open(p, GUI_Coraux.class);
				case 45 -> {
					if (event.getClick().equals(ClickType.SHIFT_LEFT)) {
						Main.getInstance().getGUI_Manager().open(p, GUI_Main.class);
						break;
					}
					Main.getInstance().getGUI_Manager().open(p, GUI_Nether.class);
				}
				case 50 -> {
					if (event.getClick().equals(ClickType.RIGHT)) {

						Executor.Rayon(brushBuilder, -1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {

						Executor.Rayon(brushBuilder, -10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
						return;
					}
					if (event.getClick().equals(ClickType.LEFT)) {

						Executor.Rayon(brushBuilder, +1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_LEFT)) {

						Executor.Rayon(brushBuilder, +10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
						return;
					}
				}
				case 24 -> {
					event.setCancelled(true);
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "39,");
					Gestion_GUI.Select(event, event.getSlot());
				}
				default -> {
				}
			}

			switch (current.getType()) {
			case HONEYCOMB:
				Executor.brush(p, brushBuilder);
				break;

			case SPECTRAL_ARROW:
				Executor.clear_brush(p, brushBuilder);
				break;

			case POTION:

				if (event.getClick().equals(ClickType.LEFT)) {

					Executor.AirBrush(brushBuilder, 1);
					Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
				}
				if (event.getClick().equals(ClickType.SHIFT_LEFT)) {

					Executor.AirBrush(brushBuilder, 10);
					Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
					return;
				}

				if (event.getClick().equals(ClickType.RIGHT)) {

					Executor.AirBrush(brushBuilder, -1);
					Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
				}
				if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {

					Executor.AirBrush(brushBuilder, -10);
					Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
					return;
				}
			case SHEARS:
				if (brushBuilder.getUpperLower().equalsIgnoreCase("upper")) {
					brushBuilder.setUpperLower("lower");
				} else {
					brushBuilder.setUpperLower("upper");
				}
				brushBuilder.Build(brushBuilder);
				Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
				break;

			case OAK_SAPLING:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "6")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case SPRUCE_SAPLING:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "6:1,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case BIRCH_SAPLING:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "6:2,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case JUNGLE_SAPLING:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "6:3,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case ACACIA_SAPLING:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "6:4,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case DARK_OAK_SAPLING:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "6:5,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;

			case OAK_LEAVES:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "18,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case SPRUCE_LEAVES:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "18:1,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case BIRCH_LEAVES:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "18:2,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case JUNGLE_LEAVES:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "18:3,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case ACACIA_LEAVES:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "161,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case DARK_OAK_LEAVES:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "161:1,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;

			case GRASS:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "31:1,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case TALL_GRASS:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "175:2[half=" + brushBuilder.getUpperLower() + "],")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case FERN:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "31:2,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case LARGE_FERN:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "175:3[half=" + brushBuilder.getUpperLower() + "],")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case DEAD_BUSH:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "32,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case RED_MUSHROOM:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "40,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case COBWEB:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "cobweb,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;

			case POPPY:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "38,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case DANDELION:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "37,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case AZURE_BLUET:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "38:3,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case BLUE_ORCHID:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "38:1,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case CORNFLOWER:// minecraft:cornflower
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "cornflower,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case ALLIUM:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "38:2,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case OXEYE_DAISY:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "38:8,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case LILY_OF_THE_VALLEY:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "lily_of_the_valley,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case WHITE_TULIP:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "38:6,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case PINK_TULIP:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "38:7,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case ORANGE_TULIP:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "38:5,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case RED_TULIP:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "38:4,")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case PEONY:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "peony[half=" + brushBuilder.getUpperLower() + "],")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case LILAC:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "lilac[half=" + brushBuilder.getUpperLower() + "],")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case ROSE_BUSH:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "rose_bush[half=" + brushBuilder.getUpperLower() + "],")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			case SUNFLOWER:
				brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "sunflower[half=" + brushBuilder.getUpperLower() + "],")
						.Build(brushBuilder);
				Gestion_GUI.Select(event, event.getSlot());
				break;
			default:
				break;
			}
		}
	}
}