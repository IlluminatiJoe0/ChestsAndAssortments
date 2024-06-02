package net.illuminatijoe.chests_and_assortments.init;

import net.illuminatijoe.chests_and_assortments.ChestsAndAssortments;
import net.illuminatijoe.chests_and_assortments.block.CopperChest;
import net.illuminatijoe.chests_and_assortments.block.IronChest;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ChestsAndAssortments.MODID);

    public static final RegistryObject<CopperChest> COPPER_CHEST_ENTITY_BLOCK = BLOCKS.register("copper_chest_block",
            () -> new CopperChest(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).noOcclusion()));

    public static final RegistryObject<IronChest> IRON_CHEST_ENTITY_BLOCK = BLOCKS.register("iron_chest_block",
            () -> new IronChest(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
}
