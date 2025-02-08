package fr.marodeur.expertbuild.object;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Flag {

    private final GlueList<IndividualFlag> flags;

    public Flag(String acceptableValue) {
        flags = new GlueList<>();

        for (char c : acceptableValue.toCharArray()) {
            this.add(c, false);
        }
    }

    public <T> void add(char flag, T flagValue) {

        if (getFlagAlreadyRegister(flag)) {
            flags.get(flag).setElement(flagValue);

        } else {
            flags.add(new IndividualFlag(flag, flagValue));
        }
    }

    public void add(char flag) {

        if (getFlagAlreadyRegister(flag)) {

            flags.stream()
                    .filter(individualFlag -> individualFlag.getFlag() == flag)
                    .findFirst()
                    .get().setElement(this.returnDefaultValue(flag));

        } else {

            flags.add(new IndividualFlag(flag, returnDefaultValue(flag)));
        }
    }

    private <T> T returnDefaultValue(char flag) {

        return switch (flag) {

            case 'a', 'b', 'c', 'e', 'n', 'o', 's', 'x' -> (T) Boolean.TRUE;

            default -> (T) Boolean.FALSE;
        };
    }


    public <T> T get(char flag) {

        if (getFlagAlreadyRegister(flag)) {

            return (T) flags.stream().filter(individualFlag -> individualFlag.getFlag() == flag).findFirst().get().getElement();
        }
        else {
            return null;
        }
    }

    private boolean getFlagAlreadyRegister(char flag) {

        if (flags.isEmpty()) {
            return false;
        } else {

            return flags.stream().map(IndividualFlag::getFlag).toList().contains(flag);
        }
    }


    // Method to convert flags to JSON using JsonObject
    private JsonArray flagsToJson() {
        JsonArray jsonArray = new JsonArray();

        for (IndividualFlag<?> flag : flags) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("flag", String.valueOf(flag.getFlag()));

            // Dynamically add the element based on its type
            if (flag.getElement() instanceof String) {
                jsonObject.addProperty("element", (String) flag.getElement());
            } else if (flag.getElement() instanceof Number) {
                jsonObject.addProperty("element", (Number) flag.getElement());
            } else if (flag.getElement() instanceof Boolean) {
                jsonObject.addProperty("element", (Boolean) flag.getElement());
            } else {
                jsonObject.addProperty("element", String.valueOf(flag.getElement()));
            }
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    // Method to save JSON to a file
    public void saveJsonToFile(File filePath) {
        JsonArray jsonArray = flagsToJson();

        try (FileWriter fileWriter = new FileWriter(filePath)) {

            fileWriter.write(jsonArray.toString());
            fileWriter.flush();
        } catch (IOException e) {
            Main.getInstance().getLogger().severe("Failed to save JSON from file:" + filePath.getName() + " : " + e.getMessage());
        }
    }

    // Method to load JSON from a file and convert it to flags
    public static Flag loadJsonFromFile(String filePath) {

        Flag newFlag = new Flag("abcem");

        try (FileReader fileReader = new FileReader(filePath)) {

            JsonArray jsonArray = JsonParser.parseReader(fileReader).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();

                char flag = jsonObject.get("flag").getAsCharacter();
                JsonElement elementValue = jsonObject.get("element");

                if (elementValue.isJsonPrimitive()) {

                    if (elementValue.getAsJsonPrimitive().isNumber()) {
                        newFlag.add(flag);
                    } else if (elementValue.getAsJsonPrimitive().isBoolean()) {
                        newFlag.add(flag);
                    } else {
                        newFlag.add(flag);
                    }
                } else {
                    newFlag.add(flag);
                }
            }

        } catch (IOException e) {
            Main.getInstance().getLogger().severe("Failed to load JSON from file:" + filePath + " : " + e.getMessage());
        }
        return newFlag;
    }



    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        flags.forEach(individualFlag -> {

            sb.append("flag : ")
                    .append(individualFlag.getFlag())
                    .append(" -> :")
                    .append(individualFlag.getElement().toString())
                    .append("\n");

        });

        return "Flag{" + sb + '}';
    }

    private static class IndividualFlag<T> {

        private final char flag;
        private T element;

         public IndividualFlag(char flag, T element) {
             this.flag = flag;
             this.element = element;
         }

         public char getFlag() {
             return flag;
         }

         public T getElement() {
             return element;
         }

         public void setElement(T element) {
             this.element = element;
         }
    }

}
