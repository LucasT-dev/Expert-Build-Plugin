package fr.marodeur.expertbuild.commands;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.api.TransferSchema;
import fr.marodeur.expertbuild.object.Configuration;
import fr.marodeur.expertbuild.object.MessageBuilder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandTransferSchem implements CommandExecutor, TabCompleter {

	private static final MessageBuilder message = Main.getInstance().getMessageConfig();
	private final Configuration conf = Main.configuration();

	private final List<String> serv =
			Arrays.asList(conf.getServerName1()+"", conf.getServerName2()+"",
					conf.getServerName3()+"", conf.getServerName4()+"");

	@Override
	public List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, String @NotNull [] args) {

		Player p = (Player) s;

		if (args.length <= 1) {
			List<String> l = new ArrayList<>();
			StringUtil.copyPartialMatches(args[0], new UtilsFAWE(p).getFileList(), l);
			return l;
		}

		if (args.length == 2) {
			List<String> l2 = new ArrayList<>();
			StringUtil.copyPartialMatches(args[1], this.serv, l2);
			return l2;
		}
		return null;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, String[] args) {

		if (!(s instanceof Player p)) {
			s.sendMessage(Main.prefix + message.getConsoleNotExecuteCmd());
			return false;
		}

		if (!p.hasPermission("exp.command.schemtrans")) {
			p.sendMessage(Main.prefix + message.getNoPermissionNode("exp.command.schemtrans"));
			return false;
		}

		BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);

		if (brushBuilder == null) {
			p.sendMessage(Main.prefix + message.getNoPermissionNode("exp.register"));
			return false;
		}

		if (cmd.getName().equalsIgnoreCase("schemtrans")) {

			if (args.length < 1) {

				brushBuilder.sendMessage(message.getUse("/schemtrans <schem> <server>"));
				return false;

			}

			if (args.length == 1) {

				brushBuilder.sendMessage(message.getUse("/schemtrans " + args[0] + " <server>"));
				return false;

			}

			if (args.length == 2) {


				File file = new File("plugins/FastAsyncWorldEdit/schematics/"+p.getUniqueId()+"/"+ args[0]);
				File file1 = new File("plugins/FastAsyncWorldEdit/schematics/"+args[0]);

				if (!file.exists() && !file1.exists()) {
					brushBuilder.sendMessage(message.getFileNotExist(args[0]));
					return false;
				}

				if ((file.length()/1024) > conf.getMax_file_size() && conf.getMax_file_size() != -1) {
					brushBuilder.sendMessage(message.getFileTooLarge());
					return false;
				}

				if (args[1].equalsIgnoreCase(conf.getServerName1())) {

					if (conf.getStateServer1().equals(false)) {
						brushBuilder.sendMessage(message.getServerOff());
						return false;
					}

					YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/ExpertBuild", "Server1.yml"));

					getConfig(p, c, args[0], args[1]);

					return false;
				}

				if (args[1].equalsIgnoreCase(conf.getServerName2())) {

					if (conf.getStateServer2().equals(false)) {
						brushBuilder.sendMessage(message.getServerOff());
						return false;
					}

					YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/ExpertBuild", "Server2.yml"));

					getConfig(p, c, args[0], args[1]);

					return false;
				}
				if (args[1].equalsIgnoreCase(conf.getServerName3())) {

					if (conf.getStateServer3().equals(false)) {
						brushBuilder.sendMessage(message.getServerOff());
						return false;
					}

					YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/ExpertBuild", "Server3.yml"));

					getConfig(p, c, args[0], args[1]);

					return false;
				}
				if (args[1].equalsIgnoreCase(conf.getServerName4())) {

					if (conf.getStateServer4().equals(false)) {
						brushBuilder.sendMessage(message.getServerOff());
						return false;
					}

					YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("plugins/ExpertBuild", "Server4.yml"));

					getConfig(p, c, args[0], args[1]);

					return false;
				} else {
					brushBuilder.sendMessage(message.getUnknownServer());
				}
			}
		}
		return false;
	}
	private void getConfig(@NotNull Player p, @NotNull YamlConfiguration c, String file, String server) {

		BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);

		if (brushBuilder == null) {
			p.sendMessage(Main.prefix + message.getNoPermissionNode("exp.register"));
			return;
		}

		brushBuilder.sendMessage(message.getTransfert());

		String user = c.getString("Server.user");
		String addressServer = c.getString("Server.ServerAddress");
		String mdp = c.getString("Server.mdp");
		int port = c.getInt("Server.port");

		new TransferSchema(p, file, addressServer, port, user, mdp, server);

	}
}