package org.wallentines.atimer.impl;


import java.util.ArrayList;
import java.util.List;

public class TimerManager {

    private final List<TimerImpl> runningTimers = new ArrayList<>();

    public void startTracking(TimerImpl timer) {
        runningTimers.add(timer);
    }

    public void stopTracking(TimerImpl timer) {
        runningTimers.remove(timer);
    }

    public void tick() {
        for(TimerImpl timer : runningTimers) {
            timer.tick();
        }
    }
}
