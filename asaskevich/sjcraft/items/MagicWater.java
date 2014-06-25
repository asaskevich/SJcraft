package asaskevich.sjcraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import asaskevich.sjcraft.mod.ModInfo;

public class MagicWater extends Item {
	public MagicWater() {
		this.setTextureName(ModInfo.ID + ":magic_water");
		this.setFull3D();
		setCreativeTab(CreativeTabs.tabMaterials);
	}
}