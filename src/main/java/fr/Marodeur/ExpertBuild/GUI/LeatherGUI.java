package fr.Marodeur.ExpertBuild.GUI;

import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.ItemBuilder;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LeatherGUI {

    private static final MessageBuilder msg = Main.getInstance().getMessageConfig();
    public static final String RightArrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlkZGRhM2RkMTkxZDYwNTk5MDA3MDBlZDBjYWY1NGZjNzdjM2Q4MTVhYTI5NDZiYzA5YjY5YWIyZjJmZjk5NiJ9fX0=";

    public static int RED = 1;
    public static int GREEN = 1;
    public static int BLUE = 1;

    public void openLeatherGUI(Player p) {

        RyseInventory.builder()
                .title(Main.prefix + msg.getLeatherGuiTitle())
                .rows(6)
                .provider(new InventoryProvider() {

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        contents.set(0,8, IntelligentItem.of(new ItemBuilder(msg.getBack(), Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(RightArrow)
                                        .addLore(msg.getBack())
                                        .build(),
                                event -> new MainGUI().openMainInventory(p)));


                        contents.set(1,1, IntelligentItem.of(new ItemBuilder(msg.getLeatherHelmet(), Material.LEATHER_HELMET, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(5, event.getCurrentItem())));

                        contents.set(2,1, IntelligentItem.of(new ItemBuilder(msg.getLeatherChestplate(), Material.LEATHER_CHESTPLATE, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(6, event.getCurrentItem())));

                        contents.set(3,1, IntelligentItem.of(new ItemBuilder(msg.getLeatherLeggings(), Material.LEATHER_LEGGINGS, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(7, event.getCurrentItem())));

                        contents.set(4,1, IntelligentItem.of(new ItemBuilder(msg.getLeatherBoots(), Material.LEATHER_BOOTS, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(8, event.getCurrentItem())));


                        contents.set(4,4, IntelligentItem.of(new ItemBuilder(msg.getRedColor(), Material.RED_DYE, RED == 0 ? 1: RED)
                                        .addLore(msg.getRed(String.valueOf(RED)))
                                        .build(),
                                event -> RED = calculateNewColorLevem(RED, event.isShiftClick(), event.isRightClick())));

                        contents.set(4,5, IntelligentItem.of(new ItemBuilder(msg.getGreenColor(), Material.GREEN_DYE, GREEN == 0 ? 1: GREEN)
                                        .addLore(msg.getGreen(String.valueOf(GREEN)))
                                        .build(),
                                event -> GREEN = calculateNewColorLevem(GREEN, event.isShiftClick(), event.isRightClick())));



                        contents.set(4,6, IntelligentItem.of(new ItemBuilder(msg.getBlueColor(), Material.BLUE_DYE, BLUE == 0 ? 1: BLUE)
                                        .addLore(msg.getBlue(String.valueOf(BLUE)))
                                        .build(),
                                event -> BLUE = calculateNewColorLevem(BLUE, event.isShiftClick(), event.isRightClick())));
                    }

                    @Override
                    public void update(Player player, InventoryContents contents) {

                        contents.update(1,1, IntelligentItem.of(new ItemBuilder(msg.getLeatherHelmet(), Material.LEATHER_HELMET, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(5, event.getCurrentItem())));

                        contents.update(2,1, IntelligentItem.of(new ItemBuilder(msg.getLeatherChestplate(), Material.LEATHER_CHESTPLATE, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(6, event.getCurrentItem())));

                        contents.update(3,1, IntelligentItem.of(new ItemBuilder(msg.getLeatherLeggings(), Material.LEATHER_LEGGINGS, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(7, event.getCurrentItem())));

                        contents.update(4,1, IntelligentItem.of(new ItemBuilder(msg.getLeatherBoots(), Material.LEATHER_BOOTS, 1)
                                        .setLeatherColor(RED, GREEN, BLUE)
                                        .build(),
                                event -> p.getInventory().setItem(8, event.getCurrentItem())));



                        contents.update(4,4, IntelligentItem.of(new ItemBuilder(msg.getRedColor(), Material.RED_DYE, RED == 0 ? 1: RED)
                                        .addLore(msg.getRed(String.valueOf(RED)))
                                        .build(),
                                event -> RED = calculateNewColorLevem(RED, event.isShiftClick(), event.isRightClick())));

                        contents.update(4,5, IntelligentItem.of(new ItemBuilder(msg.getGreenColor(), Material.GREEN_DYE, GREEN == 0 ? 1: GREEN)
                                        .addLore(msg.getGreen(String.valueOf(GREEN)))
                                        .build(),
                                event -> GREEN = calculateNewColorLevem(GREEN, event.isShiftClick(), event.isRightClick())));

                        contents.update(4,6, IntelligentItem.of(new ItemBuilder(msg.getBlueColor(), Material.BLUE_DYE, BLUE == 0 ? 1: BLUE)
                                        .addLore(msg.getBlue(String.valueOf(BLUE)))
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