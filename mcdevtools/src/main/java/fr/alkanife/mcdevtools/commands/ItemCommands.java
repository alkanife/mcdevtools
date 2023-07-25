package fr.alkanife.mcdevtools.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import fr.alkanife.mcdevtools.DevTool;
import fr.alkanife.mcdevtools.ToolCollection;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemCommands extends ToolCollection {

    @DevTool
    public void item_name() {
        new CommandAPICommand(":item_name")
                .withFullDescription("Changes the name of the item the player is holding")
                .withPermission(CommandPermission.OP)
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));

                    ItemStack itemStack = player.getInventory().getItemInMainHand();

                    if (itemStack.getType().equals(Material.AIR))
                        return;

                    ItemMeta itemMeta = itemStack.getItemMeta();

                    Component component = Component.text("")
                            .color(TextColor.color(255, 255, 255)).decoration(TextDecoration.ITALIC, false)
                            .append(getMinimessageAt(objects, 1));
                    itemMeta.displayName(component);
                    itemStack.setItemMeta(itemMeta);
                }).register();
    }

    @DevTool
    public void item_lore() {
        new CommandAPICommand(":item_lore")
                .withFullDescription("Changes the lore of the item the player is holding")
                .withPermission(CommandPermission.OP)
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("mini_messages"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));

                    ItemStack itemStack = player.getInventory().getItemInMainHand();

                    if (itemStack.getType().equals(Material.AIR))
                        return;

                    String content = (String) Objects.requireNonNull(objects.get(1));
                    String[] lines = content.split("<newline>");

                    List<Component> components = new ArrayList<>();

                    for (String line : lines)
                        components.add(clearedText(MiniMessage.miniMessage().deserialize(line)));

                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.lore(components);
                    itemStack.setItemMeta(itemMeta);
                }).register();
    }

    @DevTool
    public void item_lore_at() {
        new CommandAPICommand(":item_lore_at")
                .withFullDescription("Changes the lore of the item the player is holding, specifying which line to change")
                .withPermission(CommandPermission.OP)
                .withArguments(new PlayerArgument("player"), new IntegerArgument("pos"), new GreedyStringArgument("mini_message"))
                .executes((commandSender, objects) -> {
                    Player player = (Player) Objects.requireNonNull(objects.get(0));

                    ItemStack itemStack = player.getInventory().getItemInMainHand();

                    if (itemStack.getType().equals(Material.AIR))
                        return;

                    int pos = (int) Objects.requireNonNull(objects.get(1));

                    ItemMeta itemMeta = itemStack.getItemMeta();

                    List<Component> newLore = new ArrayList<>();

                    int i = 0;
                    for (Component component : Objects.requireNonNull(itemMeta.lore())) {
                        if (pos == i)
                            newLore.add(clearedText(getMinimessageAt(objects, 2)));
                        else
                            newLore.add(component);

                        i++;
                    }

                    itemMeta.lore(newLore);
                    itemStack.setItemMeta(itemMeta);
                }).register();
    }

}
