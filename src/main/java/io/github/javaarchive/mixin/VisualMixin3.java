package io.github.javaarchive.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.javaarchive.MainMod;
import io.github.javaarchive.VisualMessage;


@Mixin(GameRenderer.class)
public class VisualMixin3 {
	@Inject(at = @At("HEAD"), method = "renderWorld()V")
	private void onRender(float tickDelta, long limitTime, MatrixStack matrix, CallbackInfo info) {
		MainMod mod = MainMod.getInstance();
		MinecraftClient mc = MinecraftClient.getInstance();
		// mc.joinWorld(world);
	}
}
