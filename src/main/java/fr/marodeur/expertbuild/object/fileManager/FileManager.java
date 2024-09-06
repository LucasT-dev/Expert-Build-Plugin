package fr.marodeur.expertbuild.object.fileManager;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.builderObjects.AreaTimerParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileManager {

    private static final MultiFileObject areaTimerFile;
    //private static final FileObject data;

    private static Logger LOG;

    static {

        LOG = Main.getInstance().getLogger();

        areaTimerFile = new MultiFileObject<>("plugins/ExpertBuild/Data/AreaTimer", Extension.JSON);
        // Example
        //data = new FileObject<>("plugins/ExpertBuild/Data/PluginVersion", "PluginVersion", Extension.JSON, String.class );

    }

    public FileManager() {

        createFileOnEnable();

        loadFileOnEnable();

    }


    private void createFileOnEnable() {

        if (areaTimerFile.createPathFiles()) LOG.info("FilePath 'data' created with success");

        //if (data.createFile()) LOG.info("File 'data' created with success");

    }

    private void loadFileOnEnable() {

        /*if (!data.isEmpty()) {
            LOG.info("data.loadFromFile() = " + data.loadFromFile());
        }*/

        AreaTimerParameter[] areaTimerParameter = (AreaTimerParameter[]) areaTimerFile.loadFromFile(AreaTimerParameter.class);
        Main.AREA_TIMER_PARAMETERS = Arrays.stream(areaTimerParameter).collect(Collectors.toCollection(ArrayList::new));


    }

    public void saveFileOnDisable() {

        // Exemple
        //data.saveToFile(Main.getVersion());

        areaTimerFile.saveToFile(Main.AREA_TIMER_PARAMETERS);
    }
}
