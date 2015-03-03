package com.cterm2.block_steamer.client;

// Client Proxy

import net.minecraft.block.*;
import com.cterm2.block_steamer.*;

public class ClientProxy implements IProxy
{
	public void registerRenderer()
	{

	}
	public int getRenderType(Class<? extends Block> block_class)
	{
		return 0;
	}
}

