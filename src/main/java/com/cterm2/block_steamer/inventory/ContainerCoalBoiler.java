package com.cterm2.block_steamer.inventory;

// Container class of CoalBoiler

import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;

import com.cterm2.block_steamer.tile.*;

public class ContainerCoalBoiler extends Container
{
	private TileCoalBoiler tent;

	public ContainerCoalBoiler(EntityPlayer player,
		TileCoalBoiler t)
	{
		this.tent = t;

		// container slots
		this.addSlotToContainer(new Slot(t, 0, 76, 39));

		// init player slots
		// primary
		for(int i = 0; i < 9; i++)
		{
			Slot s = new Slot(player.inventory, i, 9 + i * 20, 137);
			this.addSlotToContainer(s);
		}
		// matrix
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				Slot s = new Slot(player.inventory, 9 + i * 9 + j,
					9 + j * 20, 75 + i * 20);
				this.addSlotToContainer(s);
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.tent.isUseableByPlayer(player);
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotFrom)
	{
		ItemStack stack = null;
		Slot sf = (Slot)this.inventorySlots.get(slotFrom);

		if(sf != null && sf.getHasStack())
		{
			ItemStack slotStack = sf.getStack();
			stack = slotStack.copy();

			if(slotFrom == 0)
			{
				// from 0
				if(!this.mergeItemStack(slotStack, 1, 37, true))
				{
					return null;
				}
			}
			else
			{
				// from player inventory
				if(TileEntityFurnace.isItemFuel(slotStack))
				{
					if(!this.mergeItemStack(slotStack, 0, 1, true))
					{
						return null;
					}
				}
				else
				{
					if(1 <= slotFrom && slotFrom <= 9)
					{
						if(!this.mergeItemStack(slotStack, 10, 37, false))
						{
							return null;
						}
					}
					else if(!this.mergeItemStack(slotStack, 1, 9, false))
					{
						return null;
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

			if(slotStack.stackSize == stack.stackSize)
			{
				return null;
			}
			sf.onPickupFromSlot(player, slotStack);
		}

		return stack;
	}
}

