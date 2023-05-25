package fr.Marodeur.ExpertBuild.Object;

import fr.Marodeur.ExpertBuild.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

	private ItemStack itemStack;
	private ItemMeta itemMeta;

	public ItemBuilder(String name, List<String> lore, EntityType entity, int amount) {
		initItem(name, lore, entity, amount);
	}

	@SuppressWarnings("deprecation")
	private void initItem(String name, List<String> lore, EntityType entity, int amount) {
		if (this.itemStack == null) {
			this.itemStack = new ItemStack(Material.CREEPER_SPAWN_EGG, amount);

			SpawnEggMeta meta = (SpawnEggMeta) this.itemStack.getItemMeta();
			meta.setSpawnedType(entity);
			meta.setDisplayName(name);
			meta.setLore(lore);
			this.itemStack.setItemMeta(meta);
		}
	}

	public ItemBuilder(Material mat, int amount, int data) {
		initItem(mat, amount, data);
	}

	@SuppressWarnings("deprecation")
	private void initItem(Material mat, int amount, int data) {
		if (this.itemStack == null) {
			this.itemStack = new ItemStack(mat, amount, (short) data);
		}
	}

	public ItemBuilder(String name, Material mat, int amount) {
		initItem(name, mat, amount);
	}

	private void initItem(String name, Material mat, int amount) {
		if (this.itemStack == null) {
			this.itemStack = new ItemStack(mat, amount);
		}
		if (this.itemMeta == null) {
			this.itemMeta = this.itemStack.getItemMeta();
			this.itemMeta.setDisplayName(name);
			this.itemStack.setItemMeta(this.itemMeta);
		}
	}

	public ItemBuilder(String name, Material mat, int amount, int data) {
		initItem(name, mat, amount, data);
	}

	private void initItem(String name, Material mat, int amount, int data) {
		initItem(mat, amount, data);
		if (this.itemMeta == null) {
			this.itemMeta = this.itemStack.getItemMeta();
			this.itemMeta.setDisplayName(name);
			this.itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
			this.itemStack.setItemMeta(this.itemMeta);
		}
	}

	public ItemBuilder(String name, List<String> lore, Material mat, int amount) {
		initItem(name, lore, mat, amount);
	}

	private void initItem(String name, List<String> lore, Material mat, int amount) {
		if (this.itemStack == null) {
			this.itemStack = new ItemStack(mat, amount);
		}
		if (this.itemMeta == null) {
			this.itemMeta = this.itemStack.getItemMeta();
			this.itemMeta.setDisplayName(name);
			this.itemMeta.setLore(lore);
			this.itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
			this.itemStack.setItemMeta(this.itemMeta);
		}
	}

	public ItemBuilder setUnbreakable(boolean unbreakable) {
		this.itemMeta = this.itemStack.getItemMeta();
		this.itemMeta.setUnbreakable(unbreakable);
		this.itemStack.setItemMeta(this.itemMeta);
		this.itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_UNBREAKABLE });
		this.itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		this.itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_DESTROYS });

		return this;
	}

	public ItemBuilder(String name, List<String> lore, Material mat, int amount, int data) {
		initItem(name, lore, mat, amount, data);
	}

	@SuppressWarnings("deprecation")
	private void initItem(String name, List<String> lore, Material mat, int amount, int data) {
		if (this.itemStack == null) {
			this.itemStack = new ItemStack(mat, amount, (short) data);
		}
		if (this.itemMeta == null) {
			this.itemMeta = this.itemStack.getItemMeta();
			this.itemMeta.setDisplayName(name);
			this.itemMeta.setLore(lore);
			this.itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
			this.itemStack.setItemMeta(this.itemMeta);
		}
	}

	public ItemBuilder addLore(String lore) {
		if (this.itemMeta != null) {
			if (this.itemMeta.getLore() != null) {
				List<String> lores = new ArrayList<String>();
				lores = this.itemMeta.getLore();
				lores.add(lore);
				this.itemMeta.setLore(lores);
				itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);

				itemMeta.setUnbreakable(true);
			} else {
				this.itemMeta.setLore(Arrays.asList(new String[] { lore }));
			}
			this.itemStack.setItemMeta(this.itemMeta);
		} else {
			throw new IllegalStateException(Main.prefix + "itemMeta non initialise !");
		}
		return this;
	}

	public ItemBuilder addEnchant(Enchantment enchant, int level) {
		if (this.itemMeta != null) {
			this.itemMeta.addEnchant(enchant, level, true);
			this.itemStack.setItemMeta(this.itemMeta);
		} else {
			throw new IllegalStateException(Main.prefix + "itemMeta non initialise !");
		}
		return this;
	}

	@SuppressWarnings("deprecation")
	public ItemBuilder setDurability(int durability) {
		this.itemStack.setDurability((short) durability);
		return this;
	}

	public ItemBuilder setCustomModelData(int durability) {
		this.itemStack.getItemMeta().setCustomModelData(durability);
		return this;
	}

	public ItemBuilder hideEnchants() {
		this.itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		this.itemStack.setItemMeta(this.itemMeta);
		return this;
	}

	/*
	 * public ItemBuilder setLeatherArmorColor(Color r, Color g, Color b) { try {
	 * LeatherArmorMeta im = (LeatherArmorMeta)this.itemStack.getItemMeta();
	 * im.setColor(fromRGB(r,g,b)); this.itemStack.setItemMeta((ItemMeta)im); }
	 * catch (ClassCastException classCastException) {} return this; }
	 */

	public ItemStack build() {
		return this.itemStack;
	}
}