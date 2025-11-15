package dev.redstone.schizophrenia.client.renderer;

import dev.redstone.schizophrenia.entity.HallucinationEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class HallucinationRenderer extends MobEntityRenderer<HallucinationEntity, BipedEntityModel<HallucinationEntity>> {
    public HallucinationRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BipedEntityModel<>(ctx.getPart(EntityModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(HallucinationEntity entity) {
        return Identifier.of("schizophrenia", "textures/entity/hallucination.png");
    }
}