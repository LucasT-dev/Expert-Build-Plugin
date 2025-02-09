package fr.marodeur.expertbuild.object.builderObjects;

import com.sk89q.worldedit.function.pattern.Pattern;

import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;
import fr.marodeur.expertbuild.object.AbstractShape;
import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.lison.AdvancedParticleOperation;

import fr.marodeur.expertbuild.object.shape.SphereShape;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GohaParameter extends IDataProfile{

    private int height;
    private Pattern pattern;
    private short Commutateur;
    private BlockVectorTool startLoc;
    private UUID particleID;


    private boolean pregen;
    
    
    //Generation
    private final GlueList<BlockVectorTool> bv4 = new GlueList<>();

    private final double factor = getConfig().getArm_correction_factor();
    private int seven;

    private BlockVectorTool l;
    private BlockVectorTool Jcerveau;

    private BlockVectorTool JepaulD;
    private BlockVectorTool JepaulG;

    private BlockVectorTool JbassinD;
    private BlockVectorTool JbassinG;

    private BlockVectorTool JarmG;
    private BlockVectorTool JarmD;

    private BlockVectorTool handG;
    private BlockVectorTool handD;

    private BlockVectorTool JlegG;
    private BlockVectorTool JlegD;

    private BlockVectorTool footG;
    private BlockVectorTool footD;
    //End Generation
    
    
    

    private Boolean ArmD;
    private short ArmDXAngle;
    private short ArmDYAngle;

    private Boolean ArmG;
    private short ArmGXAngle;
    private short ArmGYAngle;

    private Boolean ForeArmD;
    private short ForeArmDXAngle;
    private short ForeArmDYAngle;

    private Boolean ForeArmG;
    private short ForeArmGXAngle;
    private short ForeArmGYAngle;

    private Boolean Torso;
    private short TorsoXAngle;
    private short TorsoYAngle;

    private Boolean Head;
    private short HeadXAngle;
    private short HeadYAngle;

    private Boolean LegD;
    private short LegDXAngle;
    private short LegDYAngle;

    private Boolean LegG;
    private short LegGXAngle;
    private short LegGYAngle;

    private Boolean TibiaD;
    private short TibiaDXAngle;
    private short TibiaDYAngle;

    private Boolean TibiaG;
    private short TibiaGXAngle;
    private short TibiaGYAngle;


    public GohaParameter(UUID profileID, int height, Pattern pattern, short commutateur, Location startLoc, UUID particleID, boolean pregen,
                         Boolean armD, short armDXAngle, short armDYAngle,
                         Boolean armG, short armGXAngle, short armGYAngle,
                         Boolean foreArmD, short foreArmDXAngle, short foreArmDYAngle,
                         Boolean foreArmG, short foreArmGXAngle, short foreArmGYAngle,
                         Boolean torso, short torsoXAngle, short torsoYAngle,
                         Boolean head, short headXAngle, short headYAngle,
                         Boolean legD, short legDXAngle, short legDYAngle,
                         Boolean legG, short legGXAngle, short legGYAngle,
                         Boolean tibiaD, short tibiaDXAngle, short tibiaDYAngle,
                         Boolean tibiaG, short tibiaGXAngle, short tibiaGYAngle) {
        super(profileID);

        this.height = height;
        this.seven = height / 7;
        this.pattern = pattern;
        this.Commutateur = commutateur;
        this.startLoc = new BlockVectorTool().toBlockVectorTool(startLoc);
        this.particleID = particleID;
        this.pregen = pregen;
        this.ArmD = armD;
        this.ArmDXAngle = armDXAngle;
        this.ArmDYAngle = armDYAngle;
        this.ArmG = armG;
        this.ArmGXAngle = armGXAngle;
        this.ArmGYAngle = armGYAngle;
        this.ForeArmD = foreArmD;
        this.ForeArmDXAngle = foreArmDXAngle;
        this.ForeArmDYAngle = foreArmDYAngle;
        this.ForeArmG = foreArmG;
        this.ForeArmGXAngle = foreArmGXAngle;
        this.ForeArmGYAngle = foreArmGYAngle;
        this.Torso = torso;
        this.TorsoXAngle = torsoXAngle;
        this.TorsoYAngle = torsoYAngle;
        this.Head = head;
        this.HeadXAngle = headXAngle;
        this.HeadYAngle = headYAngle;
        this.LegD = legD;
        this.LegDXAngle = legDXAngle;
        this.LegDYAngle = legDYAngle;
        this.LegG = legG;
        this.LegGXAngle = legGXAngle;
        this.LegGYAngle = legGYAngle;
        this.TibiaD = tibiaD;
        this.TibiaDXAngle = tibiaDXAngle;
        this.TibiaDYAngle = tibiaDYAngle;
        this.TibiaG = tibiaG;
        this.TibiaGXAngle = tibiaGXAngle;
        this.TibiaGYAngle = tibiaGYAngle;
    }

    public GohaParameter setHeight(int height) {

        this.height = height;
        this.seven = height / 7;
        return this;
    }

    public GohaParameter addHeight(boolean isShiftClick, boolean isRightClick) {

        int maxRotation = 350;
        int minRotation = 1;
        int n = this.height;
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
            this.height = maxRotation;
        } else if (n + num < minRotation) {
            this.height = minRotation;
        } else {
            this.height = n + num;
        }

        this.seven = this.height / 7;

        return this;
    }

    public GohaParameter setPattern(Pattern pattern) {
        this.pattern = pattern;
        return this;
    }

    public GohaParameter setCommutateur(short commutateur) {
        this.Commutateur = commutateur;
        return this;
    }

    public GohaParameter setStartLoc(BlockVectorTool startLoc) {
        this.startLoc = startLoc;
        return this;
    }

    public GohaParameter setParticleID() {
        this.particleID = UUID.randomUUID();
        return this;
    }

    public GohaParameter setPregen(boolean pregen) {
        this.pregen = pregen;
        return this;
    }

    public GohaParameter setArmD(Boolean armD) {
        this.ArmD = armD;
        return this;
    }

    public GohaParameter setArmDXAngle(short armDXAngle) {
        this.ArmDXAngle = armDXAngle;
        return this;
    }

    public GohaParameter addArmDXAngle(short armDXAngle) {
        this.ArmDXAngle += armDXAngle;
        return this;
    }

    public GohaParameter setArmDYAngle(short armDYAngle) {
        this.ArmDYAngle = armDYAngle;
        return this;
    }

    public GohaParameter addArmDYAngle(short armDYAngle) {
        this.ArmDYAngle += armDYAngle;
        return this;
    }

    public GohaParameter setArmG(Boolean armG) {
        this.ArmG = armG;
        return this;
    }

    public GohaParameter setArmGXAngle(short armGXAngle) {
        this.ArmGXAngle = armGXAngle;
        return this;
    }

    public GohaParameter addArmGXAngle(short armGXAngle) {
        this.ArmGXAngle += armGXAngle;
        return this;
    }

    public GohaParameter setArmGYAngle(short armGYAngle) {
        this.ArmGYAngle = armGYAngle;
        return this;
    }

    public GohaParameter addArmGYAngle(short armGYAngle) {
        this.ArmGYAngle += armGYAngle;
        return this;
    }

    public GohaParameter setForeArmD(Boolean foreArmD) {
        this.ForeArmD = foreArmD;
        return this;
    }

    public GohaParameter setForeArmDXAngle(short foreArmDXAngle) {
        this.ForeArmDXAngle = foreArmDXAngle;
        return this;
    }

    public GohaParameter addForeArmDXAngle(short foreArmDXAngle) {
        this.ForeArmDXAngle += foreArmDXAngle;
        return this;
    }

    public GohaParameter setForeArmDYAngle(short foreArmDYAngle) {
        this.ForeArmDYAngle = foreArmDYAngle;
        return this;
    }

    public GohaParameter addForeArmDYAngle(short foreArmDYAngle) {
        this.ForeArmDYAngle += foreArmDYAngle;
        return this;
    }

    public GohaParameter setForeArmG(Boolean foreArmG) {
        this.ForeArmG = foreArmG;
        return this;
    }

    public GohaParameter setForeArmGXAngle(short foreArmGXAngle) {
        this.ForeArmGXAngle = foreArmGXAngle;
        return this;
    }

    public GohaParameter addForeArmGXAngle(short foreArmGXAngle) {
        this.ForeArmGXAngle += foreArmGXAngle;
        return this;
    }

    public GohaParameter setForeArmGYAngle(short foreArmGYAngle) {
        this.ForeArmGYAngle = foreArmGYAngle;
        return this;
    }

    public GohaParameter addForeArmGYAngle(short foreArmGYAngle) {
        this.ForeArmGYAngle += foreArmGYAngle;
        return this;
    }

    public GohaParameter setTorso(Boolean torso) {
        this.Torso = torso;
        return this;
    }

    public GohaParameter setTorsoXAngle(short torsoXAngle) {
        this.TorsoXAngle = torsoXAngle;
        return this;
    }

    public GohaParameter addTorsoXAngle(short torsoXAngle) {
        this.TorsoXAngle += torsoXAngle;
        return this;
    }

    public GohaParameter setTorsoYAngle(short torsoYAngle) {
        this.TorsoYAngle = torsoYAngle;
        return this;
    }

    public GohaParameter addTorsoYAngle(short torsoYAngle) {
        this.TorsoYAngle += torsoYAngle;
        return this;
    }

    public GohaParameter setHead(Boolean head) {
        this.Head = head;
        return this;
    }

    public GohaParameter setHeadXAngle(short headXAngle) {
        this.HeadXAngle = headXAngle;
        return this;
    }

    public GohaParameter addHeadXAngle(short headXAngle) {
        this.HeadXAngle += headXAngle;
        return this;
    }

    public GohaParameter setHeadYAngle(short headYAngle) {
        this.HeadYAngle = headYAngle;
        return this;
    }

    public GohaParameter addHeadYAngle(short headYAngle) {
        this.HeadYAngle += headYAngle;
        return this;
    }

    public GohaParameter setLegD(Boolean legD) {
        this.LegD = legD;
        return this;
    }

    public GohaParameter setLegDXAngle(short legDXAngle) {
        this.LegDXAngle = legDXAngle;
        return this;
    }

    public GohaParameter addLegDXAngle(short legDXAngle) {
        this.LegDXAngle += legDXAngle;
        return this;
    }

    public GohaParameter setLegDYAngle(short legDYAngle) {
        this.LegDYAngle = legDYAngle;
        return this;
    }

    public GohaParameter addLegDYAngle(short legDYAngle) {
        this.LegDYAngle += legDYAngle;
        return this;
    }

    public GohaParameter setLegG(Boolean legG) {
        this.LegG = legG;
        return this;
    }

    public GohaParameter setLegGXAngle(short legGXAngle) {
        this.LegGXAngle = legGXAngle;
        return this;
    }

    public GohaParameter addLegGXAngle(short legGXAngle) {
        this.LegGXAngle += legGXAngle;
        return this;
    }

    public GohaParameter setLegGYAngle(short legGYAngle) {
        this.LegGYAngle = legGYAngle;
        return this;
    }

    public GohaParameter addLegGYAngle(short legGYAngle) {
        this.LegGYAngle += legGYAngle;
        return this;
    }

    public GohaParameter setTibiaD(Boolean tibiaD) {
        this.TibiaD = tibiaD;
        return this;
    }

    public GohaParameter setTibiaDXAngle(short tibiaDXAngle) {
        this.TibiaDXAngle = tibiaDXAngle;
        return this;
    }

    public GohaParameter addTibiaDXAngle(short tibiaDXAngle) {
        this.TibiaDXAngle += tibiaDXAngle;
        return this;
    }

    public GohaParameter setTibiaDYAngle(short tibiaDYAngle) {
        this.TibiaDYAngle = tibiaDYAngle;
        return this;
    }

    public GohaParameter addTibiaDYAngle(short tibiaDYAngle) {
        this.TibiaDYAngle += tibiaDYAngle;
        return this;
    }

    public GohaParameter setTibiaG(Boolean tibiaG) {
        this.TibiaG = tibiaG;
        return this;
    }

    public GohaParameter setTibiaGXAngle(short tibiaGXAngle) {
        this.TibiaGXAngle = tibiaGXAngle;
        return this;
    }

    public GohaParameter addTibiaGXAngle(short tibiaGXAngle) {
        this.TibiaGXAngle += tibiaGXAngle;
        return this;
    }

    public GohaParameter setTibiaGYAngle(short tibiaGYAngle) {
        this.TibiaGYAngle = tibiaGYAngle;
        return this;
    }

    public GohaParameter addTibiaGYAngle(short tibiaGYAngle) {
        this.TibiaGYAngle += tibiaGYAngle;
        return this;
    }





    public int getHeight() {
        return height;
    }


    public Pattern getPattern() {
        return pattern;
    }

    public short getCommutateur() {
        return Commutateur;
    }

    public BlockVectorTool getStartLoc() {
        return startLoc;
    }

    public UUID getParticleID() {
        return particleID;
    }

    public boolean getPregen() {
        return pregen;
    }

    // Arm D
    @NotNull
    public Boolean getArmD() {
        return ArmD;
    }

    public short getArmDXAngle() {
        return ArmDXAngle;
    }
    public short getArmDYAngle() {
        return ArmDYAngle;
    }

    // Arm G
    @NotNull
    public Boolean getArmG() {
        return ArmG;
    }

    public short getArmGXAngle() {
        return ArmGXAngle;
    }
    public short getArmGYAngle() {
        return ArmGYAngle;
    }

    // Fore Arm D
    @NotNull
    public Boolean getForeArmD() {
        return ForeArmD;
    }

    public short getForeArmDXAngle() {
        return ForeArmDXAngle;
    }
    public short getForeArmDYAngle() {
        return ForeArmDYAngle;
    }

    // Fore Arm G
    @NotNull
    public Boolean getForeArmG() {
        return ForeArmG;
    }

    public short getForeArmGXAngle() {
        return ForeArmGXAngle;
    }
    public short getForeArmGYAngle() {
        return ForeArmGYAngle;
    }

    // torso
    @NotNull
    public Boolean getTorso() {
        return Torso;
    }

    public short getTorsoXAngle() {
        return TorsoXAngle;
    }
    public short getTorsoYAngle() {
        return TorsoYAngle;
    }

    // Head
    @NotNull
    public Boolean getHead() {
        return Head;
    }

    public short getHeadXAngle() {
        return HeadXAngle;
    }
    public short getHeadYAngle() {
        return HeadYAngle;
    }

    // Leg D
    @NotNull
    public Boolean getLegD() {
        return LegD;
    }

    public short getLegDXAngle() {
        return LegDXAngle;
    }
    public short getLegDYAngle() {
        return LegDYAngle;
    }

    // Leg G
    @NotNull
    public Boolean getLegG() {
        return LegG;
    }

    public short getLegGXAngle() {
        return LegGXAngle;
    }
    public short getLegGYAngle() {
        return LegGYAngle;
    }

    // Tibia D
    @NotNull
    public Boolean getTibiaD() {
        return TibiaD;
    }

    public short getTibiaDXAngle() {
        return TibiaDXAngle;
    }
    public short getTibiaDYAngle() {
        return TibiaDYAngle;
    }

    // Tibia G
    @NotNull
    public Boolean getTibiaG() {
        return TibiaG;
    }
    public short getTibiaGXAngle() {
        return TibiaGXAngle;
    }
    public short getTibiaGYAngle() {
        return TibiaGYAngle;
    }
    
    
    // Start generation

    public GohaParameter getTorsoPoint() {

        l = startLoc.clone().getBlockVectorAngle(this.getTorsoXAngle(), this.getTorsoYAngle(), 3 * seven);

        JepaulD = new BlockVectorTool((l.getX() + seven), l.getY(), l.getZ());
        JepaulG = new BlockVectorTool((l.getX() - seven), l.getY(), l.getZ());

        JbassinD = new BlockVectorTool((startLoc.getX() + (seven * 0.5)), startLoc.getY(), startLoc.getZ());
        JbassinG = new BlockVectorTool((startLoc.getX() - (seven * 0.5)), startLoc.getY(), startLoc.getZ());

        return this;
    }
    public GohaParameter getHeadPoint() {

        Jcerveau = l.clone().getBlockVectorAngle(this.getHeadXAngle(), this.getHeadYAngle(), (int) ((int) (seven * factor) / 1.5));
        return this;
    }

    public GohaParameter getLeftArmPoint() {

        JarmG = JepaulG.clone().getBlockVectorAngle(this.getArmGXAngle(), this.getArmGYAngle(), (int) (seven*factor));
        return this;
    }

    public GohaParameter getRightArmPoint() {

        JarmD = JepaulD.clone().getBlockVectorAngle(this.getArmDXAngle(), this.getArmDYAngle(), (int) (seven*factor));
        return this;
    }

    public GohaParameter getLeftForeArmPoint() {

        handG = JarmG.clone().getBlockVectorAngle(this.getForeArmGXAngle(), this.getForeArmGYAngle(), (int) (seven*factor));
        return this;
    }

    public GohaParameter getRightForeArmPoint() {

        handD = JarmD.clone().getBlockVectorAngle(this.getForeArmDXAngle(), this.getForeArmDYAngle(), (int) (seven*factor));
        return this;
    }

    public GohaParameter getLeftLegPoint() {

        JlegG = JbassinG.clone().getBlockVectorAngle(this.getLegGXAngle(), this.getLegGYAngle(), (2 *seven));
        return this;
    }

    public GohaParameter getRightLegPoint() {
        JlegD = JbassinD.clone().getBlockVectorAngle(this.getLegDXAngle(), this.getLegDYAngle(), (2 *seven));
        return this;
    }

    public GohaParameter getLeftTibiaPoint() {

        footG = JlegG.clone().getBlockVectorAngle(this.getTibiaGXAngle(), this.getTibiaGYAngle(), (2 *seven));
        return this;
    }

    public GohaParameter getRightTibiaPoint() {

        footD = JlegD.clone().getBlockVectorAngle(this.getTibiaDXAngle(), this.getTibiaDYAngle(), (2 *seven));
        return this;
    }

    //--PARTICLE--
    public GohaParameter generateTorsoParticle() {

        if (this.getTorso()) {

            //LineVisualize.genLineParticle(getPlayer(), startLoc, l);
            //LineVisualize.genLineParticle(getPlayer(), JepaulG, JepaulD);
            //LineVisualize.genLineParticle(getPlayer(), JbassinG, JbassinD);

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(startLoc.getX(), startLoc.getY(), startLoc.getZ()),
                            new BlockVectorTool(l.getX(), l.getY(), l.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(JepaulG.getX(), JepaulG.getY(), JepaulG.getZ()),
                            new BlockVectorTool(JepaulD.getX(), JepaulD.getY(), JepaulD.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(JbassinG.getX(), JbassinG.getY(), JbassinG.getZ()),
                            new BlockVectorTool(JbassinD.getX(), JbassinD.getY(), JbassinD.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

        }

        return this;
    }

    public GohaParameter generateHeadParticle() {

        if (this.getHead()) {

            //LineVisualize.genLineParticle(getPlayer(), l, Jcerveau);
            //LineVisualize.genSphereParticle(getPlayer(), (int) (seven / 1.5), Jcerveau);

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(l.getX(), l.getY(), l.getZ()),
                            new BlockVectorTool(Jcerveau.getX(), Jcerveau.getY(), Jcerveau.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

            new AdvancedParticleOperation(getPlayer())
                    .sphereParticle(
                            Jcerveau,
                            (int) (seven / 1.5),
                            getConfig().getParticle_convex_type_line(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

        }

        return this;
    }

    public GohaParameter generateLeftArmParticle() {

        if (this.getArmG()) {

            //LineVisualize.genLineParticle(getPlayer(), JarmG, JepaulG);

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(JarmG.getX(), JarmG.getY(), JarmG.getZ()),
                            new BlockVectorTool(JepaulG.getX(), JepaulG.getY(), JepaulG.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

        }

        return this;
    }

    public GohaParameter generateRightArmParticle() {

        if (this.getArmD()) {

            //LineVisualize.genLineParticle(getPlayer(), JarmD, JepaulD);

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(JarmD.getX(), JarmD.getY(), JarmD.getZ()),
                            new BlockVectorTool(JepaulD.getX(), JepaulD.getY(), JepaulD.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

        }

        return this;
    }

    public GohaParameter generateLeftForeArmParticle() {

        if (this.getForeArmG()) {

            //LineVisualize.genLineParticle(getPlayer(), JarmG, handG);

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(JarmG.getX(), JarmG.getY(), JarmG.getZ()),
                            new BlockVectorTool(handG.getX(), handG.getY(), handG.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });
        }
        return this;
    }

    public GohaParameter generateRightForeArmParticle() {

        if (this.getForeArmD()) {

            //LineVisualize.genLineParticle(getPlayer(), JarmD, handD);

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(JarmD.getX(), JarmD.getY(), JarmD.getZ()),
                            new BlockVectorTool(handD.getX(), handD.getY(), handD.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });
        }
        return this;
    }

    public GohaParameter generateLeftLegParticle() {

        if (this.getLegG()) {

            //LineVisualize.genLineParticle(getPlayer(), JbassinG, JlegG);

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(JbassinG.getX(), JbassinG.getY(), JbassinG.getZ()),
                            new BlockVectorTool(JlegG.getX(), JlegG.getY(), JlegG.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

        }
        return this;
    }

    public GohaParameter generateRightLegParticle() {

        if (this.getLegD()) {

            //LineVisualize.genLineParticle(getPlayer(), JbassinD, JlegD);

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(JbassinD.getX(), JbassinD.getY(), JbassinD.getZ()),
                            new BlockVectorTool(JlegD.getX(), JlegD.getY(), JlegD.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

        }

        return this;
    }

    public GohaParameter generateLeftTibiaParticle() {

        if (this.getTibiaG()) {

            //LineVisualize.genLineParticle(getPlayer(), JlegG, footG);

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(JlegG.getX(), JlegG.getY(), JlegG.getZ()),
                            new BlockVectorTool(footG.getX(), footG.getY(), footG.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

        }

        return this;
    }

    public GohaParameter generateRightTibiaParticle() {

        if (this.getTibiaD()) {

            //LineVisualize.genLineParticle(getPlayer(), JlegD, footD);

            new AdvancedParticleOperation(getPlayer())
                    .lineParticle(
                            new BlockVectorTool(JlegD.getX(), JlegD.getY(), JlegD.getZ()),
                            new BlockVectorTool(footD.getX(), footD.getY(), footD.getZ()),
                            getConfig().getParticle_convex_type_line(), getConfig().getSpacing_between_particles(),
                            new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });
        }
        return this;
    }

    //--BLOCK--

    public GohaParameter generateTorsoBlock() {

        if (this.getTorso()) {

            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(startLoc, l));
            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(JepaulG, JepaulD));
            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(JbassinG, JbassinD));

        }
        return this;
    }

    public GohaParameter generateHeadBlock() {

        if (this.getHead()) {

            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(l, Jcerveau));

            AbstractShape abstractShape = new SphereShape();
            abstractShape.addParameter("radius", (int) (seven / 1.5));

            bv4.addAll( abstractShape.generateShape(BrushBuilder.getBrushBuilderPlayer(getPlayer()), Jcerveau).toList());

        }

        return this;
    }

    public GohaParameter generateLeftArmBlock() {

        if (this.getArmG()) {

            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(JarmG, JepaulG));
        }

        return this;
    }

    public GohaParameter generateRightArmBlock() {

        if (this.getArmD()) {

            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(JarmD, JepaulD));
        }

        return this;
    }

    public GohaParameter generateLeftForeArmBlock() {

        if (this.getForeArmG()) {

            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(JarmG, handG));
        }

        return this;
    }

    public GohaParameter generateRightForeArmBlock() {

        if (this.getForeArmD()) {

            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(JarmD, handD));
        }

        return this;
    }

    public GohaParameter generateLeftLegBlock() {

        if (this.getLegG()) {

            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(JbassinG, JlegG));
        }

        return this;
    }

    public GohaParameter generateRightLegBlock() {

        if (this.getLegD()) {

            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(JbassinD, JlegD));
        }

        return this;
    }

    public GohaParameter generateLeftTibiaBlock() {

        if (this.getTibiaG()) {

            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(JlegG, footG));
        }
        return this;
    }

    public GohaParameter generateRightTibiaBlock() {

        if (this.getTibiaD()) {

            bv4.addAll(new BlockVectorTool().getBlockBetweenTwoPoint(JlegD, footD));
        }

        return this;
    }

    public GohaParameter getAllPoint() {

        getTorsoPoint()
                .getHeadPoint()

                .getLeftArmPoint()
                .getRightArmPoint()

                .getLeftForeArmPoint()
                .getRightForeArmPoint()

                .getLeftLegPoint()
                .getRightLegPoint()

                .getLeftTibiaPoint()
                .getRightTibiaPoint();

        return this;
    }

    public GohaParameter generateAllParticle() {

        generateTorsoParticle()
                .generateHeadParticle()

                .generateLeftArmParticle()
                .generateRightArmParticle()

                .generateLeftForeArmParticle()
                .generateRightForeArmParticle()

                .generateLeftLegParticle()
                .generateRightLegParticle()

                .generateLeftTibiaParticle()
                .generateRightTibiaParticle();


        return this;
    }

    public GohaParameter generateAllBlock() {

        generateTorsoBlock()
                .generateHeadBlock()

                .generateLeftArmBlock()
                .generateRightArmBlock()

                .generateLeftForeArmBlock()
                .generateRightForeArmBlock()

                .generateLeftLegBlock()
                .generateRightLegBlock()

                .generateLeftTibiaBlock()
                .generateRightTibiaBlock();

        return this;
    }

    public void buildBlock() {

        new FaweAPI(getPlayer()).setBlock(bv4, pattern, true);

        bv4.clear();

    }
    
    // End generation
    
    

    @Override
    public String toString() {
        return "GOHA : " + height + pattern + Commutateur + startLoc + particleID +

                " ArmD > " + ArmD + " Pitch : " + ArmDXAngle + " Yaw : " + ArmDYAngle +
                " ArmG > " + ArmG + " Pitch : " + ArmGXAngle + " Y : " + ArmGYAngle +

                " ForeArmD > " + ForeArmD + " Pitch : " + ForeArmDXAngle + " Yaw : " + ForeArmDYAngle +
                " ForeArmG > " + ForeArmG + " Pitch : " + ForeArmGXAngle + " Yaw : " + ForeArmGYAngle +

                " Torso > " + Torso + " Pitch : " + TorsoXAngle + " Yaw : " + TorsoYAngle +
                " Head > " + Head + " Pitch : " + HeadXAngle + " Yaw : " + HeadYAngle +

                " LegD> " + LegD + " Pitch : " + LegDXAngle + " Yaw : " + LegDYAngle +
                " LegG> " + LegG + " Pitch : " + LegGXAngle + " Yaw : " + LegGYAngle +

                " TibiaD> " + TibiaD + " Pitch : " + TibiaDXAngle + " Yaw : " + TibiaDYAngle +
                " TibiaG> " + TibiaG + " Pitch : " + TibiaGXAngle + " Yaw : " + TibiaGYAngle;

    }
}