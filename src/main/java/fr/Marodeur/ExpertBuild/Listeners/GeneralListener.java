package fr.Marodeur.ExpertBuild.Listeners;

import com.sk89q.worldedit.math.BlockVector3;

import fr.Marodeur.ExpertBuild.Commands.CommandAutoCb;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Enum.FaceDirection;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.Configuration;
import fr.Marodeur.ExpertBuild.Object.GOHA_Builder;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.CommandBlock;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GeneralListener implements Listener {

	private final Configuration conf = Main.getInstance().getConfig();
	private final MessageBuilder message = Main.getInstance().getMessageConfig();

	@EventHandler
	public void onJoin(@NotNull PlayerJoinEvent e) {

		Player p = e.getPlayer();

		if (!p.hasPermission("exp.register")) return;

		if (!Main.containsGohaBuilder(p)) {
			GOHA_Builder.registerPlayer(p);
		}

		if (!Main.containsBrushBuilder(p)) {
			BrushBuilder bb = BrushBuilder.registerPlayer(p, false);
			bb.sendMessage(message.getBuilderProfileRegistered());
		}

		if (p.isOp()) {
			//update system
			Main.updateChecker(version -> {
				if (Main.getVersion().equals(version)) {
					p.sendMessage(Main.prefix + message.getNotNewUpdate());
				} else {
					p.sendMessage(Main.prefix + message.getNewUpdateAvailable(Main.latestVersion, Main.getVersion(), Main.latestVersion));
				}
			}, Main.id);
		}


		if (p.hasPermission("worldedit.wand")) {
			//patch Fawe bug
			if (p.getInventory().contains(Material.WOODEN_AXE)) {
				for (int i = 0; i < 9; i++) {

					if (p.getInventory().getItem(i) == null) {
						continue;
					}

					if (Objects.requireNonNull(p.getInventory().getItem(i)).getType().equals(Material.WOODEN_AXE)) {
						p.getInventory().setHeldItemSlot(i);
						p.getInventory().remove(Material.WOODEN_AXE);
						Bukkit.dispatchCommand(p, "/wand");
						return;
					}
				}
			}
		}
	}

	@EventHandler
	private void onQuit(@NotNull PlayerQuitEvent e) {

		Player p = e.getPlayer();

		if (Main.containsBrushBuilder(p)) {
			Main.removeBrushBuilder(p);
		}
	}

	@EventHandler
	public void setIntelligenceRepeater(@NotNull PlayerInteractEvent e) {

		ItemStack it = e.getItem();
		Block block = e.getClickedBlock();
		Player p = e.getPlayer();
		Action a = e.getAction();

		if (!p.hasPermission("exp.command.tool")) {
			return;
		}

		if (block == null)
			return;

		if (it == null)
			return;

		if (a != Action.RIGHT_CLICK_BLOCK)
			return;

		if (p.getInventory().getItemInMainHand().getType().equals(Material.REPEATER)) {

			if (block.getType().equals(Material.REPEATER)) {
				return;
			}

			BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);

			if (brushBuilder == null) {
				return;
			}

			if (brushBuilder.getEnable().equals(true) && brushBuilder.getEnable().equals(true)) {

				Location loc = new Location(p.getWorld(), block.getX(), block.getY() +1, block.getZ());

				if (loc.getBlock().getType().equals(Material.AIR)) {

					loc.getBlock().setType(Material.REPEATER);

					Repeater rp1 = (Repeater) loc.getBlock().getBlockData();
					BlockFace blockFace = p.getFacing().getOppositeFace();

					rp1.setDelay(brushBuilder.getTickRT());
					rp1.setFacing(blockFace);

					loc.getBlock().setBlockData(rp1);
					loc.getBlock().getState().update();

					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onBlockPlace(@NotNull BlockPlaceEvent e) {

		Player p = e.getPlayer();
		Block b = e.getBlock();

		if (p.getUniqueId() != CommandAutoCb.uuid) return;

		if (b.getType() != Material.COMMAND_BLOCK) return;

		if (CommandAutoCb.state == CommandAutoCb.list.size()) {
			p.sendMessage(Main.prefix + "Auto cb finish");
			CommandAutoCb.uuid = null;
		}

		CommandBlock commandBlock = (CommandBlock) b.getState();
		commandBlock.setCommand(CommandAutoCb.list.get(CommandAutoCb.state));
		commandBlock.update();
		CommandAutoCb.state++;
	}

	@EventHandler
	public void onMove(@NotNull PlayerMoveEvent e) {
		Player p = e.getPlayer();

		if (Main.getCommand.contains(p.getUniqueId())) {

			Location loc = p.getTargetBlock(null, conf.getMax_brush_distance()).getLocation().clone();

			Block b = loc.getBlock();

			if (b.getType() != Material.COMMAND_BLOCK) return;

			CommandBlock commandBlock = (CommandBlock) b.getState();

			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§7" + commandBlock.getCommand()));
		}
	}
	@EventHandler
	private void onPlaceBlock(@NotNull BlockPlaceEvent e) {

		Player p = e.getPlayer();
		Block block = e.getBlock();
		BlockData blockData = block.getState().getBlockData();
		BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);
		Location loc = block.getLocation();

		if (brushBuilder == null) {
			return;
		}

		if (brushBuilder.getBrushType().equals(BrushEnum.AUTOFLIP) && brushBuilder.getEnable().equals(true)) {

			BlockVector3 bv3 = brushBuilder.getBlockVec4List().get(0).toBlockVector3();

			if (brushBuilder.getBlockFace().equals(BlockFace.UP)) {


				if (loc.getY() > bv3.getY()) {

					int y = (int) (block.getLocation().getY() - bv3.getY());

					if (setBlock(new Location(p.getWorld(), block.getX(), bv3.getY()-y, block.getZ()), block, brushBuilder).equals(true)) {
						new Location(p.getWorld(), block.getX(), bv3.getY()-y, block.getZ()).getBlock().setBlockData(blockData);
						//l.getBlock().setBlockData(blockData);
					}
					return;
				}

				if (loc.getY() < bv3.getY()) {

					int y = bv3.getY() - (int) (block.getLocation().getY());

					if (setBlock(new Location(p.getWorld(), block.getX(), bv3.getY()+y, block.getZ()), block, brushBuilder).equals(true)) {

						new Location(p.getWorld(), block.getX(), bv3.getY()+y, block.getZ()).getBlock().setBlockData(blockData);

					}
					return;
				}
			}

			if (brushBuilder.getBlockFace().equals(BlockFace.WEST)) {

				if (loc.getX() > bv3.getX()) {

					int x = (int) (block.getLocation().getX() - bv3.getX());

					if (setBlock(new Location(p.getWorld(), bv3.getX() - x, block.getY(), block.getZ()), block, brushBuilder).equals(true)) {
						new Location(p.getWorld(), bv3.getX() - x, block.getY(), block.getZ()).getBlock().setBlockData(blockData);
					}
					return;
				}

				if (loc.getX() < bv3.getX()) {

					int x = bv3.getX() - (int) (block.getLocation().getX());

					if (setBlock(new Location(p.getWorld(), bv3.getX() + x, block.getY(), block.getZ()), block, brushBuilder).equals(true)) {

						new Location(p.getWorld(), bv3.getX() + x, block.getY(), block.getZ()).getBlock().setBlockData(blockData);
					}
					return;
				}
			}

			if (brushBuilder.getBlockFace().equals(BlockFace.NORTH)) {

				if (loc.getZ() > bv3.getZ()) {

					int z = (int) (block.getLocation().getZ() - brushBuilder.getRegion().getMaximumPoint().getZ());

					if (setBlock(new Location(p.getWorld(), block.getX(), block.getY(), bv3.getZ() - z), block, brushBuilder).equals(true)) {

						new Location(p.getWorld(), block.getX(), block.getY(), bv3.getZ() - z).getBlock().setBlockData(blockData);
					}
					return;
				}

				if (loc.getZ() < bv3.getZ()) {

					int z = bv3.getZ() - (int) (block.getLocation().getZ());

					if (setBlock(new Location(p.getWorld(), block.getX(), block.getY(), bv3.getZ() + z), block, brushBuilder).equals(true)) {

						new Location(p.getWorld(), block.getX(), block.getY(), bv3.getZ() + z).getBlock().setBlockData(blockData);
					}
				}
			}
		}
	}

	private static @NotNull Boolean setBlock(Location l, @NotNull Block block, BrushBuilder brushBuilder) {

		if (block.getType().name().contains("SLAB")) {

			if (brushBuilder.getBlockFace().equals(BlockFace.UP)) {

				Slab slab = (Slab) block.getBlockData();

				if (slab.getType().equals(Slab.Type.TOP)) {
					slab.setType(Slab.Type.BOTTOM);
					l.getBlock().setBlockData(slab);
					return false;
				}
				if (slab.getType().equals(Slab.Type.BOTTOM)) {
					slab.setType(Slab.Type.TOP);
					l.getBlock().setBlockData(slab);
					return false;
				}
			}
		}
		if (block.getType().name().contains("STAIR")) {

			if (brushBuilder.getBlockFace().equals(BlockFace.UP)) {
				Stairs stairs = (Stairs) block.getBlockData();

				if (stairs.getHalf().equals(Bisected.Half.TOP)) {
					stairs.setHalf(Bisected.Half.BOTTOM);
					l.getBlock().setBlockData(stairs);
					return false;
				}

				if (stairs.getHalf().equals(Bisected.Half.BOTTOM)) {
					stairs.setHalf(Bisected.Half.TOP);
					l.getBlock().setBlockData(stairs);
					return false;
				}
			}
			if (brushBuilder.getBlockFace().equals(BlockFace.WEST)) {
				Stairs stairs = (Stairs) block.getBlockData();

				if (!stairs.getShape().equals(Stairs.Shape.STRAIGHT)) {
					stairs.setShape(FaceDirection.getOppositeFaceAngle(stairs.getShape()));
				}

				if (stairs.getFacing().equals(BlockFace.WEST) || stairs.getFacing().equals(BlockFace.EAST)) {

					stairs.setFacing(FaceDirection.getOppositeFaceDirection(stairs.getFacing()));
					l.getBlock().setBlockData(stairs);
					return false;
				} else {
					l.getBlock().setBlockData(stairs);
					return false;
				}
			}
			if (brushBuilder.getBlockFace().equals(BlockFace.NORTH)) {
				Stairs stairs = (Stairs) block.getBlockData();

				if (!stairs.getShape().equals(Stairs.Shape.STRAIGHT)) {
					stairs.setShape(FaceDirection.getOppositeFaceAngle(stairs.getShape()));
				}

				if (stairs.getFacing().equals(BlockFace.NORTH) || stairs.getFacing().equals(BlockFace.SOUTH)) {

					stairs.setFacing(FaceDirection.getOppositeFaceDirection(stairs.getFacing()));
					l.getBlock().setBlockData(stairs);
					return false;
				} else {
					l.getBlock().setBlockData(stairs);
					return false;
				}
			}
		} else {
			return true;
		}
		return true;
	}

	@EventHandler
	public void onBreakBlock(@NotNull BlockBreakEvent e) {

		Player p = e.getPlayer();
		Block block = e.getBlock();
		BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);
		Location loc = block.getLocation();

		if (brushBuilder == null) {
			return;
		}

		if (brushBuilder.getBrushType().equals(BrushEnum.AUTOFLIP) && brushBuilder.getEnable().equals(true)) {

			BlockVector3 bv3 = brushBuilder.getBlockVec4List().get(0).toBlockVector3();

			if (brushBuilder.getBlockFace().equals(BlockFace.UP)) {

				if (loc.getY() > bv3.getY()) {

					int y = (int) (block.getLocation().getY() - bv3.getY());
					new Location(p.getWorld(), block.getX(), bv3.getY() - y, block.getZ()).getBlock().setType(Material.AIR);
					return;
				}

				if (loc.getY() < bv3.getY()) {

					int y = bv3.getY() - (int) (block.getLocation().getY());
					new Location(p.getWorld(), block.getX(), bv3.getY() + y, block.getZ()).getBlock().setType(Material.AIR);
					return;
				}
			}

			if (brushBuilder.getBlockFace().equals(BlockFace.WEST)) {

				if (loc.getX() > bv3.getX()) {

					int x = (int) (block.getLocation().getX() - bv3.getX());

					new Location(p.getWorld(), bv3.getX() - x, block.getY(), block.getZ()).getBlock().setType(Material.AIR);

					return;
				}

				if (loc.getX() < bv3.getX()) {

					int x = bv3.getX() - (int) (block.getLocation().getX());

					new Location(p.getWorld(), bv3.getX() + x, block.getY(), block.getZ()).getBlock().setType(Material.AIR);
					return;
				}
			}

			if (brushBuilder.getBlockFace().equals(BlockFace.NORTH)) {

				if (loc.getZ() > bv3.getZ()) {

					int z = (int) (block.getLocation().getZ() - bv3.getZ());

					new Location(p.getWorld(), block.getX(), block.getY(), bv3.getZ() - z).getBlock().setType(Material.AIR);
					return;
				}

				if (loc.getZ() < bv3.getZ()) {

					int z = bv3.getZ() - (int) (block.getLocation().getZ());

					new Location(p.getWorld(), block.getX(), block.getY(), bv3.getZ() + z).getBlock().setType(Material.AIR);
				}
			}
		}
	}
}