package com.qiutiandev.mods;

import com.qiutiandev.mods.command.FpsSpoofCommand;
import com.qiutiandev.mods.config.FpsConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class FakeFpsClient implements ClientModInitializer {
	private static FpsConfig config;

	@Override
	public void onInitializeClient() {
		config = FpsConfig.load();
		FakeFps.LOGGER.info("FPS Spoofer initialized");

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
			FpsSpoofCommand.register(dispatcher, config)
		);
	}

	public static FpsConfig getConfig() {
		return config;
	}
}

