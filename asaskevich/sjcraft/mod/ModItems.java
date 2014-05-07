package asaskevich.sjcraft.mod;

import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import asaskevich.sjcraft.items.Guitar;
import asaskevich.sjcraft.items.HellBud;
import asaskevich.sjcraft.items.HellBushSeeds;
import asaskevich.sjcraft.items.MysteriousLabel;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

	public static Guitar guitar;
	public static MysteriousLabel mysteriousLabel;
	public static HellBushSeeds hellBushSeeds;
	public static HellBud hellBud;

	public static void initItems() {
		guitar = new Guitar();
		mysteriousLabel = new MysteriousLabel();
		hellBud = new HellBud();
		hellBushSeeds = new HellBushSeeds(ModBlocks.hellBushBlock, ModBlocks.hellSandBlock);
	}

	public static void addNames() {
		guitar.setUnlocalizedName("guitar");
		mysteriousLabel.setUnlocalizedName("mysteriousLabel");
		hellBushSeeds.setUnlocalizedName("hellBushSeeds");
		hellBud.setUnlocalizedName("hellBud");
	}

	public static void addRecipes() {}

	public static void registerItems() {
		GameRegistry.registerItem(guitar, "guitar");
		GameRegistry.registerItem(mysteriousLabel, "mysteriousLabel");
		GameRegistry.registerItem(hellBushSeeds, "hellBushSeeds");
		GameRegistry.registerItem(hellBud, "hellBud");
	}

	public static void addDungeonItems() {
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(hellBushSeeds, 1, 1, 1, 4));
	}

}
