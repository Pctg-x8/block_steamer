package com.cterm2.block_steamer.client.renderer;

// Custom Renderer for TileTank

import com.cterm2.block_steamer.tile.*;
import com.cterm2.block_steamer.block.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.tileentity.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import net.minecraft.init.*;

public class RendererTank extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity ent,
		double x, double y, double z, float f)
	{
		if(ent == null || !(ent instanceof TileTank)) return;

		TileTank tent = (TileTank)ent;
		double containPercent = tent.getAmountPercent();
		if(containPercent <= 0.0) return;
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5f, (float)y + 0.5f,
			(float)z + 0.5f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		float offset = 0.0078125f;
		float xzmin = -0.5f + BlockTank.ShrinkLevel + offset;
		float xzmax = 0.5f - BlockTank.ShrinkLevel - offset;
		float bottom_y = -0.5f + offset;
		float surface_y = (float)containPercent * (1.0f - offset * 2.0f)
			- 0.5f + offset;
		Tessellator tess = Tessellator.instance;

		IIcon renderIcon = tent.getFluidStaticIcon();
		if(renderIcon != null)
		{
			float umin = renderIcon.getMinU();
			float umax = renderIcon.getMaxU();
			float vmin = renderIcon.getMinV();
			float vmax = renderIcon.getMaxV();

			this.bindTexture(TextureMap.locationBlocksTexture);
			tess.startDrawingQuads();
			tess.setNormal(0.0f, 1.0f, 0.0f);
			tess.addVertexWithUV(xzmin, surface_y, xzmin, umin, vmin);
			tess.addVertexWithUV(xzmin, surface_y, xzmax, umin, vmax);
			tess.addVertexWithUV(xzmax, surface_y, xzmax, umax, vmax);
			tess.addVertexWithUV(xzmax, surface_y, xzmin, umax, vmin);
			tess.draw();
			tess.startDrawingQuads();
			tess.setNormal(0.0f, -1.0f, 0.0f);
			tess.addVertexWithUV(xzmin, bottom_y, xzmax, umin, vmax);
			tess.addVertexWithUV(xzmin, bottom_y, xzmin, umin, vmin);
			tess.addVertexWithUV(xzmax, bottom_y, xzmin, umax, vmin);
			tess.addVertexWithUV(xzmax, bottom_y, xzmax, umax, vmax);
			tess.draw();
		}
		renderIcon = tent.getFluidFlowingIcon();
		if(renderIcon != null)
		{
			float umin = renderIcon.getMinU();
			float umax = renderIcon.getMaxU();
			float vmin = renderIcon.getMinV();
			float vmax = renderIcon.getMaxV();

			this.bindTexture(TextureMap.locationBlocksTexture);
			tess.startDrawingQuads();
			tess.setNormal(0.0f, 0.0f, -1.0f);
			tess.addVertexWithUV(xzmin, surface_y, xzmin, umin, vmin);
			tess.addVertexWithUV(xzmax, surface_y, xzmin, umax, vmin);
			tess.addVertexWithUV(xzmax, bottom_y, xzmin, umax, vmax);
			tess.addVertexWithUV(xzmin, bottom_y, xzmin, umin, vmax);
			tess.draw();
			tess.startDrawingQuads();
			tess.setNormal(0.0f, 0.0f, 1.0f);
			tess.addVertexWithUV(xzmax, surface_y, xzmax, umax, vmin);
			tess.addVertexWithUV(xzmin, surface_y, xzmax, umin, vmin);
			tess.addVertexWithUV(xzmin, bottom_y, xzmax, umin, vmax);
			tess.addVertexWithUV(xzmax, bottom_y, xzmax, umax, vmax);
			tess.draw();
			tess.startDrawingQuads();
			tess.setNormal(-1.0f, 0.0f, 0.0f);
			tess.addVertexWithUV(xzmin, surface_y, xzmax, umax, vmin);
			tess.addVertexWithUV(xzmin, surface_y, xzmin, umin, vmin);
			tess.addVertexWithUV(xzmin, bottom_y, xzmin, umin, vmax);
			tess.addVertexWithUV(xzmin, bottom_y, xzmax, umax, vmax);
			tess.draw();
			tess.startDrawingQuads();
			tess.setNormal(1.0f, 0.0f, 0.0f);
			tess.addVertexWithUV(xzmax, surface_y, xzmin, umin, vmin);
			tess.addVertexWithUV(xzmax, surface_y, xzmax, umax, vmin);
			tess.addVertexWithUV(xzmax, bottom_y, xzmax, umax, vmax);
			tess.addVertexWithUV(xzmax, bottom_y, xzmin, umin, vmax);
			tess.draw();
		}

		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_BLEND);
	}
}

