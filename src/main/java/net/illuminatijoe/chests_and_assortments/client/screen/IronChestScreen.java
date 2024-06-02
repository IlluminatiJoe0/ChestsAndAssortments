package net.illuminatijoe.chests_and_assortments.client.screen;

import net.illuminatijoe.chests_and_assortments.ChestsAndAssortments;
import net.illuminatijoe.chests_and_assortments.menu.IronChestMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class IronChestScreen extends AbstractContainerScreen<IronChestMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ChestsAndAssortments.MODID, "textures/gui/iron_chest.png");

    public IronChestScreen(IronChestMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        this.imageHeight = 239; //TODO: fix inventory rendering
        this.imageWidth = 176;
        this.inventoryLabelX = 7;
        this.inventoryLabelY = 146;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        renderBackground(guiGraphics);
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
