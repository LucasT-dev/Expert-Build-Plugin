package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.Main;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class DataPlugin {

    private final String pluginVersion;
    public String latestVersion;
    public String lateVersion;
    private int javaVersion;
    private String bukkitVersion;
    private String FaweVersion;
    private int MetricsId = 110059;




    public DataPlugin(Main main) {

        this.pluginVersion = main.getDescription().getVersion();
        this.FaweVersion = main.getServer().getPluginManager().getPlugin("WorldEdit").getDescription().getVersion();
        this.javaVersion = Integer.parseInt(System.getProperty("java.version").substring(0, 2));
        this.bukkitVersion = Bukkit.getBukkitVersion();

        updateChecker(version -> {
            if (!getPluginVersion().equals(version)) {
                main.getServer().getConsoleSender().sendMessage(new Message.MessageSender("expbuild.message.main.new_update_available", true, new String[]{this.getLateVersion(), this.getPluginVersion(), this.getLatestVersion()}).getMessage());
            }
        },getMetricsId());

    }


    public String getPluginVersion() {
        return pluginVersion;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public String getLateVersion() {
        return lateVersion;
    }

    public int getJavaVersion() {
        return javaVersion;
    }

    public String getBukkitVersion() {
        return bukkitVersion;
    }

    public String getFaweVersion() {
        return FaweVersion;
    }

    public int getMetricsId() {
        return MetricsId;
    }

    private void updateChecker(final Consumer<String> consumer, int id) {

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            try {

                InputStream inputStream = (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + id + "\\~")).openStream();
                Scanner scanner = new Scanner(inputStream);

                if (scanner.hasNext()) {

                    this.latestVersion = scanner.next();

                    this.lateVersion = String.valueOf(Integer.parseInt(latestVersion.substring(latestVersion.length() - 2)) - Integer.parseInt(getPluginVersion().substring(getPluginVersion().length() - 1)));

                    consumer.accept(latestVersion);
                }

            } catch (IOException e) {
                Main.getInstance().getLogger().severe(new Message.MessageSender("expbuild.message.main.unable_check_update", true, new String[]{e.getMessage()}).getMessage());
            }
        });
    }
}