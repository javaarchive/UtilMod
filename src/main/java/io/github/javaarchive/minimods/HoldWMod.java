package io.github.javaarchive.minimods;

import io.github.javaarchive.Event;
import io.github.javaarchive.EventBus;
import io.github.javaarchive.MainMod;
import io.github.javaarchive.MiniModBase;
import io.github.javaarchive.VisualMessage;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class HoldWMod extends MiniModBase{
    // Holds W for you
    boolean toggled = false;
    KeyBinding keyBinding;
    boolean keyBindingLastState = false;



    @Override
    public void onLoadHook(MainMod mmod, EventBus evbus){
        evbus.subscribe(this);

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.jutilmod.autow", // The translation key of the keybinding's name
			InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_F7, // The keycode of the key
			"category.jutilmod.auto" // The translation key of the keybinding's category.
		));

        mmod.addVisualMessage(new VisualMessage("Auto-hold W","holdwactive"));
        mmod.disableText("holdwactive");

        System.out.println("Initalized Hold W Mod");


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(enabled && keyBindingLastState != keyBinding.isPressed() && !keyBinding.isPressed()){
                // release key
                System.out.println("Debug: " + mmod.screenMessages);
                toggled = !toggled;
                if(toggled == false){
                    client.options.keyForward.setPressed(false);
                }
                if(toggled){
                    mmod.enableText("holdwactive");
                }else{
                    mmod.disableText("holdwactive");
                }
            }

            keyBindingLastState = keyBinding.isPressed();

            if(toggled){
                client.options.keyForward.setPressed(true);
            }
        });
    }

    @Override
    public void processEvent(Event ev){

    }

}
