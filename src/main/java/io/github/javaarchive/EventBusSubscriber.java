package io.github.javaarchive;

public interface EventBusSubscriber {
    public void processEvent(Event e);
    public boolean caresAboutEvents();
}
