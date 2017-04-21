package com.wiccanarts.common.item.baubles;

import baubles.api.BaubleType;
import net.minecraft.item.ItemStack;

/**
 * Created by BerciTheBeast on 21.4.2017.
 */
public class ItemBelt extends ItemBauble {
	public ItemBelt(String id) {
		super(id);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.BELT;
	}
}
