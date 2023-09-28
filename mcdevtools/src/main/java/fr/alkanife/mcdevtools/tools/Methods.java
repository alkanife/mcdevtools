package fr.alkanife.mcdevtools.tools;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.ListArgument;
import dev.jorel.commandapi.arguments.ListArgumentBuilder;
import dev.jorel.commandapi.arguments.WorldArgument;
import fr.alkanife.mcdevtools.Command;
import fr.alkanife.mcdevtools.Tool;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Methods extends Tool {

    @SuppressWarnings("unchecked")
    @Command
    public void methods() {
        CommandAPICommand serverCommand = new CommandAPICommand("server")
                .withArguments(getAvailableMethods(Server.class))
                .executes((commandSender, commandArguments) -> {
                    List<String> methodNames = (List<String>) Objects.requireNonNull(commandArguments.get(0));

                    handleResult(Bukkit.getServer(), methodNames);
                });

        CommandAPICommand playerCommand = new CommandAPICommand("player")
                .withArguments(new EntitySelectorArgument.OnePlayer("player"), getAvailableMethods(Player.class))
                .executes((commandSender, commandArguments) -> {
                    Player player = (Player) Objects.requireNonNull(commandArguments.get(0));
                    List<String> methodNames = (List<String>) Objects.requireNonNull(commandArguments.get(1));

                    handleResult(player, methodNames);
                });

        CommandAPICommand entityCommand = new CommandAPICommand("entity")
                .withArguments(new EntitySelectorArgument.OneEntity("entity"), getAvailableMethods(Entity.class))
                .executes((commandSender, commandArguments) -> {
                    Entity entity = (Entity) Objects.requireNonNull(commandArguments.get(0));
                    List<String> methodNames = (List<String>) Objects.requireNonNull(commandArguments.get(1));

                    handleResult(entity, methodNames);
                });

        CommandAPICommand worldCommand = new CommandAPICommand("world")
                .withArguments(new WorldArgument("world"), getAvailableMethods(World.class))
                .executes((commandSender, commandArguments) -> {
                    World world = (World) Objects.requireNonNull(commandArguments.get(0));
                    List<String> methodNames = (List<String>) Objects.requireNonNull(commandArguments.get(1));

                    handleResult(world, methodNames);
                });

        createCommand("methods", "Execute some methods from a class")
                .withSubcommands(serverCommand, playerCommand, entityCommand, worldCommand)
                .register();
    }

    private ListArgument<?> getAvailableMethods(Class<?> clazz) {
        List<String> availableMethods = new ArrayList<>();
        for (Method method : clazz.getMethods()) {
            if (method.getParameterCount() == 0
                    && !method.isAnnotationPresent(Deprecated.class)) {
                availableMethods.add(method.getName() + "()");
            }
        }
        return new ListArgumentBuilder<String>("methods")
                .withList(availableMethods)
                .withStringMapper()
                .buildGreedy();
    }

    private void handleResult(Object object, List<String> methods) {
        Bukkit.broadcast(MiniMessage.miniMessage().deserialize("<gray>[<color:#dedede>" + object.getClass().getSimpleName()  + "<gray>]:"));
        for (Method method : object.getClass().getMethods()) {
            if (methods.contains(method.getName() + "()") && method.getParameterCount() == 0) {
                try {
                    Object o = method.invoke(object);

                    if (method.getReturnType().getName().equals("void"))
                        o = "<color:#dedede>(void method)";

                    Component objectComponent = MiniMessage.miniMessage().deserialize("<reset>"+o);

                    if (o instanceof Component component) {
                        objectComponent = component.append(MiniMessage.miniMessage().deserialize(" <dark_gray>(" + o + ")"));
                    }

                    Bukkit.broadcast(MiniMessage.miniMessage().deserialize("<gray> - <color:#f79681>" + method.getName() + "<gray>: <reset>").append(objectComponent));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Bukkit.broadcast(MiniMessage.miniMessage().deserialize("<gray>-----------------"));
    }

}
