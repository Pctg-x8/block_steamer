package com.cterm2.block_steamer.server;

// Server Proxy

import net.minecraft.block.*;
import com.cterm2.block_steamer.*;

public class ServerProxy implements IProxy
{
	public void registerRenderer(){}
	public int getRenderType(Object block_class)
	{
		return 0;
	}
}

