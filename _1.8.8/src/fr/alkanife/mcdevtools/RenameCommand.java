package fr.alkanife.mcdevtools;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.isOp())
            return true;

        if (!(commandSender instanceof Player))
            return true;

        Player player = (Player) commandSender;

        if (strings.length == 0)
            return false;

        StringBuilder input = new StringBuilder();
        input.append(strings[0]);
        for (int i = 1; i < strings.length; i++)
            input.append(" ").append(strings[i]);

        ItemStack itemStack = player.getItemInHand();

        if (itemStack == null)
            return true;

        if (itemStack.getType().equals(Material.AIR))
            return true;

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(input.toString().replaceAll("&", "§"));
        itemStack.setItemMeta(itemMeta);

        return true;
    }
}
