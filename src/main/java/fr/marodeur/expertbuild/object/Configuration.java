package fr.marodeur.expertbuild.object;

import com.sk89q.worldedit.WorldEdit;

import fr.marodeur.expertbuild.Main;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class Configuration {

    File file = new File("plugins/ExpertBuild/config.yml");
    FileConfiguration yml = YamlConfiguration.loadConfiguration(file);

    private final Logger log = Logger.getLogger("Expert-Build");

    private String version;
    private String prefix;
    private int max_rayon_brush;
    private int default_brush_rayon;
    private int default_air_brush;
    private String default_pattern_brush;
    private int max_brush_distance;

    private boolean display_bezier_curve;
    private Particle particle_bezier_curve_type;
    private int coefficient_particle_number;

    private boolean display_convex_line;
    private Particle particle_convex_type_line;
    private int spacing_between_particles;

    private int period_particle;

    private boolean wand_click_in_air;
    private boolean default_on_the_fly_selection;
    private boolean sihft_click_with_wand;
    private boolean log_shortcut;
    private Material wand_item;
    private int max_file_size;
    private int max_point_saved;
    private Material terraforming_tool_1;
    private Material terraforming_tool_2;

    private boolean share_clipboard_saved;


    private int timelapse_max_block_per_tick;
    private int max_timelapse_in_same_time;

    private boolean send_message_builder_register;

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
    }

    public Configuration loadConfiguration() throws IOException {

        if (getFileIsUpToDate()) updateFileConfig(file);

        try {

            this.version = yml.getString("build.version");

            this.prefix = yml.getString("build.prefix");

            this.max_rayon_brush = yml.getInt("build.max_brush_rayon");
            this.default_brush_rayon = yml.getInt("build.default_brush_rayon");
            this.default_air_brush = yml.getInt("build.default_air_brush");
            this.default_pattern_brush = yml.getString("build.default_pattern_brush");
            this.max_brush_distance = yml.getInt("build.max_brush_distance");

            // Particle
            this.display_bezier_curve = yml.getBoolean("build.display_bezier_curve");
            this.particle_bezier_curve_type = Particle.valueOf(yml.getString("build.particle_bezier_curve_type").toUpperCase());
            this.coefficient_particle_number = yml.getInt("build.coefficient_particle_number");

            this.display_convex_line = yml.getBoolean("build.display_convex_line");
            this.particle_convex_type_line = Particle.valueOf(yml.getString("build.particle_convex_type_line").toUpperCase());
            this.spacing_between_particles = yml.getInt("build.spacing_between_particles");

            this.period_particle = yml.getInt("build.period_particle");

            this.wand_click_in_air = yml.getBoolean("build.wand_click_in_air");
            this.default_on_the_fly_selection = yml.getBoolean("build.default_on_the_fly_selection");
            this.sihft_click_with_wand = yml.getBoolean("build.sihft_click_with_wand");
            this.log_shortcut = yml.getBoolean("build.logShortcut");
            this.wand_item = Material.matchMaterial(WorldEdit.getInstance().getConfiguration().wandItem.replace("minecraft:", ""));
            this.max_file_size = yml.getInt("build.max_file_size");
            this.max_point_saved = yml.getInt("build.max_point_saved");
            this.terraforming_tool_1 = Material.matchMaterial(Objects.requireNonNull(yml.getString("build.terraforming_tool_1")));
            this.terraforming_tool_2 = Material.matchMaterial(Objects.requireNonNull(yml.getString("build.terraforming_tool_2")));
            this.share_clipboard_saved = yml.getBoolean("build.share_clipboard_saved");


            this.timelapse_max_block_per_tick = yml.getInt("build.timelapse_max_block_per_tick");
            this.max_timelapse_in_same_time = yml.getInt("build.max_timelapse_in_same_time");

            this.send_message_builder_register = yml.getBoolean("build.send_message_builder_register");

            this.arm_correction_factor = yml.getDouble("build.GOHA.arm_correction_factor");
            this.default_material = yml.getString("build.GOHA.default_material");
            this.default_orga_height = yml.getInt("build.GOHA.default_orga_height");

            this.lang = yml.getString("build.lang");

            /*
             *
             * get state and name of server
             *
             */
            this.state_server_1 = yml.getBoolean("build.server_1.statserver");
            this.server_name_1 = yml.getString("build.server_1.name");

            this.state_server_2 = yml.getBoolean("build.server_2.statserver");
            this.server_name_2 = yml.getString("build.server_2.name");

            this.state_server_3 = yml.getBoolean("build.server_3.statserver");
            this.server_name_3 = yml.getString("build.server_3.name");

            this.state_server_4 = yml.getBoolean("build.server_4.statserver");
            this.server_name_4 = yml.getString("build.server_4.name");

        } catch (NullPointerException | IllegalStateException e) {

            log.severe("Configuration files in ExpertBuild folder is wrong or corrupt " +
                    "repair the file or delete all the files, so that the plugin recreates them");

        }
        return this;
    }

    public String getVersion() {
        return this.version;
    }

    public String prefix() {
        return prefix;
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
    public String getDefault_pattern_brush() {
        return default_pattern_brush;
    }
    public int getMax_brush_distance() {
        return max_brush_distance;
    }

    // Bezier particle
    public boolean isDisplay_bezier_curve() {
        return display_bezier_curve;
    }
    public Particle getParticle_bezier_curve_type() {
        return particle_bezier_curve_type;
    }
    public int getCoefficient_particle_number() {
        return coefficient_particle_number;
    }

    // Line particle
    public boolean getDisplay_convex_line() {
        return display_convex_line;
    }
    public Particle getParticle_convex_type_line() {
        return particle_convex_type_line;
    }
    public int getSpacing_between_particles() {
        return spacing_between_particles;
    }

    public int getPeriod_particle() {
        return period_particle;
    }

    public boolean isWand_click_in_air() {
        return wand_click_in_air;
    }

    public boolean getDefault_on_the_fly_selection() {
        return default_on_the_fly_selection;
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

    public boolean getShareClipboardSaved() {
        return share_clipboard_saved;
    }

    public int getTimelapse_max_block_per_tick() {
        return timelapse_max_block_per_tick;
    }
    public int getMaxTimelapseInSameTime() {
        return max_timelapse_in_same_time;
    }

    public boolean getSendMessageBuilderRegister() {
        return send_message_builder_register;
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




    private boolean getFileIsUpToDate() {

        //Convert old config version with no version information to new file config version
        try {
            Objects.requireNonNull(this.yml.getString("build.version"));

            this.version = this.yml.getString("build.version");

            return !this.version.equals(Main.getDataPlugin().getLatestConfigVersion());

        } catch (NullPointerException ignorred) {
            yml.set("build.version", "1.18.1.6");
            this.version = "1.18.1.6";

            return true;

        }
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
            //No modification of the configuration file

            yml.set("build.version", "1.18.1.11");
            this.version = "1.18.1.11";

            yml.save(file);

        }

        if (this.version.equals("1.18.1.11")) {
            //Update config file from 1.18.1.11 to 1.18.1.12

            //No modification of the configuration file

            yml.set("build.version", "1.18.1.12");
            this.version = "1.18.1.12";

            yml.save(file);

        }

        if (this.version.equals("1.18.1.12")) {
            //Update config file from 1.18.1.12 to 1.18.1.13

            //No modification of the configuration file

            yml.set("build.version", "1.18.1.13");
            this.version = "1.18.1.13";

            yml.save(file);
        }

        if (this.version.equals("1.18.1.13")) {
            //Update config file from 1.18.1.13 to 1.18.1.14

            //No modification of the configuration file

            yml.set("build.version", "1.18.1.14");
            this.version = "1.18.1.14";

            yml.save(file);
        }

        if (this.version.equals("1.18.1.14")) {
            //Update config file from 1.18.1.14 to 1.18.1.15

            //No modification of the configuration file

            yml.set("build.version", "1.18.1.15");
            this.version = "1.18.1.15";

            yml.save(file);
        }

        if (this.version.equals("1.18.1.15")) {
            //Update config file from 1.18.1.15 to 1.18.1.16

            //No modification of the configuration file

            yml.set("build.version", "1.18.1.16");
            this.version = "1.18.1.16";

            yml.save(file);
        }

        if (this.version.equals("1.18.1.16")) {
            //Update config file from 1.18.1.16 to 1.18.1.17

            // Bezier particle
            yml.set("build.particle_bezier_curve_type", Particle.FLAME.name());
            yml.set("build.coefficient_particle_number", 3);

            // Line particle
            yml.set("build.display_convex_line", true);
            yml.set("build.particle_convex_type_line", Particle.FLAME.name());
            yml.set("build.spacing_between_particles", 1);

            yml.set("build.period_particle", 10);

            yml.set("build.timelapse_max_block_per_tick", 40);
            yml.set("build.max_timelapse_in_same_time", 2);

            yml.set("build.version", "1.18.1.17");
            this.version = "1.18.1.17";

            yml.save(file);

            //for next update
        }

        if (this.version.equals("1.18.1.17")) {
            //Update config file from 1.18.1.17 to 1.18.1.18

            yml.set("build.version", "1.18.1.18");
            this.version = "1.18.1.18";

            yml.save(file);
        }

        if (this.version.equals("1.18.1.18")) {
            //Update config file from 1.18.1.18 to 1.18.1.19

            yml.set("build.version", "1.18.1.19");
            this.version = "1.18.1.19";

            yml.save(file);

        }

        if (this.version.equals("1.18.1.19")) {
            //Update config file from 1.18.1.19 to 1.18.1.20

            yml.set("build.version", "1.18.1.20");
            this.version = "1.18.1.20";

            yml.save(file);

        }

        if (this.version.equals("1.18.1.20")) {
            //Update config file from 1.18.1.20 to 1.18.1.21

            yml.set("build.version", "1.18.1.21");
            this.version = "1.18.1.21";

            yml.save(file);
        }

        if (this.version.equals("1.18.1.21")) {
            //Update config file from 1.18.1.21 to 1.18.1.22

            yml.set("build.version", "1.18.1.22");
            this.version = "1.18.1.22";

            yml.save(file);
        }

        if (this.version.equals("1.18.1.22")) {
            //Update config file from 1.18.1.22 to 1.18.1.23

            // Send message builder registered
            yml.set("build.send_message_builder_register", true);

            yml.set("build.version", "23");
            this.version = "23";

            yml.save(file);

            //for next update
        }

        if (this.version.equals("23")) {
            //Update config file from 23 to 24

            // Prefix en config
            yml.set("build.prefix", "§8[§5§oEXP-Build§8] §l>§l§7 ");
            yml.set("build.share_clipboard_saved", false);

            yml.set("build.version", "24");
            this.version = "24";

            yml.save(file);

            //for next update
        }

        if (this.version.equals("24")) {
            //Update config file from 24 to 25

            // Default player on the fly selection mode
            yml.set("build.default_on_the_fly_selection", true);

            yml.set("build.version", "25");
            this.version = "25";

            yml.save(file);
            //for next update
        }

        if (this.version.equals("25")) {
            //Update config file from 25 to 26

            //for next update
        }

        log.info("File config update");
    }
}