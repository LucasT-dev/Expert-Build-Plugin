package fr.marodeur.expertbuild.object;

import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockTypes;

import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.brush.NoneBrush;
import fr.marodeur.expertbuild.object.builderObjects.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.logging.Logger;

public class BrushBuilder {

private static final Logger LOG;
    private static final Configuration CONFIG;

    static {

        LOG = Logger.getLogger("Expert-Build");
        CONFIG = Main.getConfiguration();

    }


    private final UUID uuid;
    private AbstractBrush abstractBrush;
    private Boolean isEnable;
    private Boolean selMode;
    private Boolean flyMode;
    private List<BaseBlock> flowerMaterial;
    private List<Integer> flowerMaterialRate;
    private Biome biome;
    private int airBrush;
    private Integer radius;
    private int tickRT; // tick repeater
    private Pattern pattern;

    private UUID particleID;

    /**
     * Create objet BrushBuilder
     */
    public BrushBuilder(UUID uuid, AbstractBrush abstractBrush, Boolean isEnable, Boolean selMode, Boolean flyMode,
                        List<BaseBlock> flowerMaterial, List<Integer> flowerMaterialTaux, Biome biome, int airBrush,
                        Integer rayon, int tickRT, Pattern pattern, UUID particleID) {

        this.uuid = uuid;
        this.abstractBrush = abstractBrush;
        this.isEnable = isEnable;
        this.selMode = selMode;
        this.flyMode = flyMode;
        this.flowerMaterial = flowerMaterial;
        this.flowerMaterialRate = flowerMaterialTaux;
        this.biome = biome;
        this.airBrush = airBrush;
        this.radius = rayon;
        this.tickRT = tickRT;
        this.pattern = pattern;
        this.particleID = particleID;

    }

    // Object with UUID acces :

    // TIMELAPSE
    public BrushBuilder(UUID uuid) {
        this.uuid = uuid;
    }

    public TimelapseBuilderParameter getTimeLapseProfile() {
        return Main.getDataProfile().getTimelapseProfile(this.uuid);
    }

    public TerraParameter getTerraParameterProfile() {
        return Main.getDataProfile().getTerraParameterProfile(this.uuid);
    }

    public ClipboardParameter getClipboardParameter() {
        return Main.getDataProfile().getClipboardParameterProfile(this.uuid);
    }

    public LeatherParameter getLeatherParameter() {
        return Main.getDataProfile().getLeatherParameterProfile(this.uuid);
    }

    public GohaParameter getGohaParameter() {
        return Main.getDataProfile().getGohaParameterProfile(this.uuid);
    }

    public Clipboard3DParameter getClipboard3dParameter() {
        return Main.getDataProfile().getClipboard3dProfile(this.uuid);
    }



    // GETTER

    public UUID getUUID() {
        return uuid;
    }

    public World getWorld() {
        return Bukkit.getWorld(this.uuid);
    }

    public AbstractBrush getBrushType() {
        return abstractBrush;
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

    public Pattern getPattern() {
        return pattern;
    }

    public UUID getParticleID() {
        return particleID;
    }





    public boolean hasPermission(String permission) {
        return getPlayer().hasPermission(permission);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }


    // SETTER

    public BrushBuilder setBrush(AbstractBrush abstractBrush) {
        this.abstractBrush = abstractBrush;
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

        int maxRadius = CONFIG.getMaxRayonBrush();
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

    public BrushBuilder setPattern(Pattern pattern) {
        this.pattern = pattern;
        return this;
    }

    public void setParticleID() {
        this.particleID = UUID.randomUUID();
    }


    @Override
    public String toString() {
        return "BrushBuilder{" +
                "uuid=" + uuid +
                ", abstractBrush=" + abstractBrush +
                ", isEnable=" + isEnable +
                ", selMode=" + selMode +
                ", flyMode=" + flyMode +
                ", flowerMaterial=" + flowerMaterial +
                ", flowerMaterialRate=" + flowerMaterialRate +
                ", biome=" + biome +
                ", airBrush=" + airBrush +
                ", radius=" + radius +
                ", tickRT=" + tickRT +
                ", pattern=" + pattern.toString() +
                '}';
    }


    //OPERATION

    public void executeBrush(BrushBuilder brushBuilder, Material tool, Object loc, Object ploc) {

        Main.getBrush().getBrushes().stream()
                .filter(registerBrush -> registerBrush.getBrushName().equalsIgnoreCase(brushBuilder.getBrushType().getBrushName()))
                .forEach(registerBrush -> registerBrush.execute(brushBuilder, tool, loc, ploc));
    }

    //MESSAGE:

    public final String getMainPrefix() {
        return Main.prefix;
    }

    /*public final String getFawePrefix() {
        return Main.FawePrefix;
    }*/

    public BrushBuilder sendMessage(String path, boolean prefix, String[]... var) {

        new Message.MessageSender(path, prefix, var).send(Bukkit.getPlayer(uuid));

        return this;

    }

    /*public BrushBuilder sendMessage(String msg) {

        Bukkit.getPlayer(this.uuid).sendMessage(getMainPrefix() + msg);

        return this;

    }*/

    /**
     * Register BrushBuilder of player in HashMap
     *
     * @param p Player
     * @param sendError Boolean
     * @return this
     */
    public static BrushBuilder registerPlayer(@NotNull Player p, Boolean sendError) {

        if (Main.containsBrushBuilder(p)) {
            p.sendMessage(new Message.MessageSender("expbuild.message.brush.player_already_registered", true).getMessage());
            return getBrushBuilderPlayer(p, sendError);
        }

        BaseBlock ib = Objects.requireNonNull(BlockTypes.BARRIER).getDefaultState().toBaseBlock();
        List<BaseBlock> it = new ArrayList<>(Arrays.asList(ib, ib, ib, ib, ib, ib, ib, ib, ib));
        List<Integer> flowerMaterialTaux = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0);

        Main.getDataProfile().registerPlayer(p.getUniqueId());


        return Main.registerBrushBuilder(new BrushBuilder(p.getUniqueId(),
                new NoneBrush(),
                false,
                true,
                true,
                it, flowerMaterialTaux,
                CONFIG.getDefault_biome_brush(),
                CONFIG.getDefault_air_brush(),
                CONFIG.getDefaultBrushRayon(),
                4,
                new FaweAPI(p).getPattern(CONFIG.getDefault_pattern_brush()),
                UUID.randomUUID()));
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
                LOG.severe(new Message.MessageSender("expbuild.message.error.error_brushbuilder", true, new String[]{p.getName()}).getMessage());
            }
            return null;
        }
    }

    public boolean isEmpty(BrushBuilder bb) {
        return (bb == null);
    }
}