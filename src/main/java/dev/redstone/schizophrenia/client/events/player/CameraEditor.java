package dev.redstone.schizophrenia.client.events.player;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.Random;

public class CameraEditor {

    private static final Random random = new Random();
    private static final boolean val = random.nextBoolean();
    private static final int var = SchizoConfigs.SchizoConfig.EventsSection.CameraMoveSection.AmountForCameraMove;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (!SchizoConfigs.SchizoConfig.EventsSection.CameraMoveSection.CameraMove) return;

            if (random.nextInt(SchizoConfigs.SchizoConfig.EventsSection.CameraMoveSection.ChanceForCameraMove) != 0) return;

            ClientPlayerEntity player = client.player;
            if (player != null) {
                float delta = val ? var : -var;
                player.setYaw(player.getYaw() + delta);
            }
        });
    }
}