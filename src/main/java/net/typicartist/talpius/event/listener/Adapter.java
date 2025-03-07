package net.typicartist.talpius.event.listener;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Adapter {

    private static final Adapter INSTANCE = new Adapter();
    
    @SubscribeEvent
    public void onClientChat(ClientChatEvent event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event) {
    
    }

    public static Adapter getInstance() {
        return INSTANCE;
    }
}
