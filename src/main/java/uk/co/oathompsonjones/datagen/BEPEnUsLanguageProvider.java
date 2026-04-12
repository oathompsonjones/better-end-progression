package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import uk.co.oathompsonjones.BEP;
import uk.co.oathompsonjones.BEPItems;

public class BEPEnUsLanguageProvider extends FabricLanguageProvider {
    public BEPEnUsLanguageProvider(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }

    public static String format(String str) {
        return toTitleCase(str.replace("_", " "));
    }

    public static String toTitleCase(String str) {
        if (str == null || str.isEmpty())
            return str;

        String[]      words     = str.split(" ");
        StringBuilder titleCase = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty())
                titleCase.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(
                        " ");
        }
        return titleCase.toString().trim();
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        for (Item item : BEPItems.ITEMS)
            translationBuilder.add(item, format(item.getTranslationKey().replace("item." + BEP.MOD_ID + ".", "")));
    }
}

