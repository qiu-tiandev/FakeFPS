package com.qiutiandev.mods.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.qiutiandev.mods.config.FpsConfig;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.network.chat.Component;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class FpsSpoofCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, FpsConfig config) {
        dispatcher.register(literal("fpsspoof")
            .then(literal("enable")
                .executes(context -> enable(context, config)))
            .then(literal("disable")
                .executes(context -> disable(context, config)))
            .then(literal("toggle")
                .executes(context -> toggle(context, config)))
            .then(literal("multiplier")
                .then(argument("value", DoubleArgumentType.doubleArg(0.1, 10000))
                    .executes(context -> setMultiplier(context, config))))
            .then(literal("fluctuation")
                .then(argument("percent", DoubleArgumentType.doubleArg(0.0, 99))
                    .executes(context -> setFluctuation(context, config))))
            .then(literal("reset")
                .executes(context -> reset(context, config)))
            .then(literal("status")
                .executes(context -> status(context, config)))
            .executes(context -> help(context))
        );
    }

    private static int enable(CommandContext<FabricClientCommandSource> context, FpsConfig config) {
        config.setEnabled(true);
        context.getSource().sendFeedback(Component.literal("§aEnabled §7(x" + String.format("%.1f", config.getFpsMultiplier()) + ")"));
        return 1;
    }

    private static int disable(CommandContext<FabricClientCommandSource> context, FpsConfig config) {
        config.setEnabled(false);
        context.getSource().sendFeedback(Component.literal("§cDisabled"));
        return 1;
    }

    private static int toggle(CommandContext<FabricClientCommandSource> context, FpsConfig config) {
        config.setEnabled(!config.isEnabled());
        String status = config.isEnabled() ? "§aEnabled" : "§cDisabled";
        context.getSource().sendFeedback(Component.literal(status));
        return 1;
    }

    private static int setMultiplier(CommandContext<FabricClientCommandSource> context, FpsConfig config) {
        double value = DoubleArgumentType.getDouble(context, "value");
        config.setFpsMultiplier(value);
        context.getSource().sendFeedback(Component.literal("§eMultiplier: §fx" + String.format("%.1f", value)));
        return 1;
    }

    private static int setFluctuation(CommandContext<FabricClientCommandSource> context, FpsConfig config) {
        double percent = DoubleArgumentType.getDouble(context, "percent");
        config.setFluctuationRange(percent / 100.0);
        context.getSource().sendFeedback(Component.literal("§eFluctuation: §f±" + String.format("%.1f", percent) + "%"));
        return 1;
    }

    private static int reset(CommandContext<FabricClientCommandSource> context, FpsConfig config) {
        config.setEnabled(false);
        config.setFpsMultiplier(1.0);
        config.setFluctuationRange(0.05);
        context.getSource().sendFeedback(Component.literal("§7Reset to defaults"));
        return 1;
    }

    private static int status(CommandContext<FabricClientCommandSource> context, FpsConfig config) {
        String enabled = config.isEnabled() ? "§aOn" : "§cOff";
        String mult = String.format("%.1f", config.getFpsMultiplier());
        String fluct = String.format("%.1f", config.getFluctuationRange() * 100);

        context.getSource().sendFeedback(Component.literal(
            "§6FPS Spoofer\n" +
            "  §7Status: " + enabled + "\n" +
            "  §7Multiplier: §fx" + mult + "\n" +
            "  §7Fluctuation: §f±" + fluct + "%"
        ));
        return 1;
    }

    private static int help(CommandContext<FabricClientCommandSource> context) {
        context.getSource().sendFeedback(Component.literal(
            "§6FPS Spoofer Commands\n" +
            "  §e/fpsspoof enable §7- Enable\n" +
            "  §e/fpsspoof disable §7- Disable\n" +
            "  §e/fpsspoof toggle §7- Toggle\n" +
            "  §e/fpsspoof multiplier <value> §7- Set multiplier (0.1-10000)\n" +
            "  §e/fpsspoof fluctuation <percent> §7- Set variation (0-99%)\n" +
            "  §e/fpsspoof reset §7- Reset defaults\n" +
            "  §e/fpsspoof status §7- Show settings"
        ));
        return 1;
    }
}

