package org.wallentines.atimer;

import org.jetbrains.annotations.Nullable;
import org.wallentines.mcore.lang.UnresolvedComponent;
import org.wallentines.midnightlib.types.Singleton;

public interface TimerDisplay {

    /**
     * Gets the format of this timer
     * @return An unresolved component which describes the timer's display text. Accepts the %time% placeholder.
     */
    UnresolvedComponent format();

    /**
     * Gets the parent timer this display corresponds to.
     * @return The timer this display reads and updates on.
     */
    Timer timer();

    @Nullable
    static TimerDisplay create(Timer timer, UnresolvedComponent format) {
        Factory f = Factory.Holder.FACTORY.getOrNull();
        if(f == null) return null;
        return f.create(timer, format);
    }

    interface Factory {

        TimerDisplay create(Timer timer, UnresolvedComponent format);

        class Holder {
            public static final Singleton<Factory> FACTORY = new Singleton<>();
        }
    }
}
