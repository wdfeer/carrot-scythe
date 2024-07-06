package org.wdfeer.carrot_scythe.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class CarrotScytheConfig extends MidnightConfig {
    @Entry(category = "damage", min = 0.0)
    public static Double logMultiplier = 1.0;
    @Entry(category = "damage", min = 1.0)
    public static Double linearDivisor = 1e5;
}
