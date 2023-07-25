package fr.alkanife.mcdevtools;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import fr.alkanife.mcdevtools.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

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

        // Declaring commands
        try {
            registerTools(new PlayerCommands(),
                    new OtherCommands(),
                    new ItemCommands());
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }

    // Invoke the method under the DevTool annotation for each class given
    private void registerTools(ToolCollection... toolCollections) throws InvocationTargetException, IllegalAccessException {
        for (ToolCollection toolCollection : toolCollections) {
            for (Method method : toolCollection.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(DevTool.class)) {
                    method.setAccessible(true);
                    method.invoke(toolCollection);
                }
            }
        }
    }
}
