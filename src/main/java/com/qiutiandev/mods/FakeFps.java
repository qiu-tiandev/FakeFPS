package com.qiutiandev.mods;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeFps implements ModInitializer {
	public static final String MOD_ID = "fake-fps";

	// Logger for the FPS Spoofer mod
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Initialize the FPS Spoofer mod
		LOGGER.info("FPS Spoofer mod loading...");
	}
}