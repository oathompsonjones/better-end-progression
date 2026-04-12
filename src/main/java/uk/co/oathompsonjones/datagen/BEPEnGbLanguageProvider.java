package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import uk.co.oathompsonjones.BEP;
import uk.co.oathompsonjones.BEPItems;

public class BEPEnGbLanguageProvider extends FabricLanguageProvider {
    public BEPEnGbLanguageProvider(FabricDataOutput dataGenerator) {
        super(dataGenerator, "en_gb");
    }

    public static String correctSpelling(String str) {
        return str;
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        for (Item item : BEPItems.ITEMS)
            translationBuilder.add(
                    item,
                    correctSpelling(BEPEnUsLanguageProvider.format(item
                            .getTranslationKey()
                            .replace("item." + BEP.MOD_ID + ".", "")))
            );
    }
}

