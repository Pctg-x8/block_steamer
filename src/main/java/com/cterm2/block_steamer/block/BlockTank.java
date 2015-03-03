package com.cterm2.block_steamer.block;

// Tank Block which can contain single fluids

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;

import com.cterm2.block_steamer.*;
import com.cterm2.block_steamer.tile.*;

public class BlockTank extends BlockContainer
{
	public static final float ShrinkLevel = 1.0f / 16.0f;
	public static final String SideTextureName
		= "block_steamer:water_tank_side";
	public static final String TopTextureName
		= "block_steamer:water_tank_top";
	public static final String BottomTextureName
		= "block_steamer:water_tank_bottom";

	private IIcon sideTexture, topTexture, bottomTexture;

	public BlockTank()
	{
		super(Material.glass);
		
		this.setBlockName("tank");
		this.setBlockBounds(ShrinkLevel, 0.0f, ShrinkLevel,
				1.0f - ShrinkLevel, 1.0f, 1.0f - ShrinkLevel);
	}

	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		this.sideTexture = register.registerIcon(SideTextureName);
		this.topTexture = register.registerIcon(TopTextureName);
		this.bottomTexture = register.registerIcon(BottomTextureName);
	}
	@Override
	public IIcon getIcon(int side, int meta)
	{
		switch(side)
		{
		case 0: return this.bottomTexture;
		case 1: return this.topTexture;
		default: return this.sideTexture;
		}
	}
	@Override
	public boolean onBlockActivated(World world,
		int x, int y, int z, EntityPlayer player, int side,
		float posX, float posY, float posZ)
	{
		// right click handler
		if(world.isRemote) return true;

		TileEntity tent = world.getTileEntity(x, y, z);
		if(tent != null)
		{
			// pass to handler in tile
			((TileTank)tent).handleActivate(player);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return new TileTank();
	}
	@Override
	public boolean renderAsNormalBlock(){ return false; }
	@Override
	public boolean isOpaqueCube(){ return false; }
	@Override
	public int getRenderType()
	{
		return BlockSteamer.proxy.getRenderType(this.getClass());
	}
}

