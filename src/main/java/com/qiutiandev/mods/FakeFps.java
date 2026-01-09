package com.qiutiandev.mods;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeFps implements ModInitializer {
	public static final String MOD_ID = "fake-fps";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("FakeFPS mod loading...");
	}
}