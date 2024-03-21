package fr.marodeur.expertbuild.commands.CommandsGivenTools;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.object.ItemBuilder;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;

import fr.marodeur.expertbuild.object.MessageBuilder;
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

		if (!p.hasPermission("exp.command.tool")) {
			p.sendMessage(Main.prefix + message.getNoPermissionNode("exp.command.tool"));
			return false;
		}

		BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p, false);

		if (bb == null) {
			p.sendMessage(Main.prefix + message.getNoPermissionNode("exp.register"));
			return false;
		}

		int slot = p.getInventory().getHeldItemSlot();

		ItemStack itArrow = new ItemBuilder(Material.ARROW, 1).build();
		ItemStack itGunPowder = new ItemBuilder(Material.GUNPOWDER, 1).build();
		ItemStack itFeather = new ItemBuilder(Material.FEATHER, 1).build();
		ItemStack itFlint = new ItemBuilder(Material.FLINT, 1).build();
		ItemStack itHoney = new ItemBuilder(Material.HONEYCOMB, 1).build();
		ItemStack itClayBall = new ItemBuilder(Material.CLAY_BALL, 1).build();
		ItemStack itSpectralArrow = new ItemBuilder(Material.SPECTRAL_ARROW, 1).build();


		switch (cmd.getName()) {

			case "pos" -> {

				String World = Objects.requireNonNull(p.getLocation().getWorld()).toString();
				int X = p.getLocation().getBlockX();
				int Y =  p.getLocation().getBlockY();
				int Z =  p.getLocation().getBlockZ();
				p.sendMessage(Main.prefix + "your position :");
				p.sendMessage("§8World = §7§o " + World.toString());
				p.sendMessage("§8X =§7§o " + X);
				p.sendMessage("§8Y =§7§o " + Y);
				p.sendMessage("§8Z =§7§o " + Z);
			}

			case "vox" -> {

				p.getInventory().setItem(slot, itArrow);
				p.getInventory().setItem(slot + 1, itGunPowder);

				bb.sendMessage(message.getGiveTool("Voxel/FAVS"));

			}
			case "plume" -> {

				p.getInventory().setItem(slot, itFeather);

				bb.sendMessage(message.getGiveTool("GoPaint"));
			}
			case "silex" -> {

				p.getInventory().setItem(slot, itFlint);

				bb.sendMessage(message.getGiveTool("GoBrush"));
			}
			case "terra" -> {

				p.getInventory().setItem(0, itSpectralArrow);
				p.getInventory().setItem(1, itClayBall);
				p.getInventory().setItem(2, itFeather);
				p.getInventory().setItem(3, itFlint);
				p.getInventory().setItem(4, itHoney);

				bb.sendMessage(message.getGiveTool("terraforming and painting"));
			}
			case "1" -> {

				if (!p.hasPermission("worldedit.selection.pos")) return false;

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

				if (!p.hasPermission("worldedit.selection.pos")) return false;

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

			case "c" -> {
				if (p.hasPermission("worldedit.clipboard.copy")) new UtilsFAWE(p).CopySelection(true);
			}

			case "p" -> {
				if (p.hasPermission("worldedit.clipboard.paste")) new UtilsFAWE(p).pasteClipboard();
			}

			case "pa" -> {
				if (p.hasPermission("worldedit.clipboard.paste")) new UtilsFAWE(p).pasteClipboardIgnoreAir();
			}

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

				BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);

				if (brushBuilder == null) {
					p.sendMessage(Main.prefix + message.getNoPermissionNode("exp.command.tool"));
					return false;
				}

				if (brushBuilder.getEnable().equals(false)) {

					if (args.length == 1) {

						try {

							int tickRT = Integer.parseInt(args[0]);

							if (tickRT > 4 || tickRT < 1) {
								p.sendMessage(Main.prefix + " Use delay > 0 and < 5");
								return false;
							}
						} catch (NumberFormatException ignored) {
							p.sendMessage(Main.prefix + "Invlid number, use /repeater <delay (1-4)>");
							return false;
						}

						ItemStack itemBuilder = new ItemBuilder("Intelligence repeater", Material.REPEATER, 1)
								.addLore("Delay : " + Integer.parseInt(args[0]))
								.addEnchant(Enchantment.LUCK, 1)
								.build();

						p.getInventory().setItem(slot, itemBuilder);
						p.sendMessage(Main.prefix + "Repeater give and enable ");

					} else {
						p.sendMessage(Main.prefix + "Invlid number, use /repeater <delay (1-4)>");
						return false;
					}
				}
			}
		}
		return false;
	}
}
