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

public class Switch_nether implements Listener {

	@EventHandler
	public void onClick(@NotNull InventoryClickEvent event) {

		Player p = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

		if (current == null) return;

		if (event.getView().getTitle().equalsIgnoreCase("§8[§5§oEXP-Build§8] §l>§l§8 4")) {

			switch (event.getSlot()) {
				case 53 -> Main.getInstance().getGUI_Manager().open(p, GUI_Overworld.class);
				case 45 -> {
					if (event.getClick().equals(ClickType.SHIFT_LEFT)) {
						Main.getInstance().getGUI_Manager().open(p, GUI_Main.class);
						break;
					}
					Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);
				}
				case 50 -> {
					if (event.getClick().equals(ClickType.RIGHT)) {
						Executor.Rayon(brushBuilder, -1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Nether.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {
						Executor.Rayon(brushBuilder, -10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Nether.class);
					}
					if (event.getClick().equals(ClickType.LEFT)) {
						Executor.Rayon(brushBuilder, +1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Nether.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_LEFT)) {
						Executor.Rayon(brushBuilder, +10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Nether.class);
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
						Main.getInstance().getGUI_Manager().open(p, GUI_Nether.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_LEFT)) {

						Executor.AirBrush(brushBuilder, 10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Nether.class);
						return;
					}
					if (event.getClick().equals(ClickType.RIGHT)) {

						Executor.AirBrush(brushBuilder, -1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Nether.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {

						Executor.AirBrush(brushBuilder, -10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Nether.class);
						return;
					}
				}
				case WEEPING_VINES -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "weeping_vines,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case CRIMSON_FUNGUS -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "crimson_fungus,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case CRIMSON_ROOTS -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "crimson_roots,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case TWISTING_VINES -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "twisting_vines,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case WARPED_FUNGUS -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "warped_fungus,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case WARPED_ROOTS -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "warped_roots,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case NETHER_SPROUTS -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "nether_sprouts,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case WITHER_ROSE -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "wither_rose,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				default -> {
				}
			}
		}
	}
}
