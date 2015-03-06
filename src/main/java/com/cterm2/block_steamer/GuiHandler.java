package com.cterm2.block_steamer;

// Gui Handler

import net.minecraft.entity.player.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import cpw.mods.fml.common.network.*;

import com.cterm2.block_steamer.enums.*;
import com.cterm2.block_steamer.inventory.*;
import com.cterm2.block_steamer.client.gui.*;
import com.cterm2.block_steamer.tile.*;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player,
		World world, int x, int y, int z)
	{
		TileEntity tent = world.getTileEntity(x, y, z);
		if(tent == null) return null;

		switch(EnumGuiIdentifier.values()[id])
		{
		case CoalBoiler:
			return new ContainerCoalBoiler(player, (TileCoalBoiler)tent);
		case SteamMachine:
			return new ContainerSteamMachine(player, (TileSteamMachine)tent);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player,
		World world, int x, int y, int z)
	{
		TileEntity tent = world.getTileEntity(x, y, z);
		if(tent == null) return null;

		switch(EnumGuiIdentifier.values()[id])
		{
		case CoalBoiler:
			return new ContainerCoalBoilerGui(player, (TileCoalBoiler)tent);
		case SteamMachine:
			return new ContainerSteamMachineGui(player, (TileSteamMachine)tent);
		default:
			return null;
		}
	}
}

