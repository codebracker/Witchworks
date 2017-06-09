package com.witchworks.common.potions;

import com.witchworks.api.BrewRegistry;
import com.witchworks.api.brew.BrewEffect;
import com.witchworks.api.brew.IBrew;
import com.witchworks.common.lib.LibMod;

import static net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
@ObjectHolder(LibMod.MOD_ID)
public final class ModBrews {

	public static IBrew SHELL_ARMOR;
	public static IBrew INNER_FIRE;
	public static IBrew SPIDER_NIGHTMARE;
	public static IBrew EXTINGUISH;
	public static IBrew WARWATER;
	public static IBrew FROSTBITE;

	private ModBrews() {
	}

	public static void init() {
		SHELL_ARMOR = BrewRegistry.registerBrew(new ShellArmorBrew());
		BrewRegistry.addDefault(new BrewEffect(SHELL_ARMOR, 2500, 0));

		INNER_FIRE = BrewRegistry.registerBrew(new InnerFireBrew());
		BrewRegistry.addDefault(new BrewEffect(INNER_FIRE, 1500, 0));

		SPIDER_NIGHTMARE = BrewRegistry.registerBrew(new SpiderNightmareBrew());
		BrewRegistry.addDefault(new BrewEffect(SPIDER_NIGHTMARE, 500, 0));

		EXTINGUISH = BrewRegistry.registerBrew(new ExtinguishBrew());
		BrewRegistry.addDefault(new BrewEffect(EXTINGUISH, 500, 0));

		WARWATER = BrewRegistry.registerBrew(new WarWaterBrew());
		BrewRegistry.addDefault(new BrewEffect(WARWATER, 500, 0));

		FROSTBITE = BrewRegistry.registerBrew(new FrostbiteBrew());
		BrewRegistry.addDefault(new BrewEffect(FROSTBITE, 500, 0));
	}
}
