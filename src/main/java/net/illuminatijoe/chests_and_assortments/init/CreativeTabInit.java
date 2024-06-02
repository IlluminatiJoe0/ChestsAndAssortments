package net.illuminatijoe.chests_and_assortments.init;

import net.illuminatijoe.chests_and_assortments.ChestsAndAssortments;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ChestsAndAssortments.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreativeTabInit {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ChestsAndAssortments.MODID);

    public static final List<Supplier<? extends ItemLike>> CHESTS_AND_ASSORTMENTS_TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> CHESTS_AND_ASSORTMENTS_TAB = TABS.register("chests_and_assortments_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.chests_and_assortments_tab"))
                    .icon(Items.CHEST::getDefaultInstance)
                    .displayItems((displayParams, output) ->
                            CHESTS_AND_ASSORTMENTS_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
                    .withSearchBar()
                    .build()
    );

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        CHESTS_AND_ASSORTMENTS_TAB_ITEMS.add(itemLike);
        return itemLike;
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.getEntries().putAfter(Items.ACACIA_LOG.getDefaultInstance(), Items.CHEST.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
