package fr.marodeur.expertbuild.object.block;

import org.bukkit.Material;

public class DataBlockObject {


    private final Material material;
    private CategoryBlockObject categoryBlockObject;
    private ColorBlockObject colorBlockObject;


    public DataBlockObject(Material material) {
        this.material = material;
    }


    // Adds a category to this block
    public DataBlockObject addCategories(CategoryBlockObject categoryBlockObject) {
        this.categoryBlockObject = categoryBlockObject;
        return this; // Return `this` for chaining
    }

    // Adds a color to this block
    public DataBlockObject addColor(ColorBlockObject colorBlockObject) {
        this.colorBlockObject = colorBlockObject;
        return this; // Return `this` for chaining
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
