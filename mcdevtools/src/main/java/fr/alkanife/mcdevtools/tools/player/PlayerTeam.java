package fr.alkanife.mcdevtools.tools.player;

import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import fr.alkanife.mcdevtools.Command;
import fr.alkanife.mcdevtools.Tool;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlayerTeam extends Tool {

    @Command
    public void prefixCommand() {
        createCommand("player_team_prefix", "Change player team prefix")
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    getPlayerTeam(player).prefix(getMinimessageAt(objects, 1));
                }).register();
    }

    @Command
    public void suffixCommand() {
        createCommand("player_team_suffix", "Change player team suffix")
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    getPlayerTeam(player).suffix(getMinimessageAt(objects, 1));
                }).register();
    }

    @Command
    public void colorCommand() {
        createCommand("player_team_color", "Change player team color")
                .withArguments(new PlayerArgument("player"), new StringArgument("color").replaceSuggestions(ArgumentSuggestions.stringCollection(suggestionInfo -> NamedTextColor.NAMES.keys())))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    getPlayerTeam(player).color(NamedTextColor.NAMES.value((String) Objects.requireNonNull(objects.get(1))));
                }).register();
    }

    @Command
    public void leaveTeam() {
        createCommand("player_leave_team", "Make a player leave their team")
                .withArguments(new PlayerArgument("player"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    getPlayerTeam(player).removePlayer(player);
                }).register();
    }

    // Get the player's team, create one if null
    private @NotNull Team getPlayerTeam(@NotNull Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getPlayerTeam(player);

        if (team == null) {
            team = scoreboard.getTeam("devtools_"+player.getName());

            if (team == null)
                team = scoreboard.registerNewTeam("devtools_"+player.getName());

            if (!team.hasPlayer(player))
                team.addPlayer(player);
        }

        return team;
    }

}
