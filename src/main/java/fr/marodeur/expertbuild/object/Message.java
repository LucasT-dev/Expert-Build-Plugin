package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.Main;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

public class Message {

    public FileConfiguration yml;
    private static final Logger log = Logger.getLogger("Expert-Build");

    public Message() {}

    public Message loadFileConfig() {

        try {
            this.yml = createLanguageFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    private YamlConfiguration createLanguageFile() throws URISyntaxException {

        Main main = Main.getInstance();

        try(JarFile jarFile = new JarFile(Paths.get(main.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).toString())) {
            Enumeration<JarEntry> jarFiles = jarFile.entries();

            while (jarFiles.hasMoreElements()) {

                JarEntry jarEntry = jarFiles.nextElement();

                if (jarEntry.getRealName().equalsIgnoreCase("fr/marodeur/expertbuild/api/lang/" + Main.configuration().getLang() + ".yml")) {

                    File langFile = new File(main.getDataFolder() ,jarEntry.getName());

                    if (langFile.exists()) {
                        cleanDirectory();
                    }

                    main.saveResource( jarEntry.getName(), false);

                    return YamlConfiguration.loadConfiguration(langFile);

                }
            }

            log.warning("Unable to find the language file, loading the default file : English");

            File langFile = new File(main.getDataFolder(), "fr/marodeur/expertbuild/api/lang/en.yml");


            if(langFile.exists()) {
                main.saveResource("fr/marodeur/expertbuild/api/lang/en.yml", false);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void cleanDirectory() throws IOException {

        File[] files = new File(Main.getInstance().getDataFolder() + "/fr/marodeur/expertbuild/api/lang").listFiles();

        for (File file : files) {
            file.delete();
        }

        //FileUtils.cleanDirectory(new File(Main.getInstance().getDataFolder() + "/fr/marodeur/expertbuild/api/lang"));
    }

    public static class MessageSender {

        private String message;

        public MessageSender(String path, boolean prefix, String[]... var) {

            String brutMessage = new MessageReader(path).message();

            if (var.length > 0) {

                int i = StringUtils.countMatches(brutMessage, "'");

                for (int i1 = 0; i1 < i/2; i1++) {

                    int first = brutMessage.indexOf("'");
                    int second = brutMessage.indexOf("'", brutMessage.indexOf("'") + 1)+1;

                    String brutVariable = brutMessage.substring(first, second);

                    brutMessage = brutMessage.replace(brutVariable, var[0][i1]);

                }
            }

            this.message = brutMessage;

            if (prefix) addPrefix();
        }

        private void addPrefix() {

            StringBuilder sb = new StringBuilder(this.message);

            sb.insert(0, Main.prefix);

            this.message = sb.toString();

        }

        public String getMessage() {
            return this.message;
        }

        public <T> boolean send(T receiver) {

            if (receiver instanceof Player p) {
                p.sendMessage(this.message);
                return true;
            }

            if (receiver instanceof ConsoleCommandSender consoleCommandSender) {
                consoleCommandSender.sendMessage(this.message);
                return true;
            }

            return false;
        }
    }
    private static class MessageReader {

        private String brutMessage;
        private final String path;

        public MessageReader(String path) {
            this.path = path;
        }

        public String message() {

            brutMessage = Main.getFileMessageManager().yml.getString(this.path);


            if (analyseMessage()) {
                brutMessage = "default message";
                log.severe("Error reading path :" + this.path + " ; return empty !");
            }
            return brutMessage;
        }

        private boolean analyseMessage() {

            return brutMessage.isEmpty();
        }
    }
}
