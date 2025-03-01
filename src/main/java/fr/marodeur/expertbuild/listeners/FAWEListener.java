package fr.marodeur.expertbuild.listeners;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.lison.AdvancedParticleOperation;
import fr.marodeur.expertbuild.object.builderObjects.GohaParameter;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.ConvexPolyhedralRegion;
import com.sk89q.worldedit.regions.Region;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FAWEListener implements Listener {

	@EventHandler
	public void CLickAirWand(@NotNull PlayerInteractEvent event) {

		Player p = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();
		BukkitPlayer actor = BukkitAdapter.adapt(p);
		Logger log = Logger.getLogger("Expert-Build");
		Configuration conf = Main.getConfiguration();
		BrushBuilder bb;
		Material mat = conf.getWand_item();

		if (it == null) return;

		if (! BrushBuilder.containsPlayerBrush(p)) return;

		bb = BrushBuilder.getBrushBuilderPlayer(p);

		if (!conf.isWand_click_in_air()) {
			return;
		}

		if (it.getType() == mat && p.hasPermission("exp.selection.airpos")) {


			if (!new FaweAPI(p).getFarwandActive()) {

				if (action == Action.LEFT_CLICK_AIR && !p.isSneaking()) {

					if (bb.getFlyMode()) {

						new FaweAPI(p).setPrimaryPos(BlockVector3.at(
								p.getLocation().getX(),
								p.getLocation().getY(),
								p.getLocation().getZ()));

						if (new FaweAPI(p).isCompleteSelection()) {

							bb.sendMessage("expbuild.message.selection.set_pos_1_with_area",
									true, new String[]{
											(int) p.getLocation().getX() + ", "
													+ (int) p.getLocation().getY() + ", "
													+ (int) p.getLocation().getZ(), String.valueOf(actor.getSelection().getVolume())});

						} else {

							bb.sendMessage("expbuild.message.selection.set_pos_1",
									true, new String[]{
											(int) p.getLocation().getX() + ", "
													+ (int) p.getLocation().getY() + ", "
													+ (int) p.getLocation().getZ()
									});
						}
						if (conf.getlog_shortcut())
							log.info(new Message.MessageSender("expbuild.message.selection.player_log_command", false, new String[]{p.getName(), "//pos1"}).getMessage());
					}
				}

				if (action == Action.RIGHT_CLICK_AIR && !p.isSneaking()) {

					if (bb.getFlyMode()) {

						new FaweAPI(p).setSecondaryPos(BlockVector3.at(
								p.getLocation().getX(),
								p.getLocation().getY(),
								p.getLocation().getZ()));

						if (new FaweAPI(p).isCompleteSelection()) {

							bb.sendMessage("expbuild.message.selection.set_pos_2_with_area",
									true, new String[]{(int) p.getLocation().getX() + ", "
											+ (int) p.getLocation().getY() + ", "
											+ (int) p.getLocation().getZ(), String.valueOf(actor.getSelection().getVolume())});

						} else {

							bb.sendMessage("expbuild.message.selection.set_pos_2",
									true, new String[]{
											(int) p.getLocation().getX() + ", "
													+ (int) p.getLocation().getY() + ", "
													+ (int) p.getLocation().getZ()
									});
						}
						if (conf.getlog_shortcut())
							log.info(new Message.MessageSender("expbuild.message.selection.player_log_command", false, new String[]{p.getName(), "//pos2"}).getMessage());
					}
				}

				if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK && !p.isSneaking()) {

					if (bb.getFlyMode()) {

						try {

							Region region = actor.getSelection();

							if (region instanceof ConvexPolyhedralRegion) {

								List<BlockVector3> BlockVector3 = new ArrayList<>(((ConvexPolyhedralRegion) region).getVertices());

								if (BlockVector3.size() == 3 && conf.getDisplay_convex_line()) {

									for (int i = 1; i < BlockVector3.size(); i++) {

										new AdvancedParticleOperation(p)
												.lineParticle(
														new BlockVectorTool().toBlockVectorTool(BlockVector3.get(i)),
														new BlockVectorTool().toBlockVectorTool(BlockVector3.get(i - 1)),
														conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
														new AdvancedParticleOperation.RescheduledParticle[]{new AdvancedParticleOperation.RescheduledParticle().setParticleClearRegion(true)});

									}

								} else if (BlockVector3.size() > 3 && conf.getDisplay_convex_line()) {

									new AdvancedParticleOperation(p)
											.lineParticle(
													new BlockVectorTool().toBlockVectorTool(BlockVector3.get(BlockVector3.size() - 1)),
													new BlockVectorTool().toBlockVectorTool(BlockVector3.get(BlockVector3.size() - 2)),
													conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
													new AdvancedParticleOperation.RescheduledParticle[]{new AdvancedParticleOperation.RescheduledParticle().setParticleClearRegion(true)});

								}

								if (conf.isDisplay_bezier_curve()) {

									bb.setParticleID();

									new AdvancedParticleOperation(p)
											.bezierLineParticle(
													BlockVector3,
													conf.getParticle_bezier_curve_type(), conf.getCoefficient_particle_number(),
													new AdvancedParticleOperation.RescheduledParticle[]
															{new AdvancedParticleOperation.RescheduledParticle().setParticleClearRegion(true),
																	new AdvancedParticleOperation.RescheduledParticle().setParticleClearUpdateId(true)});
								}
							}
						} catch (IncompleteRegionException e) {
							log.info(new Message.MessageSender("expbuild.message.error.error_region", false, new String[]{"1", e.toString()}).getMessage());
						}
					}
				}
			}
		}
//		if (it.getType() == mat && p.hasPermission("exp.selection.clearselection")) {
//
//			if (action == Action.RIGHT_CLICK_BLOCK) {
//				try {
//
//					actor.getSelection();
//
//				} catch (IncompleteRegionException e) {
//					log.info(new Message.MessageSender("expbuild.message.error.error_region", false, new String[]{"3", e.toString()}).getMessage());
//				}
//			}
//		}
		if (it.getType() == mat && p.hasPermission("exp.selection.clearselection")) {

			if (action == Action.RIGHT_CLICK_AIR && p.isSneaking()) {

				if (!conf.isSihft_click_with_wand() || bb.getSelMode().equals(false)) {
					return;
				}

				new FaweAPI(p).clearSelection();
				bb.sendMessage("expbuild.message.selection.selection_clear", true);

				if (conf.getlog_shortcut()) log.info(new Message.MessageSender("expbuild.message.selection.player_log_command", false, new String[]{p.getName(), "//sel"}).getMessage());

			}
		}
	}

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

		String cmd = event.getMessage();
		Player p = event.getPlayer();

		if (cmd.equals("//sel")) {

			if (BrushBuilder.containsPlayerBrush(p)) {

				//Clear all particle
				BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);
				GohaParameter gohaParameter = brushBuilder.getGohaParameter();

				gohaParameter.setPregen(false)
						.setParticleID()
						.setStartLoc(null);
			}
		}
	}
}

