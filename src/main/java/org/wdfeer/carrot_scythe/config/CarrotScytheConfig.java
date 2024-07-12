package org.wdfeer.carrot_scythe.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class CarrotScytheConfig extends MidnightConfig {
    @Entry(min = 0.0)
    public static double logMultiplier = 1.0;
    @Entry(min = 1.0)
    public static double linearDivisor = 1e4;
}
