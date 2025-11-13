package dev.redstone.schizophrenia.events.player;

import dev.redstone.schizophrenia.DayCountState;
import dev.redstone.schizophrenia.config.SchizoConfigs;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Random;

public class Sound {

    private static final Random random = new Random();

    private static final List<Identifier> sounds = SchizoConfigs.SchizoConfig.EventsSection.SoundSection.sounds;

    public static void init() {

        ServerTickEvents.END_SERVER_TICK.register(Sound::onTick);
    }

    private static void onTick(MinecraftServer server) {

        DayCountState DayCountState = new DayCountState();
        double result = DayCountState.getDayUpdateConfig();

        if (!SchizoConfigs.SchizoConfig.EventsSection.SoundSection.Sound) return;

        if (random.nextInt((int) (SchizoConfigs.SchizoConfig.EventsSection.SoundSection.ChanceForSound / result)) == 0) {
            List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
            if (!players.isEmpty()) {
                ServerPlayerEntity target = players.get(random.nextInt(players.size()));
                Identifier soundId = sounds.get(random.nextInt(sounds.size()));
                SoundEvent sound = SoundEvent.of(soundId);
                target.getWorld().playSound(null, target.getX(), target.getY(), target.getZ(), sound, target.getSoundCategory(), 1.0F, 1.0F);
            }
        }
    }
}

