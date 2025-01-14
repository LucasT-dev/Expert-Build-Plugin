package fr.marodeur.expertbuild.object.block.blockObjects;

public class ColorBlockObject {

    int rgb;


    public ColorBlockObject(int red, int green, int blue) {
        this.rgb = (red << 16) | (green << 8) | blue;
    }


    public int getRed() {
        return (rgb >> 16) & 0xFF;
    }
    public int getGreen() {
        return (rgb >> 8) & 0xFF;
    }
    public int getBlue() {
        return rgb & 0xFF;
    }

    public int getRGB() {
        return rgb;
    }

    @Override
    public String toString() {
        return "ColorBlockObject{" +
                "rgb=" + rgb +
                '}';
    }
}
