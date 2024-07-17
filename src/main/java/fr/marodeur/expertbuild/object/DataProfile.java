package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.object.builderObjects.ClipboardParameter;
import fr.marodeur.expertbuild.object.builderObjects.LeatherParameter;
import fr.marodeur.expertbuild.object.builderObjects.TerraParameter;
import fr.marodeur.expertbuild.object.builderObjects.TimelapseBuilderParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class DataProfile {

    private final HashMap<UUID, TimelapseBuilderParameter> timelapseHashMap;

    private final HashMap<UUID, TerraParameter> terraParameterHashMap;

    private final HashMap<UUID, ClipboardParameter> clipboardParameterHashMap;

    private final HashMap<UUID, LeatherParameter> leatherParameterHashMap;

    public DataProfile() {
        this.timelapseHashMap = new HashMap<>();
        this.terraParameterHashMap = new HashMap<>();
        this.clipboardParameterHashMap = new HashMap<>();
        this.leatherParameterHashMap = new HashMap<>();
    }

    void registerPlayer(UUID uuid) {

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
    }

    public TimelapseBuilderParameter getTimelapseProfile(UUID uuid) {
        return this.timelapseHashMap.get(uuid);
    }
    public GlueList<TimelapseBuilderParameter> getTimelapseHashMap() {
        GlueList<TimelapseBuilderParameter> g = new GlueList<>();
        g.addAll(timelapseHashMap.values());
        return g;
    }

    public TerraParameter getTerraParameterProfile(UUID uuid) {
        return this.terraParameterHashMap.get(uuid);
    }
    public GlueList<TerraParameter> getTerraParameterHashMap() {
        GlueList<TerraParameter> g = new GlueList<>();
        g.addAll(terraParameterHashMap.values());
        return g;
    }

    public ClipboardParameter getClipboardParameterProfile(UUID uuid) {
        return this.clipboardParameterHashMap.get(uuid);
    }
    public GlueList<ClipboardParameter> getClipboardParameterHashMap() {
        GlueList<ClipboardParameter> g = new GlueList<>();
        g.addAll(clipboardParameterHashMap.values());
        return g;
    }

    public LeatherParameter getLeatherParameterProfile(UUID uuid) {
        return this.leatherParameterHashMap.get(uuid);
    }
    public GlueList<LeatherParameter> getLeatherParameterHashMap() {
        GlueList<LeatherParameter> g = new GlueList<>();
        g.addAll(leatherParameterHashMap.values());
        return g;
    }
}

