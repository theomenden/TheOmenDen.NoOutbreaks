package org.theomenden.nooutbreaks.mixins;

import dev.architectury.utils.GameInstance;
import net.minecraft.world.entity.ai.village.VillageSiege;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.theomenden.nooutbreaks.main.NoOutbreaks;

import java.util.Objects;

@Mixin(VillageSiege.class)
public abstract class VillageSiegeMixin implements CustomSpawner {


    @ModifyVariable(method = "tick", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private boolean onTick(boolean value) {
        try{
            var gameServer = Objects.requireNonNull(GameInstance.getServer());
            var serverGameRules = gameServer.getGameRules();

            return serverGameRules.getBoolean(NoOutbreaks.SHOULD_ZOMBIES_RAID);
        } catch(NullPointerException e){
           NoOutbreaks.LOGGER.error("Unable to get {}, defaulting to supplied value", NoOutbreaks.RULE_NAME);
        }
        return value;
    }
}
