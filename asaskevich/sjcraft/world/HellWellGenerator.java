package asaskevich.sjcraft.world;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class HellWellGenerator implements IWorldGenerator {
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// Chance to generate structure is 1 : 20 per chunk
		// Generating structure only in overworld
		int dimension = world.provider.dimensionId;
		if (rand.nextInt(20) == 0 && dimension == -1) generateStructure(world, rand, chunkX * 16, chunkZ * 16);
	}

	public boolean generateStructure(World world, Random rand, int chunkX, int chunkZ) {
		int x = chunkX + rand.nextInt(16);
		int z = chunkZ + rand.nextInt(16);
		int y = rand.nextInt(100) + 1;
		while (world.isAirBlock(x, y, z) && y > 2)
			--y;
		if (world.getBlock(x, y, z) != Blocks.netherrack) return false;
		else {
			int l;
			int i1;
			for (l = -2; l <= 2; ++l) {
				for (i1 = -2; i1 <= 2; ++i1) {
					if (world.isAirBlock(x + l, y - 1, z + i1) && world.isAirBlock(x + l, y - 2, z + i1)) { return false; }
				}
			}
			for (l = -1; l <= 0; ++l) {
				for (i1 = -2; i1 <= 2; ++i1) {
					for (int j1 = -2; j1 <= 2; ++j1) {
						world.setBlock(x + i1, y + l, z + j1, Blocks.nether_brick, 0, 2);
					}
				}
			}
			world.setBlock(x, y, z, Blocks.flowing_lava, 0, 2);
			world.setBlock(x - 1, y, z, Blocks.flowing_lava, 0, 2);
			world.setBlock(x + 1, y, z, Blocks.flowing_lava, 0, 2);
			world.setBlock(x, y, z - 1, Blocks.flowing_lava, 0, 2);
			world.setBlock(x, y, z + 1, Blocks.flowing_lava, 0, 2);
			for (l = -2; l <= 2; ++l) {
				for (i1 = -2; i1 <= 2; ++i1) {
					if (l == -2 || l == 2 || i1 == -2 || i1 == 2) {
						world.setBlock(x + l, y + 1, z + i1, Blocks.nether_brick, 0, 2);
					}
				}
			}
			world.setBlock(x + 2, y + 1, z, Blocks.nether_brick, 1, 2);
			world.setBlock(x - 2, y + 1, z, Blocks.nether_brick, 1, 2);
			world.setBlock(x, y + 1, z + 2, Blocks.nether_brick, 1, 2);
			world.setBlock(x, y + 1, z - 2, Blocks.nether_brick, 1, 2);
			for (l = -1; l <= 1; ++l) {
				for (i1 = -1; i1 <= 1; ++i1) {
					if (l == 0 && i1 == 0) {
						world.setBlock(x + l, y + 4, z + i1, Blocks.nether_brick, 0, 2);
					} else {
						world.setBlock(x + l, y + 4, z + i1, Blocks.nether_brick, 1, 2);
					}
				}
			}
			for (l = 1; l <= 3; ++l) {
				world.setBlock(x - 1, y + l, z - 1, Blocks.nether_brick, 0, 2);
				world.setBlock(x - 1, y + l, z + 1, Blocks.nether_brick, 0, 2);
				world.setBlock(x + 1, y + l, z - 1, Blocks.nether_brick, 0, 2);
				world.setBlock(x + 1, y + l, z + 1, Blocks.nether_brick, 0, 2);
			}
		}
		return true;
	}
}
