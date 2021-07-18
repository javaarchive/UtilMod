package io.github.javaarchive.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.javaarchive.MainMod;
import io.github.javaarchive.VisualMessage;
import io.github.javaarchive.WorldJoinEvent;


@Mixin(MinecraftClient.class)
public class InternalMixin {
	@Inject(at = @At("HEAD"), method = "joinWorld()V")
	private void onWorldJoin(ClientWorld world, CallbackInfo info) {
		MainMod mod = MainMod.getInstance();
		MinecraftClient mc = MinecraftClient.getInstance();
		System.out.println("New World Joined");
		mod.currentClientWorld = world;

		mod.evbus.publish(new WorldJoinEvent(world));

		// mc.joinWorld(world);
		mod.enableText("worldjointest");
	}
}
