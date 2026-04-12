package uk.co.oathompsonjones.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import uk.co.oathompsonjones.BEPItems;

import java.util.function.Consumer;

public class BEPAdvancementProvider extends FabricAdvancementProvider {

    public BEPAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    private static String id(String path) {
        return "bep:" + path;
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        // Root (hidden, just for structure)
        Advancement root = Advancement.Builder.create().display(new ItemStack(BEPItems.HEART_OF_THE_DESERT),
                Text.literal("Better End Progression"),
                Text.literal("Begin your journey"),
                null,
                AdvancementFrame.TASK,
                false,
                false,
                false
        ).criterion("tick", TickCriterion.Conditions.createTick()).build(consumer, id("root"));

        // --- Collect all 9 hearts ---
        Advancement allHearts = Advancement.Builder
                .create()
                .parent(root)
                .display(new ItemStack(BEPItems.HEART_OF_THE_DESERT),
                        Text.literal("Heart Collector"),
                        Text.literal("Obtain all nine hearts from structures around the world"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("sea", InventoryChangedCriterion.Conditions.items(Items.HEART_OF_THE_SEA))
                .criterion(
                        "brute",
                        InventoryChangedCriterion.Conditions.items(BEPItems.HEART_OF_THE_BRUTE)
                )
                .criterion("caverns", InventoryChangedCriterion.Conditions.items(BEPItems.HEART_OF_THE_CAVERNS))
                .criterion("desert", InventoryChangedCriterion.Conditions.items(BEPItems.HEART_OF_THE_DESERT))
                .criterion("jungle", InventoryChangedCriterion.Conditions.items(BEPItems.HEART_OF_THE_JUNGLE))
                .criterion("ruins", InventoryChangedCriterion.Conditions.items(BEPItems.HEART_OF_THE_RUINS))
                .criterion("swamp", InventoryChangedCriterion.Conditions.items(BEPItems.HEART_OF_THE_SWAMP))
                .criterion("tundra", InventoryChangedCriterion.Conditions.items(BEPItems.HEART_OF_THE_TUNDRA))
                .criterion("woodlands", InventoryChangedCriterion.Conditions.items(BEPItems.HEART_OF_THE_WOODLANDS))
                .requirements(new String[][] {
                        { "sea" },
                        { "brute" },
                        { "caverns" },
                        { "desert" },
                        { "jungle" },
                        { "ruins" },
                        { "swamp" },
                        { "tundra" },
                        { "woodlands" }
                })
                .build(consumer, id("all_hearts"));

        // --- Craft the End Heart ---
        Advancement endHeart = Advancement.Builder
                .create()
                .parent(allHearts)
                .display(new ItemStack(BEPItems.HEART_OF_THE_END),
                        Text.literal("The Final Heart"),
                        Text.literal("Craft the Heart of the End"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion("crafted_end_heart", InventoryChangedCriterion.Conditions.items(BEPItems.HEART_OF_THE_END))
                .build(consumer, id("end_heart"));

        // --- Wear the Guardian Helmet ---
        Advancement guardianHelmet = Advancement.Builder
                .create()
                .parent(root)
                .display(new ItemStack(BEPItems.PRISMARINE_SCALE),
                        Text.literal("Guardian of the Sea"),
                        Text.literal("Kill three elder guardians and craft the Prismarine Crown"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion("crafted_prismarine_crown",
                        InventoryChangedCriterion.Conditions.items(BEPItems.PRISMARINE_CROWN)
                )
                .build(consumer, id("guardian_helmet"));
    }
}