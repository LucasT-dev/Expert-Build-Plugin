
/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fr.marodeur.expertbuild.object;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;

import org.bukkit.Material;

import java.util.logging.Logger;

public abstract class AbstractBrush {

    private static final Configuration conf = Main.configuration();

    public abstract String getBrushName();

    public String getAliases() {
        return "unused";
    }

    public abstract String getPermission();

    public abstract void honeycombToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc);

    public abstract void spectralToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc);

    public abstract void clayballToolBrush(BrushBuilder brushBuilder, Object loc, Object ploc);

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
            this.honeycombToolBrush(brushBuilder, loc, ploc);
        }

        if (tool.equals(conf.getTerraforming_tool_1())) {
            this.spectralToolBrush(brushBuilder, loc, ploc);
        }

        if (tool.equals(conf.getTerraforming_tool_2())) {
            this.clayballToolBrush(brushBuilder, loc, ploc);
        }
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

        public RegisterBrush createBrush(AbstractBrush aClass) {

            this.save(aClass);
            logger.info(new Message.MessageSender("expbuild.message.brush.brush_registered", false, new String[]{aClass.getClass().getSimpleName()} ).getMessage());
            return this;
        }

        private void save(AbstractBrush aClass) {

            brushes.add(aClass);

        }
    }
}



