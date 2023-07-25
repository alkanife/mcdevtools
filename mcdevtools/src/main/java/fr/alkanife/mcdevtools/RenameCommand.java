package fr.alkanife.mcdevtools;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.StyleBuilderApplicable;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand {

    public void register() {
        new CommandAPICommand("renameitem")
                .withAliases("ri")
                .withArguments(new PlayerArgument("player"), new GreedyStringArgument("text"))
                .executes((commandSender, objects) -> {
                    if (!commandSender.isOp())
                        return;

                    Player player = (Player) objects.args()[0];

                    ItemStack itemStack = player.getInventory().getItemInMainHand();

                    if (itemStack.getType().equals(Material.AIR))
                        return;

                    ItemMeta itemMeta = itemStack.getItemMeta();

                    Component component = Component.text("")
                            .color(TextColor.color(255, 255, 255)).decoration(TextDecoration.ITALIC, false)
                            .append(MiniMessage.miniMessage().deserialize((String) objects.args()[1]));
                    itemMeta.displayName(component);
                    itemStack.setItemMeta(itemMeta);
                }).register();
    }
}
