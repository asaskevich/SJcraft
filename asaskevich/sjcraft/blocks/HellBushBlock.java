package asaskevich.sjcraft.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import asaskevich.sjcraft.mod.ModBlocks;
import asaskevich.sjcraft.mod.ModInfo;
import asaskevich.sjcraft.mod.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HellBushBlock extends BlockBush implements IGrowable {
	@SideOnly(Side.CLIENT)
	private IIcon[] field_149867_a;

	public HellBushBlock() {
		this.setTickRandomly(true);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		this.setCreativeTab((CreativeTabs) null);
		this.setHardness(0.0F);
		this.setStepSound(soundTypeGrass);
		this.disableStats();
		this.setBlockTextureName(ModInfo.ID + ":hell_bush");
	}

	protected boolean canPlaceBlockOn(Block p_149854_1_) {
		return p_149854_1_ == ModBlocks.hellSandBlock;
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);

		if (world.getBlockLightValue(x, y + 1, z) >= 4) {
			int l = world.getBlockMetadata(x, y, z);

			if (l < 7) {
				float f = this.checker(world, x, y, z);

				if (rand.nextInt((int) (25.0F / f) + 1) == 0) {
					++l;
					world.setBlockMetadataWithNotify(x, y, z, l, 2);
				}
			}
		}
	}

	public void func_149863_m(World p_149863_1_, int p_149863_2_, int p_149863_3_, int p_149863_4_) {
		int l = p_149863_1_.getBlockMetadata(p_149863_2_, p_149863_3_, p_149863_4_)
				+ MathHelper.getRandomIntegerInRange(p_149863_1_.rand, 2, 5);

		if (l > 7) {
			l = 7;
		}

		p_149863_1_.setBlockMetadataWithNotify(p_149863_2_, p_149863_3_, p_149863_4_, l, 2);
	}

	private float checker(World world, int x, int y, int z) {
		float f = 1.0F;
		Block block = world.getBlock(x, y, z - 1);
		Block block1 = world.getBlock(x, y, z + 1);
		Block block2 = world.getBlock(x - 1, y, z);
		Block block3 = world.getBlock(x + 1, y, z);
		Block block4 = world.getBlock(x - 1, y, z - 1);
		Block block5 = world.getBlock(x + 1, y, z - 1);
		Block block6 = world.getBlock(x + 1, y, z + 1);
		Block block7 = world.getBlock(x - 1, y, z + 1);
		boolean flag = block2 == this || block3 == this;
		boolean flag1 = block == this || block1 == this;
		boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

		for (int l = x - 1; l <= x + 1; ++l) {
			for (int i1 = z - 1; i1 <= z + 1; ++i1) {
				float f1 = 3.0F;

				if (l != x || i1 != z) {
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		if (flag2 || flag && flag1) {
			f /= 2.0F;
		}

		return f;
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		if (p_149691_2_ < 0 || p_149691_2_ > 7) {
			p_149691_2_ = 7;
		}

		return this.field_149867_a[p_149691_2_];
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType() {
		return 6;
	}

	protected Item func_149866_i() {
		return ModItems.hellBushSeeds;
	}

	protected Item func_149865_P() {
		return ModItems.hellBud;
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int p_149690_5_,
			float p_149690_6_, int p_149690_7_) {
		super.dropBlockAsItemWithChance(world, x, y, z, p_149690_5_, p_149690_6_, 0);
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return p_149650_1_ == 7 ? this.func_149865_P() : this.func_149866_i();
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random p_149745_1_) {
		return 2;
	}

	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
		return p_149851_1_.getBlockMetadata(p_149851_2_, p_149851_3_, p_149851_4_) != 7;
	}

	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return true;
	}

	/**
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
		return this.func_149866_i();
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.field_149867_a = new IIcon[8];

		for (int i = 0; i < this.field_149867_a.length; ++i) {
			this.field_149867_a[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
		}
	}

	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
		this.func_149863_m(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

		if (metadata >= 7) {
			for (int i = 0; i < 3 + fortune; ++i) {
				if (world.rand.nextInt(15) <= metadata) {
					ret.add(new ItemStack(this.func_149866_i(), 1, 0));
				}
			}
		}

		return ret;
	}
}