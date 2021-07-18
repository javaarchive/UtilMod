package io.github.javaarchive.minimods;
import io.github.javaarchive.Event;
import io.github.javaarchive.EventBus;
import io.github.javaarchive.MainMod;
import io.github.javaarchive.MiniModBase;
import io.github.javaarchive.VisualMessage;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import org.lwjgl.glfw.GLFW;

public class AutowaterMod extends MiniModBase{
    // Holds W for you
    boolean toggled = false;
    KeyBinding keyBinding;
    boolean keyBindingLastState = false;
    BlockPos targetingBlock = new BlockPos(0,0,0);
    boolean found = false;

    @Override
    public void onLoadHook(MainMod mmod, EventBus evbus){
        evbus.subscribe(this);

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.jutilmod.autotargetwater", // The translation key of the keybinding's name
			InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_F6, // The keycode of the key
			"category.jutilmod.auto" // The translation key of the keybinding's category.
		));

        mmod.addVisualMessage(new VisualMessage("Auto Water Targetting","autotargetwater"));
        mmod.disableText("autotargetwater");

        System.out.println("Initalized Water Autotarget");


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(!this.enabled){
                mmod.disableText("autotargetwater");
                return;
            }else{
                mmod.enableText("autotargetwater");
            }
            if(client.world == null){
                toggled = false;
            }
            if(keyBindingLastState != keyBinding.isPressed() && !keyBinding.isPressed()){
                // release key
                toggled = !toggled;
                System.out.println("Autowater finding state: " + toggled);
                if(toggled == false){
                    client.options.keyForward.setPressed(false);
                }
                if(toggled == true){
                    // Search for water
                    ClientWorld cw = mmod.currentClientWorld;
                    found = false;
                    BlockPos foundPos = new BlockPos(0,0,0); // placeholder
                    for(int xDelta = -7; xDelta < 7; xDelta ++){
                        for(int zDelta = -7; zDelta < 7; zDelta ++){
                            for(int yLevel = (int)  client.player.getY(); yLevel >= 0; yLevel --){
                                // TODO: Improve locating
                                BlockPos bPos = new BlockPos(new Vec3d(client.player.getX() + xDelta, yLevel, client.player.getZ() + zDelta));
                                BlockState bs = cw.getBlockState(bPos);
                                if(bs.isAir()){
                                    continue;
                                }
                                if(bs.isOf(Blocks.WATER)){
                                    foundPos = bPos;
                                    found = true;
                                    break;
                                }else{
                                    break;
                                }
                            }
                            if(found){
                                break;
                            }
                        }
                        if(found){
                            break;
                        }
                    }
                    if(found){
                        mmod.getVisualMessage("autotargetwater").setText("autotargetwater: Acquired Water at " + foundPos.getX() + " " + foundPos.getY() + " " + foundPos.getZ());
                        targetingBlock = foundPos;
                        System.out.println("Water found, targetting!");
                    }else{
                        System.out.println("Autotarget Failed to find water");
                        mmod.getVisualMessage("autotargetwater").setText("autotargetwater: Could not target block");
                    }

                }
                

                if(toggled){
                    mmod.enableText("autotargetwater");
                }else{
                    mmod.disableText("autotargetwater");
                }

                
            }

            if(found && toggled){
                client.player.lookAt(EntityAnchor.EYES, new Vec3d(targetingBlock.getX() + 0.5, targetingBlock.getY(), targetingBlock.getZ() + 0.5));
                client.options.keyForward.setPressed(true); // move forward
            }

            keyBindingLastState = keyBinding.isPressed();
        });
    }

    @Override
    public void processEvent(Event ev){

    }

}
