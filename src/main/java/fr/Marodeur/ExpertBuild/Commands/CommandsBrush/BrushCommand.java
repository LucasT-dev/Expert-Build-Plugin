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

        brushList.add("bb");

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
                        args[0].equalsIgnoreCase("blendball") |
                        args[0].equalsIgnoreCase("bb") |
                        args[0].equalsIgnoreCase("erode") |
                        args[0].equalsIgnoreCase("degrade") |
                        args[0].equalsIgnoreCase("eraser") |
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
                        args[0].equalsIgnoreCase("sphere") |
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
                        args[0].equalsIgnoreCase("sphere") |
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
        if (args.length == 2 && (args[0].equalsIgnoreCase("eb") | args[0].equalsIgnoreCase("erodeBlend"))) {
            List<String> l = new ArrayList<>();
            StringUtil.copyPartialMatches(args[1], this.erodeBlendArgs, l);
            return l;
        }

        if (args.length == 3 && (args[0].equalsIgnoreCase("eb") | args[0].equalsIgnoreCase("erodeBlend"))) {
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
            brushBuilder.sendMessage(message.getBrushEnable("/fw <brush> [optional : material/biome] [optional : radius]"));
            return false;
        }

        switch (args[0]) {

            case "drain" -> brushBuilder.setBrushType(BrushEnum.DRAIN)
                    .setRadius(getRayon(p, args, 1))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Drain"));

            case "sphere" -> brushBuilder.setBrushType(BrushEnum.SPHERE)
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .setRadius(getRayon(p, args, 2))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Sphere"));

            case "overlay" -> brushBuilder.setBrushType(BrushEnum.OVERLAY)
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .setRadius(getRayon(p, args, 2))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Overlay"));

            case "none" -> brushBuilder.setBrushType(BrushEnum.NONE)
                    .setEnable(false)
                    .sendMessage(message.getBrushDisable());

            case "update" -> brushBuilder.setBrushType(BrushEnum.UPDATECHUNK)
                    .setRadius(getRayon(p, args, 1))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("UpdateChunk"));

            case "rot2Dcube" -> brushBuilder.setBrushType(BrushEnum.ROT2DCUBE)
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .setRadius(getRayon(p, args, 2))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Rot2Dcube"));

            case "line" -> brushBuilder.setBrushType(BrushEnum.LINE)
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Line"));

            case "spike" -> brushBuilder.setBrushType(BrushEnum.SPIKE)
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .setRadius(getRayon(p, args, 2))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Spike"));

            case "biome" -> brushBuilder.setBrushType(BrushEnum.BIOME)
                    .setBiome(getBiome(brushBuilder, args, 1))
                    .setRadius(getRayon(p, args, 2))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Biome"));

            case "clipboard" -> clipboardCommand(p, args);

            case "cube" -> brushBuilder.setBrushType(BrushEnum.CUBE)
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .setRadius(getRayon(p, args, 2))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Cube"));

            case "degrade" -> brushBuilder.setBrushType(BrushEnum.DEGRADE)
                    .setRadius(getRayon(p, args, 1))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Degrade"));

            case "eraser" -> brushBuilder.setBrushType(BrushEnum.ERASER)
                    .setRadius(getRayon(p, args, 1))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Eraser"));

            /*case "find" -> {

                System.out.println("1 - FIND ");

                BukkitPlayer actor = BukkitAdapter.adapt(p);
                LocalSession session = actor.getSession();
                EditSession editSession = session.createEditSession(actor);
                Region r = actor.getSession().getSelection();

                System.out.println("2 - FIND ");

                Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

                            r.spliterator().forEachRemaining(blockVector3 -> {

                                Material mat = Material.matchMaterial(editSession.getBlock(blockVector3).getBlockType().toString());

                                if (mat.toString().equalsIgnoreCase(args[1])) {
                                    System.out.println("FIND = " + blockVector3.toString());
                                }
                            });
                        });
            }*/

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
                    .setErosionFaces(6)
                    .setErosionRecursion(0)
                    .setFillFaces(1)
                    .setFillRecursion(1)
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Lift"));

            case "melt" -> brushBuilder.setBrushType(BrushEnum.MELT)
                    .setErosionFaces(2)
                    .setErosionRecursion(1)
                    .setFillFaces(5)
                    .setFillRecursion(1)
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Melt"));

            case "fill" -> brushBuilder.setBrushType(BrushEnum.FILL)
                    .setErosionFaces(5)
                    .setErosionRecursion(1)
                    .setFillFaces(2)
                    .setFillRecursion(1)
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Fill"));

            case "smooth" -> brushBuilder.setBrushType(BrushEnum.SMOOTH)
                    .setErosionFaces(3)
                    .setErosionRecursion(1)
                    .setFillFaces(3)
                    .setFillRecursion(1)
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Smooth"));

            case "floatclean" -> brushBuilder.setBrushType(BrushEnum.FLOATCLEAN)
                    .setErosionFaces(6)
                    .setErosionRecursion(1)
                    .setFillFaces(6)
                    .setFillRecursion(1)
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("FloatClean"));

            case "custom" -> brushBuilder.setBrushType(BrushEnum.CUSTOM)
                    .setErosionFaces(getInteger(p, args[1]))
                    .setErosionRecursion(getInteger(p, args[2]))
                    .setFillFaces(getInteger(p, args[3]))
                    .setFillRecursion(getInteger(p, args[4]))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Custom"));

            case "eb", "erodeBlend" -> {

                if (args.length == 1) {
                    brushBuilder.sendMessage(message.getUse(" /fw eb <brush> [optional : radius]"));
                    break;
                }

                switch (args[1]) {

                    case "lift" -> brushBuilder.setBrushType(BrushEnum.ERODEBLEND)
                            .setRadius(getRayon(p, args, 2))
                            .setErosionFaces(6)
                            .setErosionRecursion(0)
                            .setFillFaces(1)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(message.getBrushEnable("Lift"));

                    case "melt" -> brushBuilder.setBrushType(BrushEnum.ERODEBLEND)
                            .setRadius(getRayon(p, args, 2))
                            .setErosionFaces(2)
                            .setErosionRecursion(1)
                            .setFillFaces(5)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(message.getBrushEnable("Melt"));

                    case "fill" -> brushBuilder.setBrushType(BrushEnum.ERODEBLEND)
                            .setRadius(getRayon(p, args, 2))
                            .setErosionFaces(5)
                            .setErosionRecursion(1)
                            .setFillFaces(2)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(message.getBrushEnable("Fill"));

                    case "smooth" -> brushBuilder.setBrushType(BrushEnum.ERODEBLEND)
                            .setRadius(getRayon(p, args, 2))
                            .setErosionFaces(3)
                            .setErosionRecursion(1)
                            .setFillFaces(3)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(message.getBrushEnable("Smooth"));

                    case "floatclean" -> brushBuilder.setBrushType(BrushEnum.ERODEBLEND)
                            .setRadius(getRayon(p, args, 2))
                            .setErosionFaces(6)
                            .setErosionRecursion(1)
                            .setFillFaces(6)
                            .setFillRecursion(1)
                            .setEnable(true)
                            .sendMessage(message.getBrushEnable("FloatClean"));
                }
            }

            case "blendball", "bb" -> brushBuilder.setBrushType(BrushEnum.BLENDBALL)
                    .setRadius(getRayon(p, args, 1))
                    .setEnable(true)
                    .sendMessage(message.getBrushEnable("Blendball"));


            case "register" -> registerCommand(p, args);

            case "material" -> brushBuilder
                    .setPattern(getPattern(brushBuilder, args, 1))
                    .sendMessage(message.getMaterialSet());

            case "radius" -> brushBuilder
                    .setRadius(getRayon(p, args, 1))
                    .sendMessage(message.getRadiusSet());

            default -> {

                try {
                    int i = Integer.parseInt(args[0]);
                    brushBuilder.setRadius(i).sendMessage(message.getRadiusSet());
                } catch (NumberFormatException ignored) {

                    brushBuilder.sendMessage(message.getUse(" /flower <brush> [optional : material/biome] [optional : radius]"));
                }
            }
        }
        return false;
    }

    private static int getRayon(Player p, String @NotNull [] s, int index) {

        try {
            return Integer.parseInt(s[index]);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException err) {

            return BrushBuilder.getBrushBuilderPlayer(p).getRadius();
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
            Pattern pattern = new UtilsFAWE(Bukkit.getPlayer(brushBuilder.getUUID())).getPattern(s[index]);
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
                    .setClipboards(a);

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

                brushBuilder.addClipboards(list);
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
                        .sendMessage(message.getBrushDisable());

            } else {
                brushBuilder.setEnable(true)
                        .setBrushType(BrushEnum.CLIPBOARD)
                        .sendMessage(message.getBrushEnable("Clipboard"));
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