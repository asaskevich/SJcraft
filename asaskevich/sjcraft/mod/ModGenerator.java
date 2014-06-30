package asaskevich.sjcraft.mod;

import java.util.ArrayList;
import java.util.List;
import asaskevich.sjcraft.world.DestroyedPortalGenerator;
import asaskevich.sjcraft.world.ForestersHouseGenerator;
import asaskevich.sjcraft.world.HellSandGenerator;
import asaskevich.sjcraft.world.HellWellGenerator;
import asaskevich.sjcraft.world.IglooGenerator;
import asaskevich.sjcraft.world.ShaftGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModGenerator {
	private static List<IWorldGenerator> generatorList = new ArrayList<IWorldGenerator>();

	public static void init() {
		// Wow! Generators!		
		generatorList.add(new ShaftGenerator());
		generatorList.add(new ForestersHouseGenerator());
		generatorList.add(new IglooGenerator());
		generatorList.add(new HellSandGenerator());
		generatorList.add(new DestroyedPortalGenerator());
		generatorList.add(new HellWellGenerator());
	}

	public static void registerGenerators() {
		for (IWorldGenerator generator : generatorList) {
			GameRegistry.registerWorldGenerator(generator, 0);
		}
	}

}