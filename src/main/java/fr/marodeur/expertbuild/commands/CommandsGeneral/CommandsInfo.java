package fr.marodeur.expertbuild.commands.CommandsGeneral;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.Message;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandsInfo implements CommandExecutor, TabCompleter {

	private final List<String> list = Arrays.asList("info", "reload", "sel_mode", "fly_mode", "test");

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {
		if (args.length <= 1) {
			List<String> l = new ArrayList<>();
			StringUtil.copyPartialMatches(args[0], this.list, l);
			return l;
		}
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if (!sender.hasPermission("exp.command.build")) {
			new Message.MessageSender("expbuild.message.permission.no_permission_node", true, new String[]{"exp.command.build"}).send(sender);
			return false;
		}

		if (cmd.getName().equalsIgnoreCase("expbuild")) {

			if (args.length == 0) {
				new Message.MessageSender("expbuild.message.commands.use", true, new String[]{"/expbuild <info/reload/sel_mode/fly_mode>"}).send(sender);
				return false;
			}
			if (args[0].equalsIgnoreCase("info")) {

				new Message.MessageSender("expbuild.message.main.plugin_info", new String[]{Main.getDataPlugin().getPluginVersion(), Main.getDataPlugin().getFaweVersion()}).send(sender);

				//Github Documentation
				TextComponent github_doc = new TextComponent("§5EXP-Build §7Doc : click here");
				github_doc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder("§7Open documentation URL").create()));
				github_doc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
						"https://github.com/LucasT-dev/Expert-Build-Plugin/wiki"));
				sender.spigot().sendMessage(github_doc);


				TextComponent gitHubURL = new TextComponent("§8Discord §7link : click here");
				gitHubURL.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder("§7Join official discord").create()));
				gitHubURL.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
						"https://discord.gg/R2q8HuzSmM"));
				sender.spigot().sendMessage(gitHubURL);
				return false;
			}

			if (args[0].equalsIgnoreCase("reload")) {

				Main.getInstance().reloadConfig();
				Main.getInstance().reloadMessageConfig();

				new Message.MessageSender("expbuild.message.main.config_load", true).send(sender);

				return false;
			}

			if (args[0].equalsIgnoreCase("sel_mode")) {
				if (sender instanceof Player p) {

					BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p);

					if (bb.getSelMode().equals(false)) {
						bb.setSelMode(true)
								.sendMessage("expbuild.message.commands.enable", true, new String[]{"Shift click executing /sel :"});
					} else {
						bb.setSelMode(false)
								.sendMessage("expbuild.message.commands.disable", true, new String[]{"Shift click executing /sel :"});
					}

                } else {
					new Message.MessageSender("expbuild.message.permission.console_not_execute_cmd", true).send(sender);
                }
                return false;
            }

			if (args[0].equalsIgnoreCase("fly_mode")) {
				if (sender instanceof Player p) {

					BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p);

					if (bb.getFlyMode().equals(false)) {
						bb.setFlyMode(true)
								.sendMessage("expbuild.message.commands.enable", true, new String[]{"Click air selection :"});
                    } else {
						bb.setFlyMode(false)
								.sendMessage("expbuild.message.commands.disable", true, new String[]{"Click air selection :"});
                    }

                } else {
					new Message.MessageSender("expbuild.message.permission.console_not_execute_cmd", true).send(sender);
                }
                return false;
            }


			if(args[0].equalsIgnoreCase("test")) {

				if (sender instanceof Player p) {

					//new FaweAPI(p).convertSchem(p);
				}

				//SchematicDowngradeHandler.readSchematic("C:/Bureau/Serveur MC/Server local paper 1.20.6/plugins/FastAsyncWorldEdit/schematics/test_1_21.schem",
						/*"C:/Bureau/Serveur MC/Server local paper 1.20.6/plugins/FastAsyncWorldEdit/schematics/test_1_21.schem"*/
						//(Player) sender);

//				System.out.println("BlockData.getDataBlock(Material.STONE) = " + BlockData.getDataBlock(Material.STONE).categoryBlockObject());
//
//				System.out.println("BlockData.containsBlock(Material.BEACON) = " + BlockData.containsBlock(Material.BEACON));
//
//				System.out.println("BlockData.getDataBlock(Material.FEATHER) = " + BlockData.getDataBlock(Material.FEATHER).categoryBlockObject());

				return false;


			} else {
				new Message.MessageSender("expbuild.message.commands.use", true, new String[]{"/expbuild <info, reload, sel_mode, fly_mode>"}).send(sender);
			}
		}
		return false;
	}
}