package fr.alkanife.mcdevtools.tools.player;

import dev.jorel.commandapi.arguments.PlayerArgument;
import fr.alkanife.mcdevtools.Command;
import fr.alkanife.mcdevtools.Tool;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PlayerFly extends Tool {

    @Command
    public void flyCommand() {
        createCommand("player_fly", "Toggle player flight")
                .withArguments(new PlayerArgument("player"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));
                    toggle(player);
                }).register();

        createCommand("player_fly", "Toggle player flight")
                .executesPlayer((commandSender, objects) -> {
                    toggle(commandSender);
                }).register();
    }

    private void toggle(Player player) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
        }
    }

}
