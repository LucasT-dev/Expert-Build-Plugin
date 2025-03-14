package fr.marodeur.expertbuild;

import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.api.fawe.factory.parser.SquarePatternParser;
import fr.marodeur.expertbuild.api.fawe.factory.parser.TypeChangeParser;
import fr.marodeur.expertbuild.commands.AreaTimerCommand;
import fr.marodeur.expertbuild.commands.CommandAutoCb;
import fr.marodeur.expertbuild.commands.CommandConvertSlab.CommandConvertSlab;
import fr.marodeur.expertbuild.commands.CommandsBrush.BrushCommand;
import fr.marodeur.expertbuild.commands.CommandsGeneral.CommandsInfo;
import fr.marodeur.expertbuild.commands.CommandsGivenTools.Terraforming_Painting;
import fr.marodeur.expertbuild.commands.CommandsTimeLapse.CommandTimelapse;
import fr.marodeur.expertbuild.commands.commandPainting.CommandPainting;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.function.mask.OngroundMask;
import fr.marodeur.expertbuild.brush.*;
import fr.marodeur.expertbuild.listeners.BrushListener;
import fr.marodeur.expertbuild.listeners.FAWEListener;
import fr.marodeur.expertbuild.listeners.GeneralListener;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.lison.LightweightInteractiveSystemforOptimizedParticleNavigation;
import fr.marodeur.expertbuild.object.lison.ScheduledWorkload;
import fr.marodeur.expertbuild.object.lison.ScheduledWorkloadRunnable;
import fr.marodeur.expertbuild.object.block.BlockData;
import fr.marodeur.expertbuild.object.builderObjects.DataProfile;
import fr.marodeur.expertbuild.object.fileManager.FileManager;
import fr.marodeur.expertbuild.object.guibuilder.InventoryManager;
import fr.marodeur.expertbuild.object.shape.CylinderShape;
import fr.marodeur.expertbuild.object.shape.SphereShape;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.*;

public class Main extends JavaPlugin {

	private static Main instance;
	private static DataPlugin dataPlugin;
	private static AbstractBrush.RegisterBrush brush;
	private static AbstractShape.RegisterShape shape;
	private static Configuration configuration;
	private static Message fileMessageManager;
	private static DataProfile dataProfile;
	private static FileManager fileManager;
	public static WorldEditPlugin WorldEditPlugin;


	public static InventoryManager inv = new InventoryManager();

	public static final ScheduledWorkloadRunnable scheduledWorkloadRunnable = new ScheduledWorkloadRunnable();

	// A supprimer a therme
	public static String prefix = ("§8[§5§oEXP-Build§8] §l>§l§7 ");


	// Changer d'endroit
	public static List<UUID> getCommand = new ArrayList<>();
	//


	@Override
	public void onLoad() {
		super.onLoad();

	}

	@Override
	public void onEnable() {

		instance = this;

		dataPlugin = new DataPlugin(this);

		saveDefaultConfig();

		reloadConfig();

		reloadMessageConfig();

		// LOAD MESSAGE
		fileMessageManager = new Message().loadFileConfig();

		getServer().getConsoleSender().sendMessage(new Message.MessageSender("expbuild.message.main.plugin_enable", true).getMessage());
		getLogger().info(" ____          ___  ");
		getLogger().info("|      \\  /  |   ) ");
		getLogger().info("|__     \\/   |___) ");
		getLogger().info("|       /\\   |     ");
		getLogger().info("|___   /  \\  |     ");
		getLogger().info("Version : " + dataPlugin.getPluginVersion() + " / by Marodeur");
		getLogger().info("FAWE server version : " + dataPlugin.getFaweVersion());
		getLogger().info("This plugin is not affiliated with Microsoft and Mojang Studios");

		WorldEditPlugin = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");

		// Check if server is in safe environments
		if (dataPlugin.getJavaVersion() < 16) onDisable();

		loadBrush();
		loadShape();

		registerListeners();
		registerCommand();

		// LOAD DATA PROFILE
		dataProfile = new DataProfile();

		fileManager = new FileManager();

		// gui api
		inv.invoke();


		registerPlayerBuilder();

		// LISON
		new LightweightInteractiveSystemforOptimizedParticleNavigation().loadSchedule();

		// LOAD MASK
		registerMaskAndPattern();

		// UPDATE CHECKER
		getServer().getConsoleSender().sendMessage(new Message.MessageSender("expbuild.message.main.checking_update", true).getMessage());


		BlockData blockData = new BlockData();
	}

	private void registerListeners() {

		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new GeneralListener(), this);
		pm.registerEvents(new FAWEListener(), this);
		pm.registerEvents(new BrushListener(), this);

		//Todo :
		//pm.registerEvents(new RotationEntity(), this);

		getLogger().info(new Message.MessageSender("expbuild.message.main.listeners_load", false).getMessage());

	}

	@SuppressWarnings("ConstantConditions")
	private void registerCommand() {

		getCommand("pos").setExecutor(new Terraforming_Painting());
		getCommand("getcommand").setExecutor(new Terraforming_Painting());
		getCommand("plume").setExecutor(new Terraforming_Painting());
		getCommand("silex").setExecutor(new Terraforming_Painting());
		getCommand("terra").setExecutor(new Terraforming_Painting());
		getCommand("vox").setExecutor(new Terraforming_Painting());
		getCommand("c").setExecutor(new Terraforming_Painting());
		getCommand("cube").setExecutor(new Terraforming_Painting());
		getCommand("poly").setExecutor(new Terraforming_Painting());
		getCommand("convex").setExecutor(new Terraforming_Painting());
		getCommand("sel").setExecutor(new Terraforming_Painting());
		getCommand("repeater").setExecutor(new Terraforming_Painting());
		getCommand("flower").setExecutor(new BrushCommand());
		getCommand("expbuild").setExecutor(new CommandsInfo());
		//getCommand("schemtrans").setExecutor(new CommandTransferSchem());
		getCommand("timelapse").setExecutor(new CommandTimelapse());
		//getCommand("perlin").setExecutor(new CommandPerlin());
		getCommand("autocb").setExecutor(new CommandAutoCb());
		getCommand("convertslab").setExecutor(new CommandConvertSlab());
		getCommand("painting").setExecutor(new CommandPainting());
		getCommand("areatimer").setExecutor(new AreaTimerCommand());

		//getCommand("explsystem").setExecutor(new CommandsLSystem());

		getLogger().info(new Message.MessageSender("expbuild.message.main.commands_load", false).getMessage());

	}

	public static Main getInstance() {
		return instance;
	}

	/**
	 * Class for general information of the plugin
	 * @return DataPlugin
	 */
	public static DataPlugin getDataPlugin() {
		return dataPlugin;
	}

	/**
	 * Register player after plugin reload
	 */
	public void registerPlayerBuilder() {

		Bukkit.getOnlinePlayers().forEach(player -> {
			if (player.hasPermission("exp.register")) {
				BrushBuilder.registerPlayer(player);

				getLogger().info(new Message.MessageSender("expbuild.message.brush.player_registered", false, new String[]{player.getName()}).getMessage());

			}
		});
	}

	/**
	 * Load and reload plugin configuration file (config.yml)
	 */
	public void reloadConfig() {

		try {
			configuration = new Configuration().loadConfiguration();
		} catch (IOException e) {
			getLogger().severe("Error loading plugin config");
		}

		getLogger().info("Configuration load");
	}

	/**
	 * Load and reload message file (config.yml)
	 */
	public void reloadMessageConfig() {

		fileMessageManager = new Message().loadFileConfig();
		getLogger().info("Message load");
	}

	/**
	 * get WorldEdit plugin
	 * @return WorldEditPlugin
	 */
	public WorldEditPlugin getWorldEditPlugin() {
		return WorldEditPlugin;
	}

	private void registerMaskAndPattern() {

		// Mask
		WorldEditPlugin.getWorldEdit().getMaskFactory().register(new OngroundMask(getWorldEditPlugin().getWorldEdit()));

		// Pattern
		WorldEditPlugin.getWorldEdit().getPatternFactory().register(new SquarePatternParser(getWorldEditPlugin().getWorldEdit()));
		WorldEditPlugin.getWorldEdit().getPatternFactory().register(new TypeChangeParser(getWorldEditPlugin().getWorldEdit()));

		try {

			FaweAPI.loadBlockCategoriesMask();

		} catch (IllegalStateException ignored) {

		}
	}


	// BrushBuilder
	public static Configuration getConfiguration() {
		return configuration;
	}

	public static Message getFileMessageManager() {
		return fileMessageManager;
	}

	public static DataProfile getDataProfile() {
		return dataProfile;
	}

	public static void addScheduledWorkloadRunnable(ScheduledWorkload scheduledWorkload) {
		scheduledWorkloadRunnable.addWorkload(scheduledWorkload);
	}

	public static AbstractBrush.RegisterBrush getBrush() {
		return brush;
	}

	public static AbstractShape.RegisterShape getShape() {
		return shape;
	}

	public static FileManager getFileManager() {
		return fileManager;
	}

	private void loadBrush() {

		brush = new AbstractBrush.RegisterBrush();

		brush.createBrush(new BlendBallBrush());
		brush.createBrush(new Clipboard3DBrush());
		brush.createBrush(new ClipboardsBrush());
		brush.createBrush(new CubeBrush());
		brush.createBrush(new DegradeBrush());
		brush.createBrush(new DrainBrush());
		brush.createBrush(new EraserBrush());
		brush.createBrush(new ErodeBlendBrush());
		brush.createBrush(new ErodeBrush());
		brush.createBrush(new FlowerBrush());
		brush.createBrush(new LineBrush());
		brush.createBrush(new NoneBrush());
		brush.createBrush(new OverlayBrush());
		brush.createBrush(new Rot2DCubeBrush());
		brush.createBrush(new SpikeBrush());
		//brush.createBrush(new HydrologyBrush());

		getLogger().info(new Message.MessageSender("expbuild.message.main.brush_load", false).getMessage());
	}

	private void loadShape() {

		shape = new AbstractShape.RegisterShape();

		shape.registerShape(new SphereShape());
		shape.registerShape(new CylinderShape());

	}

	@Override
	public void onDisable() {

		fileManager.saveFileOnDisable();

		getServer().getConsoleSender().sendMessage(new Message.MessageSender("expbuild.message.main.plugin_disable", true).getMessage());
	}
}