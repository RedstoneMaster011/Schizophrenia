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
    private static int cachedDayCount = 0;

    private static final Type<DayCountState> TYPE = new Type<>(

            DayCountState::new,
            DayCountState::new,
            DataFixTypes.LEVEL
    );

    public DayCountState(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        this.dayCount = nbt.getInt("count");
        this.lastRecordedDay = nbt.getLong("last_day");
    }

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
            cachedDayCount = dayCount;
            System.out.println("NEW DAY!! Day: " + dayCount);
            System.out.println("NEW DAY!! getDayUpdateConfig: " + (int) (getDayUpdateConfig()));
            System.out.println("NEW DAY!! templatemath: " + (int) (SchizoConfigs.SchizoConfig.EventsSection.SoundSection.ChanceForSound / getDayUpdateConfig()));
            markDirty();
        }
    }

    public int getDayCount() {
        return dayCount;
    }

    public static double getDayUpdateConfig() {
        double dayc = 1;
        for (int day = 0; day < cachedDayCount; day++) {
            dayc += SchizoConfigs.SchizoConfig.AmountPerDayToIncreaseBy;
        }
        if (dayc >= 60) {
            return 50;
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