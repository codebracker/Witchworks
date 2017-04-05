package com.wiccanarts.common.core;

import com.wiccanarts.api.item.crop.*;
import com.wiccanarts.common.block.*;
import com.wiccanarts.common.block.crop.*;
import com.wiccanarts.common.block.magic.*;
import com.wiccanarts.common.block.tile.*;
import com.wiccanarts.common.block.tools.*;
import com.wiccanarts.common.item.*;
import com.wiccanarts.common.item.food.*;
import com.wiccanarts.common.item.seed.*;
import com.wiccanarts.common.item.tool.*;
import com.wiccanarts.common.lib.*;
import com.wiccanarts.common.potions.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraftforge.event.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.eventhandler.*;

import static com.wiccanarts.api.WiccanArtsAPI.*;
import static com.wiccanarts.api.item.crop.Crop.*;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
@Mod.EventBusSubscriber
public class CommonRegistration {

	@SubscribeEvent
	public static void registerItems (RegistryEvent.Register<Item> event) {
		//Crops
		registerCrop (ACONITUM, ModBlocks.CROP_ACONITUM, new ItemAconitum (), LibItemName.SEED_ACONITUM);
		registerCrop (ASPHODEL, ModBlocks.CROP_ASPHODEL, new ItemAsphodel (), LibItemName.SEED_ASPHODEL);
		registerCrop (BELLADONNA, ModBlocks.CROP_BELLADONNA, new ItemBelladonna (), LibItemName.SEED_BELLADONNA);
		registerCrop (GINGER, ModBlocks.CROP_GINGER, new ItemGinger (), LibItemName.SEED_GINGER);
		registerCrop (KELP, ModBlocks.CROP_KELP, new ItemKelp (), new ItemKelpSeed ());
		registerCrop (MINT, ModBlocks.CROP_MINT, new ItemMint (), LibItemName.SEED_MINT);
		registerCrop (WHITE_SAGE, ModBlocks.CROP_WHITE_SAGE, new ItemWhiteSage (), LibItemName.SEED_WHITE_SAGE);
		registerCrop (DANDELION_ROOT, ModBlocks.CROP_DANDELION_ROOT, new ItemDandelionRoot (), LibItemName.SEED_DANDELION_ROOT);
		registerCrop (ALLIUM, ModBlocks.CROP_ALLIUM, new ItemAllium (), LibItemName.SEED_ALLIUM);
		registerCrop (MANDRAKE_ROOT, ModBlocks.CROP_MANDRAKE_ROOT, new ItemMandrakeRoot (), LibItemName.SEED_MANDRAKE_ROOT);
		registerCrop (LILAC, ModBlocks.CROP_LILAC, new ItemLilac (), LibItemName.SEED_LILAC);
		registerCrop (LAVENDER, ModBlocks.CROP_LAVENDER, new ItemLavender (), LibItemName.SEED_LAVENDER);
		registerCrop (THISTLE, ModBlocks.CROP_THISTLE, new ItemThistle (), LibItemName.SEED_THISTLE);

		CropRegistry.getFoods ().forEach ((crop, item) -> event.getRegistry ().register (item));
		CropRegistry.getSeeds ().forEach ((crop, item) -> event.getRegistry ().register (item));

		//Normal Items
		event.getRegistry ().registerAll (
				//Gems
				new ItemMod (LibItemName.GARNET),
				new ItemMod (LibItemName.MOLDAVITE),
				new ItemMod (LibItemName.NUUMMITE),
				new ItemMod (LibItemName.PETOSKEY_STONE),
				new ItemMod (LibItemName.SERPENTINE),
				new ItemMod (LibItemName.TIGERS_EYE),
				new ItemMod (LibItemName.TOURMALINE),
				new ItemMod (LibItemName.BLOODSTONE),
				new ItemMod (LibItemName.JASPER),
				new ItemMod (LibItemName.MALACHITE),
				new ItemMod (LibItemName.AMETHYST),
				new ItemMod (LibItemName.ALEXANDRITE),
				new ItemMod (LibItemName.QUARTZ),
				new ItemMod (LibItemName.SILVER_POWDER),
				new ItemMod (LibItemName.SILVER_INGOT),
				new ItemMod (LibItemName.SILVER_NUGGET),

				//Other
				new ItemMod (LibItemName.HONEY),
				new ItemSalt (),
				new ItemMod (LibItemName.WAX),
				new ItemMod (LibItemName.BEE),
				new ItemMod (LibItemName.HONEYCOMB),
				new ItemBrewPhial (),
				new ItemMod (LibItemName.GLASS_JAR),
				new ItemMod (LibItemName.LAVENDER_OIL),

				//Tools
				new ItemSilverPickaxe (ModMaterials.SILVER),
				new ItemSilverAxe (ModMaterials.SILVER),
				new ItemSilverSpade (ModMaterials.SILVER),
				new ItemSilverHoe (ModMaterials.SILVER),
				new ItemSilverSword (ModMaterials.SILVER)
		);

		//Item Blocks
		event.getRegistry ().registerAll (
				itemBlock (ModBlocks.CROP_ACONITUM),
				itemBlock (ModBlocks.CROP_ASPHODEL),
				itemBlock (ModBlocks.CROP_BELLADONNA),
				itemBlock (ModBlocks.CROP_GINGER),
				itemBlock (ModBlocks.CROP_KELP),
				itemBlock (ModBlocks.CROP_MINT),
				itemBlock (ModBlocks.CROP_WHITE_SAGE),
				itemBlock (ModBlocks.CROP_DANDELION_ROOT),
				itemBlock (ModBlocks.CROP_ALLIUM),
				itemBlock (ModBlocks.CROP_MANDRAKE_ROOT),
				itemBlock (ModBlocks.CROP_LILAC),
				itemBlock (ModBlocks.CROP_LAVENDER),
				itemBlock (ModBlocks.CROP_THISTLE),

				itemBlock (ModBlocks.SILVER_BLOCK),
				itemBlock (ModBlocks.MOLDAVITE_BLOCK),
				itemBlock (ModBlocks.COQUINA),
				itemBlock (ModBlocks.BLOODSTONE_BLOCK),
				itemBlock (ModBlocks.KETTLE),
				itemBlock (ModBlocks.SILVER_ORE),
				itemBlock (ModBlocks.MOLDAVITE_ORE),
				itemBlock (ModBlocks.MALACHITE_ORE),
				itemBlock (ModBlocks.BLOODSTONE_ORE),
				itemBlock (ModBlocks.TIGERS_EYE_ORE),
				itemBlock (ModBlocks.NUUMMITE_ORE),
				itemBlock (ModBlocks.GARNET_ORE),
				itemBlock (ModBlocks.PETOSKEY_ORE),
				itemBlock (ModBlocks.TOURMALINE_ORE),
				itemBlock (ModBlocks.SERPENTINE_ORE),
				itemBlock (ModBlocks.TOURMALINE_BLOCK),
				itemBlock (ModBlocks.NETHERSTEEL),
				new ItemBlockColor (ModBlocks.CANDLE_LARGE),
				new ItemBlockColor (ModBlocks.CANDLE_MEDIUM),
				new ItemBlockColor (ModBlocks.CANDLE_SMALL),
				itemBlock (ModBlocks.SALT_BARRIER)
		);
	}

	private static Item itemBlock (Block block) {
		return new ItemBlock (block).setRegistryName (block.getRegistryName ());
	}

	@SubscribeEvent
	public static void registerBlocks (RegistryEvent.Register<Block> event) {
		ModTiles.registerAll ();
		event.getRegistry ().registerAll (
				//Crops
				new BlockCrop (LibBlockName.CROP_ACONITUM),
				new BlockCrop (LibBlockName.CROP_ASPHODEL),
				new BlockCrop (LibBlockName.CROP_GINGER),
				new BlockCrop (LibBlockName.CROP_MINT),
				new BlockCrop (LibBlockName.CROP_WHITE_SAGE),
				new BlockCrop (LibBlockName.CROP_DANDELION_ROOT),
				new BlockCrop (LibBlockName.CROP_ALLIUM),
				new BlockCrop (LibBlockName.CROP_MANDRAKE_ROOT),
				new BlockCrop (LibBlockName.CROP_LILAC),
				new BlockCrop (LibBlockName.CROP_LAVENDER),
				new BlockCrop (LibBlockName.CROP_THISTLE),
				new BlockKelp (),
				new BlockBelladonna (),

				//Ore
				new BlockMod (LibBlockName.SILVER_BLOCK, Material.IRON).setSound (SoundType.METAL).setHardness (5.0F),
				new BlockMod (LibBlockName.SILVER_ORE, Material.ROCK).setSound (SoundType.STONE).setHardness (2.0F),
				new BlockMod (LibBlockName.MOLDAVITE_BLOCK, Material.ROCK).setSound (SoundType.GLASS).setHardness (5.0F),
				new BlockMod (LibBlockName.MOLDAVITE_ORE, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),
				new BlockMod (LibBlockName.TOURMALINE_ORE, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),
				new BlockMod (LibBlockName.MALACHITE_ORE, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),
				new BlockMod (LibBlockName.TOURMALINE_BLOCK, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),
				new BlockMod (LibBlockName.SERPENTINE_ORE, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),
				new BlockMod (LibBlockName.BLOODSTONE_ORE, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),
				new BlockMod (LibBlockName.TIGERS_EYE_ORE, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),
				new BlockMod (LibBlockName.NUUMMITE_ORE, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),
				new BlockMod (LibBlockName.GARNET_ORE, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),
				new BlockMod (LibBlockName.PETOSKEY_ORE, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),
				new BlockMod (LibBlockName.COQUINA, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),

				//Normal Blocks
				new BlockMod (LibBlockName.BLOODSTONE_BLOCK, Material.ROCK).setSound (SoundType.STONE).setHardness (5.0F),
				new BlockMod (LibBlockName.NETHERSTEEL, Material.IRON).setSound (SoundType.METAL).setHardness (5.0F),

				//Tool Blocks
				new BlockKettle (),
				new BlockCandleLarge (),
				new BlockCandleMedium (),
				new BlockCandleSmall (),
				new BlockSaltBarrier ()
		);
	}

	@SubscribeEvent
	public static void registerPotions (RegistryEvent.Register<Potion> event) {
		event.getRegistry ().registerAll (
				new BrewStoneform ()
		);
	}

	/**
	 * Register a Crop to the {@link CropRegistry}, this method creates a new {@link ItemSeed} for you.
	 *
	 * @param crop      The Crop enum
	 * @param blockCrop The block this Crop can have
	 * @param cropItem  The item this Crop will drop when harvested
	 * @param seedName  The name id the new ItemSeed
	 */
	private static void registerCrop (Crop crop, BlockCrop blockCrop, Item cropItem, String seedName) {
		registerCrop (crop, blockCrop, cropItem, new ItemSeed (seedName, blockCrop, crop.getSoil ()));
	}

	/**
	 * Register a Crop to the {@link CropRegistry}, this method accepts a custom {@link ItemSeed}.
	 * <p>
	 * The Item Seed needs to be different, for ex the Kelp seed,
	 * that needs to be placed on water so it uses a different placement logic.
	 * </p>
	 *
	 * @param crop      The Crop enum
	 * @param blockCrop The block this Crop can have
	 * @param cropItem  The item this Crop will drop when harvested
	 * @param seedItem  The seed that will place the Crop
	 */
	private static void registerCrop (Crop crop, BlockCrop blockCrop, Item cropItem, Item seedItem) {
		blockCrop.setCrop (cropItem);
		blockCrop.setSeed (seedItem);

		CropRegistry.getSeeds ().put (crop, seedItem);
		CropRegistry.getCrops ().put (crop, blockCrop);
		CropRegistry.getFoods ().put (crop, cropItem);
	}
}
