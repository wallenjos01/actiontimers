package org.wallentines.atimer;

import org.wallentines.mcore.Server;

public class CountdownTimerImpl extends TimerImpl implements CountdownTimer {

    private final int duration;

    public CountdownTimerImpl(Server server, int duration) {
        super(server);
        this.duration = duration;

        onTick().register(this, timer -> {
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
}
