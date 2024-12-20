package org.wallentines.atimer;

import org.wallentines.mcore.Player;

public interface TimerDisplayManagerHolder {

    TimerDisplayManagerImpl getTimerDisplayManager();

    static TimerDisplayManager get(Player player) {
        return ((TimerDisplayManagerHolder) player).getTimerDisplayManager();
    }

}
