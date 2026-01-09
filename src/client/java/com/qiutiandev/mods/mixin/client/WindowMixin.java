package com.qiutiandev.mods.mixin.client;

import com.mojang.blaze3d.platform.Window;
import com.qiutiandev.mods.spoofer.FpsSpoofWarning;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Window.class)
public class WindowMixin {

    @ModifyArg(
        method = "setTitle",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/glfw/GLFW;glfwSetWindowTitle(JLjava/lang/CharSequence;)V"
        ),
        index = 1
    )
    private CharSequence modifyWindowTitle(CharSequence title) {
        if (FpsSpoofWarning.isSpoofingActive() && title != null) {
            String titleStr = title.toString();
            if (titleStr.toLowerCase().contains("fps") && !titleStr.contains("[SPOOFED]")) {
                return titleStr.replaceFirst("( fps)", "$1 [SPOOFED]");
            }
        }
        return title;
    }
}

