package org.wdfeer.carrot_scythe.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import org.wdfeer.carrot_scythe.material.CarrotMaterial;

public class CarrotScythe extends HoeItem {
    public static final CarrotScythe INSTANCE = new CarrotScythe();

    public CarrotScythe() {
        super(new CarrotMaterial(), 3, 1.3f - 4f, new FabricItemSettings());
    }
}
