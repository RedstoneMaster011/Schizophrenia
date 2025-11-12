package dev.redstone.schizophrenia;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import dev.redstone.schizophrenia.player.ReplaceItemTick;
import net.fabricmc.api.ModInitializer;

public class Schizophrenia implements ModInitializer {

    @Override
    public void onInitialize() {

        SchizoConfigs.init();

        ReplaceItemTick.init();
    }
}
