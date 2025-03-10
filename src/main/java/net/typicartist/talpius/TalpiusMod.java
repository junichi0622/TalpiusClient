package net.typicartist.talpius;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.typicartist.talpius.util.TalpiusGlobals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TalpiusMod.ID)
public class TalpiusMod {
    
    public static final String ID = "talpius";
    public static final String NAME = "Talpius";
    public static final String VERSION = "b0.0.0";

    public static final Logger LOGGER = LogManager.getLogger();

    public TalpiusMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::onClientSetup);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        TalpiusGlobals.getInstance().preInit(event);
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        TalpiusGlobals.getInstance().init(event);
    }
    
}