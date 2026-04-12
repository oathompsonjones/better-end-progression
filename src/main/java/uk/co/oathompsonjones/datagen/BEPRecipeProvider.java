package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import uk.co.oathompsonjones.BEPItems;

import java.util.function.Consumer;

public class BEPRecipeProvider extends FabricRecipeProvider {
    public BEPRecipeProvider(FabricDataOutput generator) {
        super(generator);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        // ENDER_EYE
        ShapelessRecipeJsonBuilder
                .create(RecipeCategory.MISC, Items.ENDER_EYE)
                .input(BEPItems.HEART_OF_THE_END)
                .input(BEPItems.WARDEN_RIB)
                .input(BEPItems.PRISMARINE_CROWN)
                .input(Items.NETHER_STAR)
                .input(Items.WITHER_ROSE)
                .input(Items.TOTEM_OF_UNDYING)
                .input(Items.TORCHFLOWER)
                .input(Items.ECHO_SHARD)
                .input(Items.NETHERITE_SCRAP)
                .criterion(hasItem(BEPItems.HEART_OF_THE_END), conditionsFromItem(BEPItems.HEART_OF_THE_END))
                .criterion(hasItem(BEPItems.WARDEN_RIB), conditionsFromItem(BEPItems.WARDEN_RIB))
                .criterion(hasItem(BEPItems.PRISMARINE_CROWN), conditionsFromItem(BEPItems.PRISMARINE_CROWN))
                .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                .criterion(hasItem(Items.WITHER_ROSE), conditionsFromItem(Items.WITHER_ROSE))
                .criterion(hasItem(Items.TOTEM_OF_UNDYING), conditionsFromItem(Items.TOTEM_OF_UNDYING))
                .criterion(hasItem(Items.TORCHFLOWER), conditionsFromItem(Items.TORCHFLOWER))
                .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                .criterion(hasItem(Items.NETHERITE_SCRAP), conditionsFromItem(Items.NETHERITE_SCRAP))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder
                .create(RecipeCategory.MISC, Items.ENDER_EYE, 2)
                .input(Items.ENDER_EYE)
                .input(Items.ENDER_PEARL)
                .criterion(hasItem(Items.ENDER_EYE), conditionsFromItem(Items.ENDER_EYE))
                .offerTo(exporter, getRecipeName(Items.ENDER_EYE) + "_from_ender_pearl");

        // HEART_OF_THE_END
        ShapelessRecipeJsonBuilder
                .create(RecipeCategory.MISC, BEPItems.HEART_OF_THE_END)
                .input(Items.HEART_OF_THE_SEA)
                .input(BEPItems.HEART_OF_THE_BRUTE)
                .input(BEPItems.HEART_OF_THE_CAVERNS)
                .input(BEPItems.HEART_OF_THE_DESERT)
                .input(BEPItems.HEART_OF_THE_JUNGLE)
                .input(BEPItems.HEART_OF_THE_RUINS)
                .input(BEPItems.HEART_OF_THE_SWAMP)
                .input(BEPItems.HEART_OF_THE_TUNDRA)
                .input(BEPItems.HEART_OF_THE_WOODLANDS)
                .criterion(hasItem(Items.HEART_OF_THE_SEA), conditionsFromItem(Items.HEART_OF_THE_SEA))
                .criterion(hasItem(BEPItems.HEART_OF_THE_BRUTE), conditionsFromItem(BEPItems.HEART_OF_THE_BRUTE))
                .criterion(hasItem(BEPItems.HEART_OF_THE_CAVERNS), conditionsFromItem(BEPItems.HEART_OF_THE_CAVERNS))
                .criterion(hasItem(BEPItems.HEART_OF_THE_DESERT), conditionsFromItem(BEPItems.HEART_OF_THE_DESERT))
                .criterion(hasItem(BEPItems.HEART_OF_THE_JUNGLE), conditionsFromItem(BEPItems.HEART_OF_THE_JUNGLE))
                .criterion(hasItem(BEPItems.HEART_OF_THE_RUINS), conditionsFromItem(BEPItems.HEART_OF_THE_RUINS))
                .criterion(hasItem(BEPItems.HEART_OF_THE_SWAMP), conditionsFromItem(BEPItems.HEART_OF_THE_SWAMP))
                .criterion(hasItem(BEPItems.HEART_OF_THE_TUNDRA), conditionsFromItem(BEPItems.HEART_OF_THE_TUNDRA))
                .criterion(hasItem(BEPItems.HEART_OF_THE_WOODLANDS),
                        conditionsFromItem(BEPItems.HEART_OF_THE_WOODLANDS)
                )
                .offerTo(exporter, getRecipeName(BEPItems.HEART_OF_THE_END));

        // PRISMARINE_CROWN
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.MISC, BEPItems.PRISMARINE_CROWN)
                .pattern(" s ")
                .pattern("sts")
                .input('s', BEPItems.PRISMARINE_SCALE)
                .input('t', Items.TURTLE_HELMET)
                .criterion(hasItem(BEPItems.PRISMARINE_SCALE), conditionsFromItem(BEPItems.PRISMARINE_SCALE))
                .offerTo(exporter, getRecipeName(BEPItems.PRISMARINE_CROWN));
    }
}
