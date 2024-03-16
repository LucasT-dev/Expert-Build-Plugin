package fr.marodeur.expertbuild.listeners;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.LISON.AdvancedParticleVisualisation;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.ConvexPolyhedralRegion;
import com.sk89q.worldedit.regions.Region;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FAWEListener implements Listener {

	private static final MessageBuilder msg = Main.getInstance().getMessageConfig();

	@EventHandler
	public void CLickAirWand(@NotNull PlayerInteractEvent event) {

		Player p = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();
		BukkitPlayer actor = BukkitAdapter.adapt(p);
		BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p, false);
		Logger log = Logger.getLogger("Expert-Build");
		Configuration conf = Main.configuration();
		Material mat = conf.getWand_item();

		if (it == null) return;

		if (!p.hasPermission("exp.register")) return;

		if (!conf.isWand_click_in_air()) {
			return;
		}

		if (it.getType() == mat && p.hasPermission("exp.selection.airpos")) {

			if (action == Action.LEFT_CLICK_AIR && !p.isSneaking()) {

				if (bb.getFlyMode()) {

					new UtilsFAWE(p).setPrimaryPos(BlockVector3.at(
							p.getLocation().getX(),
							p.getLocation().getY(),
							p.getLocation().getZ()));

					if (new UtilsFAWE(p).isCompleteSelection()) {

						bb.sendMessage(msg.getSetPos1WithArea(
								(int) p.getLocation().getX() + ", "
										+ (int) p.getLocation().getY() + ", "
										+ (int) p.getLocation().getZ(), String.valueOf(actor.getSelection().getVolume())));
					} else {

						bb.sendMessage(msg.getSetPos1(
								(int) p.getLocation().getX() + ", "
										+ (int) p.getLocation().getY() + ", "
										+ (int) p.getLocation().getZ()));
					}


					if (conf.getlog_shortcut()) log.info(msg.getPlayerLogCommand(p.getName(), "//pos1"));

				}
			}

			if (action == Action.RIGHT_CLICK_AIR && !p.isSneaking()) {

				if (bb.getFlyMode()) {

					new UtilsFAWE(p).setSecondaryPos(BlockVector3.at(
							p.getLocation().getX(),
							p.getLocation().getY(),
							p.getLocation().getZ()));

					if (new UtilsFAWE(p).isCompleteSelection()) {

						bb.sendMessage(msg.getSetPos2WithArea(
								(int) p.getLocation().getX() + ", "
										+ (int) p.getLocation().getY() + ", "
										+ (int) p.getLocation().getZ(), String.valueOf(actor.getSelection().getVolume())));
					} else {

						bb.sendMessage(msg.getSetPos2(
								(int) p.getLocation().getX() + ", "
										+ (int) p.getLocation().getY() + ", "
										+ (int) p.getLocation().getZ()));
					}

					if (conf.getlog_shortcut()) log.info(msg.getPlayerLogCommand(p.getName(), "//pos2"));
				}
			}

			if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK && !p.isSneaking()) {


				try {

					Region region = actor.getSelection();

					if (region instanceof ConvexPolyhedralRegion) {

						List<BlockVector3> BlockVector3 = new ArrayList<>(((ConvexPolyhedralRegion) region).getVertices());

						if (BlockVector3.size() == 3 && conf.getDisplay_convex_line()) {

							for (int i = 1; i < BlockVector3.size(); i++) {

								new AdvancedParticleVisualisation(p)
										.lineParticle(
												BlockVector3.get(i),
												BlockVector3.get(i-1),
												conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
												new AdvancedParticleVisualisation.RescheduledParticle[] { new AdvancedParticleVisualisation.RescheduledParticle().setParticleClearRegion(true) });

							}

						} else if (BlockVector3.size() > 3 && conf.getDisplay_convex_line()) {

							new AdvancedParticleVisualisation(p)
									.lineParticle(
											BlockVector3.get(BlockVector3.size() - 1),
											BlockVector3.get(BlockVector3.size() - 2),
											conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
											new AdvancedParticleVisualisation.RescheduledParticle[] { new AdvancedParticleVisualisation.RescheduledParticle().setParticleClearRegion(true) });

						}

						if (conf.isDisplay_bezier_curve()) {

							bb.setParticleID();

							new AdvancedParticleVisualisation(p)
									.bezierLineParticle(
											BlockVector3,
											conf.getParticle_bezier_curve_type(), conf.getCoefficient_particle_number(),
											new AdvancedParticleVisualisation.RescheduledParticle[]
													{new AdvancedParticleVisualisation.RescheduledParticle().setParticleClearRegion(true),
															new AdvancedParticleVisualisation.RescheduledParticle().setParticleClearUpdateId(true)});
						}
					}
				} catch (IncompleteRegionException e) {
						log.info(msg.getErrorRegion("1", e.toString()));
				}
			}
		}
		if (it.getType() == mat && p.hasPermission("exp.selection.clearselection")) {

			if (action == Action.RIGHT_CLICK_BLOCK) {
				try {

					actor.getSelection();

				} catch (IncompleteRegionException e) {
					log.info(msg.getErrorRegion("3", e.toString()));
				}
			}
		}
		if (p.hasPermission("exp.selection.clearselection")) {

			if (action == Action.RIGHT_CLICK_AIR && p.isSneaking() && it.getType() == mat) {

				if (!conf.isSihft_click_with_wand() || bb.getSelMode().equals(false)) {
					return;
				}

				new UtilsFAWE(p).clearSelection();

				bb.sendMessage(msg.getSelectionClear());

				if (conf.getlog_shortcut()) log.info(msg.getPlayerLogCommand(p.getName(), "//sel"));
			}
		}
	}

	@EventHandler
	public void AddTabCompleteOnSchem(@NotNull TabCompleteEvent e) {

		if (e.getBuffer().equals("/schem load ") || e.getBuffer().equals("/schematic load ")) {

			CommandSender sender = e.getSender();

			if (!(sender instanceof Player)) {
				return;
			}

			Player p = ((Player) sender).getPlayer();

			e.setCompletions(new UtilsFAWE(p).getFileList());
		}
	}

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

		String cmd = event.getMessage();
		Player p = event.getPlayer();

		if (cmd.equals("//sel")) {

			//Clear all particle
			final GOHA_Builder goha_builder = GOHA_Builder.getGOHABuilder(p);

			goha_builder.setPregen(false)
					.setMomentallyParticleStop(false)
					.setStartLoc(null);

		}
	}
}

