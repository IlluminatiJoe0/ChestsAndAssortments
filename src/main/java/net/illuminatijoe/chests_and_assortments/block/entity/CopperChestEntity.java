package net.illuminatijoe.chests_and_assortments.block.entity;

import net.illuminatijoe.chests_and_assortments.ChestsAndAssortments;
import net.illuminatijoe.chests_and_assortments.init.BlockEntityInit;
import net.illuminatijoe.chests_and_assortments.menu.CopperChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CopperChestEntity extends BlockEntity implements MenuProvider {

    public static final Component TITLE = Component.translatable(ChestsAndAssortments.MODID + ".copper_chest");

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(45) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            CopperChestEntity.this.setChanged();
        }
    };

    private final LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.itemStackHandler);

    public CopperChestEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.COPPER_CHEST_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        CompoundTag data = pTag.getCompound(ChestsAndAssortments.MODID);
        this.itemStackHandler.deserializeNBT(data.getCompound("inventory"));
        // value = data.get("...");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);

        CompoundTag data = new CompoundTag();
        data.put("inventory", this.itemStackHandler.serializeNBT());
        // data.put("...", value);
        pTag.put(ChestsAndAssortments.MODID, data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
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

    public ItemStackHandler getInventory(){
        return this.itemStackHandler;
    }

    public ItemStack getStackInSlot(int slot){
        return this.itemStackHandler.getStackInSlot(slot);
    }

    public void setItemInSlot(int slot, ItemStack stack){
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
    public Component getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new CopperChestMenu(i, inventory, this);
    }
}
