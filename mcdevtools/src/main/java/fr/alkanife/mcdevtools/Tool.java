package fr.alkanife.mcdevtools;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.executors.CommandArguments;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class Tool {

    private MCDevTools plugin;

    public MCDevTools getPlugin() {
        return plugin;
    }

    public void setPlugin(MCDevTools plugin) {
        this.plugin = plugin;
    }

    public CommandAPICommand createCommand(String name, String description) {
        return new CommandAPICommand(":" + name)
                .withHelp(description, description)
                .withPermission(CommandPermission.OP);
    }

    public @NotNull Component getMinimessageAt(CommandArguments commandArguments, int i) {
        return MiniMessage.miniMessage().deserialize((String) Objects.requireNonNull(commandArguments.get(i)));
    }

    public @NotNull Component getEntityName(@NotNull Entity entity) {
        return entity.name()
                .colorIfAbsent(TextColor.fromCSSHexString("#dedede"))
                .hoverEvent(Component.text(entity.getUniqueId().toString()).asHoverEvent())
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, entity.getUniqueId().toString()));
    }

    public @NotNull Component miniMessage(String s) {
        return MiniMessage.miniMessage().deserialize(s);
    }
}
