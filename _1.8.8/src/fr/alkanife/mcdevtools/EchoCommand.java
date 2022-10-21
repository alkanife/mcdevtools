package fr.alkanife.mcdevtools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EchoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.isOp())
            return true;

        if (strings.length == 0)
            return false;

        StringBuilder input = new StringBuilder();
        input.append(strings[0]);
        for (int i = 1; i < strings.length; i++)
            input.append(" ").append(strings[i]);

        Bukkit.broadcastMessage(input.toString().replaceAll("&", "§"));
        return true;
    }
}
