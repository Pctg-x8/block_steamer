package com.cterm2.block_steamer.client.renderer.block;

// Custom block renderer for BlockTank

import cpw.mods.fml.client.registry.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;

import com.cterm2.block_steamer.*;
import com.cterm2.block_steamer.block.*;

public class BlockRendererTank implements ISimpleBlockRenderingHandler
{
	public void renderInventoryBlock(Block block,
		int meta, int modelIdentifier, RenderBlocks renderer)
	{
		Tessellator tess = Tessellator.instance;

		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5f, -0.5f, -0.5f);

		tess.startDrawingQuads();
		tess.setNormal(0.0f, -1.0f, 0.0f);
		renderer.renderFaceYNeg(block, 0.0, 0.0, 0.0,
			renderer.getBlockIconFromSideAndMetadata(block, 0, meta));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0f, 1.0f, 0.0f);
		renderer.renderFaceYPos(block, 0.0, 0.0, 0.0,
			renderer.getBlockIconFromSideAndMetadata(block, 1, meta));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0f, 0.0f, -1.0f);
		renderer.renderFaceZNeg(block, 0.0, 0.0, 0.0,
			renderer.getBlockIconFromSideAndMetadata(block, 2, meta));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0f, 0.0f, 1.0f);
		renderer.renderFaceZPos(block, 0.0, 0.0, 0.0,
			renderer.getBlockIconFromSideAndMetadata(block, 3, meta));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(-1.0f, 0.0f, 0.0f);
		renderer.renderFaceXNeg(block, 0.0, 0.0, 0.0,
			renderer.getBlockIconFromSideAndMetadata(block, 4, meta));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(1.0f, 0.0f, 0.0f);
		renderer.renderFaceXPos(block, 0.0, 0.0, 0.0,
			renderer.getBlockIconFromSideAndMetadata(block, 5, meta));
		tess.draw();

		GL11.glPopMatrix();
	}

	public boolean renderWorldBlock(IBlockAccess world,
		int x, int y, int z, Block block, int modelIdentifier,
		RenderBlocks renderer)
	{
		int meta = world.getBlockMetadata(x, y, z);

		renderer.renderStandardBlock(block, x, y, z);
		renderer.renderFromInside = true;
		renderer.renderStandardBlock(block, x, y, z);
		renderer.renderFromInside = false;
		return true;	
	}

	public boolean shouldRender3DInInventory(int modelID)
	{
		return true;
	}

	public int getRenderId()
	{
		return BlockSteamer.proxy.getRenderType(new BlockTank());
	}
}

