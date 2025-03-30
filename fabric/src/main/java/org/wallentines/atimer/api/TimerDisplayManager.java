package org.wallentines.atimer.api;

import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;
import org.wallentines.atimer.impl.TimerDisplayManagerHolder;
import org.wallentines.pseudonym.UnresolvedMessage;

public interface TimerDisplayManager {

    void addTimer(Timer timer, UnresolvedMessage<String> format);

    void addDisplay(TimerDisplay display);

    @Nullable
    static TimerDisplayManager get(ServerPlayer player) {
        return TimerDisplayManagerHolder.get(player);
    }

}
