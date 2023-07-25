package fr.alkanife.mcdevtools;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

public class EchoCommands {

    public void register() {
        new CommandAPICommand("echo")
                .withAliases("e")
                .withArguments(new GreedyStringArgument("text"))
                .executes((commandSender, objects) -> {
                    if (!commandSender.isOp())
                        return;

                    Bukkit.broadcast(Component.text(((String) objects.args()[0]).replaceAll("&", "§")));
                }).register();

        new CommandAPICommand("echominimessage")
                .withAliases("em")
                .withArguments(new GreedyStringArgument("minimessage"))
                .executes((commandSender, objects) -> {
                    if (!commandSender.isOp())
                        return;

                    Bukkit.broadcast(MiniMessage.miniMessage().deserialize((String) objects.args()[0]));
                }).register();
    }

}
