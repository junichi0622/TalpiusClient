package net.typicartist.talpius.util;

import net.typicartist.talpius.event.listener.Adapter;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class TalpiusGlobals {
    
    private static final TalpiusGlobals INSTANCE = new TalpiusGlobals();

    public void preInit(final FMLCommonSetupEvent event) {

    }

    public void init(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(Adapter.getInstance());
    }

    public static TalpiusGlobals getInstance() {
        return INSTANCE;
    }
}
