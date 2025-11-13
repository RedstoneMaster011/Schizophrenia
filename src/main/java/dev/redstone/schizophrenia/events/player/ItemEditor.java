package dev.redstone.schizophrenia.events.player;

import dev.redstone.schizophrenia.config.SchizoConfigs;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.Random;

public class ItemEditor {

    private static final Random Random = new Random();

    public static void ReplaceItemSlot(ServerPlayerEntity player) {

        if(!SchizoConfigs.SchizoConfig.EventsSection.InventoryReplacementSection.InventoryReplacement) return;

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

        List<Item> Items = Items();

        Item randomItem = Items.get(Random.nextInt(Items.size()));

        if(stack.isEmpty()) return;

        inv.setStack(slot, new ItemStack(randomItem));
    }

    private static ItemStack getMaterialReplacement(Item item) {

        if(!SchizoConfigs.SchizoConfig.EventsSection.InventoryReplacementSection.InventoryReplacement) return null;

        if (item.getTranslationKey().toLowerCase().contains("leather")) return new ItemStack(Items.LEATHER, 6);
        if (item.getTranslationKey().toLowerCase().contains("gold")) return new ItemStack(Items.GOLD_INGOT, 6);
        if (item.getTranslationKey().toLowerCase().contains("diamond")) return new ItemStack(Items.DIAMOND, 6);
        if (item.getTranslationKey().toLowerCase().contains("chain")) return new ItemStack(Items.CHAIN, 6);
        if (item.getTranslationKey().toLowerCase().contains("iron")) return new ItemStack(Items.IRON_INGOT, 6);
        if (item.getTranslationKey().toLowerCase().contains("netherite")) return new ItemStack(Items.NETHERITE_INGOT, 6);
        return null;
    }

    public static List<Item> Items() {

        return List.of(
                Items.TORCH, Items.COOKED_BEEF, Items.COOKED_PORKCHOP, Items.BREAD, Items.APPLE,
                Items.GOLDEN_APPLE, Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.COOKED_CHICKEN,
                Items.COOKED_MUTTON, Items.COOKED_SALMON, Items.COOKED_COD,

                Items.OAK_LOG, Items.OAK_PLANKS, Items.CRAFTING_TABLE, Items.FURNACE, Items.CHEST,
                Items.STONE, Items.COBBLESTONE, Items.DIRT, Items.GRASS_BLOCK, Items.SAND,
                Items.GLASS, Items.LADDER, Items.STICK, Items.STRING, Items.FEATHER,
                Items.LEATHER, Items.IRON_INGOT, Items.GOLD_INGOT, Items.DIAMOND, Items.EMERALD,
                Items.REDSTONE, Items.COAL, Items.LAPIS_LAZULI,

                Items.TNT, Items.ARROW, Items.SNOWBALL, Items.EGG, Items.ENDER_PEARL,
                Items.EXPERIENCE_BOTTLE, Items.ENCHANTED_BOOK, Items.BOOK, Items.PAPER, Items.MAP,
                Items.COMPASS, Items.CLOCK, Items.NAME_TAG, Items.LEAD, Items.SADDLE
        );
    }
}
