package com.wiccanarts.common.item.baubles;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;

/**
 * This class was created by BerciTheBeast on 21.4.2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
public class ItemRing extends ItemBauble {

	public ItemRing(String id) {
		super(id);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.RING;
	}
}