package fr.Marodeur.ExpertBuild.Brush.Brush;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DegradeBrush implements BrushOperation {

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.isOp() | p.hasPermission("expdegrade.use");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.DEGRADE;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return brushBuilder.getEnable();
    }

    @Deprecated
    @Override
    public void ExecuteBrushOnHoney(Player p, @NotNull Object obj1) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p)) ||
                !BrushBuilder.getBrushBuilderPlayer(p).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location l = (Location) obj1;
        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        int radius = bb.getRayon();
        List<BlockVec4> bv4 = new ArrayList<>();
        List<BlockVec4> bv4block = new ArrayList<>();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    for (int x = l.getBlockX() + radius; x >= l.getBlockX() - radius; x--) {
                        for (int z = l.getBlockZ() + radius; z >= l.getBlockZ() - radius; z--) {

                            Location locsphere = new Location(l.getWorld(), x, l.getY(), z);

                            if (l.distance(locsphere) <= radius) {
                                int y = new UtilsFAWE(p).getHeight(x, z, l);
                                if (y != 999) {
                                    int r = new Random().nextInt(11);
                                    if (r < 5) {
                                        bv4block.add(new BlockVec4(x, y, z, editsession.getFullBlock(BlockVector3.at(x, y, z))));
                                    }
                                }
                            }
                        }
                    }

                    bv4block.forEach(blockVec4 -> {

                        int x1 = new Random().nextInt(radius + 1);
                        int z1 = new Random().nextInt(radius + 1);

                        Location loc1 = new Location(l.getWorld(), l.getBlockX() - radius + x1, l.getY() + radius, l.getBlockZ() - radius + z1);
                        int y1 = new UtilsFAWE(p).getHeight(loc1.getBlockX(), loc1.getBlockZ(), l);

                        bv4.add(new BlockVec4(loc1.getBlockX(), y1+1, loc1.getBlockZ(), blockVec4.getBaseblock()));
                        bv4.add(new BlockVec4(blockVec4.getX(), blockVec4.getY(), blockVec4.getZ(), Material.AIR));

                    });

                    new UtilsFAWE(p).setBlockAnyPattern(p, bv4);
                } finally {
                    localSession.remember(editsession);
                }
            }
        });
    }
}