package fr.Marodeur.ExpertBuild.Commands.CommandsGivenTools;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.ItemBuilder;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Terraforming_Painting implements CommandExecutor {

	/**
	 * Executes the given command, returning its success.
	 * <br>
	 * If false is returned, then the "usage" plugin.yml entry for this command
	 * (if defined) will be sent to the player.
	 *
	 * @param s  Source of the command
	 * @param cmd Command which was executed
	 * @param msg   Alias of the command which was used
	 * @param args    Passed command arguments
	 * @return true if a valid command, otherwise false
	 */
	@Deprecated
	@Override
	public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, String[] args) {

		MessageBuilder message = Main.getInstance().getMessageConfig();

		if (!(s instanceof Player p)) {
			s.sendMessage(Main.prefix + message.getConsoleNotExecuteCmd());
			return false;
		}

		if (!p.isOp() || !p.hasPermission("exptool.use")) {
			p.sendMessage(Main.prefix + message.getDontPerm());
			return false;
		}

		BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p);

		ItemStack itArrow = new ItemBuilder(Material.ARROW, 1, 1).build();
		ItemStack itGunPowder = new ItemBuilder(Material.GUNPOWDER, 1, 1).build();
		ItemStack itFeather = new ItemBuilder(Material.FEATHER, 1, 1).build();
		ItemStack itFlint = new ItemBuilder(Material.FLINT, 1, 1).build();
		ItemStack itHoney = new ItemBuilder(Material.HONEYCOMB, 1, 1).build();


		switch (cmd.getName()) {

			case "pos" -> {

				String World = Objects.requireNonNull(p.getLocation().getWorld()).toString();
				int X = p.getLocation().getBlockX();
				int Y =  p.getLocation().getBlockY();
				int Z =  p.getLocation().getBlockZ();
				p.sendMessage(Main.prefix + "your position :");
				p.sendMessage("§8World = §7§o " + World);
				p.sendMessage("§8X =§7§o " + X);
				p.sendMessage("§8Y =§7§o " + Y);
				p.sendMessage("§8Z =§7§o " + Z);
			}

			case "vox" -> {

				p.getInventory().setItem(0, itArrow);
				p.getInventory().setItem(1, itGunPowder);

				bb.sendMessage(message.getGiveTool("Voxel/FAVS"));

			}
			case "plume" -> {

				p.getInventory().setItem(0, itFeather);

				bb.sendMessage(message.getGiveTool("GoPaint"));
			}
			case "silex" -> {

				p.getInventory().setItem(0, itFlint);

				bb.sendMessage(message.getGiveTool("GoBrush"));
			}
			case "terra" -> {

				p.getInventory().setItem(0, itArrow);
				p.getInventory().setItem(1, itGunPowder);
				p.getInventory().setItem(2, itFeather);
				p.getInventory().setItem(3, itFlint);
				p.getInventory().setItem(4, itHoney);

				bb.sendMessage(message.getGiveTool("terraforming and painting"));
			}
			case "1" -> {

				new UtilsFAWE(p).setPrimaryPos(BlockVector3.at(
						p.getLocation().getX(),
						p.getLocation().getY(),
						p.getLocation().getZ()));

				if (new UtilsFAWE(p).isCompleteSelection()) {

					BukkitPlayer actor = BukkitAdapter.adapt(p);

					bb.sendMessage(message.getSetPos1WithArea(
							(int) p.getLocation().getX() + ", "
									+ (int) p.getLocation().getY() + ", "
									+ (int) p.getLocation().getZ(), String.valueOf(actor.getSelection().getVolume())));
				} else {

					bb.sendMessage(message.getSetPos1(
							(int) p.getLocation().getX() + ", "
									+ (int) p.getLocation().getY() + ", "
									+ (int) p.getLocation().getZ()));
				}
			}

			case "2" -> {

				new UtilsFAWE(p).setSecondaryPos(BlockVector3.at(
						p.getLocation().getX(),
						p.getLocation().getY(),
						p.getLocation().getZ()));

				if (new UtilsFAWE(p).isCompleteSelection()) {

					BukkitPlayer actor = BukkitAdapter.adapt(p);

					bb.sendMessage(message.getSetPos2WithArea(
							(int) p.getLocation().getX() + ", "
									+ (int) p.getLocation().getY() + ", "
									+ (int) p.getLocation().getZ(), String.valueOf(actor.getSelection().getVolume())));
				} else {

					bb.sendMessage(message.getSetPos2(
							(int) p.getLocation().getX() + ", "
									+ (int) p.getLocation().getY() + ", "
									+ (int) p.getLocation().getZ()));
				}
			}

			case "c" -> new UtilsFAWE(p).CopySelection();

			case "p" -> new UtilsFAWE(p).pasteClipboard();

			case "pa" -> new UtilsFAWE(p).pasteClipboardIgnoreAir();

			case "cube" -> {
				new UtilsFAWE(p).setSelectionType(RegionSelectorType.CUBOID);
				bb.sendMessage(message.getSetSelection("cuboid"));

			}

			case "convex" -> {
				new UtilsFAWE(p).setSelectionType(RegionSelectorType.CONVEX_POLYHEDRON);
				bb.sendMessage(message.getSetSelection("convex"));
			}

			case "poly" -> {
				new UtilsFAWE(p).setSelectionType(RegionSelectorType.POLYGON);
				bb.sendMessage(message.getSetSelection("polygon"));
			}

			case "sel" -> {
				new UtilsFAWE(p).clearSelection();
				bb.sendMessage(message.getSelectionClear());
			}

			case "f" -> {

				if (args.length == 0) {
					p.sendMessage(Main.prefix + "flip");
					Bukkit.dispatchCommand(p, "/flip");
				}
				if (args[0].equalsIgnoreCase("up") || args[0].equalsIgnoreCase("down") || args[0].equalsIgnoreCase("south")
						|| args[0].equalsIgnoreCase("north") || args[0].equalsIgnoreCase("east")
						|| args[0].equalsIgnoreCase("west")) {
					p.sendMessage("§8[§7§oEXP-Build§8] §l>§l§7 flip §8" + args[0]);
					Bukkit.dispatchCommand(p, "/flip " + args[0]);
				}
			}
			case "s" -> {

				if (args.length == 0) {
					p.sendMessage(Main.help + "/s");
					p.sendMessage("§7  Usages : /s");
					p.sendMessage("§7  Arguments : <Block>");
					return false;
				}
				if (args[0] != null) {
					p.sendMessage(Main.prefix + "set §8" + args[0]);
					Bukkit.dispatchCommand(p, "/set " + args[0]);
				}
			}

			case "getcommand" -> {

				if (Main.getCommand.contains(p.getUniqueId())) {
					Main.getCommand.remove(p.getUniqueId());
					p.sendMessage(Main.prefix + "GetCommand Disable");
				} else {
					Main.getCommand.add(p.getUniqueId());
					p.sendMessage(Main.prefix + "GetCommand Enable");
				}
			}

			case "repeater" -> {

				BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);
				if (brushBuilder.getEnable().equals(false)) {

					if (args.length == 1) {

						if (Integer.parseInt(args[0]) > 4) {
							p.sendMessage(Main.prefix + " Use delay < 5");
						}

						brushBuilder.setTickRT(Integer.parseInt(args[0]))
								.setBrushType(BrushEnum.TICK_REPEATER)
								.setEnable(true)
								.Build(brushBuilder);

						ItemStack itemBuilder = new ItemBuilder("Intelligence repeater", Material.REPEATER, 1)
								.addLore("Delay : " + brushBuilder.getTickRT())
								.addEnchant(Enchantment.LUCK, 1)
								.build();

						p.getInventory().setItem(1, itemBuilder);
						p.sendMessage(Main.prefix + "Repeater give and enable ");

					}
				} else {
					brushBuilder.setBrushType(BrushEnum.NONE)
							.setEnable(false)
							.sendMessage(message.getBrushDisable())
							.Build(brushBuilder);
				}
			}
		}
		return false;
	}
}
