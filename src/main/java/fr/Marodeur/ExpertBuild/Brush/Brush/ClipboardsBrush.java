package fr.Marodeur.ExpertBuild.Brush.Brush;

import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.API.GlueList;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ClipboardsBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.isOp() | p.hasPermission("expclipboards.use");
    }

    @Override
    public BrushEnum getTypeOfBrush() { return BrushEnum.CLIPBOARD; }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) { return brushBuilder.getEnable(); }

    @Override
    public void ExecuteBrushOnHoney(Player p, Object obj1) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p)) ||
                !BrushBuilder.getBrushBuilderPlayer(p).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location l = (Location) obj1;
        Random r = new Random();
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

        if (brushBuilder.getClipboards().size() == 0) {
            p.sendMessage(Main.prefix + "Any selection register");
            return;
        }

        int index = r.nextInt(brushBuilder.getClipboards().size());

        GlueList<BlockVec4> bv4 = new GlueList<>();

        brushBuilder.getClipboards().get(index)
                .iterator()
                .forEachRemaining(bv3 -> bv4.add(new BlockVec4(l.clone().add(bv3.getX(), bv3.getY(), bv3.getZ()), bv3.getBaseblock())));
        new UtilsFAWE(p).setBlockList(p, bv4, false);
    }
}
