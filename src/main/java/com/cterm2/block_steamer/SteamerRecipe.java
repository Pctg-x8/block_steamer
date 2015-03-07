package com.cterm2.block_steamer;

// Recipe stock for Steam Machine

import java.util.*;
import net.minecraft.item.*;

public class SteamerRecipe
{
	static class RecipePair
	{
		public ItemStack input, output;
		public double requiredSteam;

		public RecipePair(ItemStack i, double rs, ItemStack o)
		{
			this.input = i;
			this.output = o;
			this.requiredSteam = rs;
		}
	}

	static ArrayList<RecipePair> recipes = new ArrayList<RecipePair>();

	public static void registerRecipe(ItemStack input, ItemStack output)
	{
		SteamerRecipe.registerRecipe(input, 1000, output);
	}
	public static void registerRecipe(ItemStack input, double requiredSteam, ItemStack output)
	{
		if(input == null || output == null) return;
		recipes.add(new RecipePair(input, requiredSteam, output));
	}

	private static RecipePair findPair(ItemStack input)
	{
		if(input == null) return null;

		for(RecipePair p : recipes)
		{
			if(p.input.getItem() == input.getItem() && p.input.getItemDamage() == input.getItemDamage())
			{
				return p;
			}
		}
		return null;
	}
	public static ItemStack findOutput(ItemStack input)
	{
		RecipePair pair = findPair(input);

		if(pair == null) return null;
		return pair.output.copy();
	}
	public static double getRequiredSteam(ItemStack input)
	{
		RecipePair pair = findPair(input);

		if(pair == null) return -1;
		return pair.requiredSteam;
	}
	public static boolean isRegisteredInput(ItemStack input)
	{
		return findPair(input) != null;
	}
}

