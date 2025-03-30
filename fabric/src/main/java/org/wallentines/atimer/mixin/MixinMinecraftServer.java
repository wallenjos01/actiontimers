package org.wallentines.atimer.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.wallentines.atimer.impl.TimerManager;
import org.wallentines.atimer.impl.TimerManagerHolder;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
@Implements(@Interface(iface= TimerManagerHolder.class, prefix="atimer$"))
public class MixinMinecraftServer {

    @Unique
    private TimerManager atimer$manager;

    @Inject(method="<init>", at=@At("TAIL"))
    private void onInit(CallbackInfo ci) {
        atimer$manager = new TimerManager();
    }

    @Inject(method="tickServer", at=@At("TAIL"))
    private void onTick(BooleanSupplier booleanSupplier, CallbackInfo ci) {
        atimer$manager.tick();
    }

    @Unique
    public TimerManager atimer$getTimerManager() {
        return atimer$manager;
    }

}
