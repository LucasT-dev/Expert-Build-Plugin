package fr.marodeur.expertbuild.api.fawe.function.blockMask;

import com.sk89q.worldedit.extension.input.InputParseException;
import com.sk89q.worldedit.world.block.*;

public class StainedGlassPaneCategory extends BlockCategory {

    public StainedGlassPaneCategory(String id) {
        super(id);
    }

    @Override
    public <B extends BlockStateHolder<B>> boolean contains(B blockStateHolder) {
        return contains(blockStateHolder.getBlockType());
    }

    @Override
    public boolean contains(BlockType type) {

        BlockCategory testCategory = BlockCategories.get("minecraft:stained_glass_pane");

        if (testCategory == null) {
            throw new InputParseException("Block category ##stained_glass_pane not found!");
        }

        return testCategory.getAll().contains(type);
    }
}

