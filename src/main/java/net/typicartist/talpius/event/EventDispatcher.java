package net.typicartist.talpius.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDispatcher {

    private Map<String, List<Object>> listeners = new HashMap<>();

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Subscriber {

    }

    public void register(Object listener) {
        
    }
}