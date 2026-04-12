package uk.co.oathompsonjones;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class BEPItems {
    public static final Item HEART_OF_THE_END = register(new Item(new Item.Settings().rarity(Rarity.EPIC)),
            "heart_of_the_end"
    );

    public static final Item HEART_OF_THE_BRUTE     = register(new Item(new Item.Settings().rarity(Rarity.UNCOMMON)),
            "heart_of_the_brute"
    );
    public static final Item HEART_OF_THE_CAVERNS   = register(new Item(new Item.Settings().rarity(Rarity.UNCOMMON)),
            "heart_of_the_caverns"
    );
    public static final Item HEART_OF_THE_DESERT    = register(new Item(new Item.Settings().rarity(Rarity.UNCOMMON)),
            "heart_of_the_desert"
    );
    public static final Item HEART_OF_THE_JUNGLE    = register(new Item(new Item.Settings().rarity(Rarity.UNCOMMON)),
            "heart_of_the_jungle"
    );
    public static final Item HEART_OF_THE_RUINS     = register(new Item(new Item.Settings().rarity(Rarity.UNCOMMON)),
            "heart_of_the_ruins"
    );
    public static final Item HEART_OF_THE_SWAMP     = register(new Item(new Item.Settings().rarity(Rarity.UNCOMMON)),
            "heart_of_the_swamp"
    );
    public static final Item HEART_OF_THE_TUNDRA    = register(new Item(new Item.Settings().rarity(Rarity.UNCOMMON)),
            "heart_of_the_tundra"
    );
    public static final Item HEART_OF_THE_WOODLANDS = register(new Item(new Item.Settings().rarity(Rarity.UNCOMMON)),
            "heart_of_the_woodlands"
    );

    public static final Item WARDEN_RIB = register(new Item(new Item.Settings()), "warden_rib");

    public static final Item PRISMARINE_SCALE = register(new Item(new Item.Settings()), "prismarine_scale");

    public static final Item PRISMARINE_CROWN = register(new CrownItem(ArmorItem.Type.HELMET,
            new Item.Settings().maxCount(1)
    ), "prismarine_crown");

    public static final List<Item> ITEMS = Arrays
            .stream(BEPItems.class.getDeclaredFields())
            .filter(field -> field.getType() == Item.class)
            .map(field -> {
                try {
                    return (Item) field.get(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access item field: " + field.getName(), e);
                }
            })
            .toList();

    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            Identifier.of(BEP.MOD_ID, "item_group")
    );

    public static <T extends Item> T register(T item, String id) {
        return Registry.register(Registries.ITEM, new Identifier(BEP.MOD_ID, id), item);
    }

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP,
                ITEM_GROUP,
                FabricItemGroup
                        .builder()
                        .icon(() -> new ItemStack(HEART_OF_THE_END))
                        .displayName(Text.of(BEP.MOD_ID.toUpperCase()))
                        .build()
        );
        for (var item : ITEMS)
            ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register((group) -> group.add(item));
    }

    private static class CrownItem extends ArmorItem {
        public CrownItem(ArmorItem.Type type, Settings settings) {
            super(new ArmorMaterial() {
                @Override
                public int getDurability(ArmorItem.Type type) {
                    return ArmorMaterials.GOLD.getDurability(type);
                }

                @Override
                public int getProtection(ArmorItem.Type type) {
                    return ArmorMaterials.GOLD.getProtection(type);
                }

                @Override
                public int getEnchantability() {
                    return 0;
                }

                @Override
                public SoundEvent getEquipSound() {
                    return SoundEvents.ITEM_ARMOR_EQUIP_TURTLE;
                }

                @Override
                public Ingredient getRepairIngredient() {
                    return Ingredient.ofItems(PRISMARINE_SCALE);
                }

                @Override
                public String getName() {
                    return "prismarine_scale";
                }

                @Override
                public float getToughness() {
                    return ArmorMaterials.GOLD.getToughness();
                }

                @Override
                public float getKnockbackResistance() {
                    return ArmorMaterials.GOLD.getKnockbackResistance();
                }
            }, type, settings);
        }

        @Override
        public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
            super.inventoryTick(stack, world, entity, slot, selected);

            // Apply the effect if the player is wearing the socks
            if (entity instanceof PlayerEntity player) {
                if (stack.getItem() instanceof CrownItem crown
                        && player.getEquippedStack(EquipmentSlot.HEAD).isOf(crown)
                        && player.isSubmergedInWater())
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 200, 0, false, true));
            }
        }
    }
}
