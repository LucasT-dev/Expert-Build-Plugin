package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.object.builderObjects.ClipboardParameter;
import fr.marodeur.expertbuild.object.builderObjects.TerraParameter;
import fr.marodeur.expertbuild.object.builderObjects.TimelapseBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class DataProfile {

    private final HashMap<UUID, TimelapseBuilder> timelapseHashMap;

    private final HashMap<UUID, TerraParameter> terraParameterHashMap;

    private final HashMap<UUID, ClipboardParameter> clipboardParameterHashMap;


    public DataProfile() {
        this.timelapseHashMap = new HashMap<>();
        this.terraParameterHashMap = new HashMap<>();
        this.clipboardParameterHashMap = new HashMap<>();
    }

    void registerPlayer(UUID uuid) {

        if (! timelapseHashMap.containsKey(uuid)) {
            timelapseHashMap.put(uuid, new TimelapseBuilder(uuid, false, false));
        }

        if (! terraParameterHashMap.containsKey(uuid)) {
            terraParameterHashMap.put(uuid, new TerraParameter(uuid, (byte) 0, (byte) 0, (byte) 0, (byte) 0));
        }

        if (! clipboardParameterHashMap.containsKey(uuid)) {
            clipboardParameterHashMap.put(uuid, new ClipboardParameter(uuid, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), false));
        }

    }

    public TimelapseBuilder getTimelapseProfile(UUID uuid) {
        return this.timelapseHashMap.get(uuid);
    }
    public GlueList<TimelapseBuilder> getTimelapseHashMap() {
        GlueList<TimelapseBuilder> g = new GlueList<>();
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
}

