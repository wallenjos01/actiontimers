package org.wallentines.atimer.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.wallentines.atimer.TimerDisplayManagerHolder;
import org.wallentines.atimer.TimerDisplayManagerImpl;
import org.wallentines.mcore.Player;

@Mixin(ServerPlayer.class)
@Implements(@Interface(iface= TimerDisplayManagerHolder.class, prefix = "atimer$"))
public class MixinServerPlayer {

    @Unique
    private TimerDisplayManagerImpl atimer$manager;

    @Inject(method="<init>", at=@At("RETURN"))
    private void onInit(MinecraftServer minecraftServer, ServerLevel serverLevel, GameProfile gameProfile, ClientInformation clientInformation, CallbackInfo ci) {
        atimer$manager = new TimerDisplayManagerImpl((Player) this);
    }

    @Inject(method="tick", at=@At("TAIL"))
    private void tick(CallbackInfo ci) {
        atimer$manager.tick();
    }

    @Inject(method="restoreFrom", at=@At("TAIL"))
    private void onRestore(ServerPlayer serverPlayer, boolean bl, CallbackInfo ci) {
        atimer$manager = (TimerDisplayManagerImpl) TimerDisplayManagerHolder.get((Player) serverPlayer);
        atimer$manager.setPlayer((Player) this);
    }

    @Unique
    public TimerDisplayManagerImpl atimer$getTimerDisplayManager() {
        return atimer$manager;
    }
}
