package dev.redstone.schizophrenia.client.mixin;

import dev.redstone.schizophrenia.client.events.player.FakeItemChange;
import dev.redstone.schizophrenia.config.SchizoConfigs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At("HEAD"), cancellable = true)
    private void injectGlitchTexture(ItemStack stack, ModelTransformationMode mode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        Long timestamp = FakeItemChange.glitchedStacks.get(stack);
        if (timestamp != null && System.currentTimeMillis() - timestamp < SchizoConfigs.SchizoConfig.EventsSection.FakeItemSection.TimeForFakeItem) {
            Identifier fakeTexture = Identifier.of("schizophrenia", "textures/item/fakeitem.png");

            MinecraftClient client = MinecraftClient.getInstance();
            int x = 0;
            int y = 0;

            if (client.currentScreen instanceof HandledScreen<?> screen) {
                for (Slot slot : screen.getScreenHandler().slots) {
                    if (slot.getStack() == stack) {
                        HandledScreenAccessor accessor = (HandledScreenAccessor) screen;
                        x = slot.x + accessor.getX();
                        y = slot.y + accessor.getY();
                        break;
                    }
                }
            }

            client.getTextureManager().bindTexture(fakeTexture);
            DrawContext drawContext = new DrawContext(client, client.getBufferBuilders().getEntityVertexConsumers());
            drawContext.drawTexture(fakeTexture, x, y, 0, 0, 16, 16, 16, 16);

            ci.cancel();
        }
    }
}