package dev.redstone.schizophrenia.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HallucinationEntity extends PathAwareEntity {
    public HallucinationEntity(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
        this.setAiDisabled(true);
        this.setSilent(true);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient()) {
            if (this.age > 9600) {
                this.discard();
                return;
            }

            for (PlayerEntity player : this.getWorld().getPlayers()) {
                Vec3d eye = player.getCameraPosVec(1.0F);
                Vec3d look = player.getRotationVec(1.0F);
                Vec3d target = this.getPos().add(0, this.getStandingEyeHeight(), 0);
                Vec3d toEntity = target.subtract(eye).normalize();
                double dot = look.dotProduct(toEntity);

                if (dot > 0.99) {
                    this.discard();
                    break;
                }
            }
        }
    }

    @Override
    protected void initGoals() {
        this.lookControl = new LookControl(this);
        this.goalSelector.add(0, new LookAtEntityGoal(this, PlayerEntity.class, 64.0f));
    }
}
