package fr.marodeur.expertbuild.enums;

import fr.marodeur.expertbuild.object.block.category.BlockCategoryType;
import fr.marodeur.expertbuild.object.block.category.ColorCategory;
import fr.marodeur.expertbuild.object.block.category.ShapeCategory;
import fr.marodeur.expertbuild.object.block.category.TypeCategory;

import java.util.Arrays;
import java.util.stream.Stream;

public enum BlockCategoryEnum {

    NONE(null),


    BUTTON(new ShapeCategory()),
    DOOR(new ShapeCategory()),
    FENCE(new ShapeCategory()),
    FENCE_GATE(new ShapeCategory()),
    HANGING_SIGN(new ShapeCategory()),
    WALL_HANGING_SIGN(new ShapeCategory()),
    PRESSURE_PLATE(new ShapeCategory()),
    SIGN(new ShapeCategory()),
    SLAB(new ShapeCategory()),
    STAIRS(new ShapeCategory()),
    STRIPPED(new ShapeCategory()),
    TRAPDOOR(new ShapeCategory()),
    WALL(new ShapeCategory()),
    WOOD(new ShapeCategory()),
    LOG(new ShapeCategory()),
    LEAVES(new ShapeCategory()),
    SAPLING(new ShapeCategory()),




    // Type category

    // WOOD
    ACACIA(new TypeCategory()),
    BAMBOO(new TypeCategory()),
    BIRCH(new TypeCategory()),
    CHERRY(new TypeCategory()),
    CRIMSON(new TypeCategory()),
    DARK_OAK(new TypeCategory()),
    JUNGLE(new TypeCategory()),
    MANGROVE(new TypeCategory()),
    OAK(new TypeCategory()),
    PALE_OAK(new TypeCategory()),
    SPRUCE(new TypeCategory()),
    WARPED(new TypeCategory()),

    // STONE

    COBBLESTONE(new TypeCategory()),

    MOSSY_COBBLESTONE(new TypeCategory()),
    MOSSY_STONE_BRICK(new TypeCategory()),

    SMOOTH_STONE(new TypeCategory()),
    STONE(new TypeCategory()),
    STONE_BRICK(new TypeCategory()),


    ANDESITE(new TypeCategory()),
    DIORITE(new TypeCategory()),
    GRANITE(new TypeCategory()),


    COBBLED_DEEPSLATE(new TypeCategory()),
    POLISHED_DEEPSLATE(new TypeCategory()),
    DEEPSLATE_BRICK(new TypeCategory()),
    DEEPSLATE_TILE(new TypeCategory()),


    TUFF(new TypeCategory()),
    POLISHED_TUFF(new TypeCategory()),
    TUFF_BRICK(new TypeCategory()),


    BRICK(new TypeCategory()),

    MUD(new TypeCategory()),

    RESIN(new TypeCategory()),

    SANDSTONE(new TypeCategory()),
    SMOOTH_SANDSTONE(new TypeCategory()),
    RED_SANDSTONE(new TypeCategory()),
    SMOOTH_RED_SANDSTONE(new TypeCategory()),


    PRISMARINE(new TypeCategory()),
    PRISMARINE_BRICK(new TypeCategory()),
    DARK_PRISMARINE(new TypeCategory()),


    NETHER_BRICK(new TypeCategory()),
    RED_NETHER_BRICK(new TypeCategory()),


    BLACKSTONE(new TypeCategory()),
    POLISHED_BLACKSTONE(new TypeCategory()),
    POLISHED_BLACKSTONE_BRICK(new TypeCategory()),


    END_STONE_BRICK(new TypeCategory()),
    PUPUR(new TypeCategory()),


    QUARTZ(new TypeCategory()),
    SMOOTH_QUARTZ(new TypeCategory()),


    CUT_COPPER(new TypeCategory()),
    EXPOSED_CUT_COPPER(new TypeCategory()),
    WEATHERED_CUT_COPPER(new TypeCategory()),
    OXIDIZED_CUT_COPPER(new TypeCategory()),


    WAXED_CUT_COPPER(new TypeCategory()),
    WAXED_EXPOSED_CUT_COPPER(new TypeCategory()),
    WAXED_WEATHERED_CUT_COPPER(new TypeCategory()),
    WAXED_OXIDIZED_CUT_COPPER(new TypeCategory()),




    // Color category

    LIGHT(new ColorCategory()),
    LIGHT_GRAY(new ColorCategory()),
    GRAY(new ColorCategory()),
    BLACK(new ColorCategory()),
    BROWN(new ColorCategory()),
    RED(new ColorCategory()),
    ORANGE(new ColorCategory()),
    YELLOW(new ColorCategory()),
    LIME(new ColorCategory()),
    GREEN(new ColorCategory()),
    CYAN(new ColorCategory()),
    LIGHT_BLUE(new ColorCategory()),
    BLUE(new ColorCategory()),
    PURPLE(new ColorCategory()),
    MAGENTA(new ColorCategory()),
    PINK(new ColorCategory()),



    ;

    private final BlockCategoryType categoryType;

    // Constructor
    BlockCategoryEnum(BlockCategoryType categoryType) {
        this.categoryType = categoryType;
    }

    // Getter for the category type
    public BlockCategoryType getCategoryType() {
        return categoryType;
    }

    public String getName() {

        return this.name().toLowerCase();

    }

    @Override
    public String toString() {
        return "BlockCategoryEnum{" +
                "categoryType=" + categoryType.getType() +
                '}';
    }

    public static Stream<BlockCategoryEnum> getStreamArray() {
        return Arrays.stream(BlockCategoryEnum.values());
    }
}
