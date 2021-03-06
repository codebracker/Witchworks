package com.witchworks.common.core;

import com.witchworks.api.CropRegistry;
import com.witchworks.api.state.Crop;
import com.witchworks.common.block.BlockMod;
import com.witchworks.common.block.ModBlocks;
import com.witchworks.common.block.magic.BlockSaltBarrier;
import com.witchworks.common.block.natural.BlockBeehive;
import com.witchworks.common.block.natural.BlockSaltOre;
import com.witchworks.common.block.natural.crop.*;
import com.witchworks.common.block.natural.fluid.Fluids;
import com.witchworks.common.block.tools.*;
import com.witchworks.common.item.ItemMod;
import com.witchworks.common.item.ModMaterials;
import com.witchworks.common.item.block.ItemBlockColor;
import com.witchworks.common.item.block.ItemSalt;
import com.witchworks.common.item.equipment.ItemSilverArmor;
import com.witchworks.common.item.food.*;
import com.witchworks.common.item.food.seed.ItemKelpSeed;
import com.witchworks.common.item.food.seed.ItemSeed;
import com.witchworks.common.item.magic.ItemTaglock;
import com.witchworks.common.item.magic.books.ItemDustyGrimoire;
import com.witchworks.common.item.magic.books.ItemShadowBook;
import com.witchworks.common.item.magic.brew.ItemBrewDrink;
import com.witchworks.common.item.magic.brew.ItemBrewLinger;
import com.witchworks.common.item.magic.brew.ItemBrewSplash;
import com.witchworks.common.item.tool.*;
import com.witchworks.common.lib.LibBlockName;
import com.witchworks.common.lib.LibItemName;
import com.witchworks.common.tile.ModTiles;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import static com.witchworks.api.state.Crop.*;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
@Mod.EventBusSubscriber
public final class CommonRegistration {

	private CommonRegistration() {
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		//Crops
		registerCrop(ACONITUM, new ItemAconitum(), LibItemName.SEED_ACONITUM);
		registerCrop(ASPHODEL, new ItemAsphodel(), LibItemName.SEED_ASPHODEL);
		registerCrop(BELLADONNA, new ItemBelladonna(), LibItemName.SEED_BELLADONNA);
		registerCrop(GINGER, new ItemGinger(), LibItemName.SEED_GINGER);
		registerCrop(KELP, new ItemKelp(), new ItemKelpSeed());
		registerCrop(MINT, new ItemCrop(LibItemName.MINT, 1, 2F, false), LibItemName.SEED_MINT);
		registerCrop(WHITE_SAGE, new ItemCrop(LibItemName.WHITE_SAGE, 1, 0.4F, false), LibItemName.SEED_WHITE_SAGE);
		registerCrop(MANDRAKE, new ItemCrop(LibItemName.MANDRAKE, 4, 6F, false), LibItemName.SEED_MANDRAKE);
		registerCrop(LAVENDER, new ItemLavender(), LibItemName.SEED_LAVENDER);
		registerCrop(THISTLE, new ItemThistle(), LibItemName.SEED_THISTLE);
		registerCrop(TULSI, new ItemCrop(LibItemName.TULSI, 1, 0.4F, false), LibItemName.SEED_TULSI);
		registerCrop(KENAF, new ItemCrop(LibItemName.KENAF, 4, 6F, false), LibItemName.SEED_KENAF);
		registerCrop(SILPHIUM, new ItemCrop(LibItemName.SILPHIUM, 4, 6F, false), LibItemName.SEED_SILPHIUM);
		registerCrop(GARLIC, new ItemCrop(LibItemName.GARLIC, 4, 6F, false), LibItemName.SEED_GARLIC);
		registerCrop(WORMWOOD, new ItemCrop(LibItemName.WORMWOOD, 4, 0.8F, false), LibItemName.SEED_WORMWOOD);

		CropRegistry.getFoods().forEach((crop, item) -> registry.register(item));
		CropRegistry.getSeeds().forEach((crop, item) -> registry.register(item));

		//Normal Items
		registry.registerAll(
				//Gems
				new ItemMod(LibItemName.GARNET),
				new ItemMod(LibItemName.MOLDAVITE),
				new ItemMod(LibItemName.NUUMMITE),
				new ItemMod(LibItemName.TIGERS_EYE),
				new ItemMod(LibItemName.TOURMALINE),
				new ItemMod(LibItemName.BLOODSTONE),
				new ItemMod(LibItemName.JASPER),
				new ItemMod(LibItemName.GEMSTONE_AMALGAM),
				new ItemMod(LibItemName.MALACHITE),
				new ItemMod(LibItemName.AMETHYST),
				new ItemMod(LibItemName.ALEXANDRITE),
				new ItemMod(LibItemName.SILVER_POWDER),
				new ItemMod(LibItemName.SILVER_INGOT),
				new ItemMod(LibItemName.SILVER_NUGGET),

				//Other
				new ItemHoney(),
				new ItemSalt(),
				new ItemMod(LibItemName.WAX),
				new ItemMod(LibItemName.BEE).setMaxDamage(35),
				new ItemMod(LibItemName.HONEYCOMB),
				new ItemMod(LibItemName.EMPTY_HONEYCOMB),
				new ItemMod(LibItemName.MORTAR_AND_PESTLE),
				new ItemMod(LibItemName.UNREFINED_CHALK),
				new ItemBrewDrink(),
				new ItemBrewSplash(),
				new ItemBrewLinger(),
				new ItemMod(LibItemName.GLASS_JAR),

				//Tools
				new ItemSilverPickaxe(),
				new ItemSilverAxe(),
				new ItemSilverSpade(),
				new ItemSilverHoe(),
				new ItemSilverSword(),
				new ItemAthame(),
				new ItemBoline(),
				new ItemTaglock(),
				new ItemMod(LibItemName.CHALK_ITEM),
				new ItemMod(LibItemName.NEEDLE_BONE),

				//Books
				new ItemShadowBook(),
				new ItemDustyGrimoire(),

				//Equipment
				new ItemSilverArmor(LibItemName.SILVER_HELMET, ModMaterials.ARMOR_SILVER, 1, EntityEquipmentSlot.HEAD),
				new ItemSilverArmor(LibItemName.SILVER_CHESTPLATE, ModMaterials.ARMOR_SILVER, 1, EntityEquipmentSlot.CHEST),
				new ItemSilverArmor(LibItemName.SILVER_LEGGINGS, ModMaterials.ARMOR_SILVER, 2, EntityEquipmentSlot.LEGS),
				new ItemSilverArmor(LibItemName.SILVER_BOOTS, ModMaterials.ARMOR_SILVER, 1, EntityEquipmentSlot.FEET)
		);

		//Item Blocks
		registry.registerAll(
				itemBlock(ModBlocks.CROP_ACONITUM),
				itemBlock(ModBlocks.CROP_ASPHODEL),
				itemBlock(ModBlocks.CROP_BELLADONNA),
				itemBlock(ModBlocks.CROP_GINGER),
				itemBlock(ModBlocks.CROP_KELP),
				itemBlock(ModBlocks.CROP_MINT),
				itemBlock(ModBlocks.CROP_SILPHIUM),
				itemBlock(ModBlocks.CROP_WHITE_SAGE),
				itemBlock(ModBlocks.CROP_MANDRAKE),
				itemBlock(ModBlocks.CROP_LAVENDER),
				itemBlock(ModBlocks.CROP_THISTLE),
				itemBlock(ModBlocks.CROP_TULSI),
				itemBlock(ModBlocks.CROP_KENAF),
				itemBlock(ModBlocks.CROP_GARLIC),
				itemBlock(ModBlocks.CROP_WORMWOOD),

				itemBlock(ModBlocks.SILVER_BLOCK),
				itemBlock(ModBlocks.MOLDAVITE_BLOCK),
				itemBlock(ModBlocks.COQUINA),
				itemBlock(ModBlocks.BLOODSTONE_BLOCK),
				itemBlock(ModBlocks.CAULDRON),
				itemBlock(ModBlocks.ALTAR),
				itemBlock(ModBlocks.APIARY),
				itemBlock(ModBlocks.BEEHIVE),
				itemBlock(ModBlocks.SILVER_ORE),
				itemBlock(ModBlocks.MOLDAVITE_ORE),
				itemBlock(ModBlocks.MALACHITE_ORE),
				itemBlock(ModBlocks.BLOODSTONE_ORE),
				itemBlock(ModBlocks.TIGERS_EYE_ORE),
				itemBlock(ModBlocks.NUUMMITE_ORE),
				itemBlock(ModBlocks.JASPER_ORE),
				itemBlock(ModBlocks.GARNET_ORE),
				itemBlock(ModBlocks.TOURMALINE_ORE),
				itemBlock(ModBlocks.TOURMALINE_BLOCK),
				itemBlock(ModBlocks.SALT_ORE),
				itemBlock(ModBlocks.AMETHYST_ORE),
				itemBlock(ModBlocks.ALEXANDRITE_ORE),
				itemBlock(ModBlocks.NETHERSTEEL),
				new ItemBlockColor(ModBlocks.CANDLE_LARGE),
				new ItemBlockColor(ModBlocks.CANDLE_MEDIUM),
				new ItemBlockColor(ModBlocks.CANDLE_SMALL),
				itemBlock(ModBlocks.SALT_BARRIER),
				itemBlock(ModBlocks.CHALK)
		);
		for (final IFluidBlock fluidBlock : Fluids.MOD_FLUID_BLOCKS) {
			registry.register(itemBlock((Block) fluidBlock));
		}
	}

	private static Item itemBlock(Block block) {
		return new ItemBlock(block).setRegistryName(block.getRegistryName());
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();
		ModTiles.registerAll();
		registry.registerAll(
				//Crops
				new BlockCrop(LibBlockName.CROP_ACONITUM),
				new BlockCrop(LibBlockName.CROP_ASPHODEL),
				new BlockCrop(LibBlockName.CROP_GINGER),
				new CropMint(),
				new BlockCrop(LibBlockName.CROP_WHITE_SAGE),
				new BlockCrop(LibBlockName.CROP_MANDRAKE),
				new BlockCrop(LibBlockName.CROP_LAVENDER),
				new CropSilphium(),
				new CropThistle(),
				new BlockCrop(LibBlockName.CROP_TULSI),
				new CropKenaf(),
				new BlockCrop(LibBlockName.CROP_GARLIC),
				new CropWormwood(),
				new CropKelp(),
				new CropBelladonna(),
				new BlockBeehive(),

				//Ore
				new BlockMod(LibBlockName.SILVER_BLOCK, Material.IRON).setSound(SoundType.METAL).setHardness(5.0F),
				new BlockMod(LibBlockName.SILVER_ORE, Material.ROCK).setSound(SoundType.STONE).setHardness(2.0F),
				new BlockMod(LibBlockName.MOLDAVITE_BLOCK, Material.ROCK).setSound(SoundType.GLASS).setHardness(5.0F),
				new BlockMod(LibBlockName.MOLDAVITE_ORE, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.TOURMALINE_ORE, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.MALACHITE_ORE, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.TOURMALINE_BLOCK, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.JASPER_ORE, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.BLOODSTONE_ORE, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.TIGERS_EYE_ORE, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.NUUMMITE_ORE, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.GARNET_ORE, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockSaltOre(),
				new BlockMod(LibBlockName.ALEXANDRITE_ORE, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.COQUINA, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.CHALK, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.AMETHYST_ORE, Material.ROCK).setSound(SoundType.STONE).setHardness(7.0F),

				//Normal Blocks
				new BlockMod(LibBlockName.BLOODSTONE_BLOCK, Material.ROCK).setSound(SoundType.STONE).setHardness(5.0F),
				new BlockMod(LibBlockName.NETHERSTEEL, Material.IRON).setSound(SoundType.METAL).setHardness(5.0F),

				//Tool Blocks
				new BlockCauldron(),
				new BlockCandleLarge(),
				new BlockCandleMedium(),
				new BlockCandleSmall(),
				new BlockSaltBarrier(),
				new BlockApiary(),
				new BlockAltar()
		);
		for (final IFluidBlock fluidBlock : Fluids.MOD_FLUID_BLOCKS) {
			registry.register((Block) fluidBlock);
		}
	}

	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll(
		);
	}

	/**
	 * Register a Crop to the {@link CropRegistry}, this method creates a new {@link ItemSeed} for you.
	 *
	 * @param crop     The Crop enum
	 * @param cropItem The item this Crop will drop when harvested
	 * @param seedName The name id the new ItemSeed
	 */
	private static void registerCrop(Crop crop, Item cropItem, String seedName) {
		registerCrop(crop, cropItem, new ItemSeed(seedName, crop.getPlaced(), crop.getSoil()));
	}

	/**
	 * Register a Crop to the {@link CropRegistry}, this method accepts a custom {@link ItemSeed}.
	 * <p>
	 * The Item Seed needs to be different, for ex the Kelp seed,
	 * that needs to be placed on water so it uses a different placement recipeDropLogic.
	 * </p>
	 *
	 * @param crop     The Crop enum
	 * @param cropItem The item this Crop will drop when harvested
	 * @param seedItem The seed that will place the Crop
	 */
	private static void registerCrop(Crop crop, Item cropItem, Item seedItem) {
		BlockCrop placed = crop.getPlaced();
		placed.setCrop(cropItem);
		placed.setSeed(seedItem);

		CropRegistry.getSeeds().put(crop, seedItem);
		CropRegistry.getCrops().put(crop, placed);
		CropRegistry.getFoods().put(crop, cropItem);
	}
}
