package uk.co.oathompsonjones.mixin;

import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.oathompsonjones.BEPItems;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method="dropLoot", at=@At("HEAD"))
    private void bep$dropLoot(DamageSource damageSource, boolean causedByPlayer, CallbackInfo ci) {
        if (causedByPlayer) {
            String type = this.getType().getName().getString();

            switch (type) {
                case "Warden" -> this.dropStack(new ItemStack(BEPItems.WARDEN_RIB));
                case "Elder Guardian" -> this.dropStack(new ItemStack(BEPItems.PRISMARINE_SCALE));
            }
        }
    }
}
