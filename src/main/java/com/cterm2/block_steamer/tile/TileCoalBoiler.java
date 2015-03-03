package com.cterm2.block_steamer.tile;

import net.minecraft.tileentity.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.play.server.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;

public class TileCoalBoiler extends TileEntity implements ISidedInventory
{
	private static final String keyCoalStack = "CoalStack";
	private static final String keyLastBurnTime = "LastBurnTime";
	private static final String keyMaxBurnTime = "MaxBurnTime";
	private ItemStack stack;
	private int lastBurnTime, maxBurnTime;

	private TileTank connectedTank = null;

	// Data ReadWrite
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		if(this.stack != null)
		{
			NBTTagCompound tag_stack = new NBTTagCompound();
			this.stack.writeToNBT(tag_stack);
			tag.setTag(keyCoalStack, tag_stack);
		}
		tag.setInteger(keyLastBurnTime, this.lastBurnTime);
		tag.setInteger(keyMaxBurnTime, this.maxBurnTime);
	}
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		if(tag.hasKey(keyCoalStack))
		{
			NBTTagCompound tag_stack = tag.getCompoundTag(keyCoalStack);
			this.stack = ItemStack.loadItemStackFromNBT(tag_stack);
		}
		else
		{
			this.stack = null;
		}
		this.lastBurnTime = tag.getInteger(keyLastBurnTime);
		this.maxBurnTime = tag.getInteger(keyMaxBurnTime);
	}

	// Network Synchronize
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag_packet = new NBTTagCompound();
		this.writeToNBT(tag_packet);
		return new S35PacketUpdateTileEntity(this.xCoord,
			this.yCoord, this.zCoord, 1, tag_packet);
	}
	@Override
	public void onDataPacket(NetworkManager network, 
		S35PacketUpdateTileEntity packet)
	{
		this.readFromNBT(packet.func_148857_g());
	}

	// TileConnection
	public void updateTileConnection()
	{
		System.out.println("Checking tile connection...");

		TileEntity tent_u = this.worldObj.getTileEntity(this.xCoord,
			this.yCoord + 1, this.zCoord);
		if(tent_u != null && tent_u instanceof TileTank)
		{
			// currenly tanks can use only in this mod
			System.out.println("Connection established.");
			this.connectedTank = (TileTank)tent_u;
		}
		else
		{
			System.out.println("Tile not found.");
			this.connectedTank = null;
		}
	}

	// IInventory implementation
	@Override
	public void openInventory(){}
	@Override
	public void closeInventory(){}
	
	@Override
	public String getInventoryName(){ return "inventory.coalBoiler"; }
	@Override
	public boolean hasCustomInventoryName(){ return false; }
	@Override
	public int getInventoryStackLimit(){ return 64; }
	@Override
	public int getSizeInventory(){ return 1; }
	@Override
	public boolean isUseableByPlayer(EntityPlayer player){ return true; }
	@Override
	public boolean isItemValidForSlot(int index, ItemStack item)
	{
		if(index != 0) return false;
		return TileEntityFurnace.isItemFuel(item);
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		if(index == 0) return this.stack;
		return null;
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		if(index == 0)
		{
			ItemStack temp = this.stack;
			this.stack = null;
			return temp;
		}
		return null;
	}
	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		if(stack != null)
		{
			if(stack.stackSize >= this.getInventoryStackLimit())
			{
				stack.stackSize = this.getInventoryStackLimit();
			}
		}

		if(index == 0)
		{
			this.stack = stack;
			if(this.stack != null &&
				this.stack.stackSize <= 0) this.stack = null;
		}
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if(index == 0 && this.stack != null)
		{
			ItemStack temp = null;

			if(this.stack.stackSize <= count)
			{
				temp = this.stack;
				this.stack = null;
			}
			else
			{
				temp = this.stack.splitStack(count);
				if(this.stack.stackSize <= 0) this.stack = null;
			}
			return temp;
		}
		return null;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack item, int side)
	{
		if(index != 0) return false;
		return item != null && this.stack != null &&
			item.getItem() == this.stack.getItem() &&
			item.getItemDamage() == this.stack.getItemDamage() &&
			item.stackSize <= this.stack.stackSize;
	}
	@Override
	public boolean canInsertItem(int index, ItemStack item, int side)
	{
		if(index != 0) return false;
		if(this.stack == null) return this.isItemValidForSlot(index, item);
		return this.stack.getItem() == item.getItem() &&
			this.stack.getItemDamage() == item.getItemDamage() &&
			stack.stackSize + item.stackSize <= getInventoryStackLimit();
	}
	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		// only accessable to index 0
		return new int[] { 0 };
	}
}

