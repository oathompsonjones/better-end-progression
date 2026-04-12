package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import uk.co.oathompsonjones.BEPItems;

import java.util.function.BiConsumer;

public class BEPLootTableProvider extends SimpleFabricLootTableProvider {
    public BEPLootTableProvider(FabricDataOutput output) {
        super(output, LootContextTypes.CHEST);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
        exporter.accept(
                new Identifier("bep", "chests/swamp_hut"),
                LootTable
                        .builder()
                        .pool(LootPool
                                .builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .with(ItemEntry.builder(BEPItems.HEART_OF_THE_SWAMP)))
                        .pool(LootPool
                                .builder()
                                .rolls(UniformLootNumberProvider.create(5, 10))
                                .with(ItemEntry
                                        .builder(Items.GLASS_BOTTLE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
                                        .weight(5))
                                .with(ItemEntry
                                        .builder(Items.REDSTONE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
                                        .weight(4))
                                .with(ItemEntry
                                        .builder(Items.GLOWSTONE_DUST)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
                                        .weight(3))
                                .with(ItemEntry
                                        .builder(Items.GUNPOWDER)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
                                        .weight(3))
                                .with(ItemEntry
                                        .builder(Items.SPIDER_EYE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
                                        .weight(3))
                                .with(ItemEntry
                                        .builder(Items.STICK)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 5)))
                                        .weight(5))
                                .with(ItemEntry
                                        .builder(Items.RED_MUSHROOM)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
                                        .weight(4))
                                .with(ItemEntry
                                        .builder(Items.BROWN_MUSHROOM)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
                                        .weight(4))
                                .with(ItemEntry
                                        .builder(Items.SUGAR)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
                                        .weight(3)))
        );
    }
}
