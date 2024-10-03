package fr.marodeur.expertbuild;

import fr.marodeur.expertbuild.api.fawe.factory.parser.SquarePatternParser;
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
import fr.marodeur.expertbuild.api.metrics.Metrics;
import fr.marodeur.expertbuild.brush.*;
import fr.marodeur.expertbuild.listeners.BrushListener;
import fr.marodeur.expertbuild.listeners.FAWEListener;
import fr.marodeur.expertbuild.listeners.GeneralListener;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.LISON.LightweightInteractiveSystemforOptimizedParticleNavigation;
import fr.marodeur.expertbuild.object.LISON.ScheduledWorkload;
import fr.marodeur.expertbuild.object.LISON.ScheduledWorkloadRunnable;
import fr.marodeur.expertbuild.object.builderObjects.AreaTimerParameter;
import fr.marodeur.expertbuild.object.builderObjects.DataProfile;
import fr.marodeur.expertbuild.object.fileManager.FileManager;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class Main extends JavaPlugin {

	private static Main instance;
	private static DataPlugin dataPlugin;
	private static AbstractBrush.RegisterBrush brush;
	private static Configuration configuration;
	private static Message fileMessageManager;
	private static DataProfile dataProfile;
	private FileManager fileManager;
	public static WorldEditPlugin WorldEditPlugin;


	public static fr.marodeur.expertbuild.object.guibuilder.InventoryManager inv =
			new fr.marodeur.expertbuild.object.guibuilder.InventoryManager();
	public static final ScheduledWorkloadRunnable scheduledWorkloadRunnable = new ScheduledWorkloadRunnable();

	private static final HashMap<UUID, BrushBuilder> BrushBuilder = new HashMap<>();


	public static String prefix = ("§8[§5§oEXP-Build§8] §l>§l§7 ");


	// Changer d'endroit
	public static List<UUID> getCommand = new ArrayList<>();
	public static List<AreaTimerParameter> AREA_TIMER_PARAMETERS = new ArrayList<>();
	//

    @Override
	public void onEnable() {

		instance = this;

		dataPlugin = new DataPlugin(this);

		saveDefaultConfig();

		reloadConfig();

		reloadMessageConfig();

		getServer().getConsoleSender().sendMessage(new Message.MessageSender("expbuild.message.main.plugin_enable", true).getMessage());
		getLogger().info(" ____          ___  ");
		getLogger().info("|      \\  /  |   ) ");
		getLogger().info("|__     \\/   |___) ");
		getLogger().info("|       /\\   |     ");
		getLogger().info("|___   /  \\  |     ");
		getLogger().info("Version : " + dataPlugin.getPluginVersion() + " / by Marodeur");
		getLogger().info("FAWE server version : " + WorldEdit.getVersion());
		getLogger().info("This plugin is not affiliated with Mojang Studios");

		WorldEditPlugin = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");

		//bstat
		bstatsManager(new Metrics(this, 16755));

		// Check if server is in safe environments
		if (dataPlugin.getJavaVersion() < 16) onDisable();

		try {
			serverFileBuilder();
		} catch (IOException e) {
			getLogger().severe(new Message.MessageSender("expbuild.message.error.file_configuration_error", true).getMessage());
		}

		// LOAD MESSAGE
		fileMessageManager = new Message().loadFileConfig();

		loadBrush();

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
		loadCustomMask();


		// UPDATE CHECKER
		getServer().getConsoleSender().sendMessage(new Message.MessageSender("expbuild.message.main.checking_update", true).getMessage());


		//DataBlockRegister.generateBlockData();

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
		getCommand("1").setExecutor(new Terraforming_Painting());
		getCommand("2").setExecutor(new Terraforming_Painting());
		getCommand("c").setExecutor(new Terraforming_Painting());
		getCommand("p").setExecutor(new Terraforming_Painting());
		getCommand("pa").setExecutor(new Terraforming_Painting());
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
				fr.marodeur.expertbuild.object.BrushBuilder.registerPlayer(player, false);

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

	private void loadCustomMask() {
		WorldEditPlugin.getWorldEdit().getMaskFactory().register(new OngroundMask(getWorldEditPlugin().getWorldEdit()));
		WorldEditPlugin.getWorldEdit().getPatternFactory().register(new SquarePatternParser(getWorldEditPlugin().getWorldEdit()));
		//WorldEditPlugin.getWorldEdit().getPatternFactory().register(new TrianglePatternParser(getWorldEditPlugin().getWorldEdit()));
		//WorldEditPlugin.getWorldEdit().getPatternFactory().register(new ColorPatternParser(getWorldEditPlugin().getWorldEdit()));

	}

	// BrushBuilder
	public static Configuration getConfiguration() {
		return configuration;
	}

	public static Message getFileMessageManager() {
		return fileMessageManager;
	}

	public static BrushBuilder getBrushBuilder(@NotNull Player p) {
		return BrushBuilder.get(p.getUniqueId());
	}

	public static HashMap<UUID, BrushBuilder> getHashMapBrushBuilder() {
		return BrushBuilder;
	}

	/**
	 *
	 * Register player in BrushBuilder, using player uuid,
	 *
	 * @param brushBuilder register player
	 * @return brushBuilder
	 */
	public static @NotNull BrushBuilder registerBrushBuilder(BrushBuilder brushBuilder) {
		BrushBuilder.put(brushBuilder.getUUID(), brushBuilder);
		return brushBuilder;
	}

	public static @NotNull Boolean containsBrushBuilder(@NotNull Player p) {
		return BrushBuilder.containsKey(p.getUniqueId());
	}

	public static void removeBrushBuilder(@NotNull Player p) {
		BrushBuilder.remove(p.getUniqueId());
	}

	public static int getBrushBuilderSize() {
		return BrushBuilder.size();
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

	public FileManager getFileManager() {
		return fileManager;
	}

	private void loadBrush() {

		brush = new AbstractBrush.RegisterBrush();

		brush.createBrush(new BiomeBrush());
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
		brush.createBrush(new SphereBrush());
		brush.createBrush(new SpikeBrush());
		//brush.createBrush(new HydrologyBrush());

		getLogger().info(new Message.MessageSender("expbuild.message.main.brush_load", false).getMessage());
	}

	private void serverFileBuilder() throws IOException {

		for (int i = 1; i < 5; i++) {

			File SFTP;
			YamlConfiguration c = new YamlConfiguration();

			SFTP = new File("plugins/ExpertBuild/Server" + i + ".yml");
			if (!SFTP.exists()) {

				c.set("Server", null);
				c.set("Server.user", "User");
				c.set("Server.ServerAddress", "host");
				c.set("Server.mdp", "mdp");
				c.set("Server.port", 1234);
				c.save(new File("plugins/ExpertBuild", "Server" + i + ".yml"));

				getLogger().info(new Message.MessageSender("expbuild.message.main.schematic_transfert_file", false).getMessage());
			}
		}
	}

	/**
	 *
	 * Send server use expert-build plugin and number of players connected
	 *
	 * @param metrics
	 */
	private void bstatsManager(@NotNull Metrics metrics) {

		metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));

		metrics.addCustomChart(new Metrics.SingleLineChart("players", () -> {
			// (This is useless as there is already a player chart by default.)
			return Bukkit.getOnlinePlayers().size();
		}));
	}

	@Override
	public void onDisable() {

		fileManager.saveFileOnDisable();

		getServer().getConsoleSender().sendMessage(new Message.MessageSender("expbuild.message.main.plugin_disable", true).getMessage());
	}
}