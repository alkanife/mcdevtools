package fr.alkanife.mcdevtools;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

import java.util.Objects;

public class EchoCommands {

    public void register() {
        new CommandAPICommand("echo")
                .withFullDescription("Broadcast input")
                .withPermission(CommandPermission.OP)
                .withArguments(new GreedyStringArgument("minimessage"))
                .executes((commandSender, objects) -> {
                    Bukkit.broadcast(MiniMessage.miniMessage().deserialize((String) Objects.requireNonNull(objects.get(0))));
                }).register();
    }

}
