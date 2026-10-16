package hrcaoc.disablefluidspread.mixin.client;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.fluid.FluidState;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ClientPlayerInteractionManager.class})
public class MixinMultiPlayerGameMode {
   @Shadow
   @Final
   private MinecraftClient client;

   @Redirect(
      method = {"Lnet/minecraft/client/network/ClientPlayerInteractionManager;breakBlock(Lnet/minecraft/util/math/BlockPos;)Z"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/fluid/FluidState;getBlockState()Lnet/minecraft/block/BlockState;"
)
   )
   private BlockState allowBreakingWater(FluidState instance, BlockPos pos) {
      BlockState blockState = this.client.world.getBlockState(pos);
      return blockState.getBlock() instanceof FluidBlock ? Blocks.AIR.getDefaultState() : instance.getBlockState();
   }
}
