package fr.alkanife.mcdevtools;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import fr.alkanife.mcdevtools.tools.Chat;
import fr.alkanife.mcdevtools.tools.Items;
import fr.alkanife.mcdevtools.tools.Methods;
import fr.alkanife.mcdevtools.tools.Tablist;
import fr.alkanife.mcdevtools.tools.entity.EntityLocation;
import fr.alkanife.mcdevtools.tools.player.PlayerFly;
import fr.alkanife.mcdevtools.tools.player.PlayerName;
import fr.alkanife.mcdevtools.tools.player.PlayerTeam;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MCDevTools extends JavaPlugin {


    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).silentLogs(true));
    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable();

        // Register tools
        try {
            registerTool(new Tablist());
            registerTool(new Chat());
            registerTool(new Items());
            registerTools(new PlayerFly(), new PlayerName(), new PlayerTeam());
            registerTools(new EntityLocation());
            registerTools(new Methods());
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }

    private void registerTools(@NotNull Tool... tools) throws InvocationTargetException, IllegalAccessException {
        for (Tool tool : tools)
            registerTool(tool);
    }

    private void registerTool(@NotNull Tool tool) throws InvocationTargetException, IllegalAccessException {
        tool.setPlugin(this);

        // Invoke command methods
        for (Method method : tool.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                method.setAccessible(true);
                method.invoke(tool);
            }
        }

        // Register events
        for (Class<?> interfaces : tool.getClass().getInterfaces()) {
            if (interfaces == Listener.class) {
                Bukkit.getPluginManager().registerEvents((Listener) tool, this);
                break;
            }
        }
    }
}
