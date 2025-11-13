package dev.redstone.schizophrenia.client.events.player;

import dev.redstone.schizophrenia.DayCountState;
import dev.redstone.schizophrenia.config.SchizoConfigs;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import java.util.List;
import java.util.Random;
import java.util.WeakHashMap;

public class FakeItemChange {

    public static final WeakHashMap<ItemStack, Long> glitchedStacks = new WeakHashMap<>();
    private static boolean triggered = false;

    public static void init() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            double result = DayCountState.getDayUpdateConfig();

            if (client.currentScreen instanceof HandledScreen<?> screen && !triggered) {

                triggered = true;
                if(!SchizoConfigs.SchizoConfig.EventsSection.FakeItemSection.FakeItem) return;

                if (new Random().nextInt((int) (SchizoConfigs.SchizoConfig.EventsSection.FakeItemSection.ChanceForFakeItem / result)) == 0) {

                    ScreenHandler handler = screen.getScreenHandler();
                    List<Slot> slots = handler.slots;
                    if (!slots.isEmpty()) {

                        Slot slot = slots.get(new Random().nextInt(slots.size()));
                        ItemStack stack = slot.getStack();
                        if (!stack.isEmpty()) {

                            glitchedStacks.put(stack, System.currentTimeMillis());
                        }
                    }
                }
            }

            if (!(client.currentScreen instanceof HandledScreen<?>)) {
                triggered = false;
            }
        });
    }
}