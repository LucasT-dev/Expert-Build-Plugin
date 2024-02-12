package fr.marodeur.expertbuild.object;

import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockTypes;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.enums.BrushEnum;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class BrushBuilder {

    private static final Logger log = Logger.getLogger("Expert-Build");
    private static final MessageBuilder message = Main.getInstance().getMessageConfig();
    private static final Configuration conf = Main.getInstance().getConfig();

    private final UUID uuid;
    private BrushEnum brushEnum;
    private Boolean isEnable;
    private Boolean selMode;
    private Boolean flyMode;
    private Material material;
    private List<BaseBlock> flowerMaterial;
    private List<Integer> flowerMaterialRate;
    private Biome biome;
    private int airBrush;
    private Integer radius;
    private int tickRT; // tick repeater
    private Region region; //autoFlip
    private BlockFace blockFace;
    private List<BlockVec4> bv4; //using autoflip
    private Pattern pattern;

    private TerraParameter terraParameter;
    private final ClipboardParameter clipboardParameter;
    private final ClipboardBrush clipboardBrush;

    /**
     * Create objet BrushBuilder
     */
    public BrushBuilder(UUID uuid, BrushEnum brushEnum, Boolean isEnable, Boolean selMode, Boolean flyMode, Material material,
                        List<BaseBlock> flowerMaterial, List<Integer> flowerMaterialTaux, Biome biome, int airBrush,
                        Integer rayon, int tickRT, Region region,
                        BlockFace blockFace, List<BlockVec4> bv4, Pattern pattern,
                        TerraParameter terraParameter, ClipboardParameter clipboardParameter, ClipboardBrush clipboardBrush) {

        this.uuid = uuid;
        this.brushEnum = brushEnum;
        this.isEnable = isEnable;
        this.selMode = selMode;
        this.flyMode = flyMode;
        this.material = material;
        this.flowerMaterial = flowerMaterial;
        this.flowerMaterialRate = flowerMaterialTaux;
        this.biome = biome;
        this.airBrush = airBrush;
        this.radius = rayon;
        this.tickRT = tickRT;
        this.region = region;
        this.blockFace = blockFace;
        this.bv4 = bv4;
        this.pattern = pattern;

        this.terraParameter = terraParameter;
        this.clipboardParameter = clipboardParameter;
        this.clipboardBrush = clipboardBrush;

    }

    // GETTER

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
        return selMode;
    }

    public Boolean getFlyMode() {
        return flyMode;
    }

    public Material getMaterial() {
        return material;
    }

    public List<BaseBlock> getFlowerMaterial() {
        return flowerMaterial;
    }

    public List<Integer> getFlowerMaterialRate() {
        return flowerMaterialRate;
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

    public BlockFace getBlockFace() {
        return blockFace;
    }

    public List<BlockVec4> getBlockVec4List() {
        return bv4;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public TerraParameter getTerraParameter() {
        return this.terraParameter;
    }

    public int getErosionFaces() {
        return this.terraParameter.getErosionFaces();
    }

    public int getErosionRecursion() {
        return this.terraParameter.getErosionRecursion();
    }

    public int getFillFaces() {
        return this.terraParameter.getFillFaces();
    }

    public int getFillRecursion() {
        return this.terraParameter.getFillRecursion();
    }

    public ClipboardParameter getClipboardsParameter() {
        return this.clipboardParameter;
    }


    public ClipboardBrush getClipboardBrush() {
        return clipboardBrush;
    }

    // SETTER

    public BrushBuilder setBrushType(BrushEnum brushEnum) {
        this.brushEnum = brushEnum;
        return this;
    }

    public BrushBuilder setEnable(Boolean enable) {
        this.isEnable = enable;
        return this;
    }

    public BrushBuilder setSelMode(Boolean selMode) {
        this.selMode = selMode;
        return this;
    }

    public BrushBuilder setFlyMode(Boolean flyMode) {
        this.flyMode = flyMode;
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

    public BrushBuilder setFlowerMaterialRate(List<Integer> flowerMaterialRate) {
        this.flowerMaterialRate = flowerMaterialRate;
        return this;
    }

    public BrushBuilder addFlowerMaterialRate(Integer flowerMaterialRate, int index) {
        this.flowerMaterialRate.set(index, flowerMaterialRate);
        return this;
    }

    public BrushBuilder addFlowerMaterialRate(int index, boolean isShiftClick, boolean isRightClick) {

        int maxRadius = 100;
        int minRadius = 1;
        int n =  this.flowerMaterialRate.get(index);
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
            this.flowerMaterialRate.set(index, maxRadius);
        } else if (n + num < minRadius) {
            this.flowerMaterialRate.set(index, minRadius);
        } else {
            this.flowerMaterialRate.set(index, n + num);
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

    public void setTerraParameter(TerraParameter terraParameter) {
        this.terraParameter = terraParameter;
    }

    public BrushBuilder setErosionFaces(int erosionFaces) {
        this.terraParameter.setErosionFaces(erosionFaces);
        return this;
    }

    public BrushBuilder setErosionRecursion(int erosionRecursion) {
        this.terraParameter.setErosionRecursion(erosionRecursion);
        return this;
    }

    public BrushBuilder setFillFaces(int fillFaces) {
        this.terraParameter.setFillFaces(fillFaces);
        return this;
    }

    public BrushBuilder setFillRecursion(int fillRecursion) {
        this.terraParameter.setFillRecursion(fillRecursion);
        return this;
    }

    public ClipboardBrush clipboardBrush() {
        return clipboardBrush;
    }

    @Override
    public String toString() {
        return "BrushBuilder{" +
                "uuid=" + uuid +
                ", brushEnum=" + brushEnum +
                ", isEnable=" + isEnable +
                ", selMode=" + selMode +
                ", flyMode=" + flyMode +
                ", material=" + material +
                ", flowerMaterial=" + flowerMaterial +
                ", flowerMaterialRate=" + flowerMaterialRate +
                ", biome=" + biome +
                ", airBrush=" + airBrush +
                ", radius=" + radius +
                ", tickRT=" + tickRT +
                ", region=" + region +
                ", blockFace=" + blockFace +
                ", bv4=" + bv4 +
                ", pattern=" + pattern.toString() +
                ", terraParameter=" + terraParameter.toString() +
                ", clipboardParameter=" + clipboardParameter.toString() +
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
     * @param brushBuilder BrushBuilder
     * @param obj Object
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
     * @param brushBuilder BrushBuilder
     * @param obj Object
     * @param loc Object
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
     * Register BrushBuilder of player in HashMap
     *
     * @param p Player
     * @param sendError Boolean
     * @return this
     */
    public static BrushBuilder registerPlayer(@NotNull Player p, Boolean sendError) {

        if (Main.containsBrushBuilder(p)) {
            p.sendMessage(Main.prefix + message.getPlayerAlreadyRegistered());
            return getBrushBuilderPlayer(p, sendError);
        }

        BaseBlock ib = Objects.requireNonNull(BlockTypes.BARRIER).getDefaultState().toBaseBlock();
        List<BaseBlock> it = new ArrayList<>(Arrays.asList(ib, ib, ib, ib, ib, ib, ib, ib, ib));

        List<Integer> flowerMaterialTaux = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0);


        return Main.registerBrushBuilder(new BrushBuilder(p.getUniqueId(),
                BrushEnum.NONE,
                false,
                true,
                true,
                conf.getDefault_material_brush(),
                it, flowerMaterialTaux,
                conf.getDefault_biome_brush(),
                conf.getDefault_air_brush(),
                conf.getDefaultBrushRayon(),
                4,
                null,
                null,
                new ArrayList<>(),
                new UtilsFAWE(p).getPattern(conf.getDefault_pattern_brush()),
                new TerraParameter(0, 0, 0, 0),
                new ClipboardParameter(new ArrayList<>(), new ArrayList<>(), false),
                new ClipboardBrush(new GlueList<>())));
    }

        /**
         * Using before player de-connection
         * When the player login, he retrieves his BrushBuilder profile
         *
         * @param p Player
         * @param brushBuilder BrushBuilder
         * @return this
         */
    public static @NotNull BrushBuilder registerPlayer(@NotNull Player p, BrushBuilder brushBuilder) {

        Main.removeBrushBuilder(p);

        return Main.registerBrushBuilder(brushBuilder);
    }

    /**
     *
     * Get objet BrushBuild associated at the player
     *
     * @param p Player
     * @param sendError Boolean
     */
    public static @Nullable BrushBuilder getBrushBuilderPlayer(@NotNull Player p, Boolean sendError) {

        if (Main.containsBrushBuilder(p)) {
            return Main.getBrushBuilder(p);

        } else {

            if (sendError) {
                log.severe(message.getErrorBrushbuilder(p.getName()));
                log.severe(message.getNoPermissionNode("exp.register"));
            }
            return null;
        }
    }

    public boolean isEmpty(BrushBuilder bb) {
        return (bb == null);
    }


    private static class TerraParameter {

        private int erosionFaces;
        private int erosionRecursion;
        private int fillFaces;
        private int fillRecursion;

        public TerraParameter(int erosionFaces, int erosionRecursion, int fillFaces, int fillRecursion) {
            this.erosionFaces = erosionFaces;
            this.erosionRecursion = erosionRecursion;
            this.fillFaces = fillFaces;
            this.fillRecursion = fillRecursion;
        }

        public int getErosionFaces() {
            return erosionFaces;
        }

        public void setErosionFaces(int erosionFaces) {
            this.erosionFaces = erosionFaces;
        }

        public int getErosionRecursion() {
            return erosionRecursion;
        }

        public void setErosionRecursion(int erosionRecursion) {
            this.erosionRecursion = erosionRecursion;
        }

        public int getFillFaces() {
            return fillFaces;
        }

        public void setFillFaces(int fillFaces) {
            this.fillFaces = fillFaces;
        }

        public int getFillRecursion() {
            return fillRecursion;
        }

        public void setFillRecursion(int fillRecursion) {
            this.fillRecursion = fillRecursion;
        }

        @Override
        public String toString() {
            return "TerraParameter{" +
                    "erosionFaces=" + erosionFaces +
                    ", erosionRecursion=" + erosionRecursion +
                    ", fillFaces=" + fillFaces +
                    ", fillRecursion=" + fillRecursion +
                    '}';
        }
    }

    public static class ClipboardParameter {

        private ArrayList<List<BlockVec4>> clipboardsBlock;
        private ArrayList<String> clipboardsName;
        private boolean isRandomRotation;

        public ClipboardParameter(ArrayList<List<BlockVec4>> clipboardsBlock, ArrayList<String> clipboardsName, boolean isRandomRotation) {
            this.clipboardsBlock = clipboardsBlock;
            this.clipboardsName = clipboardsName;
            this.isRandomRotation = isRandomRotation;
        }

        public ClipboardParameter setClipboardsBlock(ArrayList<List<BlockVec4>> clipboardsBlock) {
            this.clipboardsBlock = clipboardsBlock;
            return this;
        }

        public ClipboardParameter setClipboardsName(ArrayList<String> clipboardsName) {
            this.clipboardsName = clipboardsName;
            return this;
        }

        public ClipboardParameter addClipboards(List<BlockVec4> clipboardsBlock, String clipboardsName) {
            this.clipboardsBlock.add(clipboardsBlock);
            this.clipboardsName.add(clipboardsName);
            return this;
        }

        public ClipboardParameter removeClipboards(String clipboardsName) {

            int index = this.clipboardsName.indexOf(clipboardsName);
            this.clipboardsName.remove(index);
            this.clipboardsBlock.remove(index);

            return this;
        }

        public boolean isRandomRotation() {
            return isRandomRotation;
        }

        public void setRandomRotation(boolean randomRotation) {
            isRandomRotation = randomRotation;
        }

        public ArrayList<List<BlockVec4>> getClipboardsBlock() {
            return this.clipboardsBlock;
        }

        public ArrayList<String> getClipboardsName() {
            return this.clipboardsName;
        }

        public boolean getClipboardsNameExist(String clipboardsName) {
            return this.clipboardsName.contains(clipboardsName);
        }

        public void clearAll() {
            this.clipboardsName.clear();
            this.clipboardsBlock.clear();
        }

        @Override
        public String toString() {

            AtomicReference<String> name = new AtomicReference<>("");

            clipboardsName.forEach(s -> name.set(name + "{" + s + "},"));

            return "ClipboardParameter{" +
                    "clipboardsBlock.size=" + clipboardsBlock.size() +
                    ", clipboardsName=" + name +
                    ", isRandomRotation=" + isRandomRotation +
                    '}';
        }
    }

    public static class ClipboardBrush {

        private GlueList<BlockVec4> clipboardsBrush;

        public ClipboardBrush(GlueList<BlockVec4> clipboardsBrush) {
            this.clipboardsBrush = clipboardsBrush;
        }

        public GlueList<BlockVec4>getClipboardsBrush() {
            return clipboardsBrush;
        }

        public void setClipboardsBrush(GlueList<BlockVec4> clipboardsBrush) {
            this.clipboardsBrush = clipboardsBrush;
        }

        @Override
        public String toString() {

            AtomicReference<String> s = new AtomicReference<>("");
            clipboardsBrush.iterator().forEachRemaining(blockVec4 -> s.set(s + blockVec4.toString() + "\n"));

            return "ClipboardBrush{" +
                    "clipboardsBrush=" + clipboardsBrush + "\n" +
                    "size=" + clipboardsBrush.size() + "\n" +
                    "blockVec4 Iteration=" + s +
                    '}';
        }
    }
}