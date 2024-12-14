package me.salamander.nowaterspread.client;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.text.LiteralText;

public class NoWaterSpreadClient implements ClientModInitializer {
   public static boolean FLUID_BLOCKS = false;

   public void onInitializeClient() {
      ClientCommandManager.DISPATCHER.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)ClientCommandManager.literal("selectablefluids").executes((context) -> {
         ((FabricClientCommandSource)context.getSource()).sendFeedback(new LiteralText("Selectable fluids is currently " + (FLUID_BLOCKS ? "enabled" : "disabled")));
         return 1;
      })).then(ClientCommandManager.literal("toggle").executes((context) -> this.setFluidBlocks(context, !FLUID_BLOCKS)))).then(ClientCommandManager.literal("enable").executes((context) -> this.setFluidBlocks(context, true)))).then(ClientCommandManager.literal("disable").executes((context) -> this.setFluidBlocks(context, false)))).then(ClientCommandManager.literal("set").then(ClientCommandManager.argument("value", BoolArgumentType.bool()).executes((context) -> this.setFluidBlocks(context, BoolArgumentType.getBool(context, "value"))))));
   }

   private int setFluidBlocks(CommandContext<FabricClientCommandSource> context, boolean value) {
      FLUID_BLOCKS = value;
      ((FabricClientCommandSource)context.getSource()).sendFeedback(new LiteralText("Selectable fluids is now " + (FLUID_BLOCKS ? "enabled" : "disabled")));
      return 1;
   }
}
