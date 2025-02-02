package fr.marodeur.expertbuild.gui;

import com.fastasyncworldedit.core.registry.state.PropertyKey;

import com.sk89q.worldedit.NotABlockException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.registry.state.Property;
import com.sk89q.worldedit.util.Direction;
import com.sk89q.worldedit.world.block.BaseBlock;

import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.brush.FlowerBrush;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.builderObjects.FlowerBrushParameter;
import fr.marodeur.expertbuild.object.guibuilder.*;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FlowerGUI {

    //public static final String RightArrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlkZGRhM2RkMTkxZDYwNTk5MDA3MDBlZDBjYWY1NGZjNzdjM2Q4MTVhYTI5NDZiYzA5YjY5YWIyZjJmZjk5NiJ9fX0=";

    private final String RightArrow = "16227036b8afed6935d53143d16488d39cf4fb73a671f2b2955e80fc9dfe458";

    public void openFlowerInventory(Player p) {

        Inventory.build()
                .setTitle(new Message.MessageSender("expbuild.message.gui.flower_gui_title", true).getMessage())
                .rows(6)
                .updateTask(false)
                .period(20)

                .listener(new EventBuilder<>(InventoryClickEvent.class, event -> {

                    if (event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {

                        ItemStack it = event.getCurrentItem();
                        assert it != null;

                        event.setCursor(it);
                    }

                }))

                .provider(new InventoryProvider() {

                    @Override
                    public void close(Player player, Inventory inventory) {
                        buildBrush(BrushBuilder.getBrushBuilderPlayer(p, false));
                    }

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);

                        FlowerBrushParameter fbp = brushBuilder.getFlowerBrushParameter();

                        // Exit return to main gui
                        contents.set(new ItemData(0, 8, new ItemBuilder(Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(RightArrow)
                                        .addLore("expbuild.message.gui.back", false)
                                        .build(),
                                event -> {

                                    buildBrush(brushBuilder);

                                    new MainGUI().openMainInventory(p);
                                }));

                        //FLOWER
                        contents.set(new ItemData(1,
                                new ItemBuilder("expbuild.message.gui.flower_gui_title", false, Material.HONEYCOMB, 1)
                                        .addLoreLineTest("expbuild.message.gui.brush_enable", "expbuild.message.gui.brush_disable", brushBuilder.getEnable(), false)
                                        .build(), event -> {

                                    brushBuilder.setEnable(!brushBuilder.getEnable());

                                    String lore = brushBuilder.getEnable() ? new Message.MessageSender("expbuild.message.gui.brush_enable", false).getMessage() : new Message.MessageSender("expbuild.message.gui.brush_disable", false).getMessage();

                                    contents.updateMaterial(0, brushBuilder.getEnable() ? Material.LIME_STAINED_GLASS_PANE: Material.RED_STAINED_GLASS_PANE);
                                    contents.updateLore(0, 0, lore);
                                    contents.updateLore(1, 0, lore);

                            }));

                        String lore = brushBuilder.getEnable() ? new Message.MessageSender("expbuild.message.gui.brush_enable", false).getMessage() : new Message.MessageSender("expbuild.message.gui.brush_disable", false).getMessage();

                        //SET BRUSH

                            contents.set(new ItemData(0,
                                    new ItemBuilder("expbuild.message.gui.flower_gui_title", false,
                                            brushBuilder.getEnable() ? Material.LIME_STAINED_GLASS_PANE: Material.RED_STAINED_GLASS_PANE, 1)
                                            .addLore(lore)
                                            .build(), event -> {

                                        brushBuilder.setEnable(!brushBuilder.getEnable());

                                        String loreUpdate = brushBuilder.getEnable() ? new Message.MessageSender("expbuild.message.gui.brush_enable", false).getMessage() : new Message.MessageSender("expbuild.message.gui.brush_disable", false).getMessage();

                                        contents.updateMaterial(0, brushBuilder.getEnable() ? Material.LIME_STAINED_GLASS_PANE: Material.RED_STAINED_GLASS_PANE);
                                        contents.updateLore(0, 0, loreUpdate);
                                        contents.updateLore(1, 0, loreUpdate);

                            }));

                        //SET RADIUS
                        contents.set(new ItemData(3,
                                new ItemBuilder("expbuild.message.gui.radius_text", false, Material.BROWN_MUSHROOM, 1)
                                        .addLore("expbuild.message.gui.radius_value", false, new String[]{String.valueOf(brushBuilder.getRadius())})
                                        .build(), event -> {

                            brushBuilder.setRadius(event.isShiftClick(), event.isRightClick());
                            contents.updateLore(3,0,"expbuild.message.gui.radius_value", false, new String[]{String.valueOf(brushBuilder.getRadius())});
                        }));

                        //SET AIR BRUSH
                        contents.set(new ItemData(5,
                                new ItemBuilder("expbuild.message.gui.air_text", false, Material.POTION, 1)
                                        .addLore("expbuild.message.gui.air_value", false, new String[]{String.valueOf(fbp.airBrush())})
                                        .addLore("expbuild.message.gui.total", false, new String[]{String.valueOf((fbp.flowerMaterialRate().stream().mapToInt(j -> j).sum() + fbp.airBrush()))})
                                        .build(), event -> {
                            fbp.setAirBrush(event.isShiftClick(), event.isRightClick());
                            contents.updateLore(5, 0, "expbuild.message.gui.air_value", false, new String[]{String.valueOf(fbp.airBrush())});
                            contents.updateLore(5, 1, "expbuild.message.gui.total", false, new String[]{String.valueOf((fbp.flowerMaterialRate().stream().mapToInt(j -> j).sum() + fbp.airBrush()))});

                            // Update material proportion
                            for (int i = 9; i < 18; i++) {
                                contents.updateLore(i, 1, "expbuild.message.gui.total", false, new String[]{String.valueOf((fbp.flowerMaterialRate().stream().mapToInt(j -> j).sum() + fbp.airBrush()))});
                            }

                        }));


                        //SET MATERIAL PROPORTION
                        for (int i = 9; i < 18; i++) {
                            int finalI = i;
                            contents.set(new ItemData(i,
                                    new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1)
                                            .addLore("expbuild.message.gui.right_arrow", false, new String[]{fbp.flowerMaterialRate().get(i - 9) + " % of " + fbp.flowerMaterial().get(i - 9)})
                                            .addLore("expbuild.message.gui.total", false, new String[]{String.valueOf((fbp.flowerMaterialRate().stream().mapToInt(j -> j).sum() + fbp.airBrush()))})
                                            .addLore("expbuild.message.gui.click_for_change", false)
                                            .build(), event -> {

                                int slot = event.getSlot();

                                if (!BukkitAdapter.adapt(fbp.flowerMaterial().get(slot-9).getBlockType()).equals(Material.BARRIER)) {

                                    fbp.addFlowerMaterialRate(finalI - 9, event.isShiftClick(), event.isRightClick());
                                    contents.updateLore(slot, 0, "expbuild.message.gui.right_arrow", false, new String[]{fbp.flowerMaterialRate().get(slot - 9) + " % of " + fbp.flowerMaterial().get(slot - 9)});
                                    contents.updateLore(slot, 1, "expbuild.message.gui.total", false, new String[]{String.valueOf((fbp.flowerMaterialRate().stream().mapToInt(j -> j).sum() + fbp.airBrush()))});
                                    contents.updateLore(slot, 2, "expbuild.message.gui.click_for_change", false);

                                    //Update Air brush item
                                    contents.updateLore(5, 1, "expbuild.message.gui.total", false, new String[]{String.valueOf((fbp.flowerMaterialRate().stream().mapToInt(j -> j).sum() + fbp.airBrush()))});

                                    // Update
                                    for (int k = 9; k < 18; k++) {
                                        contents.updateLore(k, 1, "expbuild.message.gui.total", false, new String[]{String.valueOf((fbp.flowerMaterialRate().stream().mapToInt(j -> j).sum() + fbp.airBrush()))});
                                    }
                                }
                            }));
                        }

                        //SET MATERIAL
                        for (int i = 45; i < 54; i++) {

                            int finalI = i;

                            contents.set(new ItemData(i,
                                    new ItemBuilder(BukkitAdapter.adapt(fbp.flowerMaterial().get(i - 45).getBlockType()))
                                            .build(), event -> {

                                if (event.getCursor().getType().isAir() || event.getCursor() == null) {

                                    fbp.addFlowerMaterial(
                                            new BaseBlock(BukkitAdapter.asBlockState(
                                                    new ItemBuilder(Material.BARRIER)
                                                            .build())), event.getSlot() - 45);
                                    fbp.addFlowerMaterialRate(0, event.getSlot() - 45);

                                    contents.updateMaterial(event.getSlot(), Material.BARRIER);


                                    // Update
                                    for (int k = 9; k < 18; k++) {

                                        contents.updateLore(k, 0, "expbuild.message.gui.right_arrow", false, new String[]{fbp.flowerMaterialRate().get(k - 9) + " % of " + fbp.flowerMaterial().get(k - 9)});
                                        contents.updateLore(k, 1, "expbuild.message.gui.total", false, new String[]{String.valueOf((fbp.flowerMaterialRate().stream().mapToInt(j -> j).sum() + fbp.airBrush()))});
                                        contents.updateLore(k, 2, "expbuild.message.gui.click_for_change", false);
                                    }


                                    //Remove property material
                                    for (int j = 1; j < 4; j++) {
                                        int slot = (event.getSlot() - (j * 9));
                                        contents.updateMaterial(slot, Material.AIR);
                                    }

                                } else {

                                    BaseBlock baseBlock;
                                    try {
                                        baseBlock = new BaseBlock(BukkitAdapter.asBlockState(event.getCursor()));
                                    } catch (NotABlockException e) {
                                        return;
                                    }

                                    if (fbp.flowerMaterialRate().get(finalI - 45).equals(0)) {
                                        fbp.flowerMaterialRate().set(finalI - 45, 1);
                                    }

                                    contents.updateLore(finalI - 36, 0, "expbuild.message.gui.right_arrow", false, new String[]{fbp.flowerMaterialRate().get(finalI - 45) + " % of " + fbp.flowerMaterial().get(finalI - 45)});

                                    fbp.addFlowerMaterial(baseBlock, finalI - 45);
                                    contents.updateMaterial(finalI, BukkitAdapter.adapt(baseBlock.getBlockType()));


                                    //Iteration Property block
                                    int iter = 0;
                                    for (Map.Entry<Property<?>, Object> set : baseBlock.getStates().entrySet()) {

                                        iter++;

                                        int finalIter = iter;
                                        contents.updateOrSet(new ItemData(finalI - (iter * 9), new ItemBuilder(Material.YELLOW_BANNER)
                                                .addLore("expbuild.message.gui.property_key", false, new String[]{set.getKey().getName()})
                                                .addLore("expbuild.message.gui.value_propertykey", false, new String[]{set.getValue().toString().toUpperCase()})
                                                .addLore("expbuild.message.gui.click_for_change_property", false)
                                                .build(), event1 -> {

                                            if (contents.getItemData((finalI - (finalIter * 9))).itemBuilder().getType().equals(Material.YELLOW_BANNER)) {

                                              itemBrushManager(event.getSlot() - 45, (finalI - (finalIter * 9)), event1, fbp, set, contents, baseBlock);
                                            }
                                        }));

                                        if (iter == 3) break;
                                    }
                                    event.setCursor(new ItemStack(Material.AIR));
                                }
                            }));

                            if (!BukkitAdapter.adapt(fbp.flowerMaterial().get(i - 45).getBlockType()).equals(Material.BARRIER)) {

                                BaseBlock baseBlock = fbp.flowerMaterial().get(i - 45);

                                //ITERATION PROPERTY-KEY BLOCK
                                int iter = 0;
                                for (Map.Entry<Property<?>, Object> set : baseBlock.getStates().entrySet()) {

                                    iter++;

                                    int finalIter = iter;
                                    contents.updateOrSet(new ItemData(finalI - (iter * 9), new ItemBuilder(Material.YELLOW_BANNER)
                                            .addLore("expbuild.message.gui.property_key", false, new String[]{set.getKey().getName()})
                                            .addLore("expbuild.message.gui.value_propertykey", false, new String[]{set.getValue().toString().toUpperCase()})
                                            .addLore("expbuild.message.gui.click_for_change_property", false)
                                            .build(), event -> {

                                        if (contents.getItemData((finalI - (finalIter * 9))).itemBuilder().getType().equals(Material.YELLOW_BANNER)) {
                                            itemBrushManager(finalI - 45, (finalI - (finalIter * 9)), event, fbp, set, contents, baseBlock);
                                        }
                                    }));

                                    if (iter == 3) break;

                                }
                            }
                        }
                    }

                    @Override
                    public void update(Player player, InventoryContents contents) {}

                })
                .build()
                .open(p);
    }

    private static void itemBrushManager(int materialIndex, int modifySlot, InventoryClickEvent event1, FlowerBrushParameter fbp, Map.Entry<Property<?>, Object> set, InventoryContents contents, BaseBlock baseBlock) {

        BaseBlock baseBlockProperty = fbp.flowerMaterial().get(materialIndex);

        //Modify propertyKey TYPE
        if (set.getKey().getName().equalsIgnoreCase("TYPE")) {

            if (baseBlockProperty.getState(PropertyKey.TYPE).toString().equalsIgnoreCase("top")) {
                baseBlockProperty = baseBlock.with(PropertyKey.TYPE, "bottom");
            } else if (baseBlockProperty.getState(PropertyKey.TYPE).toString().equalsIgnoreCase("bottom")) {
                baseBlockProperty = baseBlock.with(PropertyKey.TYPE, "double");
            } else if (baseBlockProperty.getState(PropertyKey.TYPE).toString().equalsIgnoreCase("double")) {
                baseBlockProperty = baseBlock.with(PropertyKey.TYPE, "top");
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.TYPE).toString().toUpperCase());
        }

        //Modify propertyKey HALF
        if (set.getKey().getName().equalsIgnoreCase("HALF")) {

            if (baseBlockProperty.getState(PropertyKey.HALF).toString().equalsIgnoreCase("upper")) {
                baseBlockProperty = baseBlock.with(PropertyKey.HALF, "lower");
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.HALF, "upper");
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.HALF).toString().toUpperCase());
        }

        //Modify Property WATERLOGGED
        if (set.getKey().getName().equalsIgnoreCase("WATERLOGGED")) {

            if (baseBlockProperty.getState(PropertyKey.WATERLOGGED).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlockProperty.with(PropertyKey.WATERLOGGED, false);
            } else {
                baseBlockProperty = baseBlockProperty.with(PropertyKey.WATERLOGGED, true);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.WATERLOGGED).toString().toUpperCase());
        }

        //Modify Property AGE
        if (set.getKey().getName().equalsIgnoreCase("AGE")) {

            if (event1.isRightClick()) {
                int age = Integer.parseInt(baseBlockProperty.getState(PropertyKey.AGE).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.AGE, age - 1);
            }
            if (event1.isLeftClick()) {
                int age = Integer.parseInt(baseBlockProperty.getState(PropertyKey.AGE).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.AGE, age + 1);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.AGE).toString().toUpperCase());

        }

        //Modify Property PICKLES
        if (set.getKey().getName().equalsIgnoreCase("PICKLES")) {

            if (event1.isRightClick()) {
                int age = Integer.parseInt(baseBlockProperty.getState(PropertyKey.PICKLES).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.PICKLES, age - 1);
            }
            if (event1.isLeftClick()) {
                int age = Integer.parseInt(baseBlockProperty.getState(PropertyKey.PICKLES).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.PICKLES, age + 1);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.PICKLES).toString().toUpperCase());

        }

        //Modify Property PERSISTENT
        if (set.getKey().getName().equalsIgnoreCase("PERSISTENT")) {

            if (baseBlockProperty.getState(PropertyKey.PERSISTENT).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.PERSISTENT, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.PERSISTENT, true);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.PERSISTENT).toString().toUpperCase());
        }

        //Modify Property TILT
        if (set.getKey().getName().equalsIgnoreCase("TILT")) {

            if (baseBlockProperty.getState(PropertyKey.TILT).toString().equalsIgnoreCase("full")) {
                baseBlockProperty = baseBlock.with(PropertyKey.TILT, "none");
            } else if (baseBlockProperty.getState(PropertyKey.TILT).toString().equalsIgnoreCase("none")) {
                baseBlockProperty = baseBlock.with(PropertyKey.TILT, "partial");
            } else if (baseBlockProperty.getState(PropertyKey.TILT).toString().equalsIgnoreCase("partial")) {
                baseBlockProperty = baseBlock.with(PropertyKey.TILT, "unstable");
            } else if (baseBlockProperty.getState(PropertyKey.TILT).toString().equalsIgnoreCase("unstable")) {
                baseBlockProperty = baseBlock.with(PropertyKey.TILT, "full");
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.TILT).toString().toUpperCase());
        }


        //NOT OK
        //Modify Property FACING
        if (set.getKey().getName().equalsIgnoreCase("FACING")) {

            if (baseBlockProperty.getState(PropertyKey.FACING).toString().equalsIgnoreCase("east")) {
                baseBlockProperty = baseBlock.with(PropertyKey.FACING, Direction.NORTH);
            } else if (baseBlockProperty.getState(PropertyKey.FACING).toString().equalsIgnoreCase("north")) {
                baseBlockProperty = baseBlock.with(PropertyKey.FACING, Direction.SOUTH);
            } else if (baseBlockProperty.getState(PropertyKey.FACING).toString().equalsIgnoreCase("south")) {
                baseBlockProperty = baseBlock.with(PropertyKey.FACING, Direction.WEST);
            } else if (baseBlockProperty.getState(PropertyKey.FACING).toString().equalsIgnoreCase("west")) {
                baseBlockProperty = baseBlock.with(PropertyKey.FACING, Direction.EAST);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.FACING).toString().toUpperCase());
        }

        //Modify Property CANDLES
        if (set.getKey().getName().equalsIgnoreCase("CANDLES")) {

            if (event1.isRightClick()) {
                int candle = Integer.parseInt(baseBlockProperty.getState(PropertyKey.CANDLES).toString());
                System.out.println("candle = " + candle);
                baseBlockProperty = baseBlockProperty.with(PropertyKey.CANDLES, candle - 1);
            }
            if (event1.isLeftClick()) {
                int candle = Integer.parseInt(baseBlockProperty.getState(PropertyKey.CANDLES).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.CANDLES, candle + 1);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.CANDLES).toString().toUpperCase());

        }

        //Modify Property LIT
        if (set.getKey().getName().equalsIgnoreCase("LIT")) {

            if (baseBlockProperty.getState(PropertyKey.LIT).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.LIT, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.LIT, true);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.LIT).toString().toUpperCase());
        }

        //Modify Property LAYER
        if (set.getKey().getName().equalsIgnoreCase("LAYERS")) {

            if (event1.isRightClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.LAYERS).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.LAYERS, layer - 1);
            }
            if (event1.isLeftClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.LAYERS).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.LAYERS, layer + 1);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.LAYERS).toString().toUpperCase());
        }

        //Modify Property AXIS
        if (set.getKey().getName().equalsIgnoreCase("AXIS")) {

            if (baseBlockProperty.getState(PropertyKey.AXIS).toString().equalsIgnoreCase("x")) {
                baseBlockProperty = baseBlockProperty.with(PropertyKey.AXIS, "y");
            } else if (baseBlockProperty.getState(PropertyKey.AXIS).toString().equalsIgnoreCase("y")) {
                baseBlockProperty = baseBlockProperty.with(PropertyKey.AXIS, "z");
            } else if (baseBlockProperty.getState(PropertyKey.AXIS).toString().equalsIgnoreCase("z")) {
                baseBlockProperty = baseBlockProperty.with(PropertyKey.AXIS, "x");
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.AXIS).toString().toUpperCase());
        }

        //Modify Property LEAVES
        if (set.getKey().getName().equalsIgnoreCase("LEAVES")) {

            if (baseBlockProperty.getState(PropertyKey.LEAVES).toString().equalsIgnoreCase("small")) {
                baseBlockProperty = baseBlockProperty.with(PropertyKey.LEAVES, "large");
            } else if (baseBlockProperty.getState(PropertyKey.LEAVES).toString().equalsIgnoreCase("large")) {
                baseBlockProperty = baseBlockProperty.with(PropertyKey.LEAVES, "none");
            } else if (baseBlockProperty.getState(PropertyKey.LEAVES).toString().equalsIgnoreCase("none")) {
                baseBlockProperty = baseBlockProperty.with(PropertyKey.LEAVES, "small");
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.LEAVES).toString().toUpperCase());
        }

        //Modify Property POWERED
        if (set.getKey().getName().equalsIgnoreCase("POWERED")) {

            if (baseBlockProperty.getState(PropertyKey.POWERED).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.POWERED, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.POWERED, true);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.POWERED).toString().toUpperCase());
        }

        //Modify Property POWER
        if (set.getKey().getName().equalsIgnoreCase("POWER")) {

            if (event1.isRightClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.POWER).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.POWER, layer - 1);
            }
            if (event1.isLeftClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.POWER).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.POWER, layer + 1);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.POWER).toString().toUpperCase());
        }

        //Modify Property HANGING
        if (set.getKey().getName().equalsIgnoreCase("HANGING")) {

            if (baseBlockProperty.getState(PropertyKey.HANGING).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.HANGING, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.HANGING, true);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.HANGING).toString().toUpperCase());
        }

        //Modify Property OCCUPIED
        if (set.getKey().getName().equalsIgnoreCase("OCCUPIED")) {

            if (baseBlockProperty.getState(PropertyKey.OCCUPIED).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.OCCUPIED, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.OCCUPIED, true);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.OCCUPIED).toString().toUpperCase());
        }

        //Modify Property PART
        if (set.getKey().getName().equalsIgnoreCase("PART")) {

            if (baseBlockProperty.getState(PropertyKey.PART).toString().equalsIgnoreCase("foot")) {
                baseBlockProperty = baseBlock.with(PropertyKey.PART, "head");
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.PART, "foot");
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.PART).toString().toUpperCase());
        }

        //Modify Property MOISTURE
        if (set.getKey().getName().equalsIgnoreCase("MOISTURE")) {

            if (event1.isRightClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.MOISTURE).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.MOISTURE, layer - 1);
            }
            if (event1.isLeftClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.MOISTURE).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.MOISTURE, layer + 1);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.MOISTURE).toString().toUpperCase());
        }

        //Modify Property SNOWY
        if (set.getKey().getName().equalsIgnoreCase("SNOWY")) {

            if (baseBlockProperty.getState(PropertyKey.SNOWY).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.SNOWY, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.SNOWY, true);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.SNOWY).toString().toUpperCase());
        }

        //Modify Property MOISTURE
        if (set.getKey().getName().equalsIgnoreCase("HONEY_LEVEL")) {

            if (event1.isRightClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.HONEY_LEVEL).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.HONEY_LEVEL, layer - 1);
            }
            if (event1.isLeftClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.HONEY_LEVEL).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.HONEY_LEVEL, layer + 1);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.HONEY_LEVEL).toString().toUpperCase());
        }

        //Modify Property STAGE
        if (set.getKey().getName().equalsIgnoreCase("STAGE")) {

            if (event1.isRightClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.STAGE).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.STAGE, layer - 1);
            }
            if (event1.isLeftClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.STAGE).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.STAGE, layer + 1);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.STAGE).toString().toUpperCase());
        }

        //Modify Property OPEN
        if (set.getKey().getName().equalsIgnoreCase("OPEN")) {

            if (baseBlockProperty.getState(PropertyKey.OPEN).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.OPEN, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.OPEN, true);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.OPEN).toString().toUpperCase());
        }

        //NOT OK
        //Modify Property PART
        if (set.getKey().getName().equalsIgnoreCase("ATTACHMENT")) {

            if (baseBlockProperty.getState(PropertyKey.ATTACHMENT).toString().equalsIgnoreCase("ceiling")) {
                baseBlockProperty = baseBlock.with(PropertyKey.ATTACHMENT, "double_wall");
            } else if (baseBlockProperty.getState(PropertyKey.ATTACHMENT).toString().equalsIgnoreCase("double_wall")){
                baseBlockProperty = baseBlock.with(PropertyKey.ATTACHMENT, "floor");
            } else if (baseBlockProperty.getState(PropertyKey.ATTACHMENT).toString().equalsIgnoreCase("floor")){
                baseBlockProperty = baseBlock.with(PropertyKey.ATTACHMENT, "SINGLE_WALL");
            } else if (baseBlockProperty.getState(PropertyKey.ATTACHMENT).toString().equalsIgnoreCase("single_wall")) {
                baseBlockProperty = baseBlock.with(PropertyKey.ATTACHMENT, "ceiling");
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.ATTACHMENT).toString().toUpperCase());
        }

        //Modify Property LEVEL
        if (set.getKey().getName().equalsIgnoreCase("LEVEL")) {

            if (event1.isRightClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.LEVEL).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.LEVEL, layer - 1);
            }
            if (event1.isLeftClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.LEVEL).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.LEVEL, layer + 1);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.LEVEL).toString().toUpperCase());
        }

        //Modify Property ROTATION
        if (set.getKey().getName().equalsIgnoreCase("ROTATION")) {

            if (event1.isRightClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.ROTATION).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.ROTATION, layer - 1);
            }
            if (event1.isLeftClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.ROTATION).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.ROTATION, layer + 1);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.ROTATION).toString().toUpperCase());
        }

        // Modify Property TIP
        if (set.getKey().getName().equalsIgnoreCase("tip")) {

            boolean tip = Boolean.parseBoolean(baseBlockProperty.getState(PropertyKey.getByName("tip")).toString());
            baseBlockProperty = baseBlock.with(PropertyKey.getByName("tip"), !tip);

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.getByName("tip")).toString().toUpperCase());
        }

        //Modify Property FLOWER_AMOUNT
        if (set.getKey().getName().equalsIgnoreCase("flower_amount")) {

            if (event1.isRightClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.getByName("flower_amount")).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.getByName("flower_amount"), layer - 1);
            }
            if (event1.isLeftClick()) {
                int layer = Integer.parseInt(baseBlockProperty.getState(PropertyKey.getByName("flower_amount")).toString());
                baseBlockProperty = baseBlockProperty.with(PropertyKey.getByName("flower_amount"), layer + 1);
            }

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.getByName("flower_amount")).toString().toUpperCase());
        }

        // Modify Property TIP
        if (set.getKey().getName().equalsIgnoreCase("bottom")) {

            boolean bottom = Boolean.parseBoolean(baseBlockProperty.getState(PropertyKey.getByName("bottom")).toString());
            baseBlockProperty = baseBlock.with(PropertyKey.getByName("bottom"), !bottom);

            fbp.addFlowerMaterial(baseBlockProperty, materialIndex);
            contents.updateLore(modifySlot, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.getByName("bottom")).toString().toUpperCase());
        }

//        baseBlockProperty.getStates().forEach((property, object) -> {
//
//            System.out.println("KEY : " + property.getKey().getName());
//            System.out.println("VALUES : " + object.toString());
//
//            System.out.println("NAME : " + property.getName().toString());
//            property.getValues().forEach(System.out::println);
//
//        });
    }





    private static void buildBrush(@NotNull BrushBuilder brushBuilder) {

        if (brushBuilder.getEnable()) {
            brushBuilder.setBrush(new FlowerBrush());

            FlowerBrushParameter fbp = brushBuilder.getFlowerBrushParameter();

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < fbp.flowerMaterial().size(); i++) {

                if (!fbp.flowerMaterial().get(i).toBaseBlock().getBlockType().equals(BukkitAdapter.asBlockType(Material.BARRIER))) {

                    // Remplacer tout ce qui est entre {} y compris les {}
                    String result = fbp.flowerMaterial().get(i).toString().replaceAll("\\{[^}]*}", "");

                    stringBuilder.append(fbp.flowerMaterialRate().get(i))
                            .append("%")
                            .append(result)
                            .append(',');

                }
            }
            stringBuilder.append(fbp.airBrush())
                    .append("%0");


            String toRemove = ",is_waxed:0B,id:'minecraft:sign',front_text:}";
            int start = stringBuilder.indexOf(toRemove);
            if (start != -1) {
                stringBuilder.delete(start, start + toRemove.length());
            }

            brushBuilder.setPattern(new FaweAPI(brushBuilder.getPlayer()).getPattern(stringBuilder.toString()));
        }
    }
}