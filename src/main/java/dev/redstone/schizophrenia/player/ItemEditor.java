package dev.redstone.schizophrenia.player;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.Random;

public class ItemEditor {

    private static final Random Random = new Random();

    public static void ReplaceItemSlot(ServerPlayerEntity player) {

        if(!SchizoConfigs.SchizoConfig.ItemEditingSection.InventoryReplacement) return;

        PlayerInventory inv = player.getInventory();
        int slot = Random.nextInt(inv.size());
        ItemStack stack = inv.getStack(slot);

        if (!stack.isEmpty()) {
            Item item = stack.getItem();
            ItemStack replacement = getMaterialReplacement(item);
            if (replacement != null) {
                inv.setStack(slot, replacement);
                return;
            }
        }

        List<Item> allItems = Registries.ITEM.stream().toList();
        Item randomItem = allItems.get(Random.nextInt(allItems.size()));

        if(stack.isEmpty()) return;

        inv.setStack(slot, new ItemStack(randomItem));
    }

    private static ItemStack getMaterialReplacement(Item item) {

        if(!SchizoConfigs.SchizoConfig.ItemEditingSection.InventoryReplacement) return null;

        if (item.getTranslationKey().toLowerCase().contains("leather")) return new ItemStack(Items.LEATHER, 6);
        if (item.getTranslationKey().toLowerCase().contains("gold")) return new ItemStack(Items.GOLD_INGOT, 6);
        if (item.getTranslationKey().toLowerCase().contains("diamond")) return new ItemStack(Items.DIAMOND, 6);
        if (item.getTranslationKey().toLowerCase().contains("chain")) return new ItemStack(Items.CHAIN, 6);
        if (item.getTranslationKey().toLowerCase().contains("iron")) return new ItemStack(Items.IRON_INGOT, 6);
        if (item.getTranslationKey().toLowerCase().contains("netherite")) return new ItemStack(Items.NETHERITE_INGOT, 6);
        return null;
    }
}
