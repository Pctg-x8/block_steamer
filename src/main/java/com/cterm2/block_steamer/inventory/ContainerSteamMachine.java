package com.cterm2.block_steamer.inventory;

// Container of Steam Machine

import net.minecraft.inventory.*;
import net.minecraft.item.*;
import com.cterm2.block_steamer.tile.*;
import com.cterm2.block_steamer.inventory.*;
import net.minecraft.entity.player.*;

public class ContainerSteamMachine extends Container
{
	TileSteamMachine tile;

	public ContainerSteamMachine(EntityPlayer player, TileSteamMachine t)
	{
		this.tile = t;

		// custom inventory
		this.addSlotToContainer(new Slot(this.tile, 0, 89, 21));
		this.addSlotToContainer(new SlotOutput(this.tile, 1, 89, 57));

		// player inventory
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(player.inventory, 9 + i * 9 + j, 9 + j * 20, 95 + i * 20));
			}
		}
		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(player.inventory, i, 9 + i * 20, 157));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player){ return this.tile.isUseableByPlayer(player); }

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotFrom)
	{
		ItemStack stack = null;
		Slot sf = (Slot)this.inventorySlots.get(slotFrom);

		if(sf != null && sf.getHasStack())
		{
			ItemStack slotStack = sf.getStack();
			stack = slotStack.copy();

			if(slotFrom == 0 || slotFrom == 1)
			{
				// from input
				if(!this.mergeItemStack(slotStack, 2, 2 + 36, true)) return null;
			}
			else
			{
				// from player inventory
				if(!this.mergeItemStack(slotStack, 0, 0, false))
				{
					if(2 <= slotFrom && slotFrom <= 29)
					{
						if(!this.mergeItemStack(slotStack, 29, 38, false)) return null;
					}
					else
					{
						if(!this.mergeItemStack(slotStack, 2, 29, false)) return null;
					}
				}
			}

			if(slotStack.stackSize == 0)
			{
				sf.putStack(null);
			}
			else
			{
				sf.onSlotChanged();
			}

			if(slotStack.stackSize == stack.stackSize) return null;
			sf.onPickupFromSlot(player, slotStack);
		}

		return stack;
	}
}

