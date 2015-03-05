package com.cterm2.block_steamer.client.gui;

// Coal boiler gui

import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;

import com.cterm2.block_steamer.inventory.*;
import com.cterm2.block_steamer.tile.*;

public class ContainerCoalBoilerGui extends GuiContainer
{
	private static final ResourceLocation texture = 
		new ResourceLocation("block_steamer:textures/gui/container/coal_boiler.png");

	private TileCoalBoiler tent;
	
	public ContainerCoalBoilerGui(EntityPlayer player,
		TileCoalBoiler t)
	{
		super(new ContainerCoalBoiler(player, t));

		this.tent = t;
		this.xSize = 194;
		this.ySize = 162;
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float a, int x, int z)
	{
		this.mc.renderEngine.bindTexture(texture);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop,
			0, 0, this.xSize, this.ySize);
	}

	@Override
	public void drawGuiContainerForegroundLayer(int x, int z)
	{
		// foreground
		String title = StatCollector.translateToLocal("container.block_steamer:coalBoiler");
		String inventory = StatCollector.translateToLocal("container.inventory");
		String temperature = Integer.toString((int)this.tent.getCurrentTemperature()) + " C";
		int titleWidth = this.fontRendererObj.getStringWidth(title);
		double temperatureBaseHeight = 33.0 - 33.0 * ((this.tent.getCurrentTemperature() - 20) / 180.0);

		this.fontRendererObj.drawString(title, (this.xSize - titleWidth) / 2, 6, 0x00404040);
		this.fontRendererObj.drawString(temperature, 134, 44, 0x00404040);
		this.fontRendererObj.drawString(inventory, 9, 62, 0x00404040);

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(texture);
		this.drawTexturedModalRect(104, (int)(21 - 3 + temperatureBaseHeight), 194, 16, 20, 7);
	}
}

