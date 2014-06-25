package asaskevich.sjcraft.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import cpw.mods.fml.common.IWorldGenerator;

public class ShaftGenerator implements IWorldGenerator {
	// Block ID's
	final Block GRASS = Blocks.grass;
	final Block DIRT = Blocks.dirt;
	final Block STONE = Blocks.stone;
	final Block COBBLESTONE = Blocks.cobblestone;
	final Block COBBLESTONE_MOSSY = Blocks.mossy_cobblestone;
	final Block FENCE = Blocks.fence;
	final Block WEB = Blocks.web;
	final Block LADDER = Blocks.ladder;
	final Block TORCH = Blocks.torch;
	final Block CHEST = Blocks.chest;
	final Block SPAWNER = Blocks.mob_spawner;

	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// Chance to generate mineshaft is 1 : 20 per chunk
		// Generating structure only in overworld
		int dimension = world.provider.dimensionId;
		if (rand.nextInt(20) == 0 && dimension == 0) generateStructure(world, rand, chunkX * 16, chunkZ * 16);
	}

	// TODO edit generator
	public void generateStructure(World world, Random rand, int chunkX, int chunkZ) {
		// Random position for generating structure
		int posX = chunkX + rand.nextInt(16);
		int posZ = chunkZ + rand.nextInt(16);
		int depth = rand.nextInt(10);
		String biomeName = world.getBiomeGenForCoords(posX, posZ).biomeName;
		int posY = rand.nextInt(100) + 1;

		while (!world.isAirBlock(posX, posY, posZ))
			posY++;

		if (world.isAirBlock(posX, posY - 1, posZ)) {
			return;
		} else if (posY < 35 + depth) {
			return;
		} else if (!biomeName.equals("Plains")) {
			return;
		}

		for (int x = posX - 2; x <= posX + 2; x++) {
			for (int y = posY - 10; y <= posY; y++) {
				for (int z = posZ - 2; z <= posZ + 2; z++) {
					if (y == posY) {
						world.setBlock(x, y, z, GRASS);
					} else if (y > posY - 5) {
						world.setBlock(x, y, z, DIRT);
					} else {
						world.setBlock(x, y, z, STONE);
					}
				}
			}
		}
		for (int x = posX - 1; x <= posX + 1; x++)
			for (int y = posY + 1; y <= posY + 1; y++)
				for (int z = posZ - 1; z <= posZ + 1; z++)
					world.setBlock(x, y, z, rand.nextInt(3) == 0 ? COBBLESTONE_MOSSY : COBBLESTONE);

		for (int x = posX - 1; x <= posX + 1; x++)
			for (int y = posY + 4; y <= posY + 4; y++)
				for (int z = posZ - 1; z <= posZ + 1; z++)
					world.setBlock(x, y, z, rand.nextInt(3) == 0 ? COBBLESTONE_MOSSY : COBBLESTONE);

		for (int y = posY + 2; y < posY + 4; y++) {
			world.setBlock(posX - 1, y, posZ - 1, FENCE);
			world.setBlock(posX + 1, y, posZ - 1, FENCE);
			world.setBlock(posX - 1, y, posZ + 1, FENCE);
			world.setBlock(posX + 1, y, posZ + 1, FENCE);
		}
		for (int x = posX - 1; x <= posX + 1; x++) {
			for (int y = posY + 1; y >= posY - 27 - depth; y--) {
				for (int z = posZ - 1; z <= posZ + 1; z++) {
					if (rand.nextInt(3) == 0) {
						world.setBlock(x, y, z, COBBLESTONE_MOSSY);
					} else {
						world.setBlock(x, y, z, COBBLESTONE);
					}
					if (rand.nextInt(20) != 0) world.setBlock(posX, y, posZ, LADDER, 3, 1 & 2 & 4);
					if ((y % 5 == 0) && (y > posY - 20 - depth) && (y < posY)) {
						world.setBlock(posX - 1, y, posZ, TORCH);
						world.setBlock(posX - 2, y, posZ, rand.nextInt(3) == 0 ? COBBLESTONE_MOSSY : COBBLESTONE);
					}
				}
			}
		}
		for (int x = posX - 4; x <= posX + 4; x++) {
			for (int y = posY - 29 - depth; y <= posY - 24 - depth; y++) {
				for (int z = posZ - 4; z <= posZ + 4; z++) {
					if (rand.nextInt(3) == 0) {
						world.setBlock(x, y, z, COBBLESTONE_MOSSY);
					} else {
						world.setBlock(x, y, z, COBBLESTONE);
					}
				}
			}
		}
		world.setBlock(posX, posY - 24 - depth, posZ, LADDER, 3, 1 & 2 & 4);
		for (int x = posX - 3; x <= posX + 3; x++) {
			for (int y = posY - 28 - depth; y <= posY - 25 - depth; y++) {
				for (int z = posZ - 3; z <= posZ + 3; z++) {
					world.setBlockToAir(x, y, z);
				}
			}
		}
		// Generating chests with random contents
		// In shaft usually one or two chests
		for (int i = 0; i < rand.nextInt(2) + 1; i++) {
			int px = posX + 3 - rand.nextInt(7);
			int pz = posZ + 3 - rand.nextInt(7);
			world.setBlock(px, posY - 28 - depth, pz, CHEST, 0, 2);
			TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(px, posY - 28 - depth, pz);

			if (tileentitychest != null) {
				ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
				WeightedRandomChestContent.generateChestContents(rand, info.getItems(rand), tileentitychest, info.getCount(rand));
			}
		}
		world.setBlock(posX, posY - depth - 28, posZ, SPAWNER, 0, 2);
		// Generating monster spawner
		TileEntityMobSpawner tileEntity = (TileEntityMobSpawner) world.getTileEntity(posX, posY - 28 - depth, posZ);

		if (tileEntity != null) {
			tileEntity.func_145881_a().setEntityName(this.pickMobSpawner(rand));
		}

		for (int i = 0; i <= rand.nextInt(6); i++) {
			int px = posX + 3 - rand.nextInt(7);
			int pz = posZ + 3 - rand.nextInt(7);
			world.setBlock(px, posY - depth - 25, pz, WEB);
		}
		for (int y = posY - 28 - depth; y <= posY - depth - 25; y++) {
			world.setBlock(posX - 3, y, posZ - 3, FENCE);
			world.setBlock(posX + 3, y, posZ - 3, FENCE);
			world.setBlock(posX - 3, y, posZ + 3, FENCE);
			world.setBlock(posX + 3, y, posZ + 3, FENCE);
		}
	}

	private String pickMobSpawner(Random par1Random) {
		return DungeonHooks.getRandomDungeonMob(par1Random);
	}

}