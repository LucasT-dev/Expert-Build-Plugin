package fr.Marodeur.ExpertBuild.Commands.CommandsBrush;

import com.fastasyncworldedit.core.command.SuggestInputParseException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.command.util.SuggestionHelper;
import com.sk89q.worldedit.extension.factory.PatternFactory;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;
import com.sk89q.worldedit.world.biome.BiomeType;
import com.sk89q.worldedit.world.block.BlockType;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BlockVec4;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.Configuration;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BrushCommand implements CommandExecutor, TabCompleter {

    private static final Configuration conf = Main.getInstance().getConfig();
    private static final MessageBuilder message = Main.getInstance().getMessageConfig();

    private final List<String> customBrush = Arrays
            .asList("<erosion faces> <erosion recursion> <fill faces> <fill recursion>");

    private final List<String> erodeBlendArgs = Arrays
            .asList("lift", "melt", "fill", "smooth", "floatclean");

    private final List<String> multiClipboardEnable = Arrays
            .asList("add", "clear", "enable");

    private final List<String> multiClipboardDisable = Arrays
            .asList("add", "clear", "disable");

    private final List<String> Ints = List.of("<radius>");

    public List<String> getBiomeList(final String @NotNull [] args, final boolean space, int indexArg) {
        return SuggestionHelper.getNamespacedRegistrySuggestions(BiomeType.REGISTRY, args[indexArg])
                .map(value -> value.toLowerCase(Locale.ENGLISH).replace("minecraft:", ""))
                .filter(value -> value.startsWith(args[indexArg].toLowerCase(Locale.ENGLISH)))
                .collect(Collectors.toList());
    }

    public List<String> getMaterialList(final String @NotNull [] args, final boolean space, int indexArg) {
        return SuggestionHelper.getNamespacedRegistrySuggestions(BlockType.REGISTRY, args[indexArg])
                .map(value -> value.toLowerCase(Locale.ENGLISH).replace("minecraft:", ""))
                //.filter(value -> value.startsWith(args[indexArg].toLowerCase(Locale.ENGLISH)))
                .collect(Collectors.toList());
    }

    public List<String> getPatternFactoryList(final String @NotNull [] args) {
        return new PatternFactory(WorldEdit.getInstance()).getSuggestions(args[1]);
    }

    private static @NotNull List<String> getBrushList() {

        List<String> brushList = Stream.of(BrushEnum.values())
                .filter(brushEnum -> brushEnum.getBclass() != null)
                .map(BrushEnum::getBrush)
                .collect(Collectors.toList());

        brushList.add("melt");
        brushList.add("fill");
        brushList.add("smooth");
        brushList.add("floatclean");
        brushList.add("custom");
        brushList.add("eb");

        brushList.add("register");
        brushList.add("material");

        //brushList.remove("erode");

        return brushList;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, @NotNull String[] args) {

        BrushBuilder builder = null;

        if (s instanceof Player p) {
            builder = BrushBuilder.getBrushBuilderPlayer(p);
        }

        if (args.length <= 1) {
            List<String> l = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], getBrushList(), l);
            return l;
        }

        //cmd /fw biome <biome> <int>
        if (args.length == 2 && args[0].equalsIgnoreCase("biome")) {
            List<String> l2 = new ArrayList<>();
            StringUtil.copyPartialMatches(args[1], this.getBiomeList(args, true, 1), l2);
            return l2;
        }
        if (args.length == 3 && getBiomeList(args, true, 1).contains(args[1])) {
            List<String> l3 = new ArrayList<>();
            StringUtil.copyPartialMatches(args[2], this.Ints, l3);
            return l3;
        }

        //cmd type /fw <brush> <integer>
        if (args.length == 2 &&
                (args[0].equalsIgnoreCase("update_chunk") |
                        args[0].equalsIgnoreCase("drain") |
                        args[0].equalsIgnoreCase("erode") |
                        args[0].equalsIgnoreCase("degrade") |
                        args[0].equalsIgnoreCase("smooth"))) {
            List<String> l2 = new ArrayList<>();
            StringUtil.copyPartialMatches(args[1], this.Ints, l2);
            return l2;
        }

        //cmd type /fw <brush> <Material>
        if (args.length == 2 &&
                (args[0].equalsIgnoreCase("material") |
                        args[0].equalsIgnoreCase("overlay") |
                        args[0].equalsIgnoreCase("spike") |
                        args[0].equalsIgnoreCase("cube") |
                        args[0].equalsIgnoreCase("line") |
                        args[0].equalsIgnoreCase("rot2Dcube"))) {
            List<String> l2 = new ArrayList<>();
            StringUtil.copyPartialMatches(args[1], this.getPatternFactoryList(args), l2);
            return l2;
        }

        //cmd type /fw <brush> <Material> <INT>
        if (args.length == 3 &&
                (args[0].equalsIgnoreCase("material") |
                        args[0].equalsIgnoreCase("overlay") |
                        args[0].equalsIgnoreCase("spike") |
                        args[0].equalsIgnoreCase("cube") |
                        args[0].equalsIgnoreCase("rot2Dcube"))) {
            List<String> l2 = new ArrayList<>();
            StringUtil.copyPartialMatches(args[2], this.Ints, l2);
            return l2;
        }

        //fw clipboard <add/clear/enable/disable>
        if (args.length == 2 && args[0].equalsIgnoreCase("clipboard")) {
            if (builder.getEnable().equals(false)) {
                List<String> l = new ArrayList<>();
                StringUtil.copyPartialMatches(args[1], this.multiClipboardEnable, l);
                return l;
            }
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("clipboard")) {
            List<String> l = new ArrayList<>();
            StringUtil.copyPartialMatches(args[1], this.multiClipboardDisable, l);
            return l;
        }

        //Custom erode brush
        if (args[0].equalsIgnoreCase("custom")) {
            List<String> l = new ArrayList<>();
            StringUtil.copyPartialMatches(args[1], this.customBrush, l);
            return l;
        }
        if (args[1].equalsIgnoreCase("custom")) {
            List<String> l = new ArrayList<>();
            StringUtil.copyPartialMatches(args[2], this.customBrush, l);
            return l;
        }

        //fw eb
        if (args.length == 2 && args[0].equalsIgnoreCase("eb")) {
            if (builder.getEnable().equals(false)) {
                List<String> l = new ArrayList<>();
                StringUtil.copyPartialMatches(args[1], this.erodeBlendArgs, l);
                return l;
            }
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("eb")) {
            List<String> l = new ArrayList<>();
            StringUtil.copyPartialMatches(args[2], this.Ints, l);
            return l;
        }

        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String msg, @NotNull String[] args) {

        if (! (s instanceof Player p)) {
            s.sendMessage(Main.prefix + message.getConsoleNotExecuteCmd());
            return false;
        }

        if (!p.isOp()) {
            p.sendMessage(Main.prefix + message.getDontPerm());
            return false;
        }

        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

        if (args.length < 1) {
            brushBuilder.sendMessage(message.getBrushEnable("/fw <brush> [material] [radius]"));
            return false;
        }

        switch (args[0]) {

            case "drain" -> brushBuilder.setBrushType(BrushEnum.DRAIN)
                    .setRayon(getRayon(p, args, 1))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Drain"))
                    .Build(brushBuilder);

            case "overlay" -> brushBuilder.setBrushType(BrushEnum.OVERLAY)
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .setRayon(getRayon(p, args, 2))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Overlay"))
                    .Build(brushBuilder);

            case "none" -> brushBuilder.setBrushType(BrushEnum.NONE)
                    .setEnable(false)
                    .sendMessage(message.getBrushDisable())
                    .Build(brushBuilder);

            case "update" -> brushBuilder.setBrushType(BrushEnum.UPDATECHUNK)
                    .setRayon(getRayon(p, args, 1))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("UpdateChunk"))
                    .Build(brushBuilder);

            case "rot2Dcube" -> brushBuilder.setBrushType(BrushEnum.ROT2DCUBE)
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .setRayon(getRayon(p, args, 2))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Rot2Dcube"))
                    .Build(brushBuilder);

            case "line" -> brushBuilder.setBrushType(BrushEnum.LINE)
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Line"))
                    .Build(brushBuilder);

            case "spike" -> brushBuilder.setBrushType(BrushEnum.SPIKE)
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .setRayon(getRayon(p, args, 2))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Spike"))
                    .Build(brushBuilder);

            case "biome" -> brushBuilder.setBrushType(BrushEnum.BIOME)
                    .setBiome(getBiome(brushBuilder, args, 1))
                    .setRayon(getRayon(p, args, 2))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Biome"))
                    .Build(brushBuilder);

            case "clipboard" -> clipboardCommand(p, args);

            case "cube" -> brushBuilder.setBrushType(BrushEnum.CUBE)
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .setRayon(getRayon(p, args, 2))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Cube"))
                    .Build(brushBuilder);

            case "degrade" -> brushBuilder.setBrushType(BrushEnum.DEGRADE)
                    .setRayon(getRayon(p, args, 1))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Degrade"))
                    .Build(brushBuilder);

            /*NONE(new ErosionPreset(0, 1, 0, 1)),
    -MELT(new ErosionPreset(2, 1, 5, 1)),
    -FILL(new ErosionPreset(5, 1, 2, 1)),
    SMOOTH(new ErosionPreset(3, 1, 3, 1)),
    -LIFT(new ErosionPreset(6, 0, 1, 1)),
    FLOATCLEAN(new ErosionPreset(6, 1, 6, 1));

    0 > Erosion face
    1 > Erosion erosionRecursion
    2 > fill Faces
    3 > fill Recursion

    */
            case "lift" -> brushBuilder.setBrushType(BrushEnum.LIFT)
                    .setErodeBlend(false)
                    .setErosionFaces(6)
                    .setErosionRecursion(0)
                    .setFillFaces(1)
                    .setFillRecursion(1)
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Lift"))
                    .Build(brushBuilder);

            case "melt" -> brushBuilder.setBrushType(BrushEnum.MELT)
                    .setErodeBlend(false)
                    .setErosionFaces(2)
                    .setErosionRecursion(1)
                    .setFillFaces(5)
                    .setFillRecursion(1)
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Melt"))
                    .Build(brushBuilder);

            case "fill" -> brushBuilder.setBrushType(BrushEnum.FILL)
                    .setErodeBlend(false)
                    .setErosionFaces(5)
                    .setErosionRecursion(1)
                    .setFillFaces(2)
                    .setFillRecursion(1)
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Fill"))
                    .Build(brushBuilder);

            case "smooth" -> brushBuilder.setBrushType(BrushEnum.SMOOTH)
                    .setErodeBlend(false)
                    .setErosionFaces(3)
                    .setErosionRecursion(1)
                    .setFillFaces(3)
                    .setFillRecursion(1)
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Smooth"))
                    .Build(brushBuilder);

            case "floatclean" -> brushBuilder.setBrushType(BrushEnum.FLOATCLEAN)
                    .setErodeBlend(false)
                    .setErosionFaces(6)
                    .setErosionRecursion(1)
                    .setFillFaces(6)
                    .setFillRecursion(1)
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("FloatClean"))
                    .Build(brushBuilder);

            case "custom" -> brushBuilder.setBrushType(BrushEnum.CUSTOM)
                    .setErodeBlend(false)
                    .setErosionFaces(getInteger(p, args[1]))
                    .setErosionRecursion(getInteger(p, args[2]))
                    .setFillFaces(getInteger(p, args[3]))
                    .setFillRecursion(getInteger(p, args[4]))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Custom"))
                    .Build(brushBuilder);

            case "eb" -> {

                switch (args[1]) {

                    case "lift" -> brushBuilder.setBrushType(BrushEnum.LIFT)
                            .setRayon(getRayon(p, args, 2))
                            .setErodeBlend(true)
                            .setErosionFaces(6)
                            .setErosionRecursion(0)
                            .setFillFaces(1)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(message.getBrushEnable("Lift"))
                            .Build(brushBuilder);

                    case "melt" -> brushBuilder.setBrushType(BrushEnum.MELT)
                            .setRayon(getRayon(p, args, 2))
                            .setErodeBlend(true)
                            .setErosionFaces(2)
                            .setErosionRecursion(1)
                            .setFillFaces(5)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(message.getBrushEnable("Melt"))
                            .Build(brushBuilder);

                    case "fill" -> brushBuilder.setBrushType(BrushEnum.FILL)
                            .setRayon(getRayon(p, args, 2))
                            .setErodeBlend(true)
                            .setErosionFaces(5)
                            .setErosionRecursion(1)
                            .setFillFaces(2)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(message.getBrushEnable("Fill"))
                            .Build(brushBuilder);

                    case "smooth" -> brushBuilder.setBrushType(BrushEnum.SMOOTH)
                            .setRayon(getRayon(p, args, 2))
                            .setErodeBlend(true)
                            .setErosionFaces(3)
                            .setErosionRecursion(1)
                            .setFillFaces(3)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(message.getBrushEnable("Smooth"))
                            .Build(brushBuilder);

                    case "floatclean" -> brushBuilder.setBrushType(BrushEnum.FLOATCLEAN)
                            .setRayon(getRayon(p, args, 2))
                            .setErodeBlend(true)
                            .setErosionFaces(6)
                            .setErosionRecursion(1)
                            .setFillFaces(6)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(message.getBrushEnable("FloatClean"))
                            .Build(brushBuilder);

                    case "custom" -> brushBuilder.setBrushType(BrushEnum.CUSTOM)
                            .setErodeBlend(true)
                            .setErosionFaces(getInteger(p, args[1]))
                            .setErosionRecursion(getInteger(p, args[2]))
                            .setFillFaces(getInteger(p, args[3]))
                            .setFillRecursion(getInteger(p, args[4]))
                            .setEnable(true)
                            .sendMessage(message.getBrushEnable("Custom"))
                            .Build(brushBuilder);

                }
            }

            case "blendball" -> brushBuilder.setBrushType(BrushEnum.BLENDBALL)
                    .setRayon(getRayon(p, args, 1))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("BlendBall"))
                    .Build(brushBuilder);


            case "register" -> registerCommand(p, args);

            case "material" -> brushBuilder
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .sendMessage(message.getMaterialSet())
                    .Build(brushBuilder);

            case "radius" -> brushBuilder
                    .setRayon(getRayon(p, args, 1))
                    .sendMessage(message.getRadiusSet())
                    .Build(brushBuilder);

            default -> brushBuilder.sendMessage(message.getUse("Use /flower <brush>"));
        }
        return false;
    }

    private static int getRayon(Player p, String @NotNull [] s, int index) {

        try {
            return Integer.parseInt(s[index]);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException err) {

            return BrushBuilder.getBrushBuilderPlayer(p).getRayon();
        }
    }

    private static Biome getBiome(BrushBuilder brushBuilder, String @NotNull [] s, int index) {

        try {

            if (Stream.of(Biome.values())
                    .map(Biome::toString).toList().toString().contains(s[index])) {
                return Biome.valueOf(s[index]);

            } else {

                brushBuilder.sendMessage(message.getInvalidBiomeSet(brushBuilder.getBiome().name()));
                return brushBuilder.getBiome();
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            return brushBuilder.getBiome();
        }
    }

    private static Material getMaterial(BrushBuilder brushBuilder, String @NotNull [] s, int index) {

        try {

            if (Stream.of(Material.values())
                    .map(Material::toString).toList().contains(s[index])) {
                return Material.valueOf(s[index]);

            } else {
                brushBuilder.sendMessage(message.getInvalidMaterialSet(brushBuilder.getMaterial().toString()));
                return brushBuilder.getMaterial();
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            return brushBuilder.getMaterial();
        }
    }

    private static Pattern getPattern(BrushBuilder brushBuilder, String[] s, int index) {

        try {
            Pattern pattern = new UtilsFAWE(brushBuilder.getPlayer()).getPattern(s[index]);
            return pattern;

        } catch (NullPointerException | SuggestInputParseException | ArrayIndexOutOfBoundsException err) {
            brushBuilder.sendMessage(message.getInvalidMaterialSet(brushBuilder.getPattern().toString()));
            return brushBuilder.getPattern();
        }
    }

    private static int getInteger(Player p, String s) {

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException err) {
            p.sendMessage(message.getInvalidNumberIntegerUpper0());
            return 0;
        }
    }

    private static void clipboardCommand(Player p, String @NotNull [] args) {

        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p);

        if (args[1].equalsIgnoreCase("clear")) {

            ArrayList<List<BlockVec4>> a = new ArrayList<>();

            brushBuilder
                    .setClipboards(a)
                    .Build(brushBuilder);

            p.sendMessage(Main.prefix + "all selection brush cleared");
            return;
        }

        if (args[1].equalsIgnoreCase("add")) {

            if (new UtilsFAWE(p).isValidSelection(RegionSelectorType.CUBOID)) {

                Clipboard clip = new UtilsFAWE(p).CopySelection();
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

                brushBuilder.addClipboards(list).Build(brushBuilder);
            }
        }

        if (args[1].equalsIgnoreCase("enable") || args[1].equalsIgnoreCase("disable")) {

            if (brushBuilder.getClipboards().size() == 0) {
                p.sendMessage(Main.prefix + "any selection save");
                return;
            }

            if (brushBuilder.getEnable().equals(true)) {

                brushBuilder.setEnable(false)
                        .setBrushType(BrushEnum.NONE)
                        .sendMessage(message.getBrushDisable())
                        .Build(brushBuilder);

            } else {
                brushBuilder.setEnable(true)
                        .setBrushType(BrushEnum.CLIPBOARD)
                        .sendMessage(message.getBrushEnable("Clipboard"))
                        .Build(brushBuilder);
            }
        }
    }

    private static void registerCommand(Player p, String @NotNull [] args) {

        if (args.length <= 1) {
            BrushBuilder.registerPlayer(p);
        }

        if (args.length == 2) {

            Bukkit.getOnlinePlayers().stream()
                    .filter(player -> player.getName().equals(args[1]))
                    .forEach(player ->
                        p.sendMessage(Main.prefix + BrushBuilder.getBrushBuilderPlayer(player).toString()));

        }
    }
}