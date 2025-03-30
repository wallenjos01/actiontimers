package org.wallentines.atimer.api;

import net.minecraft.server.MinecraftServer;
import org.wallentines.atimer.impl.CountdownTimerImpl;

public interface CountdownTimer extends Timer {

    /**
     * Gets the timer's initial duration
     * @return The timer's initial duration
     */
    int duration();

    /**
     * Gets the number of remaining seconds
     * @return The number of remaining seconds
     */
    int remaining();

    /**
     * Creates a countdown timer, if possible, using the given server and initial duration
     * @param server A running server
     * @param duration The number of seconds the timer will be active
     * @return A new countdown timer.
     */
    static CountdownTimer create(MinecraftServer server, int duration) {
        return CountdownTimerImpl.create(server, duration);
    }
}
