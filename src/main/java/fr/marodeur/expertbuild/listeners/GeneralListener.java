package fr.marodeur.expertbuild.listeners;

import com.sk89q.worldedit.math.BlockVector3;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.commands.CommandAutoCb;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.builderObjects.AreaTimerParameter;
import fr.marodeur.expertbuild.object.builderObjects.GohaParameter;
import fr.marodeur.expertbuild.object.builderObjects.TimelapseBuilderParameter;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.CommandBlock;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class GeneralListener implements Listener {

	private final Configuration conf = Main.getConfiguration();

	@EventHandler
	public void onJoin(@NotNull PlayerJoinEvent e) {

		Player p = e.getPlayer();

		if (!p.hasPermission("exp.register")) return;

		if (!Main.containsBrushBuilder(p)) {
			BrushBuilder bb = BrushBuilder.registerPlayer(p, false);

			if (conf.getSendMessageBuilderRegister()) {
				bb.sendMessage("expbuild.message.brush.builder_profile_registered", true);
			}

		} else {
			BrushBuilder.registerPlayer(p, Main.getBrushBuilder(p));
		}

		//update system
		if (p.isOp()) {

			DataPlugin dataPlugin = Main.getDataPlugin();

			if (!dataPlugin.getPluginVersion().equals(dataPlugin.getLatestVersion())) {
				p.sendMessage(new Message.MessageSender("expbuild.message.main.new_update_available", true, new String[]{dataPlugin.getLateVersion(), dataPlugin.getPluginVersion(), dataPlugin.getLatestVersion()}).getMessage());
			}
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
					}
				}
			}
		}
	}

	@EventHandler
	private void onQuit(@NotNull PlayerQuitEvent e) {

		Player p = e.getPlayer();

		//Clear all particle
		if (Main.containsBrushBuilder(p)) {

			BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, false);
			GohaParameter goha_builder = brushBuilder.getGohaParameter();

			goha_builder.setPregen(false)
					.setParticleID()
					.setStartLoc(null);
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

		ItemStack handItemStack = p.getInventory().getItemInMainHand();

		if (handItemStack.getType().equals(Material.REPEATER) && handItemStack.getItemMeta().hasEnchant(Enchantment.LUCK)) {

			if (block.getType().equals(Material.REPEATER)) {
				return;
			}

			Location loc = new Location(p.getWorld(), block.getX(), block.getY() + 1, block.getZ());

			if (loc.getBlock().getType().equals(Material.AIR)) {

				loc.getBlock().setType(Material.REPEATER);

				Repeater rp1 = (Repeater) loc.getBlock().getBlockData();
				BlockFace blockFace = p.getFacing().getOppositeFace();

				rp1.setDelay(Integer.parseInt(handItemStack.getItemMeta().getLore().get(0).substring(handItemStack.getItemMeta().getLore().get(0).length()-1)));
				rp1.setFacing(blockFace);

				loc.getBlock().setBlockData(rp1);
				loc.getBlock().getState().update();

				e.setCancelled(true);
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
		UUID playerUUID = p.getUniqueId();
		BlockVectorTool to = new BlockVectorTool().toBlockVectorTool(e.getTo());
		BlockVectorTool from = new BlockVectorTool().toBlockVectorTool(e.getFrom());

		// GetCommand in cb

		if (Main.getCommand.contains(p.getUniqueId())) {

			Location loc = p.getTargetBlock(null, conf.getMax_brush_distance()).getLocation().clone();

			Block b = loc.getBlock();

			if (b.getType() != Material.COMMAND_BLOCK) return;

			CommandBlock commandBlock = (CommandBlock) b.getState();

			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§7" + commandBlock.getCommand()));
		}


		//AreaTimer command

		for (AreaTimerParameter areaTimerParameter : Main.getDataProfile().getAreaTimerParameterList()) {

			if (areaTimerParameter.isRunning()) {

				// Verifie si le joueur est entré dans la zone
				if (areaTimerParameter.isInArea(to, e.getTo().getWorld().getName())) {

					// Verifie si le joueur est pas déja enregistre dans la zone pour pouvoir l'enregister
					if (!areaTimerParameter.playerIsAlreadyRegisterInArea(playerUUID)) {
						areaTimerParameter.playerEnteredZone(playerUUID);
					}

				} else {

					// Verifie si le joueur est pas déja enregistre dans la zone
					if (areaTimerParameter.playerIsAlreadyRegisterInArea(playerUUID)) {
						areaTimerParameter.playerExitedZone(playerUUID);
					}
				}
			}
		}
	}

	/**
	 *
	 * Allows water not to regenerate during a timelapse
	 *
	 * @param e event BlockFromToEvent
	 */
	@EventHandler
	public void onBlockFromTo(BlockFromToEvent e) {

		Main.getHashMapBrushBuilder().forEach((uuid, brushBuilder) -> {

			TimelapseBuilderParameter timelapseBuilder = Main.getDataProfile().getTimelapseProfile(uuid);
			Location l = e.getBlock().getLocation();

			if (timelapseBuilder.hasTimelapseRunning()) {

				if (BlockVector3.at(l.getBlockX(), l.getBlockY(), l.getBlockZ()).containedWithin(timelapseBuilder.selection()[0], timelapseBuilder.selection()[1])) {
					e.setCancelled(true);
				}
			}
		});
	}
}