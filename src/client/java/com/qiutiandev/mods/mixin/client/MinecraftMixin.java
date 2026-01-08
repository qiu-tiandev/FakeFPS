package com.qiutiandev.mods.mixin.client;

import com.qiutiandev.mods.FakeFpsClient;
import com.qiutiandev.mods.spoofer.FpsSpoofer;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    public abstract int getFps();

    @Inject(method = "getFps", at = @At("RETURN"), cancellable = true) //force change fps
    private void modifyFps(CallbackInfoReturnable<Integer> cir) {
        int originalFps = cir.getReturnValue();
        int modifiedFps = FpsSpoofer.modifyFps(originalFps, FakeFpsClient.getConfig());
        cir.setReturnValue(modifiedFps);
    }
}

