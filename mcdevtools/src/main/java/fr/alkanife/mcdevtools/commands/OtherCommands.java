package fr.alkanife.mcdevtools.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import fr.alkanife.mcdevtools.DevTool;
import fr.alkanife.mcdevtools.ToolCollection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OtherCommands extends ToolCollection {

    @DevTool
    public void echo() {
        new CommandAPICommand(":echo")
                .withFullDescription("Broadcasts input")
                .withPermission(CommandPermission.OP)
                .withArguments(new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Bukkit.broadcast(getMinimessageAt(objects, 0));
                }).register();
    }

    @DevTool
    public void tablist_header() {
        new CommandAPICommand(":tablist_header")
                .withFullDescription("Changes player list header")
                .withPermission(CommandPermission.OP)
                .withArguments(new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    for (Player player : Bukkit.getOnlinePlayers())
                        player.sendPlayerListHeader(getMinimessageAt(objects, 0));
                }).register();
    }

    @DevTool
    public void tablist_footer() {
        new CommandAPICommand(":tablist_footer")
                .withFullDescription("Changes player list footer")
                .withPermission(CommandPermission.OP)
                .withArguments(new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    for (Player player : Bukkit.getOnlinePlayers())
                        player.sendPlayerListFooter(getMinimessageAt(objects, 0));
                }).register();
    }

}
