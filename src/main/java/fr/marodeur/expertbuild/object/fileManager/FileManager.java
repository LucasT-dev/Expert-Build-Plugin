package fr.marodeur.expertbuild.object.fileManager;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.builderObjects.AreaTimerParameter;
import fr.marodeur.expertbuild.object.builderObjects.ClipboardParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileManager {

    public static final MultiFileObject areaTimerFile;
    public static final MultiFileObject clipboardFile;
    //private static final FileObject data;

    private static Logger LOG;

    static {

        LOG = Main.getInstance().getLogger();

        areaTimerFile = new MultiFileObject<>("plugins/ExpertBuild/Data/AreaTimer", Extension.JSON);
        clipboardFile = new MultiFileObject("plugins/ExpertBuild/Data/Clipboard", Extension.SCHEM);
        // Example
        //data = new FileObject<>("plugins/ExpertBuild/Data/PluginVersion", "PluginVersion", Extension.JSON, String.class );

    }

    public FileManager() {

        createFileOnEnable();

        loadFileOnEnable();

    }


    private void createFileOnEnable() {

        if (areaTimerFile.createPathFiles()) LOG.info("FilePath 'AreaTimer' created with success");

        if (clipboardFile.createPathFiles()) LOG.info("FilePath 'Clipboard' created with success");

        //if (data.createFile()) LOG.info("File 'data' created with success");

    }

    private void loadFileOnEnable() {

        /*if (!data.isEmpty()) {
            LOG.info("data.loadFromFile() = " + data.loadFromFile());
        }*/

        AreaTimerParameter[] areaTimerParameter = (AreaTimerParameter[]) areaTimerFile.loadFromFile(AreaTimerParameter.class);
        Main.getDataProfile().getAreaTimerParameterList().addAll(Arrays.stream(areaTimerParameter).collect(Collectors.toCollection(ArrayList::new)));

        ClipboardParameter.addClipboardsFolder(clipboardFile.getSubdirectories());




    }

    public void saveFileOnDisable() {

        // Exemple
        //data.saveToFile(Main.getVersion());

        areaTimerFile.saveToFile(Main.getDataProfile().getAreaTimerParameterList());
    }
}
