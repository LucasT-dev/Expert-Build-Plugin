package fr.marodeur.expertbuild.object.fileManager;

import com.google.gson.Gson;
import fr.marodeur.expertbuild.Main;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MultiFileObject<T> {

    private final String path;
    private final Extension extension;

    private static Logger LOG;

    static {
        LOG = Main.getInstance().getLogger();
    }

    public MultiFileObject(String path, Extension extension) {

        this.path = path;
        this.extension  = extension;
    }

    public String path() {
        return path;
    }

    public Extension extension() {
        return extension;
    }

    // Methode crée le fichier
    public boolean createPathFiles() {

        File file = new File(this.path);

        if (file.exists()) return false;

        return file.mkdirs();
    }


    // Méthode pour enregistrer le JSON dans un fichier
    public void saveToFile(List<Object> objects) {

        if (this.extension.equals(Extension.JSON)) {

            for (Object object : objects) {

                String fileName = "null";

                try {
                    fileName = (String) object.getClass().getMethod("getName").invoke(object);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }

                new FileObject<>(this.path(), fileName, extension, object.getClass()).saveToFile(object);

            }
        }
    }

    // Méthode pour lire un objet AreaTimerParameter à partir d'un fichier JSON
    public T[] loadFromFile(Class<T> tClass) {

        List<T> result = new ArrayList<>();

        File[] files = new File(this.path()).listFiles();

        for (File file : files) {

            if (!isEmpty(file)) {

                try (Reader reader = new FileReader(file)) {
                    Gson gson = new Gson();

                    T t = gson.fromJson(reader, tClass);

                    result.add(t);

                } catch (IOException e) {
                    LOG.warning("Error : invalid Gson" + tClass.toString() + " to " + tClass.getName());
                }
            }
        }

        // Convertir la liste en tableau

        T[] resultArray = (T[]) Array.newInstance(tClass, result.size());
        resultArray = result.toArray(resultArray);

        return resultArray;
    }

    public boolean isEmpty(File file) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            if (br.readLine() == null) {
                return true;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

