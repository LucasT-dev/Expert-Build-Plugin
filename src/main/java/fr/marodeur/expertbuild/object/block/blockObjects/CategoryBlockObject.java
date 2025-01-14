package fr.marodeur.expertbuild.object.block.blockObjects;

import fr.marodeur.expertbuild.enums.BlockCategoryEnum;
import fr.marodeur.expertbuild.object.block.BlockData;
import fr.marodeur.expertbuild.object.block.DataBlockManager;
import fr.marodeur.expertbuild.object.block.category.ColorCategory;
import fr.marodeur.expertbuild.object.block.category.ShapeCategory;
import fr.marodeur.expertbuild.object.block.category.TypeCategory;

import java.util.EnumSet;
import java.util.stream.Collectors;

public record CategoryBlockObject(EnumSet<BlockCategoryEnum> categories) {


    public boolean containsCategory(BlockCategoryEnum blockCategory) {
        return this.categories.contains(blockCategory);
    }

    public boolean containsTypeCategory() {
        return this.categories.stream().anyMatch(blockCategoryEnum -> blockCategoryEnum.getCategoryType() instanceof TypeCategory);
    }
    public BlockCategoryEnum getTypeCategory() {
        return this.categories.stream()
                .filter(blockCategoryEnum -> blockCategoryEnum.getCategoryType() instanceof TypeCategory)
                .findAny()
                .orElse(null);
    }

    public boolean containsShapeCategory() {
        return this.categories.stream().anyMatch(blockCategoryEnum -> blockCategoryEnum.getCategoryType() instanceof ShapeCategory);
    }
    public BlockCategoryEnum getShapeCategory() {
        return this.categories.stream()
                .filter(blockCategoryEnum -> blockCategoryEnum.getCategoryType() instanceof ShapeCategory)
                .findAny()
                .orElse(null);
    }

    public boolean containsColorCategory() {
        return this.categories.stream().anyMatch(blockCategoryEnum -> blockCategoryEnum.getCategoryType() instanceof ColorCategory);
    }
    public BlockCategoryEnum getColorCategory() {
        return this.categories.stream()
                .filter(blockCategoryEnum -> blockCategoryEnum.getCategoryType() instanceof ColorCategory)
                .findAny()
                .orElse(null);
    }

    private EnumSet<BlockCategoryEnum> modifyEnumSet(BlockCategoryEnum newBlockCategoryEnum) {

        EnumSet<BlockCategoryEnum> enumSet = categories.clone();

        for (BlockCategoryEnum blockCategoryEnum : enumSet) {

            if (blockCategoryEnum.getCategoryType().getType().equals(newBlockCategoryEnum.getCategoryType().getType())) {

                enumSet.remove(blockCategoryEnum);
                enumSet.add(newBlockCategoryEnum);

            }
        }
        return enumSet;
    }

    public boolean hasEquivalent(BlockCategoryEnum newBlockCategoryEnum) {
        return BlockData.containsBlock(modifyEnumSet(newBlockCategoryEnum));
    }
    public DataBlockManager getEquivalent(BlockCategoryEnum newBlockCategoryEnum) {

        EnumSet<BlockCategoryEnum> enumSet = modifyEnumSet(newBlockCategoryEnum);

        if (BlockData.containsBlock(enumSet)) {
            return BlockData.getDataBlock(enumSet);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Block Categories: " + categories.stream()
                .map(BlockCategoryEnum::name)
                .sorted()
                .collect(Collectors.joining(", "));
    }
}
