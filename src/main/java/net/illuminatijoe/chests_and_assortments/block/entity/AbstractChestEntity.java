package net.illuminatijoe.chests_and_assortments.block.entity;

import net.illuminatijoe.chests_and_assortments.ChestsAndAssortments;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractChestEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemStackHandler;
    private final LazyOptional<ItemStackHandler> optional;

    public AbstractChestEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int size) {
        super(type, pos, state);
        this.itemStackHandler = new ItemStackHandler(size) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                AbstractChestEntity.this.setChanged();
            }
        };
        this.optional = LazyOptional.of(() -> this.itemStackHandler);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        CompoundTag data = pTag.getCompound(ChestsAndAssortments.MODID);
        this.itemStackHandler.deserializeNBT(data.getCompound("inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        CompoundTag data = new CompoundTag();
        data.put("inventory", this.itemStackHandler.serializeNBT());
        pTag.put(ChestsAndAssortments.MODID, data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return this.optional.cast();
        } else {
            return super.getCapability(cap);
        }
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.optional.invalidate();
    }

    public ItemStackHandler getInventory() {
        return this.itemStackHandler;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.itemStackHandler.getStackInSlot(slot);
    }

    public void setItemInSlot(int slot, ItemStack stack) {
        this.itemStackHandler.setStackInSlot(slot, stack);
    }

    public LazyOptional<ItemStackHandler> getOptional() {
        return this.optional;
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public abstract Component getDisplayName();

    @Nullable
    @Override
    public abstract AbstractContainerMenu createMenu(int i, Inventory inventory, Player player);
}
