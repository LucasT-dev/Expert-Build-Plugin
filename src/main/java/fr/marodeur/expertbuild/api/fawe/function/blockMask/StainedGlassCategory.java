package fr.marodeur.expertbuild.api.fawe.function.blockMask;

import com.sk89q.worldedit.extension.input.InputParseException;
import com.sk89q.worldedit.world.block.BlockCategories;
import com.sk89q.worldedit.world.block.BlockCategory;
import com.sk89q.worldedit.world.block.BlockStateHolder;
import com.sk89q.worldedit.world.block.BlockType;

public class StainedGlassCategory extends BlockCategory {

    public StainedGlassCategory(String id) {
        super(id);
    }

    @Override
    public <B extends BlockStateHolder<B>> boolean contains(B blockStateHolder) {
        return contains(blockStateHolder.getBlockType());
    }

    @Override
    public boolean contains(BlockType type) {

        BlockCategory testCategory = BlockCategories.get("minecraft:stained_glass");

        if (testCategory == null) {
            throw new InputParseException("Block category ##stained_glass not found!");
        }

        return testCategory.getAll().contains(type);
    }
}

