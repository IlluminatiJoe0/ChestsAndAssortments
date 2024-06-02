package net.illuminatijoe.chests_and_assortments.init;

import net.illuminatijoe.chests_and_assortments.ChestsAndAssortments;
import net.illuminatijoe.chests_and_assortments.block.entity.CopperChestEntity;
import net.illuminatijoe.chests_and_assortments.block.entity.IronChestEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ChestsAndAssortments.MODID);

    public static final RegistryObject<BlockEntityType<CopperChestEntity>> COPPER_CHEST_ENTITY =
            BLOCK_ENTITIES.register("copper_chest_block_entity",
                    () -> BlockEntityType.Builder.of(CopperChestEntity::new, BlockInit.COPPER_CHEST_ENTITY_BLOCK.get()).build(null)
            );

    public static final RegistryObject<BlockEntityType<IronChestEntity>> IRON_CHEST_ENTITY =
            BLOCK_ENTITIES.register("iron_chest_block_entity",
                    () -> BlockEntityType.Builder.of(IronChestEntity::new, BlockInit.IRON_CHEST_ENTITY_BLOCK.get()).build(null)
            );
}
