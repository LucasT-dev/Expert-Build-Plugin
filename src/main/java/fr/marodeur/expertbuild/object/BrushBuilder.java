package fr.marodeur.expertbuild.object;

import com.sk89q.worldedit.function.pattern.Pattern;

import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.brush.NoneBrush;
import fr.marodeur.expertbuild.object.builderObjects.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

public class BrushBuilder {

    private static final HashMap<UUID, BrushBuilder> BRUSH_BUILDER_HASH_MAP = new HashMap<>();
    private static final Configuration CONFIG;

    static {

        CONFIG = Main.getConfiguration();

    }


    private final UUID uuid;
    private AbstractBrush abstractBrush;
    private Boolean isEnable;
    private Boolean selMode;
    private Boolean flyMode;
    private Integer radius;
    private Pattern pattern;

    private UUID particleID;

    /**
     * Create objet BrushBuilder
     */
    public BrushBuilder(UUID uuid, AbstractBrush abstractBrush, Boolean isEnable, Boolean selMode, Boolean flyMode,
                        Integer rayon, Pattern pattern, UUID particleID) {

        this.uuid = uuid;
        this.abstractBrush = abstractBrush;
        this.isEnable = isEnable;
        this.selMode = selMode;
        this.flyMode = flyMode;
        this.radius = rayon;
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

    // TERRA B EB brush
    public TerraParameter getTerraParameterProfile() {
        return Main.getDataProfile().getTerraParameterProfile(this.uuid);
    }

    // CLIPBOARD Brush
    public ClipboardParameter getClipboardParameter() {
        return Main.getDataProfile().getClipboardParameterProfile(this.uuid);
    }

    // LEATHER
    public LeatherParameter getLeatherParameter() {
        return Main.getDataProfile().getLeatherParameterProfile(this.uuid);
    }

    // GOHA
    public GohaParameter getGohaParameter() {
        return Main.getDataProfile().getGohaParameterProfile(this.uuid);
    }

    // CLIBBOARD 3D Brush
    public Clipboard3DParameter getClipboard3dParameter() {
        return Main.getDataProfile().getClipboard3dProfile(this.uuid);
    }

    // FLOWER Brush
    public FlowerBrushParameter getFlowerBrushParameter() {
        return Main.getDataProfile().getFlowerBrushProfile(this.uuid);
    }

    // BRUSH Brush
    public BrushParameter getBrushParameter() {
        return Main.getDataProfile().getBrushProfile(this.uuid);
    }



    // GETTER

    public UUID getUUID() {
        return uuid;
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

    public Integer getRadius() {
        return radius;
    }

    public Pattern getPattern() {
        return pattern;
    }
    public UUID getParticleID() {
        return particleID;
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
                ", abstractBrush=" + abstractBrush.getBrushName() +
                ", isEnable=" + isEnable +
                ", selMode=" + selMode +
                ", flyMode=" + flyMode +
                ", radius=" + radius +
                ", pattern=" + pattern.toString() +
                '}';
    }


    //OPERATION

    public void executeBrush(BrushBuilder brushBuilder, Material tool, Object loc, Object ploc) {

        Main.getBrush().getBrushes().stream()
                .filter(registerBrush -> registerBrush.getBrushName().equalsIgnoreCase(brushBuilder.getBrushType().getBrushName()))
                .forEach(registerBrush -> registerBrush.execute(brushBuilder, tool, loc, ploc));
    }

    public BrushBuilder sendMessage(String path, boolean prefix, String[]... var) {

        new Message.MessageSender(path, prefix, var).send(Bukkit.getPlayer(uuid));
        return this;
    }

    public BrushBuilder sendMessage(String path, String[]... var) {
        return sendMessage(path, true, var);
    }


    /**
     * Register BrushBuilder of player in HashMap
     *
     * @return this
     */
    public static BrushBuilder registerPlayer(@NotNull Player p) {

        if (containsPlayerBrush(p)) {
            p.sendMessage(new Message.MessageSender("expbuild.message.brush.player_already_registered", true).getMessage());
            return getBrushBuilderPlayer(p);
        }

        Main.getDataProfile().registerPlayer(p.getUniqueId());

        BRUSH_BUILDER_HASH_MAP.put(p.getUniqueId(), new BrushBuilder(p.getUniqueId(),
                new NoneBrush(),
                false,
                true,
                CONFIG.getDefault_click_fly_selection(),
                CONFIG.getDefaultBrushRayon(),
                new FaweAPI(p).getPattern(CONFIG.getDefault_pattern_brush()),
                UUID.randomUUID()));

        return BrushBuilder.getBrushBuilderPlayer(p);
    }

    public static boolean containsPlayerBrush(Player p) {
        return BRUSH_BUILDER_HASH_MAP.containsKey(p.getUniqueId());
    }

    /**
     *
     * Get objet BrushBuild associated at the player
     *
     * @param p Player
     */
    public static BrushBuilder getBrushBuilderPlayer(Player p) {

        BrushBuilder brushBuilder = BRUSH_BUILDER_HASH_MAP.get(p.getUniqueId());

        if (brushBuilder == null) {
            throw new NullPointerException(p.getName() + " has not been registered as a builder, for this he must be an operator or have the `exp.register` permission");
        }
        return brushBuilder;
    }

    public static Stream<BrushBuilder> getBrushBuilderStream() {

        return BRUSH_BUILDER_HASH_MAP.values().parallelStream();

    }

    private static boolean isEmpty(BrushBuilder bb) {
        return (bb == null);
    }
}