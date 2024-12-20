package org.wallentines.atimer;

import org.wallentines.mcore.lang.CustomPlaceholder;
import org.wallentines.mcore.lang.UnresolvedComponent;

public class TimerDisplayImpl implements TimerDisplay {

    private final Timer timer;
    private final UnresolvedComponent format;

    public TimerDisplayImpl(Timer timer, UnresolvedComponent format) {
        this.timer = timer;
        this.format = format.copyWith(CustomPlaceholder.inline("time", this::getTimeString));
    }

    @Override
    public UnresolvedComponent format() {
        return format;
    }

    @Override
    public Timer timer() {
        return timer;
    }

    private String getTimeString() {
        return toTimeString(timer.time());
    }


    public static String toTimeString(int seconds) {

        int minutes = seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;

        seconds = seconds % 60;
        minutes = minutes % 60;
        hours = hours % 24;

        StringBuilder sb = new StringBuilder();
        if(days > 0) {
            sb.append(days).append(":");
        }
        if(hours > 0 || days > 0) {
            sb.append(String.format("%02d:", hours));
        }
        sb.append(String.format("%02d:%02d", minutes, seconds));

        return sb.toString();
    }

}
