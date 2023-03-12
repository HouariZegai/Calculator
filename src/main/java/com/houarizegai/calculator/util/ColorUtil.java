package com.houarizegai.calculator.util;

import java.awt.*;
import java.util.Optional;

public class ColorUtil {

    private ColorUtil() {
        throw new AssertionError("Constructor is not allowed");
    }

    public static Color hex2Color(String colorHex) {
        return Optional.ofNullable(colorHex)
                .map(hex -> new Color(
                        Integer.valueOf(colorHex.substring(0, 2), 16),
                        Integer.valueOf(colorHex.substring(2, 4), 16),
                        Integer.valueOf(colorHex.substring(4, 6), 16)))
                .orElse(null);
    }
}
