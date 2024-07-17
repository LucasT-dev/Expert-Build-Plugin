package fr.marodeur.expertbuild.gui;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.ItemBuilder;
import fr.marodeur.expertbuild.object.Message;

import fr.marodeur.expertbuild.object.builderObjects.LeatherParameter;
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

    public void openLeatherGUI(Player p) {

        RyseInventory.builder()
                .title(new Message.MessageSender("expbuild.message.gui.leather_gui_title", true).getMessage())
                .rows(6)
                .period(1, TimeSetting.MILLISECONDS)
                .provider(new InventoryProvider() {

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        LeatherParameter leatherParameter = BrushBuilder.getBrushBuilderPlayer(p,false).getLeatherParameter();

                        contents.set(0,8, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.back", false, Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(RightArrow)
                                        .addLore("expbuild.message.gui.back", false)
                                        .build(),
                                event -> new MainGUI().openMainInventory(p)));


                        contents.set(1,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_helmet", false, Material.LEATHER_HELMET, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(5, event.getCurrentItem())));

                        contents.set(2,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_chestplate", false, Material.LEATHER_CHESTPLATE, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(6, event.getCurrentItem())));

                        contents.set(3,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_leggings", false, Material.LEATHER_LEGGINGS, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(7, event.getCurrentItem())));

                        contents.set(4,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_boots", false, Material.LEATHER_BOOTS, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(8, event.getCurrentItem())));


                        contents.set(4,4, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.red_color", false, Material.RED_DYE, leatherParameter.getRed() == 0 ? 1: leatherParameter.getRed())
                                        .addLore("expbuild.message.gui.red", false, new String[]{String.valueOf(leatherParameter.getRed())})
                                        .build(),
                                event -> leatherParameter.setRed(calculateNewColorLevel(leatherParameter.getRed(), event.isShiftClick(), event.isRightClick()))));

                        contents.set(4,5, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.green_color", false, Material.GREEN_DYE, leatherParameter.getGreen() == 0 ? 1: leatherParameter.getGreen())
                                        .addLore("expbuild.message.gui.green", false, new String[]{String.valueOf(leatherParameter.getGreen())})
                                        .build(),
                                event -> leatherParameter.setGreen(calculateNewColorLevel(leatherParameter.getGreen(), event.isShiftClick(), event.isRightClick()))));



                        contents.set(4,6, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.blue_color", false, Material.BLUE_DYE, leatherParameter.getBlue() == 0 ? 1: leatherParameter.getBlue())
                                        .addLore("expbuild.message.gui.blue", false, new String[]{String.valueOf(leatherParameter.getBlue())})
                                        .build(),
                                event -> leatherParameter.setBlue(calculateNewColorLevel(leatherParameter.getBlue(), event.isShiftClick(), event.isRightClick()))));
                    }

                    @Override
                    public void update(Player player, InventoryContents contents) {

                        LeatherParameter leatherParameter = BrushBuilder.getBrushBuilderPlayer(p,false).getLeatherParameter();

                        contents.update(1,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_helmet", false, Material.LEATHER_HELMET, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(5, event.getCurrentItem())));

                        contents.update(2,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_chestplate", false, Material.LEATHER_CHESTPLATE, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(6, event.getCurrentItem())));

                        contents.update(3,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_leggings", false, Material.LEATHER_LEGGINGS, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(7, event.getCurrentItem())));

                        contents.update(4,1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_boots", false, Material.LEATHER_BOOTS, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(8, event.getCurrentItem())));



                        contents.update(4,4, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.red_color", false, Material.RED_DYE, leatherParameter.getRed() == 0 ? 1: leatherParameter.getRed())
                                        .addLore("expbuild.message.gui.red", false, new String[]{String.valueOf(leatherParameter.getRed())})
                                        .build(),
                                event -> leatherParameter.setRed(calculateNewColorLevel(leatherParameter.getRed(), event.isShiftClick(), event.isRightClick()))));

                        contents.update(4,5, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.green_color", false, Material.GREEN_DYE, leatherParameter.getGreen() == 0 ? 1: leatherParameter.getGreen())
                                        .addLore("expbuild.message.gui.green", false, new String[]{String.valueOf(leatherParameter.getGreen())})
                                        .build(),
                                event -> leatherParameter.setGreen(calculateNewColorLevel(leatherParameter.getGreen(), event.isShiftClick(), event.isRightClick()))));

                        contents.update(4,6, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.blue_color", false, Material.BLUE_DYE, leatherParameter.getBlue() == 0 ? 1: leatherParameter.getBlue())
                                        .addLore("expbuild.message.gui.blue", false, new String[]{String.valueOf(leatherParameter.getBlue())})
                                        .build(),
                                event -> leatherParameter.setBlue(calculateNewColorLevel(leatherParameter.getBlue(), event.isShiftClick(), event.isRightClick()))));
                    }
                })
                .build(Main.getInstance())
                .open(p);
    }

    private short calculateNewColorLevel(short num, boolean hasShiftClick, boolean hasRightClick ) {

        short n = num;

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