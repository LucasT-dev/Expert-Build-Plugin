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

    private String fileNotExist;
    private String fileTooLarge;
    private String serverOff;
    private String unknownServer;
    private String transfert;
    private String dontRestart;
    private String succesTransfert;
    private String transfertLog;

    public MessageBuilder() {

        String lang = config.getLang();

        System.out.println("pluginEnable = " + lang);

        File file = new File("plugins/ExpertBuild/config.yml");
        this.yml = YamlConfiguration.loadConfiguration(file);
    }

    public MessageBuilder loadConfiguration() {

        String lang = config.getLang();

        System.out.println("lang = " + lang);

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

            this.fileNotExist = this.yml.getString("build.message." + lang + ".commands.file_not_exist");
            this.fileTooLarge = this.yml.getString("build.message." + lang + ".commands.file_too_large");
            this.serverOff = this.yml.getString("build.message." + lang + ".commands.server_off");
            this.unknownServer = this.yml.getString("build.message." + lang + ".commands.unknown_server");
            this.transfert = this.yml.getString("build.message." + lang + ".commands.transfert");
            this.dontRestart = this.yml.getString("build.message." + lang + ".commands.dont_restart");
            this.succesTransfert = this.yml.getString("build.message." + lang + ".commands.succes_transfert");
            this.transfertLog = this.yml.getString("build.message." + lang + ".commands.transfert_log");


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
        return blockModified.replace("a'num'", num);
    }

    public String getBlockModifiedWithTime(String num1, String num2) {
        return blockModifiedWithTime.replace("a'num'", num1).replace("'num2'", num2);
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
                '}';
    }
}