package dev.redstone.schizophrenia.player;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class ReplaceItemTick {

    private static int tickC = 0;

    public static void init() {

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickC++;
            if (tickC >= SchizoConfigs.SchizoConfig.EventsSection.InventoryReplacementSection.InventoryReplacementTimer * 20) {
                tickC = 0;
                for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                    ItemEditor.ReplaceItemSlot(player);
                }
            }
        });
    }
}
