package fr.marodeur.expertbuild.enums;

import fr.marodeur.expertbuild.Main;

import java.util.Arrays;
import java.util.stream.Stream;

public enum BlocksDataColor {

    // If blockVersion = 1.07, block version is <= 1.7.2

    // ADD SNOW

    YELLOW_WOOL("yellow_wool", 249, 198, 40, 1.07),
    YELLOW_TERRACOTTA("yellow_terracotta", 186, 133, 35, 1.07),
    YELLOW_GLAZED_TERRACOTTA("yellow_glazed_terracotta", 236, 195, 91, 1.12),
    YELLOW_CONCRETE_POWDER("yellow_concrete_powder", 233, 199, 55, 1.12),
    YELLOW_CONCRETE("yellow_concrete", 241, 175, 21, 1.12),
    WHITE_WOOL("white_wool", 234, 236, 237, 1.07),
    WHITE_TERRACOTTA("white_terracotta", 210, 178, 161, 1.07),
    WHITE_GLAZED_TERRACOTTA("white_glazed_terracotta", 186, 212, 206, 1.12),
    WHITE_CONCRETE_POWDER("white_concrete_powder", 226, 228, 228, 1.12),
    WHITE_CONCRETE("white_concrete", 207, 213, 214, 1.12),
    WET_SPONGE("wet_sponge", 170, 180, 70, 1.08),
    WEATHERED_CUT_COPPER("weathered_cut_copper", 109, 145, 107, 1.17),
    WEATHERED_COPPER("weathered_copper", 109, 154, 110, 1.17),
    WARPED_WART_BLOCK("warped_wart_block", 23, 120, 121, 1.16),
    WARPED_STEM("warped_stem", 53, 113, 112, 1.16),
    WARPED_PLANKS("warped_planks", 43, 105, 99, 1.16),
    WARPED_NYLIUM("warped_nylium", 43, 115, 101, 1.16),
    VERDANT_FROGLIGHT("verdant_froglight", 217, 238, 215, 1.19),
    TUFF("tuff", 108, 110, 103, 1.17),
    TUBE_CORAL_BLOCK("tube_coral_block", 49, 88, 207, 1.13),
    TNT("tnt", 180, 93, 90, 1.07),
    TERRACOTTA("terracotta", 152, 94, 68, 1.07),
    STICKY_PISTON("sticky_piston", 118, 151, 89, 1.07),
    //STRIPPED_WARPED_STEM("stripped_warped_stem", 46, 95, 81, 1.16),
    //STRIPPED_SPRUCE_LOG("stripped_spruce_log", 108, 82, 48, 1.13),
    //STRIPPED_OAK_LOG("stripped_oak_log", 162, 131, 78, 1.13),
    //STRIPPED_MANGROVE_LOG("stripped_mangrove_log", 109, 43, 43, 1.19),
    //STRIPPED_JUNGLE_LOG("stripped_jungle_log", 166, 122, 82, 1.13),
    //STRIPPED_DARK_OAK_LOG("stripped_dark_oak_log", 67, 44, 22, 1.13),
    //STRIPPED_CRIMSON_STEM("stripped_crimson_stem", 92, 48, 66, 1.16),
    //STRIPPED_CHERRY_LOG("stripped_cherry_log", 222, 167, 160, 1.20),
    //STRIPPED_BIRCH_LOG("stripped_birch_log", 192, 173, 118, 1.13),
    STRIPPED_BAMBOO_BLOCK("stripped_bamboo_block", 178, 159, 73, 1.20),
    //STRIPPED_ACACIA_LOG("stripped_acacia_log", 167, 91, 51, 1.13),
    STONE_BRICKS("stone_bricks", 122, 122, 122, 1.07),
    STONE("stone", 126, 126, 126, 1.07),
    SPRUCE_PLANKS("spruce_planks", 115, 85, 49, 1.07),

    //SPRUCE_LOG("spruce_log", 110, 82, 48, 1.07),
    SPONGE("sponge", 196, 193, 75, 1.07),
    SOUL_SOIL("soul_soil", 76, 58, 47, 1.16),
    SOUL_SAND("soul_sand", 81, 62, 51, 1.07),
    SNOW("snow", 249, 254, 254, 1.07),
    //SMOOTH_STONE_SLAB("smooth_stone_slab", 168, 168, 168),
    SMOOTH_STONE("smooth_stone", 161, 161, 161, 1.07),
    SMOOTH_BASALT("smooth_basalt", 72, 72, 78, 1.17),
    SMOKER("smoker", 103, 93, 79, 1.14),
    SMITHING_TABLE("smithing_table", 56, 57, 69, 1.14),
    SHROOMLIGHT("shroomlight", 242, 151, 75, 1.16),
    SEA_LANTERN("sea_lantern", 182, 207, 198, 1.08),
    SCULK_CATALYST("sculk_catalyst", 78, 95, 91, 1.19),
    SCULK("sculk", 13, 18, 23, 1.19),
    SMOOTH_SANDSTONE("smooth_sandstone", 224, 214, 170, 1.07),
    SANDSTONE("sandstone", 224, 214, 170, 1.07),
    SAND("sand", 219, 207, 163, 1.07),
    ROOTED_DIRT("rooted_dirt", 144, 103, 76, 1.17),
    RESPAWN_ANCHOR("respawn_anchor", 39, 24, 62, 1.16),
    REINFORCED_DEEPSLATE("reinforced_deepslate", 100, 106, 99, 1.19),
    RED_WOOL("red_wool", 161, 39, 35, 1.07),
    RED_TERRACOTTA("red_terracotta", 143, 61, 47, 1.07),
    SMOOOTH_RED_SANDSTONE("smooth_red_sandstone", 181, 98, 32, 1.08),
    RED_SAND("red_sand", 190, 103, 33, 1.07),
    RED_NETHER_BRICKS("red_nether_bricks", 70, 7, 9, 1.10),
    RED_MUSHROOM_BLOCK("red_mushroom_block", 201, 48, 46, 1.07),
    RED_GLAZED_TERRACOTTA("red_glazed_terracotta", 182, 59, 52, 1.12),
    RED_CONCRETE_POWDER("red_concrete_powder", 168, 54, 51, 1.12),
    RED_CONCRETE("red_concrete", 142, 33, 33, 1.12),
    REDSTONE_ORE("redstone_ore", 142, 108, 108, 1.17),
    //REDSTONE_LAMP_ON("redstone_lamp_on", 151, 109, 65),
    REDSTONE_LAMP("redstone_lamp", 99, 57, 31, 1.07),
    REDSTONE_BLOCK("redstone_block", 115, 12, 0, 1.07),
    RAW_IRON_BLOCK("raw_iron_block", 166, 136, 107, 1.17),
    RAW_GOLD_BLOCK("raw_gold_block", 221, 168, 47, 1.17),
    RAW_COPPER_BLOCK("raw_copper_block", 156, 106, 79, 1.17),
    QUARTZ_PILLAR("quartz_pillar", 236, 231, 224, 1.07),
    QUARTZ_BRICKS("quartz_bricks", 235, 229, 222, 1.16),
    QUARTZ_BLOCK("quartz_block", 237, 230, 224, 1.07),
    PURPUR_PILLAR("purpur_pillar", 172, 123, 172, 1.09),
    PURPUR_BLOCK("purpur_block", 170, 126, 170, 1.09),
    PURPLE_WOOL("purple_wool", 122, 42, 173, 1.07),
    PURPLE_TERRACOTTA("purple_terracotta", 118, 70, 86, 1.07),
    PURPLE_GLAZED_TERRACOTTA("purple_glazed_terracotta", 109, 49, 152, 1.12),
    PURPLE_CONCRETE_POWDER("purple_concrete_powder", 132, 56, 178, 1.12),
    PURPLE_CONCRETE("purple_concrete", 100, 32, 156, 1.12),
    PUMPKIN("pumpkin", 198, 117, 25, 1.13),
    PRISMARINE_BRICKS("prismarine_bricks", 99, 172, 159, 1.08),
    PRISMARINE("prismarine", 99, 162, 146, 1.08),
    POWDER_SNOW("powder_snow", 248, 253, 253, 1.17),
    POLISHED_GRANITE("polished_granite", 155, 107, 89, 1.08),
    POLISHED_DIORITE("polished_diorite", 195, 195, 196, 1.08),
    POLISHED_DEEPSLATE("polished_deepslate", 72, 72, 73, 1.17),
    POLISHED_BLACKSTONE_BRICKS("polished_blackstone_bricks", 48, 43, 50, 1.16),
    POLISHED_BLACKSTONE("polished_blackstone", 53, 49, 57, 1.16),
    POLISHED_BASALT("polished_basalt", 101, 100, 102, 1.16),
    POLISHED_ANDESITE("polished_andesite", 132, 135, 134, 1.08),
    PODZOL("podzol", 92, 63, 24, 1.07),

    //PISTON("piston", 152, 126, 82, 1.07),
    PINK_WOOL("pink_wool", 238, 141, 172, 1.07),
    PINK_TERRACOTTA("pink_terracotta", 162, 78, 79, 1.07),
    PINK_GLAZED_TERRACOTTA("pink_glazed_terracotta", 237, 156, 182, 1.12),
    PINK_CONCRETE_POWDER("pink_concrete_powder", 229, 154, 181, 1.12),
    PINK_CONCRETE("pink_concrete", 214, 101, 143, 1.12),
    PEARLESCENT_FROGLIGHT_SIDE("pearlescent_froglight_side", 239, 230, 232, 1.19),
    PACKED_MUD("packed_mud", 143, 107, 80, 1.19),
    PACKED_ICE("packed_ice", 141, 180, 251, 1.07),
    OXIDIZED_CUT_COPPER("oxidized_cut_copper", 80, 154, 127, 1.17),
    OXIDIZED_COPPER("oxidized_copper", 83, 164, 134, 1.17),
    ORANGE_WOOL("orange_wool", 241, 118, 20, 1.07),
    ORANGE_TERRACOTTA("orange_terracotta", 162, 84, 38, 1.07),
    ORANGE_GLAZED_TERRACOTTA("orange_glazed_terracotta", 162, 146, 87, 1.12),
    ORANGE_CONCRETE_POWDER("orange_concrete_powder", 227, 132, 32, 1.12),
    ORANGE_CONCRETE("orange_concrete", 224, 97, 1, 1.12),
    OCHRE_FROGLIGHT("ochre_froglight", 247, 238, 189, 1.19),
    OBSIDIAN("obsidian", 15, 11, 25, 1.07),
    OBSERVER("observer", 70, 68, 68, 1.11),
    OAK_PLANKS("oak_planks", 162, 131, 79, 1.07),
    //OAK_LOG("oak_log", 155, 125, 75, 1.07),
    NOTE_BLOCK("note_block", 92, 60, 41, 1.07),
    NETHER_WART_BLOCK("nether_wart_block", 114, 3, 2, 1.10),
    NETHER_QUARTZ_ORE("nether_quartz_ore", 121, 70, 66, 1.07),
    NETHER_GOLD_ORE("nether_gold_ore", 118, 57, 43, 1.16),
    NETHER_BRICKS("nether_bricks", 33, 17, 20, 1.07),
    NETHERRACK("netherrack", 98, 38, 38, 1.07),
    NETHERITE_BLOCK("netherite_block", 68, 63, 66, 1.16),
    MYCELIUM("mycelium", 131, 105, 106, 1.07),
    MUSHROOM_STEM("mushroom_stem", 203, 196, 185, 1.07),
    MUD_BRICKS("mud_bricks", 171, 134, 97, 1.19),
    MUDDY_MANGROVE_ROOTS("muddy_mangrove_roots", 68, 59, 49, 1.19),
    MUD("mud", 60, 58, 61, 1.19),
    MOSS_BLOCK("moss_block", 89, 110, 45, 1.17),
    MOSSY_STONE_BRICKS("mossy_stone_bricks", 116, 121, 106, 1.07),
    MOSSY_COBBLESTONE("mossy_cobblestone", 109, 118, 94, 1.07),
    MELON("melon", 114, 146, 30, 1.07),
    MANGROVE_PLANKS("mangrove_planks", 118, 54, 49, 1.19),
    //MANGROVE_LOG("mangrove_log", 104, 47, 42, 1.19),
    MAGMA_BLOCK("magma_block", 141, 62, 31, 1.10),
    MAGENTA_WOOL("magenta_wool", 189, 69, 180, 1.07),
    MAGENTA_TERRACOTTA("magenta_terracotta", 150, 88, 109, 1.07),
    MAGENTA_GLAZED_TERRACOTTA("magenta_glazed_terracotta", 207, 100, 190, 1.12),
    MAGENTA_CONCRETE_POWDER("magenta_concrete_powder", 193, 84, 185, 1.12),
    MAGENTA_CONCRETE("magenta_concrete", 169, 48, 159, 1.12),
    LOOM("loom", 141, 119, 93, 1.14),
    LIME_WOOL("lime_wool", 112, 185, 26, 1.07),
    LIME_TERRACOTTA("lime_terracotta", 103, 118, 53, 1.07),
    LIME_GLAZED_TERRACOTTA("lime_glazed_terracotta", 163, 197, 54, 1.12),
    LIME_CONCRETE_POWDER("lime_concrete_powder", 126, 189, 42, 1.12),
    LIME_CONCRETE("lime_concrete", 94, 169, 25, 1.12),
    LIGHT_GRAY_WOOL("light_gray_wool", 142, 142, 135, 1.07),
    LIGHT_GRAY_TERRACOTTA("light_gray_terracotta", 135, 107, 98, 1.07),
    LIGHT_GRAY_GLAZED_TERRACOTTA("light_gray_glazed_terracotta", 145, 167, 169, 1.12),
    LIGHT_GRAY_CONCRETE_POWDER("light_gray_concrete_powder", 155, 155, 148, 1.12),
    LIGHT_GRAY_CONCRETE("light_gray_concrete", 125, 125, 115, 1.12),
    LIGHT_BLUE_WOOL("light_blue_wool", 58, 175, 217, 1.07),
    LIGHT_BLUE_TERRACOTTA("light_blue_terracotta", 114, 109, 138, 1.07),
    LIGHT_BLUE_GLAZED_TERRACOTTA("light_blue_glazed_terracotta", 96, 165, 209, 1.12),
    LIGHT_BLUE_CONCRETE_POWDER("light_blue_concrete_powder", 74, 181, 214, 1.12),
    LIGHT_BLUE_CONCRETE("light_blue_concrete", 36, 137, 199, 1.12),
    LAPIS_ORE("lapis_ore", 105, 117, 143, 1.17),
    LAPIS_BLOCK("lapis_block", 31, 67, 140, 1.07),
    JUNGLE_PLANKS("jungle_planks", 161, 115, 81, 1.07),
    //JUNGLE_LOG("jungle_log", 154, 112, 73, 1.07),
    IRON_ORE("iron_ore", 138, 130, 123, 1.17),
    IRON_BLOCK("iron_block", 222, 222, 222, 1.07),
    HORN_CORAL_BLOCK("horn_coral_block", 216, 199, 66, 1.13),
    HONEYCOMB_BLOCK("honeycomb_block", 229, 149, 30, 1.15),
    HAY_BLOCK("hay_block", 166, 140, 12, 1.07),
    GREEN_WOOL("green_wool", 85, 110, 27, 1.07),
    GREEN_TERRACOTTA("green_terracotta", 76, 83, 42, 1.07),
    GREEN_GLAZED_TERRACOTTA("green_glazed_terracotta", 114, 139, 62, 1.12),
    GREEN_CONCRETE_POWDER("green_concrete_powder", 97, 119, 45, 1.12),
    GREEN_CONCRETE("green_concrete", 73, 91, 36, 1.12),
    GRAY_WOOL("gray_wool", 63, 68, 72, 1.07),
    GRAY_TERRACOTTA("gray_terracotta", 58, 42, 36, 1.07),
    GRAY_GLAZED_TERRACOTTA("gray_glazed_terracotta", 83, 91, 94, 1.12),
    GRAY_CONCRETE_POWDER("gray_concrete_powder", 77, 81, 85, 1.12),
    GRAY_CONCRETE("gray_concrete", 55, 58, 62, 1.12),
    GRAVEL("gravel", 132, 128, 127, 1.07),
    //GRASS_BLOCK_SNOW("grass_block_snow", 168, 148, 130),
    GRANITE("granite", 149, 103, 86, 1.08),
    GOLD_ORE("gold_ore", 147, 135, 105, 1.17),
    GOLD_BLOCK("gold_block", 248, 211, 62, 1.07),
    GLOWSTONE("glowstone", 173, 132, 85, 1.07),
    GILDED_BLACKSTONE("gilded_blackstone", 56, 43, 39, 1.16),
    FURNACE("furnace", 114, 113, 113, 1.07),
    FIRE_CORAL_BLOCK("fire_coral_block", 164, 35, 47, 1.13),
    EXPOSED_CUT_COPPER("exposed_cut_copper", 155, 122, 101, 1.17),
    EXPOSED_COPPER("exposed_copper", 161, 126, 104, 1.17),
    END_STONE_BRICKS("end_stone_bricks", 219, 224, 162, 1.09),
    END_STONE("end_stone", 219, 223, 158, 1.07),
    EMERALD_ORE("emerald_ore", 106, 137, 114, 1.17),
    EMERALD_BLOCK("emerald_block", 43, 205, 90, 1.07),
    DRIPSTONE_BLOCK("dripstone_block", 134, 107, 92, 1.17),
    DRIED_KELP_BLOCK("dried_kelp_block", 52, 60, 40, 1.13),
    DIRT_PATH("dirt_path", 148, 122, 65, 1.09),
    DIRT("dirt", 134, 96, 67, 1.07),
    DIORITE("diorite", 189, 189, 189, 1.08),
    DIAMOND_ORE("diamond_ore", 120, 143, 143, 1.17),
    DIAMOND_BLOCK("diamond_block", 101, 239, 229, 1.07),
    DEEPSLATE("deepslate", 87, 87, 89, 1.17),
    DEEPSLATE_TILES("deepslate_tiles", 55, 55, 56, 1.17),
    DEEPSLATE_REDSTONE_ORE("deepslate_redstone_ore", 107, 72, 73, 1.17),
    DEEPSLATE_LAPIS_ORE("deepslate_lapis_ore", 79, 91, 118, 1.17),
    DEEPSLATE_IRON_ORE("deepslate_iron_ore", 109, 101, 96, 1.17),
    DEEPSLATE_GOLD_ORE("deepslate_gold_ore", 118, 104, 77, 1.17),
    DEEPSLATE_EMERALD_ORE("deepslate_emerald_ore", 77, 106, 87, 1.17),
    DEEPSLATE_DIAMOND_ORE("deepslate_diamond_ore", 83, 109, 110, 1.17),
    DEEPSLATE_COPPER_ORE("deepslate_copper_ore", 93, 94, 89, 1.17),
    DEEPSLATE_COAL_ORE("deepslate_coal_ore", 73, 73, 75, 1.17),
    DEEPSLATE_BRICKS("deepslate_bricks", 71, 71, 71, 1.17),
    DEAD_TUBE_CORAL_BLOCK("dead_tube_coral_block", 131, 124, 120, 1.13),
    DEAD_HORN_CORAL_BLOCK("dead_horn_coral_block", 133, 126, 122, 1.13),
    DEAD_FIRE_CORAL_BLOCK("dead_fire_coral_block", 132, 124, 120, 1.13),
    DEAD_BUBBLE_CORAL_BLOCK("dead_bubble_coral_block", 132, 124, 120, 1.13),
    DEAD_BRAIN_CORAL_BLOCK("dead_brain_coral_block", 125, 118, 115, 1.13),
    DARK_PRISMARINE("dark_prismarine", 52, 92, 76, 1.08),
    DARK_OAK_PLANKS("dark_oak_planks", 67, 43, 20, 1.07),
    //DARK_OAK_LOG("dark_oak_log", 68, 45, 22, 1.07),
    CYAN_WOOL("cyan_wool", 21, 138, 145, 1.07),
    CYAN_TERRACOTTA("cyan_terracotta", 87, 91, 91, 1.07),
    CYAN_GLAZED_TERRACOTTA("cyan_glazed_terracotta", 52, 116, 122, 1.12),
    CYAN_CONCRETE_POWDER("cyan_concrete_powder", 37, 148, 157, 1.12),
    CYAN_CONCRETE("cyan_concrete", 21, 119, 136, 1.12),
    CUT_SANDSTONE("cut_sandstone", 218, 207, 160, 1.14),

    //1.8 ou 1.14 a verif
    CUT_RED_SANDSTONE("cut_red_sandstone", 190, 102, 32, 1.14),
    CUT_COPPER("cut_copper", 191, 107, 81, 1.17),
    CRYING_OBSIDIAN("crying_obsidian", 34, 10, 63, 1.16),
    CRIMSON_STEM("crimson_stem", 114, 51, 72, 1.16),
    CRIMSON_PLANKS("crimson_planks", 101, 49, 71, 1.16),
    CRIMSON_NYLIUM("crimson_nylium", 131, 32, 32, 1.16),
    CRAFTING_TABLE("crafting_table", 123, 75, 43, 1.07),
    CRACKED_STONE_BRICKS("cracked_stone_bricks", 118, 118, 118, 1.07),
    CRACKED_POLISHED_BLACKSTONE_BRICKS("cracked_polished_blackstone_bricks", 44, 38, 44, 1.16),
    CRACKED_NETHER_BRICKS("cracked_nether_bricks", 40, 20, 24, 1.16),
    CRACKED_DEEPSLATE_TILES("cracked_deepslate_tiles", 53, 53, 53, 1.17),
    CRACKED_DEEPSLATE_BRICKS("cracked_deepslate_bricks", 64, 64, 65, 1.17),
    COPPER_ORE("copper_ore", 124, 125, 119, 1.17),
    COPPER_BLOCK("copper_block", 193, 108, 80, 1.17),
    //COMPOSTER("composter", 111, 70, 32, 1.14),
    COBBLESTONE("cobblestone", 128, 127, 127, 1.07),
    COBBLED_DEEPSLATE("cobbled_deepslate", 77, 77, 80, 1.17),
    COARSE_DIRT("coarse_dirt", 119, 85, 59, 1.08),
    COAL_ORE("coal_ore", 104, 104, 103, 1.17),
    COAL_BLOCK("coal_block", 16, 16, 16, 1.07),
    CLAY("clay", 161, 167, 179, 1.07),
    //CHISELED_STONE_BRICKS("chiseled_stone_bricks", 120, 119, 120, 1.07),
    //CHISELED_SANDSTONE("chiseled_sandstone", 216, 203, 155, 1.07),
    //CHISELED_RED_SANDSTONE("chiseled_red_sandstone", 183, 97, 27, 1.07),
    //CHISELED_QUARTZ_BLOCK("chiseled_quartz_block", 232, 227, 218, 1.07),
    //CHISELED_POLISHED_BLACKSTONE("chiseled_polished_blackstone", 54, 49, 57, 1.16),
    CHISELED_NETHER_BRICKS("chiseled_nether_bricks", 48, 24, 28, 1.16),
    CHISELED_DEEPSLATE("chiseled_deepslate", 55, 55, 56, 1.17),
    //CHISELED_BOOKSHELF("chiseled_bookshelf", 177, 144, 88, 1.20),
    CHERRY_PLANKS("cherry_planks", 227, 179, 173, 1.20),
    //CHERRY_LOG("cherry_log", 195, 149, 144, 1.20),
    CHERRY_LEAVES("cherry_leaves", 230, 173, 195, 1.20),
    CALCITE("calcite", 224, 225, 221, 1.17),
    BUBBLE_CORAL_BLOCK("bubble_coral_block", 166, 27, 163, 1.13),
    BROWN_WOOL("brown_wool", 114, 72, 41, 1.07),
    BROWN_TERRACOTTA("brown_terracotta", 77, 51, 36, 1.07),
    BROWN_MUSHROOM_BLOCK("brown_mushroom_block", 149, 112, 81, 1.07),
    BROWN_GLAZED_TERRACOTTA("brown_glazed_terracotta", 125, 106, 83, 1.12),
    BROWN_CONCRETE_POWDER("brown_concrete_powder", 126, 85, 54, 1.12),
    BROWN_CONCRETE("brown_concrete", 96, 60, 32, 1.12),
    BRICKS("bricks", 151, 97, 83, 1.07),
    BRAIN_CORAL_BLOCK("brain_coral_block", 208, 92, 160, 1.13),
    //BOOKSHELF("bookshelf", 115, 93, 58, 1.07),
    BONE_BLOCK("bone_block", 208, 204, 177, 1.10),
    BLUE_WOOL("blue_wool", 53, 57, 157, 1.07),
    BLUE_TERRACOTTA("blue_terracotta", 74, 60, 91, 1.07),
    BLUE_ICE("blue_ice", 116, 168, 253, 1.13),
    BLUE_GLAZED_TERRACOTTA("blue_glazed_terracotta", 48, 68, 144, 1.12),
    BLUE_CONCRETE_POWDER("blue_concrete_powder", 70, 73, 167, 1.12),
    BLUE_CONCRETE("blue_concrete", 45, 47, 143, 1.12),
    BLAST_FURNACE("blast_furnace", 79, 79, 79, 1.14),
    BLACK_WOOL("black_wool", 21, 21, 26, 1.07),
    BLACK_TERRACOTTA("black_terracotta", 37, 23, 17, 1.07),
    BLACK_GLAZED_TERRACOTTA("black_glazed_terracotta", 69, 30, 32, 1.12),
    BLACK_CONCRETE_POWDER("black_concrete_powder", 25, 27, 32, 1.12),
    BLACK_CONCRETE("black_concrete", 8, 10, 15, 1.12),
    BLACKSTONE("blackstone", 42, 35, 41, 1.16),
    BIRCH_PLANKS("birch_planks", 193, 175, 121, 1.07),
    //BIRCH_LOG("birch_log", 194, 179, 132, 1.07),
    BEE_NEST("bee_nest", 197, 150, 77, 1.15),
    BEEHIVE("beehive", 158, 127, 76, 1.15),
    BEDROCK("bedrock", 85, 85, 85, 1.07),
    BASALT("basalt", 80, 81, 86, 1.16),
    BARREL("barrel", 115, 85, 49, 1.14),
    BAMBOO_PLANKS("bamboo_planks", 194, 173, 81, 1.20),
    BAMBOO_MOSAIC("bamboo_mosaic", 190, 170, 78, 1.20),
    BAMBOO_BLOCK("bamboo_block", 127, 144, 58, 1.20),
    AZALEA_LEAVES("azalea_leaves", 90, 115, 44, 1.17),
    ANDESITE("andesite", 136, 136, 137, 1.08),
    AMETHYST_BLOCK("amethyst_block", 134, 98, 191, 1.17),
    //BUDDING_AMETHISTE
    ACACIA_PLANKS("acacia_planks", 168, 90, 50, 1.07),
    //ACACIA_LOG("acacia_log", 103, 97, 87, 1.07),


    SPRUCE_WOOD("spruce_wood", 59, 38, 17, 1.07),
    OAK_WOOD("oak_wood", 109, 85, 51, 1.07),
    MANGROVE_WOOD("mangrove_wood", 84, 67, 41, 1.19),
    JUNGLE_WOOD("jungle_wood", 85, 68, 25, 1.07),
    DARK_OAK_WOOD("dark_oak_wood", 60, 47, 26, 1.07),
    CHERRY_WOOD("cherry_wood", 55, 33, 44, 1.20),
    BIRCH_WOOD("birch_wood", 219, 217, 213, 1.07),
    ACACIA_WOOD("acacia_wood", 103, 97, 87, 1.07),
    ANCIENT_DEBRIS("ancient_debris",96, 64, 56, 1.16),


    STRIPPED_SPRUCE_WOOD("stripped_spruce_wood", 116, 90, 53, 1.07),
    STRIPPED_OAK_WOOD("stripped_oak_wood", 179, 145, 87, 1.07),
    STRIPPED_MANGROVE_WOOD("stripped_mangrove_wood", 120, 55, 48, 1.19),
    STRIPPED_JUNGLE_WOOD("stripped_jungle_wood", 172, 133, 85, 1.07),
    STRIPPED_DARK_OAK_WOOD("stripped_dark_oak_wood", 73, 57, 36, 1.07),
    STRIPPED_CHERRY_WOOD("stripped_cherry_wood", 216, 146, 150, 1.20),
    STRIPPED_BIRCH_WOOD("stripped_birch_wood", 198, 177, 119, 1.07),
    STRIPPED_ACACIA_WOOD("stripped_acacia_wood", 176, 93, 60, 1.07);

    // Honey block
    // Slime block


    final String name;
    final Integer r;
    final Integer g;
    final Integer b;
    final double blockVersion;

    BlocksDataColor(String name, Integer r, Integer g, Integer b, double blockVersion) {
        this.name = name;
        this.r = r;
        this.g = g;
        this.b = b;
        this.blockVersion = blockVersion;
    }

    public String getName() {
        return this.name;
    }

    public Integer getR() {
        return this.r;
    }

    public Integer getG() {
        return this.g;
    }

    public Integer getB() {
        return this.b;
    }

    public double getBlockVersion() {
        return blockVersion;
    }

    public static Stream<BlocksDataColor> getStreamArray() {
        return Arrays.stream(BlocksDataColor.values()).filter(blocksDataColor -> blocksDataColor.getBlockVersion() <= Double.parseDouble(Main.getBukkitVersion().substring(0, 4)));
    }
}