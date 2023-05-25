package fr.Marodeur.ExpertBuild.Commands.CommandsOrganic;

import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.MsgEnum;
import fr.Marodeur.ExpertBuild.Object.GOHA_Builder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.Marodeur.ExpertBuild.Main.prefix;

public class CommandOrga implements CommandExecutor, TabCompleter {

	private final List<String> list = Arrays.asList("pregen", "clear", "generate");

	@Override
	public List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, String @NotNull [] args) {

		if (args.length <= 1) {
			List<String> l = new ArrayList<>();
			StringUtil.copyPartialMatches(args[0], this.list, l);
			return l;
		}
		if (args.length == 2 && args[0].equalsIgnoreCase("")) {
			List<String> l2 = new ArrayList<>();
			StringUtil.copyPartialMatches(args[1], this.list, l2);
			return l2;
		}
		return null;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, String[] args) {

		if (!(s instanceof Player p)) {
			new UtilsFAWE().sendMessage(s, MsgEnum.CONSOLE_NOT_EXECUTE_CMD);
			return false;
		}

		if (!p.isOp() | !p.hasPermission("exporga.use")) {
			new UtilsFAWE(p).sendMessage(MsgEnum.NOT_PERM);
			return false;
		}

		if (cmd.getName().equalsIgnoreCase("organic")) {

			GOHA_Builder goha_builder = GOHA_Builder.getGOHABuilder(p);

			if (args.length == 0) {
				p.sendMessage(prefix + "Use /goha <material/pregen/clear/debug/generate>");
				return false;
			}

			if (args[0].equalsIgnoreCase("pregen")) {

				goha_builder.setPregen(true)
						.setStartLoc(p.getLocation())
						.buildGoha(goha_builder);

				new GOHA_Builder.OrganicGeneration(p)
						.getAllPoint()
						.generateAllParticle();

				p.sendMessage(prefix + "Running pregen...");
			}

			if (args[0].equalsIgnoreCase("clear")) {

				goha_builder.setPregen(false)
						.setStartLoc(null)
						.buildGoha(goha_builder);

				p.sendMessage(prefix + "particle clear");
			}

			if (args[0].equalsIgnoreCase("generate")) {

				if (goha_builder.getStartLoc() != null) {

					new GOHA_Builder.OrganicGeneration(p, goha_builder.getStartLoc())
							.getAllPoint()
							.generateAllBlock()
							.buildBlock();
				} else {

					new GOHA_Builder.OrganicGeneration(p)
							.getAllPoint()
							.generateAllBlock()
							.buildBlock();
				}
			}
			return false;
		}
		return false;
	}
}