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
	
	private TileSteamMachine tent;

	public ContainerSteamMachineGui(EntityPlayer player, TileSteamMachine t)
	{
		super(new ContainerSteamMachine(player, t));

		this.tent = t;
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
		String title = StatCollector.translateToLocal("container.block_steamer:steamMachine");
		String inventory = StatCollector.translateToLocal("container.inventory");
		double titleWidth = this.fontRendererObj.getStringWidth(title);
		double progressHeight = this.tent.getProcessedPercentage() * 14.0;

		this.fontRendererObj.drawString(title, (int)((this.xSize - titleWidth) / 2), 6, 0x00404040);
		this.fontRendererObj.drawString(inventory, 9, 82, 0x00404040);

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(texture);
		this.drawTexturedModalRect(91, 40, 194, 0, 12, (int)progressHeight);
	}
}

