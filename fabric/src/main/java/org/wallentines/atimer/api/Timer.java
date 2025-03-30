package org.wallentines.atimer.api;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.server.MinecraftServer;
import org.wallentines.atimer.impl.TimerImpl;

public interface Timer {

    /**
     * Returns an event invoked once a second
     * @return An event
     */
    Event<Tick> onTick();

    /**
     * Starts the timer's ticking
     */
    void start();

    /**
     * Stops the timer's ticking
     */
    void stop();

    /**
     * Stops the timer's ticking, marks the timer as cancelled, and clears all event handlers registered to onTick.
     * @see Timer::onTick
     */
    void cancel();

    /**
     * Queries if the timer is cancelled. If this is true, cancel() has been called and start() can no longer be called.
     * @return Whether the timer has been cancelled.
     */
    boolean isCancelled();

    /**
     * Resets the elapsed time to 0
     */
    void restart();

    /**
     * Gets the timer's elapsed time
     * @return The timer's elapsed time
     */
    int elapsed();

    /**
     * Gets the time which should be displayed for this timer.
     * @return The time which should be displayed for this timer. For regular Timers, this is the same as elapsed().
     * For CountdownTimers, this is the same as remaining().
     * @see CountdownTimer
     */
    int time();


    /**
     * Creates a timer, if possible, using the given server for tick timing
     * @param server A running server
     * @return A new Timer
     */
    static Timer create(MinecraftServer server) {
        return TimerImpl.create(server);
    }

    /**
     * Tick event handler
     */
    interface Tick {
        void tick(Timer timer);
    }

}
