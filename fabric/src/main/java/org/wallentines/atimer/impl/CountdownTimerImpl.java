package org.wallentines.atimer.impl;

import net.minecraft.server.MinecraftServer;
import org.wallentines.atimer.api.CountdownTimer;

public class CountdownTimerImpl extends TimerImpl implements CountdownTimer {

    private final int duration;

    public CountdownTimerImpl(TimerManager manager, int duration) {
        super(manager);
        this.duration = duration;

        onTick().register(timer -> {
            int remaining = this.duration - elapsed();
            if (remaining == 0) {
                stop();
            }
        });

    }

    @Override
    public int duration() {
        return duration;
    }

    @Override
    public int remaining() {
        return duration - elapsed();
    }

    @Override
    public int time() {
        return remaining();
    }

    public static CountdownTimer create(MinecraftServer server, int time) {
        return new CountdownTimerImpl(TimerManagerHolder.get(server), time);
    }
}
