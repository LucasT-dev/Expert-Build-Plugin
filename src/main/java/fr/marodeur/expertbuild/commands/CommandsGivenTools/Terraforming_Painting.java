package fr.marodeur.expertbuild.commands.CommandsGivenTools;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.object.ItemBuilder;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;

import fr.marodeur.expertbuild.object.Message;
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
			s.sendMessage(new Message.MessageSender("expbuild.message.permission.console_not_execute_cmd", true).getMessage());
			return false;
		}

		if (!p.hasPermission("exp.command.tool")) {
			p.sendMessage(new Message.MessageSender("expbuild.message.permission.no_permission_node", true, new String[]{"exp.command.tool"}).getMessage());
			return false;
		}

		BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p, false);

		if (bb == null) {
			p.sendMessage(new Message.MessageSender("expbuild.message.permission.no_permission_node", true, new String[]{"exp.register"}).getMessage());
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

				p.sendMessage(new Message.MessageSender("expbuild.message.tools.give_tool", true, new String[]{"Voxel/FAVS"}).getMessage());

			}
			case "plume" -> {

				p.getInventory().setItem(slot, itFeather);

				p.sendMessage(new Message.MessageSender("expbuild.message.tools.give_tool", true, new String[]{"GoPaint"}).getMessage());

			}
			case "silex" -> {

				p.getInventory().setItem(slot, itFlint);

				p.sendMessage(new Message.MessageSender("expbuild.message.tools.give_tool", true, new String[]{"GoBrush"}).getMessage());

			}
			case "terra" -> {

				p.getInventory().setItem(0, itSpectralArrow);
				p.getInventory().setItem(1, itClayBall);
				p.getInventory().setItem(2, itFeather);
				p.getInventory().setItem(3, itFlint);
				p.getInventory().setItem(4, itHoney);

				p.sendMessage(new Message.MessageSender("expbuild.message.tools.give_tool", true, new String[]{"terraforming and painting"}).getMessage());

			}
			case "1" -> {

				if (!p.hasPermission("worldedit.selection.pos")) return false;

				new FaweAPI(p).setPrimaryPos(BlockVector3.at(
						p.getLocation().getX(),
						p.getLocation().getY(),
						p.getLocation().getZ()));

				if (new FaweAPI(p).isCompleteSelection()) {

					BukkitPlayer actor = BukkitAdapter.adapt(p);

					bb.sendMessage("expbuild.message.selection.set_pos_1_with_area",
							true,
							new String[]{
									(int) p.getLocation().getX() + ", "
							+ (int) p.getLocation().getY() + ", "
							+ (int) p.getLocation().getZ(),
									String.valueOf(actor.getSelection().getVolume())
					});

				} else {


					bb.sendMessage("expbuild.message.selection.set_pos_1",
							true,
							new String[]{
							(int) p.getLocation().getX() + ", "
									+ (int) p.getLocation().getY() + ", "
									+ (int) p.getLocation().getZ()
					});
				}
			}

			case "2" -> {

				if (!p.hasPermission("worldedit.selection.pos")) return false;

				new FaweAPI(p).setSecondaryPos(BlockVector3.at(
						p.getLocation().getX(),
						p.getLocation().getY(),
						p.getLocation().getZ()));

				if (new FaweAPI(p).isCompleteSelection()) {

					BukkitPlayer actor = BukkitAdapter.adapt(p);

					bb.sendMessage("expbuild.message.selection.set_pos_2_with_area",
							true,
							new String[]{
									(int) p.getLocation().getX() + ", "
											+ (int) p.getLocation().getY() + ", "
											+ (int) p.getLocation().getZ(),
									String.valueOf(actor.getSelection().getVolume())
					});

				} else {

					bb.sendMessage("expbuild.message.selection.set_pos_2",
							true,
							new String[]{
							(int) p.getLocation().getX() + ", "
									+ (int) p.getLocation().getY() + ", "
									+ (int) p.getLocation().getZ()
					});
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
				new FaweAPI(p).setSelectionType(RegionSelectorType.CUBOID);
				bb.sendMessage("expbuild.message.selection.set_selection", true, new String[]{"cuboid"});
			}

			case "convex" -> {
				new FaweAPI(p).setSelectionType(RegionSelectorType.CONVEX_POLYHEDRON);
				bb.sendMessage("expbuild.message.selection.set_selection", true, new String[]{"convex"});

			}

			case "poly" -> {
				new FaweAPI(p).setSelectionType(RegionSelectorType.POLYGON);
				bb.sendMessage("expbuild.message.selection.set_selection", true, new String[]{"poly"});

			}

			case "sel" -> {
				new FaweAPI(p).clearSelection();
				bb.sendMessage("expbuild.message.selection.selection_clear", true);

			}

			case "getcommand" -> {

				if (Main.getCommand.contains(p.getUniqueId())) {
					Main.getCommand.remove(p.getUniqueId());
					bb.sendMessage("expbuild.message.commands.disable", true, new String[]{"GetCommand"});
				} else {
					Main.getCommand.add(p.getUniqueId());
					bb.sendMessage("expbuild.message.commands.enable", true, new String[]{"GetCommand"});

				}
			}

			case "repeater" -> {

                if (bb.getEnable().equals(false)) {

					if (args.length == 1) {

						try {

							int tickRT = Integer.parseInt(args[0]);

							if (tickRT > 4 || tickRT < 1) {
								p.sendMessage(Main.prefix + " Use delay > 0 and < 5");
								return false;
							}
						} catch (NumberFormatException ignored) {
							p.sendMessage(Main.prefix + "Invalid number, use /repeater <delay (1-4)>");
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
