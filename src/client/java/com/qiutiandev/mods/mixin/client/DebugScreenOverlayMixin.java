package com.qiutiandev.mods.mixin.client;

import com.qiutiandev.mods.spoofer.FpsSpoofWarning;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.ArrayList;
import java.util.List;

@Mixin(DebugScreenOverlay.class)
public class DebugScreenOverlayMixin {

    @ModifyArg(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/components/DebugScreenOverlay;renderLines(Lnet/minecraft/client/gui/GuiGraphics;Ljava/util/List;Z)V",
            ordinal = 0
        ),
        index = 1
    )
    private List<String> modifyLeftDebugText(List<String> lines) {
        if (FpsSpoofWarning.isSpoofingActive() && lines != null) {
            List<String> modified = new ArrayList<>(lines);
            for (int i = 0; i < modified.size(); i++) {
                String line = modified.get(i);
                if (line != null && line.toLowerCase().contains(" fps")) {
                    String modifiedLine = line.replaceFirst("( fps)", "$1 [SPOOFED]");
                    modified.set(i, modifiedLine);
                    break;
                }
            }
            return modified;
        }
        return lines;
    }
}

