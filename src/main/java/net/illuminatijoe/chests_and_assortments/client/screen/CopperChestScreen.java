package net.illuminatijoe.chests_and_assortments.client.screen;

import net.illuminatijoe.chests_and_assortments.ChestsAndAssortments;
import net.illuminatijoe.chests_and_assortments.menu.CopperChestMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CopperChestScreen extends AbstractContainerScreen<CopperChestMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ChestsAndAssortments.MODID, "textures/gui/copper_chest.png");
//    private static final int inventoryLabelX = 7;
//    private static final int inventoryLabelY = 108;

    public CopperChestScreen(CopperChestMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        this.imageHeight = 203;
        this.imageWidth = 176;
        this.inventoryLabelX = 7;
        this.inventoryLabelY = 109;
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
