package fr.marodeur.expertbuild.object.lison;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;

import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.BrushBuilder;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AdvancedParticleOperation implements ScheduledWorkload {

    private final Player p;
    private double x, y, z;
    private Particle particle;


    private UUID Id;

    private RescheduledParticle[] rescheduledParticleTable;

    public AdvancedParticleOperation(Player p) {
        this.p = p;
    }

    private AdvancedParticleOperation(Player p, double x, double y, double z, Particle particle, RescheduledParticle[] rescheduledParticleTable) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.z = z;
        this.particle = particle;
        this.rescheduledParticleTable = rescheduledParticleTable;

        Arrays.stream(rescheduledParticleTable).forEach(rescheduledParticle -> {

            BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

            if (rescheduledParticle.particleClearUpdateId) {
                this.Id = brushBuilder.getParticleID();
            }

            if (rescheduledParticle.particleClearOrga) {
                this.Id = brushBuilder.getGohaParameter().getParticleID();
            }
        });
    }

    public AdvancedParticleOperation particle(double x, double y, double z, Particle particle, RescheduledParticle[] rescheduledParticleTable) {
        return new AdvancedParticleOperation(this.p, x, y, z, particle, rescheduledParticleTable);
    }

    public void lineParticle(BlockVectorTool bv1, BlockVectorTool bv2, Particle particle, int spacingParticles, RescheduledParticle[] rescheduledParticleTable) {

        Location l1 = new Location(p.getWorld(), bv1.getX(), bv1.getY(), bv1.getZ());
        Location l2 = new Location(p.getWorld(), bv2.getX(), bv2.getY(), bv2.getZ());

        double distance = l1.distance(l2);
        Vector p1 = l1.toVector();
        Vector p2 = l2.toVector();
        Vector vector = p2.clone().subtract(p1).normalize().multiply(spacingParticles);
        double length = 0;

        for (; length < distance; p1.add(vector)) {

            new LightweightInteractiveSystemforOptimizedParticleNavigation()
                    .addScheduledWorkloadRunnable(
                            new AdvancedParticleOperation(p)
                                    .particle(p1.getX(), p1.getY(), p1.getZ(), particle, rescheduledParticleTable));

            length += spacingParticles;
        }
    }

    public void sphereParticle(Location center, int rayon, Particle particle, RescheduledParticle[] rescheduledParticleTable) {

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

                        new LightweightInteractiveSystemforOptimizedParticleNavigation()
                                .addScheduledWorkloadRunnable(new AdvancedParticleOperation(p)
                                        .particle(loc.getX(), loc.getY(), loc.getZ(), particle, rescheduledParticleTable));
                    }
                }
            }
        }
    }

    public void sphereParticle(BlockVectorTool center, int rayon, Particle particle, RescheduledParticle[] rescheduledParticleTable) {

        BlockVectorTool loc1 = center.clone()
                .add(-rayon, -rayon, -rayon);

        BlockVectorTool loc2 = center.clone()
                .add(+rayon, +rayon, +rayon);

        for (double x = loc1.getX(); x <= loc2.getX(); x++) {
            for (double y = loc1.getY(); y <= loc2.getY(); y++) {
                for (double z = loc1.getZ(); z <= loc2.getZ(); z++) {

                    BlockVectorTool loc = new BlockVectorTool(x, y, z);
                    if (loc.distance(center) < (rayon + 0.5) && loc.distance(center) > (rayon - 0.5)) {

                        new LightweightInteractiveSystemforOptimizedParticleNavigation()
                                .addScheduledWorkloadRunnable(new AdvancedParticleOperation(p)
                                        .particle(loc.getX(), loc.getY(), loc.getZ(), particle, rescheduledParticleTable));
                    }
                }
            }
        }
    }

    public void bezierLineParticle(List<BlockVector3> point, Particle particle, int configCoef, RescheduledParticle[] rescheduledParticleTable) {

        double distance = 0;

        for (int i = 1; i < point.size(); i++) {

            distance += point.get(i-1).distance(point.get(i));
        }
        drawBezierCurve(p, point, (int) (distance / configCoef), particle, rescheduledParticleTable);
    }

    @Override
    public void compute() {

        if (!particle.getDataType().equals(Void.class)) {
            return;
        }
        p.spawnParticle(this.particle, x, y, z, 0);
    }

    @Override
    public boolean shouldBeRescheduled() {

        return Arrays.stream(this.rescheduledParticleTable).allMatch(element -> element.analyse(p, Id));

    }

    private void drawBezierCurve(Player p, List<BlockVector3> controlPoints, int numSamples, Particle particle, RescheduledParticle[] rescheduledParticleTable) {

        for (int i = 0; i < numSamples; i++) {
            double t = i / (double) (numSamples - 1);

            double[] point = evaluateBezierCurve(controlPoints, t);

            new LightweightInteractiveSystemforOptimizedParticleNavigation()
                    .addScheduledWorkloadRunnable(new AdvancedParticleOperation(p)
                            .particle(point[0], point[1], point[2], particle, rescheduledParticleTable));

        }
    }

    private double[] evaluateBezierCurve(List<BlockVector3> controlPoints, double t) {

        int n = controlPoints.size() - 1;
        double[] bernsteinPolynomials = new double[n + 1];
        double[] result = new double[] {0, 0, 0};

        for (int i = 0; i <= n; i++) {

            bernsteinPolynomials[i] = binomialCoefficient(n, i) * Math.pow(1 - t, n - i) * Math.pow(t, i);

            double dernsteinCoef = bernsteinPolynomials[i];
            double[] cloneControlePoint = new double[] {
                    controlPoints.get(i).x() * dernsteinCoef,
                    controlPoints.get(i).y() * dernsteinCoef,
                    controlPoints.get(i).z() * dernsteinCoef };

            result[0] += cloneControlePoint[0];
            result[1] += cloneControlePoint[1];
            result[2] += cloneControlePoint[2];

        }
        return result;
    }

    private int binomialCoefficient(int n, int k) {
        int result = 1;
        for (int i = 1; i <= k; i++) {
            result *= (n - i + 1);
            result /= i;
        }
        return result;
    }

    public static class RescheduledParticle {

        private boolean particleClearRegion;
        private boolean particleClearOrga;
        private boolean particleClearUpdateId;

        public RescheduledParticle() {
        }

        public RescheduledParticle setParticleClearOrga(boolean particleClearOrga) {
            this.particleClearOrga = particleClearOrga;
            return this;
        }

        public RescheduledParticle setParticleClearRegion(boolean particleClearRegion) {
            this.particleClearRegion = particleClearRegion;
            return this;
        }

        public RescheduledParticle setParticleClearUpdateId(boolean particleClearUpdateId) {
            this.particleClearUpdateId = particleClearUpdateId;
            return this;
        }


        private boolean analyseParticleClearRegion(Player p) {

            try {
                BukkitAdapter.adapt(p).getSession().getSelection();
            } catch (IncompleteRegionException regionException) {
                return false;
            }
            return true;
        }

        private boolean analyseParticleClearOrga(Player p, UUID Id) {

            BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

            return brushBuilder.getGohaParameter().getParticleID().equals(Id);
        }

        private boolean analyseParticleClearUpdateId(Player p, UUID Id) {

            BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

            return brushBuilder.getParticleID().equals(Id);

        }

        public boolean analyse(Player p, UUID Id) {

            if (this.particleClearOrga) {
                return analyseParticleClearOrga(p, Id);
            }

            if (this.particleClearRegion) {
                return analyseParticleClearRegion(p);
            }

            if (this.particleClearUpdateId) {
                return analyseParticleClearUpdateId(p, Id);
            }

            return false;
        }
    }
}


