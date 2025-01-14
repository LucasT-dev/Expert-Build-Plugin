package fr.marodeur.expertbuild.object.block;

import fr.marodeur.expertbuild.object.block.blockObjects.CategoryBlockObject;
import fr.marodeur.expertbuild.object.block.blockObjects.ColorBlockObject;
import org.bukkit.Material;

public class DataBlockManager {


    private final Material material;
    private CategoryBlockObject categoryBlockObject;
    private ColorBlockObject colorBlockObject;


    public DataBlockManager(Material material) {
        this.material = material;
    }


    // Adds a category to this block
    public DataBlockManager addCategories(CategoryBlockObject categoryBlockObject) {
        this.categoryBlockObject = categoryBlockObject;
        return this; // Return `this` for chaining
    }

    // Adds a color to this block
    public DataBlockManager addColor(ColorBlockObject colorBlockObject) {
        this.colorBlockObject = colorBlockObject;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public CategoryBlockObject categoryBlockObject() {
        return categoryBlockObject;
    }

    public ColorBlockObject colorBlockObject() {
        return colorBlockObject;
    }

    @Override
    public String toString() {
        return "DataBlockObject{" +
                "material=" + material +
                '}';
    }
}
