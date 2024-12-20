package org.wallentines.atimer;

import net.fabricmc.api.ModInitializer;

public class Init implements ModInitializer {

    @Override
    public void onInitialize() { }

    static {
        Timer.Factory.Holder.FACTORY.set(TimerImpl::new);
        CountdownTimer.Factory.Holder.FACTORY.set(CountdownTimerImpl::new);
        TimerDisplay.Factory.Holder.FACTORY.set(TimerDisplayImpl::new);
        TimerDisplayManager.Getter.Holder.GETTER.set(TimerDisplayManagerHolder::get);
    }
}
