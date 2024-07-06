package org.wdfeer.carrot_scythe.item;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.wdfeer.carrot_scythe.TheMod;
import org.wdfeer.carrot_scythe.config.CarrotScytheConfig;
import org.wdfeer.carrot_scythe.material.CarrotMaterial;

import java.util.List;
import java.util.UUID;

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

    private static void onCarrotHarvest(ItemStack stack) {
        incrementCarrots(stack);
        stack.setDamage(0);
    }

    private static void incrementCarrots(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt("carrots", nbt.getInt("carrots") + 1);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (state.getBlock() == Blocks.CARROTS && ((CropBlock)Blocks.CARROTS).getAge(state) == 7){
            onCarrotHarvest(stack);
        }

        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
        var baseAttributes = ArrayListMultimap.create(getAttributeModifiers(slot));
        if (slot == EquipmentSlot.MAINHAND){
            double damage = getExtraDamage(stack);
            baseAttributes.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(UUID.nameUUIDFromBytes(TheMod.MOD_ID.getBytes()),
                            TheMod.MOD_ID,
                            damage,
                            EntityAttributeModifier.Operation.ADDITION));
        }
        return baseAttributes;
    }

    private double getExtraDamage(ItemStack stack){
        double carrots = stack.getOrCreateNbt().getInt("carrots");
        return CarrotScytheConfig.logMultiplier * Math.log(carrots + 1) + carrots / CarrotScytheConfig.linearDivisor;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));

        return true;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var result = super.useOnBlock(context);
        if (result == ActionResult.PASS && context.getPlayer() != null){
            BlockState state = context.getWorld().getBlockState(context.getBlockPos());
            if (state.getBlock() == Blocks.CARROTS && ((CropBlock)Blocks.CARROTS).getAge(state) == 7){
                context.getWorld().breakBlock(context.getBlockPos(), true, context.getPlayer());

                onCarrotHarvest(context.getStack());

                return ActionResult.SUCCESS;
            }
        }
        return result;
    }
}
