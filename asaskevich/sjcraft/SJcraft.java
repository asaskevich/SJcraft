package asaskevich.sjcraft;

import asaskevich.sjcraft.mod.ModBlocks;
import asaskevich.sjcraft.mod.ModGenerator;
import asaskevich.sjcraft.mod.ModInfo;
import asaskevich.sjcraft.mod.ModItems;
import asaskevich.sjcraft.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class SJcraft {
	@SidedProxy(clientSide = ModInfo.PROXY_LOCATION + ".ClientProxy", serverSide = ModInfo.PROXY_LOCATION + ".CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		ModGenerator.init();
		ModBlocks.initBlocks();
		ModBlocks.registerBlocks();
		ModItems.initItems();
		ModItems.registerItems();
		ModItems.addDungeonItems();
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		ModGenerator.registerGenerators();
		ModBlocks.addRecipes();
		ModBlocks.addNames();
		ModItems.addRecipes();
		ModItems.addNames();
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
	}
}
