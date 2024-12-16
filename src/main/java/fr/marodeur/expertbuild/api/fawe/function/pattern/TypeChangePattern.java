package fr.marodeur.expertbuild.api.fawe.function.pattern;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.function.pattern.AbstractExtentPattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BaseBlock;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.enums.BlockCategoryEnum;

import org.bukkit.Material;

public class TypeChangePattern extends AbstractExtentPattern {

    private BlockCategoryEnum blockCat;

    public TypeChangePattern(Extent extent, String[] args) {
        super(extent);

        if (args.length >= 1 ) this.blockCat = BlockCategoryEnum.valueOf(args[0].toUpperCase());


    }


    @Override
    public BaseBlock applyBlock(BlockVector3 position) {

        Material oldMat = BukkitAdapter.adapt(position.getBlock(getExtent()).getBlockType());
        Material newMat;

        if (Main.getBlockData().containsBlock(oldMat)) {

            if (Main.getBlockData().getDataBlock(oldMat).categoryBlockObject().hasEquivalent(blockCat)) {

                newMat = Main.getBlockData().getDataBlock(oldMat).categoryBlockObject().getEquivalent(blockCat).getMaterial();

                final BaseBlock[] baseblock = {BukkitAdapter.asBlockType(newMat).getItemType().getBlockType().getDefaultState().toBaseBlock()};
                position.getBlock(getExtent()).toBaseBlock().getStates().forEach((property, object) -> {

                    baseblock[0] = baseblock[0].with(property.getKey(), object);

                });

                return baseblock[0];
            }
        }

        return position.getBlock(getExtent()).toBaseBlock();

    }
}