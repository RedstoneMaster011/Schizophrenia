package dev.redstone.schizophrenia;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.World;

public class DayCountState extends PersistentState {

    private static final String KEY = "day_count";
    private long lastRecordedDay = -1;
    private int dayCount = 0;

    private static final Type<DayCountState> TYPE = new Type<>(

            DayCountState::new,
            DayCountState::new,
            DataFixTypes.LEVEL
    );

    public DayCountState(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {}

    public static DayCountState get(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(TYPE, KEY);
    }

    public DayCountState() {}

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {

        nbt.putInt("count", dayCount);
        nbt.putLong("last_day", lastRecordedDay);
        return nbt;
    }

    public void tick(ServerWorld world) {

        long currentDay = world.getTimeOfDay() / 24000;
        if (currentDay > lastRecordedDay) {
            lastRecordedDay = currentDay;
            dayCount++;
            System.out.println("NEW DAY!! Day: " + dayCount);
            markDirty();
        }
    }

    public int getDayCount() {
        return dayCount;
    }

    public double getDayUpdateConfig() {

        double dayc = 1;

        for (int day = 0; day < getDayCount(); day++) {
            dayc = dayc + SchizoConfigs.SchizoConfig.AmountPerDayToIncreaseBy;
        }
        return dayc;
    }

    public static void init() {

        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (world.getRegistryKey() == World.OVERWORLD && !world.isClient()) {
                DayCountState.get(world).tick(world);
            }
        });
    }
}