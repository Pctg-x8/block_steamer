package com.cterm2.block_steamer;

// Proxy interface
import net.minecraft.block.*;

public interface IProxy
{
	public void registerRenderer();
	public int getRenderType(Object block_class);
}

