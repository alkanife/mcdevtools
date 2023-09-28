package fr.alkanife.mcdevtools.tools;

import dev.jorel.commandapi.arguments.GreedyStringArgument;
import fr.alkanife.mcdevtools.Command;
import fr.alkanife.mcdevtools.Tool;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

public class Chat extends Tool {

    @Command
    public void echoCommand() {
        createCommand("echo", "Broadcasts input")
                .withArguments(new GreedyStringArgument("minimessage"))
                .executes((commandSender, objects) -> {
                    Bukkit.broadcast(getMinimessageAt(objects, 0));
                }).register();
    }

    @Command
    public void clearCommand() {
        createCommand("clear_chat", "Clear chat by sending empty messages")
                .executes((commandSender, commandArguments) -> {
                    Component empty = MiniMessage.miniMessage().deserialize("<white>");

                    for (int i = 0; i < 100; i++)
                        Bukkit.broadcast(empty);
                }).register();
    }
}
