package fr.marodeur.expertbuild.gui;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.ItemBuilder;

import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GmaskGUI {

    public void openGmaskGUI(Player p) {

        RyseInventory.builder()
                .title(Main.prefix + "Organic")
                .rows(3)
                .provider(new InventoryProvider() {

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        contents.fillRow(1, new ItemBuilder("", Material.YELLOW_STAINED_GLASS_PANE, 1)
                                        .build());

                    }
                })
                .build(Main.getInstance())
                .open(p);
    };

    /*public static final SmartInventory GMASK = SmartInventory.builder()
            .id("customInventory")
            .provider(new GmaskGUI())
            .manager(Main.getInvManager())
            .size(3, 9)
            .title(Main.prefix + "Gmask")
            .closeable(true)
            .build();

    @Override
    public void init(@NotNull Player p, @NotNull InventoryContents contents) {

        contents.set(1,1, ClickableItem.of(new ItemBuilder("test", Material.STONE, 1)
                        .addLore("")
                        .build(),
                event -> {

                }));

    }

    @Override
    public void update(Player p, @NotNull InventoryContents contents) {

    }*/
}