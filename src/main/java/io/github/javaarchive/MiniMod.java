package io.github.javaarchive;


public interface MiniMod {
    // Minimod
    void onLoadHook(MainMod mmod, EventBus evbus);

    void disable();
    void enable();
}
