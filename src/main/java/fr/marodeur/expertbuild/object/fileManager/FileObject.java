package fr.marodeur.expertbuild.object.fileManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.marodeur.expertbuild.Main;

import java.io.*;
import java.util.logging.Logger;

public class FileObject<T> {

    private final String path;
    private final String name;
    private final Extension extension;

    private final Class<T> tClass;

    private static Logger LOG;

    static {
        LOG = Main.getInstance().getLogger();
    }

    public FileObject(String path, String name, Extension extension, Class<T> tClass) {
        this.path = path;
        this.name = name;
        this.extension = extension;
        this.tClass = tClass;
    }

    public String path() {
        return path;
    }

    public String name() {
        return name;
    }

    public Extension extension() {
        return extension;
    }

    public Class<T> tClass() {
        return tClass;
    }

    //get l'enseble du chemin du nom et de l'extention
    private String getFilePath() {
        return this.path + "/" + this.name + "." + this.extension.name();
    }

    // Methode pour verifier si le fichier exist
    public boolean fileExist() {
        File file = new File(getFilePath());
        return file.exists();
    }

    // Methode crée le fichier
    public boolean createFile() {

        File file = new File(getFilePath());

        if (file.exists()) return false;

        file.getParentFile().mkdirs();

        try {
            return file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Methode de suppression du fichier
    public boolean deleteFile() {
        return new File(getFilePath()).delete();
    }


    // Méthode pour sérialiser en JSON
    public String toJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

    // Méthode pour désérialiser depuis JSON
    public T fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, this.tClass);
    }


    // Méthode pour enregistrer le JSON dans un fichier
    public void saveToFile(Object object) {

        if (this.extension.equals(Extension.JSON)) {

            try (Writer writer = new FileWriter(getFilePath())) {

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(object, writer);

                LOG.info("JSON saved to file: " + getFilePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour lire un objet AreaTimerParameter à partir d'un fichier JSON
    public T loadFromFile() {

        try (Reader reader = new FileReader(getFilePath())) {
            Gson gson = new Gson();

            return gson.fromJson(reader, this.tClass);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   public boolean isEmpty() {

       BufferedReader br = null;
       try {
           br = new BufferedReader(new FileReader(this.getFilePath()));
           if (br.readLine() == null) {
               return true;
           }

       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       return false;
   }
}

