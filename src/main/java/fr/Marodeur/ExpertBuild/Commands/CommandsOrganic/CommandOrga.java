package fr.Marodeur.ExpertBuild.Commands.CommandsOrganic;

import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.GOHA_Builder;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;
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

public class CommandOrga implements CommandExecutor, TabCompleter {

	private static final MessageBuilder message = Main.getInstance().getMessageConfig();
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
			s.sendMessage(Main.prefix + message.getConsoleNotExecuteCmd());
			return false;
		}

		if (!p.isOp() | !p.hasPermission("exporga.use")) {
			p.sendMessage(Main.prefix + message.getDontPerm());
			return false;
		}

		if (cmd.getName().equalsIgnoreCase("organic")) {

			GOHA_Builder goha_builder = GOHA_Builder.getGOHABuilder(p);
			BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

			if (args.length == 0) {
				brushBuilder.sendMessage(message.getUse("/goha <material/pregen/clear/debug/generate>"));
				return false;
			}

			if (args[0].equalsIgnoreCase("pregen")) {

				goha_builder.setPregen(true)
						.setStartLoc(p.getLocation())
						.buildGoha(goha_builder);

				new GOHA_Builder.OrganicGeneration(p)
						.getAllPoint()
						.generateAllParticle();

				brushBuilder.sendMessage(message.getPregeneration());
			}

			if (args[0].equalsIgnoreCase("clear")) {

				goha_builder.setPregen(false)
						.setStartLoc(null)
						.buildGoha(goha_builder);

				brushBuilder.sendMessage(message.getSelectionClear());
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