package dev.redstone.schizophrenia.player;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Random;

public class ReplaceItemTick {

    static Random random = new Random();

    public static void init() {

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (random.nextInt(SchizoConfigs.SchizoConfig.EventsSection.InventoryReplacementSection.ChanceForInventoryReplacement) == 0) {
                for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                    ItemEditor.ReplaceItemSlot(player);
                }
            }
        });
    }
}
