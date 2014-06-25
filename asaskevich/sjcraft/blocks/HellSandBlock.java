package asaskevich.sjcraft.blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import asaskevich.sjcraft.mod.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HellSandBlock extends BlockFalling {
	final float	EPS		= 0.005f;
	final float	DELTA	= 0.0125F;

	public HellSandBlock() {
		super();
		this.setResistance(2f);
		this.setHardness(4);
		this.setBlockTextureName(ModInfo.ID + ":hell_sand_block");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setLightLevel(0.1f);
	}

	@Override
	public String getLocalizedName() {
		return "Hell Sand";
	}

	@Override
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity entity) {
		entity.setFire(1);
		entity.attackEntityFrom(DamageSource.magic, 0.5f);
		super.onEntityCollidedWithBlock(w, x, y, z, entity);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
		return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x) + DELTA, (double) y + DELTA, (double) ((float) z) + DELTA,
				(double) ((float) (x + 1) - DELTA), (double) ((float) (y + 1) - DELTA), (double) ((float) (z + 1) - DELTA));
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World w, int x, int y, int z) {
		return AxisAlignedBB.getAABBPool().getAABB((double) ((float) x) + DELTA, (double) y + DELTA, (double) ((float) z) + DELTA,
				(double) ((float) (x + 1) - DELTA), (double) ((float) (y + 1) - DELTA), (double) ((float) (z + 1) - DELTA));
	}

	@Override
	public void randomDisplayTick(World w, int x, int y, int z, Random r) {
		if (isCollideWithWater(w, x, y, z)) w.newExplosion(null,  x,  y,  z,  1F, false, false);
		super.randomDisplayTick(w, x, y, z, r);
	}

	private boolean isCollideWithWater(World w, int x, int y, int z) {
		Block water = Blocks.water;
		Block temp = w.getBlock(x - 1, y, z);
		if (Block.isEqualTo(water, temp)) return true;
		temp = w.getBlock(x + 1, y, z);
		if (Block.isEqualTo(water, temp)) return true;
		temp = w.getBlock(x, y, z - 1);
		if (Block.isEqualTo(water, temp)) return true;
		temp = w.getBlock(x, y, z + 1);
		if (Block.isEqualTo(water, temp)) return true;
		temp = w.getBlock(x, y - 1, z);
		if (Block.isEqualTo(water, temp)) return true;
		temp = w.getBlock(x, y + 1, z);
		if (Block.isEqualTo(water, temp)) return true;
		return false;
	}
}
