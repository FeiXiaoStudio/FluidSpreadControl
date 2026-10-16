package me.salamander.nowaterspread;

import me.salamander.nowaterspread.mixin.GameRulesAccess;
import me.salamander.nowaterspread.mixin.GameRulesBooleanValueAccess;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.Category;

public class NoWaterSpread implements ModInitializer {
	public static final GameRules.Key<GameRules.BooleanRule> GAME_RULE_WATER_SPREAD;
	public static final GameRules.Key<GameRules.BooleanRule> GAME_RULE_LAVA_SPREAD;

	public void onInitialize() {
	}

	static {
		GAME_RULE_WATER_SPREAD = GameRulesAccess.<GameRules.BooleanRule>invokeRegister("doWaterSpread", Category.UPDATES, GameRulesBooleanValueAccess.invokeCreate(true));
		GAME_RULE_LAVA_SPREAD = GameRulesAccess.<GameRules.BooleanRule>invokeRegister("doLavaSpread", Category.UPDATES, GameRulesBooleanValueAccess.invokeCreate(true));
	}
}