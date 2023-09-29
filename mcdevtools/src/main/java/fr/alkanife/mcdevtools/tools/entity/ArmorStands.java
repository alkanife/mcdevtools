package fr.alkanife.mcdevtools.tools.entity;

import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.ListArgumentBuilder;
import fr.alkanife.mcdevtools.Command;
import fr.alkanife.mcdevtools.Tool;
import io.papermc.paper.math.Rotations;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.EulerAngle;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ArmorStands extends Tool {

    @SuppressWarnings("unchecked")
    @Command
    public void position() {
        createCommand("armorstand_position", "Show armorstand position & angles")
                .withArguments(
                        new EntitySelectorArgument.OneEntity("armorstand"),
                        new ListArgumentBuilder<String>("args")
                                .withList(Arrays.asList("*", "body", "head", "arms", "legs"))
                                .withStringMapper()
                                .buildGreedy()
                )
                .executes((commandSender, commandArguments) -> {
                    Entity entity = (Entity) Objects.requireNonNull(commandArguments.get(0));
                    List<String> args = (List<String>) Objects.requireNonNull(commandArguments.get(1));

                    if (!entity.getType().equals(EntityType.ARMOR_STAND))
                        return;

                    showPositions((ArmorStand) entity, args);
                }).register();

        createCommand("armorstand_position", "Show armorstand position & angles")
                .withArguments(
                        new EntitySelectorArgument.OneEntity("armorstand")
                )
                .executes((commandSender, commandArguments) -> {
                    Entity entity = (Entity) Objects.requireNonNull(commandArguments.get(0));

                    if (!entity.getType().equals(EntityType.ARMOR_STAND))
                        return;

                    showPositions((ArmorStand) entity, Collections.singletonList("*"));
                }).register();
    }

    private void showPositions(ArmorStand armorStand, List<String> args) {
        boolean joker = args.contains("*");

        Bukkit.broadcast(miniMessage("<gray>[<color:#dedede>").append(getEntityName(armorStand)).append(miniMessage("<gray>]:")));

        if (joker || args.contains("body")) {
            printRotations("Body", armorStand.getBodyRotations());
            printAngles("Body", armorStand.getBodyPose());
            printSpacer();
        }

        if (joker || args.contains("head")) {
            printRotations("Head", armorStand.getHeadRotations());
            printAngles("Head", armorStand.getHeadPose());
            printSpacer();
        }

        if (joker || args.contains("arms")) {
            printRotations("Left arm", armorStand.getLeftArmRotations());
            printAngles("Left arm", armorStand.getLeftArmPose());
            printSpacer();

            printRotations("Right arm", armorStand.getRightArmRotations());
            printAngles("Right arm", armorStand.getRightArmPose());
            printSpacer();
        }

        if (joker || args.contains("legs")) {
            printRotations("Left leg", armorStand.getLeftLegRotations());
            printAngles("Left leg", armorStand.getLeftLegPose());
            printSpacer();

            printRotations("Right leg", armorStand.getRightLegRotations());
            printAngles("Right leg", armorStand.getRightLegPose());
            printSpacer();
        }
    }

    private void printSpacer() {
        Bukkit.broadcast(miniMessage("<gray> -------------"));
    }

    private void printRotations(String name, Rotations rotations) {
        printValue(name + " rotations", rotations.x(), rotations.y(), rotations.z());
    }

    private void printAngles(String name, EulerAngle eulerAngle) {
        printValue(name + " pose", eulerAngle.getX(), eulerAngle.getY(), eulerAngle.getZ());
    }

    private void printValue(String name, double x, double y, double z) {
        DecimalFormat df = new DecimalFormat("#.###");
        StringBuilder string = new StringBuilder(" - <gray>").append(name).append(": ");

        string.append(axisValue("X", df.format(x)));
        string.append(", ");
        string.append(axisValue("Y", df.format(y)));
        string.append(", ");
        string.append(axisValue("Z", df.format(z)));

        Bukkit.broadcast(miniMessage(string.toString()));
    }

    private String axisValue(String axis, String value) {
        return "<color:#63d8e6>" + axis + "=<color:#f79681>" + value + "<color:#63d8e6>";
    }
}
