package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.api.GlueList;

public class Flag {

    private GlueList<IndividualFlag> flags;

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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        flags.forEach(individualFlag -> {

            sb.append("flag : " + individualFlag.getFlag() + " -> :" + individualFlag.getElement().toString() + "\n");

        });

        return "Flag{" + sb + '}';
    }

    private static class IndividualFlag<T> {

        private char flag;
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
