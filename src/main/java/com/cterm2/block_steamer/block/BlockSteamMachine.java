package com.cterm2.block_steamer.block;

// Steaming Machine Block

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.util.*;

import com.cterm2.block_steamer.*;
import com.cterm2.block_steamer.tile.*;
import com.cterm2.block_steamer.enums.*;

public class BlockSteamMachine extends BlockContainer
{
	private static final String SideTextureName = "block_steamer:steamer_side";
	private static final String TopBottomTextureName = "block_steamer:steamer_yplane";

	private IIcon sideTexture, tbTexture;

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

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int size, float posX, float posY, float posZ)
	{
		if(world.isRemote) return true;

		TileEntity t = world.getTileEntity(x, y, z);

		if(t != null)
		{
			player.openGui(BlockSteamer.instance, EnumGuiIdentifier.SteamMachine.ordinal(), world, x, y, z);
		}
		
		return true;
	}
	
	@Override
	public boolean renderAsNormalBlock(){ return false; }
	@Override
	public boolean isOpaqueCube(){ return false; }
	@Override
	public int getRenderType(){ return BlockSteamer.proxy.getRenderType(this); }

	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		this.sideTexture = register.registerIcon(SideTextureName);
		this.tbTexture = register.registerIcon(TopBottomTextureName);
	}
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if(side == 0 || side == 1) return this.tbTexture;
		return this.sideTexture;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileEntity t = world.getTileEntity(x, y, z);

		if(t != null)
		{
			((TileSteamMachine)t).handleBreak();
		}

		super.breakBlock(world, x, y, z, block, meta);
	}
}

