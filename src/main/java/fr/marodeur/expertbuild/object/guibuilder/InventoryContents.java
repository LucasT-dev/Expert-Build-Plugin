package fr.marodeur.expertbuild.object.guibuilder;

import fr.marodeur.expertbuild.object.ItemBuilder;
import fr.marodeur.expertbuild.object.Message;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import javax.annotation.Nonnegative;
import java.util.ArrayList;
import java.util.List;

public class InventoryContents implements Cloneable{

    private final Player player;
    private final Inventory inventory;

    private final List<ItemData> inventoryData;

    public InventoryContents(Player player, Inventory inventory) {

        this.player = player;
        this.inventory = inventory;
        this.inventoryData = new ArrayList<>(inventory.size());
    }



    public void set(ItemData item) throws IllegalArgumentException {

        if (item.slot() > this.inventory.size())
            throw new IllegalArgumentException("Invalid slot");

        item.setUpdate(true);

        this.inventoryData.add(item);
    }

    public void setAll(List<ItemData> itemData) {
        itemData.forEach(this::set);
    }

    public void update(ItemData item) throws IllegalArgumentException {

        if (this.containsSlot(item.slot())) {
            deleteItemData(item.slot());
            set(item);
        }
    }

    public void updateOrSet(ItemData item) throws IllegalArgumentException {

        if (this.containsSlot(item.slot())) {
            deleteItemData(item.slot());
            set(item);
        } else {
            set(item);
        }
    }

    public void updateTitle(@Nonnegative int slot, String title) {

        if (this.containsSlot(slot)) {

            ItemData itemData = this.getItemData(slot);
            ItemStack it = itemData.itemBuilder();
            ItemMeta im = it.getItemMeta();
            im.setDisplayName(title);
            it.setItemMeta(im);
        }
    }

    public void updateTitle(@Nonnegative int slot, String path, boolean prefix, String[]... var) {

        if (this.containsSlot(slot)) {

            String text = new Message.MessageSender(path, prefix, var).getMessage();
            updateTitle(slot, text);
        }
    }

    public void updateLore(@Nonnegative int slot, @Nonnegative int line, String lore) {

        if (this.containsSlot(slot)) {

            ItemData itemData = this.getItemData(slot);

            ItemStack it = itemData.itemBuilder();

            List<String> lores = it.getLore();

            if (line <= (lores.size()-1)) {

                lores.set(line, lore);

                List<Component> componentList = new ArrayList<>();
                lores.forEach(string -> componentList.add(Component.newline().content(string)));

                it.lore(componentList);
            }
        }
    }
    public void updateLore(int slot, @Nonnegative int line, String path, boolean prefix, String[]... var) {

        String text = new Message.MessageSender(path, prefix, var).getMessage();

        updateLore(slot, line, text);

    }


    public void updateLore(int row, int column, @Nonnegative int line, String path, boolean prefix, String[]... var) {

        String text = new Message.MessageSender(path, prefix, var).getMessage();

        updateLore(row * 9 + column, line, text);

    }

    public void updateMaterial(@Nonnegative int slot, Material material) {

        if (this.containsSlot(slot)) {

            ItemStack it = this.getItemData(slot).itemBuilder();
            it.setType(material);

        }
    }

    public void updateAmount(@Nonnegative int slot, int amount) {

        if (this.containsSlot(slot)) {

            ItemStack it = this.getItemData(slot).itemBuilder();
            it.setAmount(amount);

        }
    }
    //updateLeatherColor

    public void updateAmount(int row, int column, int amount) {

        updateAmount(row * 9 + column, amount);
    }

    public void updateLeatherColor(int slot, int r, int g, int b) {

        if (this.containsSlot(slot)) {

            ItemStack it = this.getItemData(slot).itemBuilder();

            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) it.getItemMeta();
            leatherArmorMeta.setColor(Color.fromBGR(b, g, r));
            it.setItemMeta(leatherArmorMeta);

        }
    }

    public void updateLeatherColor(int row, int column, int r, int g, int b) {
        updateLeatherColor(row * 9 + column, r, g, b);
    }

    public Player player() {
        return player;
    }

    public Inventory inventory() {
        return inventory;
    }

    public List<ItemData> inventoryData() {
        return inventoryData;
    }



    public boolean containsSlot(int slot) {
        return inventoryData().stream().anyMatch(itemData -> itemData.slot() == slot);
    }

    public ItemData getItemData(int slot) {
        return inventoryData().stream()
                .filter(itemData -> itemData.slot() == slot)
                .findFirst().orElse(new ItemData(0, new ItemBuilder(Material.AIR)));
    }

    public void deleteItemData(int slot) {
        inventoryData().remove(getItemData(slot));
    }


    public String inventoryDataToString() {

        StringBuilder sb = new StringBuilder();

        inventoryData.forEach(itemData -> sb.append(itemData.slot()).append("-").append(itemData.itemBuilder().getType()).append(" "));

        return sb.toString();
    }


    @Override
    public String toString() {
        return "InventoryContents{" +
                "player=" + player +
                ", inventory=" + inventory.toString() +
                ", inventoryData.size=" + inventoryData.size() +
                ", inventoryData.isEmpty=" + inventoryData.isEmpty() +
                '}';
    }

    @Override
    public InventoryContents clone() {
        try {
            InventoryContents clone = (InventoryContents) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


}
