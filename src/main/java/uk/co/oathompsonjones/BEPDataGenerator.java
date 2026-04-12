package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import uk.co.oathompsonjones.datagen.*;

public class BEPDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(BEPAdvancementProvider::new);
        pack.addProvider(BEPEnGbLanguageProvider::new);
        pack.addProvider(BEPEnUsLanguageProvider::new);
        pack.addProvider(BEPLootTableProvider::new);
        pack.addProvider(BEPModelProvider::new);
        pack.addProvider(BEPRecipeOverrideProvider::new);
        pack.addProvider(BEPRecipeProvider::new);
    }
}
