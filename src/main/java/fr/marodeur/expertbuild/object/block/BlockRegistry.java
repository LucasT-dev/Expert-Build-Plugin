package fr.marodeur.expertbuild.object.block;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.enums.BlockCategoryEnum;
import org.bukkit.Material;

import java.util.*;

public class BlockRegistry {

    private final Map<Material, DataBlockObject> dataBlockHashMap = new HashMap<>();

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
        this.registerBlock("OAK_HANGING_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.OAK, BlockCategoryEnum.HANGING_SIGN)));
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
        this.registerBlock("SPRUCE_HANGING_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.SPRUCE, BlockCategoryEnum.HANGING_SIGN)));
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
        this.registerBlock("BIRCH_HANGING_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BIRCH, BlockCategoryEnum.HANGING_SIGN)));
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
        this.registerBlock("JUNGLE_HANGING_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.JUNGLE, BlockCategoryEnum.HANGING_SIGN)));
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
        this.registerBlock("ACACIA_HANGING_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.ACACIA, BlockCategoryEnum.HANGING_SIGN)));
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
        this.registerBlock("DARK_OAK_HANGING_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock(Material.DARK_OAK_PRESSURE_PLATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock(Material.DARK_OAK_BUTTON)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.BUTTON)));
        this.registerBlock(Material.DARK_OAK_LEAVES)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.LEAVES)));
        this.registerBlock(Material.DARK_OAK_SAPLING)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.DARK_OAK, BlockCategoryEnum.SAPLING)));


        // MANGROVE
        this.registerBlock("MANGROVE_SLAB", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.SLAB)));
        this.registerBlock("MANGROVE_STAIRS", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.STAIRS)));
        this.registerBlock("MANGROVE_FENCE", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.FENCE)));
        this.registerBlock("MANGROVE_FENCE_GATE", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock("MANGROVE_DOOR", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.DOOR)));
        this.registerBlock("MANGROVE_TRAPDOOR", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock("MANGROVE_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.SIGN)));
        this.registerBlock("MANGROVE_HANGING_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("MANGROVE_PRESSURE_PLATE", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock("MANGROVE_BUTTON", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.BUTTON)));
        this.registerBlock("MANGROVE_LEAVES", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.LEAVES)));
        this.registerBlock("MANGROVE_PROPAGULE", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.MANGROVE, BlockCategoryEnum.SAPLING)));


        // CHERRY
        this.registerBlock("CHERRY_SLAB", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.SLAB)));
        this.registerBlock("CHERRY_STAIRS", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.STAIRS)));
        this.registerBlock("CHERRY_FENCE", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.FENCE)));
        this.registerBlock("CHERRY_FENCE_GATE", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock("CHERRY_DOOR", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.DOOR)));
        this.registerBlock("CHERRY_TRAPDOOR", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock("CHERRY_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.SIGN)));
        this.registerBlock("CHERRY_HANGING_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("CHERRY_PRESSURE_PLATE", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock("CHERRY_BUTTON", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.BUTTON)));
        this.registerBlock("CHERRY_LEAVES", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.LEAVES)));
        this.registerBlock("CHERRY_SAPLING", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CHERRY, BlockCategoryEnum.SAPLING)));


        // BAMBOO
        this.registerBlock("BAMBOO_SLAB", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.SLAB)));
        this.registerBlock("BAMBOO_STAIRS", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.STAIRS)));
        this.registerBlock("BAMBOO_FENCE", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.FENCE)));
        this.registerBlock("BAMBOO_FENCE_GATE", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.FENCE_GATE)));
        this.registerBlock("BAMBOO_DOOR", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.DOOR)));
        this.registerBlock("BAMBOO_TRAPDOOR", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.TRAPDOOR)));
        this.registerBlock("BAMBOO_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.SIGN)));
        this.registerBlock("BAMBOO_HANGING_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock("BAMBOO_PRESSURE_PLATE", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock("BAMBOO_BUTTON", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.BAMBOO, BlockCategoryEnum.BUTTON)));
        this.registerBlock("BAMBOO_SAPLING", "18.1")
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
        this.registerBlock("CRIMSON_HANGING_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.CRIMSON, BlockCategoryEnum.HANGING_SIGN)));
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
        this.registerBlock("WARPED_HANGING_SIGN", "18.1")
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.HANGING_SIGN)));
        this.registerBlock(Material.WARPED_PRESSURE_PLATE)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.PRESSURE_PLATE)));
        this.registerBlock(Material.WARPED_BUTTON)
                .addCategories(new CategoryBlockObject(EnumSet.of(BlockCategoryEnum.WARPED, BlockCategoryEnum.BUTTON)));


    }

    public BlockRegistry(String serverVersion) {
        System.out.println("BLOCK REGISTRY");
        String[] version = serverVersion.split("\\.");
//        System.out.println("version[0] = " + version[0]);
//        System.out.println("version[1] = " + version[1]);
//        this.majorVersion = Integer.parseInt(version[0]);
//        this.minorVersion = Integer.parseInt(version[1]);
    }



    public DataBlockObject registerBlock(String stringMaterial, String version) {

        String[] v = version.split("\\.");

        if (Integer.parseInt(v[0]) <= this.majorVersion && Integer.parseInt(v[1]) <= this.minorVersion) {

            System.out.println(" register : " + stringMaterial);
            return registerBlock(Material.getMaterial(stringMaterial));

        } else {

            System.out.println(" invalid : " + stringMaterial + ", return air");
            return registerBlock(Material.AIR);

        }
    }

    public DataBlockObject registerBlock(Material material) {

        if (material == null) {
            System.out.println("Material null !! ");
            return getDataBlock(Material.AIR);
        }

        System.out.println(" register : " + material.name());

        dataBlockHashMap.putIfAbsent(material, new DataBlockObject(material));
        return getDataBlock(material);
    }

    public boolean containsBlock(Material material) {
        return dataBlockHashMap.containsKey(material);
    }

    public boolean materialExist(EnumSet<BlockCategoryEnum> enumSet) {

        return dataBlockHashMap.values().stream().anyMatch(dataBlockObject -> dataBlockObject.categoryBlockObject().categories().equals(enumSet));

    }

    public DataBlockObject getDataBlock(EnumSet<BlockCategoryEnum> targetCategories) {

        return dataBlockHashMap.values().stream()
                // Filter DataBlockObjects whose categories match the given EnumSet
                .filter(dataBlockObject -> dataBlockObject.categoryBlockObject().categories().equals(targetCategories))
                // Retrieve the first matching object, if any
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No DataBlockObject found with the specified categories"));
    }


    public DataBlockObject getDataBlock(Material material) {

        if (containsBlock(material)) return dataBlockHashMap.get(material);

        else {
            System.out.println(" material does not exist " + material);
            throw new RuntimeException("Error material does not exist");
        }
    }
}