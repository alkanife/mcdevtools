package fr.alkanife.mcdevtools;

import org.bukkit.plugin.java.JavaPlugin;

public class MCDevTools extends JavaPlugin {

    @Override
    public void onEnable() {
        new EchoCommands().register();
        new LoreCommand().register();
        new RenameCommand().register();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
