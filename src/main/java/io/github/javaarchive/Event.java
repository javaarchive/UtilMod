package io.github.javaarchive;

public class Event {
    // Subclass this
    boolean isCancelled = false;

    public void cancel(){
        this.isCancelled = true;
    }

    public void setCancelled(boolean isCancelled){
        this.isCancelled = isCancelled;
    }
}
