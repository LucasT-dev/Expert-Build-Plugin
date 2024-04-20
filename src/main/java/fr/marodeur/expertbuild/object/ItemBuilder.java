package fr.marodeur.expertbuild.object;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {

	private ItemStack itemStack;


	public ItemBuilder(Material material)
	{
		this(material, 1);
	}

	public ItemBuilder(ItemStack itemStack)
	{
		this.itemStack = itemStack;
	}

	public ItemBuilder(Material material, int amount)
	{
		itemStack = new ItemStack(material, amount);
	}


	public ItemBuilder(String name, Material material, int amount){
		itemStack = new ItemStack(material,amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name);
		itemStack.setItemMeta(itemMeta);
	}

	public ItemBuilder(StringBuilder name, Material material, int amount){
		itemStack = new ItemStack(material,amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name.toString());
		itemStack.setItemMeta(itemMeta);
	}

	public ItemBuilder(String path, boolean prefix, Material material, int amount, String[]... var){

		String name = new Message.MessageSender(path, prefix, var).getMessage();

		itemStack = new ItemStack(material,amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name.toString());
		itemStack.setItemMeta(itemMeta);
	}

	public ItemBuilder(Material material, int amount, short data)
	{
		itemStack = new ItemStack(material, amount, data);
	}

	public ItemBuilder(Material material, int amount, short data, String owner)
	{
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (byte) 3);
		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		skullMeta.setOwner(owner);
		skull.setItemMeta(skullMeta);
		itemStack = skull;
	}

	@Override
	public ItemBuilder clone()
	{
		return new ItemBuilder(itemStack);
	}


	public ItemBuilder setName(String name)
	{
		ItemMeta im = itemStack.getItemMeta();
		im.setDisplayName(name);
		itemStack.setItemMeta(im);
		return this;
	}

	public ItemBuilder addLore(String line)
	{
		ItemMeta im = itemStack.getItemMeta();
		List<String> lore = new ArrayList<>();
		if (im.hasLore()) lore = new ArrayList<>(im.getLore());
		lore.add(line);
		im.setLore(lore);
		itemStack.setItemMeta(im);
		return this;
	}

	public ItemBuilder addLore(StringBuilder line)
	{
		ItemMeta im = itemStack.getItemMeta();
		List<String> lore = new ArrayList<>();
		if (im.hasLore()) lore = new ArrayList<>(im.getLore());
		lore.add(line.toString());
		im.setLore(lore);
		itemStack.setItemMeta(im);
		return this;
	}

	public ItemBuilder addLore(String path, boolean prefix, String[]... var)
	{
		String line = new Message.MessageSender(path, prefix, var).getMessage();

		ItemMeta im = itemStack.getItemMeta();
		List<String> lore = new ArrayList<>();
		if (im.hasLore()) lore = new ArrayList<>(im.getLore());
		lore.add(line);
		im.setLore(lore);
		itemStack.setItemMeta(im);
		return this;
	}

	public ItemBuilder addLoreLineTest(String lineIfTrue, String lineIfFalse, Boolean b)
	{
		ItemMeta im = itemStack.getItemMeta();
		List<String> lore = new ArrayList<>();
		if (im.hasLore()) lore = new ArrayList<>(im.getLore());
		if (b) lore.add(lineIfTrue);
		if (!b) lore.add(lineIfFalse);
		im.setLore(lore);
		itemStack.setItemMeta(im);
		return this;
	}

	public ItemBuilder addLoreLineTest(StringBuilder lineIfTrue, StringBuilder lineIfFalse, Boolean b)
	{
		ItemMeta im = itemStack.getItemMeta();
		List<String> lore = new ArrayList<>();
		if (im.hasLore()) lore = new ArrayList<>(im.getLore());
		if (b) lore.add(lineIfTrue.toString());
		if (!b) lore.add(lineIfFalse.toString());
		im.setLore(lore);
		itemStack.setItemMeta(im);
		return this;
	}

	public ItemBuilder addLoreLineTest(String pathLineIfTrue, String pathLineIfFalse, boolean condition, boolean prefix)
	{

		ItemMeta im = itemStack.getItemMeta();
		List<String> lore = new ArrayList<>();
		if (im.hasLore()) lore = new ArrayList<>(im.getLore());
		if (condition) lore.add(new Message.MessageSender(pathLineIfTrue, false).getMessage());
		if (!condition) lore.add(new Message.MessageSender(pathLineIfFalse, false).getMessage());
		im.setLore(lore);
		itemStack.setItemMeta(im);
		return this;
	}


	public ItemBuilder addLoreLine(String line, int pos)
	{
		ItemMeta im = itemStack.getItemMeta();
		List<String> lore = new ArrayList<>(im.getLore());
		lore.set(pos, line);
		im.setLore(lore);
		itemStack.setItemMeta(im);
		return this;
	}

	public ItemBuilder setUnbreakable(boolean unbreakable)
	{
		ItemMeta im = itemStack.getItemMeta();
		im.setUnbreakable(unbreakable);
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_UNBREAKABLE});
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES});
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_DESTROYS});
		itemStack.setItemMeta(im);
		return this;
	}

	public ItemBuilder addEnchant(Enchantment enchantment, int level){
		ItemMeta im = itemStack.getItemMeta();
		im.addEnchant(enchantment,level,true);
		itemStack.setItemMeta(im);
		return this;
	}

	public ItemBuilder setDurability(short durability){
		itemStack.setDurability(durability);
		hideAttributes();
		return this;
	}

	public ItemBuilder hideAttributes(){
		ItemMeta im = itemStack.getItemMeta();
		im.setUnbreakable(true);
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES});
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_DESTROYS});
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_UNBREAKABLE});
		itemStack.setItemMeta(im);
		return this;
	}

	public ItemBuilder setCustomModelData(int modelData){
		ItemMeta im = itemStack.getItemMeta();
		im.setCustomModelData(modelData);
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES});
		itemStack.setItemMeta(im);
		return this;
	}

	public <T, Z> ItemBuilder setPersistentDataContainer(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
		ItemMeta im = itemStack.getItemMeta();
		im.getPersistentDataContainer().set(key, type, value);
		itemStack.setItemMeta(im);
		return this;
	}

	public ItemBuilder setLeatherColor(Color color) {
		LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
		leatherArmorMeta.setColor(color);
		itemStack.setItemMeta(leatherArmorMeta);
		return this;
	}

	public ItemBuilder setLeatherColor(int r, int g, int b) {

		if (r > 255) r = 255;
		if (g > 255) g = 255;
		if (b > 255) b = 255;

		if (r < 0) r = 0;
		if (g < 0) g = 0;
		if (b < 0) b = 0;

		LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
		leatherArmorMeta.setColor(Color.fromBGR(b, g, r));
		itemStack.setItemMeta(leatherArmorMeta);
		return this;
	}

	public ItemBuilder setSkullByName(String name) {
		SkullMeta skullMeta = (SkullMeta) this.itemStack.getItemMeta();
		skullMeta.setOwner(name);
		this.itemStack.setItemMeta(skullMeta);
		return this;
	}

	private static @NotNull PlayerProfile getProfile(String url) {

		PlayerProfile profile = Bukkit.createPlayerProfile(UUID.nameUUIDFromBytes(("https://textures.minecraft.net/texture/"+url).getBytes())); // Get a new player profile
		PlayerTextures textures = profile.getTextures();
		URL urlObject;

		try {
			urlObject = new URL("https://textures.minecraft.net/texture/"+url); // The URL to the skin, for example: https://textures.minecraft.net/texture/18813764b2abc94ec3c3bc67b9147c21be850cdf996679703157f4555997ea63a
		} catch (MalformedURLException exception) {
			throw new RuntimeException("Invalid URL", exception);
		}
		textures.setSkin(urlObject); // Set the skin of the player profile to the URL
		profile.setTextures(textures); // Set the textures back to the profile
		return profile;
	}

	// Old method, disable #19
	/*public ItemBuilder setSkullTextures1(String textures) throws IllegalArgumentException, NullPointerException {

		this.itemStack = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta headMeta = (SkullMeta) this.itemStack.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);

		itemStack.setItemMeta(headMeta);

		profile.getProperties().put("textures", new Property("textures", textures));

		try {
			Field profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headMeta, profile);
			this.itemStack.setItemMeta(headMeta);
		} catch (NoSuchFieldException|IllegalAccessException ignored) {}
		return this;
	}*/

	// https://blog.jeff-media.com/creating-custom-heads-in-spigot-1-18-1/
	// https://minecraft-heads.com/custom-heads/search

	public ItemBuilder setSkullTextures(String textures) {

		PlayerProfile pProfile = getProfile(textures);

		this.itemStack = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta skullMeta = (SkullMeta) this.itemStack.getItemMeta();

		((SkullMeta) this.itemStack.getItemMeta()).setOwnerProfile(pProfile);

		skullMeta.setOwnerProfile(pProfile);
		this.itemStack.setItemMeta(skullMeta);

		return this;
	}

	public ItemStack build() {
		return itemStack;
	}
}