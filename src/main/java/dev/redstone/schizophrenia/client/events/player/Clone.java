package dev.redstone.schizophrenia.client.events.player;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class Clone {
    private static float lastYaw = 0;
    private static boolean shouldRender = false;
    private static long ghostStartTime = 0;
    private static final long GHOST_DURATION_MS = 15000;
    static int renderTicks = 0;
    public static Vec3d ghostPosition = null;

    private static boolean wasTurning = false;

    public static void onClientTick(MinecraftClient client) {
        if (client.player == null || client.world == null) return;
        if (SchizoConfigs.SchizoConfig.EventsSection.CloneSection.Clone) return;

        float currentYaw = client.player.getYaw();
        float deltaYaw = Math.abs(currentYaw - lastYaw);
        lastYaw = currentYaw;

        boolean isTurning = deltaYaw > 1;

        if (wasTurning && !isTurning) {
            shouldRender = true;
            ghostStartTime = System.currentTimeMillis();

            double yawRad = Math.toRadians(client.player.getYaw() + 85);
            double offsetX = -Math.sin(yawRad) * 1.5;
            double offsetZ = Math.cos(yawRad) * 1.5;
            ghostPosition = client.player.getPos().add(offsetX, 0, offsetZ);
            renderTicks = 0;
        }

        wasTurning = isTurning;

        if (System.currentTimeMillis() - ghostStartTime > GHOST_DURATION_MS) {
            shouldRender = false;
            ghostPosition = null;
        }
    }

    public static boolean shouldRenderGhost() {

        Random random = new Random();

        if (random.nextInt(SchizoConfigs.SchizoConfig.EventsSection.CloneSection.ChanceForClone) != 0) return false;
        return shouldRender && renderTicks < 35;
    }
}
