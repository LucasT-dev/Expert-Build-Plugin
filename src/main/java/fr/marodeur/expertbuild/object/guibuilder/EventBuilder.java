package fr.marodeur.expertbuild.object.guibuilder;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class EventBuilder<T> {

    private Class<T> tClass;
    private Consumer<T> consumer;

    public EventBuilder(@NotNull Class<T> tClass, @NotNull Consumer<T> consumer) {
        this.tClass = tClass;
        this.consumer = consumer;
    }

    public void accept(T t) {
        this.consumer.accept(t);
    }

    /**
     * Returns the class of the object.
     *
     * @return The class of the object.
     */
    public Class<T> gettClass() {
        return tClass;
    }
}
