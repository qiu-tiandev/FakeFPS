package com.qiutiandev.mods.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qiutiandev.mods.FakeFps;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FpsConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_FILE_NAME = "fps-spoofer-config.json";

    private boolean enabled;
    private double fpsMultiplier;
    private double fluctuationRange;

    public FpsConfig() {
        this.enabled = false;
        this.fpsMultiplier = 2.0;
        this.fluctuationRange = 0.05;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public double getFpsMultiplier() {
        return fpsMultiplier;
    }

    public double getFluctuationRange() {
        return fluctuationRange;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        save();
    }

    public void setFpsMultiplier(double fpsMultiplier) {
        this.fpsMultiplier = Math.max(0.1, fpsMultiplier);
        save();
    }

    public void setFluctuationRange(double fluctuationRange) {
        this.fluctuationRange = Math.max(0.0, fluctuationRange);
        save();
    }

    public static FpsConfig load() {
        File configFile = getConfigFile();

        if (!configFile.exists()) {
            FpsConfig config = new FpsConfig();
            config.save();
            return config;
        }

        try (FileReader reader = new FileReader(configFile)) {
            FpsConfig config = GSON.fromJson(reader, FpsConfig.class);
            if (config == null) {
                return new FpsConfig();
            }
            return config;
        } catch (Exception e) {
            FakeFps.LOGGER.error("Failed to load config, using defaults", e);
            return new FpsConfig();
        }
    }

    public void save() {
        File configFile = getConfigFile();

        try {
            File parentDir = configFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            try (FileWriter writer = new FileWriter(configFile)) {
                GSON.toJson(this, writer);
            }
        } catch (IOException e) {
            FakeFps.LOGGER.error("Failed to save config", e);
        }
    }

    private static File getConfigFile() {
        return new File("config", CONFIG_FILE_NAME);
    }
}

