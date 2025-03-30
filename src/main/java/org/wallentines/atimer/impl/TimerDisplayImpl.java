package org.wallentines.atimer.impl;


import net.minecraft.network.chat.Component;
import org.wallentines.atimer.api.Timer;
import org.wallentines.atimer.api.TimerDisplay;
import org.wallentines.pseudonym.PipelineContext;
import org.wallentines.pseudonym.Message;

import java.util.Optional;

public class TimerDisplayImpl implements TimerDisplay {

    private final Timer timer;
    private final Message<Component> format;

    public TimerDisplayImpl(Timer timer, Message<Component> format) {
        this.timer = timer;
        this.format = context ->
                format.get(context.and(PipelineContext.builder()
                        .withContextPlaceholder("time", ctx -> Optional.of(getTimeString()))
                        .build())
                );
    }

    @Override
    public Message<Component> format() {
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
