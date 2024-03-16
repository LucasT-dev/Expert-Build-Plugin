package fr.marodeur.expertbuild.object.LISON;

public interface ScheduledWorkload {

    void compute();

    default boolean shouldBeRescheduled() {
        return false;
    }
}
