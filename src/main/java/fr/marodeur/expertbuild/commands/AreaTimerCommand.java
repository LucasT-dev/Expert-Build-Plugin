package fr.marodeur.expertbuild.commands;

import com.sk89q.worldedit.regions.Region;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.enums.ExecutorType;
import fr.marodeur.expertbuild.object.AbstractCommand;
import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.Message;
import fr.marodeur.expertbuild.object.builderObjects.AreaTimerParameter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        return "/areatimer <create/delete/info/stop/resume> <area_name>";
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
        Region selection;


        if (args[0].equalsIgnoreCase("create")) {

            if (this.getValidArgument().hasSelection(p)) {
                selection = this.getValidArgument().getSelection(p);
            } else {
                this.getValidArgument().sendMessageInvalidSelection(executor);
                return;
            }

            String areaName = args[1].replace(" ", "_");

            if (!AreaTimerExist(areaName)) {

                Main.getDataProfile().getAreaTimerParameterList().add(new AreaTimerParameter(
                        new BlockVectorTool().toBlockVectorTool(selection.getMinimumPoint()),
                        new BlockVectorTool().toBlockVectorTool(selection.getMaximumPoint()),
                        p.getWorld().getName(),
                        areaName,
                        true
                ));

                new Message.MessageSender("expbuild.message.commands.areatimer_create", true, new String[]{areaName}).send(p);
                return;
            }

            new Message.MessageSender("expbuild.message.commands.areatimer_already_exist", true, new String[]{areaName}).send(p);
            return;
        }

        if (Main.getDataProfile().getAreaTimerParameterList() == null) return;

        String areaName = args[1].replace(" ", "_");
        if (AreaTimerExist(areaName)) {
            areaTimerParameter = getAreaTimerUsingName(areaName);
        } else {
            this.getValidArgument().sendMessageInvalidArgument(p, areaName, "AreaTimer Name");
            return;
        }


        if (args[0].equalsIgnoreCase("delete")) {

           boolean b = Main.getDataProfile().getAreaTimerParameterList().remove(areaTimerParameter);

           Main.getInstance().getFileManager().areaTimerFile.deleteFile(areaName);

           if (b) new Message.MessageSender("expbuild.message.commands.areatimer_delete", true, new String[]{areaName}).send(p);

           else new Message.MessageSender("expbuild.message.commands.areatimer_error_delete", true, new String[]{areaName}).send(p);

        }

        if (args[0].equalsIgnoreCase("info")) {

            new Message.MessageSender("expbuild.message.commands.areatimer_info", true,
                    new String[]{
                            areaTimerParameter.getName(),
                            areaTimerParameter.getWorldName(),
                            String.valueOf(areaTimerParameter.getPos1()),
                            String.valueOf(areaTimerParameter.getPos2()),
                            areaTimerParameter.getTotalTimeInZone(p.getUniqueId()),
                            areaTimerParameter.getTotalTimeInZone()
                    }).send(p);

            return;
        }

        if (args[0].equalsIgnoreCase("stop")) {

            if (areaTimerParameter.isRunning()) {

                areaTimerParameter.setRunning(false);

                // Retire les joueurs présent dans la areatimer
                areaTimerParameter.getUuidPlayerEnteredInArea().forEach(areaTimerParameter::playerExitedZone);

                new Message.MessageSender("expbuild.message.commands.areatimer_pause", true, new String[]{areaName}).send(p);
            } else {
                new Message.MessageSender("expbuild.message.commands.areatimer_already_pause", true, new String[]{areaName}).send(p);
            }
        }

        if (args[0].equalsIgnoreCase("resume")) {

            if (!areaTimerParameter.isRunning()) {
                areaTimerParameter.setRunning(true);
                new Message.MessageSender("expbuild.message.commands.areatimer_resume", true, new String[]{areaName}).send(p);
            } else {
                new Message.MessageSender("expbuild.message.commands.areatimer_already_resume", true, new String[]{areaName}).send(p);
            }
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

            if (!(Main.getDataProfile().getAreaTimerParameterList() == null)) {

                List<String> areaTimerName = Main.getDataProfile().getAreaTimerParameterList()
                        .stream()
                        .map(AreaTimerParameter::getName)
                        .toList();

                subCommandSender.addSubCommand(new SubCommandSelector().getList(1, areaTimerName).toSubCommand("None", new ConditionArgumentBefore("create", 0)));
                subCommandSender.addSubCommand(new SubCommandSelector().getList(1, areaTimerName).toSubCommand("None", new ConditionArgumentBefore("delete", 0)));
                subCommandSender.addSubCommand(new SubCommandSelector().getList(1, areaTimerName).toSubCommand("None", new ConditionArgumentBefore("info", 0)));
                subCommandSender.addSubCommand(new SubCommandSelector().getList(1, areaTimerName).toSubCommand("None", new ConditionArgumentBefore("stop", 0)));
                subCommandSender.addSubCommand(new SubCommandSelector().getList(1, areaTimerName).toSubCommand("None", new ConditionArgumentBefore("resume", 0)));
            }

            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "create").toSubCommand("None"));
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "delete").toSubCommand("None"));
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "info").toSubCommand("None"));
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "stop").toSubCommand("None"));
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "resume").toSubCommand("None"));

        }

        return subCommandSender;
    }

    private boolean AreaTimerExist(String worldNameTest) {

        if (Main.getDataProfile().getAreaTimerParameterList() != null) {

            for (AreaTimerParameter areaTimerParameter : Main.getDataProfile().getAreaTimerParameterList()) {
                if (areaTimerParameter.getName().equalsIgnoreCase(worldNameTest)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private AreaTimerParameter getAreaTimerUsingName(String worldNameTest) {

        for (AreaTimerParameter areaTimerParameter : Main.getDataProfile().getAreaTimerParameterList()) {
            if (areaTimerParameter.getName().equalsIgnoreCase(worldNameTest)) {
                return areaTimerParameter;
            }
        }
        return null;
    }
}
