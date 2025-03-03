package org.wdfeer.carrot_scythe;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.wdfeer.carrot_scythe.config.CarrotScytheConfig;
import org.wdfeer.carrot_scythe.item.CarrotScythe;

public class TheMod implements ModInitializer {
	public static final String MOD_ID = "carrot_scythe";

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "carrot_scythe"), CarrotScythe.INSTANCE);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(new ItemStack(CarrotScythe.INSTANCE)));

		MidnightConfig.init(MOD_ID, CarrotScytheConfig.class);
	}
}