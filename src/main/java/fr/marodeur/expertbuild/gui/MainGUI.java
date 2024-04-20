package fr.marodeur.expertbuild.gui;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.ItemBuilder;
import fr.marodeur.expertbuild.object.Message;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MainGUI {

    public void openMainInventory(Player p) {

        RyseInventory.builder()
                .title(new Message.MessageSender("expbuild.message.gui.main_gui_title", false).getMessage())
                .rows(3)
                .disableUpdateTask()
                .provider(new InventoryProvider() {

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);

                        if (brushBuilder == null) {
                            p.sendMessage(new Message.MessageSender("expbuild.message.permission.no_permission_node", true, new String[]{"exp.register"}).getMessage());
                            return;
                        }

                        contents.set(7, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.organic_gui_title", false, Material.BONE, 1)
                                        .addLore("expbuild.message.gui.organic_item", false)
                                        .build(),
                                event -> {

                            if (p.hasPermission("exp.gui.orga")) {
                                new OrganicGUI().openOrganicGUI(p);
                            } else {
                                brushBuilder.sendMessage("expbuild.message.gui.organic_item", true, new String[]{"exp.gui.orga"});
                            }
                        }));

                        contents.set(13, IntelligentItem.empty(new ItemBuilder("EXP_Build Main", Material.HONEYCOMB, 1)
                                .addLore("expbuild.message.gui.main_item_1", false)
                                .addLore("expbuild.message.gui.main_item_2", false)
                                .addLore("expbuild.message.gui.main_item_3", false)
                                .build()));

                        contents.set(19, IntelligentItem.of(new ItemBuilder("EXP_Build Gmask", Material.SPONGE, 1)
                                .addLore("Easily configure my gmask")
                                .build(),
                                event -> {

                        }));

                        contents.set(25, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.flower_gui_title", false, Material.SUNFLOWER, 1)
                                        .addLore("expbuild.message.gui.flower_item", false)
                                        .build(),
                                event -> {
                                    if (p.hasPermission("exp.gui.flower")) {
                                        new FlowerGUI().openFlowerInventory(p);
                                    } else {
                                        brushBuilder.sendMessage("expbuild.message.permission.no_permission_node", true, new String[]{"exp.gui.flower"});
                                    }
                                }));

                        contents.set(1, IntelligentItem.of(new ItemBuilder("expbuild.message.gui.leather_gui_title", false, Material.LEATHER_CHESTPLATE, 1)
                                        .addLore("expbuild.message.gui.leather_item", false)
                                        .build(),
                                event -> {
                                    if (p.hasPermission("exp.gui.leather")) {
                                        new LeatherGUI().openLeatherGUI(p);
                                    } else {
                                        brushBuilder.sendMessage("expbuild.message.permission.no_permission_node", true, new String[]{"exp.gui.leather"});
                                    }
                                }));
                    }
                })
                .build(Main.getInstance())
                .open(p);
    }
}