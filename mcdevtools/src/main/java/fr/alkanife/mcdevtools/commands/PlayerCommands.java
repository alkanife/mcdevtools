package fr.alkanife.mcdevtools.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PlayerCommands extends ToolCollection {

    //
    // Name
    //
    @DevTool
    public void display_name() {
        new CommandAPICommand(":player_displayname")
                .withFullDescription("Changes player display name")
                .withPermission(CommandPermission.OP)
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    player.displayName(getMinimessageAt(objects, 1));
                }).register();
    }

    @DevTool
    public void custom_name() {
        new CommandAPICommand(":player_customname")
                .withFullDescription("Changes player custom name")
                .withPermission(CommandPermission.OP)
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    player.customName(getMinimessageAt(objects, 1));
                    player.setCustomNameVisible(true);
                }).register();
    }

    @DevTool
    public void tablist_name() {
        new CommandAPICommand(":player_listname")
                .withFullDescription("Changes player list name")
                .withPermission(CommandPermission.OP)
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    player.playerListName(getMinimessageAt(objects, 1));
                }).register();
    }

    //
    // Team
    //
    @DevTool
    public void team_prefix() {
        new CommandAPICommand(":player_team_prefix")
                .withFullDescription("Changes player team prefix")
                .withPermission(CommandPermission.OP)
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    getPlayerTeam(player).prefix(getMinimessageAt(objects, 1));
                }).register();
    }

    @DevTool
    public void team_suffix() {
        new CommandAPICommand(":player_team_suffix")
                .withFullDescription("Changes player team suffix")
                .withPermission(CommandPermission.OP)
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    getPlayerTeam(player).suffix(getMinimessageAt(objects, 1));
                }).register();
    }

    @DevTool
    public void team_color() {
        new CommandAPICommand(":player_team_color")
                .withFullDescription("Changes player team color")
                .withPermission(CommandPermission.OP)
                .withArguments(new PlayerArgument("player"), new StringArgument("color").replaceSuggestions(ArgumentSuggestions.stringCollection(suggestionInfo -> NamedTextColor.NAMES.keys())))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    getPlayerTeam(player).color(NamedTextColor.NAMES.value((String) Objects.requireNonNull(objects.get(1))));
                }).register();
    }
}
