package fr.marodeur.expertbuild.object.builderObjects;

import java.util.UUID;

public class TerraParameter extends IDataProfile {

    private byte erosionFaces;
    private byte erosionRecursion;
    private byte fillFaces;
    private byte fillRecursion;

    public TerraParameter(UUID profileID, byte erosionFaces, byte erosionRecursion, byte fillFaces, byte fillRecursion) {
        super(profileID);

        this.erosionFaces = erosionFaces;
        this.erosionRecursion = erosionRecursion;
        this.fillFaces = fillFaces;
        this.fillRecursion = fillRecursion;
    }

    public byte getErosionFaces() {
        return erosionFaces;
    }

    public TerraParameter setErosionFaces(byte erosionFaces) {
        this.erosionFaces = erosionFaces;
        return this;
    }

    public byte getErosionRecursion() {
        return erosionRecursion;
    }

    public TerraParameter setErosionRecursion(byte erosionRecursion) {
        this.erosionRecursion = erosionRecursion;
        return this;
    }

    public byte getFillFaces() {
        return fillFaces;
    }

    public TerraParameter setFillFaces(byte fillFaces) {
        this.fillFaces = fillFaces;
        return this;
    }

    public byte getFillRecursion() {
        return fillRecursion;
    }

    public TerraParameter setFillRecursion(byte fillRecursion) {
        this.fillRecursion = fillRecursion;
        return this;
    }

    @Override
    public String toString() {
        return "TerraParameter{" +
                "erosionFaces=" + erosionFaces +
                ", erosionRecursion=" + erosionRecursion +
                ", fillFaces=" + fillFaces +
                ", fillRecursion=" + fillRecursion +
                '}';
    }
}
