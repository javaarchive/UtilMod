package io.github.javaarchive.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
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


@Mixin(InGameHud.class)
public class VisualMixin2 {
	
	@Inject(at = @At("HEAD"), method = "renderHotbar()V")
	private void onRender(float param1,MatrixStack matrices, CallbackInfo info) {
		MainMod mod = MainMod.getInstance();
		MinecraftClient mc = MinecraftClient.getInstance();
		TextRenderer tr = mc.textRenderer;
		// tr.drawWithShadow(matrices, "DEBUG: " + mod.screenMessages.size(), 0,0, 16777215);
		int yPos = 10;
		for(int i = 0; i < mod.screenMessages.size(); i ++){
			VisualMessage message = mod.screenMessages.get(i);
			if(message.hidden){
				continue; // skip
			}
			tr.drawWithShadow(matrices, message.messageText, 20, yPos, 16777215);
			yPos += 15;
		}
	}
}
