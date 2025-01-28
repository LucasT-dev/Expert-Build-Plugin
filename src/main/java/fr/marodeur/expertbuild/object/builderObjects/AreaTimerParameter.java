package fr.marodeur.expertbuild.object.builderObjects;

import fr.marodeur.expertbuild.object.BlockVectorTool;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class AreaTimerParameter {

    private final BlockVectorTool pos1;
    private final BlockVectorTool pos2;
    private final String worldName;

    private final String name;

    // Stocker l'heure d'entrée de chaque joueur dans la zone
    private final HashMap<UUID, Long> entryTimeMap;

    // Stocker le temps total passé dans la zone pour chaque joueur
    private final HashMap<UUID, Long> totalTimeMap;

    boolean isRunning;

    public AreaTimerParameter(BlockVectorTool pos1, BlockVectorTool pos2, String worldName, String name, boolean isRunning) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.worldName = worldName;
        this.name = name;
        this.isRunning = isRunning;
        this.entryTimeMap = new HashMap<>();
        this.totalTimeMap = new HashMap<>();
    }

    public BlockVectorTool getPos1() {
        return pos1;
    }

    public BlockVectorTool getPos2() {
        return pos2;
    }

    public String getWorldName() {
        return worldName;
    }

    public String getName() {
        return name;
    }

    public boolean isRunning() {
        return isRunning;
    }


    public void setRunning(boolean running) {
        isRunning = running;
    }

    // Appelé pour virer tous les joueurs de l'area pour un running = false

    public Set<UUID> getUuidPlayerEnteredInArea() {
        // Utilisation de keySet() pour obtenir un Set des clés, puis conversion en List
        return entryTimeMap.keySet();
    }

    // Appelé lorsqu'un joueur entre dans la zone
    public boolean playerIsAlreadyRegisterInArea(UUID playerUUID) {
        return this.entryTimeMap.containsKey(playerUUID);
    }

    public void playerEnteredZone(UUID playerUUID) {
        entryTimeMap.put(playerUUID, System.currentTimeMillis());
    }

    // Appelé lorsqu'un joueur quitte la zone
    public void playerExitedZone(UUID playerUUID) {
        Long entryTime = entryTimeMap.remove(playerUUID);
        if (entryTime != null) {
            long timeSpent = System.currentTimeMillis() - entryTime;
            totalTimeMap.put(playerUUID, totalTimeMap.getOrDefault(playerUUID, 0L) + timeSpent);
        }
    }

    // Obtenir le temps total passé dans la zone pour un joueur
    public String getTotalTimeInZone(UUID playerUUID) {

        long playerTime = totalTimeMap.getOrDefault(playerUUID, 0L);
        long totalTime = totalTimeMap.values().stream().mapToLong(Long::longValue).sum();

        // Calcul du pourcentage du temps total
        double percentage = totalTime > 0 ? (double) playerTime / totalTime * 100 : 0;

        // Formatage du temps individuel et ajout du pourcentage
        return String.format("%s (%.2f%%)", this.formatTime(playerTime), percentage);
    }

    // Méthode pour obtenir le temps total cumulé de tous les joueurs dans la zone
    public String getTotalTimeInZone() {
        long totalTime = 0;
        for (long time : totalTimeMap.values()) {
            totalTime += time;
        }
        return this.formatTime(totalTime);
    }

    // Méthode pour vérifier si un joueur est dans la zone
    public boolean isInArea(BlockVectorTool blockVectorTool, String worldName) {
        return worldName.equals(this.worldName) &&
                blockVectorTool.getX() >= Math.min(pos1.getX(), pos2.getX()) &&
                blockVectorTool.getX() <= Math.max(pos1.getX(), pos2.getX()) &&
                blockVectorTool.getY() >= Math.min(pos1.getY(), pos2.getY()) &&
                blockVectorTool.getY() <= Math.max(pos1.getY(), pos2.getY()) &&
                blockVectorTool.getZ() >= Math.min(pos1.getZ(), pos2.getZ()) &&
                blockVectorTool.getZ() <= Math.max(pos1.getZ(), pos2.getZ());
    }

    // Méthode pour formater le temps en une chaîne lisible
    private String formatTime(long timeInMillis) {
        long days = TimeUnit.MILLISECONDS.toDays(timeInMillis);
        long hours = TimeUnit.MILLISECONDS.toHours(timeInMillis) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeInMillis) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeInMillis) % 60;

        return String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
    }

    @Override
    public String toString() {
        return "AreaTimerParameter{" +
                "pos1=" + pos1 +
                ", pos2=" + pos2 +
                ", worldName='" + worldName + '\'' +
                ", isRunning=" + isRunning +
                '}';
    }
}
