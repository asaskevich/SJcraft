package asaskevich.sjcraft.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import cpw.mods.fml.common.IWorldGenerator;

public class ShaftGenerator implements IWorldGenerator {

	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	public void generateSurface(World world, Random random, int chunkX,
			int chunkZ) {
		if (random.nextInt(20) != 0) {
			return;
		}
		for (int l = 0; l < 1; ++l) {
			int posX = chunkX + random.nextInt(16);
			int posZ = chunkZ + random.nextInt(16);
			int deep = random.nextInt(10);

			int posY = random.nextInt(100) + 1;
			while (world.getBlockId(posX, posY, posZ) != 0) {
				posY++;
			}
			if (world.getBlockId(posX, posY - 1, posZ) == 0) {
				return;
			}
			if (posY < 35 + deep) {
				return;
			}
			String name = world.getBiomeGenForCoords(posX, posZ).biomeName;

			if (name != "Plains") {
				return;
			}

			for (int x = posX - 2; x <= posX + 2; x++) {
				for (int y = posY - 10; y <= posY; y++) {
					for (int z = posZ - 2; z <= posZ + 2; z++) {
						if (y == posY) {
							world.setBlock(x, y, z, Block.grass.blockID);
						} else if (y > posY - 5) {
							world.setBlock(x, y, z, Block.dirt.blockID);
						} else {
							world.setBlock(x, y, z, Block.stone.blockID);
						}
					}
				}
			}
			for (int x = posX - 1; x <= posX + 1; x++) {
				for (int y = posY + 1; y <= posY + 1; y++) {
					for (int z = posZ - 1; z <= posZ + 1; z++) {
						if (random.nextInt(3) == 0) {
							world.setBlock(x, y, z,
									Block.cobblestoneMossy.blockID);
						} else {
							world.setBlock(x, y, z, Block.cobblestone.blockID);
						}
					}
				}
			}
			for (int x = posX - 1; x <= posX + 1; x++) {
				for (int y = posY + 4; y <= posY + 4; y++) {
					for (int z = posZ - 1; z <= posZ + 1; z++) {
						if (random.nextInt(3) == 0) {
							world.setBlock(x, y, z,
									Block.cobblestoneMossy.blockID);
						} else {
							world.setBlock(x, y, z, Block.cobblestone.blockID);
						}
					}
				}
			}
			for (int y = posY + 2; y < posY + 4; y++) {
				world.setBlock(posX - 1, y, posZ - 1, Block.fence.blockID);
				world.setBlock(posX + 1, y, posZ - 1, Block.fence.blockID);
				world.setBlock(posX - 1, y, posZ + 1, Block.fence.blockID);
				world.setBlock(posX + 1, y, posZ + 1, Block.fence.blockID);
			}
			for (int x = posX - 1; x <= posX + 1; x++) {
				for (int y = posY + 1; y >= posY - 27 - deep; y--) {
					for (int z = posZ - 1; z <= posZ + 1; z++) {
						if (random.nextInt(3) == 0) {
							world.setBlock(x, y, z,
									Block.cobblestoneMossy.blockID);
						} else {
							world.setBlock(x, y, z, Block.cobblestone.blockID);
						}
						world.setBlock(posX, y, posZ, Block.ladder.blockID, 3,
								1 & 2 & 4);
						if ((y % 5 == 0) && (y > posY - 20 - deep)
								&& (y < posY)) {
							world.setBlock(posX - 1, y, posZ,
									Block.torchWood.blockID);
							if (random.nextInt(3) == 0) {
								world.setBlock(posX - 2, y, posZ,
										Block.cobblestoneMossy.blockID);
							} else {
								world.setBlock(posX - 2, y, posZ,
										Block.cobblestone.blockID);
							}
						}
					}
				}
			}
			for (int x = posX - 4; x <= posX + 4; x++) {
				for (int y = posY - 29 - deep; y <= posY - 24 - deep; y++) {
					for (int z = posZ - 4; z <= posZ + 4; z++) {
						if (random.nextInt(3) == 0) {
							world.setBlock(x, y, z,
									Block.cobblestoneMossy.blockID);
						} else {
							world.setBlock(x, y, z, Block.cobblestone.blockID);
						}
					}
				}
			}
			world.setBlock(posX, posY - 24 - deep, posZ, Block.ladder.blockID,
					3, 1 & 2 & 4);
			for (int x = posX - 3; x <= posX + 3; x++) {
				for (int y = posY - 28 - deep; y <= posY - 25 - deep; y++) {
					for (int z = posZ - 3; z <= posZ + 3; z++) {
						world.setBlock(x, y, z, 0);
					}
				}
			}

			for (int i = 0; i < random.nextInt(2) + 1; i++) {
				int px = posX + 3 - random.nextInt(7);
				int pz = posZ + 3 - random.nextInt(7);
				world.setBlock(px, posY - 28 - deep, pz, Block.chest.blockID,
						0, 2);
				TileEntityChest tileentitychest = (TileEntityChest) world
						.getBlockTileEntity(px, posY - 28 - deep, pz);

				if (tileentitychest != null) {
					ChestGenHooks info = ChestGenHooks
							.getInfo(ChestGenHooks.DUNGEON_CHEST);
					WeightedRandomChestContent.generateChestContents(random,
							info.getItems(random), tileentitychest,
							info.getCount(random));
				}
			}
			world.setBlock(posX, posY - 28 - deep, posZ,
					Block.mobSpawner.blockID, 0, 2);
			TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner) world
					.getBlockTileEntity(posX, posY - 28 - deep, posZ);

			if (tileentitymobspawner != null) {
				tileentitymobspawner.getSpawnerLogic().setMobID(
						this.pickMobSpawner(random));
			}

			for (int i = 0; i <= random.nextInt(6); i++) {
				int px = posX + 3 - random.nextInt(7);
				int pz = posZ + 3 - random.nextInt(7);
				world.setBlock(px, posY - 25 - deep, pz, Block.web.blockID);
			}
			for (int y = posY - 28 - deep; y <= posY - 25 - deep; y++) {
				world.setBlock(posX - 3, y, posZ - 3, Block.fence.blockID);
				world.setBlock(posX + 3, y, posZ - 3, Block.fence.blockID);
				world.setBlock(posX - 3, y, posZ + 3, Block.fence.blockID);
				world.setBlock(posX + 3, y, posZ + 3, Block.fence.blockID);
			}

		}

	}

	private String pickMobSpawner(Random par1Random) {
		return DungeonHooks.getRandomDungeonMob(par1Random);
	}

}