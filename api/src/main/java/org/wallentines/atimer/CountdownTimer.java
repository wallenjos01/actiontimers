package org.wallentines.atimer;

import org.jetbrains.annotations.Nullable;
import org.wallentines.mcore.Server;
import org.wallentines.midnightlib.types.Singleton;

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
    @Nullable
    static CountdownTimer create(Server server, int duration) {
        Factory f = Factory.Holder.FACTORY.getOrNull();
        if(f == null) return null;
        return f.create(server, duration);
    }

    interface Factory {

        CountdownTimer create(Server server, int time);
        class Holder {
            public static final Singleton<Factory> FACTORY = new Singleton<>();
        }
    }
}
