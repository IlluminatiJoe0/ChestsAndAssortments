package net.illuminatijoe.chests_and_assortments.block;

import net.illuminatijoe.chests_and_assortments.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CopperChest extends AbstractChest {

    //constructor
    public CopperChest(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    // creates the entity
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return BlockEntityInit.COPPER_CHEST_ENTITY.get().create(blockPos, blockState);
    }
}
