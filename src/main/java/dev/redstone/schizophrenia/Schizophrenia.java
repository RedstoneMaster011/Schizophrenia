package dev.redstone.schizophrenia;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import dev.redstone.schizophrenia.entity.SchizoEntities;
import dev.redstone.schizophrenia.events.player.Hallucination;
import dev.redstone.schizophrenia.events.player.ReplaceItemTick;
import dev.redstone.schizophrenia.events.player.Sound;
import net.fabricmc.api.ModInitializer;

public class Schizophrenia implements ModInitializer {

    @Override
    public void onInitialize() {

        SchizoConfigs.init();
        ReplaceItemTick.init();
        Sound.init();
        DayCountState.init();
        SchizoEntities.init();

        Hallucination.init();
    }
}
