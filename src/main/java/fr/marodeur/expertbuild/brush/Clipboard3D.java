package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.enums.BrushEnum;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.BrushOperation;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Clipboard3D implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission("exp.brush.clipboard3d");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.CLIPBOARD_3D ;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return brushBuilder.getEnable();
    }

    @Deprecated
    @Override
    public void ExecuteBrushOnHoney(Player p, Object obj1) {


        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p, true)) ||
                !BrushBuilder.getBrushBuilderPlayer(p, true).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location l = (Location) obj1;
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);

        assert brushBuilder != null;

        GlueList<BlockVec4> bv4 = new GlueList<>();
        GlueList<BlockVec4> tempbv4 = new GlueList<>();

        float pitch = p.getLocation().getPitch();
        float yaw = p.getLocation().getYaw();

        // Y rotation -> Yaw
        // X rotation -> Pitch

        brushBuilder.getClipboardBrush().getClipboardsBrush().iterator()
                .forEachRemaining(bv3 ->
                        tempbv4.add(
                                bv3.rotateAroundX(
                                        l.getBlockY(),
                                        l.getBlockZ(),
                                        l.getBlockY() + bv3.getY(),
                                        l.getBlockZ() + bv3.getZ(),
                                        pitch+90,
                                        bv3.getBaseblock(),
                                        l.getBlockX() + bv3.getX())));

        tempbv4.forEach(blv4 ->
                bv4.add(
                        blv4.rotateAroundY(
                                l.getBlockX(),
                                l.getBlockZ(),
                                blv4.getX(),
                                blv4.getZ(),
                                yaw,
                                blv4.getBaseblock(),
                                blv4.getY() + 1)
                ));

        new UtilsFAWE(p).setBlockList(p, bv4, false);
    }

    @Override
    public void ExecuteBrushOnArrow(Player p, Object obj1, Object loc) {
        BrushOperation.super.ExecuteBrushOnArrow(p, obj1, loc);
    }

    @Override
    public void ExecuteBrushOnGunpowder(Player p, Object obj1, Object loc) {
        BrushOperation.super.ExecuteBrushOnGunpowder(p, obj1, loc);
    }
}
