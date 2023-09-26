package fr.alkanife.mcdevtools.tools.player;

import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import fr.alkanife.mcdevtools.Command;
import fr.alkanife.mcdevtools.Tool;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PlayerName extends Tool {

    @Command
    public void displayNameCommand() {
        createCommandTool("player_displayname", "Change player display name", "<player> <mini_message>")
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    player.displayName(getMinimessageAt(objects, 1));
                }).register();
    }

    @Command
    public void customNameCommand() {
        createCommandTool("player_customname", "Change player custom name", "<player> <mini_message>")
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    player.customName(getMinimessageAt(objects, 1));
                    player.setCustomNameVisible(true);
                }).register();
    }

    @Command
    public void listNameCommand() {
        createCommandTool("player_listname", "Change player list name", "<player> <mini_message>")
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    player.playerListName(getMinimessageAt(objects, 1));
                }).register();
    }

}
