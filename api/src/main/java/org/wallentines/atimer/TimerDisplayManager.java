package org.wallentines.atimer;

import org.jetbrains.annotations.Nullable;
import org.wallentines.mcore.Player;
import org.wallentines.mcore.lang.UnresolvedComponent;
import org.wallentines.midnightlib.types.Singleton;

public interface TimerDisplayManager {

    void addTimer(Timer timer, UnresolvedComponent format);

    void addDisplay(TimerDisplay display);

    @Nullable
    static TimerDisplayManager get(Player player) {
        Getter getter = Getter.Holder.GETTER.getOrNull();
        if(getter == null) return null;
        return getter.get(player);
    }

    interface Getter {
        TimerDisplayManager get(Player player);
        class Holder {
            public static final Singleton<Getter> GETTER = new Singleton<>();
        }
    }

}
