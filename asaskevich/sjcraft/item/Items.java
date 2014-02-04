package asaskevich.sjcraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import asaskevich.sjcraft.lib.ItemID;
import asaskevich.sjcraft.lib.ItemInfo;
import cpw.mods.fml.common.registry.GameRegistry;

public class Items {
	public static Item guitar;

	public static void init() {
		guitar = new Guitar(ItemID.guitarID);
		GameRegistry.registerItem(guitar, ItemInfo.guitar_name);
	}

	public static void addNames() {
		guitar.setUnlocalizedName(ItemInfo.guitar_name);
	}

	public static void addRecipes() {
		GameRegistry.addRecipe(new ItemStack(guitar, 1), new Object[] { "XJ*",
				"FI*", "**I", ('X'), Block.planks, ('I'), Item.stick, ('F'),
				Item.redstone, ('J'), Item.silk });
	}

}
