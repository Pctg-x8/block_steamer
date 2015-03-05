package com.cterm2.block_steamer.block;

// Steaming Machine Block

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import com.cterm2.block_steamer.tile.*;

public class BlockSteamMachine extends BlockContainer
{
	public BlockSteamMachine()
	{
		super(Material.grass);

		this.setBlockName("block_steamer:steamMachine");
		this.setStepSound(soundTypeGrass);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileSteamMachine();
	}
}

