package com.wiccanarts.common.potions;

import com.wiccanarts.client.*;
import com.wiccanarts.common.lib.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.potion.*;
import net.minecraftforge.fml.relauncher.*;

/**
 * This class was created by Arekkuusu on 27/03/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
public class BrewMod extends Potion {

	private final int iconIndex;

	BrewMod () {
		super (false, 0);
		iconIndex = 0;
	}

	public BrewMod (String name, boolean badEffect, int color, int iconIndex) {
		super (badEffect, color);
		setPotionName ("effect." + LibMod.MOD_ID + "." + name);
		setRegistryName (name);
		this.iconIndex = iconIndex;
	}

	@SideOnly (Side.CLIENT)
	@Override
	public void renderInventoryEffect (int x, int y, PotionEffect effect, Minecraft mc) {
		render (x + 6, y + 7, 1);
	}

	@SideOnly (Side.CLIENT)
	@Override
	public void renderHUDEffect (int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
		render (x + 3, y + 3, alpha);
	}

	@SideOnly (Side.CLIENT)
	private void render (int x, int y, float alpha) {
		Minecraft.getMinecraft ().renderEngine.bindTexture (ResourceLocations.POTION_TEXTURES);
		Tessellator tessellator = Tessellator.getInstance ();
		VertexBuffer buf = tessellator.getBuffer ();
		buf.begin (7, DefaultVertexFormats.POSITION_TEX);
		GlStateManager.color (1, 1, 1, alpha);

		int textureX = iconIndex % 8 * 18;
		int textureY = 198 + iconIndex / 8 * 18;
		float f = 0.00390625F;

		buf.pos (x, y + 18, 0).tex (textureX * f, (textureY + 18) * f).endVertex ();
		buf.pos (x + 18, y + 18, 0).tex ((textureX + 18) * f, (textureY + 18) * f).endVertex ();
		buf.pos (x + 18, y, 0).tex ((textureX + 18) * f, textureY * f).endVertex ();
		buf.pos (x, y, 0).tex (textureX * f, textureY * f).endVertex ();

		tessellator.draw ();
	}
}
