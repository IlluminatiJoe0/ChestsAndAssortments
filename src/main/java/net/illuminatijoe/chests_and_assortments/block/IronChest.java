package net.illuminatijoe.chests_and_assortments.block;

import net.illuminatijoe.chests_and_assortments.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class IronChest extends AbstractChest{

    public IronChest(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return BlockEntityInit.IRON_CHEST_ENTITY.get().create(blockPos, blockState);
    }

}
