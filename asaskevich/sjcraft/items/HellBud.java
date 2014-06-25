package asaskevich.sjcraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import asaskevich.sjcraft.mod.ModInfo;

public class HellBud extends Item {

	public HellBud() {
		this.setTextureName(ModInfo.ID + ":hell_bud");
		this.setFull3D();
		setCreativeTab(CreativeTabs.tabMaterials);
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int par7, float par8,
			float par9, float par10) {
		return true;
	}
}