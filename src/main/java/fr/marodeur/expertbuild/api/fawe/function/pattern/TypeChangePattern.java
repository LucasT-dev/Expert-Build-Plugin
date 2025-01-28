package fr.marodeur.expertbuild.api.fawe.function.pattern;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.function.pattern.AbstractExtentPattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BaseBlock;

import fr.marodeur.expertbuild.enums.BlockCategoryEnum;
import fr.marodeur.expertbuild.object.block.BlockData;

import org.bukkit.Material;

public class TypeChangePattern extends AbstractExtentPattern {

    private final int argsLength;
    private BlockCategoryEnum blockCategoryArgs_1;
    private BlockCategoryEnum blockCategoryArgs_2;

    public TypeChangePattern(Extent extent, String[] args) {
        super(extent);

        this.argsLength = args.length;

        if (args.length >= 1 ) this.blockCategoryArgs_1 = BlockCategoryEnum.valueOf(args[0].toUpperCase());
        if (args.length >= 2 ) this.blockCategoryArgs_2 = BlockCategoryEnum.valueOf(args[1].toUpperCase());

    }


    @Override
    public BaseBlock applyBlock(BlockVector3 position) {

        Material oldMat = BukkitAdapter.adapt(position.getBlock(getExtent()).getBlockType());
        Material newMat;

        if (this.argsLength == 1) {

            System.out.println("oldMat = " + oldMat);

            if (BlockData.containsBlock(oldMat)) {

                System.out.println("oldMat = " + oldMat);

                if (BlockData.getDataBlock(oldMat).categoryBlockObject().hasEquivalent(blockCategoryArgs_1)) {

                    System.out.println("has equivalent true");

                    newMat = BlockData.getDataBlock(oldMat).categoryBlockObject().getEquivalent(blockCategoryArgs_1).getMaterial();

                    System.out.println("newMat = " + newMat);

                    final BaseBlock[] baseblock = {BukkitAdapter.asBlockType(newMat).getItemType().getBlockType().getDefaultState().toBaseBlock()};

                    position.getBlock(getExtent())
                            .toBaseBlock()
                            .getStates()
                            .forEach((property, object) -> baseblock[0] = baseblock[0].with(property.getKey(), object));

                    return baseblock[0];
                }
            }

            System.out.println("----------------------------------------");

        } else {

            if (BlockData.containsBlock(oldMat)) {

                if (BlockData.getDataBlock(oldMat).categoryBlockObject().containsCategory(blockCategoryArgs_1)) {


                    if (BlockData.getDataBlock(oldMat).categoryBlockObject().hasEquivalent(blockCategoryArgs_2)) {

                        newMat = BlockData.getDataBlock(oldMat).categoryBlockObject().getEquivalent(blockCategoryArgs_2).getMaterial();

                        final BaseBlock[] baseblock = {BukkitAdapter.asBlockType(newMat).getItemType().getBlockType().getDefaultState().toBaseBlock()};

                        position.getBlock(getExtent())
                                .toBaseBlock()
                                .getStates()
                                .forEach((property, object) -> baseblock[0] = baseblock[0].with(property.getKey(), object));

                        return baseblock[0];
                    }
                }
            }
        }

        return position.getBlock(getExtent()).toBaseBlock();

    }
}