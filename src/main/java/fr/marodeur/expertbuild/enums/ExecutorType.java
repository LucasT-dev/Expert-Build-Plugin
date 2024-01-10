package fr.marodeur.expertbuild.enums;

public enum ExecutorType {

    PLAYER(),
    CONSOLE(),
    COMMAND_BLOCK(),
    NONE();

    @Override
    public String toString() {
        return "ExecutorType{}";
    }
}
