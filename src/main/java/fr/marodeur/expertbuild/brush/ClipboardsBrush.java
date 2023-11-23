package fr.marodeur.expertbuild.brush;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.enums.BrushEnum;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.BrushOperation;
import com.sk89q.worldedit.world.block.BaseBlock;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ClipboardsBrush implements BrushOperation {

    private static final List<Integer> rotation = Arrays.asList(0, 90, 180, 270);

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission("exp.brush.clipboard");
    }

    @Override
    public BrushEnum getTypeOfBrush() { return BrushEnum.CLIPBOARD; }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return BrushOperation.super.hasEnabelingBrush(brushBuilder);
    }

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
        Random r = new Random();
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);
        GlueList<BlockVec4> bv4 = new GlueList<>();

        if (brushBuilder.getClipboardsParameter().getClipboardsBlock().size() == 0) {
            p.sendMessage(Main.prefix + "Any selection register");
            return;
        }

        int randomRotation = 0;
        int index = r.nextInt(brushBuilder.getClipboardsParameter().getClipboardsName().size());

        if (brushBuilder.getClipboardsParameter().isRandomRotation()) {
            randomRotation = rotation.get(r.nextInt(rotation.size()));
        }

        int finalRandomRotation = randomRotation;
        brushBuilder.getClipboardsParameter().getClipboardsBlock().get(index)
                .iterator()
                .forEachRemaining(bv3 ->
                        bv4.add(bv3.rotateAroundY(l.getBlockX(), l.getBlockZ(),
                                l.getBlockX() + bv3.getX(),
                                l.getBlockZ() + bv3.getZ(),
                                finalRandomRotation, bv3.getBaseblock(),
                                l.getBlockY() + bv3.getY() + 1)));

        new UtilsFAWE(p).setBlockList(p, bv4, false);
    }
}
