package fr.Marodeur.ExpertBuild.Brush.GUI;

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

public class GUI_Gmask implements GUI_Builder {

	@Override
	public String name() {
		return "§8[§5§oEXP-Build§8] §l>§l§8 Gmask";
	}

	@Override
	public int getSize() {
		return 3 * 9;
	}

	@Override
	public void contents(Player p, @NotNull Inventory GUI_Gmask) {

		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta sk = (SkullMeta) item.getItemMeta();
		Executor.applyTextures(sk, UUID.randomUUID(), Gestion_GUI.RightArrow);
		item.setItemMeta(sk);
		ItemMeta item_meta = item.getItemMeta();
		item_meta.setDisplayName("Previous page");
		item.setItemMeta(item_meta);
		GUI_Gmask.setItem(8, item);

		GUI_Gmask.setItem(1, new ItemBuilder("" + "Gmask rebuilding",
				Material.SPONGE, 1).build());

		/*if (Brush_Manager.BrushManagerObjet.get(p.getName() + "GmaskEnable").equals("Enable")) {
			GUI_Gmask.setItem(0, new ItemBuilder("", Material.LIME_STAINED_GLASS_PANE, 1).build());
			GUI_Gmask.setItem(2, new ItemBuilder("", Material.LIME_STAINED_GLASS_PANE, 1).build());
		}
		if (Brush_Manager.BrushManagerObjet.get(p.getName() + "GmaskEnable").equals("Disable")) {
			GUI_Gmask.setItem(0, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
			GUI_Gmask.setItem(2, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
		}*/
		GUI_Gmask.setItem(9, new ItemBuilder("", Material.YELLOW_STAINED_GLASS_PANE, 1).build());
		GUI_Gmask.setItem(10, new ItemBuilder("", Material.YELLOW_STAINED_GLASS_PANE, 1).build());
		GUI_Gmask.setItem(11, new ItemBuilder("", Material.YELLOW_STAINED_GLASS_PANE, 1).build());
		GUI_Gmask.setItem(12, new ItemBuilder("", Material.YELLOW_STAINED_GLASS_PANE, 1).build());
		GUI_Gmask.setItem(13, new ItemBuilder("", Material.YELLOW_STAINED_GLASS_PANE, 1).build());
		GUI_Gmask.setItem(14, new ItemBuilder("", Material.YELLOW_STAINED_GLASS_PANE, 1).build());
		GUI_Gmask.setItem(15, new ItemBuilder("", Material.YELLOW_STAINED_GLASS_PANE, 1).build());
		GUI_Gmask.setItem(16, new ItemBuilder("", Material.YELLOW_STAINED_GLASS_PANE, 1).build());
		GUI_Gmask.setItem(17, new ItemBuilder("", Material.YELLOW_STAINED_GLASS_PANE, 1).build());

		//String[] gmask = ((String) Brush_Manager.BrushManagerObjet.get(p.getName() + "MatGmask"))
				//.replace("AIR", "BARRIER").split(",");

		/*GUI_Gmask.setItem(18, new ItemBuilder("", Material.getMaterial(gmask[0]), 1).build());
		GUI_Gmask.setItem(19, new ItemBuilder("", Material.getMaterial(gmask[1]), 1).build());
		GUI_Gmask.setItem(20, new ItemBuilder("", Material.getMaterial(gmask[2]), 1).build());
		GUI_Gmask.setItem(21, new ItemBuilder("", Material.getMaterial(gmask[3]), 1).build());
		GUI_Gmask.setItem(22, new ItemBuilder("", Material.getMaterial(gmask[4]), 1).build());
		GUI_Gmask.setItem(23, new ItemBuilder("", Material.getMaterial(gmask[5]), 1).build());
		GUI_Gmask.setItem(24, new ItemBuilder("", Material.getMaterial(gmask[6]), 1).build());
		GUI_Gmask.setItem(25, new ItemBuilder("", Material.getMaterial(gmask[7]), 1).build());
		GUI_Gmask.setItem(26, new ItemBuilder("", Material.getMaterial(gmask[8]), 1).build());*/
	}

	@Override
	public void onClick(Player p, Inventory inv, ItemStack current, int slot) {
	}
}
