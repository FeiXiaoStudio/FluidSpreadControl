package me.salamander.nowaterspread.mixin.client;

import me.salamander.nowaterspread.NoWaterSpreadClient;
import net.minecraft.world.BlockView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({WorldRenderer.class})
public class MixinLevelRenderer {
   @Redirect(
      method = {"Lnet/minecraft/client/render/WorldRenderer;drawBlockOutline(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/entity/Entity;DDDLnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/block/BlockState;getOutlineShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;"
)
   )
   private VoxelShape getFluidShape(BlockState instance, BlockView blockGetter, BlockPos blockPos, ShapeContext collisionContext) {
      VoxelShape defaultShape = instance.getOutlineShape(blockGetter, blockPos, collisionContext);
      return defaultShape.isEmpty() && NoWaterSpreadClient.FLUID_BLOCKS ? instance.getFluidState().getShape(blockGetter, blockPos) : defaultShape;
   }
}
