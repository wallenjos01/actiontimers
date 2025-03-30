package org.wallentines.atimer.impl;

import net.minecraft.server.level.ServerPlayer;
import org.wallentines.atimer.api.TimerDisplayManager;

public interface TimerDisplayManagerHolder {

    TimerDisplayManagerImpl getTimerDisplayManager();

    static TimerDisplayManager get(ServerPlayer player) {
        return ((TimerDisplayManagerHolder) player).getTimerDisplayManager();
    }

}
