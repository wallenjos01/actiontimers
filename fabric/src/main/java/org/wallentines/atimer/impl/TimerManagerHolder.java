package org.wallentines.atimer.impl;

import net.minecraft.server.MinecraftServer;

public interface TimerManagerHolder {

    TimerManager getTimerManager();

    static TimerManager get(MinecraftServer server) {
        return ((TimerManagerHolder) server).getTimerManager();
    }

}
