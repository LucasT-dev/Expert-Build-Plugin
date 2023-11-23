package fr.marodeur.expertbuild.utils;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.GOHA_Builder;
import fr.marodeur.expertbuild.object.MessageBuilder;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LineVisualize {

	private static final int space = 1;
	private static final Logger logger = Logger.getLogger("Expert-Build");
	private static final MessageBuilder message = Main.getInstance().getMessageConfig();

	public static void generate_line(@NotNull Player p, @NotNull BlockVector3 Bv1, @NotNull BlockVector3 Bv2) {

		Location l1 = new Location(p.getWorld(), Bv1.getX(), Bv1.getY(), Bv1.getZ());
		Location l2 = new Location(p.getWorld(), Bv2.getX(), Bv2.getY(), Bv2.getZ());

		new BukkitRunnable() {
			@Override
			public void run() {

				try {

					Region region = BukkitAdapter.adapt(p).getSession().getSelection();

					double distance = l1.distance(l2);
					Vector p1 = l1.toVector();
					Vector p2 = l2.toVector();
					Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
					double length = 0;

					for (; length < distance; p1.add(vector)) {
						p.spawnParticle(Particle.FLAME, p1.getX(), p1.getY(), p1.getZ(), (int) 0.01);
						length += space;
					}
				} catch (IncompleteRegionException e) {
					logger.info(message.getIncompleteSelection());
					cancel();
				}
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 0, 10);
	}

	public static void bezierCurveDisplay(@NotNull Player p, Actor actor, @NotNull BlockVector3 l1, @NotNull BlockVector3 l2, @NotNull BlockVector3 l3) {

		Location p0 = new Location(p.getWorld(), l1.getX(), l1.getY(), l1.getZ());
		Location p1 = new Location(p.getWorld(), l2.getX(), l2.getY(), l2.getZ());
		Location p2 = new Location(p.getWorld(), l3.getX(), l3.getY(), l3.getZ());
		//these 3 locations were debugged and are valid.

		new BukkitRunnable() {
			@Override
			public void run() {

				try {
					Region region = actor.getSession().getSelection();

				} catch (IncompleteRegionException e) {
					logger.info(message.getIncompleteSelection());
					cancel();
				}

				List<Location> curve = bezierCurve(50, p0, p1, p2);
				for (Location point : curve) {
					p.spawnParticle(Particle.CLOUD, point.getX(), point.getY(), point.getZ(), (int) 0.01);
				}
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 0, 10);
	}


	public static @NotNull Location bezierPoint(float t, @NotNull Location p0, @NotNull Location p1, @NotNull Location p2) {
		float a = ((1 - t) * (1 - t));
		float b = (2 * (1 - t) * t);
		float c = (t * t);

		return p0.clone().multiply(a).add(p1.clone().multiply(b)).add(p2.clone().multiply(c));
	}

	public static @NotNull List<Location> bezierCurve(int segmentCount, Location p0, Location p1, Location p2) {
		List<Location> points = new ArrayList<>();
		for (int i = 1; i < segmentCount; i++) {
			float t = i / (float) segmentCount;
			points.add(bezierPoint(t, p0, p1, p2));
		}
		return points;
	}

	public static void genLineParticle(final Player p, Location Pos1, Location Pos2) {

		new BukkitRunnable() {

			@Override
			public void run() {

				GOHA_Builder goha_builder = GOHA_Builder.getGOHABuilder(p);

				if (goha_builder.getMomentallyParticleStop().equals(false) || goha_builder.getPregen().equals(false)) {
					cancel();
					return;
				}

				double distance = Pos1.distance(Pos2);

				Vector p1 = Pos1.toVector();
				Vector p2 = Pos2.toVector();
				Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
				double length = 0;
				for (; length < distance; p1.add(vector)) {
					p.spawnParticle(Particle.FLAME, p1.getX(), p1.getY(), p1.getZ(), (int) 0.01);
					length += space;
				}
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 0, 10);
	}

	public static void genSphereParticle(Player p, int rayon, @NotNull Location center) {

		new BukkitRunnable() {

			@Override
			public void run() {

				GOHA_Builder goha_builder = GOHA_Builder.getGOHABuilder(p);

				if (goha_builder.getMomentallyParticleStop().equals(false) || goha_builder.getPregen().equals(false)) {
					cancel();
					return;
				}

				Location loc1 = center.clone()
						.add(-rayon, -rayon, -rayon)
						.getBlock().getLocation();
				Location loc2 = center.clone()
						.add(+rayon, +rayon, +rayon)
						.getBlock().getLocation();

				for (double x = loc1.getX(); x <= loc2.getX(); x++) {
					for (double y = loc1.getY(); y <= loc2.getY(); y++) {
						for (double z = loc1.getZ(); z <= loc2.getZ(); z++) {
							Location loc = new Location(loc1.getWorld(), x, y, z);
							if (loc.distance(center) < (rayon + 0.5) && loc.distance(center) > (rayon - 0.5)) {

								p.spawnParticle(Particle.FLAME, loc.getX(), loc.getY(), loc.getZ(), (int) 0.01);
							}
						}
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 0, 10);
	}
}
