package dev.redstone.schizophrenia.client.events.player;

import dev.redstone.schizophrenia.DayCountState;
import dev.redstone.schizophrenia.config.SchizoConfigs;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Random;

public class Message {
    private static final Random random = new Random();

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            List texts = List.of(new String[]{
                    "Hello",
                    "Hello?",
                    "Welcome Friend.",
                    "Heyyy",
                    "What?"
            });

            String text = texts.get(random.nextInt(texts.size())).toString();

            double result = DayCountState.getDayUpdateConfig();

            if (!SchizoConfigs.SchizoConfig.EventsSection.MessageSection.Message) return;

            if (random.nextInt((int) (SchizoConfigs.SchizoConfig.EventsSection.MessageSection.ChanceForMessage / result)) != 0) return;

            if (MinecraftClient.getInstance().player == null) return;

            MinecraftClient.getInstance().player.sendMessage(Text.of("<" + MinecraftClient.getInstance().player.getName().getLiteralString() + "> " + text));
        });
    }
}
