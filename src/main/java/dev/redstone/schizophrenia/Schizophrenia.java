package dev.redstone.schizophrenia;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import dev.redstone.schizophrenia.inv.ItemEditor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class Schizophrenia implements ModInitializer {
    private static int tickC = 0;

    @Override
    public void onInitialize() {

        SchizoConfigs.init();
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickC++;
            if (tickC >= SchizoConfigs.SchizoConfig.ItemEditingSection.InventoryReplacementTimer * 20) {
                tickC = 0;
                for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                    ItemEditor.ReplaceItemSlot(player);
                }
            }
        });
    }
}
