package hrcaoc.disablefluidspread;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

public class DisableFluidSpreadClient implements ClientModInitializer {
	public static boolean FLUID_BLOCKS = false;

	public void onInitializeClient() {
		assert ClientCommandManager.getActiveDispatcher() != null;
		ClientCommandRegistrationCallback.EVENT.register(((commandDispatcher, commandRegistryAccess) -> {
			commandDispatcher.register(ClientCommandManager
					.literal("selectablefluids")
					.executes((context) -> {
						((FabricClientCommandSource)context.getSource()).sendFeedback(Text.literal("Selectable fluids is currently " + (FLUID_BLOCKS ? "enabled" : "disabled")));
						return 1;
					}).then(
							ClientCommandManager.literal("toggle").executes((context) -> this.setFluidBlocks(context, !FLUID_BLOCKS))
					).then(
							ClientCommandManager.literal("enable").executes((context) -> this.setFluidBlocks(context, true))
					).then(
							ClientCommandManager.literal("disable").executes((context) -> this.setFluidBlocks(context, false))
					).then(
							ClientCommandManager.literal("set").then(ClientCommandManager.argument("value", BoolArgumentType.bool()).executes((context) -> this.setFluidBlocks(context, BoolArgumentType.getBool(context, "value"))))
					)
			);
		}));
	}

	private int setFluidBlocks(CommandContext<FabricClientCommandSource> context, boolean value) {
		FLUID_BLOCKS = value;
		((FabricClientCommandSource)context.getSource()).sendFeedback(Text.literal("Selectable fluids is now " + (FLUID_BLOCKS ? "enabled" : "disabled")));
		return 1;
	}
}