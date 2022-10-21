package fr.alkanife.mcdevtools;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class MCDevTools extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginCommand echoCommand = getCommand("echo");
        echoCommand.setAliases(Collections.singletonList("e"));
        echoCommand.setExecutor(new EchoCommand());

        PluginCommand renameCommand = getCommand("rename");
        renameCommand.setExecutor(new RenameCommand());

        PluginCommand loreCommand = getCommand("lore");
        loreCommand.setExecutor(new LoreCommand());

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
