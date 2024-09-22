package fr.marodeur.expertbuild.object.guibuilder;

import fr.marodeur.expertbuild.object.ItemBuilder;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ItemData {


    private final int slot;
    private final ItemStack itemBuilder;
    private Consumer<InventoryClickEvent> defaultConsumer;

    private boolean update;

    public ItemData(int slot, ItemStack itemStack, Consumer<InventoryClickEvent> eventConsumer) {
        this.slot = slot;
        this.itemBuilder = itemStack;
        this.defaultConsumer = eventConsumer;
    }

    public ItemData(int slot, ItemBuilder itemBuilder, Consumer<InventoryClickEvent> eventConsumer) {
        this.slot = slot;
        this.itemBuilder = itemBuilder.build();
        this.defaultConsumer = eventConsumer;
    }

    public ItemData(int slot, ItemBuilder itemBuilder) {
        this(slot, itemBuilder.build(), null);
    }

    public ItemData(int row, int column, ItemStack itemStack) {
        this(row * 9 + column, itemStack, null);
    }

    public ItemData(int row, int column, ItemBuilder itemBuilder) {
        this(row * 9 + column, itemBuilder.build(), null);
    }

    public ItemData(int row, int column, ItemStack itemStack, Consumer<InventoryClickEvent> eventConsumer) {
        this(row * 9 + column, itemStack, eventConsumer);
    }

    public ItemData(int row, int column, ItemBuilder itemBuilder, Consumer<InventoryClickEvent> eventConsumer) {
        this(row * 9 + column, itemBuilder.build(), eventConsumer);
    }

    public ItemData(int slot, ItemStack itemBuilder) {
        this.slot = slot;
        this.itemBuilder = itemBuilder;
    }

    public ItemData() {
        this.slot = Integer.MAX_VALUE;
        this.itemBuilder = null;
    }

    public List<ItemData> itemDataList(Integer[] slot, ItemStack itemStack) {

        List<ItemData> itemDataList = new ArrayList<>(slot.length);

        for (int i : slot) {
            itemDataList.add(new ItemData(i, itemStack, null));
        }
        return itemDataList;
    }


    public int slot() {
        return slot;
    }

    public ItemStack itemBuilder() {
        return itemBuilder;
    }

    public Consumer<InventoryClickEvent> defaultConsumer() {
        return defaultConsumer;
    }

    public boolean update() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    /**
     * Removes the consumer from an IntelligentItem
     */
    public void clearConsumer() {
        this.defaultConsumer = event -> {
        };
    }

    @Override
    public String toString() {
        return "ItemData{" +
                "slot=" + slot +
                ", itemBuilder=" + itemBuilder +
                ", defaultConsumer=" + defaultConsumer +
                ", update=" + update +
                '}';
    }
}
