package fr.Marodeur.ExpertBuild.Brush.GUI;

import fr.Marodeur.ExpertBuild.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Gestion_GUI implements Listener {

	public static final String RightArrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTliZjMyOTJlMTI2YTEwNWI1NGViYTcxM2FhMWIxNTJkNTQxYTFkODkzODgyOWM1NjM2NGQxNzhlZDIyYmYifX19";
	public static final String LeftArrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==";
	public static List<Player> Toggle = new ArrayList<>();

    @EventHandler
	public void onInteract(@NotNull PlayerInteractEvent event) {

		Player p = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();

		if (p.getOpenInventory().getTitle().equals("§8[§5§oEXP-Build§8] §l>§l§8 Gmask")) return;
		if (it == null) return;

		if (it.getType() == Material.HONEYCOMB) {
			if (action == Action.LEFT_CLICK_AIR) {

				if (!Main.containsBrushBuilder(p)) {
					p.sendMessage(Main.prefix + "You are not registered, execute: /fw register");
					return;
				}

				Main.getInstance().getGUI_Manager().open(p, GUI_Main.class);
			}
		}
	}

	public static void Select(@NotNull InventoryClickEvent event, int slot) {

		Player p = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		ItemMeta Im = current.getItemMeta();

		if (current == null) return;

		Toggle.add(p);
		Im.addEnchant(Enchantment.ARROW_DAMAGE, 200, true);
		Im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		p.getOpenInventory().getInventory(0).getItem(slot).setItemMeta(Im);
		p.updateInventory();
	}
}