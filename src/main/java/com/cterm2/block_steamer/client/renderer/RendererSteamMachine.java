package com.cterm2.block_steamer.client.renderer;

// Custom Renderer for TileSteamMachine

import com.cterm2.block_steamer.tile.*;
import com.cterm2.block_steamer.block.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.tileentity.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import cpw.mods.fml.client.registry.*;

public class RendererSteamMachine extends TileEntitySpecialRenderer
{
	@Override
	public void renderTileEntityAt(TileEntity ent,
		double x, double y, double z, float f)
	{
		if(ent == null) return;
		if(((TileSteamMachine)ent).getVisibleItem() == null) return;
		if(!(((TileSteamMachine)ent).getVisibleItem() instanceof ItemBlock)) return;
		Block visibleBlock = ((ItemBlock)((TileSteamMachine)ent).getVisibleItem()).field_150939_a;
		// System.out.println("RenderBlock: " + visibleBlock);

		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.0f, (float)y + 0.0f, (float)z + 0.0f);

		this.bindTexture(TextureMap.locationBlocksTexture);
		Tessellator tess = Tessellator.instance;
		RenderBlocks rb = new RenderBlocks(ent.getWorldObj());
		rb.setRenderBounds(0.0625, 0.0625, 0.0625, 0.9375, 0.9375, 0.9375);
		tess.startDrawingQuads();
		tess.setNormal(0.0f, 0.0f, -1.0f);
		rb.renderFaceZNeg(visibleBlock, 0.0, 0.0, 0.0, rb.getBlockIconFromSideAndMetadata(visibleBlock, 2, 0));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0f, 0.0f, 1.0f);
		rb.renderFaceZPos(visibleBlock, 0.0, 0.0, 0.0, rb.getBlockIconFromSideAndMetadata(visibleBlock, 3, 0));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(-1.0f, 0.0f, 0.0f);
		rb.renderFaceXNeg(visibleBlock, 0.0, 0.0, 0.0, rb.getBlockIconFromSideAndMetadata(visibleBlock, 4, 0));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(1.0f, 0.0f, 0.0f);
		rb.renderFaceXPos(visibleBlock, 0.0, 0.0, 0.0, rb.getBlockIconFromSideAndMetadata(visibleBlock, 5, 0));
		tess.draw();
		
		GL11.glPopMatrix();
	}
}

