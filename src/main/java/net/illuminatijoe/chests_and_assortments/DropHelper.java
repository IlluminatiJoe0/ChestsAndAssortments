package net.illuminatijoe.chests_and_assortments;

import net.illuminatijoe.chests_and_assortments.block.entity.CopperChestEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;

public class DropHelper {
    public static void dropInventory(BlockEntity be){
        if(be instanceof CopperChestEntity){
            ItemStackHandler itemStackHandler = ((CopperChestEntity) be).getInventory();
            for (int i = 0; i < itemStackHandler.getSlots(); i++) {
                ItemStack stack = itemStackHandler.getStackInSlot(i);
                var entity = new ItemEntity(be.getLevel(), be.getBlockPos().getX()+0.5, be.getBlockPos().getY()+0.5, be.getBlockPos().getZ()+0.5, stack);
                be.getLevel().addFreshEntity(entity);
            }
        }
    }
}
