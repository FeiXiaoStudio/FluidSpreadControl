package me.salamander.nowaterspread.mixin;

import me.salamander.nowaterspread.client.NoWaterSpreadClient;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.block.Block;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({FluidBlock.class})
public abstract class MixinLiquidBlock extends Block {
   public MixinLiquidBlock(AbstractBlock.Settings properties) {
      super(properties);
      throw new RuntimeException("Mixin failed to apply!");
   }

   public boolean canReplace(BlockState state, ItemPlacementContext useContext) {
      return NoWaterSpreadClient.FLUID_BLOCKS ? false : super.canReplace(state, useContext);
   }

   public boolean canBucketPlace(BlockState state, Fluid fluid) {
      return NoWaterSpreadClient.FLUID_BLOCKS ? false : super.canBucketPlace(state, fluid);
   }
}
