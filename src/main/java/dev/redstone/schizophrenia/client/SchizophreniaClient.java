package dev.redstone.schizophrenia.client;

import dev.redstone.schizophrenia.client.events.player.Clone;
import dev.redstone.schizophrenia.client.events.player.CloneRenderer;
import dev.redstone.schizophrenia.client.events.player.FakeItemChange;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class SchizophreniaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        FakeItemChange.init();
        ClientTickEvents.END_CLIENT_TICK.register(Clone::onClientTick);
        WorldRenderEvents.AFTER_ENTITIES.register(CloneRenderer::onWorldRender);

    }
}
