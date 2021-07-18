package io.github.javaarchive;
import java.util.*;
// Yet another one

public class EventBus {
    List<EventBusSubscriber> subscribers = new LinkedList<EventBusSubscriber>();
    
    public void publish(Event ev){
        for(EventBusSubscriber subscriber : subscribers){
            if(!subscriber.caresAboutEvents()){
                continue;
            }
            subscriber.processEvent(ev);
            if(ev.isCancelled){
                return;
            }
        }
    }

    public void subscribe(EventBusSubscriber newSubscriber){
        subscribers.add(newSubscriber);
    }

    public void unsubscribe(EventBusSubscriber subscriber){
        subscribers.remove(subscriber);
    }

}
