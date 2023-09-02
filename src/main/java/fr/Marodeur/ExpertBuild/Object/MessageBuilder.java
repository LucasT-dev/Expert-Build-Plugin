package fr.Marodeur.ExpertBuild.Object;

import fr.Marodeur.ExpertBuild.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.logging.Logger;

public class MessageBuilder {

    private final FileConfiguration yml;
    private final Configuration config = Main.getInstance().getConfig();
    private final Logger log = Logger.getLogger("Expert-Build");

    private String pluginEnable;
    private String pluginDisable;

    private String listenersLoad;
    private String commandsLoad;
    private String configLoad;
    private String guiLoad;
    private String brushLoad;
    private String schematicTransfertFile;

    private String checkingUpdate;
    private String notNewUpdate;
    private String newUpdateAvailable;
    private String unableCheckUpdate;


    private String brushEnable;
    private String brushEnableWithRadius;
    private String brushEnableWithMaterial;
    private String brushEnableWithRadiusPattern;
    private String brushDisable;
    private String brushRegistered;
    private String materialSet;
    private String radiusSet;

    private String playerRegistered;
    private String builderProfileRegistered;
    private String playerAlreadyRegistered;

    private String pointAdd;
    private String pointNotSave;


    private String dontPerm;
    private String consoleNotExecuteCmd;


    private String setPos1;
    private String setPos1WithArea;
    private String setPos2;
    private String setPos2WithArea;
    private String addVertexPos;
    private String selectionClear;
    private String setSelection;

    private String blockModified;
    private String blockModifiedWithTime;


    private String errorRegion;
    private String errorSelection;
    private String errorBrushbuilder;
    private String errorJschException;
    private String errorSftpException;
    private String fileConfigurationError;
    private String incompleteSelection;
    private String invalidNumber;
    private String invalidNumberInteger;
    private String invalidNumberIntegerUpper0;
    private String invalidMaterial;
    private String invalidMaterialSet;
    private String invalidBiome;
    private String invalidBiomeSet;


    private String giveTool;


    private String use;
    private String pregeneration;
    private String generate;

    private String fileNotExist;
    private String fileTooLarge;
    private String serverOff;
    private String unknownServer;
    private String transfert;
    private String dontRestart;
    private String succesTransfert;
    private String transfertLog;


    private String back;
    private String exit;
    private String main_gui_title;
    private String main_item_1;
    private String main_item_2;
    private String main_item_3;

    private String leather_gui_title;
    private String leather_item;
    private String leather_helmet;
    private String leather_chestplate;
    private String leather_leggings;
    private String leather_boots;
    private String red_color;
    private String green_color;
    private String blue_color;
    private String red;
    private String green;
    private String blue;

    private String organic_gui_title;
    private String organic_item;
    private String pitch_angle_conf;
    private String yaw_angle_conf;
    private String click_change_angle;
    private String click_enable_disable;
    private String clickPregen;
    private String clickGenerate;
    private String clickClearParticle;

    private String member_conf;
    private String height;
    private String organic_height;
    private String interact_member;
    private String switch_member;
    private String pitch_level;
    private String yaw_level;

    private String flowerGuiTitle;
    private String flowerItem;
    private String brushEnable2;
    private String brushDisable2;
    private String radiusText;
    private String radiusValue;
    private String airText;
    private String airValue;
    private String total;
    private String rightArrow;
    private String clickForChange;
    private String propertyKey;
    private String valuePropertykey;
    private String clickForChangeProperty;


    public MessageBuilder() {

        File file = new File("plugins/ExpertBuild/config.yml");
        this.yml = YamlConfiguration.loadConfiguration(file);
    }

    public MessageBuilder loadConfiguration() {

        String lang = config.getLang();

        try {

            this.pluginEnable = this.yml.getString("build.message." + lang + ".main.plugin_enable");
            this.pluginDisable = this.yml.getString("build.message." + lang + ".main.plugin_disable");

            this.listenersLoad = this.yml.getString("build.message." + lang + ".main.listeners_load");
            this.commandsLoad = this.yml.getString("build.message." + lang + ".main.commands_load");
            this.configLoad = this.yml.getString("build.message." + lang + ".main.config_load");
            this.guiLoad = this.yml.getString("build.message." + lang + ".main.gui_load");
            this.brushLoad = this.yml.getString("build.message." + lang + ".main.brush_load");
            this.schematicTransfertFile = this.yml.getString("build.message." + lang + ".main.schematic_transfert_file");

            this.checkingUpdate = this.yml.getString("build.message." + lang + ".main.checking_update");
            this.notNewUpdate = this.yml.getString("build.message." + lang + ".main.not_new_update");
            this.newUpdateAvailable = this.yml.getString("build.message." + lang + ".main.new_update_available");
            this.unableCheckUpdate = this.yml.getString("build.message." + lang + ".main.unable_check_update");


            this.brushEnable = this.yml.getString("build.message." + lang + ".brush.brush_enable");
            this.brushEnableWithRadius = this.yml.getString("build.message." + lang + ".brush.brush_enable_with_radius");
            this.brushEnableWithMaterial = this.yml.getString("build.message." + lang + ".brush.brush_enable_with_material");
            this.brushEnableWithRadiusPattern = this.yml.getString("build.message." + lang + ".brush.brush_enable_with_radius_pattern");
            this.brushDisable = this.yml.getString("build.message." + lang + ".brush.brush_disable");
            this.brushRegistered = this.yml.getString("build.message." + lang + ".brush.brush_registered");
            this.materialSet = this.yml.getString("build.message." + lang + ".brush.material_set");
            this.radiusSet = this.yml.getString("build.message." + lang + ".brush.radius_set");


            this.playerRegistered = this.yml.getString("build.message." + lang + ".brush.player_registered");
            this.builderProfileRegistered = this.yml.getString("build.message." + lang + ".brush.builder_profile_registered");
            this.playerAlreadyRegistered = this.yml.getString("build.message." + lang + ".brush.player_already_registered");

            this.pointAdd = this.yml.getString("build.message." + lang + ".brush.point_add");
            this.pointNotSave = this.yml.getString("build.message." + lang + ".brush.point_not_save");


            this.dontPerm = this.yml.getString("build.message." + lang + ".permission.dont_perm");
            this.consoleNotExecuteCmd = this.yml.getString("build.message." + lang + ".permission.console_not_execute_cmd");


            this.setPos1 = this.yml.getString("build.message." + lang + ".selection.set_pos_1");
            this.setPos1WithArea = this.yml.getString("build.message." + lang + ".selection.set_pos_1_with_area");
            this.setPos2 = this.yml.getString("build.message." + lang + ".selection.set_pos_2");
            this.setPos2WithArea = this.yml.getString("build.message." + lang + ".selection.set_pos_2_with_area");
            this.addVertexPos = this.yml.getString("build.message." + lang + ".selection.add_vertex_pos");
            this.selectionClear = this.yml.getString("build.message." + lang + ".selection.selection_clear");
            this.setSelection = this.yml.getString("build.message." + lang + ".selection.set_selection");

            this.blockModified = this.yml.getString("build.message." + lang + ".selection.block_modified");
            this.blockModifiedWithTime = this.yml.getString("build.message." + lang + ".selection.block_modified_with_time");


            this.errorRegion = this.yml.getString("build.message." + lang + ".error.error_region");
            this.errorSelection = this.yml.getString("build.message." + lang + ".error.error_selection");
            this.errorBrushbuilder = this.yml.getString("build.message." + lang + ".error.error_brushbuilder");
            this.errorJschException = this.yml.getString("build.message." + lang + ".error.error_jsch_exception");
            this.errorSftpException = this.yml.getString("build.message." + lang + ".error.error_sftp_exception");
            this.fileConfigurationError = this.yml.getString("build.message." + lang + ".error.file_configuration_error");
            this.incompleteSelection = this.yml.getString("build.message." + lang + ".error.incomplete_selection");
            this.invalidNumber = this.yml.getString("build.message." + lang + ".error.invalid_number");
            this.invalidNumberInteger = this.yml.getString("build.message." + lang + ".error.invalid_number_integer");
            this.invalidNumberIntegerUpper0 = this.yml.getString("build.message." + lang + ".error.invalid_number_integer_upper_0");
            this.invalidMaterial = this.yml.getString("build.message." + lang + ".error.invalid_material");
            this.invalidMaterialSet = this.yml.getString("build.message." + lang + ".error.invalid_material_set");
            this.invalidBiome = this.yml.getString("build.message." + lang + ".error.invalid_biome");
            this.invalidBiomeSet = this.yml.getString("build.message." + lang + ".error.invalid_biome_set");


            this.giveTool = this.yml.getString("build.message." + lang + ".tools.give_tool");


            this.use = this.yml.getString("build.message." + lang + ".commands.use");
            this.pregeneration = this.yml.getString("build.message." + lang + ".commands.pregeneration");
            this.generate = this.yml.getString("build.message." + lang + ".commands.generate");


            this.fileNotExist = this.yml.getString("build.message." + lang + ".commands.file_not_exist");
            this.fileTooLarge = this.yml.getString("build.message." + lang + ".commands.file_too_large");
            this.serverOff = this.yml.getString("build.message." + lang + ".commands.server_off");
            this.unknownServer = this.yml.getString("build.message." + lang + ".commands.unknown_server");
            this.transfert = this.yml.getString("build.message." + lang + ".commands.transfert");
            this.dontRestart = this.yml.getString("build.message." + lang + ".commands.dont_restart");
            this.succesTransfert = this.yml.getString("build.message." + lang + ".commands.succes_transfert");
            this.transfertLog = this.yml.getString("build.message." + lang + ".commands.transfert_log");

            this.back = this.yml.getString("build.message." + lang + ".gui.back");
            this.exit = this.yml.getString("build.message." + lang + ".gui.exit");
            this.main_gui_title = this.yml.getString("build.message." + lang + ".commands.main_gui_title");
            this.main_item_1 = this.yml.getString("build.message." + lang + ".gui.main_item_1");
            this.main_item_2 = this.yml.getString("build.message." + lang + ".gui.main_item_2");
            this.main_item_3 = this.yml.getString("build.message." + lang + ".gui.main_item_3");

            this.leather_gui_title = this.yml.getString("build.message." + lang + ".gui.leather_gui_title");
            this.leather_item = this.yml.getString("build.message." + lang + ".gui.leather_item");
            this.leather_helmet = this.yml.getString("build.message." + lang + ".gui.leather_helmet");
            this.leather_chestplate = this.yml.getString("build.message." + lang + ".gui.leather_chestplate");
            this.leather_leggings = this.yml.getString("build.message." + lang + ".gui.leather_leggings");
            this.leather_boots = this.yml.getString("build.message." + lang + ".gui.leather_boots");
            this.red_color = this.yml.getString("build.message." + lang + ".gui.red_color");
            this.green_color = this.yml.getString("build.message." + lang + ".gui.green_color");
            this.blue_color = this.yml.getString("build.message." + lang + ".gui.blue_color");
            this.red = this.yml.getString("build.message." + lang + ".gui.red");
            this.green = this.yml.getString("build.message." + lang + ".gui.green");
            this.blue = this.yml.getString("build.message." + lang + ".gui.blue");

            this.organic_gui_title = this.yml.getString("build.message." + lang + ".gui.organic_gui_title");
            this.organic_item = this.yml.getString("build.message." + lang + ".gui.organic_item");
            this.pitch_angle_conf = this.yml.getString("build.message." + lang + ".gui.pitch_angle_conf");
            this.yaw_angle_conf = this.yml.getString("build.message." + lang + ".gui.yaw_angle_conf");
            this.click_change_angle = this.yml.getString("build.message." + lang + ".gui.click_change_angle");
            this.click_enable_disable = this.yml.getString("build.message." + lang + ".gui.click_enable_disable");
            this.clickPregen = this.yml.getString("build.message." + lang + ".gui.click_pregen");
            this.clickGenerate = this.yml.getString("build.message." + lang + ".gui.click_generate");
            this.clickClearParticle = this.yml.getString("build.message." + lang + ".gui.click_clear_particle");
            this.member_conf = this.yml.getString("build.message." + lang + ".gui.member_conf");
            this.height = this.yml.getString("build.message." + lang + ".gui.height");
            this.organic_height = this.yml.getString("build.message." + lang + ".gui.organic_height");
            this.interact_member = this.yml.getString("build.message." + lang + ".gui.interact_member");
            this.switch_member = this.yml.getString("build.message." + lang + ".gui.switch_member");
            this.pitch_level = this.yml.getString("build.message." + lang + ".gui.pitch_level");
            this.yaw_level = this.yml.getString("build.message." + lang + ".gui.yaw_level");

            this.flowerGuiTitle = this.yml.getString("build.message." + lang + ".gui.flower_gui_title");
            this.flowerItem = this.yml.getString("build.message." + lang + ".gui.flower_item");
            this.brushEnable2 = this.yml.getString("build.message." + lang + ".gui.brush_enable");
            this.brushDisable2 = this.yml.getString("build.message." + lang + ".gui.brush_disable");
            this.radiusText = this.yml.getString("build.message." + lang + ".gui.radius_text");
            this.radiusValue = this.yml.getString("build.message." + lang + ".gui.radius_value");
            this.airText = this.yml.getString("build.message." + lang + ".gui.air_text");
            this.airValue = this.yml.getString("build.message." + lang + ".gui.air_value");
            this.total = this.yml.getString("build.message." + lang + ".gui.total");
            this.rightArrow = this.yml.getString("build.message." + lang + ".gui.right_arrow");
            this.clickForChange = this.yml.getString("build.message." + lang + ".gui.click_for_change");
            this.propertyKey = this.yml.getString("build.message." + lang + ".gui.property_key");
            this.valuePropertykey = this.yml.getString("build.message." + lang + ".gui.value_propertykey");
            this.clickForChangeProperty = this.yml.getString("build.message." + lang + ".gui.click_for_change_property");


        } catch (NullPointerException | IllegalStateException e) {

            log.severe("Configuration files in ExpertBuild folder is wrong or corrupt " +
                    "repair the file or delete all the files, so that the plugin recreates them");

        }
        return this;
    }

    public String getPluginEnable() {
        return pluginEnable;
    }

    public String getPluginDisable() {
        return pluginDisable;
    }

    public String getListenersLoad() {
        return listenersLoad;
    }

    public String getCommandsLoad() {
        return commandsLoad;
    }

    public String getConfigLoad() {
        return configLoad;
    }

    public String getGuiLoad() {
        return guiLoad;
    }

    public String getBrushLoad() {
        return brushLoad;
    }

    public String getSchematicTransfertFile(String num) {
        return schematicTransfertFile.replace("'num'", num);
    }

    public String getCheckingUpdate() {
        return checkingUpdate;
    }

    public String getNotNewUpdate() {
        return notNewUpdate;
    }

    public String getNewUpdateAvailable(String v1, String v2) {
        return newUpdateAvailable.replace("'v1'", v1).replace("'v2'", v2);
    }

    public String getUnableCheckUpdate(String error) {
        return unableCheckUpdate.replace("'error'", error);
    }

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

    public String getMaterialSet() {
        return materialSet;
    }

    public String getRadiusSet() {
        return radiusSet;
    }

    public String getBrushDisable() {
        return brushDisable;
    }

    public String getBrushRegistered(String brush) {
        return brushRegistered.replace("'brush'", brush);
    }

    public String getPlayerRegistered(String player) {
        return playerRegistered.replace("'player'", player);
    }

    public String getBuilderProfileRegistered() {
        return builderProfileRegistered;
    }

    public String getPlayerAlreadyRegistered() {
        return playerAlreadyRegistered;
    }

    public String getPointAdd(String point) {
        return pointAdd.replace("'point'", point);
    }

    public String getPointNotSave() {
        return pointNotSave;
    }

    public String getDontPerm() {
        return dontPerm;
    }

    public String getConsoleNotExecuteCmd() {
        return consoleNotExecuteCmd;
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

    public String getSelectionClear() {
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

    public String getErrorRegion(String num, String error) {
        return errorRegion.replace("'num'", num).replace("'error'", error);
    }

    public String getErrorSelection(String selection) {
        return errorSelection.replace("'selection'", selection);
    }

    public String getErrorBrushbuilder(String player) {
        return errorBrushbuilder.replace("'player'", player);
    }

    public String getErrorJschException() {
        return errorJschException;
    }

    public String getErrorSftpException() {
        return errorSftpException;
    }

    public String getFileConfigurationError() {
        return fileConfigurationError;
    }

    public String getIncompleteSelection() {
        return incompleteSelection;
    }

    public String getInvalidNumber() {
        return invalidNumber;
    }

    public String getInvalidNumberInteger() {
        return invalidNumberInteger;
    }

    public String getInvalidNumberIntegerUpper0() {
        return invalidNumberIntegerUpper0;
    }

    public String getInvalidMaterial() {
        return invalidMaterial;
    }

    public String getInvalidMaterialSet(String pattern) {
        return invalidMaterialSet.replace("'pattern'", pattern);
    }

    public String getInvalidBiome() {
        return invalidBiome;
    }

    public String getInvalidBiomeSet(String biome) {
        return invalidBiomeSet.replace("'biome'", biome);
    }

    public String getGiveTool(String tool) {
        return giveTool.replace("'tool'", tool);
    }

    public String getUse(String cmd) {
        return use.replace("'cmd'", cmd);
    }

    public String getPregeneration() {
        return pregeneration;
    }

    public String getGenerate() {
        return generate;
    }

    public String getFileNotExist(String file) {
        return fileNotExist.replace("'file'", file);
    }

    public String getFileTooLarge() {
        return fileTooLarge;
    }

    public String getServerOff() {
        return serverOff;
    }

    public String getUnknownServer() {
        return unknownServer;
    }

    public String getTransfert() {
        return transfert;
    }

    public String getDontRestart() {
        return dontRestart;
    }

    public String getSuccesTransfert(String file, String server) {
        return succesTransfert.replace("'file'", file).replace("'server'", server);
    }

    public String getTransfertLog(String file, String player, String server) {
        return transfertLog.replace("'file'", file).replace("'player'", player).replace("'server'", server);
    }

    public String getBack() {
        return back;
    }

    public String getExit() {
        return exit;
    }

    public String getMainGuiTitle() {
        return main_gui_title;
    }

    public String getMainItem1() {
        return main_item_1;
    }

    public String getMainItem2() {
        return main_item_2;
    }

    public String getMainItem3() {
        return main_item_3;
    }

    public String getLeatherGuiTitle() {
        return leather_gui_title;
    }

    public String getLeatherItem() {
        return leather_item;
    }

    public String getLeatherHelmet() {
        return leather_helmet;
    }

    public String getLeatherChestplate() {
        return leather_chestplate;
    }

    public String getLeatherLeggings() {
        return leather_leggings;
    }

    public String getLeatherBoots() {
        return leather_boots;
    }

    public String getRedColor() {
        return red_color;
    }

    public String getGreenColor() {
        return green_color;
    }

    public String getBlueColor() {
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

    public String getOrganicGuiTitle() {
        return organic_gui_title;
    }

    public String getOrganicItem() {
        return organic_item;
    }

    public String getPitchAngleConf() {
        return pitch_angle_conf;
    }

    public String getYawAngleConf() {
        return yaw_angle_conf;
    }

    public String getClickChangeAngle(String type) {
        return click_change_angle.replace("'type'", type);
    }

    public String getClickEnableDisable() {
        return click_enable_disable;
    }

    public String getClickPregen() {
        return this.clickPregen;
    }

    public String getClickGenerate() {
        return this.clickGenerate;
    }

    public String getClickClearParticle() {
        return this.clickClearParticle;
    }

    public String getMemberConf() {
        return member_conf;
    }

    public String getHeight() {
        return height;
    }

    public String getOrganicHeight(String value) {
        return organic_height.replace("'value'", value);
    }

    public String getInteractMember() {
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


    public String getFlowerGuiTitle() {
        return flowerGuiTitle;
    }

    public String getFlowerItem() {
        return flowerItem;
    }

    public String getBrushEnable2() {
        return brushEnable2;
    }

    public String getBrushDisable2() {
        return brushDisable2;
    }

    public String getRadiusText() {
        return radiusText;
    }

    public String getRadiusValue(String value) {
        return radiusValue.replace("'value'", value);
    }

    public String getAirText() {
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

    public String getClickForChange() {
        return clickForChange;
    }

    public String getPropertyKey(String value) {
        return propertyKey.replace("'value'", value);
    }

    public String getValuePropertykey(String value) {
        return valuePropertykey.replace("'value'", value);
    }

    public String getClickForChangeProperty() {
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
}