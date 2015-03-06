package com.cterm2.block_steamer.inventory;

// Slot for output

import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class SlotOutput extends Slot
{
	public SlotOutput(IInventory inventory, int idx, int x, int y)
	{
		super(inventory, idx, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return false;
	}
}

