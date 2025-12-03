package dev.redstone.schizophrenia.events.player;

import dev.redstone.schizophrenia.DayCountState;
import dev.redstone.schizophrenia.config.SchizoConfigs;
import dev.redstone.schizophrenia.events.player.util.ItemEditor;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.Random;

public class ReplaceItemTick {

    static Random random = new Random();

    public static void init() {

        ServerTickEvents.END_SERVER_TICK.register(server -> {

            double result = DayCountState.getDayUpdateConfig();

            if (random.nextInt((int) (SchizoConfigs.SchizoConfig.EventsSection.InventoryReplacementSection.ChanceForInventoryReplacement / result)) == 0) {

                List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
                if (!players.isEmpty()) {
                    Random random = new Random();
                    ServerPlayerEntity randomPlayer = players.get(random.nextInt(players.size()));
                    ItemEditor.ReplaceItemSlot(randomPlayer);
                }

            }
        });
    }
}
