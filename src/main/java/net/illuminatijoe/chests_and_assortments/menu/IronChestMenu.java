package net.illuminatijoe.chests_and_assortments.menu;

import net.illuminatijoe.chests_and_assortments.block.IronChest;
import net.illuminatijoe.chests_and_assortments.block.entity.CopperChestEntity;
import net.illuminatijoe.chests_and_assortments.block.entity.IronChestEntity;
import net.illuminatijoe.chests_and_assortments.init.BlockInit;
import net.illuminatijoe.chests_and_assortments.init.MenuInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;

public class IronChestMenu extends AbstractContainerMenu {

    private final IronChestEntity blockEntity;
    private final ContainerLevelAccess levelAccess;

    // server constructor
    public IronChestMenu(int pContainerId, Inventory inventory, BlockEntity blockEntity) {
        super(MenuInit.IRON_CHEST_MENU.get(), pContainerId);
        if(blockEntity instanceof IronChestEntity be){
            this.blockEntity = be;
        } else {
            throw new IllegalStateException("Incorrect Block Entity Class %s passed into Menu".formatted(IronChestEntity.class.getCanonicalName()));
        }

        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        createPlayerHotbar(inventory);
        createPlayerInventory(inventory);
        createEntityInventory(be);
    }

    private void createEntityInventory(IronChestEntity copperChestEntity) {
        copperChestEntity.getOptional().ifPresent(inventory -> {
            for (int row = 0; row < 7; row++) {
                for (int collumn = 0; collumn < 9; collumn++) {
                    addSlot(new SlotItemHandler(inventory, collumn + row*9, 8 + collumn*18, 18 + row*18));
                }
            }
        });
    }

    private void createPlayerInventory(Inventory inventory) {
        for (int row = 0; row < 3; row++) {
            for (int collumn = 0; collumn < 9; collumn++) {
                addSlot(new Slot(inventory, 9 + collumn + row*9, 8 + collumn*18, 121 + row*18));
            }
        }
    }

    private void createPlayerHotbar(Inventory inventory) {
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(inventory, i, 8 + (i*18), 179));
        }
    }

    // client constructor
    public IronChestMenu(int pContainerId, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
        this(pContainerId, inventory, inventory.player.level().getBlockEntity(friendlyByteBuf.readBlockPos()));
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 63;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.levelAccess, player, BlockInit.IRON_CHEST_ENTITY_BLOCK.get());
    }

    public IronChestEntity getBlockEntity() {
        return this.blockEntity;
    }
}
