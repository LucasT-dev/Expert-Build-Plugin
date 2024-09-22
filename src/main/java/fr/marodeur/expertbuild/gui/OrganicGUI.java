package fr.marodeur.expertbuild.gui;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.builderObjects.GohaParameter;
import fr.marodeur.expertbuild.object.guibuilder.*;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OrganicGUI {

    //private final String X = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzM4YWIxNDU3NDdiNGJkMDljZTAzNTQzNTQ5NDhjZTY5ZmY2ZjQxZDllMDk4YzY4NDhiODBlMTg3ZTkxOSJ9fX0=";
    //private final String Y = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTcxMDcxYmVmNzMzZjQ3NzAyMWIzMjkxZGMzZDQ3ZjBiZGYwYmUyZGExYjE2NWExMTlhOGZmMTU5NDU2NyJ9fX0=";
    //public final String RightArrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlkZGRhM2RkMTkxZDYwNTk5MDA3MDBlZDBjYWY1NGZjNzdjM2Q4MTVhYTI5NDZiYzA5YjY5YWIyZjJmZjk5NiJ9fX0=";

    private final String X = "c38ab145747b4bd09ce0354354948ce69ff6f41d9e098c6848b80e187e919";
    private final String Y = "a71071bef733f477021b3291dc3d47f0bdf0be2da1b165a119a8ff1594567";
    private final String RightArrow = "16227036b8afed6935d53143d16488d39cf4fb73a671f2b2955e80fc9dfe458";

    public void openOrganicGUI(Player p) {

        Inventory.build()
                .setTitle(new Message.MessageSender("expbuild.message.gui.organic_gui_title", true).getMessage())
                .rows(6)
                .updateTask(false)
                .listener(new EventBuilder<>(InventoryClickEvent.class, event -> {

                    /*
                    BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);
                    final GohaParameter goha_builder = brushBuilder.getGohaParameter();

                    short slot = (short) event.getSlot();
                    Inventory inventory = event.getClickedInventory();

                    //Pregen
                    if (slot == 43) {

                        //Clear pregeneration particle
                        if (goha_builder.getPregen()) {

                            inventory.setItem(43,
                                    new ItemBuilder("expbuild.message.commands.pregeneration", false, Material.ARMOR_STAND, 1)
                                            .addLore("expbuild.message.gui.click_pregen", false)
                                            .build());

                            goha_builder.setPregen(false)
                                    .setParticleID()
                                    .setStartLoc(null);

                            //Pregeneration
                        } else {

                            inventory.setItem(43,
                                    new ItemBuilder("expbuild.message.commands.generate", false, Material.ARMOR_STAND, 1)
                                            .addLore("expbuild.message.gui.click_generate", false)
                                            .build());

                            goha_builder.setPregen(true)
                                    .setParticleID()
                                    .setStartLoc(new BlockVectorTool().toBlockVectorTool(p.getLocation()))

                                    .getAllPoint()
                                    .generateAllParticle();

                        }
                    }

                    // Generate
                    if (slot == 44) {

                        if (goha_builder.getStartLoc() != null) {

                            goha_builder.getAllPoint()
                                    .generateAllBlock()
                                    .buildBlock();
                        } else {

                            goha_builder.setStartLoc(new BlockVectorTool().toBlockVectorTool(p.getLocation()))
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
                                new ItemBuilder("expbuild.message.gui.pitch_angle_conf", false, Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(X)
                                        .addLore("expbuild.message.gui.click_change_angle", false, new String[]{"pitch"})
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
                                new ItemBuilder("expbuild.message.gui.yaw_angle_conf", false, Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(Y)
                                        .addLore("expbuild.message.gui.click_change_angle", false, new String[]{"yaw"})
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
                                new ItemBuilder("expbuild.message.gui.member_conf", false, Material.BARRIER, 1)
                                        .addLore("expbuild.message.gui.click_enable_disable", false)
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
                                new ItemBuilder("expbuild.message.gui.height", false, Material.BROWN_MUSHROOM, 1)
                                        .addLore("expbuild.message.gui.organic_height", false, new String[]{String.valueOf(goha_builder.getHeight())})
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
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Head", String.valueOf(goha_builder.getHead())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getHeadXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getHeadYAngle())})
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
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Right arm", String.valueOf(goha_builder.getArmD())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getArmDXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getArmDYAngle())})
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
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Left Arm", String.valueOf(goha_builder.getArmG())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getArmGXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getArmGYAngle())})
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
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Right Fore Arm", String.valueOf(goha_builder.getForeArmD())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getForeArmDXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getForeArmDYAngle())})
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
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Left Fore Arm", String.valueOf(goha_builder.getForeArmG())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getForeArmGXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getForeArmGYAngle())})
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
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Torso", String.valueOf(goha_builder.getTorso())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getTorsoXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getTorsoYAngle())})
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
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Right Leg", String.valueOf(goha_builder.getLegD())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getLegDXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getLegDYAngle())})
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
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Left Leg", String.valueOf(goha_builder.getLegG())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getLegGXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getLegGYAngle())})
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
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Right Tibia", String.valueOf(goha_builder.getTibiaD())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getTibiaDXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getTibiaDYAngle())})
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
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Left Tibia", String.valueOf(goha_builder.getTibiaG())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getTibiaGXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getTibiaGYAngle())})
                                        .build());
                    }

                    if (goha_builder.getPregen()) {

                        goha_builder.setParticleID();

                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {

                            goha_builder.getAllPoint()
                                    .generateAllParticle();

                        }, 20);
                    }*/
                }))


                .provider(new InventoryProvider() {

                    final BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);
                    final GohaParameter goha_builder = brushBuilder.getGohaParameter();

                    @Override
                    public void update(Player player, InventoryContents contents) {}

                    @Override
                    public void close(Player player, Inventory inventory) {}

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        contents.set(new ItemData(0,8, new ItemBuilder("expbuild.message.gui.back", false, Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(RightArrow)
                                        .addLore("expbuild.message.gui.back", false)
                                        .build(),
                                event -> new MainGUI().openMainInventory(p)));

                        contents.setAll(new ItemData().itemDataList(
                                new Integer[]{5,14,23,32,41,50},
                                new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1).build()));

                        contents.setAll(new ItemData().itemDataList(
                                new Integer[]{0,1,3,4,6,7,9,13,19,21,27,28,30,31,33,34,35,36,38,40,45,47,49,51,52,53},
                                new ItemBuilder(" ", Material.BLACK_STAINED_GLASS_PANE, 1).build()));


                        //X rotation
                        contents.set(new ItemData(24,
                                new ItemBuilder("expbuild.message.gui.pitch_angle_conf", false, Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(X)
                                        .addLore("expbuild.message.gui.click_change_angle", false, new String[]{"pitch"})
                                        .build(),
                                event -> {

                                    if (goha_builder.getCommutateur() != 2) {
                                        goha_builder.setCommutateur((short) 2);

                                        contents.updateMaterial(15, Material.GREEN_STAINED_GLASS_PANE);
                                        contents.updateMaterial(16, Material.RED_STAINED_GLASS_PANE);
                                        contents.updateMaterial(17, Material.RED_STAINED_GLASS_PANE);
                                    }
                                }));
                        //Y rotation
                        contents.set(new ItemData(25,
                                new ItemBuilder("expbuild.message.gui.yaw_angle_conf", false, Material.PLAYER_HEAD, 1)
                                        .setSkullTextures(Y)
                                        .addLore("expbuild.message.gui.click_change_angle", false, new String[]{"yaw"})
                                        .build(),
                                event -> {

                                    if (goha_builder.getCommutateur() != 3) {
                                        goha_builder.setCommutateur((short) 3);

                                        contents.updateMaterial(15, Material.RED_STAINED_GLASS_PANE);
                                        contents.updateMaterial(16, Material.GREEN_STAINED_GLASS_PANE);
                                        contents.updateMaterial(17, Material.RED_STAINED_GLASS_PANE);
                                    }
                                }));

                        // ENABLE / DISABLE MEMBER
                        contents.set(new ItemData(26,
                                new ItemBuilder("expbuild.message.gui.member_conf", false, Material.BARRIER, 1)
                                    .addLore("expbuild.message.gui.click_enable_disable", false)
                                    .build(),
                                event -> {

                                    if (goha_builder.getCommutateur() != 0) {
                                        goha_builder.setCommutateur((short) 0);

                                        contents.updateMaterial(15, Material.RED_STAINED_GLASS_PANE);
                                        contents.updateMaterial(16, Material.RED_STAINED_GLASS_PANE);
                                        contents.updateMaterial(17, Material.GREEN_STAINED_GLASS_PANE);
                                    }
                                }));


                        // XY

                        // Commutateur = 0 -> Enable disable member:
                        // Commutateur = 1 -> Pitch member:
                        // Commutateur = 2 -> Yaw member:

                        contents.set(new ItemData(15, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1),
                                event -> {

                            if (goha_builder.getCommutateur() != 1) {
                                goha_builder.setCommutateur((short) 1);

                                contents.updateMaterial(15, Material.GREEN_STAINED_GLASS_PANE);
                                contents.updateMaterial(16, Material.RED_STAINED_GLASS_PANE);
                                contents.updateMaterial(17, Material.RED_STAINED_GLASS_PANE);
                            }
                        }));
                        contents.set(new ItemData(16, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1),
                                event -> {

                            if (goha_builder.getCommutateur() != 2) {
                                goha_builder.setCommutateur((short) 2);

                                contents.updateMaterial(15, Material.RED_STAINED_GLASS_PANE);
                                contents.updateMaterial(16, Material.GREEN_STAINED_GLASS_PANE);
                                contents.updateMaterial(17, Material.RED_STAINED_GLASS_PANE);
                            }
                        }));
                        contents.set(new ItemData(17, new ItemBuilder("", Material.RED_STAINED_GLASS_PANE, 1),
                                event -> {

                            if (goha_builder.getCommutateur() != 0) {
                                goha_builder.setCommutateur((short) 0);

                                contents.updateMaterial(15, Material.RED_STAINED_GLASS_PANE);
                                contents.updateMaterial(16, Material.RED_STAINED_GLASS_PANE);
                                contents.updateMaterial(17, Material.GREEN_STAINED_GLASS_PANE);
                            }
                        }));

                        if (goha_builder.getCommutateur() == 0) {
                            contents.updateMaterial(17,Material.GREEN_STAINED_GLASS_PANE);
                        }
                        if (goha_builder.getCommutateur() == 1) {
                            contents.updateMaterial(15,Material.GREEN_STAINED_GLASS_PANE);
                        }
                        if (goha_builder.getCommutateur() == 2) {
                            contents.updateMaterial(16,Material.GREEN_STAINED_GLASS_PANE);
                        }

                        //Height
                        contents.set(new ItemData(42,
                                new ItemBuilder("expbuild.message.gui.height", false, Material.BROWN_MUSHROOM, 1)
                                    .addLore("expbuild.message.gui.organic_height", false, new String[]{String.valueOf(goha_builder.getHeight())}),
                                event -> {

                                    goha_builder.addHeight(event.isShiftClick(), event.isRightClick());

                                    contents.updateTitle(42, "expbuild.message.gui.height", false);
                                    contents.updateLore(42, 0, "expbuild.message.gui.organic_height", false, new String[]{String.valueOf(goha_builder.getHeight())});

                                }));


                        //Pregen

                        contents.set(new ItemData(43,
                                new ItemBuilder("expbuild.message.commands.pregeneration", false, Material.ARMOR_STAND, 1)
                                        .addLore(goha_builder.getPregen()? "expbuild.message.gui.click_clear_particle" : "expbuild.message.gui.click_pregen"
                                                , false),
                                event -> {

                                    //Clear pregeneration particle
                                    if (goha_builder.getPregen()) {

                                        goha_builder.setPregen(false)
                                                .setParticleID()
                                                .setStartLoc(null);

                                        contents.updateLore(43, 0, "expbuild.message.gui.click_pregen", false);

                                        //Pregeneration
                                    } else {

                                        goha_builder.setPregen(true)
                                                .setParticleID()
                                                .setStartLoc(new BlockVectorTool().toBlockVectorTool(p.getLocation()))

                                                .getAllPoint()
                                                .generateAllParticle();

                                        contents.updateLore(43, 0, "expbuild.message.gui.click_clear_particle", false);

                                    }
                                }));

                        //Generate
                        contents.set(new ItemData(44,
                                new ItemBuilder("expbuild.message.commands.generate", false, Material.ARMOR_STAND, 1)
                                        .addLore("expbuild.message.gui.click_generate", false),
                                event -> {

                                    if (goha_builder.getStartLoc() != null) {

                                        goha_builder.getAllPoint()
                                                .generateAllBlock()
                                                .buildBlock();
                                    } else {

                                        goha_builder.setStartLoc(new BlockVectorTool().toBlockVectorTool(p.getLocation()))
                                                .getAllPoint()
                                                .generateAllBlock()
                                                .buildBlock();
                                    }

                                    goha_builder.setPregen(false)
                                            .setParticleID()
                                            .setStartLoc(null);

                                    contents.updateLore(43, 0, "expbuild.message.gui.click_pregen", false);

                                }));

                        //tete
                        contents.set(new ItemData(2,
                                new ItemBuilder(Main.prefix, Material.PLAYER_HEAD, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Head", String.valueOf(goha_builder.getHead())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getHeadXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getHeadYAngle())}),
                                event -> {

                                    if (goha_builder.getCommutateur() == 0) {
                                        goha_builder.setHead(!goha_builder.getHead());
                                        contents.updateLore(2, 1, "expbuild.message.gui.switch_member", false, new String[]{"Head", String.valueOf(goha_builder.getHead())});
                                    } else if (goha_builder.getCommutateur() == 1) {
                                        goha_builder.setHeadXAngle(addRotationMember(goha_builder.getHeadXAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(2,2, "expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getHeadXAngle())});
                                    } else if (goha_builder.getCommutateur() == 2) {
                                        goha_builder.setHeadYAngle(addRotationMember(goha_builder.getHeadYAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(2, 3, "expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getHeadYAngle())});
                                    }
                                }));

                        //bras
                        contents.set(new ItemData(10,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Right arm", String.valueOf(goha_builder.getArmD())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getArmDXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getArmDYAngle())}),
                                event -> {

                                    if (goha_builder.getCommutateur() == 0) {
                                        goha_builder.setArmD(!goha_builder.getArmD());
                                        contents.updateLore(10, 1, "expbuild.message.gui.switch_member", false, new String[]{"Right arm", String.valueOf(goha_builder.getArmD())});
                                    } else if (goha_builder.getCommutateur() == 1) {
                                        goha_builder.setArmDXAngle(addRotationMember(goha_builder.getArmDXAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(10, 2, "expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getArmDXAngle())});
                                    } else if (goha_builder.getCommutateur() == 2) {
                                        goha_builder.setArmDYAngle(addRotationMember(goha_builder.getArmDYAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(10, 3, "expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getArmDYAngle())});
                                    }
                                }));

                        contents.set(new ItemData(11, new ItemBuilder(Main.prefix, Material.BONE, 1)));

                        contents.set(new ItemData(12,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Left Arm", String.valueOf(goha_builder.getArmG())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getArmGXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getArmGYAngle())}),
                                event -> {

                                    if (goha_builder.getCommutateur() == 0) {
                                        goha_builder.setArmG(!goha_builder.getArmG());
                                        contents.updateLore(12, 1, "expbuild.message.gui.switch_member", false, new String[]{"Left Arm", String.valueOf(goha_builder.getArmG())});
                                    } else if (goha_builder.getCommutateur() == 1) {
                                        goha_builder.setArmGXAngle(addRotationMember(goha_builder.getArmGXAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(12, 2, "expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getArmGXAngle())});
                                    } else if (goha_builder.getCommutateur() == 2) {
                                        goha_builder.setArmGYAngle(addRotationMember(goha_builder.getArmGYAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(12, 3, "expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getArmGYAngle())});
                                    }
                                }));

                        // Avant bras
                        contents.set(new ItemData(18,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Right Fore Arm", String.valueOf(goha_builder.getForeArmD())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getForeArmDXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getForeArmDYAngle())}),
                                event -> {

                                    if (goha_builder.getCommutateur() == 0) {
                                        goha_builder.setForeArmD(!goha_builder.getForeArmD());
                                        contents.updateLore(18, 1, "expbuild.message.gui.switch_member", false, new String[]{"Right Fore Arm", String.valueOf(goha_builder.getForeArmD())});
                                    } else if (goha_builder.getCommutateur() == 1) {
                                        goha_builder.setForeArmDXAngle(addRotationMember(goha_builder.getForeArmDXAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(18, 2, "expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getForeArmDXAngle())});
                                    } else if (goha_builder.getCommutateur() == 2) {
                                        goha_builder.setForeArmDYAngle(addRotationMember(goha_builder.getForeArmDYAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(18, 3, "expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getForeArmDYAngle())});
                                    }
                                }));

                        contents.set(new ItemData(22,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Left Fore Arm", String.valueOf(goha_builder.getForeArmG())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getForeArmGXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getForeArmGYAngle())}),
                                event -> {

                                    if (goha_builder.getCommutateur() == 0) {
                                        goha_builder.setForeArmG(!goha_builder.getForeArmG());
                                        contents.updateLore(22, 1, "expbuild.message.gui.switch_member", false, new String[]{"Left Fore Arm", String.valueOf(goha_builder.getForeArmG())});
                                    } else if (goha_builder.getCommutateur() == 1) {
                                        goha_builder.setForeArmGXAngle(addRotationMember(goha_builder.getForeArmGXAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(22, 2, "expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getForeArmGXAngle())});
                                    } else if (goha_builder.getCommutateur() == 2) {
                                        goha_builder.setForeArmGYAngle(addRotationMember(goha_builder.getForeArmGYAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(22, 3, "expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getForeArmGYAngle())});
                                    }
                                }));


                        // torse
                        contents.set(new ItemData(20,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Torso", String.valueOf(goha_builder.getTorso())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getTorsoXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getTorsoYAngle())}),
                                event -> {

                                    if (goha_builder.getCommutateur() == 0) {
                                        goha_builder.setTorso(!goha_builder.getTorso());
                                        contents.updateLore(20, 1, "expbuild.message.gui.switch_member", false, new String[]{"Torso", String.valueOf(goha_builder.getTorso())});
                                    } else if (goha_builder.getCommutateur() == 1) {
                                        goha_builder.setTorsoXAngle(addRotationMember(goha_builder.getTorsoXAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(20, 2, "expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getTorsoXAngle())});
                                    } else if (goha_builder.getCommutateur() == 2) {
                                        goha_builder.setTorsoYAngle(addRotationMember(goha_builder.getTorsoYAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(20, 3, "expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getTorsoYAngle())});
                                    }
                                }));


                        contents.set(new ItemData(29, new ItemBuilder(Main.prefix, Material.BONE, 1)));

                        // cuisse
                        contents.set(new ItemData(37,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Right Leg", String.valueOf(goha_builder.getLegD())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getLegDXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getLegDYAngle())}),
                                event -> {

                                    if (goha_builder.getCommutateur() == 0) {
                                        goha_builder.setLegD(!goha_builder.getLegD());
                                        contents.updateLore(37, 1, "expbuild.message.gui.switch_member", false, new String[]{"Right Leg", String.valueOf(goha_builder.getLegD())});
                                    } else if (goha_builder.getCommutateur() == 1) {
                                        goha_builder.setLegDXAngle(addRotationMember(goha_builder.getLegDXAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(37, 2, "expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getLegDXAngle())});
                                    } else if (goha_builder.getCommutateur() == 2) {
                                        goha_builder.setLegDYAngle(addRotationMember(goha_builder.getLegDYAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(37, 3, "expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getLegDYAngle())});
                                    }
                                }));


                        contents.set(new ItemData(39,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Left Leg", String.valueOf(goha_builder.getLegG())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getLegGXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getLegGYAngle())}),
                                event -> {

                                    if (goha_builder.getCommutateur() == 0) {
                                        goha_builder.setLegG(!goha_builder.getLegG());
                                        contents.updateLore(39, 1, "expbuild.message.gui.switch_member", false, new String[]{"Left Leg", String.valueOf(goha_builder.getLegG())});
                                    } else if (goha_builder.getCommutateur() == 1) {
                                        goha_builder.setLegGXAngle(addRotationMember(goha_builder.getLegGXAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(39, 2, "expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getLegGXAngle())});
                                    } else if (goha_builder.getCommutateur() == 2) {
                                        goha_builder.setLegGYAngle(addRotationMember(goha_builder.getLegGYAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(39, 3, "expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getLegGYAngle())});
                                    }
                                }));

                        // tibia
                        contents.set(new ItemData(46,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Right Tibia", String.valueOf(goha_builder.getTibiaD())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getTibiaDXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getTibiaDYAngle())}),
                                event -> {

                                    if (goha_builder.getCommutateur() == 0) {
                                        goha_builder.setTibiaD(!goha_builder.getTibiaD());
                                        contents.updateLore(46, 1, "expbuild.message.gui.switch_member", false, new String[]{"Right Tibia", String.valueOf(goha_builder.getTibiaD())});
                                    } else if (goha_builder.getCommutateur() == 1) {
                                        goha_builder.setTibiaDXAngle(addRotationMember(goha_builder.getTibiaDXAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(46, 2, "expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getTibiaDXAngle())});
                                    } else if (goha_builder.getCommutateur() == 2) {
                                        goha_builder.setTibiaDYAngle(addRotationMember(goha_builder.getTibiaDYAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(46, 3, "expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getTibiaDYAngle())});
                                    }
                                }));


                        contents.set(new ItemData(48,
                                new ItemBuilder(Main.prefix, Material.BONE, 1)
                                        .addEnchant(Enchantment.LUCK, 1)
                                        .addLore("expbuild.message.gui.interact_member", false)
                                        .addLore("expbuild.message.gui.switch_member", false, new String[]{"Left Tibia", String.valueOf(goha_builder.getTibiaG())})
                                        .addLore("expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getTibiaGXAngle())})
                                        .addLore("expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getTibiaGYAngle())}),
                                event -> {

                                    if (goha_builder.getCommutateur() == 0) {
                                        goha_builder.setTibiaG(!goha_builder.getTibiaG());
                                        contents.updateLore(48, 1, "expbuild.message.gui.switch_member", false, new String[]{"Left Tibia", String.valueOf(goha_builder.getTibiaG())});
                                    } else if (goha_builder.getCommutateur() == 1) {
                                        goha_builder.setTibiaGXAngle(addRotationMember(goha_builder.getTibiaGXAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(48, 2, "expbuild.message.gui.pitch_level", false, new String[]{String.valueOf(goha_builder.getTibiaGXAngle())});
                                    } else if (goha_builder.getCommutateur() == 2) {
                                        goha_builder.setTibiaGYAngle(addRotationMember(goha_builder.getTibiaGYAngle(), event.isShiftClick(), event.isRightClick()));
                                        contents.updateLore(48, 3, "expbuild.message.gui.yaw_level", false, new String[]{String.valueOf(goha_builder.getTibiaGYAngle())});
                                    }
                                }));
                    }
                })
                .build()
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
