package uk.co.oathompsonjones.mixin;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.SwampHutGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SwampHutGenerator.class)
public abstract class SwampHutGeneratorMixin extends StructurePiece {
    protected SwampHutGeneratorMixin(StructurePieceType type, int length, BlockBox boundingBox) {
        super(type, length, boundingBox);
    }

    @Unique
    private static ItemStack getRandomPotion(Random random) {
        List<Potion> potions = List.of(Potions.POISON,
                Potions.POISON,
                Potions.WEAKNESS,
                Potions.WEAKNESS,
                Potions.SLOWNESS,
                Potions.SLOWNESS,
                Potions.HEALING,
                Potions.REGENERATION,
                Potions.STRENGTH,
                Potions.SWIFTNESS,
                Potions.FIRE_RESISTANCE
        );
        Potion potion = potions.get(random.nextInt(potions.size()));
        return PotionUtil.setPotion(
                new ItemStack(random.nextFloat() < 0.3f ? Items.SPLASH_POTION : Items.POTION),
                potion
        );
    }

    @Inject(method="generate", at=@At("TAIL"))
    private void addBarrel(
            StructureWorldAccess world,
            StructureAccessor structureAccessor,
            ChunkGenerator chunkGenerator,
            net.minecraft.util.math.random.Random random,
            BlockBox chunkBox,
            ChunkPos chunkPos,
            BlockPos pivot,
            CallbackInfo ci
    ) {
        // Add barrel
        BlockPos barrelPos = this.offsetPos(2, 2, 6);
        world.setBlockState(barrelPos, Blocks.BARREL.getDefaultState().with(BarrelBlock.FACING, Direction.UP), 3);
        if (world.getBlockEntity(barrelPos) instanceof LootableContainerBlockEntity lootable)
            lootable.setLootTable(new Identifier("bep", "chests/swamp_hut"), random.nextLong());

        // Add brewing stand
        BlockPos brewingStandPos = this.offsetPos(2, 3, 6);
        world.setBlockState(brewingStandPos, Blocks.BREWING_STAND.getDefaultState(), 3);
        if (world.getBlockEntity(brewingStandPos) instanceof BrewingStandBlockEntity brewingStand) {
            for (int i = 0; i < 3; i++) {
                if (random.nextFloat() < 0.5f) {
                    ItemStack potion = getRandomPotion(random);
                    brewingStand.setStack(i, potion);
                }
            }
        }
    }
}
