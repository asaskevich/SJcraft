package asaskevich.sjcraft.mod;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import asaskevich.sjcraft.blocks.HellBushBlock;
import asaskevich.sjcraft.blocks.HellSandBlock;
import asaskevich.sjcraft.blocks.SugarBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static SugarBlock sugar_block;
	public static HellSandBlock hellSandBlock;
	public static HellBushBlock hellBushBlock;

	public static void initBlocks() {
		sugar_block = new SugarBlock();
		hellSandBlock = new HellSandBlock();
		hellBushBlock = new HellBushBlock();
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(sugar_block, "sugarBlock");
		GameRegistry.registerBlock(hellSandBlock, "hellSand");
		GameRegistry.registerBlock(hellBushBlock, "hellBush");
	}

	public static void addRecipes() {
		// 4 sugar <-> 1 sugar block
		GameRegistry.addShapelessRecipe(new ItemStack(sugar_block), new Object[] { new ItemStack(Items.sugar), new ItemStack(Items.sugar),
				new ItemStack(Items.sugar), new ItemStack(Items.sugar) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.sugar, 4), new Object[] { new ItemStack(sugar_block) });
		// 4 snow blocks <-> 1 ice block
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.ice), new Object[] { new ItemStack(Blocks.snow), new ItemStack(Blocks.snow),
				new ItemStack(Blocks.snow), new ItemStack(Blocks.snow) });
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.snow, 4), new Object[] { new ItemStack(Blocks.ice) });
	}

	public static void addNames() {
		sugar_block.setBlockName("sugarBlock");
		hellSandBlock.setBlockName("hellSand");
		hellBushBlock.setBlockName("hellBush");
	}

}
