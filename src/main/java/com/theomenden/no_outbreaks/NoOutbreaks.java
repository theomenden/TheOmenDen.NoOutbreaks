package com.theomenden.no_outbreaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class NoOutbreaks implements ModInitializer {
    public static final String MOD_ID = "nooutbreaks";
    public static final String MOD_NAME = "No Outbreaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static final String RULE_NAME = "shouldZombiesRaid";
    public static GameRules.Key<GameRules.BooleanValue> SHOULD_ZOMBIES_RAID;

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        SHOULD_ZOMBIES_RAID = GameRuleRegistry.register(RULE_NAME, GameRules.Category.SPAWNING, GameRuleFactory.createBooleanRule(true));

        if(GameRuleRegistry.hasRegistration(MOD_ID)) {
            LOGGER.info("No more outbreaks game rule registered - No more Zombies!");
        }
    }
}
