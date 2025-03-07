package net.typicartist.talpius.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDispatcher {

    private Map<Class<?>, List<Object>> listeners = new HashMap<>();

    @Target(value = ElementType.METHOD)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface Subscriber {
        
    }



    public void dispatch(Object event) {
        listeners.get(event);
    }
}