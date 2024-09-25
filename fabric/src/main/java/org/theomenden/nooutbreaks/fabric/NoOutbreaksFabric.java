package org.theomenden.nooutbreaks.fabric;

import net.fabricmc.api.ModInitializer;
import org.theomenden.nooutbreaks.main.NoOutbreaks;

public final class NoOutbreaksFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NoOutbreaks.init();
    }
}
