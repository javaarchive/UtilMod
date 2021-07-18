package io.github.javaarchive;

public class MiniModBase implements MiniMod, EventBusSubscriber {

    protected boolean enabled = false;

    @Override
    public void onLoadHook(MainMod mmod, EventBus evbus) {
        // Register self for event bus
        evbus.subscribe(this);
    }

    @Override
    public void disable() {
        this.enabled = false;
    }

    @Override
    public void enable() {
        this.enabled = true;
    }

    @Override
    public void processEvent(Event e) {
        // TODO: Override in subclass
    }

    @Override
    public boolean caresAboutEvents() {
        return this.enabled;
    }
    
}
