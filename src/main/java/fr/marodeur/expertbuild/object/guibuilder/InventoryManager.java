package fr.marodeur.expertbuild.object.guibuilder;

import fr.marodeur.expertbuild.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class InventoryManager {

    private static final HashMap<UUID, InventoryContents> content;
    private static final HashMap<UUID, Inventory> inventories;


    static {
        content = new HashMap<>();
        inventories = new HashMap<>();
    }

    public void invoke() {
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), Main.getInstance());
    }

    protected void removeInventoryFromPlayer(UUID uuid) {
        content.remove(uuid);
        inventories.remove(uuid);
    }

    public void setContents(UUID uuid, InventoryContents contents) {
        content.put(uuid, contents);
    }

    protected void setInventory(UUID uuid, Inventory inventory) {

        inventories.put(uuid, inventory);
    }

    protected void removeInventory(UUID uuid) {

        inventories.remove(uuid);
    }

    protected boolean hasInventory(UUID uuid) {

        return inventories.containsKey(uuid);
    }

    protected Inventory getInventory(UUID uuid) {

        return inventories.get(uuid);
    }

    public void updateInventoryInterface(UUID uuid) {

        org.bukkit.inventory.Inventory inventory = inventories.get(uuid).inventories();

        content.get(uuid).inventoryData()
                .stream()
                .filter(ItemData::update)
                .filter(itemData -> !inventories.get(uuid).ignoreSlot().contains(itemData.slot()))
                .forEach(itemData -> inventory.setItem(itemData.slot(), itemData.itemBuilder()));

    }


    protected void invokeScheduler(Player player, Inventory inventory) {

        if (!inventory.updateTask()) return;

        new BukkitRunnable() {
            @Override
            public void run() {

                if (!hasInventory(player.getUniqueId())) {

                    this.cancel();
                    return;
                }

                Inventory savedInventory = inventories.get(player.getUniqueId());

                if (savedInventory != inventory) {

                    this.cancel();
                    return;
                }

                savedInventory.getInventoryProvider().update(player, content.get(player.getUniqueId()));

                updateInventoryInterface(player.getUniqueId());

                player.updateInventory();

            }
        }.runTaskTimer(Main.getInstance(), inventory.delay(), inventory.period());
    }


    public class InventoryListener implements Listener {

        @EventHandler(priority = EventPriority.LOWEST)
        public void onInventoryClick(InventoryClickEvent event) {

            HumanEntity whoClicked = event.getWhoClicked();

            if (!(whoClicked instanceof Player p)) return;
            if (!hasInventory(p.getUniqueId())) return;
            if (event.getClickedInventory() == null) return;

            int slot = event.getSlot();
            Inventory inventory = inventories.get(p.getUniqueId());
            InventoryContents contents = content.get(p.getUniqueId());
            ItemData itemData = contents.getItemData(slot);

            EventBuilder<InventoryClickEvent> customEvent = (EventBuilder<InventoryClickEvent>) inventory.getEvent(InventoryClickEvent.class);

            if (customEvent != null)
                customEvent.accept(event);

            if (itemData.defaultConsumer() != null) {

                // if is not player inventory
                if (! event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {

                    itemData.defaultConsumer().accept(event);

                    updateInventoryInterface(p.getUniqueId());
                    p.updateInventory();
                }
            }
            event.setCancelled(true);
        }

        @EventHandler(priority = EventPriority.LOWEST)
        public void onPluginDisable(@NotNull PluginDisableEvent event) {

            Plugin disabledPlugin = event.getPlugin();

            if (disabledPlugin != Main.getInstance()) return;


            Bukkit.getOnlinePlayers().forEach(player -> {

                if (!hasInventory(player.getUniqueId())) return;

                Inventory inventory = inventories.get(player.getUniqueId());

                inventory.getInventoryProvider().close(player, inventory);


            });
        }

        @EventHandler(priority = EventPriority.LOWEST)
        public void onInventoryClose(InventoryCloseEvent event) {

            if (!(event.getPlayer() instanceof Player p)) return;

            if (!hasInventory(p.getUniqueId())) {

                return;
            }

            Inventory mainInventory = inventories.get(p.getUniqueId());

            if (!mainInventory.isCloseable()) {
                mainInventory.open(p);
                return;
            }

            /*EventBuilder<InventoryCloseEvent> customEvent = (EventBuilder<InventoryCloseEvent>) mainInventory.getEvent(InventoryCloseEvent.class);
            if (customEvent != null) {
                customEvent.accept(event);
                mainInventory.clearData(p);
                return;
            }*/

            mainInventory.getInventoryProvider().close(p, mainInventory);

            mainInventory.close(p);
        }


        @EventHandler(priority = EventPriority.LOWEST)
        public void onPlayerQuit(@NotNull PlayerQuitEvent event) {

            Player p = event.getPlayer();

            if (!hasInventory(p.getUniqueId()))
                return;

            Inventory mainInventory = inventories.get(p.getUniqueId());

            mainInventory.getInventoryProvider().close(p, mainInventory);
            mainInventory.close(p);

        }
    }
}
