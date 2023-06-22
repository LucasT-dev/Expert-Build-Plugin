package fr.Marodeur.ExpertBuild.Listeners;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.ConvexPolyhedralRegion;
import com.sk89q.worldedit.regions.Region;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.Configuration;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;
import fr.Marodeur.ExpertBuild.Utils.LineVisualize;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FAWEListener implements Listener {

	private static final MessageBuilder message = Main.getInstance().getMessageConfig();

	@EventHandler
	public void CLickAirWand(@NotNull PlayerInteractEvent event) {

		Player p = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();
		BukkitPlayer actor = BukkitAdapter.adapt(p);
		Configuration conf = Main.getInstance().getConfig();
		BrushBuilder bb = BrushBuilder.getBrushBuilderPlayer(p);
		Logger log = Logger.getLogger("Expert-Build");
		Material mat = conf.getWand_item();

		if (it == null) return;

		if (it.getType() == mat && actor.hasPermission("worldedit.selection.pos")) {

			if (!conf.isWand_click_in_air()) { return; }

			if (action == Action.LEFT_CLICK_AIR && !p.isSneaking()) {

				new UtilsFAWE(p).setPrimaryPos(BlockVector3.at(
								p.getLocation().getX(),
								p.getLocation().getY(),
								p.getLocation().getZ()));

				if (new UtilsFAWE(p).isCompleteSelection()) {

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

			if (action == Action.RIGHT_CLICK_AIR && !p.isSneaking()) {

				new UtilsFAWE(p).setSecondaryPos(BlockVector3.at(
								p.getLocation().getX(),
								p.getLocation().getY(),
								p.getLocation().getZ()));

				if (new UtilsFAWE(p).isCompleteSelection()) {

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

				try {

					Region region = actor.getSelection();

					if (region instanceof ConvexPolyhedralRegion) {

						List<BlockVector3> BlockVector3 = new ArrayList<>(((ConvexPolyhedralRegion) region).getVertices());

						LineVisualize.generate_line(p,
								BlockVector3.get(BlockVector3.size() - 1),
								BlockVector3.get(BlockVector3.size() - 2));

						if (BlockVector3.size() >=3 && BlockVector3.size() % 2 == 0 && conf.isDisplay_bezier_curve()) {

							LineVisualize.bezierCurveDisplay(p, actor, BlockVector3.get(BlockVector3.size() - 1),
									BlockVector3.get(BlockVector3.size() - 2),
									BlockVector3.get(BlockVector3.size() - 3));
						}
					}

				} catch (IncompleteRegionException e) {

					log.info(message.getErrorRegion("1", e.toString()));

					List<BlockVector3> bv3 = new ArrayList<>(new ConvexPolyhedralRegion(actor.getWorld()).getVertices());

					try {
						new UtilsFAWE(p).getPrimaryPos();
					} catch (IncompleteRegionException e1) {

						log.info(message.getErrorRegion("2", e.toString()));
						return;
					}

					if (bv3.size() == 0) {

						LineVisualize.generate_line(p, BlockVector3.at(
								p.getLocation().getX(),
								p.getLocation().getY(),
								p.getLocation().getZ()), new UtilsFAWE(p).getPrimaryPos());

						return;
					}
				}
			}
			if (action == Action.RIGHT_CLICK_BLOCK) {
				try {

					actor.getSelection();

				} catch (IncompleteRegionException e) {
					log.info(message.getErrorRegion("3", e.toString()));
				}
			}
		}

		if (action == Action.RIGHT_CLICK_AIR && p.isSneaking() && it.getType() == mat) {
			if (!conf.isSihft_click_with_wand() || bb.getSelMode().equals(false)) { return; }

			new UtilsFAWE(p).clearSelection();
			bb.sendMessage(message.getSelectionClear());
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
}

