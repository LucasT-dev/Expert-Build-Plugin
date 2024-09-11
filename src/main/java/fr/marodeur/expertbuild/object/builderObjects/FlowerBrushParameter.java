package fr.marodeur.expertbuild.object.builderObjects;

import com.sk89q.worldedit.world.block.BaseBlock;

import java.util.List;
import java.util.UUID;

public class FlowerBrushParameter extends IDataProfile {

    private final List<BaseBlock> flowerMaterial;
    private final List<Integer> flowerMaterialRate;

    private int airBrush;

    public FlowerBrushParameter(UUID ProfileID, List<BaseBlock> flowerMaterial, List<Integer> flowerMaterialRate, int airBrush) {
        super(ProfileID);

        this.flowerMaterial = flowerMaterial;
        this.flowerMaterialRate = flowerMaterialRate;
        this.airBrush = airBrush;
    }

    public List<BaseBlock> flowerMaterial() {
        return flowerMaterial;
    }

    public List<Integer> flowerMaterialRate() {
        return flowerMaterialRate;
    }

    public int airBrush() {
        return airBrush;
    }


    // Setter

    public FlowerBrushParameter addFlowerMaterial(BaseBlock flowerMaterial, int index) {
        this.flowerMaterial.set(index, flowerMaterial);
        return this;
    }

    public FlowerBrushParameter addFlowerMaterialRate(Integer flowerMaterialRate, int index) {
        this.flowerMaterialRate.set(index, flowerMaterialRate);
        return this;
    }

    public FlowerBrushParameter addFlowerMaterialRate(int index, boolean isShiftClick, boolean isRightClick) {

        int maxRadius = 100;
        int minRadius = 1;
        int n =  this.flowerMaterialRate.get(index);
        int num;

        if (isShiftClick) {
            if (isRightClick) {
                num = -10;
            } else {
                num = 10;
            }
        } else {
            if (isRightClick) {
                num = -1;
            } else {
                num = 1;
            }
        }

        if (n + num > maxRadius) {
            this.flowerMaterialRate.set(index, maxRadius);
        } else if (n + num < minRadius) {
            this.flowerMaterialRate.set(index, minRadius);
        } else {
            this.flowerMaterialRate.set(index, n + num);
        }
        return this;
    }

    public FlowerBrushParameter setAirBrush(boolean isShiftClick, boolean isRightClick) {

        int maxRotation = 100;
        int minRotation = 0;
        int n = this.airBrush;
        int num;

        if (isShiftClick) {
            if (isRightClick) {
                num = -10;
            } else {
                num = 10;
            }
        } else {
            if (isRightClick) {
                num = -1;
            } else {
                num = 1;
            }
        }

        if (n + num > maxRotation) {
            this.airBrush = maxRotation;
        } else if (n + num < minRotation) {
            this.airBrush = minRotation;
        } else {
            this.airBrush = n + num;
        }
        return this;
    }
}
