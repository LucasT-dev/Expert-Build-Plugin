package fr.Marodeur.ExpertBuild.Brush.Brush;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.API.GlueList;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.BrushOperation;
import fr.Marodeur.ExpertBuild.Object.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class LineBrush implements BrushOperation {

    private final HashMap<UUID,ArrayList<BlockVec4>> point = new HashMap<>();
    Configuration conf = Main.getInstance().getConfig();

    @Override
    public boolean hasPermission(@NotNull Player p) {
        return p.isOp() | p.hasPermission("expline.use");
    }

    @Override
    public BrushEnum getTypeOfBrush() {
        return BrushEnum.LINE;
    }

    @Override
    public boolean hasEnabelingBrush(@NotNull BrushBuilder brushBuilder) {
        return brushBuilder.getEnable();
    }

    @Override
    public void ExecuteBrushOnHoney(Player p, Object obj1) {
    }

    @Override
    public void ExecuteBrushOnArrow(Player p, Object obj1) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p)) ||
                !BrushBuilder.getBrushBuilderPlayer(p).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        Location l = (Location) obj1;

        if (!point.containsKey(p.getUniqueId())) {

            ArrayList<BlockVec4> pointList = new ArrayList<>();
            pointList.add(new BlockVec4(l));

            point.put(p.getUniqueId(), pointList);
            p.sendMessage(Main.prefix + "Point add at (" + l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ() + ")");
            return;
        }
        if (point.containsKey(p.getUniqueId())) {

            ArrayList<BlockVec4> pointList = new ArrayList<>();
            pointList.addAll(point.get(p.getUniqueId()));

            //en config
            if (pointList.size() >= conf.getMax_point_saved()) {
                point.replace(p.getUniqueId(), pointList);
                p.sendMessage(Main.prefix + "limite size, point not save");

                return;
            }

            pointList.add(new BlockVec4(l));
            point.replace(p.getUniqueId(), pointList);
            p.sendMessage(Main.prefix + "Point add at (" + l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ() + ")");
        }
    }

    @Deprecated
    @Override
    public void ExecuteBrushOnGunpowder(Player p, Object obj1) {

        if (!hasPermission(p)) {
            return;
        }

        if (!hasEnabelingBrush(BrushBuilder.getBrushBuilderPlayer(p)) ||
                !BrushBuilder.getBrushBuilderPlayer(p).getBrushType().equals(getTypeOfBrush())) {
            return;
        }

        BukkitPlayer actor = BukkitAdapter.adapt(p);
        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        GlueList<BlockVec4> bv4 = new GlueList<>();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    ExecuteBrushOnArrow(p, obj1);

                    if (point.containsKey(p.getUniqueId())) {

                        ArrayList<BlockVec4> bv41 = point.get(p.getUniqueId());

                        for (int i = 0; i <= bv41.size(); i++) {
                            System.out.println("i = " + i);

                            if (i == bv41.size()-1) {
                                System.out.println("return = ");

                                point.get(p.getUniqueId()).stream().forEach(blockVec4 -> {

                                    blockVec4.setMat(brushBuilder.getMaterial());
                                    bv4.add(blockVec4);

                                    new UtilsFAWE(p).setBlockListSimple(p, bv4);

                                    point.remove(p.getUniqueId());

                                });
                                return;
                            }

                            BlockVec4 bv42 = bv41.get(i);

                            BlockVec4 bv421 = bv41.get(i+1);
                            bv4.addAll(new BlockVec4()
                                    .getPointInto2Point(new Location(p.getWorld(), bv42.getX(), bv42.getY(), bv42.getZ()),
                                            new Location(p.getWorld(), bv421.getX(), bv421.getY(), bv421.getZ()),
                                            1,
                                            brushBuilder.getMaterial()
                                            ));
                        }


                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
