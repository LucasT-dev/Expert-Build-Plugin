package fr.Marodeur.ExpertBuild.Brush.GUI;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.Marodeur.ExpertBuild.API.FAWE.UtilsFAWE;
import fr.Marodeur.ExpertBuild.Enum.BrushEnum;
import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.BrushBuilder;
import fr.Marodeur.ExpertBuild.Object.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.UUID;

public class Executor {

	protected static String Swater_air;

	public static void AirBrush(@NotNull BrushBuilder brushBuilder, int incrementation) {

		if (brushBuilder.getAirBrush() + incrementation > 100) {
			brushBuilder.setAirBrush(100)
					.Build(brushBuilder);
			return;
		}
		if (brushBuilder.getAirBrush() + incrementation < 0) {
			brushBuilder.setAirBrush(0)
					.Build(brushBuilder);

		} else {
			brushBuilder.setAirBrush(brushBuilder.getAirBrush() + incrementation)
					.Build(brushBuilder);
		}
	}

	public static void Rayon(@NotNull BrushBuilder brushBuilder, int incrementation) {

		Configuration conf = Main.getInstance().getConfig();

		if (brushBuilder.getRayon() + incrementation > conf.getMaxRayonBrush()) {
			brushBuilder.setRayon(conf.getMaxRayonBrush())
					.Build(brushBuilder);
			return;
		}
		if (brushBuilder.getRayon() + incrementation < 1) {
			brushBuilder.setRayon(1)
					.Build(brushBuilder);

		} else {
			brushBuilder.setRayon(brushBuilder.getRayon() + incrementation)
					.Build(brushBuilder);
		}
	}

	public static void Years(@NotNull BrushBuilder brushBuilder, int crementation) {

		if (brushBuilder.getYears() + crementation > 7) {
			brushBuilder.setYears(7)
					.Build(brushBuilder);
			return;
		}
		if (brushBuilder.getYears() + crementation < 1) {
			brushBuilder.setYears(1)
					.Build(brushBuilder);

		} else {
			brushBuilder.setYears(brushBuilder.getYears() + crementation)
					.Build(brushBuilder);
		}
	}

	public static void brush(@NotNull Player p, @NotNull BrushBuilder brushBuilder) {

		if (brushBuilder.getFlowerMaterial().equals(""))
			return;

		if (brushBuilder.getWaterlog().equals(true)) {
			Swater_air = "water";
		} else {
			Swater_air = "air";
		}

		p.sendMessage(
				Main.prefix + "/br sphere " + brushBuilder.getFlowerMaterial() + brushBuilder.getAirBrush() + "%"
						+ Swater_air + " " + brushBuilder.getRayon());

		brushBuilder
				.setPattern(new UtilsFAWE(p).getPattern(brushBuilder.getFlowerMaterial() + brushBuilder.getAirBrush() + "%" + Swater_air))
				.setEnable(true)
				.setBrushType(BrushEnum.FLOWER)
				.Build(brushBuilder);

	}

	public static void clear_brush(@NotNull Player p, @NotNull BrushBuilder brushBuilder) {

		if (brushBuilder.getBrushType().equals(BrushEnum.FLOWER)) {
			if (!brushBuilder.getFlowerMaterial().equals("")) {

				brushBuilder.setFlowerMaterial("")
						.setBrushType(BrushEnum.NONE)
						.setEnable(false)
						.Build(brushBuilder);

				Bukkit.dispatchCommand(p, "/br none");

				p.sendMessage(
						Main.prefix + "/br sphere " + brushBuilder.getFlowerMaterial() + brushBuilder.getAirBrush() + "%"
								+ Swater_air + " " + brushBuilder.getRayon());
			}
		}
	}

	public static void applyTextures(SkullMeta meta, UUID uuid, String url) {
		GameProfile gameProfile = new GameProfile(uuid, null);
		gameProfile.getProperties().put("textures", new Property("textures", url));
		applyProfile(meta, gameProfile);
	}

	public static void applyProfile(@NotNull SkullMeta meta, GameProfile gameProfile) {
		try {
			Field profileField = meta.getClass().getDeclaredField("profile");

			profileField.setAccessible(true);
			profileField.set(meta, gameProfile);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}