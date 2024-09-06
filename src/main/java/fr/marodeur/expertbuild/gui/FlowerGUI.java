package fr.marodeur.expertbuild.gui;

import com.fastasyncworldedit.core.registry.state.PropertyKey;

import com.sk89q.worldedit.NotABlockException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.registry.state.Property;
import com.sk89q.worldedit.util.Direction;
import com.sk89q.worldedit.world.block.BaseBlock;

import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.brush.FlowerBrush;
import fr.marodeur.expertbuild.object.*;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.enums.TimeSetting;
import io.github.rysefoxx.inventory.plugin.other.EventCreator;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FlowerGUI {

    //public static final String RightArrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlkZGRhM2RkMTkxZDYwNTk5MDA3MDBlZDBjYWY1NGZjNzdjM2Q4MTVhYTI5NDZiYzA5YjY5YWIyZjJmZjk5NiJ9fX0=";

    private final String RightArrow = "16227036b8afed6935d53143d16488d39cf4fb73a671f2b2955e80fc9dfe458";

    public void openFlowerInventory(Player p) {

        RyseInventory.builder()
                .title(new Message.MessageSender("expbuild.message.gui.flower_gui_title", true).getMessage())
                .rows(6)
                .period(2, TimeSetting.MILLISECONDS)

                .listener(new EventCreator<>(InventoryCloseEvent.class, event -> {

                    if (event.getView().getTitle().equalsIgnoreCase(new Message.MessageSender("expbuild.message.gui.flower_gui_title", true).getMessage())) {
                        buildBrush(BrushBuilder.getBrushBuilderPlayer(p, false));
                    }
                }))

                .listener(new EventCreator<>(InventoryClickEvent.class, event -> {

                    if (event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {

                        ItemStack it = event.getCurrentItem();
                        assert it != null;

                        event.setCursor(it);
                    }
                }))

                .provider(new InventoryProvider() {

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);

                        if (brushBuilder == null) {
                            p.sendMessage(new Message.MessageSender("expbuild.message.permission.no_permission_node", true, new String[]{"exp.register"}).getMessage());
                            return;
                        }

                        // Exit return to main gui
                        contents.set(0, 8, IntelligentItem.of(new ItemBuilder(Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(RightArrow)
                                        .addLore("expbuild.message.gui.back", false)
                                        .build(),
                                event -> {

                                    buildBrush(brushBuilder);
                                    new MainGUI().openMainInventory(p);
                                }));

                        //FLOWER
                        contents.set(1, IntelligentItem.of(
                                new ItemBuilder("expbuild.message.gui.flower_gui_title", false, Material.HONEYCOMB, 1)
                                        .addLoreLineTest("expbuild.message.gui.brush_enable", "expbuild.message.gui.brush_disable", brushBuilder.getEnable(), false)
                                        .build(), event -> brushBuilder.setEnable(!brushBuilder.getEnable())));

                        //SET BRUSH FALSE
                        if (brushBuilder.getEnable().equals(true)) {
                            contents.set(0, IntelligentItem.of(
                                    new ItemBuilder("expbuild.message.gui.flower_gui_title", false, Material.LIME_STAINED_GLASS_PANE, 1)
                                            .addLore("expbuild.message.gui.brush_enable", false)
                                            .build(), event -> {

                                        brushBuilder.setEnable(false);
                                        contents.updateMaterial(0, Material.RED_STAINED_GLASS_PANE);
                                        contents.updateLore(0, 0, new Message.MessageSender("expbuild.message.gui.brush_disable", false).getMessage());
                                    }));

                            //SET BRUSH TRUE
                        } else {
                            contents.set(0, IntelligentItem.of(
                                    new ItemBuilder("expbuild.message.gui.flower_gui_title", false, Material.RED_STAINED_GLASS_PANE, 1)
                                            .addLore("expbuild.message.gui.brush_disable", false)
                                            .build(), event -> {

                                        brushBuilder.setEnable(true);
                                        contents.updateMaterial(0, Material.LIME_STAINED_GLASS_PANE);
                                        contents.updateLore(0, 0, new Message.MessageSender("expbuild.message.gui.brush_enable", false).getMessage());
                                    }));
                        }

                        //SET RADIUS
                        contents.set(3, IntelligentItem.of(
                                new ItemBuilder("expbuild.message.gui.radius_text", false, Material.BROWN_MUSHROOM, 1)
                                        .addLore("expbuild.message.gui.radius_value", false, new String[]{String.valueOf(brushBuilder.getRadius())})
                                        .build(), event -> brushBuilder.setRadius(event.isShiftClick(), event.isRightClick()))
                        );

                        //SET AIR BRUSH
                        contents.set(5, IntelligentItem.of(
                                new ItemBuilder("expbuild.message.gui.air_text", false, Material.POTION, 1)
                                        .addLore("expbuild.message.gui.air_value", false, new String[]{String.valueOf(brushBuilder.getAirBrush())})
                                        .addLore("expbuild.message.gui.total", false, new String[]{(brushBuilder.getFlowerMaterialRate().stream().mapToInt(j -> j).sum() + brushBuilder.getAirBrush()) + " %"})
                                        .build(), event -> brushBuilder.setAirBrush(event.isShiftClick(), event.isRightClick())));

                        //SET MATERIAL PROPORTION
                        for (int i = 9; i < 18; i++) {
                            int finalI = i;
                            contents.set(i, IntelligentItem.of(
                                    new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1)
                                            .addLore("expbuild.message.gui.right_arrow", false, new String[]{brushBuilder.getFlowerMaterialRate().get(i - 9) + " % of " + brushBuilder.getFlowerMaterial().get(i - 9)})
                                            .addLore("expbuild.message.gui.total", false, new String[]{(brushBuilder.getFlowerMaterialRate().stream().mapToInt(j -> j).sum() + brushBuilder.getAirBrush()) + " %"})
                                            .addLore("expbuild.message.gui.click_for_change", false)
                                            .build(), event -> brushBuilder.addFlowerMaterialRate(finalI - 9, event.isShiftClick(), event.isRightClick())));
                        }

                        //SET MATERIAL
                        for (int i = 45; i < 54; i++) {

                            int finalI = i;

                            contents.set(i, IntelligentItem.of(
                                    new ItemBuilder(BukkitAdapter.adapt(brushBuilder.getFlowerMaterial().get(i - 45).getBlockType()))
                                            .build(), event -> {
                                    }));

                            BaseBlock baseBlock = brushBuilder.getFlowerMaterial().get(i - 45);

                            //ITERATION PROPERTY-KEY BLOCK
                            int iter = 0;
                            for (Map.Entry<Property<?>, Object> set : baseBlock.getStates().entrySet()) {

                                iter++;

                                contents.updateOrSet(finalI - (iter * 9), IntelligentItem.of(new ItemBuilder(Material.YELLOW_BANNER)
                                        .addLore("expbuild.message.gui.property_key", false, new String[]{set.getKey().getName()})
                                        .addLore("expbuild.message.gui.value_propertykey", false, new String[]{set.getValue().toString().toUpperCase()})
                                        .addLore("expbuild.message.gui.click_for_change_property", false)
                                        .build(), event -> itemBrushManager(finalI-45, event, event, brushBuilder, set, contents, baseBlock)));

                                if (iter == 3) break;
                            }
                        }
                    }

                    @Override
                    public void update(Player player, InventoryContents contents) {

                        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);

                        if (brushBuilder == null) {
                            p.sendMessage(new Message.MessageSender("expbuild.message.permission.no_permission_node", false, new String[]{"exp.register"}).getMessage());
                            return;
                        }

                        //SET MATERIAL PROPORTION
                        for (int i = 9; i < 18; i++) {
                            int finalI = i;
                            contents.updateOrSet(i, IntelligentItem.of(
                                    new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1)
                                            .addLore("expbuild.message.gui.right_arrow", false, new String[]{brushBuilder.getFlowerMaterialRate().get(i - 9) + " % of " + brushBuilder.getFlowerMaterial().get(i - 9)})
                                            .addLore("expbuild.message.gui.total", false, new String[]{(brushBuilder.getFlowerMaterialRate().stream().mapToInt(j -> j).sum() + brushBuilder.getAirBrush()) + " %"})
                                            .addLore("expbuild.message.gui.click_for_change", false)
                                            .build(), event -> {
                                        brushBuilder.addFlowerMaterialRate(finalI - 9, event.isShiftClick(), event.isRightClick());
                                    }));
                        }

                        //SET BRUSH FALSE
                        if (brushBuilder.getEnable().equals(true)) {
                            contents.updateOrSet(0, IntelligentItem.of(
                                    new ItemBuilder("expbuild.message.gui.flower_gui_title", false, Material.LIME_STAINED_GLASS_PANE, 1)
                                            .addLore("expbuild.message.gui.brush_enable", false)
                                            .build(), event -> brushBuilder.setEnable(false)));

                            contents.updateOrSet(1, IntelligentItem.of(
                                    new ItemBuilder("expbuild.message.gui.flower_gui_title", false, Material.HONEYCOMB, 1)
                                            .addLore("expbuild.message.gui.brush_enable", false)
                                            .build(), event -> brushBuilder.setEnable(false)));

                            //SET BRUSH TRUE
                        } else {
                            contents.updateOrSet(0, IntelligentItem.of(
                                    new ItemBuilder("expbuild.message.gui.flower_gui_title", false, Material.RED_STAINED_GLASS_PANE, 1)
                                            .addLore("expbuild.message.gui.brush_disable", false)
                                            .build(), event -> brushBuilder.setEnable(true)));

                            contents.updateOrSet(1, IntelligentItem.of(
                                    new ItemBuilder("expbuild.message.gui.flower_gui_title", false, Material.HONEYCOMB, 1)
                                            .addLore("expbuild.message.gui.brush_disable", false)
                                            .build(), event -> brushBuilder.setEnable(true)));
                        }

                        //SET RADIUS
                        contents.updateOrSet(3, IntelligentItem.of(
                                new ItemBuilder("expbuild.message.gui.radius_text", false, Material.BROWN_MUSHROOM, 1)
                                        .addLore("expbuild.message.gui.radius_value", false, new String[]{String.valueOf(brushBuilder.getRadius())})
                                        .build(), event -> brushBuilder.setRadius(event.isShiftClick(), event.isRightClick())));

                        //SET AIR BRUSH
                        contents.updateOrSet(5, IntelligentItem.of(
                                new ItemBuilder("expbuild.message.gui.air_text", false, Material.POTION, 1)
                                        .addLore("expbuild.message.gui.air_value", false, new String[]{String.valueOf(brushBuilder.getAirBrush())})
                                        .addLore("expbuild.message.gui.total", false, new String[]{(brushBuilder.getFlowerMaterialRate().stream().mapToInt(j -> j).sum() + brushBuilder.getAirBrush()) + " %"})
                                        .build(), event -> brushBuilder.setAirBrush(event.isShiftClick(), event.isRightClick())));


                        //UPDATE MATERIAL
                        for (int i = 45; i < 54; i++) {

                            int finalI = i;
                            contents.updateOrSet(i, IntelligentItem.of(
                                    new ItemBuilder(BukkitAdapter.adapt(brushBuilder.getFlowerMaterial().get(i - 45).getBlockType()))
                                            .build(),
                                    event -> {

                                        if (event.getCursor().getType().isAir()) {

                                            brushBuilder.addFlowerMaterial(
                                                            new BaseBlock(BukkitAdapter.asBlockState(
                                                                    new ItemBuilder(Material.BARRIER)
                                                                            .build())), event.getSlot() - 45)
                                                    .addFlowerMaterialRate(0, event.getSlot() - 45);

                                            //Remove property material
                                            for (int j = 0; j < 4; j++) {
                                                int slot = (event.getSlot() - (j * 9));
                                                contents.updateOrSet(slot, new ItemBuilder(Material.AIR).build());
                                            }
                                        } else {

                                            //Remove last property material
                                            for (int j = 0; j < 4; j++) {
                                                int slot = (event.getSlot() - (j * 9));
                                                contents.updateOrSet(slot, new ItemBuilder(Material.AIR).build());
                                            }

                                            BaseBlock baseBlock;
                                            try {
                                                baseBlock = new BaseBlock(BukkitAdapter.asBlockState(event.getCursor()));
                                            } catch (NotABlockException e) {
                                                return;
                                            }

                                            if (brushBuilder.getFlowerMaterialRate().get(finalI - 45).equals(0)) {
                                                brushBuilder.getFlowerMaterialRate().set(finalI - 45, 1);
                                            }

                                            brushBuilder.addFlowerMaterial(baseBlock, finalI - 45);

                                            //Iteration Property block
                                            int iter = 0;
                                            for (Map.Entry<Property<?>, Object> set : baseBlock.getStates().entrySet()) {

                                                iter++;

                                                contents.updateOrSet(finalI - (iter * 9), IntelligentItem.of(new ItemBuilder(Material.YELLOW_BANNER)
                                                        .addLore("expbuild.message.gui.property_key", false, new String[]{set.getKey().getName()})
                                                        .addLore("expbuild.message.gui.value_propertykey", false, new String[]{set.getValue().toString().toUpperCase()})
                                                        .addLore("expbuild.message.gui.click_for_change_property", false)
                                                        .build(), event1 -> {

                                                    itemBrushManager(event.getSlot()-45, event, event1, brushBuilder, set, contents, baseBlock);

                                                }));

                                                if (iter == 3) break;
                                            }
                                            event.setCursor(new ItemStack(Material.AIR));
                                        }
                                    }));
                        }
                    }
                })
                .build(Main.getInstance())
                .open(p);
    }

    private static void itemBrushManager(int index, InventoryClickEvent event, InventoryClickEvent event1, BrushBuilder brushBuilder, Map.Entry<Property<?>, Object> set, InventoryContents contents, BaseBlock baseBlock) {

        int slotItem = event.getSlot();
        int slotClicked = event1.getSlot();
        //int index = slotItem - 45;

        //Index error
        BaseBlock baseBlockProperty = brushBuilder.getFlowerMaterial().get(index);


        //Modify propertyKey TYPE
        if (set.getKey().getName().equalsIgnoreCase("TYPE")) {

            if (baseBlockProperty.getState(PropertyKey.TYPE).toString().equalsIgnoreCase("top")) {
                baseBlockProperty = baseBlock.with(PropertyKey.TYPE, "bottom");
            } else if (baseBlockProperty.getState(PropertyKey.TYPE).toString().equalsIgnoreCase("bottom")) {
                baseBlockProperty = baseBlock.with(PropertyKey.TYPE, "double");
            } else if (baseBlockProperty.getState(PropertyKey.TYPE).toString().equalsIgnoreCase("double")) {
                baseBlockProperty = baseBlock.with(PropertyKey.TYPE, "top");
            }

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.TYPE).toString().toUpperCase());
        }

        //Modify propertyKey HALF
        if (set.getKey().getName().equalsIgnoreCase("HALF")) {

            if (baseBlockProperty.getState(PropertyKey.HALF).toString().equalsIgnoreCase("upper")) {
                baseBlockProperty = baseBlock.with(PropertyKey.HALF, "lower");
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.HALF, "upper");
            }

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.HALF).toString().toUpperCase());
        }

        //Modify Property WATERLOGGED
        if (set.getKey().getName().equalsIgnoreCase("WATERLOGGED")) {

            if (baseBlockProperty.getState(PropertyKey.WATERLOGGED).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlockProperty.with(PropertyKey.WATERLOGGED, false);
            } else {
                baseBlockProperty = baseBlockProperty.with(PropertyKey.WATERLOGGED, true);
            }

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.WATERLOGGED).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.AGE).toString().toUpperCase());

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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.PICKLES).toString().toUpperCase());

        }

        //Modify Property PERSISTENT
        if (set.getKey().getName().equalsIgnoreCase("PERSISTENT")) {

            if (baseBlockProperty.getState(PropertyKey.PERSISTENT).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.PERSISTENT, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.PERSISTENT, true);
            }

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.PERSISTENT).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.TILT).toString().toUpperCase());
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


            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : " + baseBlockProperty.getState(PropertyKey.FACING).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.CANDLES).toString().toUpperCase());

        }

        //Modify Property LIT
        if (set.getKey().getName().equalsIgnoreCase("LIT")) {

            if (baseBlockProperty.getState(PropertyKey.LIT).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.LIT, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.LIT, true);
            }

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.LIT).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.LAYERS).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.AXIS).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.LEAVES).toString().toUpperCase());
        }

        //Modify Property POWERED
        if (set.getKey().getName().equalsIgnoreCase("POWERED")) {

            if (baseBlockProperty.getState(PropertyKey.POWERED).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.POWERED, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.POWERED, true);
            }

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.POWERED).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.POWER).toString().toUpperCase());
        }

        //Modify Property HANGING
        if (set.getKey().getName().equalsIgnoreCase("HANGING")) {

            if (baseBlockProperty.getState(PropertyKey.HANGING).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.HANGING, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.HANGING, true);
            }

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.HANGING).toString().toUpperCase());
        }

        //Modify Property OCCUPIED
        if (set.getKey().getName().equalsIgnoreCase("OCCUPIED")) {

            if (baseBlockProperty.getState(PropertyKey.OCCUPIED).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.OCCUPIED, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.OCCUPIED, true);
            }

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.OCCUPIED).toString().toUpperCase());
        }

        //Modify Property PART
        if (set.getKey().getName().equalsIgnoreCase("PART")) {

            if (baseBlockProperty.getState(PropertyKey.PART).toString().equalsIgnoreCase("foot")) {
                baseBlockProperty = baseBlock.with(PropertyKey.PART, "head");
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.PART, "foot");
            }

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.PART).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.MOISTURE).toString().toUpperCase());
        }

        //Modify Property SNOWY
        if (set.getKey().getName().equalsIgnoreCase("SNOWY")) {

            if (baseBlockProperty.getState(PropertyKey.SNOWY).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.SNOWY, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.SNOWY, true);
            }

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.SNOWY).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.HONEY_LEVEL).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.STAGE).toString().toUpperCase());
        }

        //Modify Property OPEN
        if (set.getKey().getName().equalsIgnoreCase("OPEN")) {

            if (baseBlockProperty.getState(PropertyKey.OPEN).toString().equalsIgnoreCase("true")) {
                baseBlockProperty = baseBlock.with(PropertyKey.OPEN, false);
            } else {
                baseBlockProperty = baseBlock.with(PropertyKey.OPEN, true);
            }

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.OPEN).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.ATTACHMENT).toString().toUpperCase());
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

            brushBuilder.addFlowerMaterial(baseBlockProperty, index);
            contents.updateLore(slotClicked, 1, "Value : §7" + baseBlockProperty.getState(PropertyKey.LEVEL).toString().toUpperCase());
        }
    }

    private static void buildBrush(@NotNull BrushBuilder brushBuilder) {

        if (brushBuilder.getEnable()) {
            brushBuilder.setBrush(new FlowerBrush());

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < brushBuilder.getFlowerMaterial().size(); i++) {

                if (!brushBuilder.getFlowerMaterial().get(i).toBaseBlock().getBlockType().equals(BukkitAdapter.asBlockType(Material.BARRIER))) {

                    stringBuilder.append(brushBuilder.getFlowerMaterialRate().get(i))
                            .append("%")
                            .append(brushBuilder.getFlowerMaterial().get(i))
                            .append(',');
                }
            }
            stringBuilder.append(brushBuilder.getAirBrush())
                    .append("%")
                    .append(0);

            brushBuilder.setPattern(new FaweAPI(brushBuilder.getPlayer()).getPattern(stringBuilder.toString()));
        }
    }
}
