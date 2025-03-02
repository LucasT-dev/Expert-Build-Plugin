package fr.marodeur.expertbuild.commands.CommandsBrush;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.regions.Region;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.brush.*;
import fr.marodeur.expertbuild.enums.ExecutorType;
import fr.marodeur.expertbuild.object.*;
import fr.marodeur.expertbuild.object.builderObjects.Clipboard3DParameter;
import fr.marodeur.expertbuild.object.builderObjects.ClipboardParameter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class BrushCommand extends AbstractCommand {

    private static final Configuration CONFIG;

    static {

        CONFIG = Main.getConfiguration();
    }

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
        Integer radius;

        if (args.length == 1 && !args[0].equalsIgnoreCase("bb") && !args[0].equalsIgnoreCase("blendball") && !args[0].equalsIgnoreCase("clipboard3D") && !args[0].equalsIgnoreCase("none")) {

            if (this.getValidArgument().isInteger(args[0], 1, CONFIG.getMaxRayonBrush())) {
                int rad = this.getValidArgument().getInteger(args[0]);
                brushBuilder.setRadius(rad)
                        .sendMessage("expbuild.message.brush.radius_set", true);

            } else {
                this.getValidArgument().sendMessageInvalidInteger(executor, args[0], 1, CONFIG.getMaxRayonBrush());
                return;
            }
        }

        switch (args[0].toLowerCase()) {

            case "material" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                brushBuilder.setPattern(pattern)
                        .sendMessage("expbuild.message.brush.material_set", true);
            }

            case "radius" -> {

                if (this.getValidArgument().isInteger(args[1], 0, CONFIG.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, CONFIG.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setRadius(radius)
                        .sendMessage("expbuild.message.brush.radius_set", true);
            }

            case "shape" -> {

                AbstractShape shape;

                if (this.getValidArgument().isShape(args[1])) {
                    shape = this.getValidArgument().getShape(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidShape(executor, args[1]);
                    break;
                }

                brushBuilder.getBrushParameter().setShape(shape)
                        .sendMessage("expbuild.message.brush.shape_set", true);
            }

            // TYPE : Brush Pattern Radius
            case "overlay" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, CONFIG.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, CONFIG.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new OverlayBrush())
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Overlay"});

            }
            case "spike" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, CONFIG.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, CONFIG.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new SpikeBrush())
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Spike"});

            }
            case "cube" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, CONFIG.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, CONFIG.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new CubeBrush())
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Cube"});
            }

            case "2dcube" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, CONFIG.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, CONFIG.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new Rot2DCubeBrush())
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"2Dcube"});
            }

            // TYPE : Brush pattern
            case "line" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                brushBuilder.setBrush(new LineBrush())
                        .setEnable(true)
                        .setPattern(pattern)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"line"});
            }

            // TYPE : Brush integer
            case "blendball", "bb" -> {

                if (args.length >= 2) {

                    if (this.getValidArgument().isInteger(args[1], 0, CONFIG.getMaxRayonBrush())) {
                        radius = this.getValidArgument().getInteger(args[1]);
                        brushBuilder.setRadius(radius);
                    } else {
                        this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, CONFIG.getMaxRayonBrush());
                        break;
                    }
                }

                brushBuilder.setBrush(new BlendBallBrush())
                        .setEnable(true)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"blendBall"});
            }

            case "drain" -> {

                if (this.getValidArgument().isInteger(args[1], 1, CONFIG.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 1, CONFIG.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new DrainBrush())
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"drain"});

            }

            case "updatechunk" -> {

                if (this.getValidArgument().isInteger(args[1], 0, CONFIG.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 1, CONFIG.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new UpdateChunkBrush())
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"UpdateChunk"});

            }

            case "eraser" -> {

                if (this.getValidArgument().isInteger(args[1], 0, CONFIG.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 1, CONFIG.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new EraserBrush())
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"eraser"});

            }

            /*case "hydrology" -> {

                if (this.getValidArgument().isInteger(args[1], 0, CONFIG.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, CONFIG.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new HydrologyBrush())
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"hydrology"});

            }*/

            case "degrade" -> {

                if (this.getValidArgument().isInteger(args[1], 1, CONFIG.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 1, CONFIG.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new DegradeBrush())
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Degrade"});

            }

            case "clipboard3d" -> {

                if (this.getValidArgument().hasSelection(p)) {
                    Region selection = this.getValidArgument().getSelection(p);
                } else {
                    this.getValidArgument().sendMessageInvalidSelection(executor);
                    break;
                }

                Clipboard3DParameter cb3d = brushBuilder.getClipboard3dParameter();

                cb3d.setClipboardHolder(new FaweAPI(p).copySelection(false, false, false, true));

                brushBuilder.setBrush(new Clipboard3DBrush())
                        .setEnable(true)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"3DClipboard"});
            }

            case "erode", "e" -> {

                if (args.length >= 3) {

                    if (this.getValidArgument().isInteger(args[2], 1, CONFIG.getMaxRayonBrush())) {
                        radius = this.getValidArgument().getInteger(args[2]);
                        brushBuilder.setRadius(radius);
                    } else {
                        this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 1, CONFIG.getMaxRayonBrush());
                        break;
                    }
                }

                switch (args[1]) {

                    case "lift" -> brushBuilder.setBrush(new ErodeBrush())
                            .setEnable(true)
                            .getTerraParameterProfile()

                            .setErosionFaces((byte) 6)
                            .setErosionRecursion((byte)0)
                            .setFillFaces((byte)1)
                            .setFillRecursion((byte)1)

                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"erode lift"});

                    case "melt" -> brushBuilder.setBrush(new ErodeBrush())
                            .setEnable(true)
                            .getTerraParameterProfile()
                            .setErosionFaces((byte) 2)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 5)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"erode melt"});

                    case "fill" -> brushBuilder.setBrush(new ErodeBrush())
                            .setEnable(true)
                            .getTerraParameterProfile()
                            .setErosionFaces((byte) 5)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 2)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"erode fill"});

                    case "smooth" -> brushBuilder.setBrush(new ErodeBrush())
                            .setEnable(true)
                            .getTerraParameterProfile()
                            .setErosionFaces((byte) 3)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 3)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"erode smooth"});

                    case "floatclean" -> brushBuilder.setBrush(new ErodeBrush())
                            .setEnable(true)
                            .getTerraParameterProfile()
                            .setErosionFaces((byte) 6)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 6)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"erode floatClean"});
                }
            }

            //fw eb melt 5
            case "eb", "erodeblend" -> {

                if (args.length >= 3) {

                    if (this.getValidArgument().isInteger(args[2], 1, CONFIG.getMaxRayonBrush())) {
                        radius = this.getValidArgument().getInteger(args[2]);
                        brushBuilder.setRadius(radius);
                    } else {
                        this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 1, CONFIG.getMaxRayonBrush());
                        break;
                    }
                }

                switch (args[1]) {

                    case "lift" -> brushBuilder.setBrush(new ErodeBlendBrush())
                            .setEnable(true)
                            .getTerraParameterProfile()
                            .setErosionFaces((byte) 6)
                            .setErosionRecursion((byte) 0)
                            .setFillFaces((byte) 1)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"ErodeBlend lift"});

                    case "melt" -> brushBuilder.setBrush(new ErodeBlendBrush())
                            .setEnable(true)
                            .getTerraParameterProfile()
                            .setErosionFaces((byte) 2)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 5)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"ErodeBlend melt"});

                    case "fill" -> brushBuilder.setBrush(new ErodeBlendBrush())
                            .setEnable(true)
                            .getTerraParameterProfile()
                            .setErosionFaces((byte) 5)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 2)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"ErodeBlend fill"});

                    case "smooth" -> brushBuilder.setBrush(new ErodeBlendBrush())
                            .setEnable(true)
                            .getTerraParameterProfile()
                            .setErosionFaces((byte) 3)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 3)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"ErodeBlend smooth"});

                    case "floatclean" -> brushBuilder.setBrush(new ErodeBlendBrush())
                            .setEnable(true)
                            .getTerraParameterProfile()
                            .setErosionFaces((byte) 6)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 6)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"ErodeBlend floatClean"});

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

                if (this.getValidArgument().isInteger(args[5], 1, CONFIG.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[5]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[5], 1, CONFIG.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new ErodeBrush())
                        .setRadius(radius)
                        .setEnable(true)
                        .getTerraParameterProfile()
                        .setErosionFaces((byte) parameter1)
                        .setErosionRecursion((byte) parameter2)
                        .setFillFaces((byte) parameter3)
                        .setFillRecursion((byte) parameter4)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Custom"});

            }

            case "none" -> brushBuilder.setBrush(new NoneBrush())
                    .setEnable(false)
                    .sendMessage("expbuild.message.brush.brush_disable", true);

            case "register" -> {

                if (args.length <= 1) {
                    BrushBuilder.registerPlayer(p);
                }

                if (args.length == 2) {

                    Bukkit.getOnlinePlayers().stream()
                            .filter(player -> player.getName().equals(args[1]))
                            .forEach(player ->
                                    p.sendMessage("debug" + BrushBuilder.getBrushBuilderPlayer(player).toString()));
                }
            }

            case "clipboard" -> clipboardCommand(p, args, this.getValidArgument());
        }
    }


    @Override
    protected OptionalConditionExecution optionalConditionExecution(CommandSender sender) {
        return new OptionalConditionExecution(sender).AddBrushBuilderProfile();
    }

    @Override
    protected ArgumentLengthList getArgumentLengthList() {

        return new ArgumentLengthList(Arrays.asList(

                new ArgumentLength(2, "material", 0, "/flower material <pattern>", 2),
                new ArgumentLength(2, "radius", 0, "/flower radius <integer>", 2),
                new ArgumentLength(2, "shape", 0, "/flower shape <shape>", 2),

                //register

                new ArgumentLength(2, "line", 0, "/flower line <pattern>", 2),

                new ArgumentLength(3, "overlay", 0, "/flower overlay <pattern> <radius>", 2),
                new ArgumentLength(3, "spike", 0, "/flower spike <pattern> <radius>", 2),
                new ArgumentLength(3, "cube", 0, "/flower cube <pattern> <height>", 2),
                new ArgumentLength(3, "2dcube", 0, "/flower 2Dcube <pattern> <height>", 2),

                new ArgumentLength(2, "updatechunk", 0, "/flower updatechunk <radius>", 2),
                new ArgumentLength(2, "degrade", 0, "/flower degrade <radius>", 2),

                new ArgumentLength(2, "hydrology", 0, "/flower hydrology <radius>", 2),
                new ArgumentLength(2, "drain", 0, "/flower drain  <radius>", 2),
                new ArgumentLength(2, "eraser", 0, "/flower eraser <radius>", 2),
                new ArgumentLength(1, "blendball", 0, "/flower bb [radius]", 2),
                new ArgumentLength(1, "bb", 0, "/flower bb [radius]", 2),


                new ArgumentLength(2, "e", 0, "/flower e <lift-melt-fill-smooth-floatclean> [radius]", 2),
                new ArgumentLength(2, "erode", 0, "/flower e <lift-melt-fill-smooth-floatclean> [radius]", 2),

                new ArgumentLength(2, "eb", 0, "/flower eb <lift-melt-fill-smooth-floatclean> [radius]", 2),
                new ArgumentLength(2, "erodeblend", 0, "/flower eb <lift-melt-fill-smooth-floatclean> [radius]", 2),

                new ArgumentLength(6, "custom", 0, "/flower bb <erosion face> <erosion recursion> <fill faces> <fill recursion> <radius>", 2),


                new ArgumentLength(3, "remove", 1, "/flower clipboard remove <clipboard name>", 2),
                new ArgumentLength(2, "removeAll", 1, "/flower clipboard clear", 1),
                new ArgumentLength(2, "autorotation", 1, "/flower clipboard autorotate", 1),
                new ArgumentLength(2, "add", 1, "/flower clipboard add [clipboard name] [flag]", 1),
                new ArgumentLength(3, "save", 1, "/flower clipboard save [clipboard folder name] [freeAccess]", 1),
                new ArgumentLength(3, "load", 1, "/flower clipboard load [clipboard folder name]", 1),
                new ArgumentLength(3, "delete", 1, "/flower clipboard delete [clipboard folder name]", 1),

                new ArgumentLength(2, "clipboard", 0, "/flower clipboard <add-remove-clear-autorotation>", 2)



        ));
    }

    @Override
    public SubCommandSender getSubCommand(CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        final List<String> erodeArgs = Arrays
                .asList("lift", "melt", "fill", "smooth", "floatclean");

        final List<String> clipboardBrush = Arrays
                .asList("add", "clear", "remove", "autoRotate", "save", "load", "delete");

        SubCommandSender subCommandSender = new SubCommandSender();

        if (sender instanceof Player p) {

            // Brush
            // name
            Main.getBrush().getBrushes().iterator().forEachRemaining(registerBrush -> subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, registerBrush.getBrushName()).toSubCommand(registerBrush.getPermission())));

            // aliases
            Main.getBrush().getBrushes().stream()
                    .filter(abstractBrush -> !abstractBrush.getAliases().equals("unused"))
                    .forEach(registerBrush -> subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, registerBrush.getAliases()).toSubCommand(registerBrush.getPermission())));

            //Arrays.stream(BrushEnum.values()).toList().forEach(brushEnum -> subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, brushEnum.getBrush()).toSubCommand(brushEnum.getPermission())));

            // Material
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "material").toSubCommand("None"));
            // Shape
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "shape").toSubCommand("None"));
            subCommandSender.addSubCommand(new SubCommandSelector().getShapeList(args, 1).toSubCommand("None", new ConditionArgumentBefore("shape", 0)));
            // Radius
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "radius").toSubCommand("None"));
            // Register
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "register").toSubCommand("exp.register"));
            subCommandSender.addSubCommand(new SubCommandSelector().getPlayerList(args, 1).toSubCommand("None", new ConditionArgumentBefore("register", 0)));


            // Brush with pattern <pattern>
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("none", new ConditionArgumentBefore("material", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.overlay", new ConditionArgumentBefore("overlay", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.spike", new ConditionArgumentBefore("spike", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.cube", new ConditionArgumentBefore("cube", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.line", new ConditionArgumentBefore("line", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.2dcube", new ConditionArgumentBefore("2Dcube", 0)));

            // Brush with pattern/biome and integer <integer>
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.overlay", new ConditionArgumentBefore("overlay", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.spike", new ConditionArgumentBefore("spike", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.cube", new ConditionArgumentBefore("cube", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.2dcube", new ConditionArgumentBefore("rot2Dcube", 0)));


            // Brush with <integer>
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.blendball", new ConditionArgumentBefore("bb", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.blendball", new ConditionArgumentBefore("blendball", 0)));

            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.drain", new ConditionArgumentBefore("drain", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.degrade", new ConditionArgumentBefore("degrade", 0)));

            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.updatechunk", new ConditionArgumentBefore("update_chunk", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.eraser", new ConditionArgumentBefore("eraser", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("none", new ConditionArgumentBefore("radius", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.hydrology", new ConditionArgumentBefore("hydrology", 0)));


            // Erode / ErodeBlend
            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, erodeArgs).toSubCommand("exp.brush.erode", new ConditionArgumentBefore("e", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, erodeArgs).toSubCommand("exp.brush.erode", new ConditionArgumentBefore("erode", 0)));

            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, erodeArgs).toSubCommand("exp.brush.erodeblend", new ConditionArgumentBefore("eb", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, erodeArgs).toSubCommand("exp.brush.erodeblend", new ConditionArgumentBefore("erodeblend", 0)));


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
            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, clipboardBrush).toSubCommand("exp.brush.clipboard", new ConditionArgumentBefore("clipboard", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getList(2, this.getBrushBuilder(p).getClipboardParameter().getClipboardsName().stream().toList()).toSubCommand("exp.brush.clipboard", new ConditionArgumentBefore("remove", 1)));

            subCommandSender.addSubCommand(new SubCommandSelector().getList(2, ClipboardParameter.getClipboardsSaveInFile(p.getUniqueId())).toSubCommand("exp.brush.clipboard", new ConditionArgumentBefore("load", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getList(2, ClipboardParameter.getClipboardsSaveInFile(p.getUniqueId())).toSubCommand("exp.brush.clipboard", new ConditionArgumentBefore("delete", 1)));


            // Clipboard add flag
            subCommandSender.addSubCommand(new SubCommandSelector().getFlag(args, 2, "abce").toSubCommand("exp.brush.clipboard", new ConditionArgumentBefore("add", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getFlag(args, 3, "abce").toSubCommand("exp.brush.clipboard", new ConditionArgumentBefore("add", 1)));

            // Clipboard save <name> <freeAccess>
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(2,"<name>").toSubCommand("exp.brush.clipboard", new ConditionArgumentBefore("save", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getBooleanList(args, 3).toSubCommand("exp.brush.clipboard", new ConditionArgumentBefore("save", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(3, "<freeAccess>").toSubCommand("exp.brush.clipboard", new ConditionArgumentBefore("save", 1)));

        }
        return subCommandSender;
    }

    private void clipboardCommand(Player p, String @NotNull [] args, ValidArgument validArgument) {

        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);
        ClipboardParameter clipboardParameter = brushBuilder.getClipboardParameter();

        if (args[1].equalsIgnoreCase("autoRotate")) {

            if (clipboardParameter.isRandomRotation()) {
                clipboardParameter
                        .setRandomRotation(false)
                        .sendMessage("expbuild.message.commands.disable", true, new String[]{"Auto-rotate"});

            } else {
                clipboardParameter
                        .setRandomRotation(true)
                        .sendMessage("expbuild.message.commands.enable", true, new String[]{"Auto-rotate"});
            }
        }

        if (args[1].equalsIgnoreCase("clear")) {

            brushBuilder.setBrush(new NoneBrush())
                    .setEnable(false)
                    .sendMessage("expbuild.message.commands.all_clipboards_delete", true);

            clipboardParameter.clearAll();

            return;
        }
        if (args[1].equalsIgnoreCase("remove")) {

            if (args.length == 2) {
                brushBuilder.sendMessage("expbuild.message.commands.use", true, new String[]{"/fw clipboard remove <clipboard-name>"});
                return;
            }

            if (clipboardParameter.getClipboardsNameExist(args[2])) {
                clipboardParameter
                        .removeClipboards(args[2])
                        .sendMessage("expbuild.message.commands.clipboard_remove", true, new String[]{args[2]});

            } else {
                brushBuilder.sendMessage("expbuild.message.commands.clipboard_does_not_exist", true, new String[]{args[2]});
            }
            return;

        }

        if (args[1].equalsIgnoreCase("add")) {

            if (!validArgument.hasSelection(p)) {
                validArgument.sendMessageInvalidSelection(p);
                return;
            }

            String clipboardName;
            Flag flag = new Flag("abce");

            if (args.length == 3) {

                if (args[2].startsWith("-")) {

                    if (this.getValidArgument().isFlag(args[2])) {
                        flag = this.getValidArgument().getFlag(args[2]);
                    } else {
                        this.getValidArgument().sendMessageInvalidIFlag(p, args[2]);
                        return;
                    }

                    clipboardName = "clipboards_" + clipboardParameter.getClipboardHolders().size();

                } else if (clipboardParameter.getClipboardsNameExist(args[2])) {

                    brushBuilder.sendMessage("expbuild.message.commands.clipboard_already_exist", true, new String[]{args[2]});
                    return;
                } else {
                    clipboardName = args[2];
                }

            } else if (args.length >= 4) {

                if (args[3].startsWith("-")) {

                    if (this.getValidArgument().isFlag(args[3])) {
                        flag = this.getValidArgument().getFlag(args[3]);
                    } else {
                        this.getValidArgument().sendMessageInvalidIFlag(p, args[3]);
                        return;
                    }


                    if (clipboardParameter.getClipboardsNameExist(args[2])) {

                        brushBuilder.sendMessage("expbuild.message.commands.clipboard_already_exist", true, new String[]{args[2]});
                        return;
                    } else {
                        clipboardName = args[2];
                    }

                } else {
                    // Flag invalid
                    this.getValidArgument().sendMessageInvalidIFlag(p, args[3]);
                    return;
                }

            } else {
                clipboardName = "clipboards_" + clipboardParameter.getClipboardHolders().size();
            }

            brushBuilder.setBrush(new ClipboardsBrush())
                    .setEnable(true)
                    .sendMessage("expbuild.message.commands.clipboard_add_and_enable", true, new String[]{clipboardName});

            clipboardParameter
                    .addClipboards(new FaweAPI(p)
                            .copySelection(
                                    flag.get('b'),
                                    flag.get('e'),
                                    false,
                                    false,
                                    flag.get('c') ? new BlockVectorTool().toBlockVectorTool(BukkitAdapter.adapt(p).getSession().getSelection().getCenter().toBlockPoint()) : new BlockVectorTool().toBlockVectorTool(p.getLocation())
                            ), clipboardName, flag);

        }

        if (args[1].equalsIgnoreCase("save")) {

            if (clipboardParameter.hasClipboardLoad()) {

                if (ClipboardParameter.folderExist(args[2])) {
                    brushBuilder.sendMessage("expbuild.message.commands.clipboard_folder_already_exist",new String[]{args[2]});
                    return;
                }

                if (args.length >= 4) {
                    if (args[3].equalsIgnoreCase("true")) {
                        clipboardParameter.saveToFolder(args[2], true);

                    } else {
                        clipboardParameter.saveToFolder(args[2], false);
                    }
                } else {
                    clipboardParameter.saveToFolder(args[2], false);
                }

                brushBuilder.sendMessage("expbuild.message.commands.clipboard_folder_save",new String[]{args[2]});

            } else {
                brushBuilder.sendMessage("expbuild.message.commands.clipboard_not_loaded",new String[]{});
                return;
            }
        }

        if (args[1].equalsIgnoreCase("load")) {

            if (ClipboardParameter.getClipboardsSaveInFile(p.getUniqueId()).contains(args[2])) {
                clipboardParameter.loadSinceFolder(args[2]);

                brushBuilder.setBrush(new ClipboardsBrush())
                        .setEnable(true)
                        .sendMessage("expbuild.message.commands.clipboard_add_and_enable", true, new String[]{args[2]});

            } else {
                brushBuilder.sendMessage("expbuild.message.commands.clipboard_folder_does_not_exist",new String[]{args[2]});
            }
        }

        if (args[1].equalsIgnoreCase("delete")) {

            if (ClipboardParameter.getClipboardsSaveInFile(p.getUniqueId()).contains(args[2])) {

                ClipboardParameter.deleteFolder(args[2]);

                brushBuilder.sendMessage("expbuild.message.commands.clipboard_folder_delete",new String[]{args[2]});

            } else {
                brushBuilder.sendMessage("expbuild.message.commands.clipboard_folder_does_not_exist",new String[]{args[2]});
            }
        }
    }
}