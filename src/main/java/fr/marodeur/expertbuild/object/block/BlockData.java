package fr.marodeur.expertbuild.object.block;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.enums.BlockCategoryEnum;

import fr.marodeur.expertbuild.object.block.blockObjects.CategoryBlockObject;
import fr.marodeur.expertbuild.object.block.blockObjects.ColorBlockObject;
import org.bukkit.Material;

import java.util.*;

public class BlockData {

    private static final Map<Material, DataBlockManager> dataBlockHashMap = new HashMap<>();

    private final int majorVersion = Integer.parseInt(Main.getDataPlugin().getBukkitVersion().substring(2, 4));
    private final int minorVersion = Integer.parseInt(Main.getDataPlugin().getBukkitVersion().substring(5, 6));

    {

//        this.registerBlock(Material.AIR)
//                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.NONE)))
//                .addColor(new ColorBlockObject(1, 2, 3));

        this.registerBlock(Material.STONE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.STONE)))
                .addColor(new ColorBlockObject(1, 2, 5));

        this.registerBlock(Material.ANDESITE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ANDESITE)))
                .addColor(new ColorBlockObject(1, 2, 5));


        // Pour les test


        // STONE
        this.registerBlock(Material.STONE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.STONE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.STONE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.STONE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.STONE_BUTTON)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.STONE, BlockCategoryEnum.BUTTON)));


        // COBBLESTONE
        this.registerBlock(Material.COBBLESTONE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.COBBLESTONE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.COBBLESTONE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.COBBLESTONE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.COBBLESTONE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.COBBLESTONE, BlockCategoryEnum.WALL)));


        // MOSSY_COBBLESTONE
        this.registerBlock(Material.MOSSY_COBBLESTONE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MOSSY_COBBLESTONE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.MOSSY_COBBLESTONE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MOSSY_COBBLESTONE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.MOSSY_COBBLESTONE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MOSSY_COBBLESTONE, BlockCategoryEnum.WALL)));


        // ANDESITE
        this.registerBlock(Material.ANDESITE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ANDESITE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.ANDESITE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ANDESITE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.ANDESITE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ANDESITE, BlockCategoryEnum.WALL)));


        // DIORITE
        this.registerBlock(Material.DIORITE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DIORITE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.DIORITE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DIORITE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.DIORITE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DIORITE, BlockCategoryEnum.WALL)));


        // GRANITE
        this.registerBlock(Material.GRANITE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.GRANITE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.GRANITE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.GRANITE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.GRANITE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.GRANITE, BlockCategoryEnum.WALL)));


        // WOOD

        // OAK
        this.registerBlock(Material.OAK_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.OAK_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.OAK_FENCE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.FENCE)));
        this.registerBlock(Material.OAK_FENCE_GATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock(Material.OAK_DOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.DOOR)));
        this.registerBlock(Material.OAK_TRAPDOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock(Material.OAK_SIGN)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.SIGN)));
        this.registerBlock("OAK_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("OAK_WALL_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.WALL_HANGING_SIGN)));
        this.registerBlock(Material.OAK_PRESSURE_PLATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock(Material.OAK_BUTTON)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.BUTTON)));
        this.registerBlock(Material.OAK_LEAVES)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.LEAVES)));
        this.registerBlock(Material.OAK_SAPLING)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.SAPLING)));


        // SPRUCE
        this.registerBlock(Material.SPRUCE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.SPRUCE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.SPRUCE_FENCE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.FENCE)));
        this.registerBlock(Material.SPRUCE_FENCE_GATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock(Material.SPRUCE_DOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.DOOR)));
        this.registerBlock(Material.SPRUCE_TRAPDOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock(Material.SPRUCE_SIGN)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.SIGN)));
        this.registerBlock("SPRUCE_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("SPRUCE_WALL_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.WALL_HANGING_SIGN)));
        this.registerBlock(Material.SPRUCE_PRESSURE_PLATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock(Material.SPRUCE_BUTTON)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.BUTTON)));
        this.registerBlock(Material.SPRUCE_LEAVES)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.LEAVES)));
        this.registerBlock(Material.SPRUCE_SAPLING)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.SAPLING)));


        // BIRCH
        this.registerBlock(Material.BIRCH_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.BIRCH_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.BIRCH_FENCE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.FENCE)));
        this.registerBlock(Material.BIRCH_FENCE_GATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock(Material.BIRCH_DOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.DOOR)));
        this.registerBlock(Material.BIRCH_TRAPDOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock(Material.BIRCH_SIGN)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.SIGN)));
        this.registerBlock("BIRCH_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("BIRCH_WALL_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.WALL_HANGING_SIGN)));
        this.registerBlock(Material.BIRCH_PRESSURE_PLATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock(Material.BIRCH_BUTTON)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.BUTTON)));
        this.registerBlock(Material.BIRCH_LEAVES)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.LEAVES)));
        this.registerBlock(Material.BIRCH_SAPLING)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.SAPLING)));


        // JUNGLE
        this.registerBlock(Material.JUNGLE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.JUNGLE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.JUNGLE_FENCE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.FENCE)));
        this.registerBlock(Material.JUNGLE_FENCE_GATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock(Material.JUNGLE_DOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.DOOR)));
        this.registerBlock(Material.JUNGLE_TRAPDOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock(Material.JUNGLE_SIGN)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.SIGN)));
        this.registerBlock("JUNGLE_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("JUNGLE_WALL_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.WALL_HANGING_SIGN)));
        this.registerBlock(Material.JUNGLE_PRESSURE_PLATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock(Material.JUNGLE_BUTTON)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.BUTTON)));
        this.registerBlock(Material.JUNGLE_LEAVES)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.LEAVES)));
        this.registerBlock(Material.JUNGLE_SAPLING)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.SAPLING)));


        // ACACIA
        this.registerBlock(Material.ACACIA_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.ACACIA_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.ACACIA_FENCE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.FENCE)));
        this.registerBlock(Material.ACACIA_FENCE_GATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock(Material.ACACIA_DOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.DOOR)));
        this.registerBlock(Material.ACACIA_TRAPDOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock(Material.ACACIA_SIGN)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.SIGN)));
        this.registerBlock("ACACIA_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("ACACIA_WALL_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.WALL_HANGING_SIGN)));
        this.registerBlock(Material.ACACIA_PRESSURE_PLATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock(Material.ACACIA_BUTTON)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.BUTTON)));
        this.registerBlock(Material.ACACIA_LEAVES)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.LEAVES)));
        this.registerBlock(Material.ACACIA_SAPLING)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.SAPLING)));


        // DARK_OAK
        this.registerBlock(Material.DARK_OAK_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.DARK_OAK_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.DARK_OAK_FENCE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.FENCE)));
        this.registerBlock(Material.DARK_OAK_FENCE_GATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock(Material.DARK_OAK_DOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.DOOR)));
        this.registerBlock(Material.DARK_OAK_TRAPDOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock(Material.DARK_OAK_SIGN)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.SIGN)));
        this.registerBlock("DARK_OAK_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("DARK_OAK_WALL_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.WALL_HANGING_SIGN)));
        this.registerBlock(Material.DARK_OAK_PRESSURE_PLATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock(Material.DARK_OAK_BUTTON)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.BUTTON)));
        this.registerBlock(Material.DARK_OAK_LEAVES)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.LEAVES)));
        this.registerBlock(Material.DARK_OAK_SAPLING)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.SAPLING)));


        // MANGROVE
        this.registerBlock("MANGROVE_SLAB", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.SLAB)));
        this.registerBlock("MANGROVE_STAIRS", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.STAIRS)));
        this.registerBlock("MANGROVE_FENCE", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.FENCE)));
        this.registerBlock("MANGROVE_FENCE_GATE", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock("MANGROVE_DOOR", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.DOOR)));
        this.registerBlock("MANGROVE_TRAPDOOR", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock("MANGROVE_SIGN", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.SIGN)));
        this.registerBlock("MANGROVE_HANGING_SIGN", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("MANGROVE_WALL_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.WALL_HANGING_SIGN)));
        this.registerBlock("MANGROVE_PRESSURE_PLATE", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock("MANGROVE_BUTTON", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.BUTTON)));
        this.registerBlock("MANGROVE_LEAVES", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.LEAVES)));
        this.registerBlock("MANGROVE_PROPAGULE", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.SAPLING)));


        // CHERRY
        this.registerBlock("CHERRY_SLAB", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.SLAB)));
        this.registerBlock("CHERRY_STAIRS", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.STAIRS)));
        this.registerBlock("CHERRY_FENCE", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.FENCE)));
        this.registerBlock("CHERRY_FENCE_GATE", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock("CHERRY_DOOR", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.DOOR)));
        this.registerBlock("CHERRY_TRAPDOOR", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock("CHERRY_SIGN", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.SIGN)));
        this.registerBlock("CHERRY_HANGING_SIGN", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("CHERRY_WALL_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.WALL_HANGING_SIGN)));
        this.registerBlock("CHERRY_PRESSURE_PLATE", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock("CHERRY_BUTTON", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.BUTTON)));
        this.registerBlock("CHERRY_LEAVES", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.LEAVES)));
        this.registerBlock("CHERRY_SAPLING", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.SAPLING)));


        // BAMBOO
        this.registerBlock("BAMBOO_SLAB", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.SLAB)));
        this.registerBlock("BAMBOO_STAIRS", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.STAIRS)));
        this.registerBlock("BAMBOO_FENCE", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.FENCE)));
        this.registerBlock("BAMBOO_FENCE_GATE", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock("BAMBOO_DOOR", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.DOOR)));
        this.registerBlock("BAMBOO_TRAPDOOR", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock("BAMBOO_SIGN", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.SIGN)));
        this.registerBlock("BAMBOO_HANGING_SIGN", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("BAMBOO_WALL_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.WALL_HANGING_SIGN)));
        this.registerBlock("BAMBOO_PRESSURE_PLATE", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock("BAMBOO_BUTTON", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.BUTTON)));
        this.registerBlock("BAMBOO_SAPLING", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.SAPLING)));


        // CRIMSON
        this.registerBlock(Material.CRIMSON_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.CRIMSON_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.CRIMSON_FENCE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.FENCE)));
        this.registerBlock(Material.CRIMSON_FENCE_GATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock(Material.CRIMSON_DOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.DOOR)));
        this.registerBlock(Material.CRIMSON_TRAPDOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock(Material.CRIMSON_SIGN)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.SIGN)));
        this.registerBlock("CRIMSON_HANGING_SIGN", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("CRIMSON_WALL_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.WALL_HANGING_SIGN)));
        this.registerBlock(Material.CRIMSON_PRESSURE_PLATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock(Material.CRIMSON_BUTTON)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.BUTTON)));


        // WARPED
        this.registerBlock(Material.WARPED_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.WARPED_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.WARPED_FENCE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.FENCE)));
        this.registerBlock(Material.WARPED_FENCE_GATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock(Material.WARPED_DOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.DOOR)));
        this.registerBlock(Material.WARPED_TRAPDOOR)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock(Material.WARPED_SIGN)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.SIGN)));
        this.registerBlock("WARPED_HANGING_SIGN", "1.18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("WARPED_WALL_HANGING_SIGN", "1.20.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.WALL_HANGING_SIGN)));
        this.registerBlock(Material.WARPED_PRESSURE_PLATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock(Material.WARPED_BUTTON)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.BUTTON)));


        // COBBLED_DEEPSLATE
        this.registerBlock(Material.COBBLED_DEEPSLATE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.COBBLED_DEEPSLATE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.COBBLED_DEEPSLATE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.COBBLED_DEEPSLATE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.COBBLED_DEEPSLATE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.COBBLED_DEEPSLATE, BlockCategoryEnum.WALL)));


        // POLISHED_DEEPSLATE
        this.registerBlock(Material.POLISHED_DEEPSLATE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_DEEPSLATE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.POLISHED_DEEPSLATE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_DEEPSLATE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.POLISHED_DEEPSLATE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_DEEPSLATE, BlockCategoryEnum.WALL)));


        // DEEPSLATE_BRICK
        this.registerBlock(Material.DEEPSLATE_BRICK_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DEEPSLATE_BRICK, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.DEEPSLATE_BRICK_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DEEPSLATE_BRICK, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.DEEPSLATE_BRICK_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DEEPSLATE_BRICK, BlockCategoryEnum.WALL)));


        // DEEPSLATE_BRICK
        this.registerBlock(Material.DEEPSLATE_TILE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DEEPSLATE_TILE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.DEEPSLATE_TILE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DEEPSLATE_TILE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.DEEPSLATE_TILE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DEEPSLATE_TILE, BlockCategoryEnum.WALL)));


        // TUFF
        this.registerBlock("TUFF_SLAB", "1.20.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.TUFF, BlockCategoryEnum.SLAB)));
        this.registerBlock("TUFF_STAIRS", "1.20.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.TUFF, BlockCategoryEnum.STAIRS)));
        this.registerBlock("TUFF_WALL", "1.20.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.TUFF, BlockCategoryEnum.WALL)));


        // POLISHED_TUFF
        this.registerBlock("POLISHED_TUFF_SLAB", "1.20.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_TUFF, BlockCategoryEnum.SLAB)));
        this.registerBlock("POLISHED_TUFF_STAIRS", "1.20.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_TUFF, BlockCategoryEnum.STAIRS)));
        this.registerBlock("POLISHED_TUFF_WALL", "1.20.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_TUFF, BlockCategoryEnum.WALL)));


        // POLISHED_TUFF
        this.registerBlock("TUFF_BRICK_SLAB", "1.20.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.TUFF_BRICK, BlockCategoryEnum.SLAB)));
        this.registerBlock("TUFF_BRICK_STAIRS", "1.20.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.TUFF_BRICK, BlockCategoryEnum.STAIRS)));
        this.registerBlock("TUFF_BRICK_WALL", "1.20.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.TUFF_BRICK, BlockCategoryEnum.WALL)));


        // POLISHED_TUFF
        this.registerBlock(Material.BRICK_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BRICK, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.BRICK_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BRICK, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.BRICK_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BRICK, BlockCategoryEnum.WALL)));


        // MUD
        this.registerBlock("MUD_BRICK_SLAB", "1.20.4")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MUD, BlockCategoryEnum.SLAB)));
        this.registerBlock("MUD_BRICK_STAIRS", "1.20.4")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MUD, BlockCategoryEnum.STAIRS)));
        this.registerBlock("MUD_BRICK_WALL", "1.20.4")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MUD, BlockCategoryEnum.WALL)));


        // SANDSTONE
        this.registerBlock(Material.SANDSTONE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SANDSTONE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.SANDSTONE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SANDSTONE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.SANDSTONE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SANDSTONE, BlockCategoryEnum.WALL)));


        // SMOOTH_SANDSTONE
        this.registerBlock(Material.SMOOTH_SANDSTONE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SMOOTH_SANDSTONE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.SMOOTH_SANDSTONE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SMOOTH_SANDSTONE, BlockCategoryEnum.STAIRS)));


        // RED_SANDSTONE
        this.registerBlock(Material.RED_SANDSTONE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.RED_SANDSTONE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.RED_SANDSTONE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.RED_SANDSTONE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.RED_SANDSTONE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.RED_SANDSTONE, BlockCategoryEnum.WALL)));


        // SMOOTH_SANDSTONE
        this.registerBlock(Material.SMOOTH_RED_SANDSTONE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SMOOTH_RED_SANDSTONE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.SMOOTH_RED_SANDSTONE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SMOOTH_RED_SANDSTONE, BlockCategoryEnum.STAIRS)));


        // PRISMARINE
        this.registerBlock(Material.PRISMARINE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.PRISMARINE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.PRISMARINE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.PRISMARINE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.PRISMARINE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.PRISMARINE, BlockCategoryEnum.WALL)));


        // PRISMARINE_BRICK
        this.registerBlock(Material.PRISMARINE_BRICK_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.PRISMARINE_BRICK, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.PRISMARINE_BRICK_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.PRISMARINE_BRICK, BlockCategoryEnum.STAIRS)));

        // SANDSTONE
        this.registerBlock(Material.DARK_PRISMARINE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_PRISMARINE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.DARK_PRISMARINE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_PRISMARINE, BlockCategoryEnum.STAIRS)));


        // NETHER_BRICK
        this.registerBlock(Material.NETHER_BRICK_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.NETHER_BRICK, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.NETHER_BRICK_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.NETHER_BRICK, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.NETHER_BRICK_FENCE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.NETHER_BRICK, BlockCategoryEnum.FENCE)));
        this.registerBlock(Material.NETHER_BRICK_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.NETHER_BRICK, BlockCategoryEnum.WALL)));


        // RED_NETHER_BRICK
        this.registerBlock(Material.RED_NETHER_BRICK_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.RED_NETHER_BRICK, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.RED_NETHER_BRICK_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.RED_NETHER_BRICK, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.RED_NETHER_BRICK_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.RED_NETHER_BRICK, BlockCategoryEnum.WALL)));


        // BLACKSTONE
        this.registerBlock(Material.BLACKSTONE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BLACKSTONE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.BLACKSTONE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BLACKSTONE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.BLACKSTONE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BLACKSTONE, BlockCategoryEnum.WALL)));


        // POLISHED BLACKSTONE
        this.registerBlock(Material.POLISHED_BLACKSTONE_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_BLACKSTONE, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.POLISHED_BLACKSTONE_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_BLACKSTONE, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.POLISHED_BLACKSTONE_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_BLACKSTONE, BlockCategoryEnum.WALL)));


        // POLISHED BLACKSTONE BRICK
        this.registerBlock(Material.POLISHED_BLACKSTONE_BRICK_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_BLACKSTONE_BRICK, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.POLISHED_BLACKSTONE_BRICK_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_BLACKSTONE_BRICK, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.POLISHED_BLACKSTONE_BRICK_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.POLISHED_BLACKSTONE_BRICK, BlockCategoryEnum.WALL)));


        // END STONE
        this.registerBlock(Material.END_STONE_BRICK_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.END_STONE_BRICK, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.END_STONE_BRICK_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.END_STONE_BRICK, BlockCategoryEnum.STAIRS)));
        this.registerBlock(Material.END_STONE_BRICK_WALL)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.END_STONE_BRICK, BlockCategoryEnum.WALL)));


        // PURPUR
        this.registerBlock(Material.PURPUR_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.PUPUR, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.PURPUR_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.PUPUR, BlockCategoryEnum.STAIRS)));


        // PURPUR
        this.registerBlock(Material.QUARTZ_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.QUARTZ, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.QUARTZ_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.QUARTZ, BlockCategoryEnum.STAIRS)));


        // PURPUR
        this.registerBlock(Material.SMOOTH_QUARTZ_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SMOOTH_QUARTZ, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.SMOOTH_QUARTZ_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SMOOTH_QUARTZ, BlockCategoryEnum.STAIRS)));

        // CUT COPPER
        this.registerBlock(Material.CUT_COPPER_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CUT_COPPER, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.CUT_COPPER_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CUT_COPPER, BlockCategoryEnum.STAIRS)));
        this.registerBlock("COPPER_DOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CUT_COPPER, BlockCategoryEnum.DOOR)));
        this.registerBlock("COPPER_TRAPDOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CUT_COPPER, BlockCategoryEnum.TRAPDOOR)));


        // EXPOSED CUT COPPER
        this.registerBlock(Material.EXPOSED_CUT_COPPER_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.EXPOSED_CUT_COPPER, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.EXPOSED_CUT_COPPER_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.EXPOSED_CUT_COPPER, BlockCategoryEnum.STAIRS)));
        this.registerBlock("EXPOSED_COPPER_DOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.EXPOSED_CUT_COPPER, BlockCategoryEnum.DOOR)));
        this.registerBlock("EXPOSED_COPPER_TRAPDOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.EXPOSED_CUT_COPPER, BlockCategoryEnum.TRAPDOOR)));

        // WEATHERED CUT COPPER
        this.registerBlock(Material.WEATHERED_CUT_COPPER_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WEATHERED_CUT_COPPER, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.WEATHERED_CUT_COPPER_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WEATHERED_CUT_COPPER, BlockCategoryEnum.STAIRS)));
        this.registerBlock("WEATHERED_COPPER_DOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WEATHERED_CUT_COPPER, BlockCategoryEnum.DOOR)));
        this.registerBlock("WEATHERED_COPPER_TRAPDOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WEATHERED_CUT_COPPER, BlockCategoryEnum.TRAPDOOR)));

        // OXIDIZED CUT COPPER
        this.registerBlock(Material.OXIDIZED_CUT_COPPER_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OXIDIZED_CUT_COPPER, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.OXIDIZED_CUT_COPPER_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OXIDIZED_CUT_COPPER, BlockCategoryEnum.STAIRS)));
        this.registerBlock("OXIDIZED_COPPER_DOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OXIDIZED_CUT_COPPER, BlockCategoryEnum.DOOR)));
        this.registerBlock("OXIDIZED_COPPER_TRAPDOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OXIDIZED_CUT_COPPER, BlockCategoryEnum.TRAPDOOR)));



        // WAXED CUT COPPER
        this.registerBlock(Material.WAXED_CUT_COPPER_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_CUT_COPPER, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.WAXED_CUT_COPPER_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_CUT_COPPER, BlockCategoryEnum.STAIRS)));
        this.registerBlock("WAXED_COPPER_DOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_CUT_COPPER, BlockCategoryEnum.DOOR)));
        this.registerBlock("WAXED_COPPER_TRAPDOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_CUT_COPPER, BlockCategoryEnum.TRAPDOOR)));


        // EXPOSED CUT COPPER
        this.registerBlock(Material.WAXED_EXPOSED_CUT_COPPER_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_EXPOSED_CUT_COPPER, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_EXPOSED_CUT_COPPER, BlockCategoryEnum.STAIRS)));
        this.registerBlock("WAXED_EXPOSED_COPPER_DOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_EXPOSED_CUT_COPPER, BlockCategoryEnum.DOOR)));
        this.registerBlock("WAXED_EXPOSED_COPPER_TRAPDOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_EXPOSED_CUT_COPPER, BlockCategoryEnum.TRAPDOOR)));

        // WEATHERED CUT COPPER
        this.registerBlock(Material.WAXED_WEATHERED_CUT_COPPER_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_WEATHERED_CUT_COPPER, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_WEATHERED_CUT_COPPER, BlockCategoryEnum.STAIRS)));
        this.registerBlock("WAXED_WEATHERED_COPPER_DOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_WEATHERED_CUT_COPPER, BlockCategoryEnum.DOOR)));
        this.registerBlock("WAXED_WEATHERED_COPPER_TRAPDOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_WEATHERED_CUT_COPPER, BlockCategoryEnum.TRAPDOOR)));

        // OXIDIZED CUT COPPER
        this.registerBlock(Material.WAXED_OXIDIZED_CUT_COPPER_SLAB)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_OXIDIZED_CUT_COPPER, BlockCategoryEnum.SLAB)));
        this.registerBlock(Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_OXIDIZED_CUT_COPPER, BlockCategoryEnum.STAIRS)));
        this.registerBlock("WAXED_OXIDIZED_COPPER_DOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_OXIDIZED_CUT_COPPER, BlockCategoryEnum.DOOR)));
        this.registerBlock("WAXED_OXIDIZED_COPPER_TRAPDOOR", "1.21.0")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WAXED_OXIDIZED_CUT_COPPER, BlockCategoryEnum.TRAPDOOR)));

    }

    public BlockData() {

    }


    private DataBlockManager registerBlock(String stringMaterial, String version) {

        String[] v = version.split("\\.");

        if (Integer.parseInt(v[1]) <= this.majorVersion && Integer.parseInt(v[2]) <= this.minorVersion) {

            return registerBlock(Material.getMaterial(stringMaterial));

        } else {

            return registerBlock(Material.AIR);

        }
    }

    private DataBlockManager registerBlock(Material material) {

        if (material == null) {
            return getDataBlock(Material.AIR);
        }

        dataBlockHashMap.putIfAbsent(material, new DataBlockManager(material));
        return getDataBlock(material);
    }

    public static boolean containsBlock(Material material) {
        return dataBlockHashMap.containsKey(material);
    }

    public static boolean containsBlock(EnumSet<BlockCategoryEnum> enumSet) {
        return dataBlockHashMap.values()
                .stream()
                .anyMatch(dataBlockObject -> dataBlockObject.categoryBlockObject().categories().equals(enumSet));
    }

    public static DataBlockManager getDataBlock(EnumSet<BlockCategoryEnum> targetCategories) {

        return dataBlockHashMap.values().stream()
                // Filter DataBlockObjects whose categories match the given EnumSet
                .filter(dataBlockObject -> dataBlockObject.categoryBlockObject().categories().equals(targetCategories))
                // Retrieve the first matching object, if any
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No DataBlockObject found with the specified categories"));
    }


    public static DataBlockManager getDataBlock(Material material) {

        if (containsBlock(material))

            return dataBlockHashMap.get(material);

        else {
            System.out.println(" material does not exist " + material);
            throw new RuntimeException("Error material does not exist");
        }
    }
}