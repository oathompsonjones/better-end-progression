package uk.co.oathompsonjones.mixin;

import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(method="getRarity", at=@At("HEAD"), cancellable=true)
    private void bep$getRarity(ItemStack stack, CallbackInfoReturnable<Rarity> cir) {
        if ((Item) (Object) this instanceof EnderEyeItem)
            cir.setReturnValue(Rarity.EPIC);
    }
}
