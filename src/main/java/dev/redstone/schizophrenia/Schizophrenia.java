package dev.redstone.schizophrenia;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import dev.redstone.schizophrenia.events.player.ReplaceItemTick;
import dev.redstone.schizophrenia.events.player.Sound;
import net.fabricmc.api.ModInitializer;

public class Schizophrenia implements ModInitializer {

    @Override
    public void onInitialize() {

        SchizoConfigs.init();
        ReplaceItemTick.init();
        Sound.init();

    }
}
