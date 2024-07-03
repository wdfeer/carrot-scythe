package org.wdfeer.carrot_scythe;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wdfeer.carrot_scythe.item.CarrotScythe;

public class TheMod implements ModInitializer {
	public static final String MOD_ID = "carrot_scythe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "carrot_scythe"), CarrotScythe.INSTANCE);
	}
}