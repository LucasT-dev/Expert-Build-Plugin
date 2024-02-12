package fr.marodeur.expertbuild.enums;

import fr.marodeur.expertbuild.object.BrushOperation;
import fr.marodeur.expertbuild.brush.*;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Stream;

public enum BrushEnum {

    ERODE("e", ErodeBrush.class, "exp.brush.erode"), //OK
    BLENDBALL("bb", BlendBallBrush.class, "exp.brush.blendball"), //OK
    ERODEBLEND("eb", ErodeBlendBrush.class, "exp.brush.erodeblend"), //OK
    ERASER("eraser", EraserBrush.class, "exp.brush.eraser"),

    OVERLAY("overlay", OverlayBrush.class, "exp.brush.overlay"), //OK
    DRAIN("drain", DrainBrush.class, "exp.brush.drain"), //OK
    ROT2DCUBE("rot2Dcube", Rot2DCubeBrush.class, "exp.brush.2dcube"), //OK
    LINE("line", LineBrush.class, "exp.brush.line"), //OK
    SPIKE("spike", SpikeBrush.class, "exp.brush.spike"), //OK
    CUBE("cube", CubeBrush.class, "exp.brush.cube"), //OK

    NONE("none", NoneBrush.class, "exp.brush.none"), //OK
    SPHERE("sphere", SphereBrush.class, "exp.brush.sphere"), //OK
    DEGRADE("degrade", DegradeBrush.class, "exp.brush.degrade"), //OK
    CLIPBOARD("clipboard", ClipboardsBrush.class, "exp.brush.clipboard"), //OK
    FLOWER("flower", FlowerBrush.class, "exp.brush.flower"), //OK
    BIOME("biome", BiomeBrush.class, "exp.brush.biome"), //OK
    UPDATECHUNK("updatechunk", UpdateChunk.class, "exp.brush.updatechunk"), //OK
    TICK_REPEATER("tick_reaperter", null, "NOT_none"),
    CLIPBOARD_3D("clipboard3D", Clipboard3D.class, "exp.brush.clipboard3d"),
    AUTOFLIP("auto_flip", null, "NOT_none");


    public final String brush;
    public final Class<? extends BrushOperation> bclass;
    public final String permission;

    BrushEnum(String brush, Class<? extends BrushOperation> bclass, String permission) {
        this.brush = brush;
        this.bclass = bclass;
        this.permission = permission;
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

    public String getPermission() {
        return permission;
    }

    public static Stream<BrushEnum> getStreamArrayFilterPermission(Player p) {
        return Arrays.stream(BrushEnum.values()).filter(brushEnum -> p.hasPermission(brushEnum.getPermission()));
    }
}
