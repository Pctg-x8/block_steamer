package com.cterm2.block_steamer.client;

// Client Proxy

import net.minecraft.block.*;
import cpw.mods.fml.client.registry.*;
import com.cterm2.block_steamer.*;
import com.cterm2.block_steamer.block.*;
import com.cterm2.block_steamer.tile.*;
import com.cterm2.block_steamer.client.renderer.*;
import com.cterm2.block_steamer.client.renderer.block.*;

public class ClientProxy implements IProxy
{
	public int tankRenderType;

	public void registerRenderer()
	{
		this.tankRenderType = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new BlockRendererTank());
		ClientRegistry.bindTileEntitySpecialRenderer(
			TileTank.class, new RendererTank());
	}
	public int getRenderType(Object block)
	{
		if(block instanceof BlockTank)
		{
			return this.tankRenderType;
		}
		return 0;
	}
}

