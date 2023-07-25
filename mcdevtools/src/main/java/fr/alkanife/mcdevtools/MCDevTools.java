package fr.alkanife.mcdevtools;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class MCDevTools extends JavaPlugin {

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).silentLogs(true));
    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable();

        new EchoCommands().register();
        new LoreCommand().register();
        new RenameCommand().register();
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }
}
