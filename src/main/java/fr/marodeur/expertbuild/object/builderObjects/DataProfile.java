package fr.marodeur.expertbuild.object.builderObjects;

import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockTypes;
import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.object.Configuration;

import fr.marodeur.expertbuild.object.Flag;
import org.bukkit.Bukkit;

import java.util.*;
import java.util.logging.Logger;

public class DataProfile {

    private static final Configuration CONF = Main.getConfiguration();
    private static final Logger LOG = Main.getInstance().getLogger();

    private final HashMap<UUID, TimelapseBuilderParameter> timelapseHashMap;

    private final HashMap<UUID, TerraParameter> terraParameterHashMap;

    private final HashMap<UUID, ClipboardParameter> clipboardParameterHashMap;

    private final HashMap<UUID, LeatherParameter> leatherParameterHashMap;

    private final HashMap<UUID, GohaParameter> gohaParameterHashMap;

    private final HashMap<UUID, Clipboard3DParameter> clipboard3DParameterHashMap;

    private final HashMap<UUID, FlowerBrushParameter> flowerBrushParameterHashMap;


    public DataProfile() {

        this.timelapseHashMap = new HashMap<>();
        this.terraParameterHashMap = new HashMap<>();
        this.clipboardParameterHashMap = new HashMap<>();
        this.leatherParameterHashMap = new HashMap<>();
        this.gohaParameterHashMap = new HashMap<>();
        this.clipboard3DParameterHashMap = new HashMap<>();
        this.flowerBrushParameterHashMap = new HashMap<>();
    }

    public void registerPlayer(UUID uuid) {

        if (! timelapseHashMap.containsKey(uuid)) {
            timelapseHashMap.put(uuid, new TimelapseBuilderParameter(uuid, false, false));
        }

        if (! terraParameterHashMap.containsKey(uuid)) {
            terraParameterHashMap.put(uuid, new TerraParameter(uuid, (byte) 0, (byte) 0, (byte) 0, (byte) 0));
        }

        if (! clipboardParameterHashMap.containsKey(uuid)) {
            clipboardParameterHashMap.put(uuid, new ClipboardParameter(uuid, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), false));
        }

        if (! leatherParameterHashMap.containsKey(uuid)) {
            leatherParameterHashMap.put(uuid, new LeatherParameter(uuid, (short)1, (short)1, (short)1));
        }

        if (! gohaParameterHashMap.containsKey(uuid)) {
            gohaParameterHashMap.put(uuid, new GohaParameter(uuid,
                    CONF.getDefault_orga_height(), new FaweAPI(Bukkit.getPlayer(uuid)).getPattern(CONF.getDefault_material().toLowerCase()),
                    (short)0, Bukkit.getPlayer(uuid).getLocation(), UUID.randomUUID(), false,
                    true, (short)70, (short)180,
                    true, (short)60, (short)0,
                    true, (short)30, (short)180,
                    true, (short)60, (short)180,
                    true, (short)-75, (short)180,
                    true, (short)-85, (short)180,
                    true, (short)70, (short)180,
                    true, (short)70, (short)180,
                    true, (short)60, (short)0,
                    true, (short)60, (short)0));
        }

        if (! clipboard3DParameterHashMap.containsKey(uuid)) {
            clipboard3DParameterHashMap.put(uuid, new Clipboard3DParameter(uuid, null, new Flag("abce")));
        }

        BaseBlock ib = BlockTypes.BARRIER.getDefaultState().toBaseBlock();

        if (! flowerBrushParameterHashMap.containsKey(uuid)) {
            flowerBrushParameterHashMap.put(uuid, new FlowerBrushParameter(uuid, Arrays.asList(ib, ib, ib, ib, ib, ib, ib, ib, ib), Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0), CONF.getDefault_air_brush()) );
        }
    }

    public TimelapseBuilderParameter getTimelapseProfile(UUID uuid) {
        return this.timelapseHashMap.get(uuid);
    }
    public GlueList<TimelapseBuilderParameter> getTimelapseHashMap() {
        GlueList<TimelapseBuilderParameter> g = new GlueList<>();
        g.addAll(this.timelapseHashMap.values());
        return g;
    }

    public TerraParameter getTerraParameterProfile(UUID uuid) {
        return this.terraParameterHashMap.get(uuid);
    }
    public GlueList<TerraParameter> getTerraParameterHashMap() {
        GlueList<TerraParameter> g = new GlueList<>();
        g.addAll(this.terraParameterHashMap.values());
        return g;
    }

    public ClipboardParameter getClipboardParameterProfile(UUID uuid) {
        return this.clipboardParameterHashMap.get(uuid);
    }
    public GlueList<ClipboardParameter> getClipboardParameterHashMap() {
        GlueList<ClipboardParameter> g = new GlueList<>();
        g.addAll(this.clipboardParameterHashMap.values());
        return g;
    }

    public LeatherParameter getLeatherParameterProfile(UUID uuid) {
        return this.leatherParameterHashMap.get(uuid);
    }
    public GlueList<LeatherParameter> getLeatherParameterHashMap() {
        GlueList<LeatherParameter> g = new GlueList<>();
        g.addAll(this.leatherParameterHashMap.values());
        return g;
    }

    public GohaParameter getGohaParameterProfile(UUID uuid) {
        return this.gohaParameterHashMap.get(uuid);
    }
    public GlueList<GohaParameter> getGohaParameterHashMap() {
        GlueList<GohaParameter> g = new GlueList<>();
        g.addAll(this.gohaParameterHashMap.values());
        return g;
    }

    public Clipboard3DParameter getClipboard3dProfile(UUID uuid) {
        return this.clipboard3DParameterHashMap.get(uuid);
    }
    public GlueList<Clipboard3DParameter> getClipboard3DParametersHashMap() {
        GlueList<Clipboard3DParameter> g = new GlueList<>();
        g.addAll(this.clipboard3DParameterHashMap.values());
        return g;
    }


    public FlowerBrushParameter getFlowerBrushProfile(UUID uuid) {
        return this.flowerBrushParameterHashMap.get(uuid);
    }
    public GlueList<FlowerBrushParameter> getFlowerBrushParametersHashMap() {
        GlueList<FlowerBrushParameter> g = new GlueList<>();
        g.addAll(this.flowerBrushParameterHashMap.values());
        return g;
    }
}

