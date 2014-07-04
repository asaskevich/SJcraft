package asaskevich.sjcraft.mod;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import asaskevich.sjcraft.items.Guitar;
import asaskevich.sjcraft.items.HellBud;
import asaskevich.sjcraft.items.HellBushSeeds;
import asaskevich.sjcraft.items.MagicWater;
import asaskevich.sjcraft.items.MysteriousLabel;
import asaskevich.sjcraft.lib.CustomWeightedRandomChestContent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
	public static Guitar			guitar;
	public static MysteriousLabel	mysteriousLabel;
	public static HellBushSeeds		hellBushSeeds;
	public static HellBud			hellBud;
	public static MagicWater		magicWater;

	public static void initItems() {
		guitar = new Guitar();
		mysteriousLabel = new MysteriousLabel();
		hellBud = new HellBud();
		hellBushSeeds = new HellBushSeeds(ModBlocks.hellBushBlock, ModBlocks.hellSandBlock);
		magicWater = new MagicWater();
	}

	public static void addNames() {
		guitar.setUnlocalizedName("guitar");
		mysteriousLabel.setUnlocalizedName("mysteriousLabel");
		hellBushSeeds.setUnlocalizedName("hellBushSeeds");
		hellBud.setUnlocalizedName("hellBud");
		magicWater.setUnlocalizedName("magicWater");
	}

	public static void addRecipes() {
		// Fermented Spider Eye + Any Potion + Water -> Magic Water
		GameRegistry.addShapelessRecipe(new ItemStack(magicWater), new Object[] { Items.fermented_spider_eye, Items.potionitem, Items.water_bucket });
	}

	public static void registerItems() {
		GameRegistry.registerItem(guitar, "guitar");
		GameRegistry.registerItem(mysteriousLabel, "mysteriousLabel");
		GameRegistry.registerItem(hellBushSeeds, "hellBushSeeds");
		GameRegistry.registerItem(hellBud, "hellBud");
		GameRegistry.registerItem(magicWater, "magicWater");
	}

	public static void addDungeonItems() {
		// Generate Hell Bush Seeds in dungeon chests
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(hellBushSeeds, 1, 1, 1, 4));
		// Generate magic labels in mineshaft corridors
		ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new CustomWeightedRandomChestContent(mysteriousLabel, 1, 1, 1, 4, 0)); // Explosion
		ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new CustomWeightedRandomChestContent(mysteriousLabel, 1, 1, 1, 4, 1)); // Lightning
																																		// bolt
		ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new CustomWeightedRandomChestContent(mysteriousLabel, 1, 1, 1, 4, 2)); // Setting
																																		// fire
		// Generate magic labels in dungeons
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new CustomWeightedRandomChestContent(mysteriousLabel, 1, 1, 1, 4, 0)); // Explosion
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new CustomWeightedRandomChestContent(mysteriousLabel, 1, 1, 1, 4, 1)); // Lightning
																																		// bolt
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new CustomWeightedRandomChestContent(mysteriousLabel, 1, 1, 1, 4, 2)); // Setting
																																		// fire
	}
}
