package org.wallentines.atimer;

import org.wallentines.mcore.Server;
import org.wallentines.midnightlib.event.HandlerList;

public class TimerImpl implements Timer {

    private final HandlerList<Timer> tickEvent = new HandlerList<>();
    private final Server server;

    private int ticks = 0;
    private int elapsed = 0;

    private boolean started = false;
    private boolean cancelled = false;

    public TimerImpl(Server server) {
        this.server = server;
    }

    @Override
    public HandlerList<Timer> onTick() {
        return tickEvent;
    }

    @Override
    public void start() {
        if(cancelled) return;

        if(!started) {
            onTick().invoke(this);
            server.tickEvent().register(this, srv -> {
                ticks++;
                if (ticks % 20 == 0) {
                    elapsed++;
                    onTick().invoke(this);
                }
            });
            started = true;
        }
    }

    @Override
    public void stop() {
        server.tickEvent().unregisterAll(this);
        started = false;
    }

    @Override
    public void cancel() {
        stop();
        tickEvent.unregisterAll();
        cancelled = true;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void restart() {
        elapsed = 0;
        start();
    }

    @Override
    public int elapsed() {
        return elapsed;
    }

    @Override
    public int time() {
        return elapsed;
    }
}
