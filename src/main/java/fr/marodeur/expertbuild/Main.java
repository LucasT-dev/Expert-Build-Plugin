package fr.marodeur.expertbuild;

import fr.marodeur.expertbuild.commands.CommandsTimeLapse.CommandTimelapse;
import fr.marodeur.expertbuild.commands.commandPainting.CommandPainting;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.function.mask.OngroundMask;
import fr.marodeur.expertbuild.api.metrics.Metrics;
import fr.marodeur.expertbuild.brush.*;
import fr.marodeur.expertbuild.commands.CommandAutoCb;
import fr.marodeur.expertbuild.commands.CommandConvertSlab.CommandConvertSlab;
import fr.marodeur.expertbuild.commands.CommandTransferSchem;
import fr.marodeur.expertbuild.commands.CommandsBrush.BrushCommand;
import fr.marodeur.expertbuild.commands.CommandsGeneral.CommandsInfo;
import fr.marodeur.expertbuild.commands.CommandsGivenTools.Terraforming_Painting;
import fr.marodeur.expertbuild.commands.CommandsPerlin.CommandPerlin;
import fr.marodeur.expertbuild.listeners.BrushListener;
import fr.marodeur.expertbuild.listeners.FAWEListener;
import fr.marodeur.expertbuild.listeners.GeneralListener;
import fr.marodeur.expertbuild.listeners.RotationEntity;
import fr.marodeur.expertbuild.object.*;

import fr.marodeur.expertbuild.object.LISON.LightweightInteractiveSystemforOptimizedParticleNavigation;
import fr.marodeur.expertbuild.object.LISON.ScheduledWorkload;
import fr.marodeur.expertbuild.object.LISON.ScheduledWorkloadRunnable;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

import static fr.marodeur.expertbuild.object.BrushBuilder.registerPlayer;

public class Main extends JavaPlugin {

	private static Main instance;
	public static int id = 110059;
	public static String latestVersion;
	public static String lateVersion;


	private static AbstractBrush.RegisterBrush brush;
	private static Configuration configuration;
	private static Message fileMessageManager;

	private static DataProfile dataProfile;

	public static final ScheduledWorkloadRunnable scheduledWorkloadRunnable = new ScheduledWorkloadRunnable();


	public static List<UUID> getCommand = new ArrayList<>();

	private static final HashMap<UUID, GOHA_Builder> GOHA = new HashMap<>();
	private static final HashMap<UUID, BrushBuilder> BrushBuilder = new HashMap<>();

	public static String prefix = ("§8[§5§oEXP-Build§8] §l>§l§7 ");
	public static String FawePrefix = "§8(§4FAWE§8) §7";

	private static final Logger log = Logger.getLogger("Expert-Build");

	private final InventoryManager inventoryManager = new InventoryManager(this);

	public static WorldEditPlugin WorldEditPlugin;


    @Override
	public void onEnable() {

		instance = this;

		saveDefaultConfig();

		reloadConfig();

		try {
			reloadMessageConfig();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		getServer().getConsoleSender().sendMessage(new Message.MessageSender("expbuild.message.main.plugin_enable", true).getMessage());
		getLogger().info(" ____          ___  ");
		getLogger().info("|      \\  /  |   ) ");
		getLogger().info("|__     \\/   |___) ");
		getLogger().info("|       /\\   |     ");
		getLogger().info("|___   /  \\  |     ");
		getLogger().info("Version : " + Main.getInstance().getDescription().getVersion()+ " / by Marodeur");
		getLogger().info("FAWE server version : " + WorldEdit.getVersion());
		getLogger().info("This plugin is not affiliated with Mojang Studios");

		WorldEditPlugin = (WorldEditPlugin)getServer().getPluginManager().getPlugin("WorldEdit");

		if (Bukkit.getPluginManager().getPlugin("PlotSquared") != null) {
			// Do something
			System.out.println("PLOTSQUARED detected");
		}

		//bstat
		bstatsManager(new Metrics(this, 16755));

		// Check if server is in safe environments
		if (!isJavaSixteenMin()) onDisable();

		inventoryManager.invoke();

		try {
			serverFileBuilder();
		} catch (IOException e) {
			getLogger().severe(new Message.MessageSender("expbuild.message.error.file_configuration_error", true).getMessage());
		}

		loadBrush();

		registerListeners();

		// LOAD DATA PROFILE
		dataProfile = new DataProfile();

		registerPlayerBuilder();

		registerCommand();

		// LISON
		new LightweightInteractiveSystemforOptimizedParticleNavigation().loadSchedule();

		// LOAD MASK
		loadCustomMask();

		// LOAD MESSAGE 2
		fileMessageManager = new Message().loadFileConfig();

		// UPDATE CHECKER
		getServer().getConsoleSender().sendMessage(new Message.MessageSender("expbuild.message.main.checking_update", true).getMessage());

		updateChecker(version -> {
			if (!this.getDescription().getVersion().equals(version)) {
				getServer().getConsoleSender().sendMessage(new Message.MessageSender("expbuild.message.main.new_update_available", true, new String[]{Main.lateVersion, Main.getVersion(), Main.latestVersion}).getMessage());
			}
		},id);
	}

	private void registerListeners() {

		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new GeneralListener(), this);
		pm.registerEvents(new FAWEListener(), this);
		pm.registerEvents(new RotationEntity(), this);
		pm.registerEvents(new BrushListener(), this);

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

		getLogger().info(new Message.MessageSender("expbuild.message.main.commands_load", false).getMessage());

	}

	/**
	 * Register player after plugin reload
	 */
	public void registerPlayerBuilder() {

		Bukkit.getOnlinePlayers().forEach(player -> {
			if (player.hasPermission("exp.register")) {
				registerPlayer(player, false);
				GOHA_Builder.registerPlayer(player);

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
			e.printStackTrace();
		}

		getLogger().info("Configuration load");
	}

	/**
	 * Load and reload message file (config.yml)
	 */
	public void reloadMessageConfig() throws URISyntaxException {

		fileMessageManager = new Message().loadFileConfig();

		getLogger().info("Message load");

	}

	/**
	 * get WorldEdit plugin
	 * @return
	 */
	public WorldEditPlugin getWorldEditPlugin() {
		return WorldEditPlugin;
	}

	private void loadCustomMask() {

		WorldEditPlugin.getWorldEdit().getMaskFactory().register(new OngroundMask(WorldEditPlugin.getWorldEdit()));

	}

	// BrushBuilder
	public static Configuration configuration() {
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
	@Contract("_ -> param1")
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

	//goha Builder
	public static GOHA_Builder getGohaBuilder(@NotNull Player p) {
		return GOHA.get(p.getUniqueId());
	}

	public static void updateGohaBuilder(GOHA_Builder gohaBuilder) {
		GOHA.replace(gohaBuilder.getPlayer().getUniqueId(), gohaBuilder);
	}

	public static void registerGohaBuilder(GOHA_Builder gohaBuilder) {
		GOHA.put(gohaBuilder.getPlayer().getUniqueId(), gohaBuilder);
	}

	public static @NotNull Boolean containsGohaBuilder(@NotNull Player p) {
		return GOHA.containsKey(p.getUniqueId());
	}

	public static Main getInstance() {
		return instance;
	}

	public static void addScheduledWorkloadRunnable(ScheduledWorkload scheduledWorkload) {
		scheduledWorkloadRunnable.addWorkload(scheduledWorkload);
	}

	/**
	 * get plugin version
	 *
	 * @return String
	 */
	public static @NotNull String getVersion() {
		return Main.getInstance().getDescription().getVersion();
	}

	public static @NotNull String getBukkitVersion() {
		return Bukkit.getBukkitVersion();
	}


	public static AbstractBrush.RegisterBrush getBrush() {
		return brush;
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
		brush.createBrush(new UpdateChunkBrush());

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

	public static void updateChecker(final Consumer<String> consumer, int id) {

		Bukkit.getScheduler().runTaskAsynchronously(getInstance(), () -> {

			try {

				InputStream inputStream = (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + id + "\\~")).openStream();
				Scanner scanner = new Scanner(inputStream);

				if (scanner.hasNext()) {

					latestVersion = scanner.next();

					lateVersion = String.valueOf(Integer.parseInt(latestVersion.substring(latestVersion.length() - 1)) - Integer.parseInt(getVersion().substring(getVersion().length() - 1)));

					consumer.accept(latestVersion);
				}

			} catch (IOException e) {
				Main.getInstance().getLogger().severe(new Message.MessageSender("expbuild.message.main.unable_check_update", true, new String[]{e.getMessage()}).getMessage());
			}
		});
	}

	private boolean isJavaSixteenMin() {

		try {
			int javaVersion = Integer.parseInt(System.getProperty("java.version").substring(0, 2));

			if (javaVersion < 16 ) {
				log.severe("Please use Java 16 minimum !");
				return false;

			} else {
				log.info("Java version : " + System.getProperty("java.version"));
				return true;
			}
		} catch (NumberFormatException e) {
			log.severe("Unable to determine java version");
			return false;
		}
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(new Message.MessageSender("expbuild.message.main.plugin_disable", true).getMessage());
	}
}