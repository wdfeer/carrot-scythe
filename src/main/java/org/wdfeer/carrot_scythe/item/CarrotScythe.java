package org.wdfeer.carrot_scythe.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.wdfeer.carrot_scythe.material.CarrotMaterial;

import java.util.List;

public class CarrotScythe extends HoeItem {
    public static final CarrotScythe INSTANCE = new CarrotScythe();

    public CarrotScythe() {
        super(new CarrotMaterial(), 3, 1.3f - 4f, new FabricItemSettings());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        MutableText base = Text.translatable("carrot_scythe.tooltip");
        MutableText number = Text.of(Integer.toString(stack.getOrCreateNbt().getInt("carrots"))).copy();
        tooltip.add(base.append(number).formatted(Formatting.GOLD));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (state.getBlock() == Blocks.CARROTS && ((CropBlock)Blocks.CARROTS).getAge(state) == 7){
            NbtCompound nbt = stack.getOrCreateNbt();
            nbt.putInt("carrots", nbt.getInt("carrots") + 1);
        }

        return super.postMine(stack, world, state, pos, miner);
    }
}
