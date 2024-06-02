package net.illuminatijoe.chests_and_assortments.init;

import net.illuminatijoe.chests_and_assortments.ChestsAndAssortments;
import net.illuminatijoe.chests_and_assortments.menu.CopperChestMenu;
import net.illuminatijoe.chests_and_assortments.menu.IronChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ChestsAndAssortments.MODID);

    public static final RegistryObject<MenuType<CopperChestMenu>> COPPER_CHEST_MENU = MENU_TYPES.register("copper_chest_menu",
                ()-> IForgeMenuType.create(CopperChestMenu::new)
            );

    public static final RegistryObject<MenuType<IronChestMenu>> IRON_CHEST_MENU = MENU_TYPES.register("iron_chest_menu",
            ()-> IForgeMenuType.create(IronChestMenu::new)
    );
}
