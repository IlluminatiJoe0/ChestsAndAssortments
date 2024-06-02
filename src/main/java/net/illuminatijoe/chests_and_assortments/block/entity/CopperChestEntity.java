package net.illuminatijoe.chests_and_assortments.block.entity;

import net.illuminatijoe.chests_and_assortments.ChestsAndAssortments;
import net.illuminatijoe.chests_and_assortments.init.BlockEntityInit;
import net.illuminatijoe.chests_and_assortments.menu.CopperChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CopperChestEntity extends AbstractChestEntity {

    public static final Component TITLE = Component.translatable(ChestsAndAssortments.MODID + ".copper_chest");

    public CopperChestEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.COPPER_CHEST_ENTITY.get(), pPos, pBlockState, 45);
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
