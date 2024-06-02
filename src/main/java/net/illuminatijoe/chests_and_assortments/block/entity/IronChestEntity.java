package net.illuminatijoe.chests_and_assortments.block.entity;

import net.illuminatijoe.chests_and_assortments.ChestsAndAssortments;
import net.illuminatijoe.chests_and_assortments.init.BlockEntityInit;
import net.illuminatijoe.chests_and_assortments.menu.IronChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class IronChestEntity extends AbstractChestEntity {

    public static final Component TITLE = Component.translatable(ChestsAndAssortments.MODID + ".iron_chest");

    public IronChestEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.IRON_CHEST_ENTITY.get(), pPos, pBlockState, 63);
    }

    @Override
    public Component getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new IronChestMenu(i, inventory, this);
    }
}
