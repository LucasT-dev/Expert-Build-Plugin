package fr.marodeur.expertbuild.object.LISON;

import fr.marodeur.expertbuild.Main;

import fr.marodeur.expertbuild.object.Configuration;
import org.bukkit.Bukkit;

public class LightweightInteractiveSystemforOptimizedParticleNavigation {

    public LightweightInteractiveSystemforOptimizedParticleNavigation() {

    }

    public void loadSchedule() {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), Main.scheduledWorkloadRunnable, 1, Main.configuration().getPeriod_particle());
    }

    public void addScheduledWorkloadRunnable(ScheduledWorkload scheduledWorkload) {
        Main.addScheduledWorkloadRunnable(scheduledWorkload);
    }
}
