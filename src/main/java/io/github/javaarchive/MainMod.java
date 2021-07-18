package io.github.javaarchive;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.world.ClientWorld;

import java.util.*;

import io.github.javaarchive.minimods.AutowaterMod;
import io.github.javaarchive.minimods.HoldWMod;

public class MainMod implements ModInitializer,ClientModInitializer {
	static MainMod instance;
	public static MainMod getInstance(){
		return instance;
	}

	public PriorityList<VisualMessage> screenMessages = new PriorityList<VisualMessage>(); // To render during gameplay
	public List<MiniMod> minimods = new LinkedList<MiniMod>();
	public EventBus evbus = new EventBus();
	public ClientWorld currentClientWorld;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		
	}

	public void disableText(String id){
		for(VisualMessage message : screenMessages){
			if(message.id.equals(id)){
				message.setHidden(true);
			}
		}
	}

	public void enableText(String id){
		for(VisualMessage message : screenMessages){
			if(message.id.equals(id)){
				message.setHidden(false);
			}
		}
	}

	public void addVisualMessage(VisualMessage visualMsg){
		screenMessages.add(visualMsg);
	}

	public VisualMessage getVisualMessage(String id){
		for(VisualMessage message : screenMessages){
			if(message.id.equals(id)){
				return message;
			}
		}
		return null;
	}

	@Override
	public void onInitializeClient(){
		instance = this;
		System.out.println("Client Entrypoint Activated");
		screenMessages.add(new VisualMessage("Utility Mod loaded","intro1"));
		// screenMessages.add(new VisualMessage("BIG PRIORITY","intro2",VisualMessage.HIGH));
		// screenMessages.add(new VisualMessage("LOW PRIORITY","intro3",VisualMessage.LOW));
		screenMessages.add(new VisualMessage("World Join Mixin Ran","worldjointest"));
		// Add mods
		System.out.println("Loading minimods");
		minimods.add(new HoldWMod());
		minimods.get(0).enable(); // testing currently
		AutowaterMod autoWaterTarget = new AutowaterMod();
		autoWaterTarget.enable();
		minimods.add(autoWaterTarget);
		// Init Mods
		System.out.println("Initalizing Minimods");
		for(MiniMod minimod: minimods){
			minimod.onLoadHook(this,evbus);
		}
		System.out.println("Minimods loaded and initalized");
		// Setup world join tester
		this.getVisualMessage("worldjointest").setHidden(true);

		// Keybinds

	}
}
