package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

public class MessageBuilder {

    private final FileConfiguration yml;
    private final Configuration config = Main.configuration();
    private final Logger log = Logger.getLogger("Expert-Build");

    private File langfile;

    private StringBuilder pluginEnable;
    private StringBuilder pluginDisable;

    private StringBuilder listenersLoad;
    private StringBuilder commandsLoad;
    private StringBuilder configLoad;
    private StringBuilder guiLoad;
    private StringBuilder brushLoad;
    private String schematicTransfertFile;

    private StringBuilder checkingUpdate;
    private StringBuilder notNewUpdate;
    private String newUpdateAvailable;
    private String unableCheckUpdate;

    private StringBuilder day;
    private StringBuilder hour;
    private StringBuilder minute;
    private StringBuilder second;


    private String brushEnable;
    private String brushEnableWithRadius;
    private String brushEnableWithMaterial;
    private String brushEnableWithRadiusPattern;
    private StringBuilder brushDisable;
    private String brushRegistered;
    private StringBuilder materialSet;
    private StringBuilder radiusSet;

    private String playerRegistered;
    private StringBuilder builderProfileRegistered;
    private StringBuilder playerAlreadyRegistered;

    private String pointAdd;
    private StringBuilder pointNotSave;


    private StringBuilder dontPerm;
    private StringBuilder consoleNotExecuteCmd;
    private String noPermissionNode;


    private String setPos1;
    private String setPos1WithArea;
    private String setPos2;
    private String setPos2WithArea;
    private String addVertexPos;
    private StringBuilder selectionClear;
    private String setSelection;

    private String blockModified;
    private String blockModifiedWithTime;

    private String playerLogCommand;

    private String copyBlock;


    private String errorRegion;
    private String errorSelection;
    private StringBuilder errorIncompleteSelection;
    private String errorBrushbuilder;
    private StringBuilder errorJschException;
    private StringBuilder errorSftpException;
    private StringBuilder fileConfigurationError;
    private StringBuilder incompleteSelection;
    private StringBuilder invalidNumber;
    private StringBuilder invalidNumberInteger;
    private StringBuilder invalidNumberIntegerUpper0;
    private StringBuilder invalidMaterial;
    private String invalidMaterialSet;
    private StringBuilder invalidBiome;
    private String invalidBiomeSet;
    private String invalidInstance;
    private String invalidArgument;
    private String invalidArgumentInteger;


    private String giveTool;


    private String use;
    private StringBuilder pregeneration;
    private StringBuilder generate;

    private String fileNotExist;
    private StringBuilder fileTooLarge;
    private StringBuilder serverOff;
    private StringBuilder unknownServer;
    private StringBuilder transfert;
    private StringBuilder dontRestart;
    private String succesTransfert;
    private String transfertLog;

    private String enable;
    private String disable;
    private StringBuilder allClipboardDelete;
    private String clipboardDoesNotExist;
    private String clipboardAlreadyExist;
    private String clipboardAddAndEnable;
    private String clipboardRemove;


    private String timelapse_already_running;
    private String timelapse_finish_recap;
    private String timelapse_estimate_time;
    private String timelapse_stopped;
    private String timelapse_no_in_progress;
    private StringBuilder too_many_timelapses;


    private StringBuilder back;
    private StringBuilder exit;
    private StringBuilder main_gui_title;
    private StringBuilder main_item_1;
    private StringBuilder main_item_2;
    private StringBuilder main_item_3;

    private StringBuilder leather_gui_title;
    private StringBuilder leather_item;
    private StringBuilder leather_helmet;
    private StringBuilder leather_chestplate;
    private StringBuilder leather_leggings;
    private StringBuilder leather_boots;
    private StringBuilder red_color;
    private StringBuilder green_color;
    private StringBuilder blue_color;
    private String red;
    private String green;
    private String blue;

    private StringBuilder organic_gui_title;
    private StringBuilder organic_item;
    private StringBuilder pitch_angle_conf;
    private StringBuilder yaw_angle_conf;
    private String click_change_angle;
    private StringBuilder click_enable_disable;
    private StringBuilder clickPregen;
    private StringBuilder clickGenerate;
    private StringBuilder clickClearParticle;

    private StringBuilder member_conf;
    private StringBuilder height;
    private String organic_height;
    private StringBuilder interact_member;
    private String switch_member;
    private String pitch_level;
    private String yaw_level;

    private StringBuilder flowerGuiTitle;
    private StringBuilder flowerItem;
    private StringBuilder brushEnable2;
    private StringBuilder brushDisable2;
    private StringBuilder radiusText;
    private String radiusValue;
    private StringBuilder airText;
    private String airValue;
    private String total;
    private String rightArrow;
    private StringBuilder clickForChange;
    private String propertyKey;
    private String valuePropertykey;
    private StringBuilder clickForChangeProperty;


    public MessageBuilder() throws URISyntaxException {

        this.yml = loadLanguageFile();

        if (! deleteLangFile()) log.severe("Error, Error on language file, Unable to delete file correctly; report to dev !");
    }

    public MessageBuilder loadConfiguration() {

        try {

            this.pluginEnable = new StringBuilder(this.yml.getString("expbuild.message.main.plugin_enable"));
            this.pluginDisable = new StringBuilder(this.yml.getString("expbuild.message.main.plugin_disable"));

            this.listenersLoad = new StringBuilder(this.yml.getString("expbuild.message.main.listeners_load"));
            this.commandsLoad = new StringBuilder(this.yml.getString("expbuild.message.main.commands_load"));
            this.configLoad = new StringBuilder(this.yml.getString("expbuild.message.main.config_load"));
            this.guiLoad = new StringBuilder(this.yml.getString("expbuild.message.main.gui_load"));
            this.brushLoad = new StringBuilder(this.yml.getString("expbuild.message.main.brush_load"));
            this.schematicTransfertFile = this.yml.getString("expbuild.message.main.schematic_transfert_file");

            this.checkingUpdate = new StringBuilder(this.yml.getString("expbuild.message.main.checking_update"));
            this.notNewUpdate = new StringBuilder(this.yml.getString("expbuild.message.main.not_new_update"));
            this.newUpdateAvailable = this.yml.getString("expbuild.message.main.new_update_available");
            this.unableCheckUpdate = this.yml.getString("expbuild.message.main.unable_check_update");

            this.day = new StringBuilder(this.yml.getString("expbuild.message.main.day"));
            this.hour = new StringBuilder(this.yml.getString("expbuild.message.main.hour"));
            this.minute = new StringBuilder(this.yml.getString("expbuild.message.main.minute"));
            this.second = new StringBuilder(this.yml.getString("expbuild.message.main.second"));


            this.brushEnable = this.yml.getString("expbuild.message.brush.brush_enable");
            this.brushEnableWithRadius = this.yml.getString("expbuild.message.brush.brush_enable_with_radius");
            this.brushEnableWithMaterial = this.yml.getString("expbuild.message.brush.brush_enable_with_material");
            this.brushEnableWithRadiusPattern = this.yml.getString("expbuild.message.brush.brush_enable_with_radius_pattern");
            this.brushDisable = new StringBuilder(this.yml.getString("expbuild.message.brush.brush_disable"));
            this.brushRegistered = this.yml.getString("expbuild.message.brush.brush_registered");
            this.materialSet = new StringBuilder(this.yml.getString("expbuild.message.brush.material_set"));
            this.radiusSet = new StringBuilder(this.yml.getString("expbuild.message.brush.radius_set"));


            this.playerRegistered = this.yml.getString("expbuild.message.brush.player_registered");
            this.builderProfileRegistered = new StringBuilder(this.yml.getString("expbuild.message.brush.builder_profile_registered"));
            this.playerAlreadyRegistered = new StringBuilder(this.yml.getString("expbuild.message.brush.player_already_registered"));

            this.pointAdd = this.yml.getString("expbuild.message.brush.point_add");
            this.pointNotSave = new StringBuilder(this.yml.getString("expbuild.message.brush.point_not_save"));


            this.dontPerm = new StringBuilder(this.yml.getString("expbuild.message.permission.dont_perm"));
            this.consoleNotExecuteCmd = new StringBuilder(this.yml.getString("expbuild.message.permission.console_not_execute_cmd"));
            this.noPermissionNode = this.yml.getString("expbuild.message.permission.no_permission_node");


            this.setPos1 = this.yml.getString("expbuild.message.selection.set_pos_1");
            this.setPos1WithArea = this.yml.getString("expbuild.message.selection.set_pos_1_with_area");
            this.setPos2 = this.yml.getString("expbuild.message.selection.set_pos_2");
            this.setPos2WithArea = this.yml.getString("expbuild.message.selection.set_pos_2_with_area");
            this.addVertexPos = this.yml.getString("expbuild.message.selection.add_vertex_pos");
            this.selectionClear = new StringBuilder(this.yml.getString("expbuild.message.selection.selection_clear"));
            this.setSelection = this.yml.getString("expbuild.message.selection.set_selection");

            this.blockModified = this.yml.getString("expbuild.message.selection.block_modified");
            this.blockModifiedWithTime = this.yml.getString("expbuild.message.selection.block_modified_with_time");

            this.playerLogCommand = this.yml.getString("expbuild.message.selection.player_log_command");

            this.copyBlock = this.yml.getString("expbuild.message.selection.copy_block");

            this.errorRegion = this.yml.getString("expbuild.message.error.error_region");
            this.errorSelection = this.yml.getString("expbuild.message.error.error_selection");
            this.errorIncompleteSelection = new StringBuilder(this.yml.getString("expbuild.message.error.error_incomplete_selection"));
            this.errorBrushbuilder = this.yml.getString("expbuild.message.error.error_brushbuilder");
            this.errorJschException = new StringBuilder(this.yml.getString("expbuild.message.error.error_jsch_exception"));
            this.errorSftpException = new StringBuilder(this.yml.getString("expbuild.message.error.error_sftp_exception"));
            this.fileConfigurationError = new StringBuilder(this.yml.getString("expbuild.message.error.file_configuration_error"));
            this.incompleteSelection = new StringBuilder(this.yml.getString("expbuild.message.error.incomplete_selection"));
            this.invalidNumber = new StringBuilder(this.yml.getString("expbuild.message.error.invalid_number"));
            this.invalidNumberInteger = new StringBuilder(this.yml.getString("expbuild.message.error.invalid_number_integer"));
            this.invalidNumberIntegerUpper0 = new StringBuilder(this.yml.getString("expbuild.message.error.invalid_number_integer_upper_0"));
            this.invalidMaterial = new StringBuilder(this.yml.getString("expbuild.message.error.invalid_material"));
            this.invalidMaterialSet = this.yml.getString("expbuild.message.error.invalid_material_set");
            this.invalidBiome = new StringBuilder(this.yml.getString("expbuild.message.error.invalid_biome"));
            this.invalidBiomeSet = this.yml.getString("expbuild.message.error.invalid_biome_set");
            this.invalidInstance = this.yml.getString("expbuild.message.error.invalid_instance");
            this.invalidArgument = this.yml.getString("expbuild.message.error.invalid_argument");
            this.invalidArgumentInteger = this.yml.getString("expbuild.message.error.invalid_argument_integer");



            this.giveTool = this.yml.getString("expbuild.message.tools.give_tool");


            this.use = this.yml.getString("expbuild.message.commands.use");
            this.pregeneration = new StringBuilder(this.yml.getString("expbuild.message.commands.pregeneration"));
            this.generate = new StringBuilder(this.yml.getString("expbuild.message.commands.generate"));


            this.fileNotExist = this.yml.getString("expbuild.message.commands.file_not_exist");
            this.fileTooLarge = new StringBuilder(this.yml.getString("expbuild.message.commands.file_too_large"));
            this.serverOff = new StringBuilder(this.yml.getString("expbuild.message.commands.server_off"));
            this.unknownServer = new StringBuilder(this.yml.getString("expbuild.message.commands.unknown_server"));
            this.transfert = new StringBuilder(this.yml.getString("expbuild.message.commands.transfert"));
            this.dontRestart = new StringBuilder(this.yml.getString("expbuild.message.commands.dont_restart"));
            this.succesTransfert = this.yml.getString("expbuild.message.commands.succes_transfert");
            this.transfertLog = this.yml.getString("expbuild.message.commands.transfert_log");

            this.enable = this.yml.getString("expbuild.message.commands.enable");
            this.disable = this.yml.getString("expbuild.message.commands.disable");
            this.allClipboardDelete = new StringBuilder(this.yml.getString("expbuild.message.commands.all_clipboards_delete"));
            this.clipboardDoesNotExist = this.yml.getString("expbuild.message.commands.clipboard_does_not_exist");
            this.clipboardAlreadyExist = this.yml.getString("expbuild.message.commands.clipboard_already_exist");
            this.clipboardAddAndEnable = this.yml.getString("expbuild.message.commands.clipboard_add_and_enable");
            this.clipboardRemove = this.yml.getString("expbuild.message.commands.clipboard_remove");

            this.timelapse_already_running = this.yml.getString("expbuild.message.commands.timelapse_already_running");
            this.timelapse_finish_recap = this.yml.getString("expbuild.message.commands.timelapse_finish_recap");
            this.timelapse_estimate_time = this.yml.getString("expbuild.message.commands.timelapse_estimate_time");
            this.timelapse_stopped = this.yml.getString("expbuild.message.commands.timelapse_stopped");
            this.timelapse_no_in_progress = this.yml.getString("expbuild.message.commands.timelapse_no_in_progress");
            this.too_many_timelapses = new StringBuilder(this.yml.getString("expbuild.message.commands.too_many_timelapses"));



            this.back = new StringBuilder(this.yml.getString("expbuild.message.gui.back"));
            this.exit = new StringBuilder(this.yml.getString("expbuild.message.gui.exit"));
            this.main_gui_title = new StringBuilder(this.yml.getString("expbuild.message.gui.main_gui_title"));
            this.main_item_1 = new StringBuilder(this.yml.getString("expbuild.message.gui.main_item_1"));
            this.main_item_2 = new StringBuilder(this.yml.getString("expbuild.message.gui.main_item_2"));
            this.main_item_3 = new StringBuilder(this.yml.getString("expbuild.message.gui.main_item_3"));

            this.leather_gui_title = new StringBuilder(this.yml.getString("expbuild.message.gui.leather_gui_title"));
            this.leather_item = new StringBuilder(this.yml.getString("expbuild.message.gui.leather_item"));
            this.leather_helmet = new StringBuilder(this.yml.getString("expbuild.message.gui.leather_helmet"));
            this.leather_chestplate = new StringBuilder(this.yml.getString("expbuild.message.gui.leather_chestplate"));
            this.leather_leggings = new StringBuilder(this.yml.getString("expbuild.message.gui.leather_leggings"));
            this.leather_boots = new StringBuilder(this.yml.getString("expbuild.message.gui.leather_boots"));
            this.red_color = new StringBuilder(this.yml.getString("expbuild.message.gui.red_color"));
            this.green_color = new StringBuilder(this.yml.getString("expbuild.message.gui.green_color"));
            this.blue_color = new StringBuilder(this.yml.getString("expbuild.message.gui.blue_color"));
            this.red = this.yml.getString("expbuild.message.gui.red");
            this.green = this.yml.getString("expbuild.message.gui.green");
            this.blue = this.yml.getString("expbuild.message.gui.blue");

            this.organic_gui_title = new StringBuilder(this.yml.getString("expbuild.message.gui.organic_gui_title"));
            this.organic_item = new StringBuilder(this.yml.getString("expbuild.message.gui.organic_item"));
            this.pitch_angle_conf = new StringBuilder(this.yml.getString("expbuild.message.gui.pitch_angle_conf"));
            this.yaw_angle_conf = new StringBuilder(this.yml.getString("expbuild.message.gui.yaw_angle_conf"));
            this.click_change_angle = this.yml.getString("expbuild.message.gui.click_change_angle");
            this.click_enable_disable = new StringBuilder(this.yml.getString("expbuild.message.gui.click_enable_disable"));
            this.clickPregen = new StringBuilder(this.yml.getString("expbuild.message.gui.click_pregen"));
            this.clickGenerate = new StringBuilder(this.yml.getString("expbuild.message.gui.click_generate"));
            this.clickClearParticle = new StringBuilder(this.yml.getString("expbuild.message.gui.click_clear_particle"));
            this.member_conf = new StringBuilder(this.yml.getString("expbuild.message.gui.member_conf"));
            this.height = new StringBuilder(this.yml.getString("expbuild.message.gui.height"));
            this.organic_height = this.yml.getString("expbuild.message.gui.organic_height");
            this.interact_member = new StringBuilder(this.yml.getString("expbuild.message.gui.interact_member"));
            this.switch_member = this.yml.getString("expbuild.message.gui.switch_member");
            this.pitch_level = this.yml.getString("expbuild.message.gui.pitch_level");
            this.yaw_level = this.yml.getString("expbuild.message.gui.yaw_level");

            this.flowerGuiTitle = new StringBuilder(this.yml.getString("expbuild.message.gui.flower_gui_title"));
            this.flowerItem = new StringBuilder(this.yml.getString("expbuild.message.gui.flower_item"));
            this.brushEnable2 = new StringBuilder(this.yml.getString("expbuild.message.gui.brush_enable"));
            this.brushDisable2 = new StringBuilder(this.yml.getString("expbuild.message.gui.brush_disable"));
            this.radiusText = new StringBuilder(this.yml.getString("expbuild.message.gui.radius_text"));
            this.radiusValue = this.yml.getString("expbuild.message.gui.radius_value");
            this.airText = new StringBuilder(this.yml.getString("expbuild.message.gui.air_text"));
            this.airValue = this.yml.getString("expbuild.message.gui.air_value");
            this.total = this.yml.getString("expbuild.message.gui.total");
            this.rightArrow = this.yml.getString("expbuild.message.gui.right_arrow");
            this.clickForChange = new StringBuilder(this.yml.getString("expbuild.message.gui.click_for_change"));
            this.propertyKey = this.yml.getString("expbuild.message.gui.property_key");
            this.valuePropertykey = this.yml.getString("expbuild.message.gui.value_propertykey");
            this.clickForChangeProperty = new StringBuilder(this.yml.getString("expbuild.message.gui.click_for_change_property"));

        } catch (NullPointerException | IllegalStateException e) {

            log.severe("Configuration files in ExpertBuild folder is wrong or corrupt " +
                    "repair the file or delete all the files, so that the plugin recreates them");

        }
        return this;
    }

    public StringBuilder getPluginEnable() {
        return pluginEnable;
    }

    public StringBuilder getPluginDisable() {
        return pluginDisable;
    }

    public StringBuilder getListenersLoad() {
        return listenersLoad;
    }

    public StringBuilder getCommandsLoad() {
        return commandsLoad;
    }

    public StringBuilder getConfigLoad() {
        return configLoad;
    }

    public StringBuilder getGuiLoad() {
        return guiLoad;
    }

    public StringBuilder getBrushLoad() {
        return brushLoad;
    }

    public String getSchematicTransfertFile(String num) {
        return schematicTransfertFile.replace("'num'", num);
    }

    public StringBuilder getCheckingUpdate() {
        return checkingUpdate;
    }

    public StringBuilder getNotNewUpdate() {
        return notNewUpdate;
    }

    public String getNewUpdateAvailable(String v, String v1, String v2) {
        return newUpdateAvailable.replace("'v'", v).replace("'v1'", v1).replace("'v2'", v2);
    }

    public String getUnableCheckUpdate(String error) {
        return unableCheckUpdate.replace("'error'", error);
    }

    public StringBuilder day() {
        return day;
    }

    public StringBuilder hour() {
        return hour;
    }

    public StringBuilder minute() {
        return minute;
    }

    public StringBuilder second() {
        return second;
    }

    // BRUSH

    public String getBrushEnable(String brush) {
        return brushEnable.replace("'brush'", brush);
    }

    public String getBrushEnableWithRadius(String brush, String radius) {
        return brushEnableWithRadius.replace("'brush'", brush).replace("'radius'", radius);
    }

    public String getBrushEnableWithMaterial(String brush, String pattern) {
        return brushEnableWithMaterial.replace("'brush'", brush).replace("'pattern'", pattern);
    }

    public String getBrushEnableWithRadiusPattern(String brush, String radius, String pattern) {
        return brushEnableWithRadiusPattern.replace("'brush'", brush).replace("'radius'", radius).replace("'pattern'", pattern);
    }

    public StringBuilder getMaterialSet() {
        return materialSet;
    }

    public StringBuilder getRadiusSet() {
        return radiusSet;
    }

    public StringBuilder getBrushDisable() {
        return brushDisable;
    }

    public String getBrushRegistered(String brush) {
        return brushRegistered.replace("'brush'", brush);
    }

    public String getPlayerRegistered(String player) {
        return playerRegistered.replace("'player'", player);
    }

    public StringBuilder getBuilderProfileRegistered() {
        return builderProfileRegistered;
    }

    public StringBuilder getPlayerAlreadyRegistered() {
        return playerAlreadyRegistered;
    }

    public String getPointAdd(String point) {
        return pointAdd.replace("'point'", point);
    }

    public StringBuilder getPointNotSave() {
        return pointNotSave;
    }


    public StringBuilder getDontPerm() {
        return dontPerm;
    }

    public StringBuilder getConsoleNotExecuteCmd() {
        return consoleNotExecuteCmd;
    }

    public String getNoPermissionNode(String node) {
        return noPermissionNode.replace("'node'", node);
    }


    public String getSetPos1(String pos) {
        return setPos1.replace("'pos'", pos);
    }

    public String getSetPos1WithArea(String pos, String area) {
        return setPos1WithArea.replace("'pos'", pos).replace("'area'", area);
    }

    public String getSetPos2(String pos) {
        return setPos2.replace("'pos'", pos);
    }

    public String getSetPos2WithArea(String pos, String area) {
        return setPos2WithArea.replace("'pos'", pos).replace("'area'", area);
    }

    public String getAddVertexPos(String pos) {
        return addVertexPos.replace("'pos'", pos);
    }

    public StringBuilder getSelectionClear() {
        return selectionClear;
    }

    public String getSetSelection(String selection) {
        return setSelection.replace("'selection'", selection);
    }

    public String getBlockModified(String num) {
        return blockModified.replace("'num'", num);
    }

    public String getBlockModifiedWithTime(String num1, String num2) {
        return blockModifiedWithTime.replace("'num'", num1).replace("'num2'", num2);
    }

    public String getPlayerLogCommand(String player, String cmd) {
        return playerLogCommand.replace("'player'", player).replace("'cmd'", cmd);
    }

    public String getCopyBlock(String num) {
        return this.copyBlock.replace("'num'", num);
    }


    // Error
    public String getErrorRegion(String num, String error) {
        return errorRegion.replace("'num'", num).replace("'error'", error);
    }

    public String getErrorSelection(String selection) {
        return errorSelection.replace("'selection'", selection);
    }

    public StringBuilder getErrorIncompleteSelection() {
        return errorIncompleteSelection;
    }

    public String getErrorBrushbuilder(String player) {
        return errorBrushbuilder.replace("'player'", player);
    }

    public StringBuilder getErrorJschException() {
        return errorJschException;
    }

    public StringBuilder getErrorSftpException() {
        return errorSftpException;
    }

    public StringBuilder getFileConfigurationError() {
        return fileConfigurationError;
    }

    public StringBuilder getIncompleteSelection() {
        return incompleteSelection;
    }

    public StringBuilder getInvalidNumber() {
        return invalidNumber;
    }

    public StringBuilder getInvalidNumberInteger() {
        return invalidNumberInteger;
    }

    public StringBuilder getInvalidNumberIntegerUpper0() {
        return invalidNumberIntegerUpper0;
    }

    public StringBuilder getInvalidMaterial() {
        return invalidMaterial;
    }

    public String getInvalidMaterialSet(String pattern) {
        return invalidMaterialSet.replace("'pattern'", pattern);
    }

    public StringBuilder getInvalidBiome() {
        return invalidBiome;
    }

    public String getInvalidBiomeSet(String biome) {
        return invalidBiomeSet.replace("'biome'", biome);
    }

    public String getInvalidInstance(String sender) {
        return this.invalidInstance.replace("'sender'", sender);
    }

    public String getInvalidArgument(String arg, String type) {
        return this.invalidArgument.replace("'arg'", arg).replace("'type'", type);
    }

    public String getInvalidArgumentInteger(String value, String min, String max) {
        return this.invalidArgumentInteger.replace("'value'", value).replace("'min'", min).replace("'max'", max);
    }


    // TOOLS
    public String getGiveTool(String tool) {
        return giveTool.replace("'tool'", tool);
    }


    // COMMANDS
    public String getUse(String cmd) {
        return use.replace("'cmd'", cmd);
    }

    public StringBuilder getPregeneration() {
        return pregeneration;
    }

    public StringBuilder getGenerate() {
        return generate;
    }

    public String getFileNotExist(String file) {
        return fileNotExist.replace("'file'", file);
    }

    public StringBuilder getFileTooLarge() {
        return fileTooLarge;
    }

    public StringBuilder getServerOff() {
        return serverOff;
    }

    public StringBuilder getUnknownServer() {
        return unknownServer;
    }

    public StringBuilder getTransfert() {
        return transfert;
    }

    public StringBuilder getDontRestart() {
        return dontRestart;
    }

    public String getSuccesTransfert(String file, String server) {
        return succesTransfert.replace("'file'", file).replace("'server'", server);
    }

    public String getTransfertLog(String file, String player, String server) {
        return transfertLog.replace("'file'", file).replace("'player'", player).replace("'server'", server);
    }

    public String getEnable(String element) {
        return this.enable.replace("'element'", element);
    }

    public String getDisable(String element) {
        return this.disable.replace("'element'", element);
    }

    public StringBuilder getAllClipboardDelete() {
        return this.allClipboardDelete;
    }

    public String getClipboardDoesNotExist(String clipboard) {
        return this.clipboardDoesNotExist.replace("'clipboard'", clipboard);
    }

    public String getClipboardAlreadyExist(String clipboard) {
        return this.clipboardAlreadyExist.replace("'clipboard'", clipboard);
    }

    public String getClipboardAddAndEnable(String clipboard) {
        return this.clipboardAddAndEnable.replace("'clipboard'", clipboard);
    }

    public String getClipboardRemove(String clipboard) {
        return this.clipboardRemove.replace("'clipboard'", clipboard);
    }

    public String getTimelapseAlreadyRunning() {
        return this.timelapse_already_running;
    }

    public String getTimelapseFinishRecap(String quantity, String time, String unit) {
        return this.timelapse_finish_recap.replace("'quantity'", quantity).replace("'time'", time).replace("'unit'", unit);
    }

    public String getTimelapseEstimateTime(String quantity, String time, String unit) {
        return this.timelapse_estimate_time.replace("'quantity'", quantity).replace("'time'", time).replace("'unit'", unit);
    }

    public String getTimelapseStopped() {
        return this.timelapse_stopped;
    }

    public String getTimelapseNoInProgress() {
        return this.timelapse_no_in_progress;
    }

    public StringBuilder getTooManyTimelapses() {
        return this.too_many_timelapses;
    }

    // GUI

    public StringBuilder getBack() {
        return back;
    }

    public StringBuilder getExit() {
        return exit;
    }

    public StringBuilder getMainGuiTitle() {
        return main_gui_title;
    }

    public StringBuilder getMainItem1() {
        return main_item_1;
    }

    public StringBuilder getMainItem2() {
        return main_item_2;
    }

    public StringBuilder getMainItem3() {
        return main_item_3;
    }

    public StringBuilder getLeatherGuiTitle() {
        return leather_gui_title;
    }

    public StringBuilder getLeatherItem() {
        return leather_item;
    }

    public StringBuilder getLeatherHelmet() {
        return leather_helmet;
    }

    public StringBuilder getLeatherChestplate() {
        return leather_chestplate;
    }

    public StringBuilder getLeatherLeggings() {
        return leather_leggings;
    }

    public StringBuilder getLeatherBoots() {
        return leather_boots;
    }

    public StringBuilder getRedColor() {
        return red_color;
    }

    public StringBuilder getGreenColor() {
        return green_color;
    }

    public StringBuilder getBlueColor() {
        return blue_color;
    }

    public String getRed(String level) {
        return red.replace("'level'", level);
    }

    public String getGreen(String level) {
        return green.replace("'level'", level);
    }

    public String getBlue(String level) {
        return blue.replace("'level'", level);
    }

    public StringBuilder getOrganicGuiTitle() {
        return organic_gui_title;
    }

    public StringBuilder getOrganicItem() {
        return organic_item;
    }

    public StringBuilder getPitchAngleConf() {
        return pitch_angle_conf;
    }

    public StringBuilder getYawAngleConf() {
        return yaw_angle_conf;
    }

    public String getClickChangeAngle(String type) {
        return click_change_angle.replace("'type'", type);
    }

    public StringBuilder getClickEnableDisable() {
        return click_enable_disable;
    }

    public StringBuilder getClickPregen() {
        return this.clickPregen;
    }

    public StringBuilder getClickGenerate() {
        return this.clickGenerate;
    }

    public StringBuilder getClickClearParticle() {
        return this.clickClearParticle;
    }

    public StringBuilder getMemberConf() {
        return member_conf;
    }

    public StringBuilder getHeight() {
        return height;
    }

    public String getOrganicHeight(String value) {
        return organic_height.replace("'value'", value);
    }

    public StringBuilder getInteractMember() {
        return interact_member;
    }

    public String getSwitchMember(String type, String state) {
        return switch_member.replace("'type'", type).replace("'state'", state);
    }

    public String getPitchLevel(String level) {
        return pitch_level.replace("'level'", level);
    }

    public String getYawLevel(String level) {
        return yaw_level.replace("'level'", level);
    }


    public StringBuilder getFlowerGuiTitle() {
        return flowerGuiTitle;
    }

    public StringBuilder getFlowerItem() {
        return flowerItem;
    }

    public StringBuilder getBrushEnable2() {
        return brushEnable2;
    }

    public StringBuilder getBrushDisable2() {
        return brushDisable2;
    }

    public StringBuilder getRadiusText() {
        return radiusText;
    }

    public String getRadiusValue(String value) {
        return radiusValue.replace("'value'", value);
    }

    public StringBuilder getAirText() {
        return airText;
    }

    public String getAirValue(String value) {
        return airValue.replace("'value'", value);
    }

    public String getTotal(String value) {
        return total.replace("'value'", value);
    }

    public String getRightArrow(String value) {
        return rightArrow.replace("'value'", value);
    }

    public StringBuilder getClickForChange() {
        return clickForChange;
    }

    public String getPropertyKey(String value) {
        return propertyKey.replace("'value'", value);
    }

    public String getValuePropertykey(String value) {
        return valuePropertykey.replace("'value'", value);
    }

    public StringBuilder getClickForChangeProperty() {
        return clickForChangeProperty;
    }

    @Override
    public String toString() {
        return "MessageBuilder{" +
                "yml=" + yml +
                ", config=" + config +
                ", pluginEnable='" + pluginEnable + '\'' +
                ", pluginDisable='" + pluginDisable + '\'' +
                ", listenersLoad='" + listenersLoad + '\'' +
                ", commandsLoad='" + commandsLoad + '\'' +
                ", configLoad='" + configLoad + '\'' +
                ", guiLoad='" + guiLoad + '\'' +
                ", brushLoad='" + brushLoad + '\'' +
                ", schematicTransfertFile='" + schematicTransfertFile + '\'' +
                ", checkingUpdate='" + checkingUpdate + '\'' +
                ", notNewUpdate='" + notNewUpdate + '\'' +
                ", newUpdateAvailable='" + newUpdateAvailable + '\'' +
                ", unableCheckUpdate='" + unableCheckUpdate + '\'' +
                ", brushEnable='" + brushEnable + '\'' +
                ", brushEnableWithRadius='" + brushEnableWithRadius + '\'' +
                ", brushEnableWithMaterial='" + brushEnableWithMaterial + '\'' +
                ", brushEnableWithRadiusPattern='" + brushEnableWithRadiusPattern + '\'' +
                ", brushDisable='" + brushDisable + '\'' +
                ", brushRegistered='" + brushRegistered + '\'' +
                ", materialSet='" + materialSet + '\'' +
                ", radiusSet='" + radiusSet + '\'' +
                ", playerRegistered='" + playerRegistered + '\'' +
                ", builderProfileRegistered='" + builderProfileRegistered + '\'' +
                ", playerAlreadyRegistered='" + playerAlreadyRegistered + '\'' +
                ", pointAdd='" + pointAdd + '\'' +
                ", pointNotSave='" + pointNotSave + '\'' +
                ", dontPerm='" + dontPerm + '\'' +
                ", consoleNotExecuteCmd='" + consoleNotExecuteCmd + '\'' +
                ", setPos1='" + setPos1 + '\'' +
                ", setPos1WithArea='" + setPos1WithArea + '\'' +
                ", setPos2='" + setPos2 + '\'' +
                ", setPos2WithArea='" + setPos2WithArea + '\'' +
                ", addVertexPos='" + addVertexPos + '\'' +
                ", selectionClear='" + selectionClear + '\'' +
                ", setSelection='" + setSelection + '\'' +
                ", blockModified='" + blockModified + '\'' +
                ", blockModifiedWithTime='" + blockModifiedWithTime + '\'' +
                ", errorRegion='" + errorRegion + '\'' +
                ", errorSelection='" + errorSelection + '\'' +
                ", errorBrushbuilder='" + errorBrushbuilder + '\'' +
                ", errorJschException='" + errorJschException + '\'' +
                ", errorSftpException='" + errorSftpException + '\'' +
                ", fileConfigurationError='" + fileConfigurationError + '\'' +
                ", incompleteSelection='" + incompleteSelection + '\'' +
                ", invalidNumber='" + invalidNumber + '\'' +
                ", invalidNumberInteger='" + invalidNumberInteger + '\'' +
                ", invalidNumberIntegerUpper0='" + invalidNumberIntegerUpper0 + '\'' +
                ", invalidMaterial='" + invalidMaterial + '\'' +
                ", invalidMaterialSet='" + invalidMaterialSet + '\'' +
                ", invalidBiome='" + invalidBiome + '\'' +
                ", invalidBiomeSet='" + invalidBiomeSet + '\'' +
                ", giveTool='" + giveTool + '\'' +
                ", use='" + use + '\'' +
                ", pregeneration='" + pregeneration + '\'' +
                ", fileNotExist='" + fileNotExist + '\'' +
                ", fileTooLarge='" + fileTooLarge + '\'' +
                ", serverOff='" + serverOff + '\'' +
                ", unknownServer='" + unknownServer + '\'' +
                ", transfert='" + transfert + '\'' +
                ", dontRestart='" + dontRestart + '\'' +
                ", succesTransfert='" + succesTransfert + '\'' +
                ", transfertLog='" + transfertLog + '\'' +
                ", back='" + back + '\'' +
                ", exit='" + exit + '\'' +
                ", main_gui_title='" + main_gui_title + '\'' +
                ", main_item_1='" + main_item_1 + '\'' +
                ", main_item_2='" + main_item_2 + '\'' +
                ", main_item_3='" + main_item_3 + '\'' +
                ", leather_gui_title='" + leather_gui_title + '\'' +
                ", leather_item='" + leather_item + '\'' +
                ", leather_helmet='" + leather_helmet + '\'' +
                ", leather_chestplate='" + leather_chestplate + '\'' +
                ", leather_leggings='" + leather_leggings + '\'' +
                ", leather_boots='" + leather_boots + '\'' +
                ", red_color='" + red_color + '\'' +
                ", green_color='" + green_color + '\'' +
                ", blue_color='" + blue_color + '\'' +
                ", red='" + red + '\'' +
                ", green='" + green + '\'' +
                ", blue='" + blue + '\'' +
                ", organic_gui_title='" + organic_gui_title + '\'' +
                ", organic_item='" + organic_item + '\'' +
                ", pitch_angle_conf='" + pitch_angle_conf + '\'' +
                ", yaw_angle_conf='" + yaw_angle_conf + '\'' +
                ", click_change_angle='" + click_change_angle + '\'' +
                ", click_enable_disable='" + click_enable_disable + '\'' +
                ", clickPregen='" + clickPregen + '\'' +
                ", clickGenerate='" + clickGenerate + '\'' +
                ", clickClearParticle='" + clickClearParticle + '\'' +
                ", member_conf='" + member_conf + '\'' +
                ", height='" + height + '\'' +
                ", organic_height='" + organic_height + '\'' +
                ", interact_member='" + interact_member + '\'' +
                ", switch_member='" + switch_member + '\'' +
                ", pitch_level='" + pitch_level + '\'' +
                ", yaw_level='" + yaw_level + '\'' +
                ", flowerGuiTitle='" + flowerGuiTitle + '\'' +
                ", flowerItem='" + flowerItem + '\'' +
                ", brushEnable2='" + brushEnable2 + '\'' +
                ", brushDisable2='" + brushDisable2 + '\'' +
                ", radiusText='" + radiusText + '\'' +
                ", radiusValue='" + radiusValue + '\'' +
                ", airText='" + airText + '\'' +
                ", airValue='" + airValue + '\'' +
                ", total='" + total + '\'' +
                ", rightArrow='" + rightArrow + '\'' +
                ", clickForChange='" + clickForChange + '\'' +
                ", propertyKey='" + propertyKey + '\'' +
                ", clickForChangeProperty='" + clickForChangeProperty + '\'' +
                '}';
    }

    private @Nullable YamlConfiguration loadLanguageFile() throws URISyntaxException {

        Main main = Main.getInstance();

        try(JarFile jarFile = new JarFile(Paths.get(main.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).toString())) {
            Enumeration<JarEntry> jarFiles = jarFile.entries();

            while (jarFiles.hasMoreElements()) {

                JarEntry jarEntry = jarFiles.nextElement();

                if (jarEntry.getRealName().equalsIgnoreCase("fr/marodeur/expertbuild/api/lang/" + this.config.getLang() + ".yml")) {

                    File langFile = new File(main.getDataFolder() ,jarEntry.getName());
                    this.langfile = langFile;

                    if(!langFile.exists()) {
                        main.saveResource(jarEntry.getName(), false);

                    }

                    return YamlConfiguration.loadConfiguration(langFile);
                }
            }

            log.warning("Unable to find the language file, loading the default file: English");

            File langFile = new File(main.getDataFolder(), "fr/marodeur/expertbuild/api/lang/en.yml");
            this.langfile = langFile;

            if(!langFile.exists()) {
                main.saveResource("fr/marodeur/expertbuild/api/lang/en.yml", false);

            }

            return YamlConfiguration.loadConfiguration(langFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean deleteLangFile() {

        File file = this.langfile;

        if (file.exists()) return file.delete();

        return false;
    }
}