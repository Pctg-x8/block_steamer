package com.cterm2.block_steamer.block;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;

import com.cterm2.block_steamer.tile.*;

public class BlockCoalBoiler extends BlockContainer
{
	// coal boiler
	public BlockCoalBoiler()
	{
		super(Material.rock);
	
		this.setStepSound(soundTypeStone);
		this.setBlockName("coalBoiler");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileCoalBoiler();
	}
}

