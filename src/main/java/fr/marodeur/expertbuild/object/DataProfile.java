package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.object.builderObjects.TimelapseBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class DataProfile {

    private final HashMap<UUID, TimelapseBuilder> timelapseHashMap;


    public DataProfile() {
        this.timelapseHashMap = new HashMap<>();
    }

    void registerPlayer(UUID uuid) {

        if (! timelapseHashMap.containsKey(uuid)) {
            timelapseHashMap.put(uuid, new TimelapseBuilder(uuid, false, false));
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
}


