package fr.marodeur.expertbuild.commands.CommandsGeneral;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.MessageBuilder;

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
import org.jetbrains.annotations.NotNull;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandsInfo implements CommandExecutor, TabCompleter {

	private static final MessageBuilder message = Main.getInstance().getMessageConfig();
	private final List<String> list = Arrays.asList("info", "version", "help", "reload", "sel_mode", "fly_mode");

	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String msg, String @NotNull [] args) {
		if (args.length <= 1) {
			List<String> l = new ArrayList<>();
			StringUtil.copyPartialMatches(args[0], this.list, l);
			return l;
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String msg, String[] args) {

		if (!sender.hasPermission("exp.command.build")) {
			sender.sendMessage(Main.prefix + message.getNoPermissionNode("exp.command.build"));
			return false;
		}

		if (cmd.getName().equalsIgnoreCase("expbuild")) {
			if (args.length == 0) {
				sender.sendMessage(Main.prefix + message.getUse("/expbuild <info/version/help/reload>"));
				return false;
			}
			if (args[0].equalsIgnoreCase("info")) {
				sender.sendMessage("§7--- §8[§5§oEXP-Build§8] §7---");

				TextComponent URL_Discord = new TextComponent("§1Discord §7link : click here");
				URL_Discord.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder("§7Open Discord URL").create()));
				URL_Discord.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/rJeJu8AcmB"));
				sender.spigot().sendMessage(URL_Discord);

				TextComponent URL_Youtube = new TextComponent("§8GitHub §7link : click here");
				URL_Youtube.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder("§7Open GitHub report bug").create()));
				URL_Youtube.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
						"https://github.com/LucasT-dev/Expert-Build-Plugin/issues"));
				sender.spigot().sendMessage(URL_Youtube);
				return false;
			}

			if (args[0].equalsIgnoreCase("version")) {
				sender.sendMessage(Main.prefix + " Version : " + Main.getInstance().getDescription().getVersion());
				return false;
			}

			if (args[0].equalsIgnoreCase("help")) {

				sender.sendMessage("§7--- §8[§5§oEXP-Build§8] §7---");
				TextComponent github_doc = new TextComponent("§5EXP-Build §7Doc : click here");
				github_doc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder("§7Open documentation URL").create()));
				github_doc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
						"https://github.com/LucasT-dev/Expert-Build-Plugin/blob/master/README.md"));
				sender.spigot().sendMessage(github_doc);
				return false;
			}
			if (args[0].equalsIgnoreCase("reload")) {

				Main.getInstance().reloadConfig();
				try {
					Main.getInstance().reloadMessageConfig();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}

				sender.sendMessage(Main.prefix + message.getConfigLoad());
			}

			if (args[0].equalsIgnoreCase("sel_mode")) {
				if (sender instanceof Player p) {

					BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p, true);

					if (bb.getSelMode().equals(false)) {
						bb.setSelMode(true)
								.sendMessage("Shift click executing /sel, enable");
						return false;

					} else {
						bb.setSelMode(false)
								.sendMessage("Shift click executing /sel, disable");
						return false;
					}

				} else {
					sender.sendMessage(Main.prefix + message.getConsoleNotExecuteCmd());
					return false;
				}
			}

			if (args[0].equalsIgnoreCase("fly_mode")) {
				if (sender instanceof Player p) {

					BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p, true);

					if (bb.getFlyMode().equals(false)) {
						bb.setFlyMode(true)
								.sendMessage("Click air selection enable");
						return false;
					} else {
						bb.setFlyMode(false)
								.sendMessage("Click air selection disable");
						return false;
					}

				} else {
					sender.sendMessage(Main.prefix + message.getConsoleNotExecuteCmd());
					return false;
				}
			}
		}
		return false;
	}
}