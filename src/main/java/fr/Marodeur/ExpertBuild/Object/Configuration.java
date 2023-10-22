package fr.Marodeur.ExpertBuild.Object;

import com.sk89q.worldedit.WorldEdit;

import fr.Marodeur.ExpertBuild.Main;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class Configuration extends FileConfiguration {

    private final FileConfiguration yml;
    private final Logger log = Logger.getLogger("Expert-Build");

    private String version;
    private int max_rayon_brush;
    private int default_brush_rayon;
    private int default_air_brush;
    private Material default_material_brush;
    private String default_pattern_brush;
    private Biome default_biome_brush;
    private int max_brush_distance;
    private boolean display_bezier_curve;
    private boolean wand_click_in_air;
    private boolean sihft_click_with_wand;
    private boolean log_shortcut;
    private Material wand_item;
    private int max_file_size;
    private int max_point_saved;
    private Material terraforming_tool_1;
    private Material terraforming_tool_2;

    private Double arm_correction_factor;
    private String default_material;
    private int default_orga_height;

    private String lang;

    private boolean state_server_1;
    private String server_name_1;
    private boolean state_server_2;
    private String server_name_2;
    private boolean state_server_3;
    private String server_name_3;
    private boolean state_server_4;
    private String server_name_4;


    public Configuration() throws IOException {


        File file = new File("plugins/ExpertBuild/config.yml");
        this.yml = YamlConfiguration.loadConfiguration(file);

        if (getFileIsUpToDate()) updateFileConfig(file);

    }

    public Configuration loadConfiguration() {

        try {

            this.version = this.yml.getString("build.version");
            this.max_rayon_brush = this.yml.getInt("build.max_brush_rayon");
            this.default_brush_rayon = this.yml.getInt("build.default_brush_rayon");
            this.default_air_brush = this.yml.getInt("build.default_air_brush");
            this.default_material_brush = Material.valueOf(Objects.requireNonNull(this.yml.getString("build.default_material_brush")));
            this.default_pattern_brush = this.yml.getString("build.default_pattern_brush");
            this.default_biome_brush = Biome.valueOf(Objects.requireNonNull(this.yml.getString("build.default_biome_brush")));
            this.max_brush_distance = this.yml.getInt("build.max_brush_distance");
            this.display_bezier_curve = this.yml.getBoolean("build.display_bezier_curve");
            this.wand_click_in_air = this.yml.getBoolean("build.wand_click_in_air");
            this.sihft_click_with_wand = this.yml.getBoolean("build.sihft_click_with_wand");
            this.log_shortcut = this.yml.getBoolean("build.log_shortcut");
            this.wand_item = Material.matchMaterial(WorldEdit.getInstance().getConfiguration().wandItem.replace("minecraft:", ""));
            this.max_file_size = this.yml.getInt("build.max_file_size");
            this.max_point_saved = this.yml.getInt("build.max_point_saved");
            this.terraforming_tool_1 = Material.matchMaterial(Objects.requireNonNull(this.yml.getString("build.terraforming_tool_1")));
            this.terraforming_tool_2 = Material.matchMaterial(Objects.requireNonNull(this.yml.getString("build.terraforming_tool_2")));

            this.arm_correction_factor = this.yml.getDouble("build.GOHA.arm_correction_factor");
            this.default_material = this.yml.getString("build.GOHA.default_material");
            this.default_orga_height = this.yml.getInt("build.GOHA.default_orga_height");

            this.lang = this.yml.getString("build.lang");

            /*
             *
             * get state and name of server
             *
             */
            this.state_server_1 = this.yml.getBoolean("build.server_1.statserver");
            this.server_name_1 = this.yml.getString("build.server_1.name");

            this.state_server_2 = this.yml.getBoolean("build.server_2.statserver");
            this.server_name_2 = this.yml.getString("build.server_2.name");

            this.state_server_3 = this.yml.getBoolean("build.server_3.statserver");
            this.server_name_3 = this.yml.getString("build.server_3.name");

            this.state_server_4 = this.yml.getBoolean("build.server_4.statserver");
            this.server_name_4 = this.yml.getString("build.server_4.name");

        } catch (NullPointerException | IllegalStateException e) {

            log.severe("Configuration files in ExpertBuild folder is wrong or corrupt " +
                    "repair the file or delete all the files, so that the plugin recreates them");

        }

        return this;
    }

    public String getVersion() {
        return this.version;
    }
    public int getMaxRayonBrush() {
        return this.max_rayon_brush;
    }
    public int getDefaultBrushRayon() {
        return this.default_brush_rayon;
    }
    public int getDefault_air_brush() {
        return this.default_air_brush;
    }
    public Material getDefault_material_brush() {
        return default_material_brush;
    }
    public String getDefault_pattern_brush() {
        return default_pattern_brush;
    }
    public Biome getDefault_biome_brush() {
        return default_biome_brush;
    }
    public int getMax_brush_distance() {
        return max_brush_distance;
    }
    public boolean isDisplay_bezier_curve() {
        return display_bezier_curve;
    }
    public boolean isWand_click_in_air() {
        return wand_click_in_air;
    }
    public boolean isSihft_click_with_wand() {
        return sihft_click_with_wand;
    }
    public boolean getlog_shortcut() {
        return log_shortcut;
    }
    public Material getWand_item() {
        return wand_item;
    }
    public int getMax_file_size() {
        return max_file_size;
    }
    public int getMax_point_saved() {
        return max_point_saved;
    }
    public Material getTerraforming_tool_1() {
        return terraforming_tool_1;
    }
    public Material getTerraforming_tool_2() {
        return terraforming_tool_2;
    }

    public Double getArm_correction_factor() {
        return this.arm_correction_factor;
    }
    public String getDefault_material() {
        return this.default_material;
    }
    public int getDefault_orga_height() {
        return default_orga_height;
    }

    public String getLang() {
        return lang;
    }

    public Boolean getStateServer1() {
        return this.state_server_1;
    }
    public String getServerName1() {
        return this.server_name_1;
    }
    public Boolean getStateServer2() {
        return this.state_server_2;
    }
    public String getServerName2() {
        return this.server_name_2;
    }
    public Boolean getStateServer3() {
        return this.state_server_3;
    }
    public String getServerName3() {
        return this.server_name_3;
    }
    public Boolean getStateServer4() {
        return this.state_server_4;
    }
    public String getServerName4() {
        return this.server_name_4;
    }


    @NotNull
    @Override
    public String saveToString() {
        return "";
    }

    @Override
    public void loadFromString(@NotNull String contents) {

    }

    private boolean getFileIsUpToDate() {

        //Convert old config version with no version information to new file config version
        try {
            Objects.requireNonNull(this.yml.getString("build.version"));

            this.version = this.yml.getString("build.version");

        } catch (NullPointerException ignorred) {
            yml.set("build.version", "1.18.1.6");
            this.version = "1.18.1.6";

        }
        return !this.version.equals(Main.getVersion());
    }

    private void updateFileConfig(File file) throws IOException {

        if (this.version.equals("1.18.1.6")) {
            //Update config file from 1.18.1.6 to 1.18.1.7

            yml.set("build.logShortcut", true);

            yml.set("build.version", "1.18.1.7");
            this.version = "1.18.1.7";

            yml.save(file);

        }

        if (this.version.equals("1.18.1.7")) {
            //Update config file from 1.18.1.7 to 1.18.1.8
            //No modification of the configuration file

            yml.set("build.version", "1.18.1.8");
            this.version = "1.18.1.8";

            yml.save(file);

        }

        if (this.version.equals("1.18.1.8")) {
            //Update config file from 1.18.1.8 to 1.18.1.9
            //No modification of the configuration file

            yml.set("build.version", "1.18.1.9");
            this.version = "1.18.1.9";

            yml.save(file);

        }

        if (this.version.equals("1.18.1.9")) {
            //Update config file from 1.18.1.9 to 1.18.1.10
            //No modification of the configuration file

            yml.set("build.version", "1.18.1.10");
            this.version = "1.18.1.10";

            yml.save(file);

        }

        if (this.version.equals("1.18.1.10")) {
            //Update config file from 1.18.1.10 to 1.18.1.11

            //for next update

        }


        log.info("File config update");
    }
}