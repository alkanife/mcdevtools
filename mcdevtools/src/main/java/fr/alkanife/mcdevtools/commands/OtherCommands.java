package fr.alkanife.mcdevtools.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import org.bukkit.Bukkit;

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

}
