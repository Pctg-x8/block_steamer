package com.cterm2.block_steamer.client.gui;

// Gui Renderer for Steam Machine

import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;

import com.cterm2.block_steamer.tile.*;
import com.cterm2.block_steamer.inventory.*;

public class ContainerSteamMachineGui extends GuiContainer
{
	private static final ResourceLocation texture = new ResourceLocation("block_steamer:textures/gui/container/steamer.png");

	public ContainerSteamMachineGui(EntityPlayer player, TileSteamMachine t)
	{
		super(new ContainerSteamMachine(player, t));

		this.xSize = 194; this.ySize = 182;
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float a, int x, int z)
	{
		this.mc.renderEngine.bindTexture(texture);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	public void drawGuiContainerForegroundLayer(int x, int z)
	{

	}
}

