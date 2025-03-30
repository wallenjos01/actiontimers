package org.wallentines.atimer.api;

import net.minecraft.network.chat.Component;
import org.wallentines.atimer.impl.TimerDisplayImpl;
import org.wallentines.pseudonym.Message;

public interface TimerDisplay {

    /**
     * Gets the format of this timer
     * @return An unresolved component which describes the timer's display text. Accepts the %time% placeholder.
     */
    Message<Component> format();

    /**
     * Gets the parent timer this display corresponds to.
     * @return The timer this display reads and updates on.
     */
    Timer timer();


    static TimerDisplay create(Timer timer, Message<Component> format) {
        return new TimerDisplayImpl(timer, format);
    }

}
