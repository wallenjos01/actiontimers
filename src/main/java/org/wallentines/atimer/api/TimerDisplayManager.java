package org.wallentines.atimer.api;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;
import org.wallentines.atimer.impl.TimerDisplayManagerHolder;
import org.wallentines.pseudonym.Message;

public interface TimerDisplayManager {

    void addTimer(Timer timer, Message<Component> format);

    void addDisplay(TimerDisplay display);

    @Nullable
    static TimerDisplayManager get(ServerPlayer player) {
        return TimerDisplayManagerHolder.get(player);
    }

}
