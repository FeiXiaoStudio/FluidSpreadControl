package hrcaoc.disablefluidspread.mixin.client;

import hrcaoc.disablefluidspread.DisableFluidSpreadClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({GameRenderer.class})
public class MixinGameRenderer {
   @Redirect(
      method = {"Lnet/minecraft/client/render/GameRenderer;updateTargetedEntity(F)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;raycast(DFZ)Lnet/minecraft/util/hit/HitResult;"
)
   )
   private HitResult redirectPick(Entity instance, double rayTraceDistance, float partialTicks, boolean rayTraceFluids) {
      return instance.raycast(rayTraceDistance, partialTicks, rayTraceFluids || DisableFluidSpreadClient.FLUID_BLOCKS);
   }
}
