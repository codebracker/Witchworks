package com.witchworks.api.recipe;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

/**
 * This class was created by Arekkuusu on 21/03/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public interface IOreMatchRecipe {

	boolean matches(ItemStack[] usedItems);

	ImmutableList<Object> getNeededItems();

	ItemStack getResult();

	default List<ItemStack> getUsedItems(ItemStack[] stacks) {
		final List<ItemStack> used = new ArrayList<>();
		for (final ItemStack stack : stacks) {
			if (stack.isEmpty()) {
				break;
			}
			used.add(stack);
		}
		return used;
	}

	default boolean containsMatch(List<ItemStack> inputs, ItemStack target) {
		for (ItemStack input : inputs) {
			if (itemMatches(target, input)) {
				return true;
			}
		}
		return false;
	}

	default boolean itemMatches(ItemStack target, ItemStack input) {
		return input != null && target != null && target.getItem() == input.getItem() && (target.getItemDamage() == input.getItemDamage()
				|| input.getItemDamage() == WILDCARD_VALUE);
	}
}
