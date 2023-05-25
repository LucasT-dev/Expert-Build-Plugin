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

public class Switch_food implements Listener {

	@EventHandler
	public void onClick(@NotNull InventoryClickEvent event) {

		Player p = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

		if (current == null)
			return;
		if (event.getView().getTitle().equalsIgnoreCase("§8[§5§oEXP-Build§8] §l>§l§8 3")) {

			switch (event.getSlot()) {
				case 53 -> Main.getInstance().getGUI_Manager().open(p, GUI_Nether.class);
				case 45 -> {
					if (event.getClick().equals(ClickType.SHIFT_LEFT)) {
						Main.getInstance().getGUI_Manager().open(p, GUI_Main.class);
						break;
					}
					Main.getInstance().getGUI_Manager().open(p, GUI_Coraux.class);
				}
				case 50 -> {
					if (event.getClick().equals(ClickType.RIGHT)) {

						Executor.Rayon(brushBuilder, -1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {

						Executor.Rayon(brushBuilder, -10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);
						return;
					}
					if (event.getClick().equals(ClickType.LEFT)) {

						Executor.Rayon(brushBuilder, +1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_LEFT)) {
						Executor.Rayon(brushBuilder, +10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);
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
						Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_LEFT)) {

						Executor.AirBrush(brushBuilder, 10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);
					}
					if (event.getClick().equals(ClickType.RIGHT)) {

						Executor.AirBrush(brushBuilder, -1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);
					}
					if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {

						Executor.AirBrush(brushBuilder, -10);
						Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);
					}
				}
				case LEATHER -> {
					if (event.getClick().equals(ClickType.LEFT)) {
						Executor.Years(brushBuilder, 1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);
					}
					if (event.getClick().equals(ClickType.RIGHT)) {
						Executor.Years(brushBuilder, -1);
						Main.getInstance().getGUI_Manager().open(p, GUI_Food.class);
					}
				}
				case WHEAT -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "59[age=" + brushBuilder.getYears() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case CARROT -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "141[age=" + brushBuilder.getYears() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case POTATO -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "142[age=" + brushBuilder.getYears() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case BEETROOT -> {
					if (brushBuilder.getYears() > 3) {
						brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "207[age=3],");
						brushBuilder.Build(brushBuilder);
						break;
					}
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "207[age=" + brushBuilder.getYears() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case SWEET_BERRIES -> {
					if (brushBuilder.getYears() > 3) {
						brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "sweet_berry_bush[age=3],");
						brushBuilder.Build(brushBuilder);
						break;
					}
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "sweet_berry_bush[age=" + brushBuilder.getYears() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case PUMPKIN_SEEDS -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "104[age=" + brushBuilder.getYears() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case MELON_SEEDS -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "105[age=" + brushBuilder.getYears() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case PUMPKIN -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "pumpkin,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case MELON -> {
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "103,");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				case NETHER_WART -> {
					if (brushBuilder.getYears() > 3) {
						brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "115[age=3],");
						brushBuilder.Build(brushBuilder);
						break;
					}
					brushBuilder.setFlowerMaterial(brushBuilder.getFlowerMaterial() + "115[age=" + brushBuilder.getYears() + "],");
					brushBuilder.Build(brushBuilder);
					Gestion_GUI.Select(event, event.getSlot());
				}
				default -> {
				}
			}
		}
	}
}
