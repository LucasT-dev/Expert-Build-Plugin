package fr.marodeur.expertbuild.object.guibuilder;

import fr.marodeur.expertbuild.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnegative;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Inventory {

    Component title;
    private InventoryProvider inventoryProvider;
    private static InventoryManager manager;
    private org.bukkit.inventory.Inventory inventories;

    List<EventBuilder<? extends Event>> events = new CopyOnWriteArrayList<>();

    static {
        manager = Main.inv;
    }


    private int size = -1;
    private int period = 1;
    private int delay = 0;


    private boolean updateTask = true;
    private boolean isCloseable = true;


    private List<Integer> ignoreSlot = new ArrayList<>();



    public static InventoryBuilder build() {

        return new Inventory.InventoryBuilder();
    }


    public InventoryProvider getInventoryProvider() {
        return inventoryProvider;
    }

    public InventoryManager getManager() {
        return manager;
    }

    public org.bukkit.inventory.Inventory inventories() {
        return inventories;
    }

    public void setManager(InventoryManager inventoryManager) {
        manager = inventoryManager;
    }


    public void open(Player player) {
        Bukkit.getScheduler().runTask(Main.getInstance(), () -> initInventory(player));
    }



    private void initInventory(Player p) {


        closeOldInventory(p);


        InventoryContents contents = new InventoryContents(p, this);

        getInventoryProvider().init(p, contents);

        getManager().setInventory(p.getUniqueId(), this);
        getManager().setContents(p.getUniqueId(), contents);

        getManager().invokeScheduler(p, this);

        inventories = Bukkit.createInventory(p, size(), this.title);

        // Place les items dans l'inventaire
        placeItem(contents);
        //

        p.openInventory(inventories);

    }

    public void close(Player p) {

        getManager().removeInventoryFromPlayer(p.getUniqueId());

        p.closeInventory();

    }

    public void placeItem(InventoryContents contents) {


        contents.inventoryData().forEach(itemData -> this.inventories.setItem(itemData.slot(), itemData.itemBuilder()));


    }

    public void closeOldInventory(Player p) {


        if (getManager().hasInventory(p.getUniqueId())) {
            Inventory mainInventory = getManager().getInventory(p.getUniqueId());
            mainInventory.close(p);
        }
    }



    public Component title() {
        return title;
    }

    public int size() {
        return size;
    }

    public int period() {
        return period;
    }

    public int delay() {
        return delay;
    }

    public boolean updateTask() {
        return updateTask;
    }

    public boolean isCloseable() {
        return isCloseable;
    }

    public List<Integer> ignoreSlot() {
        return ignoreSlot;
    }

    public @Nullable EventBuilder<? extends Event> getEvent(Class<? extends Event> event) {
        return this.events.stream().filter(eventOne -> event == eventOne.gettClass())
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "InventoryBuilder{" +
                "updateTask=" + updateTask +
                "period=" + period +
                "delay=" + delay +
                "size=" + size +
                "isCloseable" + isCloseable +
                //"title=" + title.toString() +
                //", inventories="  +
                '}';
    }


    public static class InventoryBuilder {

        private final Inventory inventory = new Inventory();



        public InventoryBuilder() {

            inventory.setManager(new InventoryManager());

        }

        public InventoryBuilder setTitle(Component title) {
            this.inventory.title = title;
            return this;
        }

        public InventoryBuilder setTitle(String title) {
            this.inventory.title = Component.text(title);
            return this;
        }


        public InventoryBuilder setSize(int size) {
            this.inventory.size = size;
            return this;
        }

        public InventoryBuilder rows(@Nonnegative int rows) throws IllegalArgumentException {
            if (rows > 6)
                throw new IllegalArgumentException("The rows can not be greater than 6");

            size(rows * 9);
            return this;
        }

        public InventoryBuilder size(@Nonnegative int size) throws IllegalArgumentException {
            if (size < 9 || size > 54)
                throw new IllegalArgumentException(size < 9 ? "The size can not be less than 9" : "The size can not be greater than 54");

            this.inventory.size = size;
            return this;
        }

        public InventoryBuilder period(int period) {
            this.inventory.period = period;
            return this;
        }

        public InventoryBuilder delay(int delay) {
            this.inventory.delay = delay;
            return this;
        }

        public InventoryBuilder updateTask(boolean updateTask) {
            this.inventory.updateTask = updateTask;
            return this;
        }

        public InventoryBuilder closeable(boolean closeable) {
            this.inventory.isCloseable = closeable;
            return this;
        }

        public InventoryBuilder ignoreSlot(List<Integer> ignoreSlot) {
            this.inventory.ignoreSlot = ignoreSlot;
            return this;
        }

        public InventoryBuilder listener(EventBuilder<? extends Event> eventBuilder) {
            this.inventory.events.add(eventBuilder);

            return this;
        }

        public InventoryBuilder provider(InventoryProvider inventoryProvider) {
            this.inventory.inventoryProvider = inventoryProvider;
            return this;
        }

        public Inventory build() {
            return this.inventory;
        }


    /*public InventoryBuilder listener(EventCreator<? extends Event> event) {
        this.inventory.events.add(event);
        return this;
    }*/

        @Override
        public String toString() {
            return "InventoryBuilder{" +
                    "updateTask=" + inventory.updateTask +
                    "period=" + inventory.period +
                    "delay=" + inventory.delay +
                    "size=" + inventory.size +
                    "title=" + inventory.title +
                    '}';
        }
    }
}
