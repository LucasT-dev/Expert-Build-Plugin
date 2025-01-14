
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.object;

import com.sk89q.worldedit.function.pattern.Pattern;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.FaweAPI;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public abstract class AbstractBrush {

    private final GlueList<BlockVectorTool> blockVectorToolGlueList = new GlueList<>();
    private BrushBuilder brushBuilder;
    private Pattern pattern;
    private static final Configuration conf = Main.getConfiguration();


    public abstract String getBrushName();

    public String getAliases() {
        return "unused";
    }

    public abstract String getPermission();



    public Pattern getPattern() {
        return this.pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public void setPattern(String stringPattern) {
        this.pattern = new FaweAPI(brushBuilder).getPattern(stringPattern);
    }

    private BrushBuilder getBrushBuilder() {
        return this.brushBuilder;
    }

    public void setBrushBuilder(BrushBuilder brushBuilder) {
        this.brushBuilder = brushBuilder;
    }

    public abstract boolean honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc);

    public abstract boolean spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc);

    public abstract boolean clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc);

    void execute(BrushBuilder brushBuilder, Material tool, Object loc, Object ploc) {

        if (!brushBuilder.hasPermission(this.getPermission())) {
            brushBuilder.sendMessage("expbuild.message.permission.no_permission_node", true, new String[]{getPermission()});
            return;
        }

        if (!brushBuilder.getEnable()) {
            brushBuilder.sendMessage("expbuild.message.brush.brush_disable", true);
            return;
        }


        if (tool.equals(Material.HONEYCOMB)) {
            if (this.honeycombToolBrush(brushBuilder, loc, ploc)) setBlock(brushBuilder.getPlayer());
            return;
        }

        if (tool.equals(conf.getTerraforming_tool_1())) {
            if (this.spectralToolBrush(brushBuilder, loc, ploc)) setBlock(brushBuilder.getPlayer());
            return;
        }

        if (tool.equals(conf.getTerraforming_tool_2())) {
            if (this.clayballToolBrush(brushBuilder, loc, ploc)) setBlock(brushBuilder.getPlayer());
            return;
        }
    }

    // for débug
    /*public void manuallySetBlock() {

        setBlock();
    }*/


    private void setBlock(Player p) {

        if (!this.blockVectorToolGlueList.isEmpty()) {

            new FaweAPI(p).setBlock(blockVectorToolGlueList, this.pattern, false);

            this.clearBlockList();
        }
    }

    public void addBlock(BlockVectorTool blockVectorTool) {
        blockVectorToolGlueList.add(blockVectorTool);
    }

    public void addBlock(GlueList<BlockVectorTool> blockVectorTools) {
        blockVectorToolGlueList.addAll(blockVectorTools);
    }

    public void clearBlockList() {
        blockVectorToolGlueList.clear();
    }

    public int size() {
        return blockVectorToolGlueList.size();
    }



    public static class RegisterBrush {

        private final GlueList<AbstractBrush> brushes;
        private final Logger logger = Logger.getLogger("Expert-Build");

        public RegisterBrush() {
            brushes = new GlueList<>();
        }

        public GlueList<AbstractBrush> getBrushes() {
            return brushes;
        }

        public void createBrush(AbstractBrush aClass) {
            this.save(aClass);
        }

        private void save(AbstractBrush aClass) {

            brushes.add(aClass);

        }
    }
}



