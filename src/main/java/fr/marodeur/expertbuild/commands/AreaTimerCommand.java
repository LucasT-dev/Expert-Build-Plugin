package fr.marodeur.expertbuild.commands;

import com.sk89q.worldedit.regions.Region;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.enums.ExecutorType;
import fr.marodeur.expertbuild.object.AbstractCommand;
import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.builderObjects.AreaTimerParameter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AreaTimerCommand extends AbstractCommand {

    @Override
    public String getCommand() {
        return "/areatimer";
    }

    @Override
    public String getSyntax() {
        return "/areatimer <create/delete/info> <area_name>";
    }

    @Override
    public Integer getMinimumArgsLength() {
        return 2;
    }

    @Override
    public String getPermission() {
        return "exp.command.areatimer";
    }

    @Override
    public List<ExecutorType> getExecutorType() {
        return Collections.singletonList(ExecutorType.PLAYER);
    }

    @Override
    public void execute(CommandSender executor, Command cmd, @NotNull String msg, @NotNull String[] args) {

        Player p = (Player) executor;

        AreaTimerParameter areaTimerParameter;
        Region selection = null;


        if (args[0].equalsIgnoreCase("create")) {

            if (this.getValidArgument().hasSelection(p)) {
                selection = this.getValidArgument().getSelection(p);
            } else {
                this.getValidArgument().sendMessageInvalidSelection(executor);
                return;
            }

            String areaName = args[1].replace(" ", "_");

            if (!AreaTimerExist(areaName)) {

                if (Main.AREA_TIMER_PARAMETERS == null) Main.AREA_TIMER_PARAMETERS = new ArrayList<>();

                Main.AREA_TIMER_PARAMETERS.add(new AreaTimerParameter(
                        new BlockVectorTool().toBlockVectorTool(selection.getMinimumPoint()),
                        new BlockVectorTool().toBlockVectorTool(selection.getMaximumPoint()),
                        p.getWorld().getName(),
                        areaName,
                        true
                ));

                p.sendMessage(Main.prefix + "Area : " + areaName + " create ");
                return;
            }

            p.sendMessage(Main.prefix + "Area : " + areaName + " already exist, define an other name");
            return;

        }

        if (Main.AREA_TIMER_PARAMETERS == null) return;

        String areaName = args[1].replace(" ", "_");
        if (AreaTimerExist(areaName)) {
            areaTimerParameter = getAreaTimerUsingName(areaName);
        } else {
            this.getValidArgument().sendMessageInvalidArgument(p, areaName, "AreaTimer Name");
            return;
        }


        if (args[0].equalsIgnoreCase("delete")) {

           boolean b = Main.AREA_TIMER_PARAMETERS.remove(areaTimerParameter);

           if (b) p.sendMessage(Main.prefix + areaName + " deleted with succes");
           else p.sendMessage(Main.prefix + "Error during the deleting");

        }

        if (args[0].equalsIgnoreCase("info")) {

            p.sendMessage(Main.prefix + "Name : " + areaTimerParameter.getName() + " : \n" +
                            "World: " + areaTimerParameter.getWorldName() + "\n" +
                            "pos 1: " + areaTimerParameter.getPos1() + "\n" +
                            "pos 2: " + areaTimerParameter.getPos2() + "\n" +
                            "Total Player : " + areaTimerParameter.getTotalTimeInZone(p.getUniqueId()) + "\n" +
                            "Total: " + areaTimerParameter.getTotalTimeInZone()
                    );
            return;
        }


        if (args[0].equalsIgnoreCase("stop")) {


        }
    }

    @Override
    protected OptionalConditionExecution optionalConditionExecution(CommandSender s) {
        return new OptionalConditionExecution(s).AddBrushBuilderProfile();
    }

    @Override
    protected ArgumentLengthList getArgumentLengthList() {

        return new ArgumentLengthList(Arrays.asList(

                new ArgumentLength(2, "create", 0, "/areatimer create <area_name>", 2),
                new ArgumentLength(2, "delete", 0, "/areatimer delete <area_name>", 2),
                new ArgumentLength(2, "info", 0, "/areatimer info <area_name>", 2),
                new ArgumentLength(2, "stop", 0, "/areatimer stop <area_name>", 2)
        ));
    }

    @Override
    protected SubCommandSender getSubCommand(CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        SubCommandSender subCommandSender = new SubCommandSender();

        if (sender instanceof Player) {

            if (!(Main.AREA_TIMER_PARAMETERS == null)) {

                List<String> areaTimerName = Main.AREA_TIMER_PARAMETERS.stream().map(AreaTimerParameter::getName).toList();

                subCommandSender.addSubCommand(new SubCommandSelector().getList(1, areaTimerName).toSubCommand("None", new ConditionArgumentBefore("create", 0)));
                subCommandSender.addSubCommand(new SubCommandSelector().getList(1, areaTimerName).toSubCommand("None", new ConditionArgumentBefore("delete", 0)));
                subCommandSender.addSubCommand(new SubCommandSelector().getList(1, areaTimerName).toSubCommand("None", new ConditionArgumentBefore("info", 0)));
                subCommandSender.addSubCommand(new SubCommandSelector().getList(1, areaTimerName).toSubCommand("None", new ConditionArgumentBefore("stop", 0)));
            }

            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "create").toSubCommand("None"));
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "delete").toSubCommand("None"));
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "info").toSubCommand("None"));
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "stop").toSubCommand("None"));

        }

        return subCommandSender;
    }

    private boolean AreaTimerExist(String worldNameTest) {

        if (Main.AREA_TIMER_PARAMETERS != null) {

            for (AreaTimerParameter areaTimerParameter : Main.AREA_TIMER_PARAMETERS) {
                if (areaTimerParameter.getName().equalsIgnoreCase(worldNameTest)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private AreaTimerParameter getAreaTimerUsingName(String worldNameTest) {

        for (AreaTimerParameter areaTimerParameter : Main.AREA_TIMER_PARAMETERS) {
            if (areaTimerParameter.getName().equalsIgnoreCase(worldNameTest)) {
                return areaTimerParameter;
            }
        }
        return null;
    }
}
