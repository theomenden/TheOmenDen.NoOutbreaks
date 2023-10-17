package com.theomenden.no_outbreaks.mixins;

import com.theomenden.no_outbreaks.NoOutbreaks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.VillageSiege;
import net.minecraft.world.level.CustomSpawner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillageSiege.class)
public abstract class VillageSiegeMixin implements CustomSpawner {
    @Shadow private VillageSiege.State siegeState;
    @Shadow private boolean hasSetupSiege;

    @Unique
    private static final Logger LOGGER = LoggerFactory.getLogger(NoOutbreaks.class);

   @Inject(method = "tick(Lnet/minecraft/server/level/ServerLevel;ZZ)I", at = @At("HEAD"), cancellable = true)
    private void onTick(ServerLevel level, boolean spawnEnemies, boolean spawnFriendlies, CallbackInfoReturnable<Integer> cir) {
      var gameRules = level.getGameRules();

      boolean shouldNotStopZombieSiege = gameRules.getBoolean(NoOutbreaks.SHOULD_ZOMBIES_RAID);

      if(shouldNotStopZombieSiege) {
          return;
      }

       LOGGER.info("Detected Zombie Siege");
       this.siegeState = VillageSiege.State.SIEGE_DONE;
       this.hasSetupSiege = false;
       cir.setReturnValue(0);
       LOGGER.info("Siege Stopped.");
   }
}
