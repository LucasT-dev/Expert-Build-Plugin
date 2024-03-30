package fr.marodeur.expertbuild.gui;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.GOHA_Builder;
import fr.marodeur.expertbuild.object.ItemBuilder;
import fr.marodeur.expertbuild.object.MessageBuilder;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.other.EventCreator;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OrganicGUI {

    private static final MessageBuilder msg = Main.getInstance().getMessageConfig();

    //private final String X = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzM4YWIxNDU3NDdiNGJkMDljZTAzNTQzNTQ5NDhjZTY5ZmY2ZjQxZDllMDk4YzY4NDhiODBlMTg3ZTkxOSJ9fX0=";
    //private final String Y = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTcxMDcxYmVmNzMzZjQ3NzAyMWIzMjkxZGMzZDQ3ZjBiZGYwYmUyZGExYjE2NWExMTlhOGZmMTU5NDU2NyJ9fX0=";
    //public final String RightArrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlkZGRhM2RkMTkxZDYwNTk5MDA3MDBlZDBjYWY1NGZjNzdjM2Q4MTVhYTI5NDZiYzA5YjY5YWIyZjJmZjk5NiJ9fX0=";

    private final String X = "c38ab145747b4bd09ce0354354948ce69ff6f41d9e098c6848b80e187e919";
    private final String Y = "a71071bef733f477021b3291dc3d47f0bdf0be2da1b165a119a8ff1594567";
    private final String RightArrow = "16227036b8afed6935d53143d16488d39cf4fb73a671f2b2955e80fc9dfe458";

    public void openOrganicGUI(Player p) {

        RyseInventory.builder()
                .title(Main.prefix + msg.getOrganicGuiTitle())
                .rows(6)
                .listener(new EventCreator<>(InventoryClickEvent.class, event -> {

                    final GOHA_Builder goha_builder = GOHA_Builder.getGOHABuilder(p);

                    short slot = (short) event.getSlot();
                    Inventory inventory = event.getClickedInventory();

                    //Pregen
                    if (slot == 43) {

                        //Clear pregeneration particle
                        if (goha_builder.getPregen()) {

                            inventory.setItem(43,
                                    new ItemBuilder(msg.getPregeneration(), Material.ARMOR_STAND, 1)
                                            .addLore(msg.getClickPregen())
                                            .build());

                            goha_builder.setPregen(false)
                                    .setParticleID()
                                    .setStartLoc(null);

                            //Pregeneration
                        } else {

                            inventory.setItem(43,
                                    new ItemBuilder(msg.getGenerate(), Material.ARMOR_STAND, 1)
                                            .addLore(msg.getClickClearParticle())
                                            .build());

                            goha_builder.setPregen(true)
                                    .setParticleID()
                                    .setStartLoc(p.getLocation());

                            new GOHA_Builder.OrganicGeneration(p)
                                    .getAllPoint()
                                    .generateAllParticle();

                        }
                    }

                    // Generate
                    if (slot == 44) {

                        if (goha_builder.getStartLoc() != null) {

                            new GOHA_Builder.OrganicGeneration(p, goha_builder.getStartLoc())
                                    .getAllPoint()
                                    .generateAllBlock()
                                    .buildBlock();
                        } else {

                            new GOHA_Builder.OrganicGeneration(p)
                                    .getAllPoint()
                                    .generateAllBlock()
                                    .buildBlock();
                        }

                        goha_builder.setPregen(false)
                                .setParticleID()
                                .setStartLoc(null);

                    }

                    //X rotation
                    if (slot == 24) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setCommutateur((short) 1);
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setCommutateur((short) 0);
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setCommutateur((short) 1);
                        }

                        inventory.setItem(24,
                                new ItemBuilder(msg.getPitchAngleConf(), Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(X)
                                        .addLore(msg.getClickChangeAngle("pitch"))
                                        .build());
                    }

                    //Y rotation
                    if (slot == 25) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setCommutateur((short) 2);
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setCommutateur((short) 2);
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setCommutateur((short) 0);
                        }

                        inventory.setItem(25,
                                new ItemBuilder(msg.getYawAngleConf(), Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(Y)
                                        .addLore(msg.getClickChangeAngle("yaw"))
                                        .build());
                    }

                    // ENABLE / DISABLE MEMBER
                    if (slot == 26) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setCommutateur((short) 0);
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setCommutateur((short) 0);
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setCommutateur((short) 0);
                        }

                        inventory.setItem(26,
                                new ItemBuilder(msg.getMemberConf(), Material.BARRIER, 1)
                                        .addLore(msg.getClickEnableDisable())
                                        .build());
                    }


                    // XY
                    if (goha_builder.getCommutateur() == 0) {
                        inventory.setItem(15, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                        inventory.setItem(16, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                        inventory.setItem(17, new ItemBuilder("", Material.GREEN_STAINED_GLASS_PANE, 1).build());
                    }
                    if (goha_builder.getCommutateur() == 1) {
                        inventory.setItem(15, new ItemBuilder("", Material.GREEN_STAINED_GLASS_PANE, 1).build());
                        inventory.setItem(16, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                        inventory.setItem(17, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                    }
                    if (goha_builder.getCommutateur() == 2) {
                        inventory.setItem(15, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                        inventory.setItem(16, new ItemBuilder("", Material.GREEN_STAINED_GLASS_PANE, 1).build());
                        inventory.setItem(17, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                    }

                    //Height
                    if (slot == 42) {

                        goha_builder.addHeight(event.isShiftClick(), event.isRightClick());

                        inventory.setItem(42,
                                new ItemBuilder(msg.getHeight(), Material.BROWN_MUSHROOM, 1)
                                        .addLore(msg.getOrganicHeight(String.valueOf(goha_builder.getHeight())))
                                        .build());
                    }

                    // HEAD
                    if (slot == 2) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setHead(!goha_builder.getHead());
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setHeadXAngle(addRotationMember(goha_builder.getHeadXAngle(), event.isShiftClick(), event.isRightClick()));
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setHeadYAngle(addRotationMember(goha_builder.getHeadYAngle(), event.isShiftClick(), event.isRightClick()));
                        }

                        inventory.setItem(2,
                                new ItemBuilder(Main.prefix, Material.PLAYER_HEAD, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Head", String.valueOf(goha_builder.getHead())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getHeadXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getHeadYAngle())))
                                        .build());

                    }

                    //bras
                    if (slot == 10) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setArmD(!goha_builder.getArmD());
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setArmDXAngle(addRotationMember(goha_builder.getArmDXAngle(), event.isShiftClick(), event.isRightClick()));
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setArmDYAngle(addRotationMember(goha_builder.getArmDYAngle(), event.isShiftClick(), event.isRightClick()));
                        }

                        inventory.setItem(10,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Right arm", String.valueOf(goha_builder.getArmD())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getArmDXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getArmDYAngle())))
                                        .build());
                    }

                    if (slot == 12) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setArmG(!goha_builder.getArmG());
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setArmGXAngle(addRotationMember(goha_builder.getArmGXAngle(), event.isShiftClick(), event.isRightClick()));
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setArmGYAngle(addRotationMember(goha_builder.getArmGYAngle(), event.isShiftClick(), event.isRightClick()));
                        }

                        inventory.setItem(12,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Left Arm", String.valueOf(goha_builder.getArmG())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getArmGXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getArmGYAngle())))
                                        .build());
                    }

                    // Avant bras
                    if (slot == 18) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setForeArmD(!goha_builder.getForeArmD());
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setForeArmDXAngle(addRotationMember(goha_builder.getForeArmDXAngle(), event.isShiftClick(), event.isRightClick()));
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setForeArmDYAngle(addRotationMember(goha_builder.getForeArmDYAngle(), event.isShiftClick(), event.isRightClick()));
                        }

                        inventory.setItem(18,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Right Fore Arm", String.valueOf(goha_builder.getForeArmD())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getForeArmDXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getForeArmDYAngle())))
                                        .build());
                    }

                    if (slot == 22) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setForeArmG(!goha_builder.getForeArmG());
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setForeArmGXAngle(addRotationMember(goha_builder.getForeArmGXAngle(), event.isShiftClick(), event.isRightClick()));
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setForeArmGYAngle(addRotationMember(goha_builder.getForeArmGYAngle(), event.isShiftClick(), event.isRightClick()));
                        }

                        inventory.setItem(22,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Left Fore Arm", String.valueOf(goha_builder.getForeArmG())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getForeArmGXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getForeArmGYAngle())))
                                        .build());
                    }

                    // torse
                    if (slot == 20) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setTorso(!goha_builder.getTorso());
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setTorsoXAngle(addRotationMember(goha_builder.getTorsoXAngle(), event.isShiftClick(), event.isRightClick()));
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setTorsoYAngle(addRotationMember(goha_builder.getTorsoYAngle(), event.isShiftClick(), event.isRightClick()));
                        }

                        inventory.setItem(20,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Torso", String.valueOf(goha_builder.getTorso())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getTorsoXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getTorsoYAngle())))
                                        .build());
                    }


                    // cuisse
                    if (slot == 37) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setLegD(!goha_builder.getLegD());
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setLegDXAngle(addRotationMember(goha_builder.getLegDXAngle(), event.isShiftClick(), event.isRightClick()));
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setLegDYAngle(addRotationMember(goha_builder.getLegDYAngle(), event.isShiftClick(), event.isRightClick()));
                        }

                        inventory.setItem(37,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Right Leg", String.valueOf(goha_builder.getLegD())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getLegDXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getLegDYAngle())))
                                        .build());
                    }

                    if (slot == 39) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setLegG(!goha_builder.getLegG());
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setLegGXAngle(addRotationMember(goha_builder.getLegGXAngle(), event.isShiftClick(), event.isRightClick()));
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setLegGYAngle(addRotationMember(goha_builder.getLegGYAngle(), event.isShiftClick(), event.isRightClick()));
                        }

                        inventory.setItem(39,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Left Leg", String.valueOf(goha_builder.getLegG())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getLegGXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getLegGYAngle())))
                                        .build());
                    }

                    // tibia
                    if (slot == 46) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setTibiaD(!goha_builder.getTibiaD());
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setTibiaDXAngle(addRotationMember(goha_builder.getTibiaDXAngle(), event.isShiftClick(), event.isRightClick()));
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setTibiaDYAngle(addRotationMember(goha_builder.getTibiaDYAngle(), event.isShiftClick(), event.isRightClick()));
                        }

                        inventory.setItem(46,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Right Tibia", String.valueOf(goha_builder.getTibiaD())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getTibiaDXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getTibiaDYAngle())))
                                        .build());
                    }

                    if (slot == 48) {

                        if (goha_builder.getCommutateur() == 0) {
                            goha_builder.setTibiaG(!goha_builder.getTibiaG());
                        } else if (goha_builder.getCommutateur() == 1) {
                            goha_builder.setTibiaGXAngle(addRotationMember(goha_builder.getTibiaGXAngle(), event.isShiftClick(), event.isRightClick()));
                        } else if (goha_builder.getCommutateur() == 2) {
                            goha_builder.setTibiaGYAngle(addRotationMember(goha_builder.getTibiaGYAngle(), event.isShiftClick(), event.isRightClick()));
                        }

                        inventory.setItem(48,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Left Tibia", String.valueOf(goha_builder.getTibiaG())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getTibiaGXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getTibiaGYAngle())))
                                        .build());
                    }

                    if (goha_builder.getPregen()) {

                        goha_builder.setParticleID();

                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {

                            new GOHA_Builder.OrganicGeneration(p, goha_builder.getStartLoc())
                                    .getAllPoint()
                                    .generateAllParticle();

                        }, 20);
                    }

                })).listener(new EventCreator<>(InventoryClickEvent.class, event -> {

                    if (event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {

                        ItemStack it = event.getCurrentItem();
                        assert it != null;

                        event.setCursor(it);
                    }
                }))
                .provider(new InventoryProvider() {

                    final GOHA_Builder goha_builder = GOHA_Builder.getGOHABuilder(p);

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        contents.set(0,8, IntelligentItem.of(new ItemBuilder(msg.getBack(), Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(RightArrow)
                                        .addLore(msg.getBack())
                                        .build(),
                                event -> new MainGUI().openMainInventory(p)));

                        contents.fillColumn(5, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1).build());

                        contents.set(0, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(1, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(3, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(4, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(6, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(7, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(9, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(13, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(19, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(21, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(26, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(27, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(28, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(30, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(31, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(33, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(34, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(35, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(36, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(38, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(40, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(45, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(47, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(49, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(51, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(52, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());
                        contents.set(53, new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build());

                        //X rotation
                        contents.set(24,
                                new ItemBuilder(msg.getPitchAngleConf(), Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(X)
                                        .addLore(msg.getClickChangeAngle("pitch"))
                                        .build());
                        //Y rotation
                        contents.set(25,
                                new ItemBuilder(msg.getYawAngleConf(), Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(Y)
                                        .addLore(msg.getClickChangeAngle("yaw"))
                                        .build());

                        contents.set(26,
                                new ItemBuilder(msg.getMemberConf(), Material.BARRIER, 1)
                                    .addLore(msg.getClickEnableDisable())
                                    .build());


                        // XY
                        if (goha_builder.getCommutateur() == 0) {
                            contents.set(15, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                            contents.set(16, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                            contents.set(17, new ItemBuilder("", Material.GREEN_STAINED_GLASS_PANE, 1).build());
                        }
                        if (goha_builder.getCommutateur() == 1) {
                            contents.set(15, new ItemBuilder("", Material.GREEN_STAINED_GLASS_PANE, 1).build());
                            contents.set(16, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                            contents.set(17, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                        }
                        if (goha_builder.getCommutateur() == 2) {
                            contents.set(15, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                            contents.set(16, new ItemBuilder("", Material.GREEN_STAINED_GLASS_PANE, 1).build());
                            contents.set(17, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1).build());
                        }

                        //Height
                        contents.set(42,
                                new ItemBuilder(msg.getHeight(), Material.BROWN_MUSHROOM, 1)
                                    .addLore(msg.getOrganicHeight(String.valueOf(goha_builder.getHeight())))
                                    .build());

                        //Pregen
                        if (goha_builder.getPregen()) {

                            contents.set(43,
                                    new ItemBuilder(msg.getPregeneration(), Material.ARMOR_STAND, 1)
                                            .addLore(msg.getClickClearParticle())
                                            .build());

                        } else {
                            contents.set(43,
                                    new ItemBuilder(msg.getPregeneration(), Material.ARMOR_STAND, 1)
                                            .addLore(msg.getClickPregen())
                                            .build());
                        }

                        //Generate
                        contents.set(44,
                                new ItemBuilder(msg.getGenerate(), Material.ARMOR_STAND, 1)
                                        .addLore(msg.getClickGenerate())
                                        .build());

                        //tete
                        contents.set(2,
                                new ItemBuilder(Main.prefix, Material.PLAYER_HEAD, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Head", String.valueOf(goha_builder.getHead())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getHeadXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getHeadYAngle())))
                                        .build());

                        //bras
                        contents.set(10,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Right arm", String.valueOf(goha_builder.getArmD())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getArmDXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getArmDYAngle())))
                                        .build());

                        contents.set(11, new ItemBuilder(Main.prefix, Material.BONE, 1).build());

                        contents.set(12,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Left Arm", String.valueOf(goha_builder.getArmG())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getArmGXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getArmGYAngle())))
                                        .build());
                        // Avant bras
                        contents.set(18,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Right Fore Arm", String.valueOf(goha_builder.getForeArmD())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getForeArmDXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getForeArmDYAngle())))
                                        .build());

                        contents.set(22,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Left Fore Arm", String.valueOf(goha_builder.getForeArmG())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getForeArmGXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getForeArmGYAngle())))
                                        .build());

                        // torse
                        contents.set(20,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Torso", String.valueOf(goha_builder.getTorso())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getTorsoXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getTorsoYAngle())))
                                        .build());

                        contents.set(29, new ItemBuilder(Main.prefix, Material.BONE, 1).build());

                        // cuisse
                        contents.set(37,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Right Leg", String.valueOf(goha_builder.getLegD())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getLegDXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getLegDYAngle())))
                                        .build());

                        contents.set(39,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Left Leg", String.valueOf(goha_builder.getLegG())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getLegGXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getLegGYAngle())))
                                        .build());
                        // tibia
                        contents.set(46,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Right Tibia", String.valueOf(goha_builder.getTibiaD())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getTibiaDXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getTibiaDYAngle())))
                                        .build());

                        contents.set(48,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore(msg.getInteractMember())
                                        .addLore(msg.getSwitchMember("Left Tibia", String.valueOf(goha_builder.getTibiaG())))
                                        .addLore(msg.getPitchLevel(String.valueOf(goha_builder.getTibiaGXAngle())))
                                        .addLore(msg.getYawLevel(String.valueOf(goha_builder.getTibiaGYAngle())))
                                        .build());

                    }
                })
                .build(Main.getInstance())
                .open(p);
    }

    private static short addRotationMember(short n, boolean isShiftClick, boolean isRightClick) {

        short num;

        if (isShiftClick) {
            if (isRightClick) {
                num = 10;
            } else {
                num = -10;
            }
        } else {
            if (isRightClick) {
                num = 1;
            } else {
                num = -1;
            }
        }

        short maxRotation = 180;
        if (n + num > maxRotation) return maxRotation;
        short minRotation = -180;
        if (n + num < minRotation) return minRotation;

        return (short) (n + num);
    }
}
