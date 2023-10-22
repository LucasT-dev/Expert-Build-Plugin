package fr.Marodeur.ExpertBuild.Object;

import fr.Marodeur.ExpertBuild.Main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

public class MessageBuilder {

    private final FileConfiguration yml;
    private final Configuration config = Main.getInstance().getConfig();
    private final Logger log = Logger.getLogger("Expert-Build");

    private File langfile;

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
    private String noPermissionNode;


    private String setPos1;
    private String setPos1WithArea;
    private String setPos2;
    private String setPos2WithArea;
    private String addVertexPos;
    private String selectionClear;
    private String setSelection;

    private String blockModified;
    private String blockModifiedWithTime;

    private String playerLogCommand;

    private String copyBlock;


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

    private String enable;
    private String disable;
    private String allClipboardDelete;
    private String clipboardDoesNotExist;
    private String clipboardAlreadyExist;
    private String clipboardAddAndEnable;
    private String clipboardRemove;


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


    public MessageBuilder() throws URISyntaxException {

        this.yml = loadLanguageFile();

        if (! deleteLangFile()) log.severe("Error, Error on language file, Unable to delete file correctly; report to dev !");
    }

    public MessageBuilder loadConfiguration() {

        try {

            this.pluginEnable = this.yml.getString("expbuild.message.main.plugin_enable");
            this.pluginDisable = this.yml.getString("expbuild.message.main.plugin_disable");

            this.listenersLoad = this.yml.getString("expbuild.message.main.listeners_load");
            this.commandsLoad = this.yml.getString("expbuild.message.main.commands_load");
            this.configLoad = this.yml.getString("expbuild.message.main.config_load");
            this.guiLoad = this.yml.getString("expbuild.message.main.gui_load");
            this.brushLoad = this.yml.getString("expbuild.message.main.brush_load");
            this.schematicTransfertFile = this.yml.getString("expbuild.message.main.schematic_transfert_file");

            this.checkingUpdate = this.yml.getString("expbuild.message.main.checking_update");
            this.notNewUpdate = this.yml.getString("expbuild.message.main.not_new_update");
            this.newUpdateAvailable = this.yml.getString("expbuild.message.main.new_update_available");
            this.unableCheckUpdate = this.yml.getString("expbuild.message.main.unable_check_update");


            this.brushEnable = this.yml.getString("expbuild.message.brush.brush_enable");
            this.brushEnableWithRadius = this.yml.getString("expbuild.message.brush.brush_enable_with_radius");
            this.brushEnableWithMaterial = this.yml.getString("expbuild.message.brush.brush_enable_with_material");
            this.brushEnableWithRadiusPattern = this.yml.getString("expbuild.message.brush.brush_enable_with_radius_pattern");
            this.brushDisable = this.yml.getString("expbuild.message.brush.brush_disable");
            this.brushRegistered = this.yml.getString("expbuild.message.brush.brush_registered");
            this.materialSet = this.yml.getString("expbuild.message.brush.material_set");
            this.radiusSet = this.yml.getString("expbuild.message.brush.radius_set");


            this.playerRegistered = this.yml.getString("expbuild.message.brush.player_registered");
            this.builderProfileRegistered = this.yml.getString("expbuild.message.brush.builder_profile_registered");
            this.playerAlreadyRegistered = this.yml.getString("expbuild.message.brush.player_already_registered");

            this.pointAdd = this.yml.getString("expbuild.message.brush.point_add");
            this.pointNotSave = this.yml.getString("expbuild.message.brush.point_not_save");


            this.dontPerm = this.yml.getString("expbuild.message.permission.dont_perm");
            this.consoleNotExecuteCmd = this.yml.getString("expbuild.message.permission.console_not_execute_cmd");
            this.noPermissionNode = this.yml.getString("expbuild.message.permission.no_permission_node");


            this.setPos1 = this.yml.getString("expbuild.message.selection.set_pos_1");
            this.setPos1WithArea = this.yml.getString("expbuild.message.selection.set_pos_1_with_area");
            this.setPos2 = this.yml.getString("expbuild.message.selection.set_pos_2");
            this.setPos2WithArea = this.yml.getString("expbuild.message.selection.set_pos_2_with_area");
            this.addVertexPos = this.yml.getString("expbuild.message.selection.add_vertex_pos");
            this.selectionClear = this.yml.getString("expbuild.message.selection.selection_clear");
            this.setSelection = this.yml.getString("expbuild.message.selection.set_selection");

            this.blockModified = this.yml.getString("expbuild.message.selection.block_modified");
            this.blockModifiedWithTime = this.yml.getString("expbuild.message.selection.block_modified_with_time");

            this.playerLogCommand = this.yml.getString("expbuild.message.selection.player_log_command");

            this.copyBlock = this.yml.getString("expbuild.message.selection.copy_block");

            this.errorRegion = this.yml.getString("expbuild.message.error.error_region");
            this.errorSelection = this.yml.getString("expbuild.message.error.error_selection");
            this.errorBrushbuilder = this.yml.getString("expbuild.message.error.error_brushbuilder");
            this.errorJschException = this.yml.getString("expbuild.message.error.error_jsch_exception");
            this.errorSftpException = this.yml.getString("expbuild.message.error.error_sftp_exception");
            this.fileConfigurationError = this.yml.getString("expbuild.message.error.file_configuration_error");
            this.incompleteSelection = this.yml.getString("expbuild.message.error.incomplete_selection");
            this.invalidNumber = this.yml.getString("expbuild.message.error.invalid_number");
            this.invalidNumberInteger = this.yml.getString("expbuild.message.error.invalid_number_integer");
            this.invalidNumberIntegerUpper0 = this.yml.getString("expbuild.message.error.invalid_number_integer_upper_0");
            this.invalidMaterial = this.yml.getString("expbuild.message.error.invalid_material");
            this.invalidMaterialSet = this.yml.getString("expbuild.message.error.invalid_material_set");
            this.invalidBiome = this.yml.getString("expbuild.message.error.invalid_biome");
            this.invalidBiomeSet = this.yml.getString("expbuild.message.error.invalid_biome_set");


            this.giveTool = this.yml.getString("expbuild.message.tools.give_tool");


            this.use = this.yml.getString("expbuild.message.commands.use");
            this.pregeneration = this.yml.getString("expbuild.message.commands.pregeneration");
            this.generate = this.yml.getString("expbuild.message.commands.generate");


            this.fileNotExist = this.yml.getString("expbuild.message.commands.file_not_exist");
            this.fileTooLarge = this.yml.getString("expbuild.message.commands.file_too_large");
            this.serverOff = this.yml.getString("expbuild.message.commands.server_off");
            this.unknownServer = this.yml.getString("expbuild.message.commands.unknown_server");
            this.transfert = this.yml.getString("expbuild.message.commands.transfert");
            this.dontRestart = this.yml.getString("expbuild.message.commands.dont_restart");
            this.succesTransfert = this.yml.getString("expbuild.message.commands.succes_transfert");
            this.transfertLog = this.yml.getString("expbuild.message.commands.transfert_log");

            this.enable = this.yml.getString("expbuild.message.commands.enable");
            this.disable = this.yml.getString("expbuild.message.commands.disable");
            this.allClipboardDelete = this.yml.getString("expbuild.message.commands.all_clipboards_delete");
            this.clipboardDoesNotExist = this.yml.getString("expbuild.message.commands.clipboard_does_not_exist");
            this.clipboardAlreadyExist = this.yml.getString("expbuild.message.commands.clipboard_already_exist");
            this.clipboardAddAndEnable = this.yml.getString("expbuild.message.commands.clipboard_add_and_enable");
            this.clipboardRemove = this.yml.getString("expbuild.message.commands.clipboard_remove");


            this.back = this.yml.getString("expbuild.message.gui.back");
            this.exit = this.yml.getString("expbuild.message.gui.exit");
            this.main_gui_title = this.yml.getString("expbuild.message.gui.main_gui_title");
            this.main_item_1 = this.yml.getString("expbuild.message.gui.main_item_1");
            this.main_item_2 = this.yml.getString("expbuild.message.gui.main_item_2");
            this.main_item_3 = this.yml.getString("expbuild.message.gui.main_item_3");

            this.leather_gui_title = this.yml.getString("expbuild.message.gui.leather_gui_title");
            this.leather_item = this.yml.getString("expbuild.message.gui.leather_item");
            this.leather_helmet = this.yml.getString("expbuild.message.gui.leather_helmet");
            this.leather_chestplate = this.yml.getString("expbuild.message.gui.leather_chestplate");
            this.leather_leggings = this.yml.getString("expbuild.message.gui.leather_leggings");
            this.leather_boots = this.yml.getString("expbuild.message.gui.leather_boots");
            this.red_color = this.yml.getString("expbuild.message.gui.red_color");
            this.green_color = this.yml.getString("expbuild.message.gui.green_color");
            this.blue_color = this.yml.getString("expbuild.message.gui.blue_color");
            this.red = this.yml.getString("expbuild.message.gui.red");
            this.green = this.yml.getString("expbuild.message.gui.green");
            this.blue = this.yml.getString("expbuild.message.gui.blue");

            this.organic_gui_title = this.yml.getString("expbuild.message.gui.organic_gui_title");
            this.organic_item = this.yml.getString("expbuild.message.gui.organic_item");
            this.pitch_angle_conf = this.yml.getString("expbuild.message.gui.pitch_angle_conf");
            this.yaw_angle_conf = this.yml.getString("expbuild.message.gui.yaw_angle_conf");
            this.click_change_angle = this.yml.getString("expbuild.message.gui.click_change_angle");
            this.click_enable_disable = this.yml.getString("expbuild.message.gui.click_enable_disable");
            this.clickPregen = this.yml.getString("expbuild.message.gui.click_pregen");
            this.clickGenerate = this.yml.getString("expbuild.message.gui.click_generate");
            this.clickClearParticle = this.yml.getString("expbuild.message.gui.click_clear_particle");
            this.member_conf = this.yml.getString("expbuild.message.gui.member_conf");
            this.height = this.yml.getString("expbuild.message.gui.height");
            this.organic_height = this.yml.getString("expbuild.message.gui.organic_height");
            this.interact_member = this.yml.getString("expbuild.message.gui.interact_member");
            this.switch_member = this.yml.getString("expbuild.message.gui.switch_member");
            this.pitch_level = this.yml.getString("expbuild.message.gui.pitch_level");
            this.yaw_level = this.yml.getString("expbuild.message.gui.yaw_level");

            this.flowerGuiTitle = this.yml.getString("expbuild.message.gui.flower_gui_title");
            this.flowerItem = this.yml.getString("expbuild.message.gui.flower_item");
            this.brushEnable2 = this.yml.getString("expbuild.message.gui.brush_enable");
            this.brushDisable2 = this.yml.getString("expbuild.message.gui.brush_disable");
            this.radiusText = this.yml.getString("expbuild.message.gui.radius_text");
            this.radiusValue = this.yml.getString("expbuild.message.gui.radius_value");
            this.airText = this.yml.getString("expbuild.message.gui.air_text");
            this.airValue = this.yml.getString("expbuild.message.gui.air_value");
            this.total = this.yml.getString("expbuild.message.gui.total");
            this.rightArrow = this.yml.getString("expbuild.message.gui.right_arrow");
            this.clickForChange = this.yml.getString("expbuild.message.gui.click_for_change");
            this.propertyKey = this.yml.getString("expbuild.message.gui.property_key");
            this.valuePropertykey = this.yml.getString("expbuild.message.gui.value_propertykey");
            this.clickForChangeProperty = this.yml.getString("expbuild.message.gui.click_for_change_property");

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

    public String getNewUpdateAvailable(String v, String v1, String v2) {
        return newUpdateAvailable.replace("'v'", v).replace("'v1'", v1).replace("'v2'", v2);
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

    public String getPlayerLogCommand(String player, String cmd) {
        return playerLogCommand.replace("'player'", player).replace("'cmd'", cmd);
    }

    public String getCopyBlock(String num) {
        return this.copyBlock.replace("'num'", num);
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

    public String getEnable(String element) {
        return this.enable.replace("'element'", element);
    }

    public String getDisable(String element) {
        return this.disable.replace("'element'", element);
    }

    public String getAllClipboardDelete() {
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

    private @Nullable YamlConfiguration loadLanguageFile() throws URISyntaxException {

        Main main = Main.getInstance();

        try(JarFile jarFile = new JarFile(Paths.get(main.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).toString())) {
            Enumeration<JarEntry> jarFiles = jarFile.entries();

            while (jarFiles.hasMoreElements()) {

                JarEntry jarEntry = jarFiles.nextElement();

                if (jarEntry.getRealName().equalsIgnoreCase("fr/Marodeur/ExpertBuild/API/Lang/" + this.config.getLang() + ".yml")) {

                    File langFile = new File(main.getDataFolder() ,jarEntry.getName());
                    this.langfile = langFile;

                    if(!langFile.exists()) {
                        main.saveResource(jarEntry.getName(), false);

                    }

                    return YamlConfiguration.loadConfiguration(langFile);
                }
            }

            log.warning("Unable to find the language file, loading the default file: English");

            File langFile = new File(main.getDataFolder(), "fr/Marodeur/ExpertBuild/API/Lang/en.yml");
            this.langfile = langFile;

            if(!langFile.exists()) {
                main.saveResource("fr/Marodeur/ExpertBuild/API/Lang/en.yml", false);

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