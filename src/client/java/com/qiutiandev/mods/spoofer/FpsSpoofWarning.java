package com.qiutiandev.mods.spoofer;

import com.qiutiandev.mods.FakeFpsClient;

public class FpsSpoofWarning {

    public static final String WARNING_TEXT = " [SPOOFED]";
    public static final String WARNING_TEXT_COLORED = " [SPOOFED]";

    public static boolean isSpoofingActive() {
        return FakeFpsClient.getConfig() != null && FakeFpsClient.getConfig().isEnabled();
    }

    public static String getWarningSuffix() {
        return isSpoofingActive() ? WARNING_TEXT : "";
    }

    public static String getColoredWarningSuffix() {
        return isSpoofingActive() ? WARNING_TEXT_COLORED : "";
    }

    public static String appendWarning(String fpsString) {
        if (isSpoofingActive() && fpsString != null) {
            return fpsString + WARNING_TEXT_COLORED;
        }
        return fpsString;
    }

    public static String appendWarningPlain(String fpsString) {
        if (isSpoofingActive() && fpsString != null) {
            return fpsString + WARNING_TEXT;
        }
        return fpsString;
    }
}

