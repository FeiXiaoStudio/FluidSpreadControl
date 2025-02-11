package hrcaoc.disablefluidspread.mixin;

import hrcaoc.disablefluidspread.DisableFluidSpread;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({LavaFluid.class})
public abstract class MixinLavaFluid extends FlowableFluid {
   public void onScheduledTick(World level, BlockPos pos, FluidState state) {
      if (level.getGameRules().getBoolean(DisableFluidSpread.GAME_RULE_LAVA_SPREAD)) {
         super.onScheduledTick(level, pos, state);
      }

   }
}
