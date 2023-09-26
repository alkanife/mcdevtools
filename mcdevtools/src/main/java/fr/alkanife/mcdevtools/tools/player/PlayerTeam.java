package fr.alkanife.mcdevtools.tools.player;

import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import fr.alkanife.mcdevtools.Command;
import fr.alkanife.mcdevtools.Tool;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PlayerTeam extends Tool {

    @Command
    public void prefixCommand() {
        createCommandTool("player_team_prefix", "Change player team prefix", "<player> <mini_message>")
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    getPlayerTeam(player).prefix(getMinimessageAt(objects, 1));
                }).register();
    }

    @Command
    public void suffixCommand() {
        createCommandTool("player_team_suffix", "Change player team suffix", "<player> <mini_message>")
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    getPlayerTeam(player).suffix(getMinimessageAt(objects, 1));
                }).register();
    }

    @Command
    public void colorCommand() {
        createCommandTool("player_team_color", "Change player team color", "<player> <color>")
                .withArguments(new PlayerArgument("player"), new StringArgument("color").replaceSuggestions(ArgumentSuggestions.stringCollection(suggestionInfo -> NamedTextColor.NAMES.keys())))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    getPlayerTeam(player).color(NamedTextColor.NAMES.value((String) Objects.requireNonNull(objects.get(1))));
                }).register();
    }

}
