package org.wallentines.atimer.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TimerManager {

    private final List<TimerImpl> runningTimers = new CopyOnWriteArrayList<>();

    public void startTracking(TimerImpl timer) {
        runningTimers.add(timer);
    }

    public void stopTracking(TimerImpl timer) {
        runningTimers.remove(timer);
    }

    public void tick() {

        for (TimerImpl timer : runningTimers) {
            timer.tick();
        }
    }
}
