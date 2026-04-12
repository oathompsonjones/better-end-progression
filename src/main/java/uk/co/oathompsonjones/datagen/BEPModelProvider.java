package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import uk.co.oathompsonjones.BEPItems;

public class BEPModelProvider extends FabricModelProvider {
    public BEPModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) { }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (Item item : BEPItems.ITEMS) {
            itemModelGenerator.register(item, Models.GENERATED);
        }
    }
}
