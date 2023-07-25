package fr.alkanife.mcdevtools;

import dev.jorel.commandapi.executors.CommandArguments;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class ToolCollection {

    public @NotNull Component getMinimessageAt(CommandArguments commandArguments, int i) {
        return MiniMessage.miniMessage().deserialize((String) Objects.requireNonNull(commandArguments.get(i)));
    }

    // Get the player's team, create one if null
    public @NotNull Team getPlayerTeam(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getPlayerTeam(player);

        if (team == null) {
            team = scoreboard.getTeam("d_"+player.getName());

            if (team == null)
                team = scoreboard.registerNewTeam("d_"+player.getName());

            if (!team.hasPlayer(player))
                team.addPlayer(player);
        }

        return team;
    }

    public Component clearedText(Component component) {
        return Component.text("")
                .color(TextColor.color(255, 255, 255)).decoration(TextDecoration.ITALIC, false)
                .append(component);
    }
}
