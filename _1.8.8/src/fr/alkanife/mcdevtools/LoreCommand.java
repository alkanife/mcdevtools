package fr.alkanife.mcdevtools;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class LoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.isOp())
            return true;

        Player player = null;

        //Find player if executed by console
        if (!(commandSender instanceof Player)) {
            if (Bukkit.getOnlinePlayers().size() != 0) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    player = p;
                    break;
                }
            }
        } else player = (Player) commandSender;

        if (player == null)
            return true;

        if (strings.length == 0)
            return false;

        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append(strings[0]);
        for (int i = 1; i < strings.length; i++)
            contentBuilder.append(" ").append(strings[i]);

        String content = contentBuilder.toString().replaceAll("&", "§");
        String[] contentSplit = content.split("-n"); // Does not work with \n or \\n

        //Bukkit.broadcastMessage(contentSplit.length+"");

        ItemStack itemStack = player.getItemInHand();

        if (itemStack == null)
            return true;

        if (itemStack.getType().equals(Material.AIR))
            return true;

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(contentSplit));
        itemStack.setItemMeta(itemMeta);

        return true;
    }
}
