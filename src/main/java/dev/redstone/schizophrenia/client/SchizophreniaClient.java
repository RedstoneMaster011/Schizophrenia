package dev.redstone.schizophrenia.client;

import dev.redstone.schizophrenia.client.events.player.*;
import dev.redstone.schizophrenia.client.renderer.HallucinationRenderer;
import dev.redstone.schizophrenia.entity.SchizoEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class SchizophreniaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        FakeItemChange.init();
        CameraEditor.init();
        Message.init();

        ClientTickEvents.END_CLIENT_TICK.register(Clone::onClientTick);
        WorldRenderEvents.AFTER_ENTITIES.register(CloneRenderer::onWorldRender);

        EntityRendererRegistry.register(SchizoEntities.HALLUCINATION, HallucinationRenderer::new);
    }
}
