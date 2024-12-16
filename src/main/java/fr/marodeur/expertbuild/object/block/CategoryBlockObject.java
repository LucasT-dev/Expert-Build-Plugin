package fr.marodeur.expertbuild.object.block;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.enums.BlockCategoryEnum;

import java.util.EnumSet;
import java.util.stream.Collectors;

public class CategoryBlockObject {

    private final EnumSet<BlockCategoryEnum> categories;

    public CategoryBlockObject(EnumSet<BlockCategoryEnum> categories) {

        this.categories = categories;
    }

    public EnumSet<BlockCategoryEnum> categories() {
        return categories;
    }

    public boolean containsCategory(BlockCategoryEnum blockCategory) {
        return this.categories.contains(blockCategory);
    }


    public boolean hasEquivalent(BlockCategoryEnum newBlockCategoryEnum) {

        EnumSet<BlockCategoryEnum> enumSet = categories.clone();



        for (BlockCategoryEnum blockCategoryEnum : enumSet) {

            if (blockCategoryEnum.getCategoryType().getType().equals(newBlockCategoryEnum.getCategoryType().getType())) {

                enumSet.remove(blockCategoryEnum);
                enumSet.add(newBlockCategoryEnum);

            }
        }

        return Main.getBlockData().materialExist(enumSet);
    }

    public DataBlockObject getEquivalent(BlockCategoryEnum newBlockCategoryEnum) {

        EnumSet<BlockCategoryEnum> enumSet = categories.clone();

        for (BlockCategoryEnum blockCategoryEnum : enumSet) {

            if (blockCategoryEnum.getCategoryType().getType().equals(newBlockCategoryEnum.getCategoryType().getType())) {

                enumSet.remove(blockCategoryEnum);
                enumSet.add(newBlockCategoryEnum);

            }
        }

        if (Main.getBlockData().materialExist(enumSet)) {
            return Main.getBlockData().getDataBlock(enumSet);
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
