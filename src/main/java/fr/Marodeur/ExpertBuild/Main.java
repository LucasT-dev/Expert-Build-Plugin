package fr.Marodeur.ExpertBuild;

import com.sk89q.worldedit.WorldEdit;

import fr.Marodeur.ExpertBuild.API.Metrics.Metrics;
import fr.Marodeur.ExpertBuild.Brush.*;
import fr.Marodeur.ExpertBuild.Commands.CommandAutoCb;
import fr.Marodeur.ExpertBuild.Commands.CommandConvertSlab.CommandConvertSlab;
import fr.Marodeur.ExpertBuild.Commands.CommandTransferSchem;
import fr.Marodeur.ExpertBuild.Commands.CommandsAutoFlips.CommandAutoFlip;
import fr.Marodeur.ExpertBuild.Commands.CommandsBrush.BrushCommand;
import fr.Marodeur.ExpertBuild.Commands.CommandsGeneral.CommandsInfo;
import fr.Marodeur.ExpertBuild.Commands.CommandsGivenTools.Terraforming_Painting;
import fr.Marodeur.ExpertBuild.Commands.CommandsPerlin.CommandPerlin;
import fr.Marodeur.ExpertBuild.Commands.CommandsTimeLapse.CommandTimeLapse;
import fr.Marodeur.ExpertBuild.Listeners.BrushListener;
import fr.Marodeur.ExpertBuild.Listeners.FAWEListener;
import fr.Marodeur.ExpertBuild.Listeners.GeneralListener;
import fr.Marodeur.ExpertBuild.Listeners.RotationEntity;
import fr.Marodeur.ExpertBuild.Object.*;
import fr.Marodeur.ExpertBuild.Utils.BrushOperationManager;

import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;

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

public class Main extends JavaPlugin {

	private static Main instance;
	public static int id = 110059;
	public static String latestVersion;
	public static String lateVersion;


	private Map<Class<? extends BrushOperation>, BrushOperation> registeredBrush;

	private static Configuration configuration;
	private static MessageBuilder messageBuilder;

	public static List<UUID> getCommand = new ArrayList<>();

	private static final HashMap<UUID, GOHA_Builder> GOHA = new HashMap<>();
	private static final HashMap<UUID, BrushBuilder> BrushBuilder = new HashMap<>();

	public static String prefix = ("§8[§5§oEXP-Build§8] §l>§l§7 ");
	public static String FawePrefix = "§8(§4FAWE§8) §7";

	public static String help = ("§8[§5§oEXP-Build§8] §l(§l§5§ohelp§o§8§l)§l§8 §l>§l§7 ");

	private final InventoryManager inventoryManager = new InventoryManager(this);


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

		getServer().getConsoleSender().sendMessage(prefix + messageBuilder.getPluginEnable());
		getLogger().info(" ____          ___  ");
		getLogger().info("|      \\  /  |   ) ");
		getLogger().info("|__     \\/   |___) ");
		getLogger().info("|       /\\   |     ");
		getLogger().info("|___   /  \\  |     ");
		getLogger().info("Version : " + Main.getInstance().getDescription().getVersion()+ " / by Marodeur");
		getLogger().info("FAWE server version : " + WorldEdit.getVersion());
		getLogger().info("This plugin is not affiliated with Mojang Studios");

		//bstat
		bstatsManager(new Metrics(this, 16755));

		// Check if server is in safe environments
		isJavaSixteenMin();

		inventoryManager.invoke();

		try {
			serverFileBuilder();
		} catch (IOException e) {
			getLogger().severe(messageBuilder.getFileConfigurationError());
		}

		loadBrushOperation();

		registerListeners();

		registerPlayerBuilder();

		registerCommand();

		updateChecker(version -> {
			if (!this.getDescription().getVersion().equals(version)) {
				getServer().getConsoleSender().sendMessage(Main.prefix + messageBuilder.getNewUpdateAvailable(lateVersion, this.getDescription().getVersion(), latestVersion));
			}
		},id);
	}

	private void registerListeners() {

		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new GeneralListener(), this);
		pm.registerEvents(new FAWEListener(), this);
		pm.registerEvents(new RotationEntity(), this);
		pm.registerEvents(new BrushListener(), this);

		getLogger().info(messageBuilder.getListenersLoad());
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
		getCommand("s").setExecutor(new Terraforming_Painting());
		getCommand("sel").setExecutor(new Terraforming_Painting());
		getCommand("f").setExecutor(new Terraforming_Painting());
		getCommand("repeater").setExecutor(new Terraforming_Painting());
		getCommand("flower").setExecutor(new BrushCommand());
		getCommand("expbuild").setExecutor(new CommandsInfo());
		getCommand("schemtrans").setExecutor(new CommandTransferSchem());
		getCommand("timelapse").setExecutor(new CommandTimeLapse());
		getCommand("perlin").setExecutor(new CommandPerlin());
		getCommand("autocb").setExecutor(new CommandAutoCb());
		getCommand("autoflip").setExecutor(new CommandAutoFlip());
		getCommand("convertslab").setExecutor(new CommandConvertSlab());

		getLogger().info(messageBuilder.getCommandsLoad());

	}

	/**
	 * Register player after plugin reload
	 */
	public void registerPlayerBuilder() {

		Bukkit.getOnlinePlayers().forEach(player -> {
			if (player.isOp() || player.hasPermission("expertbuild.use")) {
				fr.Marodeur.ExpertBuild.Object.BrushBuilder.registerPlayer(player);
				GOHA_Builder.registerPlayer(player);

				getLogger().info(messageBuilder.getPlayerRegistered(player.getName()));
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

		messageBuilder = new MessageBuilder().loadConfiguration();

		getLogger().info("Message load");
	}

	//BrushBuilder
	public @NotNull Configuration getConfig() { return configuration; }

    public MessageBuilder getMessageConfig() { return messageBuilder; }

	public static fr.Marodeur.ExpertBuild.Object.BrushBuilder getBrushBuilder(@NotNull Player p) {
		return BrushBuilder.get(p.getUniqueId());
	}

	/**
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

	public static @NotNull String getVersion() {
		return Main.getInstance().getDescription().getVersion();
	}

	public Map<Class<? extends BrushOperation>, BrushOperation> getRegisteredBrush() {
		return registeredBrush;
	}

	private void loadBrushOperation() {

		BrushOperationManager brushManager = new BrushOperationManager();

		registeredBrush = new HashMap<>();

		brushManager.addBrush(new SphereBrush());
		brushManager.addBrush(new ClipboardsBrush());
		brushManager.addBrush(new OverlayBrush());
		brushManager.addBrush(new BiomeBrush());
		brushManager.addBrush(new UpdateChunk());
		brushManager.addBrush(new NoneBrush());
		brushManager.addBrush(new DegradeBrush());
		brushManager.addBrush(new DrainBrush());
		brushManager.addBrush(new Rot2DCubeBrush());
		brushManager.addBrush(new LineBrush());
		brushManager.addBrush(new SpikeBrush());
		brushManager.addBrush(new CubeBrush());
		brushManager.addBrush(new ErodeBrush());
		brushManager.addBrush(new BlendBallBrush());
		brushManager.addBrush(new FlowerBrush());
		brushManager.addBrush(new ErodeBlendBrush());
		brushManager.addBrush(new EraserBrush());

		getLogger().info(messageBuilder.getBrushLoad());

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

				getLogger().info(messageBuilder.getSchematicTransfertFile(String.valueOf(i)));
			}
		}
	}

	private void bstatsManager(@NotNull Metrics metrics) {

		metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));

		metrics.addCustomChart(new Metrics.SingleLineChart("players", () -> {
			// (This is useless as there is already a player chart by default.)
			return Bukkit.getOnlinePlayers().size();
		}));
	}

	public static void updateChecker(final Consumer<String> consumer, int id) {

		Bukkit.getScheduler().runTaskAsynchronously(getInstance(), () -> {
			Main.getInstance().getServer().getConsoleSender().sendMessage(Main.prefix + messageBuilder.getCheckingUpdate());

			try {

				InputStream inputStream = (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + id + "/~")).openStream();
				Scanner scanner = new Scanner(inputStream);

				if (scanner.hasNext()) {

					latestVersion = scanner.next();

					lateVersion = String.valueOf(Integer.parseInt(latestVersion.substring(latestVersion.length() - 1)) - Integer.parseInt(getVersion().substring(getVersion().length() - 1)));

					consumer.accept(latestVersion);
				}

			} catch (IOException e) {
				Main.getInstance().getLogger().severe(messageBuilder.getUnableCheckUpdate(e.getMessage()));
			}
		});
	}

	private void isJavaSixteenMin() {

		try {
			int javaVersion = Integer.parseInt(System.getProperty("java.version").substring(0, 2));

			if (javaVersion < 16 ) {
				getLogger().severe("Please use Java 16 minimum");
			} else {
				getLogger().info("Java version : " + System.getProperty("java.version"));
			}
		} catch (NumberFormatException e) {
			getLogger().severe("Unable to determine java version");
		}
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(prefix + messageBuilder.getPluginDisable());
	}
}