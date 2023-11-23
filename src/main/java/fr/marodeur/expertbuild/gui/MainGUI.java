package fr.marodeur.expertbuild.gui;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.MessageBuilder;
import fr.marodeur.expertbuild.object.ItemBuilder;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MainGUI {

    private static final MessageBuilder msg = Main.getInstance().getMessageConfig();

    public void openMainInventory(Player p) {

        RyseInventory.builder()
                .title(msg.getMainGuiTitle())
                .rows(3)
                .disableUpdateTask()
                .provider(new InventoryProvider() {

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);

                        if (brushBuilder == null) {
                            p.sendMessage(Main.prefix + msg.getNoPermissionNode("exp.register"));
                            return;
                        }

                        contents.set(7, IntelligentItem.of(new ItemBuilder(msg.getOrganicGuiTitle(), Material.BONE, 1)
                                        .addLore(msg.getOrganicItem())
                                        .build(),
                                event -> {

                            if (p.hasPermission("exp.gui.orga")) {
                                new OrganicGUI().openOrganicGUI(p);
                            } else {
                                brushBuilder.sendMessage(msg.getNoPermissionNode("exp.gui.orga"));
                            }
                        }));

                        contents.set(13, IntelligentItem.empty(new ItemBuilder("EXP_Build Main", Material.HONEYCOMB, 1)
                                .addLore(msg.getMainItem1())
                                .addLore(msg.getMainItem2())
                                .addLore(msg.getMainItem3())
                                .build()));

                        contents.set(19, IntelligentItem.of(new ItemBuilder("EXP_Build Gmask", Material.SPONGE, 1)
                                .addLore("Easily configure my gmask")
                                .build(),
                                event -> {

                        }));

                        contents.set(25, IntelligentItem.of(new ItemBuilder(msg.getFlowerGuiTitle(), Material.SUNFLOWER, 1)
                                        .addLore(msg.getFlowerItem())
                                        .build(),
                                event -> {
                                    if (p.hasPermission("exp.gui.flower")) {
                                        new FlowerGUI().openFlowerInventory(p);
                                    } else {
                                        brushBuilder.sendMessage(msg.getNoPermissionNode("exp.gui.flower"));
                                    }
                                }));

                        contents.set(1, IntelligentItem.of(new ItemBuilder(msg.getLeatherGuiTitle(), Material.LEATHER_CHESTPLATE, 1)
                                        .addLore(msg.getLeatherItem())
                                        .build(),
                                event -> {
                                    if (p.hasPermission("exp.gui.leather")) {
                                        new LeatherGUI().openLeatherGUI(p);
                                    } else {
                                        brushBuilder.sendMessage(msg.getNoPermissionNode("exp.gui.leather"));
                                    }
                                }));
                    }
                })
                .build(Main.getInstance())
                .open(p);
    }
}