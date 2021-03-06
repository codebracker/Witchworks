package com.witchworks.common.block.natural;

import com.witchworks.api.helper.IModelRegister;
import com.witchworks.api.sound.WitchSoundEvents;
import com.witchworks.client.fx.ParticleF;
import com.witchworks.client.handler.ModelHandler;
import com.witchworks.common.WitchWorks;
import com.witchworks.common.block.BlockMod;
import com.witchworks.common.item.ModItems;
import com.witchworks.common.lib.LibBlockName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

import static net.minecraft.block.BlockHorizontal.FACING;

/**
 * This class was created by Joseph on 3/4/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class BlockBeehive extends BlockMod implements IModelRegister {

	private static final AxisAlignedBB BOX = new AxisAlignedBB(0.18F, 0, 0.18F, 0.82F, 1, 0.82F);

	public BlockBeehive() {
		super(LibBlockName.BEEHIVE, Material.GRASS);
		setSound(SoundType.PLANT);
		setResistance(1F);
		setHardness(1F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (rand.nextInt(10) == 0) {
			WitchWorks.proxy.spawnParticle(ParticleF.BEE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0, 0);
		}
		if (rand.nextInt(25) == 0) {
			worldIn.playSound(null, pos, WitchSoundEvents.BUZZ, SoundCategory.BLOCKS, 0.2F, 1F);
		}
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.BEE;
	}

	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOX;
	}

	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta) {
		final EnumFacing facing = EnumFacing.getHorizontal(meta);
		return getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		final EnumFacing facing = state.getValue(FACING);
		return facing.getHorizontalIndex();
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		final EnumFacing enumfacing = EnumFacing.fromAngle(placer.rotationYaw).getOpposite();
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels() {
		ModelHandler.registerBlock(this);
	}
}
