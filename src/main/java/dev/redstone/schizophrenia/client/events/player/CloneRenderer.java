package dev.redstone.schizophrenia.client.events.player;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

public class CloneRenderer {
    public static void onWorldRender(WorldRenderContext context) {
        if (!Clone.shouldRenderGhost()) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        GameProfile profile = client.player.getGameProfile();
        OtherClientPlayerEntity ghost = new OtherClientPlayerEntity(client.world, profile);
        ghost.copyFrom(client.player);
        ghost.setYaw(client.player.getYaw());
        ghost.setPitch(client.player.getPitch());

        Vec3d renderPos = Clone.ghostPosition;
        ghost.setPosition(renderPos.x, renderPos.y, renderPos.z);

        float tickDelta = context.tickCounter().getTickDelta(true);
        EntityRenderDispatcher dispatcher = client.getEntityRenderDispatcher();
        MatrixStack matrices = context.matrixStack();

        Vec3d cameraPos = context.camera().getPos();

        dispatcher.render(
                ghost,
                renderPos.x - cameraPos.x,
                renderPos.y - cameraPos.y,
                renderPos.z - cameraPos.z,
                ghost.getYaw(),
                tickDelta,
                matrices,
                context.consumers(),
                15728880
        );
        Clone.renderTicks++;
    }
}

