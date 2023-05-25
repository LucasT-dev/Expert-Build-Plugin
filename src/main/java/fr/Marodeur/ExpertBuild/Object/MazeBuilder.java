package fr.Marodeur.ExpertBuild.Object;

import fr.Marodeur.ExpertBuild.Main;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class MazeBuilder {

    private Location center;
    private BlockFace dir;
    private File file;

    public MazeBuilder(Location center, BlockFace dir, File file) {
        this.center = center;
        this.dir = dir;
        this.file = file;

    }

    public Location getCenter() {
        return center;
    }
    public BlockFace getBlockFace() {
        return dir;
    }
    public File getFile() {
        return file;
    }


    public void setCenter(Location center) {
        this.center = center;
    }
    public void setBlockFace(BlockFace dir) {
        this.dir = dir;
    }
    public void setFile(File file) {
        this.file = file;
    }


    public static void createMazeObjet(@NotNull MazeBuilder mazeBuilder, String objetName, BlockFace dir, File file) {

        mazeBuilder.setBlockFace(dir);
        mazeBuilder.setFile(file);
        Main.Maze.put(objetName, mazeBuilder);
        return;
    }
    public void saveMazeObjet(MazeBuilder mazeBuilder, String objetName) {
        Main.Maze.replace(objetName, mazeBuilder);
        return;
    }
    public static MazeBuilder getMazeObjetWithName(String objetName) {
        return Main.Maze.get(objetName);
    }

    public void createMazeObjet2(@NotNull MazeBuilder mazeBuilder, String objetName, BlockFace dir, String file) {

        mazeBuilder.setBlockFace(dir);
        mazeBuilder.setFile(new File(file));
        Main.Maze.put(objetName, mazeBuilder);
        return;
    }
}

