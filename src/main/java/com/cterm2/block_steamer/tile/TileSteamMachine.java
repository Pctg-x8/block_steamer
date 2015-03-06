package com.cterm2.block_steamer.tile;

// TileEntity of Steaming Machine

import net.minecraft.tileentity.*;
import net.minecraft.nbt.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import net.minecraft.network.play.server.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;

public class TileSteamMachine extends TileEntity implements ISidedInventory
{
	private static final String KeyInput = "InputItems";
	private static final String KeyOutput = "OutputItems";

	ItemStack inputItems = null, outputItems = null;
	
	// Data ReadWrite
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		if(tag.hasKey(KeyInput))
		{
			this.inputItems = ItemStack.loadItemStackFromNBT(tag.getCompoundTag(KeyInput));
		}
		else
		{
			this.inputItems = null;
		}
		if(tag.hasKey(KeyOutput))
		{
			this.outputItems = ItemStack.loadItemStackFromNBT(tag.getCompoundTag(KeyOutput));
		}
		else
		{
			this.outputItems = null;
		}
	}
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		if(this.inputItems != null)
		{
			NBTTagCompound tag_input = new NBTTagCompound();
			this.inputItems.writeToNBT(tag_input);
			tag.setTag(KeyInput, tag_input);
		}
		if(this.outputItems != null)
		{
			NBTTagCompound tag_output = new NBTTagCompound();
			this.outputItems.writeToNBT(tag_output);
			tag.setTag(KeyOutput, tag_output);
		}
	}
	
	// Data Sync
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}
	@Override
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet)
	{
		this.readFromNBT(packet.func_148857_g());
	}

	/// IInventory ///
	@Override
	public void openInventory(){}
	@Override
	public void closeInventory(){}
	@Override
	public String getInventoryName(){ return "inventory.block_steamer:steamMachine"; }
	@Override
	public boolean hasCustomInventoryName(){ return false; }
	@Override
	public boolean isUseableByPlayer(EntityPlayer player){ return true; }
	@Override
	public int getInventoryStackLimit(){ return 64; }
	@Override
	public int getSizeInventory(){ return 2; }
	@Override
	public ItemStack getStackInSlot(int index)
	{
		switch(index)
		{
		case 0: return this.inputItems;
		case 1: return this.outputItems;
		default: return null;
		}
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		ItemStack s = this.getStackInSlot(index);

		switch(index)
		{
		case 0:
			this.inputItems = null;
			break;
		case 1:
			this.outputItems = null;
			break;
		}

		return s;
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		ItemStack sel = this.getStackInSlot(index);

		if(sel != null)
		{
			ItemStack st_ret = null;

			if(sel.stackSize <= count)
			{
				st_ret = sel;
				this.setInventorySlotContents(index, null);
			}
			else
			{
				st_ret = sel.splitStack(count);
				if(sel.stackSize <= 0) this.setInventorySlotContents(index, null);
			}
			return st_ret;
		}
		return null;
	}
	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		ItemStack slotTo = null;
		if(stack != null)
		{
			slotTo = stack.copy();
			if(slotTo.stackSize > this.getInventoryStackLimit()) slotTo.stackSize = this.getInventoryStackLimit();
		}

		switch(index)
		{
		case 0:
			this.inputItems = slotTo;
			break;
		case 1:
			this.outputItems = slotTo;
			break;
		}
	}
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return index == 0;
	}

	/// ISidedInventory ///
	@Override
	public boolean canExtractItem(int index, ItemStack stack, int side)
	{
		if(side == 1 && index == 0 && this.inputItems != null) return true;
		if(side != 1 && index == 1 && this.outputItems != null) return true;
		return false;
	}
	@Override
	public boolean canInsertItem(int index, ItemStack stack, int side)
	{
		if(side == 1 && index == 0 && this.inputItems == null) return true;
		return false;
	}
	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		if(side == 1) return new int[]{ 0 };
		return new int[]{ 1 };
	}
}

