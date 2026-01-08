package com.qiutiandev.mods.spoofer;

import com.qiutiandev.mods.config.FpsConfig;
import java.util.Random;

public class FpsSpoofer {
    private static final Random RANDOM = new Random();
    private static final long FLUCTUATION_UPDATE_INTERVAL = 100;
    private static long lastFluctuationTime = 0;
    private static double currentFluctuation = 0;

    public static int modifyFps(int originalFps, FpsConfig config) {
        if (!config.isEnabled() || originalFps <= 0) {
            return originalFps;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFluctuationTime > FLUCTUATION_UPDATE_INTERVAL) {
            double fluctuationRange = config.getFluctuationRange();
            currentFluctuation = (RANDOM.nextDouble() * 2 - 1) * fluctuationRange;
            lastFluctuationTime = currentTime;
        }

        double multiplier = config.getFpsMultiplier();
        double fluctuationFactor = 1.0 + currentFluctuation;
        return (int) Math.round(originalFps * multiplier * fluctuationFactor); //make fps look more real
    }
}

