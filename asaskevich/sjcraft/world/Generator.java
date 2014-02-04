package asaskevich.sjcraft.world;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class Generator {
	private static List<IWorldGenerator> generatorList = new ArrayList();

	public static void init() {
		generatorList.add(new ShaftGenerator());
		generatorList.add(new ForestersHouseGenerator());
	}

	public static void registerGenerators() {
		for (IWorldGenerator generator : generatorList) {
			GameRegistry.registerWorldGenerator(generator);
		}
	}

}
