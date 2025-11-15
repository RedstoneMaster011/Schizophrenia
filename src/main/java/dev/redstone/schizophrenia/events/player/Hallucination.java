package dev.redstone.schizophrenia.events.player;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import dev.redstone.schizophrenia.entity.HallucinationEntity;
import dev.redstone.schizophrenia.entity.SchizoEntities;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.RaycastContext;

import java.util.List;
import java.util.Random;

public class Hallucination {
    private static final int MAX_DISTANCE = 50;
    private static final Random RANDOM = new Random();
    private static boolean ticking = false;
    private static MinecraftServer server;

    public static void init() {
        if (ticking) return;
        ticking = true;

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {}

                if (server == null) continue;

                if (!SchizoConfigs.SchizoConfig.EventsSection.HallucinationSection.Hallucination) continue;

                if (RANDOM.nextInt(SchizoConfigs.SchizoConfig.EventsSection.HallucinationSection.ChanceForHallucination) != 0) continue;

                for (ServerWorld world : server.getWorlds()) {
                    List<ServerPlayerEntity> players = world.getPlayers();
                    if (players.isEmpty()) continue;

                    ServerPlayerEntity player = players.get(RANDOM.nextInt(players.size()));
                    Vec3d origin = player.getPos();

                    for (int i = 0; i < 10; i++) {
                        double dx = RANDOM.nextDouble() * MAX_DISTANCE * 2 - MAX_DISTANCE;
                        double dz = RANDOM.nextDouble() * MAX_DISTANCE * 2 - MAX_DISTANCE;
                        BlockPos base = new BlockPos((int) (origin.x + dx), world.getTopY(), (int) (origin.z + dz));

                        int y = world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, base.getX(), base.getZ());
                        BlockPos surface = new BlockPos(base.getX(), y, base.getZ());

                        if (world.isAir(surface) && world.isSkyVisible(surface)) {
                            Vec3d eye = player.getCameraPosVec(1.0F);
                            Vec3d target = new Vec3d(surface.getX() + 0.5, surface.getY() + 1.6, surface.getZ() + 0.5);
                            BlockHitResult hit = world.raycast(new RaycastContext(
                                    eye, target,
                                    RaycastContext.ShapeType.COLLIDER,
                                    RaycastContext.FluidHandling.NONE,
                                    player
                            ));

                            if (hit.getType() == HitResult.Type.MISS) {
                                HallucinationEntity entity = new HallucinationEntity(SchizoEntities.HALLUCINATION, world);
                                entity.refreshPositionAndAngles(surface, 0, 0);
                                world.spawnEntity(entity);
                                break;
                            }
                        }
                    }
                }
            }
        }).start();
    }

    public static void setServer(MinecraftServer s) {
        server = s;
    }
}