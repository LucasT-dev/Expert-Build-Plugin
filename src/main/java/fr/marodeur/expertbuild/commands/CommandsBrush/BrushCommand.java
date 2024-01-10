package fr.marodeur.expertbuild.commands.CommandsBrush;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.enums.BrushEnum;
import fr.marodeur.expertbuild.enums.ExecutorType;
import fr.marodeur.expertbuild.object.*;
import org.bukkit.Bukkit;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrushCommand extends AbstractCommand {

    private static final MessageBuilder msg = Main.getInstance().getMessageConfig();
    private static final Configuration conf = Main.getInstance().getConfig();

    @Override
    public String getCommand() {
        return "/flower";
    }

    @Override
    public String getSyntax() {
        return "/flower <brush> [pattern - radius]";
    }

    @Override
    public Integer getMinimumArgsLength() {
        return 1;
    }

    @Override
    public String getPermission() {
        return "exp.command.flower";
    }

    @Override
    public List<ExecutorType> getExecutorType() {
        return List.of(ExecutorType.PLAYER);
    }

    @Override
    public void execute(CommandSender executor, Command command, @NotNull String label, @NotNull String[] args) {

        BrushBuilder brushBuilder = this.getBrushBuilder((Player) executor);
        Player p = (Player) executor;

        Pattern pattern;
        Integer radius = 0;
        Biome biome;

        switch (args[0]) {

            case "material" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                brushBuilder.setPattern(pattern).sendMessage(msg.getMaterialSet());
            }

            case "radius" -> {

                if (this.getValidArgument().isInteger(args[1], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setRadius(radius).sendMessage(msg.getRadiusSet());
            }

            case "biome" -> {

                if (this.getValidArgument().isBiome(args[1])) {
                    biome = this.getValidArgument().getBiome(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidBiome(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.BIOME)
                        .setEnable(true)
                        .setBiome(biome)
                        .setRadius(radius)
                        .sendMessage(msg.getBrushEnable("Biome"));
            }

            // TYPE : Brush Pattern Radius
            case "overlay" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.OVERLAY)
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage(msg.getBrushEnable("Overlay"));

            }
            case "spike" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.SPIKE)
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage(msg.getBrushEnable("Spike"));

            }
            case "cube" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.CUBE)
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage(msg.getBrushEnable("Cube"));
            }

            case "sphere" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.SPHERE)
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage(msg.getBrushEnable("Sphere"));
            }

            case "rot2Dcube" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.OVERLAY)
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage(msg.getBrushEnable("Rot2Dcube"));
            }

            // TYPE : Brush pattern
            case "line" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.LINE)
                        .setEnable(true)
                        .setPattern(pattern)
                        .sendMessage(msg.getBrushEnable("Line"));
            }

            // TYPE : Brush integer
            case "bb" -> {

                if (this.getValidArgument().isInteger(args[1], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.BLENDBALL)
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage(msg.getBrushEnable("blendBall"));

            }

            case "drain" -> {

                if (this.getValidArgument().isInteger(args[1], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.DRAIN)
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage(msg.getBrushEnable("Drain"));

            }

            case "updatechunk" -> {

                if (this.getValidArgument().isInteger(args[1], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.UPDATECHUNK)
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage(msg.getBrushEnable("UpdateChunk"));

            }

            case "eraser" -> {

                if (this.getValidArgument().isInteger(args[1], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.ERASER)
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage(msg.getBrushEnable("Eraser"));

            }

            case "e" -> {

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                switch (args[1]) {

                    case "lift" -> brushBuilder.setBrushType(BrushEnum.ERODE)
                            .setRadius(radius)
                            .setErosionFaces(6)
                            .setErosionRecursion(0)
                            .setFillFaces(1)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(msg.getBrushEnable("Erode lift"));

                    case "melt" -> brushBuilder.setBrushType(BrushEnum.ERODE)
                            .setRadius(radius)
                            .setErosionFaces(2)
                            .setErosionRecursion(1)
                            .setFillFaces(5)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(msg.getBrushEnable("Erode melt"));

                    case "fill" -> brushBuilder.setBrushType(BrushEnum.ERODE)
                            .setRadius(radius)
                            .setErosionFaces(5)
                            .setErosionRecursion(1)
                            .setFillFaces(2)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(msg.getBrushEnable("Erode fill"));

                    case "smooth" -> brushBuilder.setBrushType(BrushEnum.ERODE)
                            .setRadius(radius)
                            .setErosionFaces(3)
                            .setErosionRecursion(1)
                            .setFillFaces(3)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(msg.getBrushEnable("Erode smooth"));

                    case "floatclean" -> brushBuilder.setBrushType(BrushEnum.ERODE)
                            .setRadius(radius)
                            .setErosionFaces(6)
                            .setErosionRecursion(1)
                            .setFillFaces(6)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(msg.getBrushEnable("Erode floatClean"));
                }
            }

            case "eb" -> {

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                switch (args[1]) {

                    case "lift" -> brushBuilder.setBrushType(BrushEnum.ERODEBLEND)
                            .setRadius(radius)
                            .setErosionFaces(6)
                            .setErosionRecursion(0)
                            .setFillFaces(1)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(msg.getBrushEnable("ErodeBlend lift"));

                    case "melt" -> brushBuilder.setBrushType(BrushEnum.ERODEBLEND)
                            .setRadius(radius)
                            .setErosionFaces(2)
                            .setErosionRecursion(1)
                            .setFillFaces(5)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(msg.getBrushEnable("ErodeBlend melt"));

                    case "fill" -> brushBuilder.setBrushType(BrushEnum.ERODEBLEND)
                            .setRadius(radius)
                            .setErosionFaces(5)
                            .setErosionRecursion(1)
                            .setFillFaces(2)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(msg.getBrushEnable("ErodeBlend fill"));

                    case "smooth" -> brushBuilder.setBrushType(BrushEnum.ERODEBLEND)
                            .setRadius(radius)
                            .setErosionFaces(3)
                            .setErosionRecursion(1)
                            .setFillFaces(3)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(msg.getBrushEnable("ErodeBlend smooth"));

                    case "floatclean" -> brushBuilder.setBrushType(BrushEnum.ERODEBLEND)
                            .setRadius(radius)
                            .setErosionFaces(6)
                            .setErosionRecursion(1)
                            .setFillFaces(6)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(msg.getBrushEnable("ErodeBlend floatClean"));

                }
            }

            case "custom" -> {

                int parameter1;
                int parameter2;
                int parameter3;
                int parameter4;

                if (this.getValidArgument().isInteger(args[1], 0, 6)) {
                    parameter1 = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, 6);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, 6)) {
                    parameter2 = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, 6);
                    break;
                }

                if (this.getValidArgument().isInteger(args[3], 0, 6)) {
                    parameter3 = this.getValidArgument().getInteger(args[3]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[3], 0, 6);
                    break;
                }

                if (this.getValidArgument().isInteger(args[4], 0, 6)) {
                    parameter4 = this.getValidArgument().getInteger(args[4]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[4], 0, 6);
                    break;
                }

                if (this.getValidArgument().isInteger(args[4], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[4]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[4], 0, 6);
                    break;
                }

                brushBuilder.setBrushType(BrushEnum.ERODE)
                        .setErosionFaces(parameter1)
                        .setErosionRecursion(parameter2)
                        .setFillFaces(parameter3)
                        .setFillRecursion(parameter4)
                        .setRadius(radius)
                        .setEnable(true)
                        .sendMessage(msg.getBrushEnable("Custom"));

            }

            case "none" -> brushBuilder.setBrushType(BrushEnum.NONE)
                    .setEnable(false)
                    .sendMessage(msg.getBrushDisable());

            case "register" -> {

                if (args.length <= 1) {
                    BrushBuilder.registerPlayer(p, false);
                }

                if (args.length == 2) {

                    Bukkit.getOnlinePlayers().stream()
                            .filter(player -> player.getName().equals(args[1]))
                            .forEach(player ->
                                    p.sendMessage(Main.prefix + BrushBuilder.getBrushBuilderPlayer(player, false).toString()));
                }
            }

            case "clipboard" -> clipboardCommand(p, args, this.getValidArgument());
        }
    }


    @Override
    protected OptionalConditionExecution getArgumentLengthList(CommandSender sender) {
        return new OptionalConditionExecution(sender).AddBrushBuilderProfile();
    }

    @Override
    protected ArgumentLengthList getArgumentLengthList() {
        return new ArgumentLengthList(Arrays.asList(

                new ArgumentLength(2, "material", 0, "/flower material <pattern>", 2),
                new ArgumentLength(2, "radius", 0, "/flower radius <integer>", 2),

                //register

                new ArgumentLength(3, "biome", 0, "/flower biome <biome> <radius>", 2),

                new ArgumentLength(3, "line", 0, "/flower line <pettern>", 2),

                new ArgumentLength(3, "overlay", 0, "/flower overlay <pattern> <radius>", 2),
                new ArgumentLength(3, "spike", 0, "/flower spike <pattern> <radius>", 2),
                new ArgumentLength(3, "cube", 0, "/flower cube <pattern> <radius>", 2),
                new ArgumentLength(3, "rot2Dcube", 0, "/flower rot2Dcube <pattern> <radius>", 2),
                new ArgumentLength(3, "sphere", 0, "/flower sphere <pattern> <radius>", 2),

                new ArgumentLength(2, "updatechunk", 0, "/flower updatechunk <radius>", 2),
                new ArgumentLength(2, "drain", 0, "/flower drain  <radius>", 2),
                new ArgumentLength(2, "eraser", 0, "/flower eraser <radius>", 2),
                new ArgumentLength(2, "bb", 0, "/flower bb <radius>", 2),

                new ArgumentLength(3, "e", 0, "/flower e <lift-melt-fill-smooth-floatclean> <radius>", 2),
                new ArgumentLength(3, "eb", 0, "/flower eb <lift-melt-fill-smooth-floatclean> <radius>", 2),

                new ArgumentLength(6, "custom", 0, "/flower bb <erosion face> <erosion recursion> <fill faces> <fill recursion> <radius>", 2),


                new ArgumentLength(3, "remove", 1, "/flower clipboard remove <clipboard name>", 2),
                new ArgumentLength(2, "removeAll", 1, "/flower clipboard removeAll", 1),
                new ArgumentLength(2, "autorotation", 1, "/flower clipboard autorotation", 1),
                new ArgumentLength(2, "add", 1, "/flower clipboard add [clipboard name]", 1),

                new ArgumentLength(2, "clipboard", 0, "/flower clipboard <add-remove-removeAll-autorotation>", 2)

        ));
    }

    @Override
    public SubCommandSender getSubCommand(CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        final List<String> erodeArgs = Arrays
                .asList("lift", "melt", "fill", "smooth", "floatclean");

        final List<String> clipboardBrush = Arrays
                .asList("add", "removeAll", "remove", "autoRotation");

        SubCommandSender subCommandSender = new SubCommandSender();

        if (sender instanceof Player p) {

            // Brush
            Arrays.stream(BrushEnum.values()).toList().forEach(brushEnum -> subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, brushEnum.getBrush()).toSubCommand(brushEnum.getPermission())));

            // Material
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "material").toSubCommand("None"));
            // Radius
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "radius").toSubCommand("None"));
            // Register
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "register").toSubCommand("exp.register"));
            subCommandSender.addSubCommand(new SubCommandSelector().getPlayerList(args, 1).toSubCommand("None", new ConditionArgumentBefore("register", 0)));


            // Biome
            subCommandSender.addSubCommand(new SubCommandSelector().getBiomeList(args, 1).toSubCommand("exp.brush.biome", new ConditionArgumentBefore("biome", 0)));

            // Brush with pattern <pattern>
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("none", new ConditionArgumentBefore("material", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.overlay", new ConditionArgumentBefore("overlay", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.spike", new ConditionArgumentBefore("spike", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.cube", new ConditionArgumentBefore("cube", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.line", new ConditionArgumentBefore("line", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.sphere", new ConditionArgumentBefore("sphere", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.2dcube", new ConditionArgumentBefore("rot2Dcube", 0)));

            // Brush with pattern/biome and integer <integer>
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.overlay", new ConditionArgumentBefore("overlay", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.spike", new ConditionArgumentBefore("spike", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.cube", new ConditionArgumentBefore("cube", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.2dcube", new ConditionArgumentBefore("rot2Dcube", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.sphere", new ConditionArgumentBefore("sphere", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.biome", new ConditionArgumentBefore("biome", 0)));


            // Brush with <integer>
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.blendball", new ConditionArgumentBefore("bb", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.drain", new ConditionArgumentBefore("drain", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.updatechunk", new ConditionArgumentBefore("update_chunk", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.eraser", new ConditionArgumentBefore("eraser", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("none", new ConditionArgumentBefore("radius", 0)));


            // Erode / ErodeBlend
            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, erodeArgs).toSubCommand("exp.brush.erode", new ConditionArgumentBefore("e", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, erodeArgs).toSubCommand("exp.brush.erodeblend", new ConditionArgumentBefore("eb", 0)));

            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("lift", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("melt", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("smooth", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("fill", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("floatclean", 1)));


            // Custom erode brush
            // Custom
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "custom").toSubCommand("exp.brush.custom"));

            // Erosion face <integer>
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("custom", 0)));
            // Erosion recursion
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("custom", 0)));
            // Fill faces
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 3).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("custom", 0)));
            // Fill recursion
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 4).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("custom", 0)));
            // Custom erode radius <integer>
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 5).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("custom", 0)));

            // Clipboard
            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, clipboardBrush).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("clipboard", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getList(2, this.getBrushBuilder(p).getClipboardsParameter().getClipboardsName().stream().toList()).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("remove", 1)));

        }
        return subCommandSender;
    }

    private static void clipboardCommand(Player p, String @NotNull [] args, ValidArgument validArgument) {

        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);

        if (args[1].equalsIgnoreCase("autoRotation")) {

            if (brushBuilder.getClipboardsParameter().isRandomRotation()) {
                brushBuilder.sendMessage(msg.getDisable("Auto-rotation"))
                        .getClipboardsParameter().setRandomRotation(false);
            } else {
                brushBuilder.sendMessage(msg.getEnable("Auto-rotation"))
                        .getClipboardsParameter().setRandomRotation(true);
            }
        }

        if (args[1].equalsIgnoreCase("removeAll")) {

            brushBuilder.setBrushType(BrushEnum.NONE)
                    .setEnable(false)
                    .sendMessage(msg.getAllClipboardDelete())
                    .getClipboardsParameter().clearAll();
            return;
        }
        if (args[1].equalsIgnoreCase("remove")) {

            if (args.length == 2) {
                brushBuilder.sendMessage(msg.getUse("/fw clipboard remove <clipboard-name>"));
                return;
            }

            if (brushBuilder.getClipboardsParameter().getClipboardsNameExist(args[2])) {
                brushBuilder.sendMessage(msg.getClipboardRemove(args[2]))
                        .getClipboardsParameter().removeClipboards(args[2]);
            } else {
                brushBuilder.sendMessage(msg.getClipboardDoesNotExist(args[2]));
            }
            return;

        }

        if (args[1].equalsIgnoreCase("add")) {

            if (validArgument.hasSelection(p)) {
            } else {
                validArgument.sendMessageInvalidSelection(p);
                return;
            }

            String clipboardName;

            if (args.length >= 3) {

                if (brushBuilder.getClipboardsParameter().getClipboardsNameExist(args[2])) {
                    brushBuilder.sendMessage(msg.getClipboardAlreadyExist(args[2]));
                    return;
                } else {
                    clipboardName = args[2];
                }

            } else {
                clipboardName = "clipboards_" + brushBuilder.getClipboardsParameter().getClipboardsBlock().size();
            }


            Clipboard clip = new UtilsFAWE(p).CopySelection(false);
            List<BlockVec4> list = new ArrayList<>();

            clip.iterator().forEachRemaining(blockVector3 -> {

                BlockVector3 blockVector31 = clip.getOrigin().add(blockVector3);

                int blockX = blockVector31.getBlockX() - clip.getOrigin().getX();
                int blockY = blockVector31.getBlockY() - clip.getOrigin().getY();
                int blockZ = blockVector31.getBlockZ() - clip.getOrigin().getZ();

                int deltaX = blockX - clip.getOrigin().getX();
                int deltaY = blockY - clip.getOrigin().getY();
                int deltaZ = blockZ - clip.getOrigin().getZ();

                list.add(new BlockVec4(
                        deltaX,
                        deltaY,
                        deltaZ,
                        clip.getFullBlock(blockX, blockY, blockZ)));
            });

            brushBuilder.setBrushType(BrushEnum.CLIPBOARD)
                    .setEnable(true)
                    .sendMessage(msg.getClipboardAddAndEnable(clipboardName))
                    .getClipboardsParameter().addClipboards(list, clipboardName);

        }
    }
}
