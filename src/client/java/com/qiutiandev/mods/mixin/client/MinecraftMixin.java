package com.qiutiandev.mods.mixin.client;

import com.qiutiandev.mods.FakeFpsClient;
import com.qiutiandev.mods.spoofer.FpsSpoofer;
import com.qiutiandev.mods.spoofer.FpsSpoofWarning;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Minecraft.class, priority = 1100)
public abstract class MinecraftMixin {

    @Shadow
    public abstract int getFps();

    @Inject(method = "getFps", at = @At("RETURN"), cancellable = true)
    private void modifyFps(CallbackInfoReturnable<Integer> cir) {
        int originalFps = cir.getReturnValue();
        int modifiedFps = FpsSpoofer.modifyFps(originalFps, FakeFpsClient.getConfig());
        cir.setReturnValue(modifiedFps);
    }

    @ModifyArg(
        method = "createTitle",
        at = @At(
            value = "INVOKE",
            target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            ordinal = 0
        ),
        index = 0
    )
    private String modifyFpsStringInTitle(String original) {
        if (FpsSpoofWarning.isSpoofingActive() && original != null && original.toLowerCase().contains("fps")) {
            return original.replaceFirst("( fps)", "$1 [SPOOFED]");
        }
        return original;
    }
}

