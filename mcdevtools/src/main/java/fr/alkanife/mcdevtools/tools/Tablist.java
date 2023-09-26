package fr.alkanife.mcdevtools.tools;

import dev.jorel.commandapi.arguments.GreedyStringArgument;
import fr.alkanife.mcdevtools.Command;
import fr.alkanife.mcdevtools.Tool;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Tablist extends Tool implements Listener {

    private Component header, footer;

    @Command
    public void headerCommand() {
        createCommandTool("tablist_header", "Changes tab list header", "<mini_message>")
                .withArguments(new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    header = getMinimessageAt(objects, 0);

                    for (Player player : Bukkit.getOnlinePlayers())
                        refreshTab(player);
                }).register();
    }

    @Command
    public void footerCommand() {
        createCommandTool("tablist_footer", "Changes tab list footer", "<mini_message>")
                .withArguments(new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    footer = getMinimessageAt(objects, 0);

                    for (Player player : Bukkit.getOnlinePlayers())
                        player.sendPlayerListFooter(getMinimessageAt(objects, 0));
                }).register();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        refreshTab(event.getPlayer());
    }

    private void refreshTab(Player player) {
        player.sendPlayerListHeaderAndFooter(header, footer);
    }

}
