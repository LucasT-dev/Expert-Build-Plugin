package fr.marodeur.expertbuild.gui;

import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.ItemBuilder;
import fr.marodeur.expertbuild.object.Message;

import fr.marodeur.expertbuild.object.builderObjects.LeatherParameter;
import fr.marodeur.expertbuild.object.guibuilder.Inventory;

import fr.marodeur.expertbuild.object.guibuilder.InventoryContents;
import fr.marodeur.expertbuild.object.guibuilder.InventoryProvider;
import fr.marodeur.expertbuild.object.guibuilder.ItemData;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LeatherGUI {

    //public static final String RightArrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlkZGRhM2RkMTkxZDYwNTk5MDA3MDBlZDBjYWY1NGZjNzdjM2Q4MTVhYTI5NDZiYzA5YjY5YWIyZjJmZjk5NiJ9fX0=";
    public static final  String RightArrow = "16227036b8afed6935d53143d16488d39cf4fb73a671f2b2955e80fc9dfe458";

    public void openLeatherGUI(Player p) {

        Inventory.build()
                .setTitle(new Message.MessageSender("expbuild.message.gui.leather_gui_title", true).getMessage())
                .rows(6)
                .updateTask(false)
                .provider(new InventoryProvider() {

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        LeatherParameter leatherParameter = BrushBuilder.getBrushBuilderPlayer(p).getLeatherParameter();

                        contents.set(new ItemData(0, 8, new ItemBuilder("expbuild.message.gui.back", false, Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(RightArrow)
                                        .addLore("expbuild.message.gui.back", false)
                                        .build(),
                                event -> new MainGUI().openMainInventory(p)));


                        contents.set(new ItemData(1,1, new ItemBuilder("expbuild.message.gui.leather_helmet", false, Material.LEATHER_HELMET, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(5, event.getCurrentItem())));

                        contents.set(new ItemData(2,1, new ItemBuilder("expbuild.message.gui.leather_chestplate", false, Material.LEATHER_CHESTPLATE, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(6, event.getCurrentItem())));

                        contents.set(new ItemData(3,1, new ItemBuilder("expbuild.message.gui.leather_leggings", false, Material.LEATHER_LEGGINGS, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(7, event.getCurrentItem())));

                        contents.set(new ItemData(4,1, new ItemBuilder("expbuild.message.gui.leather_boots", false, Material.LEATHER_BOOTS, 1)
                                        .setLeatherColor(leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue())
                                        .build(),
                                event -> p.getInventory().setItem(8, event.getCurrentItem())));


                        // COLOR
                        contents.set(new ItemData(4,4, new ItemBuilder("expbuild.message.gui.red_color", false, Material.RED_DYE, leatherParameter.getRed() == 0 ? 1: leatherParameter.getRed())
                                        .addLore("expbuild.message.gui.red", false, new String[]{String.valueOf(leatherParameter.getRed())})
                                        .build(),
                                event -> {
                            leatherParameter.setRed(calculateNewColorLevel(leatherParameter.getRed(), event.isShiftClick(), event.isRightClick()));
                            contents.updateLore(4, 4, 0, "expbuild.message.gui.red", false, new String[]{String.valueOf(leatherParameter.getRed())});
                            contents.updateAmount(4, 4, leatherParameter.getRed() == 0 ? 1: leatherParameter.getRed());
                                    updateLeather(contents, leatherParameter);
                                }));

                        contents.set(new ItemData(4,5, new ItemBuilder("expbuild.message.gui.green_color", false, Material.GREEN_DYE, leatherParameter.getGreen() == 0 ? 1: leatherParameter.getGreen())
                                        .addLore("expbuild.message.gui.green", false, new String[]{String.valueOf(leatherParameter.getGreen())})
                                        .build(),
                                event -> {
                            leatherParameter.setGreen(calculateNewColorLevel(leatherParameter.getGreen(), event.isShiftClick(), event.isRightClick()));
                            contents.updateLore(4,5, 0, "expbuild.message.gui.green", false, new String[]{String.valueOf(leatherParameter.getGreen())});
                            contents.updateAmount(4, 5, leatherParameter.getGreen() == 0 ? 1: leatherParameter.getGreen());
                                    updateLeather(contents, leatherParameter);
                                }));

                        contents.set(new ItemData(4,6, new ItemBuilder("expbuild.message.gui.blue_color", false, Material.BLUE_DYE, leatherParameter.getBlue() == 0 ? 1: leatherParameter.getBlue())
                                        .addLore("expbuild.message.gui.blue", false, new String[]{String.valueOf(leatherParameter.getBlue())})
                                        .build(),
                                event -> {
                            leatherParameter.setBlue(calculateNewColorLevel(leatherParameter.getBlue(), event.isShiftClick(), event.isRightClick()));
                            contents.updateLore(4,6, 0, "expbuild.message.gui.blue", false, new String[]{String.valueOf(leatherParameter.getBlue())});
                            contents.updateAmount(4, 6, leatherParameter.getBlue() == 0 ? 1: leatherParameter.getBlue());
                                    updateLeather(contents, leatherParameter);
                                }));
                    }

                    @Override
                    public void update(Player player, InventoryContents contents) {}

                    @Override
                    public void close(Player player, Inventory inventory) {}

                })
                .build()
                .open(p);
    }

    private void updateLeather(InventoryContents contents, LeatherParameter leatherParameter) {

        contents.updateLeatherColor(1, 1, leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue());

        contents.updateLeatherColor(2, 1, leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue());

        contents.updateLeatherColor(3, 1, leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue());

        contents.updateLeatherColor(4, 1, leatherParameter.getRed(), leatherParameter.getGreen(), leatherParameter.getBlue());

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