package com.cterm2.block_steamer;

// Item dropping helper

import java.util.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.item.*;
import net.minecraft.nbt.*;

public class DropHelper
{
	public static void dropStackAsEntity(World w, int x, int y, int z, ItemStack s)
	{
		if(s == null) return;

		Random r = new Random();
		float xoffs = r.nextFloat() * 0.8f + 0.1f;
		float yoffs = r.nextFloat() * 0.8f + 0.1f;
		float zoffs = r.nextFloat() * 0.8f + 0.1f;

		while(s.stackSize > 0)
		{
			int count = r.nextInt(21) + 10;
			if(s.stackSize > count) count = s.stackSize;
			s.stackSize -= count;
			EntityItem item = new EntityItem(w, (double)x + xoffs, (double)y + yoffs, (double)z + zoffs, new ItemStack(s.getItem(), count, s.getItemDamage()));

			if(s.hasTagCompound())
			{
				item.getEntityItem().setTagCompound((NBTTagCompound)s.getTagCompound().copy());
			}

			float motion_value = 0.05f;
			item.motionX = (double)r.nextGaussian() * motion_value;
			item.motionY = (double)r.nextGaussian() * motion_value + 0.2f;
			item.motionZ = (double)r.nextGaussian() * motion_value;
			w.spawnEntityInWorld(item);
		}
	}
}

