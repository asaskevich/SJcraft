package asaskevich.sjcraft.items;

import asaskevich.sjcraft.mod.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class HellBushSeeds extends Item implements IPlantable {
	private Block field_150925_a;
	/**
	 * BlockID of the block the seeds can be planted on.
	 */
	private Block soilBlockID;
	public HellBushSeeds(Block p_i45352_1_, Block p_i45352_2_) {
		this.field_150925_a = p_i45352_1_;
		this.soilBlockID = p_i45352_2_;
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName("hellBushSeeds");
		this.setTextureName(ModInfo.ID + ":hell_bush_seeds");
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return True if something happen and false if it don't. This is for
	 * ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6,
			int par7, float par8, float par9, float par10) {
		if (par7 != 1) {
			return false;
		} else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)
				&& par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack)) {
			if (Block.isEqualTo(par3World.getBlock(par4, par5, par6), soilBlockID) && par3World.isAirBlock(par4, par5 + 1, par6)) {
				par3World.setBlock(par4, par5 + 1, par6, this.field_150925_a);
				--par1ItemStack.stackSize;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return field_150925_a == Blocks.nether_wart ? EnumPlantType.Nether : EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return field_150925_a;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}
}