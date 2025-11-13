package dev.redstone.schizophrenia.client;

import net.fabricmc.api.ClientModInitializer;

public class SchizophreniaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        FakeItemChange.init();
    }
}
