package com.cterm2.block_steamer.tile;

// TileEntity of Steaming Machine

import net.minecraft.tileentity.*;
import net.minecraft.nbt.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import net.minecraft.network.play.server.*;

public class TileSteamMachine extends TileEntity
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
}

