package fr.Marodeur.ExpertBuild.Enum;

import fr.Marodeur.ExpertBuild.Brush.Brush.*;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;

public enum BrushEnum {

    LIFT("lift", ErodeBrush.class), //OK
    FILL("fill", ErodeBrush.class), //OK
    CUSTOM("custom", ErodeBrush.class), //OK
    SMOOTH("smooth", ErodeBrush.class),
    MELT("smooth", ErodeBrush.class), //OK
    FLOATCLEAN("smooth", ErodeBrush.class),
    BLENDBALL("blendball", BlendBall.class), //OK

    OVERLAY("overlay", OverlayBrush.class), //OK
    DRAIN("drain", DrainBrush.class), //OK
    ROT2DCUBE("rot2Dcube", Rot2DCubeBrush.class), //OK
    LINE("line", LineBrush.class), //OK
    SPIKE("spike", SpikeBrush.class), //OK
    CUBE("cube", CubeBrush.class), //OK

    NONE("none", NoneBrush.class), //OK
    SPHERE("sphere", SphereBrush.class), //OK
    DEGRADE("degrade", DegradeBrush.class), //OK
    CLIPBOARD("clipboard", ClipboardsBrush.class), //OK
    FLOWER("flower", FlowerBrush.class), //OK
    BIOME("biome", BiomeBrush.class), //OK
    UPDATECHUNK("updatechunk", UpdateChunk.class), //OK
    TICK_REPEATER("tick_reaperter", null),
    AUTOFLIP("auto_flip", null);


    public final String brush;

    public final Class<? extends BrushOperation> bclass;

    BrushEnum(String brush, Class<? extends BrushOperation> bclass) {
        this.brush = brush;
        this.bclass = bclass;
    }

    public String getBrush() {
        return brush;
    }

    public Class<? extends BrushOperation> getBclass() {
        return bclass;
    }

    public final String getPrefix() {
        return brush;
    }
}
