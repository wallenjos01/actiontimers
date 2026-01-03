package org.wallentines.atimer.impl;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import org.wallentines.atimer.api.Timer;

public class TimerImpl implements Timer {

    public static final Identifier LATE_PHASE = Identifier.tryBuild("atimer", "late");

    private final Event<Tick> tickEvent = EventFactory.createArrayBacked(Timer.Tick.class, listeners -> (timer) -> {
        for (Timer.Tick tick : listeners) {
            tick.tick(timer);
        }
    });

    private final TimerManager owner;

    private int ticks = 0;
    private int elapsed = 0;

    private boolean started = false;
    private boolean cancelled = false;

    public TimerImpl(TimerManager owner) {
        this.owner = owner;
        tickEvent.addPhaseOrdering(Event.DEFAULT_PHASE, LATE_PHASE);
    }

    @Override
    public Event<Tick> onTick() {
        return tickEvent;
    }

    @Override
    public void start() {
        if(cancelled) return;

        if(!started) {
            onTick().invoker().tick(this);
            owner.startTracking(this);
            started = true;
        }
    }

    void tick() {
        ticks++;
        if (ticks % 20 == 0) {
            elapsed++;
            onTick().invoker().tick(this);
        }
    }

    @Override
    public void stop() {
        owner.stopTracking(this);
        started = false;
    }

    @Override
    public void cancel() {
        stop();
        cancelled = true;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void restart() {
        if(cancelled) return;
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

    public static Timer create(MinecraftServer server) {
        return new TimerImpl(TimerManagerHolder.get(server));
    }
}
