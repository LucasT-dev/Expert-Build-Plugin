package fr.Marodeur.ExpertBuild.Commands.CommandsGivenTools;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Enum.MsgEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.ItemBuilder;
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

		if (!(s instanceof Player p)) {
			new UtilsFAWE().sendMessage(s, MsgEnum.CONSOLE_NOT_EXECUTE_CMD);
			return false;
		}

		if (!p.isOp() || !p.hasPermission("exptool.use")) {
			new UtilsFAWE().sendMessage(s, MsgEnum.NOT_PERM);
			return false;
		}

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
				new UtilsFAWE(p)
						.sendMessage(MsgEnum.GIVE_TOOL, "<tool_type>", "Voxel/FAVS");
			}
			case "plume" -> {

				p.getInventory().setItem(0, itFeather);
				p.sendMessage(Main.prefix + "GoPaint tools given");
			}
			case "silex" -> {

				p.getInventory().setItem(0, itFlint);
				p.sendMessage(Main.prefix + "GoBrush tools given");
			}
			case "terra" -> {

				p.getInventory().setItem(0, itArrow);
				p.getInventory().setItem(1, itGunPowder);
				p.getInventory().setItem(2, itFeather);
				p.getInventory().setItem(3, itFlint);
				p.getInventory().setItem(4, itHoney);
				p.sendMessage(Main.prefix + "Terraforming and painting tools given");
			}
			case "1" -> new UtilsFAWE(p).setPrimaryPos(BlockVector3.at(
					p.getLocation().getX(),
					p.getLocation().getY(),
					p.getLocation().getZ()))
					.sendMessage(MsgEnum.SET_PRIMARY_POS,
							"<pos>",
							"(" + (int) p.getLocation().getX() + ", "
										+ (int) p.getLocation().getY() + ", "
										+ (int) p.getLocation().getZ() + ")");

			case "2" -> new UtilsFAWE(p).setSecondaryPos(BlockVector3.at(
					p.getLocation().getX(),
					p.getLocation().getY(),
					p.getLocation().getZ()))
					.sendMessage(MsgEnum.SET_SECONDARY_POS,
							"<pos>",
							"(" + (int) p.getLocation().getX() + ", "
										+ (int) p.getLocation().getY() + ", "
										+ (int) p.getLocation().getZ() + ")");

			case "c" -> new UtilsFAWE(p).CopySelection();

			case "p" -> new UtilsFAWE(p).pasteClipboard();

			case "pa" -> new UtilsFAWE(p).pasteClipboardIgnoreAir();

			case "cube" -> new UtilsFAWE(p).setSelectionType(RegionSelectorType.CUBOID)
					.sendMessage(MsgEnum.SET_SELECTION, "<selection_type>", "cuboid");

			case "convex" -> new UtilsFAWE(p).setSelectionType(RegionSelectorType.CONVEX_POLYHEDRON)
					.sendMessage(MsgEnum.SET_SELECTION, "<selection_type>", "convex");

			case "poly" -> new UtilsFAWE(p).setSelectionType(RegionSelectorType.POLYGON)
					.sendMessage(MsgEnum.SET_SELECTION, "<selection_type>", "polygon");

			case "sel" -> new UtilsFAWE(p).clearSelection()
					.sendMessage(MsgEnum.SELECTION_CLEAR);

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
							.sendMessage(p, MsgEnum.BRUSH_DISABLE)
							.Build(brushBuilder);
				}
			}
		}
		return false;
	}
}
