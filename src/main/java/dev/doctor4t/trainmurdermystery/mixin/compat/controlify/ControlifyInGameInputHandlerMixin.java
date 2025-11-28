package dev.doctor4t.trainmurdermystery.mixin.compat.controlify;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.doctor4t.trainmurdermystery.client.TMMClient;
import dev.doctor4t.trainmurdermystery.client.gui.screen.ingame.LimitedInventoryScreen;
import dev.isxander.controlify.ingame.InGameInputHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InGameInputHandler.class)
public class ControlifyInGameInputHandlerMixin {
    @Shadow
    @Final
    private MinecraftClient minecraft;

    @WrapOperation(method = "handleKeybinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal = 1))
    private void tmm$replaceControlifyInventoryScreenWithLimitedInventoryScreen(MinecraftClient instance, Screen screen, Operation<Void> original) {
        if (TMMClient.gameComponent.getFade() > 0) {
            return;
        }

        original.call(instance, TMMClient.isPlayerAliveAndInSurvival() ? new LimitedInventoryScreen(this.minecraft.player) : screen);
    }
}
