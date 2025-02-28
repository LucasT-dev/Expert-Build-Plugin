package fr.marodeur.expertbuild.object.builderObjects;


import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.session.ClipboardHolder;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.object.Flag;
import fr.marodeur.expertbuild.object.fileManager.FileManager;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ClipboardParameter extends IDataProfile {

    private static final List<String> clipboardsFolder = new ArrayList<>();


    private final ArrayList<ClipboardHolder> clipboardHolders;
    private final ArrayList<String> clipboardsName;

    private final ArrayList<Flag> flags;
    private boolean isRandomRotation;

    public ClipboardParameter(UUID profileID, ArrayList<ClipboardHolder> clipboardsBlock, ArrayList<String> clipboardsName, ArrayList<Flag> flags, boolean isRandomRotation) {
        super(profileID);

        this.clipboardHolders = clipboardsBlock;
        this.clipboardsName = clipboardsName;
        this.flags = flags;
        this.isRandomRotation = isRandomRotation;
    }

    public ClipboardParameter addClipboards(ClipboardHolder clipboardsBlock, String clipboardsName, Flag flag) {
        this.clipboardHolders.add(clipboardsBlock);
        this.clipboardsName.add(clipboardsName);
        this.flags.add(flag);
        return this;
    }

    public ClipboardParameter removeClipboards(String clipboardsName) {

        int index = this.clipboardsName.indexOf(clipboardsName);
        this.clipboardsName.remove(index);
        this.clipboardHolders.remove(index);
        this.flags.remove(index);

        return this;
    }

    public boolean isRandomRotation() {
        return isRandomRotation;
    }

    public ClipboardParameter setRandomRotation(boolean randomRotation) {
        isRandomRotation = randomRotation;
        return this;
    }

    public ArrayList<ClipboardHolder> getClipboardHolders() {
        return this.clipboardHolders;
    }

    public ClipboardHolder getClipboardHolder(int index) {
        return this.clipboardHolders.get(index);
    }

    public ArrayList<String> getClipboardsName() {
        return this.clipboardsName;
    }

    public String getClipboardName(int index) {
        return this.clipboardsName.get(index);
    }

    public boolean getClipboardsNameExist(String clipboardsName) {
        return this.clipboardsName.contains(clipboardsName);
    }

    public boolean hasClipboardLoad() {
        return !clipboardHolders.isEmpty();
    }

    public ClipboardParameter clearAll() {
        this.clipboardsName.clear();
        this.clipboardHolders.clear();
        this.flags.clear();

        return this;
    }

    public Flag getFlag(int index) {
        return flags.get(index);
    }


    @Override
    public String toString() {

        AtomicReference<String> name = new AtomicReference<>("");

        clipboardsName.forEach(s -> name.set(name + "{" + s + "},"));

        return "ClipboardParameter{" +
                "clipboardsBlock.size=" + clipboardHolders.size() +
                ", clipboardsName=" + name +
                ", isRandomRotation=" + isRandomRotation +
                '}';
    }

    public static List<String> getClipboardsSaveInFile(UUID uuid) {

        if (Main.getConfiguration().getShareClipboardSaved()) {
            return clipboardsFolder;
        } else {

            return clipboardsFolder.parallelStream()
                    .filter(clipboardFolder -> {
                        File ownerFile = new File(FileManager.clipboardFile.path() + "/" + clipboardFolder + "/" + "data.yml");

                        if (!ownerFile.exists()) {
                            createOwnerFile(uuid, ownerFile, true);
                        }

                        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(ownerFile);

                        // Autorisation de partage des clipboard sur le serveur
                        if (Main.getConfiguration().getShareClipboardSaved()) {
                            return (uuid.toString().equals(yaml.getString("owner"))) || (yaml.getBoolean("freeAccess"));
                        } else {
                            return (uuid.toString().equals(yaml.getString("owner")));
                        }
                    })
                    .collect(Collectors.toList());
        }
    }

    public static void addClipboardsFolder(String clipboardFolder) {
        clipboardsFolder.add(clipboardFolder);
    }
    public static void addClipboardsFolder(List<String> clipboardFolder) {
        clipboardsFolder.addAll(clipboardFolder);
    }


    public static boolean folderExist(String folderTest) {
        return clipboardsFolder.contains(folderTest);
    }

    public void saveToFolder(String clipboardFolderName, boolean freeAccess) {

        new File(FileManager.clipboardFile.path() + "/" + clipboardFolderName).mkdirs();

        File ownerFile = new File(FileManager.clipboardFile.path() + "/" + clipboardFolderName + "/" + "data.yml");

        for (int i = 0; i < this.clipboardHolders.size(); i++) {

            File file = new File(FileManager.clipboardFile.path() + "/" + clipboardFolderName + "/" + getClipboardName(i) + ".schem");
            File flagFile = new File(FileManager.clipboardFile.path() + "/" + clipboardFolderName + "/" + getClipboardName(i) + ".json");


            getFlag(i).saveJsonToFile(flagFile);

            new FaweAPI(getPlayer()).saveClipboardToFile(file, getClipboardHolder(i).getClipboard());

            createOwnerFile(getProfileID(), ownerFile, freeAccess);

            addClipboardsFolder(clipboardFolderName);
        }
    }

    public void loadSinceFolder(String clipboardFolderName) {

        if (folderExist(clipboardFolderName)) {

            File directory = new File(FileManager.clipboardFile.path() + "/" + clipboardFolderName);

            // Vérifie si le chemin est valide et s'il s'agit bien d'un répertoire
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".schem")) {

                            Clipboard clipboard = new FaweAPI(getPlayer()).loadClipboardToFile(file);
                            String fileName = file.getName().replace(".schem", "");

                            Flag flag = Flag.loadJsonFromFile(FileManager.clipboardFile.path() + "/" + clipboardFolderName + "/" + fileName + ".json");

                            addClipboards(new ClipboardHolder(clipboard), fileName, flag);

                            // Ajoutez ici l'action à effectuer sur chaque fichier
                        }
                    }
                }
            }
        }
    }

    public static void deleteFolder(String folderName) {

        if (folderExist(folderName)) {

            File directory = new File(FileManager.clipboardFile.path() + "/" + folderName);

            deleteDirectory(directory);
            clipboardsFolder.remove(folderName);

        }
    }
    private static void deleteDirectory(File directory) {

        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    deleteDirectory(file); // Appel récursif pour les sous-dossiers
                } else {
                    file.delete(); // Supprime le fichier
                }
            }
            directory.delete(); // Supprime le dossier lui-même
        }
    }

    private static void createOwnerFile(UUID uuid, File ownerFile, boolean freeAccess) {

        try {
            ownerFile.createNewFile();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(ownerFile);

            yaml.set("owner", uuid.toString());
            yaml.set("freeAccess", freeAccess);
            yaml.set("creation", dateFormat.format(date));

            yaml.save(ownerFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
