package org.wallentines.atimer.impl;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import org.wallentines.atimer.api.Timer;
import org.wallentines.atimer.api.TimerDisplay;
import org.wallentines.atimer.api.TimerDisplayManager;
import org.wallentines.pseudonym.Message;
import org.wallentines.pseudonym.PipelineContext;

import java.util.ArrayList;
import java.util.List;

public class TimerDisplayManagerImpl implements TimerDisplayManager {

    private ServerPlayer player;
    private PipelineContext context;

    private final List<TimerDisplay> displays = new ArrayList<>();

    private boolean sent = false;

    public TimerDisplayManagerImpl(ServerPlayer player) {
        setPlayer(player);
    }

    public void setPlayer(ServerPlayer player) {
        this.player = player;
        this.context = PipelineContext.builder(player).build();
    }

    @Override
    public void addTimer(Timer timer, Message<Component> format) {
        addDisplay(new TimerDisplayImpl(timer, format));
    }

    @Override
    public void addDisplay(TimerDisplay display) {
        displays.add(display);
        display.timer().onTick().register(TimerImpl.LATE_PHASE, timer -> send());
    }

    public void tick() {
        sent = false;
    }

    private Component renderAll() {
        MutableComponent out = Component.empty();
        int drawn = 0;

        for(int i = 0 ; i < displays.size() ; i++) {
            TimerDisplay display = displays.get(i);
            if(display.timer().isCancelled()) {
                displays.remove(i);
                i--;
                continue;
            }

            if(drawn > 0) {
                out.append(Component.literal("   "));
            }
            drawn++;
            out.append(display.format().get(context));
        }

        return out;
    }

    private void send() {
        if(sent) return;
        sent = true;

        player.connection.send(new ClientboundSetActionBarTextPacket(renderAll()));
    }

}
