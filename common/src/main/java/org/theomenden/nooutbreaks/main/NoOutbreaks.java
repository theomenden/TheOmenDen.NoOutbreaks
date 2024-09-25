package org.theomenden.nooutbreaks.main;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.level.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NoOutbreaks{
    public static final String MOD_ID = "nooutbreaks";
    public static final String MOD_NAME = "No Outbreaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static final String RULE_NAME = "shouldZombiesRaid";
    public static final GameRules.Key<GameRules.BooleanValue> SHOULD_ZOMBIES_RAID =
            GameRules.register(RULE_NAME, GameRules.Category.SPAWNING, GameRules.BooleanValue.create(true, (server, value) -> LOGGER.info("changed to: {}", value)));

    public static void init() {
            LOGGER.info("No more outbreaks game rule registered - No more Zombies!");
    }
}