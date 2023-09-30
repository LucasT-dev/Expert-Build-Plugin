package fr.Marodeur.ExpertBuild.Object;

import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.API.GlueList;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Utils.LineVisualize;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GOHA_Builder {

	private final Player player;
	private static final MessageBuilder message = Main.getInstance().getMessageConfig();
	private static final Configuration conf = Main.getInstance().getConfig();

	private int Height;
	private String MaterialOrga;
	private int Commutateur;
	private Boolean Pregen;
	private Location StartLoc;
	private Boolean stopParticle;
	private Boolean update;

	private Boolean ArmD;
	private int ArmDXAngle;
	private int ArmDYAngle;

	private Boolean ArmG;
	private int ArmGXAngle;
	private int ArmGYAngle;

	private Boolean ForeArmD;
	private int ForeArmDXAngle;
	private int ForeArmDYAngle;

	private Boolean ForeArmG;
	private int ForeArmGXAngle;
	private int ForeArmGYAngle;

	private Boolean Torso;
	private int TorsoXAngle;
	private int TorsoYAngle;

	private Boolean Head;
	private int HeadXAngle;
	private int HeadYAngle;

	private Boolean LegD;
	private int LegDXAngle;
	private int LegDYAngle;

	private Boolean LegG;
	private int LegGXAngle;
	private int LegGYAngle;

	private Boolean TibiaD;
	private int TibiaDXAngle;
	private int TibiaDYAngle;

	private Boolean TibiaG;
	private int TibiaGXAngle;
	private int TibiaGYAngle;

	public GOHA_Builder(Player player, int height, String materialOrga, int commutateur, Boolean pregen, Location startLoc, Boolean stopParticle, Boolean update,
									Boolean armD, int armDXAngle, int armDYAngle,
									Boolean armG, int armGXAngle, int armGYAngle,
									Boolean foreArmD, int foreArmDXAngle, int foreArmDYAngle,
									Boolean foreArmG, int foreArmGXAngle, int foreArmGYAngle,
									Boolean torso, int torsoXAngle, int torsoYAngle,
									Boolean head, int headXAngle, int headYAngle,
									Boolean legD, int legDXAngle, int legDYAngle,
									Boolean legG, int legGXAngle, int legGYAngle,
									Boolean tibiaD, int tibiaDXAngle, int tibiaDYAngle,
									Boolean tibiaG, int tibiaGXAngle, int tibiaGYAngle) {

		this.player = player;
		this.Height = height;
		this.MaterialOrga = materialOrga;
		this.Commutateur = commutateur;
		this.Pregen = pregen;
		this.StartLoc = startLoc;
		this.stopParticle = stopParticle;
		this.update = update;
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

	public GOHA_Builder setCommutateur(int commutateur) {
		this.Commutateur = commutateur;
		return this;
	}

	public GOHA_Builder setPregen(Boolean pregen) {
		this.Pregen = pregen;
		return this;
	}

	public GOHA_Builder setStartLoc(Location startLoc) {
		this.StartLoc = startLoc;
		return this;
	}

	public GOHA_Builder setMomentallyParticleStop(Boolean stopParticle) {
		this.stopParticle = stopParticle;
		return this;
	}

	public GOHA_Builder setUpdate(Boolean update) {
		this.update = update;
		return this;
	}

	public GOHA_Builder setArmD(Boolean armD) {
		this.ArmD = armD;
		return this;
	}

	public GOHA_Builder setArmDXAngle(int armDXAngle) {
		this.ArmDXAngle = armDXAngle;
		return this;
	}
	public GOHA_Builder addArmDXAngle(int armDXAngle) {
		this.ArmDXAngle += armDXAngle;
		return this;
	}

	public GOHA_Builder setArmDYAngle(int armDYAngle) {
		this.ArmDYAngle = armDYAngle;
		return this;
	}
	public GOHA_Builder addArmDYAngle(int armDYAngle) {
		this.ArmDYAngle += armDYAngle;
		return this;
	}

	public GOHA_Builder setArmG(Boolean armG) {
		this.ArmG = armG;
		return this;
	}

	public GOHA_Builder setArmGXAngle(int armGXAngle) {
		this.ArmGXAngle = armGXAngle;
		return this;
	}

	public GOHA_Builder addArmGXAngle(int armGXAngle) {
		this.ArmGXAngle += armGXAngle;
		return this;
	}

	public GOHA_Builder setArmGYAngle(int armGYAngle) {
		this.ArmGYAngle = armGYAngle;
		return this;
	}

	public GOHA_Builder addArmGYAngle(int armGYAngle) {
		this.ArmGYAngle += armGYAngle;
		return this;
	}

	public GOHA_Builder setForeArmD(Boolean foreArmD) {
		this.ForeArmD = foreArmD;
		return this;
	}

	public GOHA_Builder setForeArmDXAngle(int foreArmDXAngle) {
		this.ForeArmDXAngle = foreArmDXAngle;
		return this;
	}

	public GOHA_Builder addForeArmDXAngle(int foreArmDXAngle) {
		this.ForeArmDXAngle += foreArmDXAngle;
		return this;
	}

	public GOHA_Builder setForeArmDYAngle(int foreArmDYAngle) {
		this.ForeArmDYAngle = foreArmDYAngle;
		return this;
	}

	public GOHA_Builder addForeArmDYAngle(int foreArmDYAngle) {
		this.ForeArmDYAngle += foreArmDYAngle;
		return this;
	}

	public GOHA_Builder setForeArmG(Boolean foreArmG) {
		this.ForeArmG = foreArmG;
		return this;
	}

	public GOHA_Builder setForeArmGXAngle(int foreArmGXAngle) {
		this.ForeArmGXAngle = foreArmGXAngle;
		return this;
	}

	public GOHA_Builder addForeArmGXAngle(int foreArmGXAngle) {
		this.ForeArmGXAngle += foreArmGXAngle;
		return this;
	}

	public GOHA_Builder setForeArmGYAngle(int foreArmGYAngle) {
		this.ForeArmGYAngle = foreArmGYAngle;
		return this;
	}

	public GOHA_Builder addForeArmGYAngle(int foreArmGYAngle) {
		this.ForeArmGYAngle += foreArmGYAngle;
		return this;
	}

	public GOHA_Builder setTorso(Boolean torso) {
		this.Torso = torso;
		return this;
	}

	public GOHA_Builder setTorsoXAngle(int torsoXAngle) {
		this.TorsoXAngle = torsoXAngle;
		return this;
	}

	public GOHA_Builder addTorsoXAngle(int torsoXAngle) {
		this.TorsoXAngle += torsoXAngle;
		return this;
	}

	public GOHA_Builder setTorsoYAngle(int torsoYAngle) {
		this.TorsoYAngle = torsoYAngle;
		return this;
	}

	public GOHA_Builder addTorsoYAngle(int torsoYAngle) {
		this.TorsoYAngle += torsoYAngle;
		return this;
	}

	public GOHA_Builder setHead(Boolean head) {
		this.Head = head;
		return this;
	}

	public GOHA_Builder setHeadXAngle(int headXAngle) {
		this.HeadXAngle = headXAngle;
		return this;
	}

	public GOHA_Builder addHeadXAngle(int headXAngle) {
		this.HeadXAngle += headXAngle;
		return this;
	}

	public GOHA_Builder setHeadYAngle(int headYAngle) {
		this.HeadYAngle = headYAngle;
		return this;
	}

	public GOHA_Builder addHeadYAngle(int headYAngle) {
		this.HeadYAngle += headYAngle;
		return this;
	}

	public GOHA_Builder setLegD(Boolean legD) {
		this.LegD = legD;
		return this;
	}

	public GOHA_Builder setLegDXAngle(int legDXAngle) {
		this.LegDXAngle = legDXAngle;
		return this;
	}

	public GOHA_Builder addLegDXAngle(int legDXAngle) {
		this.LegDXAngle += legDXAngle;
		return this;
	}

	public GOHA_Builder setLegDYAngle(int legDYAngle) {
		this.LegDYAngle = legDYAngle;
		return this;
	}

	public GOHA_Builder addLegDYAngle(int legDYAngle) {
		this.LegDYAngle += legDYAngle;
		return this;
	}

	public GOHA_Builder setLegG(Boolean legG) {
		this.LegG = legG;
		return this;
	}

	public GOHA_Builder setLegGXAngle(int legGXAngle) {
		this.LegGXAngle = legGXAngle;
		return this;
	}

	public GOHA_Builder addLegGXAngle(int legGXAngle) {
		this.LegGXAngle += legGXAngle;
		return this;
	}

	public GOHA_Builder setLegGYAngle(int legGYAngle) {
		this.LegGYAngle = legGYAngle;
		return this;
	}

	public GOHA_Builder addLegGYAngle(int legGYAngle) {
		this.LegGYAngle += legGYAngle;
		return this;
	}

	public GOHA_Builder setTibiaD(Boolean tibiaD) {
		this.TibiaD = tibiaD;
		return this;
	}

	public GOHA_Builder setTibiaDXAngle(int tibiaDXAngle) {
		this.TibiaDXAngle = tibiaDXAngle;
		return this;
	}

	public GOHA_Builder addTibiaDXAngle(int tibiaDXAngle) {
		this.TibiaDXAngle += tibiaDXAngle;
		return this;
	}

	public GOHA_Builder setTibiaDYAngle(int tibiaDYAngle) {
		this.TibiaDYAngle = tibiaDYAngle;
		return this;
	}

	public GOHA_Builder addTibiaDYAngle(int tibiaDYAngle) {
		this.TibiaDYAngle += tibiaDYAngle;
		return this;
	}

	public GOHA_Builder setTibiaG(Boolean tibiaG) {
		this.TibiaG = tibiaG;
		return this;
	}

	public GOHA_Builder setTibiaGXAngle(int tibiaGXAngle) {
		this.TibiaGXAngle = tibiaGXAngle;
		return this;
	}

	public GOHA_Builder addTibiaGXAngle(int tibiaGXAngle) {
		this.TibiaGXAngle += tibiaGXAngle;
		return this;
	}

	public GOHA_Builder setTibiaGYAngle(int tibiaGYAngle) {
		this.TibiaGYAngle = tibiaGYAngle;
		return this;
	}

	public GOHA_Builder addTibiaGYAngle(int tibiaGYAngle) {
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

	public int getCommutateur() {
		return Commutateur;
	}

	public Boolean getPregen() {
		return Pregen;
	}

	public Location getStartLoc() {
		return StartLoc;
	}

	public Boolean getMomentallyParticleStop() {
		return this.stopParticle;
	}

	public Boolean getUpdate() {
		return this.update;
	}

	// Arm D
	@NotNull
	public Boolean getArmD() {
		return ArmD;
	}

	public int getArmDXAngle() {
		return ArmDXAngle;
	}
	public int getArmDYAngle() {
		return ArmDYAngle;
	}

	// Arm G
	@NotNull
	public Boolean getArmG() {
		return ArmG;
	}

	public int getArmGXAngle() {
		return ArmGXAngle;
	}
	public int getArmGYAngle() {
		return ArmGYAngle;
	}

	// Fore Arm D
	@NotNull
	public Boolean getForeArmD() {
		return ForeArmD;
	}

	public int getForeArmDXAngle() {
		return ForeArmDXAngle;
	}
	public int getForeArmDYAngle() {
		return ForeArmDYAngle;
	}

	// Fore Arm G
	@NotNull
	public Boolean getForeArmG() {
		return ForeArmG;
	}

	public int getForeArmGXAngle() {
		return ForeArmGXAngle;
	}
	public int getForeArmGYAngle() {
		return ForeArmGYAngle;
	}

	// torso
	@NotNull
	public Boolean getTorso() {
		return Torso;
	}

	public int getTorsoXAngle() {
		return TorsoXAngle;
	}
	public int getTorsoYAngle() {
		return TorsoYAngle;
	}

	// Head
	@NotNull
	public Boolean getHead() {
		return Head;
	}

	public int getHeadXAngle() {
		return HeadXAngle;
	}
	public int getHeadYAngle() {
		return HeadYAngle;
	}

	// Leg D
	@NotNull
	public Boolean getLegD() {
		return LegD;
	}

	public int getLegDXAngle() {
		return LegDXAngle;
	}
	public int getLegDYAngle() {
		return LegDYAngle;
	}

	// Leg G
	@NotNull
	public Boolean getLegG() {
		return LegG;
	}

	public int getLegGXAngle() {
		return LegGXAngle;
	}
	public int getLegGYAngle() {
		return LegGYAngle;
	}

	// Tibia D
	@NotNull
	public Boolean getTibiaD() {
		return TibiaD;
	}

	public int getTibiaDXAngle() {
		return TibiaDXAngle;
	}
	public int getTibiaDYAngle() {
		return TibiaDYAngle;
	}

	// Tibia G
	@NotNull
	public Boolean getTibiaG() {
		return TibiaG;
	}
	public int getTibiaGXAngle() {
		return TibiaGXAngle;
	}
	public int getTibiaGYAngle() {
		return TibiaGYAngle;
	}

	@Override
	public String toString() {
		return "GOHA : " + Height + MaterialOrga + Commutateur + Pregen + StartLoc +

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

		Configuration conf = Main.getInstance().getConfig();

		Main.registerGohaBuilder(new GOHA_Builder(
				p,
				conf.getDefault_orga_height(),
				conf.getDefault_material().toUpperCase(),
				0, false, null,
				true, false,
				false, 70, 180,
				true, 60, 0,
				true, 30, 180,
				true, 60, 180,
				true, -75, 180,
				true, -85, 180,
				true, 70, 180,
				true, 70, 180,
				true, 60, 0,
				true, 60, 0));
	}

	public static GOHA_Builder getGOHABuilder(@NotNull Player p) {
		return Main.getGohaBuilder(p);
	}

	public void buildGoha(GOHA_Builder goha_builder) {
		Main.updateGohaBuilder(goha_builder);
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

		double factor = Main.getInstance().getConfig().getArm_correction_factor();

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

				LineVisualize.genLineParticle(p, startLoc, l);
				LineVisualize.genLineParticle(p, JepaulG, JepaulD);
				LineVisualize.genLineParticle(p, JbassinG, JbassinD);
			}

			return this;
		}

		public OrganicGeneration generateHeadParticle() {

			if (this.goha_builder.getHead()) {

				LineVisualize.genLineParticle(p, l, Jcerveau);
				LineVisualize.genSphereParticle(p, (int) (seven / 1.5), Jcerveau);
			}

			return this;
		}

		public OrganicGeneration generateLeftArmParticle() {

			if (this.goha_builder.getArmG()) {

				LineVisualize.genLineParticle(p, JarmG, JepaulG);
			}

			return this;
		}

		public OrganicGeneration generateRightArmParticle() {

			if (this.goha_builder.getArmD()) {

				LineVisualize.genLineParticle(p, JarmD, JepaulD);
			}

			return this;
		}

		public OrganicGeneration generateLeftForeArmParticle() {

			if (this.goha_builder.getForeArmG()) {

				LineVisualize.genLineParticle(p, JarmG, handG);
			}

			return this;
		}

		public OrganicGeneration generateRightForeArmParticle() {

			if (this.goha_builder.getForeArmD()) {

				LineVisualize.genLineParticle(p, JarmD, handD);
			}

			return this;
		}

		public OrganicGeneration generateLeftLegParticle() {

			if (this.goha_builder.getLegG()) {

				LineVisualize.genLineParticle(p, JbassinG, JlegG);
			}

			return this;
		}

		public OrganicGeneration generateRightLegParticle() {

			if (this.goha_builder.getLegD()) {

				LineVisualize.genLineParticle(p, JbassinD, JlegD);
			}

			return this;
		}

		public OrganicGeneration generateLeftTibiaParticle() {

			if (this.goha_builder.getTibiaG()) {

				LineVisualize.genLineParticle(p, JlegG, footG);
			}

			return this;
		}

		public OrganicGeneration generateRightTibiaParticle() {

			if (this.goha_builder.getTibiaD()) {

				LineVisualize.genLineParticle(p, JlegD, footD);
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