package hrcaoc.disablefluidspread.mixin;

import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({GameRules.BooleanRule.class})
public interface GameRulesBooleanValueAccess {
   @Invoker
   static GameRules.Type<GameRules.BooleanRule> invokeCreate(boolean value) {
      throw new AssertionError();
   }
}
