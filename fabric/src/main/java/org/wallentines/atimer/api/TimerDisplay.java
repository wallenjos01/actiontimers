package org.wallentines.atimer.api;

import org.wallentines.atimer.impl.TimerDisplayImpl;
import org.wallentines.pseudonym.UnresolvedMessage;

public interface TimerDisplay {

    /**
     * Gets the format of this timer
     * @return An unresolved component which describes the timer's display text. Accepts the %time% placeholder.
     */
    UnresolvedMessage<String> format();

    /**
     * Gets the parent timer this display corresponds to.
     * @return The timer this display reads and updates on.
     */
    Timer timer();


    static TimerDisplay create(Timer timer, UnresolvedMessage<String> format) {
        return new TimerDisplayImpl(timer, format);
    }

}
