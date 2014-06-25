package asaskevich.sjcraft.blocks;

import java.util.Random;

import net.minecraft.block.BlockFalling;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import asaskevich.sjcraft.mod.ModInfo;

public class SugarBlock extends BlockFalling {

	public SugarBlock() {
		super();
		this.setHardness(4);
		this.setBlockTextureName(ModInfo.ID + ":sugar_block");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public Item getItemDropped(int i, Random rand, int j) {
		return Items.sugar;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 4;
	}
	
	@Override
	public String getLocalizedName() {
		return "Sugar Block";
	}
}
