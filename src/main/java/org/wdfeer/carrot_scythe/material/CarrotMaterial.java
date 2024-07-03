package org.wdfeer.carrot_scythe.material;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

public class CarrotMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return ToolMaterials.STONE.getDurability();
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return ToolMaterials.STONE.getMiningSpeedMultiplier();
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 12;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.CARROT);
    }
}
