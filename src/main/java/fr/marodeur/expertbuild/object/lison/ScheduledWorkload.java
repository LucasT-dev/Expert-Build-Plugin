package fr.marodeur.expertbuild.object.lison;

public interface ScheduledWorkload {

    void compute();

    default boolean shouldBeRescheduled() {
        return false;
    }
}
