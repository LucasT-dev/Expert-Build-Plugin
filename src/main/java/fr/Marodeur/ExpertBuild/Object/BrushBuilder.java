package fr.Marodeur.ExpertBuild.Object;

import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockTypes;

import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.logging.Logger;

public class BrushBuilder {

    private static final Logger log = Logger.getLogger("Expert-Build");
    private static final MessageBuilder message = Main.getInstance().getMessageConfig();
    private static final Configuration conf = Main.getInstance().getConfig();

    private final UUID uuid;
    private BrushEnum brushEnum;
    private Boolean isEnable;
    private Boolean SelMode;
    private Material material;
    private List<BaseBlock> flowerMaterial;
    private List<Integer> flowerMaterialTaux;
    private Biome biome;
    private int airBrush;
    private Integer radius;
    private int tickRT; // tick repeater
    private Region region; //autoFlip
    private ArrayList<List<BlockVec4>> clipboards; //Multi Clipboard
    private BlockFace blockFace;
    private List<BlockVec4> bv4; //using autoflip
    private Pattern pattern;

    private int erosionFaces;
    private int erosionRecursion;
    private int fillFaces;
    private int fillRecursion;

    /**
     * Create objet BrushBuilder
     */
    public BrushBuilder(UUID uuid, BrushEnum brushEnum, Boolean isEnable, Boolean SelMode, Material material,
                        List<BaseBlock> flowerMaterial, List<Integer> flowerMaterialTaux, Biome biome, int airBrush,
                        Integer rayon, int tickRT, Region region, ArrayList<List<BlockVec4>> clipboards,
                        BlockFace blockFace, List<BlockVec4> bv4, Pattern pattern,
                        int erosionFaces, int erosionRecursion, int fillFaces, int fillRecursion) {

        this.uuid = uuid;
        this.brushEnum = brushEnum;
        this.isEnable = isEnable;
        this.SelMode = SelMode;
        this.material = material;
        this.flowerMaterial = flowerMaterial;
        this.flowerMaterialTaux = flowerMaterialTaux;
        this.biome = biome;
        this.airBrush = airBrush;
        this.radius = rayon;
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
    }

    public UUID getUUID() {
        return uuid;
    }

    public World getWorld() {
        return Bukkit.getWorld(this.uuid);
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

    public List<BaseBlock> getFlowerMaterial() {
        return flowerMaterial;
    }

    public List<Integer> getFlowerMaterialTaux() {
        return flowerMaterialTaux;
    }

    public Biome getBiome() {
        return biome;
    }

    public int getAirBrush() {
        return airBrush;
    }

    public Integer getRadius() {
        return radius;
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

    public BrushBuilder setBrushType(BrushEnum brushEnum) {
        this.brushEnum = brushEnum;
        return this;
    }

    public BrushBuilder setEnable(Boolean enable) {
        this.isEnable = enable;
        return this;
    }

    public BrushBuilder setSelMode(Boolean selMode) {
        this.SelMode = selMode;
        return this;
    }

    public BrushBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public BrushBuilder setFlowerMaterial(List<BaseBlock> flowerMaterial) {
        this.flowerMaterial = flowerMaterial;
        return this;
    }

    public BrushBuilder addFlowerMaterial(BaseBlock flowerMaterial, int index) {
        this.flowerMaterial.set(index, flowerMaterial);
        return this;
    }

    public BrushBuilder setFlowerMaterialTaux(List<Integer> flowerMaterialTaux) {
        this.flowerMaterialTaux = flowerMaterialTaux;
        return this;
    }

    public BrushBuilder addFlowerMaterialTaux(Integer flowerMaterialTaux, int index) {
        this.flowerMaterialTaux.set(index, flowerMaterialTaux);
        return this;
    }

    public BrushBuilder addFlowerMaterialTaux(int index, boolean isShiftClick, boolean isRightClick) {

        int maxRadius = 100;
        int minRadius = 1;
        int n =  this.flowerMaterialTaux.get(index);
        int num;

        if (isShiftClick) {
            if (isRightClick) {
                num = -10;
            } else {
                num = 10;
            }
        } else {
            if (isRightClick) {
                num = -1;
            } else {
                num = 1;
            }
        }

        if (n + num > maxRadius) {
            this.flowerMaterialTaux.set(index, maxRadius);
        } else if (n + num < minRadius) {
            this.flowerMaterialTaux.set(index, minRadius);
        } else {
            this.flowerMaterialTaux.set(index, n + num);
        }
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

    public BrushBuilder setAirBrush(boolean isShiftClick, boolean isRightClick) {

        int maxRotation = 100;
        int minRotation = 0;
        int n = this.airBrush;
        int num;

        if (isShiftClick) {
            if (isRightClick) {
                num = -10;
            } else {
                num = 10;
            }
        } else {
            if (isRightClick) {
                num = -1;
            } else {
                num = 1;
            }
        }

        if (n + num > maxRotation) {
            this.airBrush = maxRotation;
        } else if (n + num < minRotation) {
            this.airBrush = minRotation;
        } else {
            this.airBrush = n + num;
        }
        return this;
    }

    public BrushBuilder setRadius(Integer radius) {
        this.radius = radius;
        return this;
    }

    public BrushBuilder setRadius(boolean isShiftClick, boolean isRightClick) {

        int maxRadius = conf.getMaxRayonBrush();
        int minRadius = 0;
        int n = this.radius;
        int num;

        if (isShiftClick) {
            if (isRightClick) {
                num = -10;
            } else {
                num = 10;
            }
        } else {
            if (isRightClick) {
                num = -1;
            } else {
                num = 1;
            }
        }

        if (n + num > maxRadius) {
            this.radius = maxRadius;
        } else if (n + num < minRadius) {
            this.radius = minRadius;
        } else {
            this.radius = n + num;
        }
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


    @Override
    public String toString() {
        return "BrushBuilder{" +
                "p=" + uuid +
                ", brushEnum=" + brushEnum +
                ", isEnable=" + isEnable +
                ", SelMode=" + SelMode +
                ", material=" + material +
                ", flowerMaterial=" + flowerMaterial +
                ", flowerMaterialTaux=" + flowerMaterialTaux +
                ", biome=" + biome +
                ", airBrush=" + airBrush +
                ", rayon=" + radius +
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
                '}';
    }

    //OPERATION

    /**
     *
     * Find Brush enable of player and execute function on honeycomb
     *
     * @param brushBuilder
     * @param obj
     */
    public void executeHoneyBrush(BrushBuilder brushBuilder, Object obj) {

        Main.getInstance().getRegisteredBrush()
                .values().stream()
                .filter(brushOperation -> brushBuilder.getBrushType().getBclass().getName().equalsIgnoreCase(brushOperation.getClass().getName().replace("class ", "")))
                .forEach(brushOperation -> brushOperation.ExecuteBrushOnHoney(Bukkit.getPlayer(this.uuid), obj));
    }

    /**
     *
     * Find Brush enable of player and execute function on first item builder
     *
     * @param brushBuilder
     * @param obj
     */
    public void executeArrowBrush(BrushBuilder brushBuilder, Object obj, Object loc) {

        Main.getInstance().getRegisteredBrush()
                .values().stream()
                .filter(brushOperation -> brushBuilder.getBrushType().getBclass().getName().equalsIgnoreCase(brushOperation.getClass().getName().replace("class ", "")))
                .forEach(brushOperation -> brushOperation.ExecuteBrushOnArrow(Bukkit.getPlayer(this.uuid), obj, loc));
    }

    /**
     *
     * Find Brush enable of player and execute function on second item builder
     *
     * @param brushBuilder
     * @param obj
     */
    public void executeGunPowderBrush(BrushBuilder brushBuilder, Object obj, Object loc) {

        Main.getInstance().getRegisteredBrush()
                .values().stream()
                .filter(brushOperation ->  brushBuilder.getBrushType().getBclass().getName().equalsIgnoreCase(brushOperation.getClass().getName().replace("class ", "")))
                .forEach(brushOperation -> brushOperation.ExecuteBrushOnGunpowder(Bukkit.getPlayer(this.uuid), obj, loc));
    }

    //MESSAGE:

    public final String getMainPrefix() {
        return Main.prefix;
    }

    /*public final String getFawePrefix() {
        return Main.FawePrefix;
    }*/

    public BrushBuilder sendMessage(String msg) {

        Bukkit.getPlayer(this.uuid).sendMessage(getMainPrefix() + msg);

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
            p.sendMessage(Main.prefix + message.getPlayerAlreadyRegistered());
            return getBrushBuilderPlayer(p);
        }

        BaseBlock ib = Objects.requireNonNull(BlockTypes.BARRIER).getDefaultState().toBaseBlock();
        List<BaseBlock> it = new ArrayList<>(Arrays.asList(ib, ib, ib, ib, ib, ib, ib, ib, ib));

        List<Integer> flowerMaterialTaux = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1);


        return Main.registerBrushBuilder(new BrushBuilder(p.getUniqueId(), BrushEnum.NONE, false, true,
                conf.getDefault_material_brush(), it, flowerMaterialTaux, conf.getDefault_biome_brush(),
                conf.getDefault_air_brush(), conf.getDefaultBrushRayon(), 4, null, new ArrayList<>(),
                null, new ArrayList<>(), new UtilsFAWE(p).getPattern(conf.getDefault_pattern_brush()),
                0, 0, 0, 0));
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

}