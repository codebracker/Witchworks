package com.wiccanarts.common.block.tile;

import com.wiccanarts.api.*;
import com.wiccanarts.api.recipe.*;
import com.wiccanarts.api.sound.*;
import com.wiccanarts.client.fx.*;
import com.wiccanarts.common.*;
import com.wiccanarts.common.net.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.*;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.*;
import java.util.*;

/**
 * This class was created by Arekkuusu on 08/03/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
@SuppressWarnings ("WeakerAccess")
public class TileKettle extends TileItemInventory implements ITickable {

	private static final String TAG_WATER = "waterLevel";
	private static final String TAG_HEAT = "heat";
	private float[] colors = new float[] {0.0f, 0.39215687f, 0.0f};
	private int waterLevel;
	private int heat;
	private int tickCount;
	private IKettleRecipe result;

	@SuppressWarnings ("ConstantConditions")
	public void collideItem (EntityItem entityItem) {
		if (! hasWater ()) return;

		ItemStack stack = entityItem.getEntityItem ();
		if (stack == null || entityItem.isDead)
			return;

		if (! world.isRemote) {
			PacketHandler.sendTileUpdateNearbyPlayers (this);
			fancySplash ();

			stack.stackSize--;
			if (stack.stackSize == 0)
				entityItem.setDead ();


			for (int i = 0; i < getSizeInventory (); i++) {
				if (itemHandler.getItemSimulate (i) == null) {
					ItemStack stackToAdd = stack.copy ();
					stackToAdd.stackSize = 1;
					itemHandler.insertItem (i, stackToAdd, false);
					break;
				}
			}
		}

		world.playSound (null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 0.1F, 8F);
		float r = world.rand.nextFloat ();
		float g = world.rand.nextFloat ();
		float b = world.rand.nextFloat ();
		colors[0] = r;
		colors[1] = g;
		colors[2] = b;
	}

	private void fancySplash () {
		if (world instanceof WorldServer) {
			for (int i = 0; i < 10; i++) {
				BlockPos pos = getPos ();
				Random rand = new Random ();
				float d3 = (pos.getX () + rand.nextFloat ());
				float d4 = (float) (pos.getY () + 1);
				float d5 = (pos.getZ () + rand.nextFloat ());
				((WorldServer) world).spawnParticle (EnumParticleTypes.CRIT_MAGIC, d3, d4, d5, 1, 0, 0, 0, 0D);
			}
		}
	}

	private void updateRecipe () {
		if (isHot () && hasWater ()) {
			result = WiccanArtsAPI.getKettleRecipes ().stream ().filter (kettleRecipe ->
					kettleRecipe.checkRecipe (itemHandler, world)).findFirst ().orElse (null);
		} else if (result != null) {
			result = null;
		}
	}

	public boolean handleRightClick (@Nullable EntityPlayer player, EnumHand hand, ItemStack stack) {
		if (! world.isRemote) {
			if (stack.hasCapability (CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				IFluidHandler fluidHandler = stack.getCapability (CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
				if (waterLevel < 6) {
					FluidStack drainWater = fluidHandler.drain (new FluidStack (FluidRegistry.WATER, Fluid.BUCKET_VOLUME), false);

					if (drainWater != null && drainWater.getFluid () == FluidRegistry.WATER
							&& drainWater.amount == Fluid.BUCKET_VOLUME) {

						world.playSound (null, getPos (), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 1.0F, 5F);

						fluidHandler.drain (new FluidStack (FluidRegistry.WATER, Fluid.BUCKET_VOLUME), true);
						setWaterLevel (6);
						world.updateComparatorOutputLevel (pos, world.getBlockState (pos).getBlock ());

						colors = new float[] {0.0f, 0.39215687f, 0.0f};

						removeItems ();
					}
				} else if (waterLevel == 6) {
					int fill = fluidHandler.fill (new FluidStack (FluidRegistry.WATER, Fluid.BUCKET_VOLUME), false);

					if (fill == Fluid.BUCKET_VOLUME) {

						world.playSound (null, getPos (), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1.0F, 5F);

						fluidHandler.fill (new FluidStack (FluidRegistry.WATER, Fluid.BUCKET_VOLUME), true);
						setWaterLevel (0);
						world.updateComparatorOutputLevel (pos, world.getBlockState (pos).getBlock ());

						removeItems ();
					}
				}
			} else if (waterLevel > 0) {
				if (result != null && result.canTake (world, player, stack)) {
					if (player != null && ! player.capabilities.isCreativeMode) {
						ItemStack fromRecipe = result.getResult ();

						if (-- stack.stackSize == 0) {
							player.setHeldItem (hand, fromRecipe);
						} else if (! player.inventory.addItemStackToInventory (fromRecipe)) {
							player.dropItem (fromRecipe, false);
						} else if (player instanceof EntityPlayerMP) {
							((EntityPlayerMP) player).sendContainerToPlayer (player.inventoryContainer);
						}
					}

					world.playSound (null, getPos (), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1F);

					setWaterLevel (waterLevel - 1);
				} else if (stack.getItem () == Items.GLASS_BOTTLE) {
					if (player != null && ! player.capabilities.isCreativeMode) {
						ItemStack fromRecipe = PotionUtils.addPotionToItemStack (new ItemStack (Items.POTIONITEM), PotionTypes.WATER);

						if (-- stack.stackSize == 0) {
							player.setHeldItem (hand, fromRecipe);
						} else if (! player.inventory.addItemStackToInventory (fromRecipe)) {
							player.dropItem (fromRecipe, false);
						} else if (player instanceof EntityPlayerMP) {
							((EntityPlayerMP) player).sendContainerToPlayer (player.inventoryContainer);
						}
					}

					world.playSound (null, getPos (), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1F);

					setWaterLevel (waterLevel - 1);
				}
			}
		}
		return true;
	}

	private void removeItems () {
		result = null;
		for (int i = 0; i < itemHandler.getSlots (); i++) {
			itemHandler.extractItem (i, 1, false);
		}
	}

	@Override
	public void writeDataNBT (NBTTagCompound cmp) {
		super.writeDataNBT (cmp);
		cmp.setInteger (TAG_WATER, waterLevel);
		cmp.setInteger (TAG_HEAT, heat);
	}

	@Override
	public void readDataNBT (NBTTagCompound cmp) {
		super.readDataNBT (cmp);
		waterLevel = cmp.getInteger (TAG_WATER);
		heat = cmp.getInteger (TAG_HEAT);
	}

	@Override
	public void update () {
		List<EntityItem> entityItemList = world.getEntitiesWithinAABB (EntityItem.class, new AxisAlignedBB (getPos ()));
		entityItemList.forEach (this :: collideItem);

		if (isHot () && hasWater ()) {
			if (world.rand.nextInt (10) == 0) {
				BlockPos pos = getPos ();
				float d3 = ((float) pos.getX () + world.rand.nextFloat ());
				float d4 = ((float) pos.getY () + 0.65F);
				float d5 = ((float) pos.getZ () + world.rand.nextFloat ());
				WiccanArts.proxy.spawnParticle (ParticleF.CAULDRON_BUBBLE, d3, d4, d5, 0.0D, 0.1D, 0.0D, colors);
			}
			if (tickCount % 60 == 0) {
				world.playSound (null, getPos (), WiccaSoundEvents.BOIL, SoundCategory.BLOCKS, 0.2F, 1F);
			}
		}

		if (! world.isRemote && tickCount % 5 == 0) {
			if (result != null) {
				double d3 = pos.getX () + 0.5;
				double d4 = pos.getY () + 0.65;
				double d5 = pos.getZ () + 0.5;
				PacketHandler.spawnParticle (ParticleF.STEAM, world, d3, d4, d5, 3, 0D, 0.1D, 0D);

				if (! hasWater ()) {
					removeItems ();
				}
			}
			updateRecipe ();
		}

		if (tickCount % 10 == 0) {
			handleRain ();
		}

		if (tickCount % 20 == 0) {
			handleHeat ();
		}
		++ tickCount;
	}

	private void handleHeat () {
		if (isAboveFire () && hasWater () && heat < 5) {
			++ heat;
		} else if ((! isAboveFire () || ! hasWater ()) && heat > 0) {
			-- heat;
		}
	}

	private boolean isAboveFire () {
		IBlockState state = world.getBlockState (getPos ().down ());
		return state.getMaterial () == Material.FIRE;
	}

	private void handleRain () {
		if (waterLevel != 6 && world.isRainingAt (getPos ().up ())) {
			Random rand = new Random ();
			if (world.isRemote) {
				for (int i = 0; i < 4; i++) {
					double d3 = pos.getX () + rand.nextFloat ();
					double d4 = pos.getY () + 0.65F;
					double d5 = pos.getZ () + rand.nextFloat ();
					world.spawnParticle (EnumParticleTypes.WATER_BUBBLE, d3, d4, d5, 0.0D, 0.0D, 0.0D);
				}
			}
			world.playSound (null, getPos (), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 0.2F, 5F);
			setWaterLevel (waterLevel + 1);
			removeItems ();
		}
	}

	@Override
	public int getSizeInventory () {
		return 8;
	}

	public int getWaterLevel () {
		return waterLevel;
	}

	public void setWaterLevel (int water) {
		waterLevel = water;
		PacketHandler.updateToNearbyPlayers (world, pos);
	}

	public boolean hasWater () {
		return waterLevel > 0;
	}

	public boolean isHot () {
		return heat == 5;
	}

	public float[] getColor () {
		return colors;
	}
}
