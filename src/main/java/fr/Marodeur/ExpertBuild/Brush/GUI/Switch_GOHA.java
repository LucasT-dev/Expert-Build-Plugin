package fr.Marodeur.ExpertBuild.Brush.GUI;

import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.GOHA_Builder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Switch_GOHA implements Listener {

	private static final int maxRotation = 180;
	private static final int minRotation = -180;

	@EventHandler
	public void onClick(@NotNull InventoryClickEvent e) {

		if (e.getView().getTitle().equalsIgnoreCase("§8[§5§oEXP-Build§8] §l>§l§8 GOHA")) {

			Player p = (Player) e.getWhoClicked();
			ItemStack current = e.getCurrentItem();
			ClickType click = e.getClick();
			GOHA_Builder goha_builder = GOHA_Builder.getGOHABuilder(p);

			if (current == null) return;

			switch (e.getRawSlot()) {

				case 2 -> {// Head
					if (goha_builder.getCommutateur() == 0) {

						if (goha_builder.getHead().equals(true))
							goha_builder.setHead(false);
						else {
							goha_builder.setHead(true);
						}
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);

						break;
					}
					if (goha_builder.getCommutateur() == 1) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setHeadXAngle(goha_builder.getHeadXAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setHeadXAngle(goha_builder.getHeadXAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setHeadXAngle(goha_builder.getHeadXAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setHeadXAngle(goha_builder.getHeadXAngle() - 10);
						}

						if (goha_builder.getHeadXAngle() > maxRotation) goha_builder.setHeadXAngle(maxRotation);

						if (goha_builder.getHeadXAngle() < minRotation) goha_builder.setHeadXAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
						break;
					}
					if (goha_builder.getCommutateur() == 2) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setHeadYAngle(goha_builder.getHeadYAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setHeadYAngle(goha_builder.getHeadYAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setHeadYAngle(goha_builder.getHeadYAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setHeadYAngle(goha_builder.getHeadYAngle() - 10);
						}
						if (goha_builder.getHeadYAngle() > maxRotation) goha_builder.setHeadYAngle(maxRotation);

						if (goha_builder.getHeadYAngle() < minRotation) goha_builder.setHeadYAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 10 -> {// ArmD
					if (goha_builder.getCommutateur() == 0) {

						if (goha_builder.getArmD().equals(true))
							goha_builder.setArmD(false);
						else {
							goha_builder.setArmD(true);
						}
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
						break;
					}
					if (goha_builder.getCommutateur() == 1) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setArmDXAngle(goha_builder.getArmDXAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setArmDXAngle(goha_builder.getArmDXAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setArmDXAngle(goha_builder.getArmDXAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setArmDXAngle(goha_builder.getArmDXAngle() - 10);
						}

						if (goha_builder.getArmDXAngle() > maxRotation) goha_builder.setArmDXAngle(maxRotation);

						if (goha_builder.getArmDXAngle() < minRotation) goha_builder.setArmDXAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
						break;
					}
					if (goha_builder.getCommutateur() == 2) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setArmDYAngle(goha_builder.getArmDYAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setArmDYAngle(goha_builder.getArmDYAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setArmDYAngle(goha_builder.getArmDYAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setArmDYAngle(goha_builder.getArmDYAngle() - 10);
						}
						if (goha_builder.getArmDYAngle() > maxRotation) goha_builder.setArmDYAngle(maxRotation);

						if (goha_builder.getArmDYAngle() < minRotation) goha_builder.setArmDYAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 12 -> {// ArmG$
					if (goha_builder.getCommutateur() == 0) {

						if (goha_builder.getArmG().equals(true))
							goha_builder.setArmG(false);
						else {
							goha_builder.setArmG(true);
						}
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 1) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setArmGXAngle(goha_builder.getArmGXAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setArmGXAngle(goha_builder.getArmGXAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setArmGXAngle(goha_builder.getArmGXAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setArmGXAngle(goha_builder.getArmGXAngle() - 10);
						}
						if (goha_builder.getArmGXAngle() > maxRotation) goha_builder.setArmGXAngle(maxRotation);

						if (goha_builder.getArmGXAngle() < minRotation) goha_builder.setArmGXAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 2) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setArmGYAngle(goha_builder.getArmGYAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setArmGYAngle(goha_builder.getArmGYAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setArmGYAngle(goha_builder.getArmGYAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setArmGYAngle(goha_builder.getArmGYAngle() - 10);
						}

						if (goha_builder.getArmGYAngle() > maxRotation) goha_builder.setArmGYAngle(maxRotation);

						if (goha_builder.getArmGYAngle() < minRotation) goha_builder.setArmGYAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 18 -> {// ForeArmD
					if (goha_builder.getCommutateur() == 0) {

						if (goha_builder.getForeArmD().equals(true))
							goha_builder.setForeArmD(false);
						else {
							goha_builder.setForeArmD(true);
						}
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 1) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setForeArmDXAngle(goha_builder.getForeArmDXAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setForeArmDXAngle(goha_builder.getForeArmDXAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setForeArmDXAngle(goha_builder.getForeArmDXAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setForeArmDXAngle(goha_builder.getForeArmDXAngle() - 10);
						}

						if (goha_builder.getForeArmDXAngle() > maxRotation) goha_builder.setForeArmDXAngle(maxRotation);

						if (goha_builder.getForeArmDXAngle() < minRotation) goha_builder.setForeArmDXAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
						break;
					}
					if (goha_builder .getCommutateur() == 2) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setForeArmDYAngle(goha_builder.getForeArmDYAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setForeArmDYAngle(goha_builder.getForeArmDYAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setForeArmDYAngle(goha_builder.getForeArmDYAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setForeArmDYAngle(goha_builder.getForeArmDYAngle() - 10);
						}
						if (goha_builder.getForeArmDYAngle() > maxRotation) goha_builder.setForeArmDYAngle(maxRotation);

						if (goha_builder.getForeArmDYAngle() < minRotation) goha_builder.setForeArmDYAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 22 -> {// ForeArmG
					if (goha_builder.getCommutateur() == 0) {

						if (goha_builder.getForeArmG().equals(true)) {
							goha_builder.setForeArmG(false);
						} else {
							goha_builder.setForeArmG(true);
						}
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 1) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setForeArmGXAngle(goha_builder.getForeArmGXAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setForeArmGXAngle(goha_builder.getForeArmGXAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setForeArmGXAngle(goha_builder.getForeArmGXAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setForeArmGXAngle(goha_builder.getForeArmGXAngle() - 10);
						}

						if (goha_builder.getForeArmGXAngle() > maxRotation) goha_builder.setForeArmGXAngle(maxRotation);

						if (goha_builder.getForeArmGXAngle() < minRotation) goha_builder.setForeArmGXAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 2) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setForeArmGYAngle(goha_builder.getForeArmGYAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setForeArmGYAngle(goha_builder.getForeArmGYAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setForeArmGYAngle(goha_builder.getForeArmGYAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setForeArmGYAngle(goha_builder.getForeArmGYAngle() - 10);
						}

						if (goha_builder.getForeArmGYAngle() > maxRotation) goha_builder.setForeArmGYAngle(maxRotation);

						if (goha_builder.getForeArmGYAngle() < minRotation) goha_builder.setForeArmGYAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 20 -> {// Torso
					if (goha_builder.getCommutateur() == 0) {

						if (goha_builder.getTorso().equals(true))
							goha_builder.setTorso(false);
						else {
							goha_builder.setTorso(true);
						}
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
						break;
					}
					if (goha_builder.getCommutateur() == 1) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setTorsoXAngle(goha_builder.getTorsoXAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setTorsoXAngle(goha_builder.getTorsoXAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setTorsoXAngle(goha_builder.getTorsoXAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setTorsoXAngle(goha_builder.getTorsoXAngle() - 10);
						}

						if (goha_builder.getTorsoXAngle() > maxRotation) goha_builder.setTorsoXAngle(maxRotation);

						if (goha_builder.getTorsoXAngle() < minRotation) goha_builder.setTorsoXAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 2) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setTorsoYAngle(goha_builder.getTorsoYAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setTorsoYAngle(goha_builder.getTorsoYAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setTorsoYAngle(goha_builder.getTorsoYAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setTorsoYAngle(goha_builder.getTorsoYAngle() - 10);
						}

						if (goha_builder.getTorsoYAngle() > maxRotation) goha_builder.setTorsoYAngle(maxRotation);

						if (goha_builder.getTorsoYAngle() < minRotation) goha_builder.setTorsoYAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 37 -> {// Leg D
					if (goha_builder.getCommutateur() == 0) {

						if (goha_builder.getLegD().equals(true))
							goha_builder.setLegD(false);
						else {
							goha_builder.setLegD(true);
						}
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 1) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setLegDXAngle(goha_builder.getLegDXAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setLegDXAngle(goha_builder.getLegDXAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setLegDXAngle(goha_builder.getLegDXAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setLegDXAngle(goha_builder.getLegDXAngle() -10);
						}
						if (goha_builder.getLegDXAngle() > maxRotation) goha_builder.setLegDXAngle(maxRotation);

						if (goha_builder.getLegDXAngle() < minRotation) goha_builder.setLegDXAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 2) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setLegDYAngle(goha_builder.getLegDYAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setLegDYAngle(goha_builder.getLegDYAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setLegDYAngle(goha_builder.getLegDYAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setLegDYAngle(goha_builder.getLegDYAngle() - 10);
						}
						goha_builder.buildGoha(goha_builder);

						if (goha_builder.getLegDYAngle() > maxRotation) goha_builder.setLegDYAngle(maxRotation);

						if (goha_builder.getLegDYAngle() < minRotation) goha_builder.setLegDYAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 39 -> {// Leg G
					if (goha_builder.getCommutateur() == 0) {
						if (goha_builder.getLegG().equals(true))
							goha_builder.setLegG(false);
						else {
							goha_builder.setLegG(true);
						}
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 1) {
						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setLegGXAngle(goha_builder.getLegGXAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setLegGXAngle(goha_builder.getLegGXAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setLegGXAngle(goha_builder.getLegGXAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setLegGXAngle(goha_builder.getLegGXAngle() - 10);
						}
						if (goha_builder.getLegGXAngle() > maxRotation) goha_builder.setLegGXAngle(maxRotation);

						if (goha_builder.getLegGXAngle() < minRotation) goha_builder.setLegGXAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 2) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setLegGYAngle(goha_builder.getLegGYAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setLegGYAngle(goha_builder.getLegGYAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setLegGYAngle(goha_builder.getLegGYAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setLegGYAngle(goha_builder.getLegGYAngle() - 10);
						}

						if (goha_builder.getLegGYAngle() > maxRotation) goha_builder.setLegGYAngle(maxRotation);
						if (goha_builder.getLegGYAngle() < minRotation) goha_builder.setLegGYAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 46 -> {// Tibia D
					if (goha_builder.getCommutateur() == 0) {

						if (goha_builder.getTibiaD().equals(true))
							goha_builder.setTibiaD(false);
						else {
							goha_builder.setTibiaD(true);
						}
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 1) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setTibiaDXAngle(goha_builder.getTibiaDXAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setTibiaDXAngle(goha_builder.getTibiaDXAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setTibiaDXAngle(goha_builder.getTibiaDXAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setTibiaDXAngle(goha_builder.getTibiaDXAngle() - 10);
						}

						if (goha_builder.getTibiaDXAngle() > maxRotation) goha_builder.setLegDXAngle(maxRotation);
						if (goha_builder.getTibiaDXAngle() < minRotation) goha_builder.setLegDXAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 2) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setTibiaDYAngle(goha_builder.getTibiaDYAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setTibiaDYAngle(goha_builder.getTibiaDYAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setTibiaDYAngle(goha_builder.getTibiaDYAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setTibiaDYAngle(goha_builder.getTibiaDYAngle() - 10);
						}

						if (goha_builder.getTibiaDYAngle() > maxRotation) goha_builder.setTibiaDYAngle(maxRotation);
						if (goha_builder.getTibiaDYAngle() < minRotation) goha_builder.setTibiaDYAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 48 -> {// Tibia G
					if (goha_builder.getCommutateur() == 0) {

						if (goha_builder.getTibiaG().equals(true))
							goha_builder.setTibiaG(false);
						else {
							goha_builder.setTibiaG(false);
						}
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 1) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setTibiaGXAngle(goha_builder.getTibiaGXAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setTibiaGXAngle(goha_builder.getTibiaGXAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setTibiaGXAngle(goha_builder.getTibiaGXAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setTibiaGXAngle(goha_builder.getTibiaGXAngle() - 10);
						}

						if (goha_builder.getTibiaGXAngle() > maxRotation) goha_builder.setTibiaGXAngle(maxRotation);
						if (goha_builder.getTibiaGXAngle() < minRotation) goha_builder.setTibiaGXAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
					if (goha_builder.getCommutateur() == 2) {

						if (click.equals(ClickType.RIGHT)) {
							goha_builder.setTibiaGYAngle(goha_builder.getTibiaGYAngle() + 1);
						}
						if (click.equals(ClickType.LEFT)) {
							goha_builder.setTibiaGYAngle(goha_builder.getTibiaGYAngle() - 1);
						}
						if (click.equals(ClickType.SHIFT_RIGHT)) {
							goha_builder.setTibiaGYAngle(goha_builder.getTibiaGYAngle() + 10);
						}
						if (click.equals(ClickType.SHIFT_LEFT)) {
							goha_builder.setTibiaGYAngle(goha_builder.getTibiaGYAngle() - 10);
						}
						if (goha_builder.getTibiaGYAngle() > maxRotation) goha_builder.setTibiaGYAngle(maxRotation);
						if (goha_builder.getTibiaGYAngle() < minRotation) goha_builder.setTibiaGYAngle(minRotation);

						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 24 -> {// X.1
					if (goha_builder.getCommutateur() == 0) {
						goha_builder.setCommutateur(1);
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
						break;
					}
					if (goha_builder.getCommutateur() == 1) {
						goha_builder.setCommutateur(0);
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
						break;
					}
					if (goha_builder.getCommutateur() == 2) {
						goha_builder.setCommutateur(1);
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 25 -> {// Y.2
					if (goha_builder.getCommutateur() == 0) {
						goha_builder.setCommutateur(2);
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
						break;
					}
					if (goha_builder.getCommutateur() == 1) {
						goha_builder.setCommutateur(2);
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
						break;
					}
					if (goha_builder.getCommutateur() == 2) {
						goha_builder.setCommutateur(0);
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 26 -> {// V.0
					if (goha_builder.getCommutateur() == 0) {
						goha_builder.setCommutateur(0);
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
						return;
					}
					if (goha_builder.getCommutateur() == 1) {
						goha_builder.setCommutateur(0);
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
						return;
					}
					if (goha_builder.getCommutateur() == 2) {
						goha_builder.setCommutateur(0);
						goha_builder.buildGoha(goha_builder);
						Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
					}
				}
				case 42 -> {
					if (click.equals(ClickType.RIGHT)) {
						goha_builder.setHeight(goha_builder.getHeight() + 1);
					}
					if (click.equals(ClickType.LEFT)) {
						goha_builder.setHeight(goha_builder.getHeight() - 1);
					}
					if (click.equals(ClickType.SHIFT_RIGHT)) {
						goha_builder.setHeight(goha_builder.getHeight() + 10);
					}
					if (click.equals(ClickType.SHIFT_LEFT)) {
						goha_builder.setHeight(goha_builder.getHeight() - 10);
					}
					goha_builder.buildGoha(goha_builder);
					Main.getInstance().getGUI_Manager().open(p, GUI_GOHA.class);
				}
			}
		}
	}

	@EventHandler
	public void closeInv(@NotNull InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		GOHA_Builder goha_builder = GOHA_Builder.getGOHABuilder(p);

		if (e.getView().getTitle().equalsIgnoreCase("§8[§5§oEXP-Build§8] §l>§l§8 GOHA")) {
			if (goha_builder.getPregen().equals(true)) {

				goha_builder.setPregen(false).buildGoha(goha_builder);

				Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
					@Override
					public void run() {
						goha_builder.setPregen(true).buildGoha(goha_builder);

						new GOHA_Builder.OrganicGeneration(p, goha_builder.getStartLoc())
								.getAllPoint()
								.generateAllParticle();

						//BodyGeneration.generateParticleBody(p, goha_builder.getStartLoc());
					}
				}, 20);
			}
		}
	}
}
