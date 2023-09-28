package fr.alkanife.mcdevtools.tools.entity;

import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;

import dev.jorel.commandapi.arguments.GreedyStringArgument;
import fr.alkanife.mcdevtools.Command;
import fr.alkanife.mcdevtools.Tool;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Objects;

public class EntityLocation extends Tool {

    @SuppressWarnings("unchecked")
    @Command
    public void entityLocation() {
        createCommand("entity_location", "Show entity location")
                .withArguments(new EntitySelectorArgument.ManyEntities("entities"))
                .executes((commandSender, arguments) -> {
                    Collection<Entity> entity = (Collection<Entity>) Objects.requireNonNull(arguments.get(0));
                    entity.forEach(e -> sendLocation(e, "world, x, y, z, yaw, pitch"));
                }).register();

        createCommand("entity_location", "Show entity location")
                .withArguments(new EntitySelectorArgument.ManyEntities("entities"), new GreedyStringArgument("format").replaceSuggestions(ArgumentSuggestions.strings(
                        "world, x, y, z, yaw, pitch",
                                    "x, y, z",
                                    "x, y, z, yaw, pitch",
                                    "X.5, Y.15, Z.5, yaw, pitch",
                                    "world, X.5, Y.15, Z.5",
                                    "X.5, Y.15, Z.5"
                )))
                .executes((commandSender, arguments) -> {
                    Collection<Entity> entity = (Collection<Entity>) Objects.requireNonNull(arguments.get(0));
                    String format = (String) Objects.requireNonNull(arguments.get(1));

                    entity.forEach(e -> sendLocation(e, format));
                }).register();
    }

    private void sendLocation(Entity entity, String format) {
        Location location = entity.getLocation();

        StringBuilder message = new StringBuilder("<gray>Location of <color:#dedede>" + entity.getName() + "<gray>: <color:#63d8e6>[");

        DecimalFormat df = new DecimalFormat("#.###");
        DecimalFormat sdf = new DecimalFormat("#");

        String formatted = format.replace("x", df.format(location.getX()))
                .replace("yaw", sdf.format(location.getYaw()))
                .replace("pitch", sdf.format(location.getPitch()))
                .replace("y", df.format(location.getY()))
                .replace("z", df.format(location.getZ()))
                .replace("X", sdf.format(location.getX()))
                .replace("Y", sdf.format(location.getY()))
                .replace("Z", sdf.format(location.getZ()))
                .replace("world", location.getWorld().getName());

        message.append(formatted).append("]");

        Component component = MiniMessage.miniMessage().deserialize(message.toString())
                .hoverEvent(Component.text("Click to copy").asHoverEvent())
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, formatted));

        Bukkit.broadcast(component);
    }

}
