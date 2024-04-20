package fr.marodeur.expertbuild.gui;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.ItemBuilder;
import fr.marodeur.expertbuild.object.Message;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.enums.TimeSetting;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LeatherGUI {

    //public static final String RightArrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlkZGRhM2RkMTkxZDYwNTk5MDA3MDBlZDBjYWY1NGZjNzdjM2Q4MTVhYTI5NDZiYzA5YjY5YWIyZjJmZjk5NiJ9fX0=";
    public static final  String RightArrow = "16227036b8afed6935d53143d16488d39cf4fb73a671f2b2955e80fc9dfe458";

    public static int RED = 1;
    public static int GREEN = 1;
    public static int BLUE = 1;

    public void openLeatherGUI(Player p) {

        RyseInventory.builder()
                .title(new Message.MessageSender("expbuild.message.gui.leather_gui_title", true).getMessage())
                .rows(6)
                .period(1, TimeSetting.SECONDS)
                .provider(new InventoryProvider() {

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        contents.set(0,8, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.back", false, Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(RightArrow)
                                        .addLore("expbuild.message.gui.back", false)
                                        .build(),
                                event -> new MainGUI().openMainInventory(p)));


                        contents.set(1,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_helmet", false, Material.LEATHER_HELMET, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(5, event.getCurrentItem())));

                        contents.set(2,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_chestplate", false, Material.LEATHER_CHESTPLATE, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(6, event.getCurrentItem())));

                        contents.set(3,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_leggings", false, Material.LEATHER_LEGGINGS, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(7, event.getCurrentItem())));

                        contents.set(4,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_boots", false, Material.LEATHER_BOOTS, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(8, event.getCurrentItem())));


                        contents.set(4,4, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.red_color", false, Material.RED_DYE, RED == 0 ? 1: RED)
                                        .addLore("expbuild.message.gui.red", false, new String[]{String.valueOf(RED)})
                                        .build(),
                                event -> RED = calculateNewColorLevem(RED, event.isShiftClick(), event.isRightClick())));

                        contents.set(4,5, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.green_color", false, Material.GREEN_DYE, GREEN == 0 ? 1: GREEN)
                                        .addLore("expbuild.message.gui.green", false, new String[]{String.valueOf(GREEN)})
                                        .build(),
                                event -> GREEN = calculateNewColorLevem(GREEN, event.isShiftClick(), event.isRightClick())));



                        contents.set(4,6, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.blue_color", false, Material.BLUE_DYE, BLUE == 0 ? 1: BLUE)
                                        .addLore("expbuild.message.gui.blue", false, new String[]{String.valueOf(BLUE)})
                                        .build(),
                                event -> BLUE = calculateNewColorLevem(BLUE, event.isShiftClick(), event.isRightClick())));
                    }

                    @Override
                    public void update(Player player, InventoryContents contents) {

                        contents.update(1,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_helmet", false, Material.LEATHER_HELMET, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(5, event.getCurrentItem())));

                        contents.update(2,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_chestplate", false, Material.LEATHER_CHESTPLATE, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(6, event.getCurrentItem())));

                        contents.update(3,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_leggings", false, Material.LEATHER_LEGGINGS, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(7, event.getCurrentItem())));

                        contents.update(4,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_boots", false, Material.LEATHER_BOOTS, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(8, event.getCurrentItem())));



                        contents.update(4,4, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.red_color", false, Material.RED_DYE, RED == 0 ? 1: RED)
                                        .addLore("expbuild.message.gui.red", false, new String[]{String.valueOf(RED)})
                                        .build(),
                                event -> RED = calculateNewColorLevem(RED, event.isShiftClick(), event.isRightClick())));

                        contents.update(4,5, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.green_color", false, Material.GREEN_DYE, GREEN == 0 ? 1: GREEN)
                                        .addLore("expbuild.message.gui.green", false, new String[]{String.valueOf(GREEN)})
                                        .build(),
                                event -> GREEN = calculateNewColorLevem(GREEN, event.isShiftClick(), event.isRightClick())));

                        contents.update(4,6, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.blue_color", false, Material.BLUE_DYE, BLUE == 0 ? 1: BLUE)
                                        .addLore("expbuild.message.gui.blue", false, new String[]{String.valueOf(BLUE)})
                                        .build(),
                                event -> BLUE = calculateNewColorLevem(BLUE, event.isShiftClick(), event.isRightClick())));
                    }
                })
                .build(Main.getInstance())
                .open(p);
    }

    private int calculateNewColorLevem(int num, boolean hasShiftClick, boolean hasRightClick ) {

        int n = num;

        if (hasShiftClick) {

            if (hasRightClick) {
                n -= 10;
            } else n += 10;

        } else {

            if (hasRightClick) {
                n -= 1;
            } else n += 1;
        }

        if (n > 255) n = 255;
        if (n < 0) n = 1;

        return n;
    }
}