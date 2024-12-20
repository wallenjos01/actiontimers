package org.wallentines.atimer;

import org.wallentines.mcore.Player;
import org.wallentines.mcore.lang.PlaceholderContext;
import org.wallentines.mcore.lang.UnresolvedComponent;
import org.wallentines.mcore.text.Component;
import org.wallentines.mcore.text.MutableComponent;

import java.util.ArrayList;
import java.util.List;

public class TimerDisplayManagerImpl implements TimerDisplayManager {

    private Player player;
    private PlaceholderContext context;

    private final List<TimerDisplay> displays = new ArrayList<>();

    private boolean sent = false;

    public TimerDisplayManagerImpl(Player player) {
        setPlayer(player);
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.context = new PlaceholderContext().withValue(player);
    }

    @Override
    public void addTimer(Timer timer, UnresolvedComponent format) {
        addDisplay(new TimerDisplayImpl(timer, format));
    }

    @Override
    public void addDisplay(TimerDisplay display) {
        displays.add(display);
        display.timer().onTick().register(this, timer -> send());
    }

    public void tick() {
        sent = false;
    }

    private Component renderAll() {
        MutableComponent out = MutableComponent.empty();
        int drawn = 0;
        for (TimerDisplay display : displays) {
            if(!display.timer().isCancelled()) {
                if(drawn > 0) {
                    out.addChild(Component.text("   "));
                }
                drawn++;
                out.addChild(display.format().resolve(context));
            }
        }
        return out.toComponent();
    }

    private void send() {
        if(sent) return;
        sent = true;

        player.sendActionBar(renderAll());
    }

}
