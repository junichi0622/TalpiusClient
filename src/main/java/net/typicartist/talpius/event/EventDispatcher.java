package net.typicartist.talpius.event;

import java.lang.annotation.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EventDispatcher {

    private static final EventDispatcher INSTANCE = new EventDispatcher();

    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private final Map<Class<?>, Set<EventHandler>> eventHandlers = new ConcurrentHashMap<>();

    @Target(value = ElementType.METHOD)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface Subscriber {
        int priority() default 0;
    }

    public <T> void dispatch(T event) {
        Set<Class<?>> eventClasses = new HashSet<>();
        Class<?> eventClass = event.getClass();

        while (eventClass != Object.class) {
            eventClasses.add(eventClass);
            eventClass = eventClass.getSuperclass();
        }

        if (eventClass != null) {
            Collections.addAll(eventClasses, eventClass.getInterfaces());
        }

        for (Class<?> eventType : eventClasses) {
            Set<EventHandler> handlers = eventHandlers.get(eventType);
            if (handlers != null) {
                PriorityQueue<EventHandler> queue = new PriorityQueue<>(Comparator.comparingInt(handler -> -handler.priority));
                for (EventHandler handler : handlers) {
                    if (handler.isActive()) {
                        queue.add(handler);
                    }
                }

                while (!queue.isEmpty()) {
                    queue.poll().invoke(event);
                }
            }
        }
    }

    public <T> void register(Class<T> eventType, EventConsumer<T> consumer, int priority) {
        eventHandlers.computeIfAbsent(eventType, k -> new HashSet<>()).add(new EventHandler(consumer, priority));
    }

    public void register(Object subscriber) {
        for (Method method : subscriber.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscriber.class)) {
                if (method.getReturnType() != void.class || method.getParameterCount() != 1) {
                    System.err.println("Invalid event handler method: " + method);
                    continue;
                }

                Subscriber annotation = method.getAnnotation(Subscriber.class);
                Class<?> eventType = method.getParameterTypes()[0];

                try {
                    MethodHandle handle = LOOKUP.unreflect(method);
                    eventHandlers.computeIfAbsent(eventType, k -> new HashSet<>()).add(new EventHandler(subscriber, handle, annotation.priority()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void subscribe(Object subscriber) {
        setActiveForSubscriber(subscriber, true);
    }

    public void unsubscribe(Object subscriber) {
        setActiveForSubscriber(subscriber, false);
    }

    private void setActiveForSubscriber(Object subscriber, boolean active) {
        for (Set<EventHandler> handlers : eventHandlers.values()) {
            for (EventHandler handler : handlers) {
                if (handler.getSubscriber() == subscriber) {
                    handler.setActive(active);
                }
            }
        }
    }

    public static EventDispatcher getInstance() {
        return INSTANCE;
    }

    public interface EventConsumer<T> {
        void accept(T event);
    }    

    private class EventHandler {

        private final Object subscriber;
        private final MethodHandle handle;
        private final EventConsumer<Object> consumer;
        private final int priority;
        private boolean active = false;

        public EventHandler(Object subscriber, MethodHandle handle, int priority) {
            this.subscriber = subscriber;
            this.handle = handle;
            this.consumer = null;
            this.priority = priority;
        }

        @SuppressWarnings("unchecked")
        public EventHandler(EventConsumer<?> consumer, int priority) {
            this.subscriber = consumer;
            this.handle = null;
            this.consumer = (EventConsumer<Object>) consumer;
            this.priority = priority;
        }

        public Object getSubscriber() {
            return subscriber;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public void invoke(Object event) {
            try {
                if (consumer != null) {
                    consumer.accept(event);
                } else {
                    handle.bindTo(subscriber).invoke(event);
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();;
            }
        } 

    }
}