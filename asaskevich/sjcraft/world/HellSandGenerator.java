package asaskevich.sjcraft.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import asaskevich.sjcraft.mod.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class HellSandGenerator implements IWorldGenerator {
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int dimension = world.provider.dimensionId;
		if (dimension == -1) generate(world, rand, chunkX * 16, chunkZ * 16);
	}

	// TODO edit generator
	public void generate(World world, Random rand, int chunkX, int chunkZ) {
		int par3 = chunkX + rand.nextInt(16);
		int par4 = rand.nextInt(100) + 10;
		int par5 = chunkZ + rand.nextInt(16);
		for (int l = 0; l < 64; ++l) {
			int i1 = par3 + rand.nextInt(8) - rand.nextInt(8);
			int j1 = par4 + rand.nextInt(4) - rand.nextInt(4);
			int k1 = par5 + rand.nextInt(8) - rand.nextInt(8);

			if (world.isAirBlock(i1, j1, k1) && world.getBlock(i1, j1 - 2, k1) == Blocks.netherrack) {
				world.setBlock(i1, j1 - 1, k1, ModBlocks.hellSandBlock);
				world.setBlock(i1, j1 - 1, k1 - 1, ModBlocks.hellSandBlock);
				world.setBlock(i1 - 1, j1 - 1, k1, ModBlocks.hellSandBlock);
				world.setBlock(i1 + 1, j1 - 1, k1, ModBlocks.hellSandBlock);
				world.setBlock(i1, j1 - 1, k1 + 1, ModBlocks.hellSandBlock);
				if (rand.nextInt(3) == 0) world.setBlock(i1, j1, k1, ModBlocks.hellBushBlock);
			}
		}
	}

}