package asaskevich.sjcraft.world;

import java.util.Random;
import asaskevich.sjcraft.lib.WorldEditor;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class DestroyedPortalGenerator implements IWorldGenerator {
	// Block ID's
	final Block			OBSIDIAN	= Blocks.obsidian;
	final Block			NETHERRACK	= Blocks.netherrack;
	// World Editor - I use it for fill custom area by some block
	private WorldEditor	edit;

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// Chance to generate structure is 1 : 20 per chunk
		// Generating structure only in overworld
		edit = new WorldEditor(world);
		int dimension = world.provider.dimensionId;
		if (rand.nextInt(20) == 0 && dimension == -1) generateStructure(world, rand, chunkX * 16, chunkZ * 16);
	}

	public void generateStructure(World world, Random rand, int chunkX, int chunkZ) {
		// Random position for generating structure
		int posX = chunkX + rand.nextInt(16);
		int posZ = chunkZ + rand.nextInt(16);
		int posY = rand.nextInt(100) + 1;
		while (!world.isAirBlock(posX, posY, posZ))
			posY++;
		// We can generate it only at blocks of netherrack
		if (!Block.isEqualTo(NETHERRACK, world.getBlock(posX, posY - 1, posZ))) return;
		edit.setToID(posX + 1, posY, posZ, 2, 1, 1, OBSIDIAN);
		edit.setToID(posX, posY + 1, posZ, 1, 3, 1, OBSIDIAN);
		edit.setToID(posX + 3, posY + 2, posZ, 1, 3, 1, OBSIDIAN);
		edit.setToID(posX + 1, posY + 4, posZ, 2, 1, 1, OBSIDIAN);
	}
}
