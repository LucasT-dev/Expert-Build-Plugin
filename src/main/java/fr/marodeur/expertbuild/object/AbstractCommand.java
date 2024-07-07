package fr.marodeur.expertbuild.object;

import com.fastasyncworldedit.core.command.SuggestInputParseException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.command.util.SuggestionHelper;
import com.sk89q.worldedit.extension.factory.PatternFactory;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.biome.BiomeType;
import com.sk89q.worldedit.world.block.BlockType;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.enums.BlocksDataColor;
import fr.marodeur.expertbuild.enums.ExecutorType;

import org.bukkit.Bukkit;
import org.bukkit.block.Biome;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

public abstract class AbstractCommand implements TabCompleter, CommandExecutor {

    public abstract String getCommand();

    public abstract String getSyntax();

    public abstract Integer getMinimumArgsLength();

    // none = no permission
    public abstract String getPermission();

    public BrushBuilder getBrushBuilder(Player p) {
        return BrushBuilder.getBrushBuilderPlayer(p, false);
    }

    public abstract List<ExecutorType> getExecutorType();

    public boolean hasPermission(@NotNull Player p) {
        return p.hasPermission(this.getPermission());
    }

    /**
     * Command execution writing method
     *
     * @param executor CommandSender
     * @param command Command
     * @param label String
     * @param args String[]
     *
     */
    public abstract void execute(CommandSender executor, Command command, @NotNull String label, @NotNull String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        // Check all conditions before executing the command (permission, player...)
        if (!validInitialCondition(this.getExecutorType(), sender, command, label, args)) {
            return false;
        }

        // Check Optional condition (Selection, BrushBuilder ...)
        if (!this.optionalConditionExecution(sender).verifyOptionalCondition()) {
            return false;
        }

        // Check length args is correct
        if (!compareArgumentLengthWithArgsLength(sender, args)) {
            return false;
        }

        // Execute command
        execute(sender, command, label, args);

        return true;
    }

    protected abstract OptionalConditionExecution optionalConditionExecution(CommandSender sender);

    protected abstract ArgumentLengthList getArgumentLengthList();

    protected abstract SubCommandSender getSubCommand(CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        // player has perm but not brushbuilder create
        if (this.optionalConditionExecution(sender).haveBrushBuilderProfile) {
            if (!Main.containsBrushBuilder((Player) sender)) {
                BrushBuilder.registerPlayer((Player) sender, false);
            }
        }

        if (args.length <= 1) {
            List<String> l = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], this.getListFirstArgs(this.getSubCommandAtPosition(0, sender, command, label, args), sender), l);
            return l;
        }

        List<String> l2 = new ArrayList<>();
        StringUtil.copyPartialMatches(args[args.length-1], this.getListArgs(this.getSubCommandAtPosition(args.length-1, sender, command, label, args), sender, args), l2);
        return l2;

    }


    /**
     * Checks if the sender command is authorized to execute the command
     *
     * @param executorTypeArrayList List<ExecutorType>
     * @param sender CommandSender
     * @return boolean
     *
     */
    public boolean executorCanExecuteCommand(List<ExecutorType> executorTypeArrayList, CommandSender sender) {

        if (sender instanceof Player) {
            if (!executorTypeArrayList.contains(ExecutorType.PLAYER)) {
                return false;
            }
        }

        if (sender instanceof CommandBlock) {
            if (!executorTypeArrayList.contains(ExecutorType.COMMAND_BLOCK)) {
                return false;
            }
        }

        if (sender instanceof ConsoleCommandSender) {
            if (!executorTypeArrayList.contains(ExecutorType.CONSOLE)) {
                return false;
            }
        }
        return true;
    }


    public boolean getSubCommandPermission(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player p) {


            for (int i = 0; i < args.length; i++) {

                if (getSubCommandAtPosition(i, sender, command, label, args).stream()
                        .map(SubCommand::getArgs)
                        .map(String::toLowerCase)
                        .distinct()
                        .toList().contains(args[i].toLowerCase())) {

                    int finalI = i;
                    SubCommand subCommand = getSubCommandAtPosition(i, sender, command, label, args).stream()
                            .filter(Sb -> Sb.getArgs().equalsIgnoreCase(args[finalI]))
                            .max(Comparator.comparing(SubCommand::getPosition))
                            .get();

                    if (!p.hasPermission(subCommand.getPermission()) && !subCommand.getPermission().equalsIgnoreCase("none")) {

                        new Message.MessageSender("expbuild.message.permission.no_permission_node", true, new String[]{subCommand.getPermission()}).send(sender);

                        return false;
                    }
                }
            }
        }
        return true;
    }

    boolean validInitialCondition(List<ExecutorType> executorTypeArrayList, CommandSender sender, Command command, String label, String[] args) {

        if (!this.executorCanExecuteCommand(executorTypeArrayList, sender)) {
            sender.sendMessage(new Message.MessageSender("expbuild.message.error.invalid_instance", true, new String[]{String.valueOf(sender)}).getMessage());
            return false;
        }

        if (sender instanceof Player p) {

            if (!this.hasPermission(p)) {
                new Message.MessageSender("expbuild.message.permission.no_permission_node", true, new String[]{this.getPermission()}).send(sender);
                return false;
            }
        }

        if (args.length < this.getMinimumArgsLength()) {
            sender.sendMessage(new Message.MessageSender("expbuild.message.commands.use", true, new String[]{getSyntax()}).getMessage());
            return false;
        }

        if (!this.getSubCommandPermission(sender, command, label, args)) return false;

        return true;
    }

    boolean compareArgumentLengthWithArgsLength(CommandSender sender, String[] args) {

        if (this.getArgumentLengthList().getNoArgumentLengthList()) {
            return true;
        }

         boolean isArgumentLength = this.getArgumentLengthList().argumentLengthList.stream()
                 .filter(argumentLength -> args.length > argumentLength.argConditionPosition)
                 .noneMatch(argumentLength -> argumentLength.getArgCondition().equals(args[argumentLength.argConditionPosition]));

        if (isArgumentLength) { return true; } else {

            boolean argsIsSup = this.getArgumentLengthList().argumentLengthList.stream()
                    .filter(argumentLength -> args.length > argumentLength.argConditionPosition)
                    .filter(argumentLength -> argumentLength.getArgCondition().equalsIgnoreCase(args[argumentLength.argConditionPosition]))

                    // Get the last ArgumentCondition
                    .max(Comparator.comparing(ArgumentLength::getArgConditionPosition))
                    .stream()
                    // Return true if condition is true (shortcut)
                    .anyMatch(argumentLength -> args.length >= argumentLength.getArgsLength());

            if (argsIsSup) return true;

             else {

                 ArgumentLength argumentLength = this.getArgumentLengthList().argumentLengthList.stream()
                         .filter(aL -> args.length-1 >= aL.argConditionPosition)

                         .filter(aL -> aL.getArgCondition().equals(args[aL.argConditionPosition]))
                         .filter(aL -> args.length <= aL.getArgsLength())
                         .sorted(Comparator.comparing(ArgumentLength::getPriority))
                         .toList().get(0);

                new Message.MessageSender("expbuild.message.commands.use", true, new String[]{argumentLength.getSyntaxCommand()}).send(sender);

                 return false;
             }
         }
    }

    public ValidArgument getValidArgument() {
        return new ValidArgument();
    }

    List<SubCommand> getSubCommandAtPosition(Integer position, CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return this.getSubCommand(sender, command, label, args).getSubCommandList().stream().filter(subCommand -> subCommand.position.equals(position)).toList();
    }

    /**
     * Get the list of all possible arguments at position 0
     *
     * @param subCommandsList List<SubCommand>
     * @param sender CommandSender
     * @return List<String>
     */
    List<String> getListFirstArgs(List<SubCommand> subCommandsList, CommandSender sender) {

        if (sender instanceof Player p) {

            return subCommandsList.stream()
                    .filter(subCommand -> p.hasPermission(subCommand.getPermission()) || subCommand.getPermission().equalsIgnoreCase("none"))
                    .map(SubCommand::getArgs)
                    .toList();
        } else {
            return subCommandsList.stream()
                    .map(SubCommand::getArgs)
                    .toList();
        }
    }

    /**
     * Get the list of all possible arguments at the defined position
     *
     * @param subCommandsList List<SubCommand>
     * @param sender CommandSender
     * @param args String[]
     * @return List<String>
     */
    List<String> getListArgs(List<SubCommand> subCommandsList, CommandSender sender, String[] args) {

        if (sender instanceof Player p) {
            return subCommandsList.stream()
                    .filter(subCommand -> p.hasPermission(subCommand.getPermission()) || subCommand.getPermission().equalsIgnoreCase("none"))
                    .filter(subCommand -> subCommand.getConditionArgumentsBefore().getArgCondition().equalsIgnoreCase(String.valueOf(args[subCommand.conditionArgumentsBefore.getArgPosition()])) ||
                            subCommand.getConditionArgument().getArgCondition())
                     .map(SubCommand::getArgs)
                    .toList();
        } else {
            return subCommandsList.stream()
                    .filter(subCommand -> subCommand.conditionArgumentsBefore.getArgCondition().equalsIgnoreCase(String.valueOf(args[subCommand.conditionArgumentsBefore.getArgPosition()])) ||
                            subCommand.getConditionArgument().getArgCondition())
                    .map(SubCommand::getArgs)
                    .toList();
        }
    }

    int getMaxArgs(CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        return getSubCommand(sender, command, label, args).getSubCommandList().stream()
                .map(SubCommand::getPosition)
                .toList()
                .stream()
                .mapToInt(value -> value)
                .max().getAsInt();
    }

    public static class SubCommandSender {

        private final List<SubCommand> subCommandList = new ArrayList<>();

        public SubCommandSender(List<SubCommand> subCommandList) {
            this.subCommandList.addAll(subCommandList);
        }

        public SubCommandSender() {
        }

        public SubCommandSender addSubCommand(SubCommand subCommand) {
            this.subCommandList.add(subCommand);
            return this;
        }

        public SubCommandSender addSubCommand(List<SubCommand> subCommand) {
            this.subCommandList.addAll(subCommand);
            return this;
        }

        public List<SubCommand> getSubCommandList() {
            return subCommandList;
        }
    }

    static class SubCommand {

        private final String args;
        private final String permission;
        private final Integer position;

        private final ConditionArgumentBefore conditionArgumentsBefore;
        private final ConditionArgument conditionArgument;

        // None = no permission
        public SubCommand(String args, String permission, Integer position, ConditionArgumentBefore conditionArgumentsBefore, ConditionArgument conditionArgument) {

            this.args = args;
            this.permission = permission;
            this.position = position;

            this.conditionArgumentsBefore = conditionArgumentsBefore;
            this.conditionArgument = conditionArgument;
        }

        public SubCommand(String args, String permission, Integer position, ConditionArgumentBefore conditionArgumentsBefore) {
            this(args, permission, position, new ConditionArgumentBefore(conditionArgumentsBefore), new ConditionArgument(false));
        }

        public SubCommand(String args, String permission, Integer position, ConditionArgument conditionArgument) {
            this(args, permission, position, new ConditionArgumentBefore("null", 0), new ConditionArgument(conditionArgument));
        }

        public SubCommand(String args, String permission, Integer position) {
            this(args, permission, position, new ConditionArgumentBefore("null", 0), new ConditionArgument(true));
        }

        public String getArgs() {
            return args;
        }

        public String getPermission() {
            return permission;
        }

        public Integer getPosition() {
            return position;
        }

        public ConditionArgumentBefore getConditionArgumentsBefore() {
            return conditionArgumentsBefore;
        }

        public ConditionArgument getConditionArgument() {
            return conditionArgument;
        }

        @Override
        public String toString() {
            return "SubCommand{" +
                    "args='" + args + '\'' +
                    ", permission='" + permission + '\'' +
                    ", position=" + position +
                    ", conditionArgumentsBefore=" + conditionArgumentsBefore +
                    ", conditionArgument=" + conditionArgument +
                    '}';
        }
    }

    public static class ConditionArgumentBefore {

        private final String argCondition;
        private final Integer argPosition;

        public ConditionArgumentBefore(String argCondition, Integer argPosition) {
            this.argCondition = argCondition;
            this.argPosition = argPosition;
        }

        public ConditionArgumentBefore(@NotNull ConditionArgumentBefore conditionArgumentBefore) {
            this(conditionArgumentBefore.argCondition, conditionArgumentBefore.argPosition);
        }

        public String getArgCondition() {
            return this.argCondition;
        }

        public Integer getArgPosition() {
            return argPosition;
        }
    }

    public static class ConditionArgument {

        private final Boolean argCondition;

        public ConditionArgument(Boolean argCondition) {
            this.argCondition = argCondition;
        }

        public ConditionArgument(@NotNull ConditionArgument conditionArgument) {
            this(conditionArgument.argCondition);
        }

        public Boolean getArgCondition() {
            return argCondition;
        }
    }

    public static class SubCommandSelector {

        private List<String> subCommand;
        private int argsIndex;

        public SubCommandSelector() {
            this.subCommand = new ArrayList<>();
        }

        public SubCommandSelector(List<String> subCommand) {
            this.subCommand = subCommand;
        }

        public List<String> getSubCommand() {
            return subCommand;
        }

        public int getArgsIndex() {
            return argsIndex;
        }


        public SubCommandSelector getList(int argsIndex, List<String> stringList) {
            this.argsIndex = argsIndex;
            this.subCommand = stringList;
            return this;
        }

        public SubCommandSelector getArgs(int argsIndex, String stringArgs) {
            this.argsIndex = argsIndex;
            this.subCommand = Collections.singletonList(stringArgs);
            return this;
        }

        /**
         * Get biome list of FAWE
         *
         * @param args String[]
         * @param argsIndex int
         * @return SubCommandSelector
         */
        public SubCommandSelector getBiomeList(@NotNull String @NotNull [] args, int argsIndex) {
            this.argsIndex = argsIndex;

            if (args.length == argsIndex + 1 ) {
                this.subCommand.addAll(SuggestionHelper.getNamespacedRegistrySuggestions(BiomeType.REGISTRY, args[argsIndex])
                        .map(value -> value.toLowerCase(Locale.ENGLISH).replace("minecraft:", ""))
                        .filter(value -> value.startsWith(args[argsIndex].toLowerCase(Locale.ENGLISH)))
                        .toList());
            } else {
                this.subCommand = List.of("");
            }
            return this;
        }

        /**
         * Get Material list of FAWE
         *
         * @param args String[]
         * @param argsIndex int
         * @return SubCommandSelector
         */
        public SubCommandSelector getMaterialList(@NotNull String @NotNull [] args, int argsIndex) {
            this.argsIndex = argsIndex;

            if (args.length == argsIndex  + 1 ) {
                this.subCommand.addAll(SuggestionHelper.getNamespacedRegistrySuggestions(BlockType.REGISTRY, args[argsIndex])
                        .map(value -> value.toLowerCase(Locale.ENGLISH).replace("minecraft:", ""))
                        //.filter(value -> value.startsWith(args[indexArg].toLowerCase(Locale.ENGLISH)))
                        .toList());
            } else {
                this.subCommand = List.of("");
            }
            return this;
        }

        /**
         * Get Pattern list of FAWE
         *
         * @param args String[]
         * @param argsIndex int
         * @return SubCommandSelector
         */
        public SubCommandSelector getPatternFactoryList(@NotNull String[] args, int argsIndex) {
            this.argsIndex = argsIndex;

            if (args.length == argsIndex + 1 ) {
                this.subCommand = new PatternFactory(WorldEdit.getInstance()).getSuggestions(args[argsIndex]);
            } else {
                this.subCommand = List.of("");
            }
            return this;
        }

        /**
         * Get positive integer list of FAWE
         *
         * @param args String[]
         * @param argsIndex int
         * @return SubCommandSelector
         */
        public SubCommandSelector getPositiveIntegerList(@NotNull String[] args, int argsIndex) {
            this.argsIndex = argsIndex;

            if (args.length == argsIndex  + 1 ) {
                this.subCommand.addAll(SuggestionHelper.suggestPositiveIntegers(args[argsIndex])
                        .toList());
            } else {
                this.subCommand = List.of("");
            }
            return this;
        }

        /**
         * Get positive double list of FAWE
         *
         * @param args String[]
         * @param argsIndex int
         * @return SubCommandSelector
         */
        public SubCommandSelector getPositiveDoubleList(@NotNull String @NotNull [] args, int argsIndex) {
            this.argsIndex = argsIndex;

            if (args.length == argsIndex  + 1 ) {
                this.subCommand.addAll(SuggestionHelper.suggestPositiveDoubles(args[argsIndex])
                        .toList());
            } else {
                this.subCommand = List.of("");
            }
            return this;
        }

        /**
         * Get boolean list of FAWE
         *
         * @param args String[]
         * @param argsIndex int
         * @return SubCommandSelector
         */
        public SubCommandSelector getBooleanList(@NotNull String @NotNull [] args, int argsIndex) {
            this.argsIndex = argsIndex;

            if (args.length == argsIndex  + 1 ) {
                this.subCommand.addAll(SuggestionHelper.suggestBoolean(args[argsIndex])
                        .toList());
            } else {
                this.subCommand = List.of("");
            }
            return this;
        }

        /**
         * Get player list on server
         *
         * @param args String[]
         * @param argsIndex int
         * @return SubCommandSelector
         */
        public SubCommandSelector getPlayerList(@NotNull String @NotNull [] args, int argsIndex) {
            this.argsIndex = argsIndex;

            if (args.length == argsIndex  + 1 ) {
                this.subCommand.addAll(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).toList());
            } else {
                this.subCommand = List.of("");
            }
            return this;
        }

        /**
         * Get player position x/y/z
         *
         * @param args String[]
         * @param argsIndex int
         * @param p Player
         * @param coordinate char
         *
         * @return SubCommandSelector
         */
        public SubCommandSelector getPlayerPosition(@NotNull String @NotNull [] args, int argsIndex, Player p, char coordinate) {
            this.argsIndex = argsIndex;

            if (args.length == argsIndex  + 1 ) {

                if (coordinate == 'x') this.subCommand.add(String.valueOf(p.getLocation().getBlockX()));
                if (coordinate == 'y') this.subCommand.add(String.valueOf(p.getLocation().getBlockY()));
                if (coordinate == 'z') this.subCommand.add(String.valueOf(p.getLocation().getBlockZ()));

            } else {
                this.subCommand = List.of("");
            }
            return this;
        }

        /**
         * Get player target position x/y/z
         *
         * @param args String[]
         * @param argsIndex int
         * @param p Player
         * @param coordinate char (x or y or z) in minuscule
         *
         * @return SubCommandSelector
         */
        public SubCommandSelector getPlayerTargetPosition(@NotNull String @NotNull [] args, int argsIndex, Player p, char coordinate) {
            this.argsIndex = argsIndex;

            if (args.length == argsIndex  + 1 ) {
                if (coordinate == 'x') this.subCommand.add(String.valueOf(p.getTargetBlock(null, 500).getLocation().getBlockX()));
                if (coordinate == 'y') this.subCommand.add(String.valueOf(p.getTargetBlock(null, 500).getLocation().getBlockY()));
                if (coordinate == 'z') this.subCommand.add(String.valueOf(p.getTargetBlock(null, 500).getLocation().getBlockZ()));

            } else {
                this.subCommand = List.of("");
            }
            return this;
        }


        public SubCommandSelector getFlag(@NotNull String[] args, int argsIndex, String tag) {
            this.argsIndex = argsIndex;

            if (args.length == argsIndex  + 1 ) {

                // Condition use tag
                if (args[argsIndex].startsWith("-")) {

                    this.subCommand = new ArrayList<>();

                    String tagEdit = tag;

                    // Remove tag already used
                    if (args[argsIndex].length() > 1)

                        for (char c : args[argsIndex].toCharArray()) {
                            tagEdit = tagEdit.replace(String.valueOf(c), "");
                        }

                    //Add tag to subCommand
                    for (char c : tagEdit.toCharArray()) {
                        this.subCommand.add(args[argsIndex] + c);
                    }
                }
                else {
                    this.subCommand = List.of("");
                }
            } else {
                this.subCommand = List.of("");
            }
            return this;
        }


        public List<SubCommand> toSubCommand(String permission) {
            List<SubCommand> subCommandsList = new ArrayList<>();

            this.subCommand.forEach(s -> subCommandsList.add(new SubCommand(s, permission, this.argsIndex)));
            return subCommandsList;
        }

        public List<SubCommand> toSubCommand(String permission, ConditionArgument conditionArgument) {
            List<SubCommand> subCommandsList = new ArrayList<>();

            this.subCommand.forEach(s -> subCommandsList.add(new SubCommand(s, permission, this.argsIndex, conditionArgument)));
            return subCommandsList;
        }

        public List<SubCommand> toSubCommand(String permission, ConditionArgumentBefore conditionArgumentBefore) {
            List<SubCommand> subCommandsList = new ArrayList<>();

            this.subCommand.forEach(s -> subCommandsList.add(new SubCommand(s, permission, this.argsIndex, conditionArgumentBefore)));
            return subCommandsList;
        }
    }

    public static class OptionalConditionExecution {

        private CommandSender commandSender;

        private boolean selectionDefined;
        private boolean haveBrushBuilderProfile;

        public OptionalConditionExecution(CommandSender commandSender) {
            this.commandSender = commandSender;
        }

        private Player getPlayer() {
            return (Player) this.commandSender;
        }

        private BrushBuilder getBrushBuilder() {
            return BrushBuilder.getBrushBuilderPlayer(getPlayer(), false);
        }

        private boolean verifyOptionalCondition() {

            if (this.haveBrushBuilderProfile) {
                if (!this.executeHaveBrushBuilderProfile()) {
                    return false;
                }
            }

            if (this.selectionDefined) {
                if (!this.executeConditionSelection()) {
                    return false;
                }
            }

            return true;
        }

        public OptionalConditionExecution AddConditionSelection() {
            this.selectionDefined = true;
            return this;
        }

        public OptionalConditionExecution AddBrushBuilderProfile() {
            this.haveBrushBuilderProfile = true;
            return this;
        }

        private boolean executeConditionSelection() {

            BukkitPlayer bukkitPlayer = BukkitAdapter.adapt(this.getPlayer());
            LocalSession session = bukkitPlayer.getSession();

            if (!session.getRegionSelector(bukkitPlayer.getWorld()).isDefined()) {
                new Message.MessageSender("expbuild.message.error.error_incomplete_selection", true).send(this.getPlayer());
                return false;

            } else { return true; }
        }

        private boolean executeHaveBrushBuilderProfile() {

            if (Main.containsBrushBuilder(this.getPlayer())) {
                return true;
            } else {
                new Message.MessageSender("expbuild.message.permission.no_permission_node", true, new String[]{"'exp.register'"}).send(this.getPlayer());
                return false;
            }
        }

        @Override
        public String toString() {
            return "OptionalConditionExecution{" +
                    "commandSender=" + commandSender +
                    ", selectionDefined=" + selectionDefined +
                    '}';
        }
    }
    public static class ArgumentLengthList {

        private List<ArgumentLength> argumentLengthList;
        private boolean noArgumentLengthList;

        public ArgumentLengthList(List<ArgumentLength> argumentLengthList) {
            this.argumentLengthList = argumentLengthList;
            this.noArgumentLengthList = false;
        }

        public ArgumentLengthList(boolean noArgumentLengthList) {
            this.noArgumentLengthList = noArgumentLengthList;
        }

        public List<ArgumentLength> getArgumentLengthList() {
            return argumentLengthList;
        }

        public boolean getNoArgumentLengthList() {
            return noArgumentLengthList;
        }
    }


    public static class ArgumentLength {

        private final int argsLength;

        private final String argCondition;
        private final int argConditionPosition;

        private final String syntaxCommand;

        //High priority = 1; Low priority = 10
        private final int priority;

        public ArgumentLength(int argsLength, String argCondition, int argConditionPosition, String syntaxCommand, int priority) {
            this.argsLength = argsLength;
            this.argCondition = argCondition;
            this.argConditionPosition = argConditionPosition;
            this.syntaxCommand = syntaxCommand;
            this.priority = priority;
        }

        public int getArgsLength() {
            return this.argsLength;
        }

        public String getArgCondition() {
            return this.argCondition;
        }

        public int getArgConditionPosition() {
            return this.argConditionPosition;
        }

        public String getSyntaxCommand() {
            return this.syntaxCommand;
        }

        public int getPriority() {
            return this.priority;
        }

        @Override
        public String toString() {
            return "ArgumentLength{" +
                    "argsLength=" + argsLength +
                    ", argCondition='" + argCondition + '\'' +
                    ", argConditionPosition=" + argConditionPosition +
                    ", syntaxCommand='" + syntaxCommand + '\'' +
                    ", priority=" + priority +
                    '}';
        }
    }

    public static class ValidArgument {


        public ValidArgument() {
        }

        // Pattern
        public boolean isPattern(Player p, String arg) {

            try {
                Pattern pattern = new FaweAPI(p).getPattern(arg);
                return true;
            } catch (NullPointerException | SuggestInputParseException ignored) {
                return false;
            }
        }

        public Pattern getPattern(Player p, String arg) {
            return new FaweAPI(p).getPattern(arg);
        }

        public void sendMessageInvalidPattern(@NotNull CommandSender sender, String arg) {
            new Message.MessageSender("expbuild.message.error.invalid_argument", true, new String[]{arg, "pattern"}).send(sender);
        }

        // Integer
        public boolean isInteger(String arg, int min, int max) {

            try {
                int v = Integer.parseInt(arg);
                return v >= min && v <= max;
            } catch (NumberFormatException ignorred) {
                return false;
            }
        }

        public Integer getInteger(String arg) {
            return Integer.parseInt(arg);
        }

        public void sendMessageInvalidInteger(@NotNull CommandSender sender, String arg, int min, int max) {
            new Message.MessageSender("expbuild.message.error.invalid_argument_integer", true, new String[]{arg, Integer.toString(min), Integer.toString(max)}).send(sender);
        }

        // Double
        public boolean isDouble(String arg, int min, int max) {

            try {
                double v = Double.parseDouble(arg);
                return v >= min && v <= max;
            } catch (NumberFormatException ignorred) {
                return false;
            }
        }

        public Double getDouble(String arg) {
            return Double.parseDouble(arg);
        }

        public void sendMessageInvalidDouble(@NotNull CommandSender sender, String arg, Double min, Double max) {
            new Message.MessageSender("expbuild.message.error.invalid_argument_integer", true, new String[]{arg, Double.toString(min), Double.toString(max)}).send(sender);
        }

        // Biome
        public boolean isBiome(@NotNull String arg) {
            return Stream.of(Biome.values())
                    .map(Biome::toString).toList().toString().contains(arg.toUpperCase());
        }

        public Biome getBiome(@NotNull String arg) {
            return Biome.valueOf(arg.toUpperCase());
        }

        public void sendMessageInvalidBiome(@NotNull CommandSender sender, String arg) {
            new Message.MessageSender("expbuild.message.error.invalid_argument", true, new String[]{arg, "biome"}).send(sender);

            //sender.sendMessage(Main.prefix + msg.getInvalidArgument(arg, "biome"));
        }

        // Selection
        public boolean hasSelection(Player p) {
            BukkitPlayer bukkitPlayer = BukkitAdapter.adapt(p);
            LocalSession session = bukkitPlayer.getSession();
            return session.getRegionSelector(bukkitPlayer.getWorld()).isDefined();
        }

        public Region getSelection(Player p) {
            BukkitPlayer bukkitPlayer = BukkitAdapter.adapt(p);
            LocalSession session = bukkitPlayer.getSession();
            return session.getSelection();
        }

        public void sendMessageInvalidSelection(@NotNull CommandSender sender) {
            new Message.MessageSender("expbuild.message.error.error_incomplete_selection", true).send(sender);
        }

        // BlockDataColor
        public boolean isBlockDataColor(String arg) {
            return BlocksDataColor.getStreamArray().map(BlocksDataColor::getName).toList().contains(arg);
        }

        public BlocksDataColor getBlockDataColor(@NotNull String arg) {
            return BlocksDataColor.valueOf(arg.toUpperCase());
        }

        public void sendMessageInvalidBlockDataColor(@NotNull CommandSender sender, String arg) {
            new Message.MessageSender("expbuild.message.error.invalid_argument", true, new String[]{arg, "pattern"}).send(sender);
        }

        public boolean isFlag(String arg) {
            return (arg.startsWith("-"));
        }

        public <T> Flag getFlag(@NotNull String arg) {

            Flag flag = new Flag("bcem");

            arg = arg.replace("-", "");

            for (char c : arg.toCharArray()) {
                flag.add(c);

            }
            return flag;
        }

        public void sendMessageInvalidIFlag(Player p, String arg) {
            new Message.MessageSender("expbuild.message.error.invalid_argument", true, new String[]{arg, "flag"}).send(p);
        }
    }
}