package me.salamander.nowaterspread.mixin.client;

import me.salamander.nowaterspread.NoWaterSpreadClient;
import net.minecraft.item.BucketItem;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({BucketItem.class})
public class MixinBucketItem {
   @Redirect(
      method = {"Lnet/minecraft/item/BucketItem;use(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/TypedActionResult;"},
      at = @At(
   value = "FIELD",
   target = "Lnet/minecraft/world/RaycastContext$FluidHandling;NONE:Lnet/minecraft/world/RaycastContext$FluidHandling;"
)
   )
   private RaycastContext.FluidHandling redirectFluid() {
      return NoWaterSpreadClient.FLUID_BLOCKS ? FluidHandling.ANY : FluidHandling.NONE;
   }
}
