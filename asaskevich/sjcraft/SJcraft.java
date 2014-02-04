package asaskevich.sjcraft;

import asaskevich.sjcraft.item.Items;
import asaskevich.sjcraft.lib.ConfigHandler;
import asaskevich.sjcraft.lib.ModInfo;
import asaskevich.sjcraft.proxy.CommonProxy;
import asaskevich.sjcraft.world.Generator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
@NetworkMod(channels = { ModInfo.CHANNEL }, clientSideRequired = true, serverSideRequired = true)
public class SJcraft {
	@SidedProxy(clientSide = ModInfo.PROXY_LOCATION + ".ClientProxy", serverSide = ModInfo.PROXY_LOCATION
			+ ".CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.loadIDs(event.getSuggestedConfigurationFile());
		Generator.init();
		Items.init();

	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		Generator.registerGenerators();
		Items.addRecipes();
		Items.addNames();
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {

	}
}
