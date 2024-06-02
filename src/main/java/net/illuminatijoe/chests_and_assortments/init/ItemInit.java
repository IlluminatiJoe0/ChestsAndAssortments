package net.illuminatijoe.chests_and_assortments.init;

import net.illuminatijoe.chests_and_assortments.ChestsAndAssortments;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.illuminatijoe.chests_and_assortments.init.CreativeTabInit.addToTab;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChestsAndAssortments.MODID);

    public static final RegistryObject<BlockItem> COPPER_CHEST_ENTITY_ITEM = addToTab(ITEMS.register("copper_chest_item",
            () -> new BlockItem(BlockInit.COPPER_CHEST_ENTITY_BLOCK.get(), new Item.Properties())
            ));
}
