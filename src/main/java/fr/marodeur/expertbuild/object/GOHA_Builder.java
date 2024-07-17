package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.LISON.AdvancedParticleOperation;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class GOHA_Builder {

	private final Player player;
	private static final Configuration conf = Main.configuration();

	private int Height;
	private String MaterialOrga;
	private short Commutateur;
	private Location StartLoc;
	private UUID particleID;

	private boolean pregen;

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

	public GOHA_Builder(Player player, int height, String materialOrga, short commutateur, Location startLoc, UUID particleID, boolean pregen,
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

		this.player = player;
		this.Height = height;
		this.MaterialOrga = materialOrga;
		this.Commutateur = commutateur;
		this.StartLoc = startLoc;
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

	public Player getPlayer() {
		return player;
	}

	public GOHA_Builder setHeight(int height) {
		this.Height = height;
		return this;
	}

	public GOHA_Builder addHeight(boolean isShiftClick, boolean isRightClick) {

		int maxRotation = 350;
		int minRotation = 1;
		int n = this.Height;
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
			this.Height = maxRotation;
		} else if (n + num < minRotation) {
			this.Height = minRotation;
		} else {
			this.Height = n + num;
		}
		return this;
	}

	public GOHA_Builder setMaterialOrga(String materialOrga) {
		this.MaterialOrga = materialOrga;
		return this;
	}

	public GOHA_Builder setCommutateur(short commutateur) {
		this.Commutateur = commutateur;
		return this;
	}

	public GOHA_Builder setStartLoc(Location startLoc) {
		this.StartLoc = startLoc;
		return this;
	}

	public GOHA_Builder setParticleID() {
		this.particleID = UUID.randomUUID();
		return this;
	}

	public GOHA_Builder setPregen(boolean pregen) {
		this.pregen = pregen;
		return this;
	}

	public GOHA_Builder setArmD(Boolean armD) {
		this.ArmD = armD;
		return this;
	}

	public GOHA_Builder setArmDXAngle(short armDXAngle) {
		this.ArmDXAngle = armDXAngle;
		return this;
	}
	public GOHA_Builder addArmDXAngle(short armDXAngle) {
		this.ArmDXAngle += armDXAngle;
		return this;
	}

	public GOHA_Builder setArmDYAngle(short armDYAngle) {
		this.ArmDYAngle = armDYAngle;
		return this;
	}
	public GOHA_Builder addArmDYAngle(short armDYAngle) {
		this.ArmDYAngle += armDYAngle;
		return this;
	}

	public GOHA_Builder setArmG(Boolean armG) {
		this.ArmG = armG;
		return this;
	}

	public GOHA_Builder setArmGXAngle(short armGXAngle) {
		this.ArmGXAngle = armGXAngle;
		return this;
	}

	public GOHA_Builder addArmGXAngle(short armGXAngle) {
		this.ArmGXAngle += armGXAngle;
		return this;
	}

	public GOHA_Builder setArmGYAngle(short armGYAngle) {
		this.ArmGYAngle = armGYAngle;
		return this;
	}

	public GOHA_Builder addArmGYAngle(short armGYAngle) {
		this.ArmGYAngle += armGYAngle;
		return this;
	}

	public GOHA_Builder setForeArmD(Boolean foreArmD) {
		this.ForeArmD = foreArmD;
		return this;
	}

	public GOHA_Builder setForeArmDXAngle(short foreArmDXAngle) {
		this.ForeArmDXAngle = foreArmDXAngle;
		return this;
	}

	public GOHA_Builder addForeArmDXAngle(short foreArmDXAngle) {
		this.ForeArmDXAngle += foreArmDXAngle;
		return this;
	}

	public GOHA_Builder setForeArmDYAngle(short foreArmDYAngle) {
		this.ForeArmDYAngle = foreArmDYAngle;
		return this;
	}

	public GOHA_Builder addForeArmDYAngle(short foreArmDYAngle) {
		this.ForeArmDYAngle += foreArmDYAngle;
		return this;
	}

	public GOHA_Builder setForeArmG(Boolean foreArmG) {
		this.ForeArmG = foreArmG;
		return this;
	}

	public GOHA_Builder setForeArmGXAngle(short foreArmGXAngle) {
		this.ForeArmGXAngle = foreArmGXAngle;
		return this;
	}

	public GOHA_Builder addForeArmGXAngle(short foreArmGXAngle) {
		this.ForeArmGXAngle += foreArmGXAngle;
		return this;
	}

	public GOHA_Builder setForeArmGYAngle(short foreArmGYAngle) {
		this.ForeArmGYAngle = foreArmGYAngle;
		return this;
	}

	public GOHA_Builder addForeArmGYAngle(short foreArmGYAngle) {
		this.ForeArmGYAngle += foreArmGYAngle;
		return this;
	}

	public GOHA_Builder setTorso(Boolean torso) {
		this.Torso = torso;
		return this;
	}

	public GOHA_Builder setTorsoXAngle(short torsoXAngle) {
		this.TorsoXAngle = torsoXAngle;
		return this;
	}

	public GOHA_Builder addTorsoXAngle(short torsoXAngle) {
		this.TorsoXAngle += torsoXAngle;
		return this;
	}

	public GOHA_Builder setTorsoYAngle(short torsoYAngle) {
		this.TorsoYAngle = torsoYAngle;
		return this;
	}

	public GOHA_Builder addTorsoYAngle(short torsoYAngle) {
		this.TorsoYAngle += torsoYAngle;
		return this;
	}

	public GOHA_Builder setHead(Boolean head) {
		this.Head = head;
		return this;
	}

	public GOHA_Builder setHeadXAngle(short headXAngle) {
		this.HeadXAngle = headXAngle;
		return this;
	}

	public GOHA_Builder addHeadXAngle(short headXAngle) {
		this.HeadXAngle += headXAngle;
		return this;
	}

	public GOHA_Builder setHeadYAngle(short headYAngle) {
		this.HeadYAngle = headYAngle;
		return this;
	}

	public GOHA_Builder addHeadYAngle(short headYAngle) {
		this.HeadYAngle += headYAngle;
		return this;
	}

	public GOHA_Builder setLegD(Boolean legD) {
		this.LegD = legD;
		return this;
	}

	public GOHA_Builder setLegDXAngle(short legDXAngle) {
		this.LegDXAngle = legDXAngle;
		return this;
	}

	public GOHA_Builder addLegDXAngle(short legDXAngle) {
		this.LegDXAngle += legDXAngle;
		return this;
	}

	public GOHA_Builder setLegDYAngle(short legDYAngle) {
		this.LegDYAngle = legDYAngle;
		return this;
	}

	public GOHA_Builder addLegDYAngle(short legDYAngle) {
		this.LegDYAngle += legDYAngle;
		return this;
	}

	public GOHA_Builder setLegG(Boolean legG) {
		this.LegG = legG;
		return this;
	}

	public GOHA_Builder setLegGXAngle(short legGXAngle) {
		this.LegGXAngle = legGXAngle;
		return this;
	}

	public GOHA_Builder addLegGXAngle(short legGXAngle) {
		this.LegGXAngle += legGXAngle;
		return this;
	}

	public GOHA_Builder setLegGYAngle(short legGYAngle) {
		this.LegGYAngle = legGYAngle;
		return this;
	}

	public GOHA_Builder addLegGYAngle(short legGYAngle) {
		this.LegGYAngle += legGYAngle;
		return this;
	}

	public GOHA_Builder setTibiaD(Boolean tibiaD) {
		this.TibiaD = tibiaD;
		return this;
	}

	public GOHA_Builder setTibiaDXAngle(short tibiaDXAngle) {
		this.TibiaDXAngle = tibiaDXAngle;
		return this;
	}

	public GOHA_Builder addTibiaDXAngle(short tibiaDXAngle) {
		this.TibiaDXAngle += tibiaDXAngle;
		return this;
	}

	public GOHA_Builder setTibiaDYAngle(short tibiaDYAngle) {
		this.TibiaDYAngle = tibiaDYAngle;
		return this;
	}

	public GOHA_Builder addTibiaDYAngle(short tibiaDYAngle) {
		this.TibiaDYAngle += tibiaDYAngle;
		return this;
	}

	public GOHA_Builder setTibiaG(Boolean tibiaG) {
		this.TibiaG = tibiaG;
		return this;
	}

	public GOHA_Builder setTibiaGXAngle(short tibiaGXAngle) {
		this.TibiaGXAngle = tibiaGXAngle;
		return this;
	}

	public GOHA_Builder addTibiaGXAngle(short tibiaGXAngle) {
		this.TibiaGXAngle += tibiaGXAngle;
		return this;
	}

	public GOHA_Builder setTibiaGYAngle(short tibiaGYAngle) {
		this.TibiaGYAngle = tibiaGYAngle;
		return this;
	}

	public GOHA_Builder addTibiaGYAngle(short tibiaGYAngle) {
		this.TibiaGYAngle += tibiaGYAngle;
		return this;
	}





	public int getHeight() {
		return Height;
	}

	@NotNull
	public String getMaterialOrga() {
		return MaterialOrga;
	}

	public short getCommutateur() {
		return Commutateur;
	}

	public Location getStartLoc() {
		return StartLoc;
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

	@Override
	public String toString() {
		return "GOHA : " + Height + MaterialOrga + Commutateur + StartLoc + particleID +

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

	public static void registerPlayer(@NotNull Player p) {

		Configuration conf = Main.configuration();

		Main.registerGohaBuilder(new GOHA_Builder(
				p,
				conf.getDefault_orga_height(),
				conf.getDefault_material().toUpperCase(),
				(short)0, null, UUID.randomUUID(), false,
				true, (short)70, (short)180,
				true, (short)60, (short)0,
				true, (short)30, (short)180,
				true, (short)60, (short)180,
				true, (short)-75, (short)180,
				true, (short)-85, (short)180,
				true, (short)70, (short)180,
				true, (short)70, (short)180,
				true, (short)60, (short)0,
				true, (short)60, (short)0));
	}

	public static GOHA_Builder getGOHABuilder(@NotNull Player p) {
		return Main.getGohaBuilder(p);
	}


	public static class OrganicGeneration {

		/*
		 * X == Pitch
		 * Y == Yaw
		 *
		 *
		 *
		 */

		private static final GlueList<BlockVec4> bv4 = new GlueList<>();

		double factor = Main.configuration().getArm_correction_factor();

		private final Player p;
		private final GOHA_Builder goha_builder;
		private final BrushBuilder bb;
		private final Location startLoc;
		private final World world;

		private final int height;
		private final int seven;

		private Location l;
		private Location Jcerveau;

		private Location JepaulD;
		private Location JepaulG;

		private Location JbassinD;
		private Location JbassinG;

		private Location JarmG;
		private Location JarmD;

		private Location handG;
		private Location handD;

		private Location JlegG;
		private Location JlegD;

		private Location footG;
		private Location footD;

		public OrganicGeneration(Player p, Location startLoc) {

			this.p = p;
			this.bb = BrushBuilder.getBrushBuilderPlayer(p, true);
			this.goha_builder = getGOHABuilder(p);
			this.height = goha_builder.getHeight();
			this.seven = height/7;
			this.world = p.getWorld();
			this.startLoc = startLoc;
			bv4.clear();
		}

		public OrganicGeneration(Player p) {

			this.p = p;
			this.bb = BrushBuilder.getBrushBuilderPlayer(p, true);
			this.goha_builder = getGOHABuilder(p);
			this.height = goha_builder.getHeight();
			this.seven = height/7;
			this.world = p.getWorld();
			this.startLoc = p.getLocation();
			bv4.clear();
		}

		public OrganicGeneration getTorsoPoint() {

				l = new BlockVec4(startLoc).getPointAngle(goha_builder.getTorsoXAngle(), goha_builder.getTorsoYAngle(), 3 * seven, world);

				JepaulD = new Location(startLoc.getWorld(), (l.getX() + seven), l.getY(), l.getZ());
				JepaulG = new Location(startLoc.getWorld(), (l.getX() - seven), l.getY(), l.getZ());


				JbassinD = new Location(startLoc.getWorld(), (startLoc.getX() + seven), startLoc.getY(), startLoc.getZ());
				JbassinG = new Location(startLoc.getWorld(), (startLoc.getX() - seven), startLoc.getY(), startLoc.getZ());

			return this;
		}
		public OrganicGeneration getHeadPoint() {

			Jcerveau = new BlockVec4(l).getPointAngle(goha_builder.getHeadXAngle(), goha_builder.getHeadYAngle(), (int) ((int) (seven * factor) / 1.5), world);
			return this;
		}

		public OrganicGeneration getLeftArmPoint() {

			JarmG = new BlockVec4(JepaulG).getPointAngle(goha_builder.getArmGXAngle(), goha_builder.getArmGYAngle(), (int) (seven*factor), world);
			return this;
		}

		public OrganicGeneration getRightArmPoint() {

			JarmD = new BlockVec4(JepaulD).getPointAngle(goha_builder.getArmDXAngle(), goha_builder.getArmDYAngle(), (int) (seven*factor), world);
			return this;
		}

		public OrganicGeneration getLeftForeArmPoint() {

			handG = new BlockVec4(JarmG).getPointAngle(goha_builder.getForeArmGXAngle(), goha_builder.getForeArmGYAngle(), (int) (seven*factor), world);
			return this;
		}

		public OrganicGeneration getRightForeArmPoint() {

			handD = new BlockVec4(JarmD).getPointAngle(goha_builder.getForeArmDXAngle(), goha_builder.getForeArmDYAngle(), (int) (seven*factor), world);
			return this;
		}

		public OrganicGeneration getLeftLegPoint() {

			JlegG = new BlockVec4(JbassinG).getPointAngle(goha_builder.getLegGXAngle(), goha_builder.getLegGYAngle(), (2 *seven), world);
			return this;
		}

		public OrganicGeneration getRightLegPoint() {
			JlegD = new BlockVec4(JbassinD).getPointAngle(goha_builder.getLegDXAngle(), goha_builder.getLegDYAngle(), (2 *seven), world);
			return this;
		}

		public OrganicGeneration getLeftTibiaPoint() {

			footG = new BlockVec4(JlegG).getPointAngle(goha_builder.getTibiaGXAngle(), goha_builder.getTibiaGYAngle(), (2 *seven), world);
			return this;
		}

		public OrganicGeneration getRightTibiaPoint() {

			footD = new BlockVec4(JlegD).getPointAngle(goha_builder.getTibiaDXAngle(), goha_builder.getTibiaDYAngle(), (2 *seven), world);
			return this;
		}

		//--PARTICLE--
		public OrganicGeneration generateTorsoParticle() {

			if (this.goha_builder.getTorso()) {

				//LineVisualize.genLineParticle(p, startLoc, l);
				//LineVisualize.genLineParticle(p, JepaulG, JepaulD);
				//LineVisualize.genLineParticle(p, JbassinG, JbassinD);

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(startLoc.getX(), startLoc.getY(), startLoc.getZ()),
								new BlockVectorTool(l.getX(), l.getY(), l.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(JepaulG.getX(), JepaulG.getY(), JepaulG.getZ()),
								new BlockVectorTool(JepaulD.getX(), JepaulD.getY(), JepaulD.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(JbassinG.getX(), JbassinG.getY(), JbassinG.getZ()),
								new BlockVectorTool(JbassinD.getX(), JbassinD.getY(), JbassinD.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

			}

			return this;
		}

		public OrganicGeneration generateHeadParticle() {

			if (this.goha_builder.getHead()) {

				//LineVisualize.genLineParticle(p, l, Jcerveau);
				//LineVisualize.genSphereParticle(p, (int) (seven / 1.5), Jcerveau);

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(l.getX(), l.getY(), l.getZ()),
								new BlockVectorTool(Jcerveau.getX(), Jcerveau.getY(), Jcerveau.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

				new AdvancedParticleOperation(p)
						.sphereParticle(
								Jcerveau,
								(int) (seven / 1.5),
								conf.getParticle_convex_type_line(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

			}

			return this;
		}

		public OrganicGeneration generateLeftArmParticle() {

			if (this.goha_builder.getArmG()) {

				//LineVisualize.genLineParticle(p, JarmG, JepaulG);

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(JarmG.getX(), JarmG.getY(), JarmG.getZ()),
								new BlockVectorTool(JepaulG.getX(), JepaulG.getY(), JepaulG.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

			}

			return this;
		}

		public OrganicGeneration generateRightArmParticle() {

			if (this.goha_builder.getArmD()) {

				//LineVisualize.genLineParticle(p, JarmD, JepaulD);

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(JarmD.getX(), JarmD.getY(), JarmD.getZ()),
								new BlockVectorTool(JepaulD.getX(), JepaulD.getY(), JepaulD.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

			}

			return this;
		}

		public OrganicGeneration generateLeftForeArmParticle() {

			if (this.goha_builder.getForeArmG()) {

				//LineVisualize.genLineParticle(p, JarmG, handG);

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(JarmG.getX(), JarmG.getY(), JarmG.getZ()),
								new BlockVectorTool(handG.getX(), handG.getY(), handG.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });
			}
			return this;
		}

		public OrganicGeneration generateRightForeArmParticle() {

			if (this.goha_builder.getForeArmD()) {

				//LineVisualize.genLineParticle(p, JarmD, handD);

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(JarmD.getX(), JarmD.getY(), JarmD.getZ()),
								new BlockVectorTool(handD.getX(), handD.getY(), handD.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });
			}
			return this;
		}

		public OrganicGeneration generateLeftLegParticle() {

			if (this.goha_builder.getLegG()) {

				//LineVisualize.genLineParticle(p, JbassinG, JlegG);

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(JbassinG.getX(), JbassinG.getY(), JbassinG.getZ()),
								new BlockVectorTool(JlegG.getX(), JlegG.getY(), JlegG.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

			}
			return this;
		}

		public OrganicGeneration generateRightLegParticle() {

			if (this.goha_builder.getLegD()) {

				//LineVisualize.genLineParticle(p, JbassinD, JlegD);

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(JbassinD.getX(), JbassinD.getY(), JbassinD.getZ()),
								new BlockVectorTool(JlegD.getX(), JlegD.getY(), JlegD.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

			}

			return this;
		}

		public OrganicGeneration generateLeftTibiaParticle() {

			if (this.goha_builder.getTibiaG()) {

				//LineVisualize.genLineParticle(p, JlegG, footG);

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(JlegG.getX(), JlegG.getY(), JlegG.getZ()),
								new BlockVectorTool(footG.getX(), footG.getY(), footG.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });

			}

			return this;
		}

		public OrganicGeneration generateRightTibiaParticle() {

			if (this.goha_builder.getTibiaD()) {

				//LineVisualize.genLineParticle(p, JlegD, footD);

				new AdvancedParticleOperation(p)
						.lineParticle(
								new BlockVectorTool(JlegD.getX(), JlegD.getY(), JlegD.getZ()),
								new BlockVectorTool(footD.getX(), footD.getY(), footD.getZ()),
								conf.getParticle_convex_type_line(), conf.getSpacing_between_particles(),
								new AdvancedParticleOperation.RescheduledParticle[] { new AdvancedParticleOperation.RescheduledParticle().setParticleClearOrga(true) });
			}
			return this;
		}

		//--BLOCK--

		public OrganicGeneration generateTorsoBlock() {

			if (this.goha_builder.getTorso()) {

				bv4.addAll(new BlockVec4().getPointInto2Point(startLoc, l, 1, bb.getMaterial()));
				bv4.addAll(new BlockVec4().getPointInto2Point(JepaulG, JepaulD, 1, bb.getMaterial()));
				bv4.addAll(new BlockVec4().getPointInto2Point(JbassinG, JbassinD, 1, bb.getMaterial()));

			}
			return this;

		}

		public OrganicGeneration generateHeadBlock() {

			if (this.goha_builder.getHead()) {

				bv4.addAll(new BlockVec4().getPointInto2Point(l, Jcerveau, 1, bb.getMaterial()));
				bv4.addAll(new BlockVec4().getPointInSphere(Jcerveau, (int) (seven / 1.5), bb.getMaterial()));
			}

			return this;
		}

		public OrganicGeneration generateLeftArmBlock() {

			if (this.goha_builder.getArmG()) {

				bv4.addAll(new BlockVec4().getPointInto2Point(JarmG, JepaulG, 1, bb.getMaterial()));
			}

			return this;
		}

		public OrganicGeneration generateRightArmBlock() {

			if (this.goha_builder.getArmD()) {

				bv4.addAll(new BlockVec4().getPointInto2Point(JarmD, JepaulD, 1, bb.getMaterial()));
			}

			return this;
		}

		public OrganicGeneration generateLeftForeArmBlock() {

			if (this.goha_builder.getForeArmG()) {

				bv4.addAll(new BlockVec4().getPointInto2Point(JarmG, handG, 1, bb.getMaterial()));
			}

			return this;
		}

		public OrganicGeneration generateRightForeArmBlock() {

			if (this.goha_builder.getForeArmD()) {

				bv4.addAll(new BlockVec4().getPointInto2Point(JarmD, handD, 1, bb.getMaterial()));
			}

			return this;
		}

		public OrganicGeneration generateLeftLegBlock() {

			if (this.goha_builder.getLegG()) {

				bv4.addAll(new BlockVec4().getPointInto2Point(JbassinG, JlegG, 1, bb.getMaterial()));
			}

			return this;
		}

		public OrganicGeneration generateRightLegBlock() {

			if (this.goha_builder.getLegD()) {

				bv4.addAll(new BlockVec4().getPointInto2Point(JbassinD, JlegD, 1, bb.getMaterial()));
			}

			return this;
		}

		public OrganicGeneration generateLeftTibiaBlock() {

			if (this.goha_builder.getTibiaG()) {

				bv4.addAll(new BlockVec4().getPointInto2Point(JlegG, footG, 1, bb.getMaterial()));
			}
			return this;
		}

		public OrganicGeneration generateRightTibiaBlock() {

			if (this.goha_builder.getTibiaD()) {

				bv4.addAll(new BlockVec4().getPointInto2Point(JlegD, footD, 1, bb.getMaterial()));
			}

			return this;
		}

		public OrganicGeneration getAllPoint() {

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

		public OrganicGeneration generateAllParticle() {

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

		public OrganicGeneration generateAllBlock() {

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

			new UtilsFAWE(p).setBlockAnyPattern(p, bv4, true);
		}
	}
}