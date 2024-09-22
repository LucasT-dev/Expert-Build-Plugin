package fr.marodeur.expertbuild.gui;

import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.ItemBuilder;
import fr.marodeur.expertbuild.object.Message;

import fr.marodeur.expertbuild.object.guibuilder.Inventory;
import fr.marodeur.expertbuild.object.guibuilder.InventoryContents;
import fr.marodeur.expertbuild.object.guibuilder.InventoryProvider;
import fr.marodeur.expertbuild.object.guibuilder.ItemData;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MainGUI {

    public void openMainInventory(Player p) {

        Inventory.build()
                .setTitle(new Message.MessageSender("expbuild.message.gui.main_gui_title", false).getMessage())
                .rows(3)
                .updateTask(false)
                .provider(new InventoryProvider() {

                    @Override
                    public void update(Player player, InventoryContents contents) {}

                    @Override
                    public void close(Player player, Inventory inventory) {}

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);

                        if (brushBuilder == null) {
                            p.sendMessage(new Message.MessageSender("expbuild.message.permission.no_permission_node", true, new String[]{"exp.register"}).getMessage());
                            return;
                        }

                        contents.set(new ItemData(7, new ItemBuilder("expbuild.message.gui.organic_gui_title", false, Material.BONE, 1)
                                        .addLore("expbuild.message.gui.organic_item", false)
                                        .build(),
                                event -> {

                            if (p.hasPermission("exp.gui.orga")) {
                                new OrganicGUI().openOrganicGUI(p);
                            } else {
                                brushBuilder.sendMessage("expbuild.message.gui.organic_item", true, new String[]{"exp.gui.orga"});
                            }
                        }));

                        contents.set(new ItemData(13, new ItemBuilder("EXP_Build Main", Material.HONEYCOMB, 1)
                                .addLore("expbuild.message.gui.main_item_1", false)
                                .addLore("expbuild.message.gui.main_item_2", false)
                                .addLore("expbuild.message.gui.main_item_3", false)
                                .build()));

                        contents.set(new ItemData(19, new ItemBuilder("EXP_Build Gmask", Material.SPONGE, 1)
                                .addLore("Easily configure my gmask")
                                .build(),
                                event -> {

                        }));

                        contents.set(new ItemData(25, new ItemBuilder("expbuild.message.gui.flower_gui_title", false, Material.SUNFLOWER, 1)
                                        .addLore("expbuild.message.gui.flower_item", false)
                                        .build(),
                                event -> {
                                    if (p.hasPermission("exp.gui.flower")) {
                                        new FlowerGUI().openFlowerInventory(p);
                                    } else {
                                        brushBuilder.sendMessage("expbuild.message.permission.no_permission_node", true, new String[]{"exp.gui.flower"});
                                    }
                                }));

                        contents.set(new ItemData(1, new ItemBuilder("expbuild.message.gui.leather_gui_title", false, Material.LEATHER_CHESTPLATE, 1)
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
                .build()
                .open(p);
    }
}