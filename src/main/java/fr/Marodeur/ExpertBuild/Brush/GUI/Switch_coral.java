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

public class Switch_coral implements Listener {

	@EventHandler
	public void onClick(@NotNull InventoryClickEvent event) {

		Player p = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

		if (current == null)
			return;
		if (event.getView().getTitle().equalsIgnoreCase("§8[§5§oEXP-Build§8] §l>§l§8 2")) {

			switch (event.getSlot()) {
				case 53 -> Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);

				case 45 -> {
					if (event.getClick().equals(ClickType.SHIFT_LEFT)) {
						Main.getInstance().getGUI_Manager().open(p, GUI_Main.class);
						break;
					}
					Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
				}
				case 50 -> {
					if (event.getClick().equals(ClickType.RIGHT)) {

						Executor.Rayon(brushBuilder, -1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Coraux.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {

						Executor.Rayon(brushBuilder, -10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Coraux.class);
						return;
					}
					if (event.getClick().equals(ClickType.LEFT)) {

						Executor.Rayon(brushBuilder, +1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Coraux.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_LEFT)) {

						Executor.Rayon(brushBuilder, +10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Coraux.class);
						return;
					}
				}
				default -> {
				}
			}

			switch (current.getType()) {
				case HONEYCOMB -> Executor.brush(p, brushBuilder);

				case SPECTRAL_ARROW -> Executor.clear_brush(p, brushBuilder);

				case POTION -> {
					if (event.getClick().equals(ClickType.LEFT)) {

						Executor.AirBrush(brushBuilder, 1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Coraux.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_LEFT)) {

						Executor.AirBrush(brushBuilder, 10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Coraux.class);
						return;
					}
					if (event.getClick().equals(ClickType.RIGHT)) {

						Executor.AirBrush(brushBuilder, -1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Coraux.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {

						Executor.AirBrush(brushBuilder, -10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Coraux.class);
						return;
					}
				}
				case WATER_BUCKET -> {
					if (brushBuilder.getWaterlog().equals(false)) {
						brushBuilder.setWaterlog(true);

					} else {
						brushBuilder.setWaterlog(false);
					}
					brushBuilder.Build(brushBuilder);
					Main.getInstance().getGUI_Manager().open(p, GUI_Coraux.class);
				}
				case TUBE_CORAL -> {
					brushBuilder.setFlowerMaterial("tube_coral[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case FIRE_CORAL -> {
					brushBuilder.setFlowerMaterial("fire_coral[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case BRAIN_CORAL -> {
					brushBuilder.setFlowerMaterial("brain_coral[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case BUBBLE_CORAL -> {
					brushBuilder.setFlowerMaterial("bubble_coral[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case HORN_CORAL -> {
					brushBuilder.setFlowerMaterial("horn_coral[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case SEAGRASS -> {
					brushBuilder.setFlowerMaterial("seagrass,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case SEA_PICKLE -> {
					brushBuilder.setFlowerMaterial("sea_pickle[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case DEAD_TUBE_CORAL -> {
					brushBuilder.setFlowerMaterial("dead_tube_coral[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case DEAD_FIRE_CORAL -> {
					brushBuilder.setFlowerMaterial("dead_fire_coral[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case DEAD_BRAIN_CORAL -> {
					brushBuilder.setFlowerMaterial("dead_brain_coral[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case DEAD_BUBBLE_CORAL -> {
					brushBuilder.setFlowerMaterial("dead_bubble_coral[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case DEAD_HORN_CORAL -> {
					brushBuilder.setFlowerMaterial("dead_horn_coral[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case KELP -> {
					brushBuilder.setFlowerMaterial("kelp,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case TUBE_CORAL_FAN -> {
					brushBuilder.setFlowerMaterial("tube_coral_fan[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case FIRE_CORAL_FAN -> {
					brushBuilder.setFlowerMaterial("fire_coral_fan[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case BRAIN_CORAL_FAN -> {
					brushBuilder.setFlowerMaterial("brain_coral_fan[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case BUBBLE_CORAL_FAN -> {
					brushBuilder.setFlowerMaterial("bubble_coral_fan[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case HORN_CORAL_FAN -> {
					brushBuilder.setFlowerMaterial("horn_coral_fan[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case DEAD_TUBE_CORAL_FAN -> {
					brushBuilder.setFlowerMaterial("dead_tube_coral_fan[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case DEAD_FIRE_CORAL_FAN -> {
					brushBuilder.setFlowerMaterial("dead_fire_coral_fan[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case DEAD_BRAIN_CORAL_FAN -> {
					brushBuilder.setFlowerMaterial("dead_brain_coral_fan[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case DEAD_BUBBLE_CORAL_FAN -> {
					brushBuilder.setFlowerMaterial("dead_bubble_coral_fan[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case DEAD_HORN_CORAL_FAN -> {
					brushBuilder.setFlowerMaterial("dead_horn_coral_fan[waterlogged=" + brushBuilder.getWaterlog() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				default -> {
				}
			}
		}
	}
}
