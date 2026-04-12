package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class BEPLootInjector {
    public static void initialize() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (id.getNamespace().equals("minecraft")) {
                switch (id.getPath()) {
                    case "chests/desert_pyramid":
                        tableBuilder.pool(desertPyramid().build());
                        break;
                    case "chests/jungle_temple":
                        tableBuilder.pool(jungleTemple().build());
                        break;
                    case "chests/woodland_mansion":
                        tableBuilder.pool(woodlandMansion().build());
                        break;
                    case "chests/igloo_chest":
                        tableBuilder.pool(iglooChest().build());
                        break;
                    case "chests/abandoned_mineshaft":
                        tableBuilder.pool(mineshaft().build());
                        break;
                    case "chests/bastion_treasure":
                        tableBuilder.pool(bastionTreasure().build());
                        break;
                    case "archaeology/trail_ruins_rare":
                        tableBuilder.modifyPools(BEPLootInjector::archaeologyTrailRuinsRare);
                        break;
                }
            }
        });
    }

    private static LootPool.Builder desertPyramid() {
        return LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                // 50% chance to get heart
                .with(ItemEntry.builder(BEPItems.HEART_OF_THE_DESERT).weight(1))
                // 50% chance to get nothing
                .with(EmptyEntry.builder().weight(1));
    }

    private static LootPool.Builder jungleTemple() {
        return LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                // 75% chance to get heart
                .with(ItemEntry.builder(BEPItems.HEART_OF_THE_JUNGLE).weight(3))
                // 25% chance to get nothing
                .with(EmptyEntry.builder().weight(1));
    }

    private static LootPool.Builder woodlandMansion() {
        return LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                // 50% chance to get heart
                .with(ItemEntry.builder(BEPItems.HEART_OF_THE_WOODLANDS).weight(1))
                // 50% chance to get nothing
                .with(EmptyEntry.builder().weight(1));
    }

    private static LootPool.Builder iglooChest() {
        return LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                // 100% chance to get heart
                .with(ItemEntry.builder(BEPItems.HEART_OF_THE_TUNDRA).weight(1));
    }

    private static LootPool.Builder mineshaft() {
        return LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                // 10% chance to get heart
                .with(ItemEntry.builder(BEPItems.HEART_OF_THE_CAVERNS).weight(1))
                // 90% chance to get nothing
                .with(EmptyEntry.builder().weight(9));
    }

    private static LootPool.Builder bastionTreasure() {
        return LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                // 50% chance to get heart
                .with(ItemEntry.builder(BEPItems.HEART_OF_THE_BRUTE).weight(1))
                // 50% chance to get nothing
                .with(EmptyEntry.builder().weight(1));
    }

    private static void archaeologyTrailRuinsRare(LootPool.Builder pool) {
        pool.with(ItemEntry.builder(BEPItems.HEART_OF_THE_RUINS).weight(1));
    }
}
