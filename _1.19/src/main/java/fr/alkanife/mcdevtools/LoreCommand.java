package fr.alkanife.mcdevtools;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LoreCommand {

    public void register() {
        new CommandAPICommand("lore")
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("text"))
                .executes((commandSender, objects) -> {
                    if (!commandSender.isOp())
                        return;

                    Player player = (Player) objects[0];

                    ItemStack itemStack = player.getInventory().getItemInMainHand();

                    if (itemStack.getType().equals(Material.AIR))
                        return;

                    String content = (String) objects[1];
                    String[] lines = content.split("<newline>");

                    List<Component> components = new ArrayList<>();

                    for (String line : lines) {
                        Component component = Component.text("")
                                .color(TextColor.color(255, 255, 255)).decoration(TextDecoration.ITALIC, false)
                                .append(MiniMessage.miniMessage().deserialize(line));
                        components.add(component);
                    }

                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.lore(components);
                    itemStack.setItemMeta(itemMeta);
                }).register();
    }

}
