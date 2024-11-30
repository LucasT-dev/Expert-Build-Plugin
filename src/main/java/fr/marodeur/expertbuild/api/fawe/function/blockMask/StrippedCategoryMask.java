package fr.marodeur.expertbuild.api.fawe.function.blockMask;

import com.sk89q.worldedit.world.block.*;

import java.util.Arrays;
import java.util.List;

public class StrippedCategoryMask extends BlockCategory {
    private final List<BlockType> STRIPPED_BLOCK = Arrays.asList(
            BlockTypes.STRIPPED_OAK_WOOD, BlockTypes.STRIPPED_OAK_LOG,
            BlockTypes.STRIPPED_SPRUCE_WOOD, BlockTypes.STRIPPED_SPRUCE_LOG,
            BlockTypes.STRIPPED_BIRCH_WOOD, BlockTypes.STRIPPED_BIRCH_LOG,
            BlockTypes.STRIPPED_JUNGLE_WOOD, BlockTypes.STRIPPED_JUNGLE_LOG,
            BlockTypes.STRIPPED_ACACIA_WOOD, BlockTypes.STRIPPED_ACACIA_LOG,
            BlockTypes.STRIPPED_DARK_OAK_WOOD, BlockTypes.STRIPPED_DARK_OAK_LOG,
            BlockTypes.STRIPPED_MANGROVE_WOOD, BlockTypes.STRIPPED_MANGROVE_LOG,
            BlockTypes.STRIPPED_CHERRY_WOOD, BlockTypes.STRIPPED_CHERRY_LOG,
            BlockTypes.STRIPPED_BAMBOO_BLOCK
            );

    public StrippedCategoryMask(String id) {
        super(id);

        //FAWE start
        this.load();
        //FAWE end
    }

    @Override
    public boolean contains(BlockType object) {
        return STRIPPED_BLOCK.contains(object);
    }

    @Override
    public <B extends BlockStateHolder<B>> boolean contains(B blockStateHolder) {
        return contains(blockStateHolder.getBlockType());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

