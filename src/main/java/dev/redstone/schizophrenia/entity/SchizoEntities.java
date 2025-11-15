package dev.redstone.schizophrenia.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class SchizoEntities {
    public static final EntityType<HallucinationEntity> HALLUCINATION = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of("schizophrenia", "hallucination"),
            net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder.createMob()
                    .entityFactory(HallucinationEntity::new)
                    .spawnGroup(SpawnGroup.MONSTER)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.8f))
                    .build()
    );

    public static void init() {
        FabricDefaultAttributeRegistry.register(
                HALLUCINATION,
                MobEntity.createMobAttributes()
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
        );
    }
}