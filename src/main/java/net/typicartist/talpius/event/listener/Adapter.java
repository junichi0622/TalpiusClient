package net.typicartist.talpius.event.listener;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.typicartist.talpius.event.EventDispatcher;

public class Adapter {

    private static final Adapter INSTANCE = new Adapter();

    private static final EventDispatcher dispatcher = EventDispatcher.getInstance();
    
    @SubscribeEvent
    public void onClientChat(ClientChatEvent event) {
        dispatcher.dispatch(event);
    }

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event) {
    
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {

    }
    
    public static Adapter getInstance() {
        return INSTANCE;
    }
    
}
