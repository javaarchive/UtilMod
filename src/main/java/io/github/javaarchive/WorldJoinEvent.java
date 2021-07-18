package io.github.javaarchive;

import net.minecraft.client.world.ClientWorld;

public class WorldJoinEvent extends Event{
    ClientWorld clientWorld;
    public WorldJoinEvent(ClientWorld world){
        super();
        this.clientWorld = world;
    }
}
