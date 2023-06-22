package fr.Marodeur.ExpertBuild.Object;

import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.regions.Region;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BrushBuilder {

    private static final Logger log = Logger.getLogger("Expert-Build");
    private static final MessageBuilder message = Main.getInstance().getMessageConfig();

    private final Player p;
    private BrushEnum brushEnum;
    private Boolean isEnable;
    private Boolean SelMode;
    private Material material;
    private String flowerMaterial;
    private Biome biome;
    private int airBrush;
    private Integer rayon;
    private String upperLower; // flower constitued 2 blocks
    private Boolean isWaterlog; // coral is waterlog
    private int years; // age of plant
    private int tickRT; // tick repeater
    private Region region; //autoFlip
    private ArrayList<List<BlockVec4>> clipboards; //Multi Clipboard
    private BlockFace blockFace;
    private List<BlockVec4> bv4; //using for undo goha and autoflip
    private Pattern pattern;

    private int erosionFaces;
    private int erosionRecursion;
    private int fillFaces;
    private int fillRecursion;
    private boolean isErodeBlend;

    /**
     *
     * Create objet BrushBuilder
     *
     */
    public BrushBuilder(Player p, BrushEnum brushEnum, Boolean isEnable, Boolean SelMode, Material material,
                        String flowerMaterial, Biome biome, int airBrush, Integer rayon,
                        String upperLower, Boolean isWaterlog, int years, int tickRT, Region region,
                        ArrayList<List<BlockVec4>> clipboards, BlockFace blockFace, List<BlockVec4> bv4, Pattern pattern,
                        int erosionFaces, int erosionRecursion, int fillFaces, int fillRecursion, boolean isErodeBlend) {

        this.p = p;
        this.brushEnum = brushEnum;
        this.isEnable = isEnable;
        this.SelMode = SelMode;
        this.material = material;
        this.flowerMaterial = flowerMaterial;
        this.biome = biome;
        this.airBrush = airBrush;
        this.rayon = rayon;
        this.upperLower = upperLower;
        this.isWaterlog = isWaterlog;
        this.years = years;
        this.tickRT = tickRT;
        this.region = region;
        this.clipboards = clipboards;
        this.blockFace = blockFace;
        this.bv4 = bv4;
        this.pattern = pattern;

        this.erosionFaces = erosionFaces;
        this.erosionRecursion = erosionRecursion;
        this.fillFaces = fillFaces;
        this.fillRecursion = fillRecursion;
        this.isErodeBlend = isErodeBlend;
    }
    public Player getPlayer() {
        return p;
    }

    public World getWorld() {
        return this.p.getWorld();
    }

    public BrushEnum getBrushType() {
        return brushEnum;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public Boolean getSelMode() {
        return SelMode;
    }

    public Material getMaterial() {
        return material;
    }

    public String getFlowerMaterial() {
        return flowerMaterial;
    }

    public Biome getBiome() {
        return biome;
    }

    public int getAirBrush() {
        return airBrush;
    }

    public Integer getRayon() {
        return rayon;
    }

    public String getUpperLower() {
        return upperLower;
    }

    public Boolean getWaterlog() {
        return isWaterlog;
    }

    public int getYears() {
        return years;
    }

    public int getTickRT() {
        return tickRT;
    }

    public Region getRegion() {
        return region;
    }

    public ArrayList<List<BlockVec4>> getClipboards() {
        return clipboards;
    }

    public BlockFace getBlockFace() {
        return blockFace;
    }

    public List<BlockVec4> getBlockVec4List() {
        return bv4;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public int getErosionFaces() {
        return erosionFaces;
    }

    public int getErosionRecursion() {
        return erosionRecursion;
    }

    public int getFillFaces() {
        return fillFaces;
    }

    public int getFillRecursion() {
        return fillRecursion;
    }

    public boolean isErodeBlend() {
        return isErodeBlend;
    }

    public BrushBuilder setBrushType(BrushEnum brushEnum) {
        this.brushEnum = brushEnum;
        return this;
    }

    public BrushBuilder setEnable(Boolean enable) {
        this.isEnable = enable;
        return this;
    }

    public BrushBuilder setSelMode(Boolean selMode) {
        SelMode = selMode;
        return this;
    }

    public BrushBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public BrushBuilder setFlowerMaterial(String flowerMaterial) {
        this.flowerMaterial = flowerMaterial;
        return this;
    }

    public BrushBuilder setBiome(Biome biome) {
        this.biome = biome;
        return this;
    }

    public BrushBuilder setAirBrush(int airBrush) {
        this.airBrush = airBrush;
        return this;
    }

    public BrushBuilder setRayon(Integer rayon) {
        this.rayon = rayon;
        return this;
    }

    public void setUpperLower(String upperLower) {
        this.upperLower = upperLower;
    }

    public void setWaterlog(Boolean waterlog) {
        this.isWaterlog = waterlog;
    }

    public BrushBuilder setYears(int years) {
        this.years = years;
        return this;
    }

    public BrushBuilder setTickRT(int tickRT) {
        this.tickRT = tickRT;
        return this;
    }

    public BrushBuilder setRegion(Region region) {
        this.region = region;
        return this;
    }

    public BrushBuilder setClipboards(ArrayList<List<BlockVec4>> clipboards) {
        this.clipboards = clipboards;
        return this;
    }

    public BrushBuilder addClipboards(List<BlockVec4> clipboard) {
        this.clipboards.add(clipboard);
        return this;
    }

    public BrushBuilder setBlockFace(BlockFace blockFace) {
        this.blockFace = blockFace;
        return this;
    }

    public BrushBuilder setBlockVec4(List<BlockVec4> bv4) {
        this.bv4 = bv4;
        return this;
    }

    public BrushBuilder setPattern(Pattern pattern) {
        this.pattern = pattern;
        return this;
    }

    public BrushBuilder setErosionFaces(int erosionFaces) {
        this.erosionFaces = erosionFaces;
        return this;
    }

    public BrushBuilder setErosionRecursion(int erosionRecursion) {
        this.erosionRecursion = erosionRecursion;
        return this;
    }

    public BrushBuilder setFillFaces(int fillFaces) {
        this.fillFaces = fillFaces;
        return this;
    }

    public BrushBuilder setFillRecursion(int fillRecursion) {
        this.fillRecursion = fillRecursion;
        return this;
    }

    public BrushBuilder setErodeBlend(boolean erodeBlend) {
        isErodeBlend = erodeBlend;
        return this;
    }

    @Override
    public String toString() {
        return "BrushBuilder{" +
                "p=" + p +
                ", brushEnum=" + brushEnum +
                ", isEnable=" + isEnable +
                ", SelMode=" + SelMode +
                ", material=" + material +
                ", flowerMaterial='" + flowerMaterial + '\'' +
                ", biome=" + biome +
                ", airBrush=" + airBrush +
                ", rayon=" + rayon +
                ", upperLower='" + upperLower + '\'' +
                ", isWaterlog=" + isWaterlog +
                ", years=" + years +
                ", tickRT=" + tickRT +
                ", region=" + region +
                ", clipboards=" + clipboards +
                ", blockFace=" + blockFace +
                ", bv4=" + bv4 +
                ", pattern=" + pattern.toString() +
                ", erosionFaces=" + erosionFaces +
                ", erosionRecursion=" + erosionRecursion +
                ", fillFaces=" + fillFaces +
                ", fillRecursion=" + fillRecursion +
                ", isErodeBlend=" + isErodeBlend +
                '}';
    }
    //OPERATION

    public void executeHoneyBrush(BrushBuilder brushBuilder, Object obj) {

        Main.getInstance().getRegisteredBrush()
                .values().stream()
                .filter(brushOperation -> brushBuilder.getBrushType().getBclass().getName().equalsIgnoreCase(brushOperation.getClass().getName().replace("class ", "")))
                .forEach(brushOperation -> brushOperation.ExecuteBrushOnHoney(p, obj));
    }

    public void executeArrowBrush(BrushBuilder brushBuilder, Object obj, Object loc) {

        Main.getInstance().getRegisteredBrush()
                .values().stream()
                .filter(brushOperation -> brushBuilder.getBrushType().getBclass().getName().equalsIgnoreCase(brushOperation.getClass().getName().replace("class ", "")))
                .forEach(brushOperation -> brushOperation.ExecuteBrushOnArrow(p, obj, loc));
    }

    public void executeGunPowderBrush(BrushBuilder brushBuilder, Object obj, Object loc) {

        Main.getInstance().getRegisteredBrush()
                .values().stream()
                .filter(brushOperation ->  brushBuilder.getBrushType().getBclass().getName().equalsIgnoreCase(brushOperation.getClass().getName().replace("class ", "")))
                .forEach(brushOperation -> brushOperation.ExecuteBrushOnGunpowder(p, obj, loc));
    }

    //MESSAGE:

    public final String getMainPrefix() {
        return Main.prefix;
    }

    /*public final String getFawePrefix() {
        return Main.FawePrefix;
    }*/

    public BrushBuilder sendMessage(String msg) {

        this.p.sendMessage(getMainPrefix() + msg);
        return this;

    }
    /**
     *
     * Register BrushBuilder of player in HashMap
     *
     * @param p Player
     *
     */
    public static BrushBuilder registerPlayer(@NotNull Player p) {

        if (Main.containsBrushBuilder(p)) {
            p.sendMessage(message.getPlayerAlreadyRegistered());
            return getBrushBuilderPlayer(p);
        }

        Configuration conf = Main.getInstance().getConfig();
        return Main.registerBrushBuilder(new BrushBuilder(p, BrushEnum.NONE, false, true, conf.getDefault_material_brush(), "",
                conf.getDefault_biome_brush(), conf.getDefault_air_brush(), conf.getDefaultBrushRayon(),
                "lower", false, 1, 4,
                null, new ArrayList<>(), null, new ArrayList<>(), new UtilsFAWE(p).getPattern(conf.getDefault_pattern_brush()),
                0, 0, 0, 0, false));
    }

    /**
     *
     * Get objet BrushBuild associated at the player
     *
     * @param p Player
     *
     */
    public static @Nullable BrushBuilder getBrushBuilderPlayer(@NotNull Player p) {

        if (Main.containsBrushBuilder(p)) {
            return Main.getBrushBuilder(p);

        } else {
            log.severe(message.getErrorBrushbuilder(p.getName()));
            return null;
        }
    }

    /**
     *
     * save objet BrushBuilder in hashMap
     *
     * @param brushBuilder modified BrushBuilder
     *
     */
    public void Build(BrushBuilder brushBuilder) {
        Main.updateBrushBuilder(brushBuilder);
    }
}


