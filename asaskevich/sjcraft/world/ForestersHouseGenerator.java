package asaskevich.sjcraft.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemDoor;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import cpw.mods.fml.common.IWorldGenerator;

public class ForestersHouseGenerator implements IWorldGenerator {

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
			int posY = random.nextInt(100) + 1;
			while (world.getBlockId(posX, posY, posZ) != 0) {
				posY++;
			}
			if (world.getBlockId(posX, posY - 1, posZ) != Block.grass.blockID) {
				return;
			}
			String name = world.getBiomeGenForCoords(posX, posZ).biomeName;

			if (name != "Forest") {
				return;
			}

			for (int x = posX - 2; x <= posX + 2; x++) {
				for (int z = posZ - 2; z <= posZ + 2; z++) {
					for (int y = posY - 5; y <= posY; y++) {
						world.setBlock(x, y, z, Block.cobblestone.blockID);
					}
				}
			}
			for (int x = posX - 2; x <= posX + 2; x++) {
				for (int z = posZ - 2; z <= posZ + 2; z++) {
					for (int y = posY + 1; y <= posY + 4; y++) {
						world.setBlock(x, y, z, Block.planks.blockID);
					}
				}
			}
			for (int x = posX - 1; x <= posX + 1; x++) {
				for (int z = posZ - 1; z <= posZ + 1; z++) {
					for (int y = posY + 1; y <= posY + 3; y++) {
						world.setBlockToAir(x, y, z);
					}
				}
			}
			world.setBlock(posX - 2, posY + 2, posZ, Block.glass.blockID);
			world.setBlock(posX + 2, posY + 2, posZ, Block.glass.blockID);
			world.setBlock(posX, posY + 2, posZ + 2, Block.glass.blockID);
			world.setBlockToAir(posX, posY + 2, posZ - 2);
			world.setBlockToAir(posX, posY + 1, posZ - 2);
			world.setBlock(posX, posY + 3, posZ - 1, Block.torchWood.blockID);
			for (int y = posY + 1; y <= posY + 4; y++) {
				world.setBlock(posX - 2, y, posZ - 2, Block.wood.blockID);
				world.setBlock(posX + 2, y, posZ - 2, Block.wood.blockID);
				world.setBlock(posX + 2, y, posZ + 2, Block.wood.blockID);
				world.setBlock(posX - 2, y, posZ + 2, Block.wood.blockID);
			}
			world.setBlock(posX - 2, posY + 4, posZ - 1, Block.wood.blockID);
			world.setBlock(posX - 2, posY + 4, posZ, Block.wood.blockID);
			world.setBlock(posX - 2, posY + 4, posZ + 1, Block.wood.blockID);
			world.setBlock(posX + 2, posY + 4, posZ + 1, Block.wood.blockID);
			world.setBlock(posX + 2, posY + 4, posZ - 1, Block.wood.blockID);
			world.setBlock(posX + 2, posY + 4, posZ, Block.wood.blockID);
			world.setBlock(posX + 1, posY + 4, posZ + 2, Block.wood.blockID);
			world.setBlock(posX - 1, posY + 4, posZ + 2, Block.wood.blockID);
			world.setBlock(posX, posY + 4, posZ + 2, Block.wood.blockID);
			world.setBlock(posX + 1, posY + 4, posZ - 2, Block.wood.blockID);
			world.setBlock(posX, posY + 4, posZ - 2, Block.wood.blockID);
			world.setBlock(posX - 1, posY + 4, posZ - 2, Block.wood.blockID);
			// door
			ItemDoor.placeDoorBlock(world, posX, posY + 1, posZ - 2, 1,
					Block.doorWood);

			EntityVillager entityvillager = new EntityVillager(world, 0);
			entityvillager.setLocationAndAngles((double) posX + 0.5D,
					(double) posY + 1, (double) posZ + 0.5D, 0.0F, 0.0F);
			world.spawnEntityInWorld(entityvillager);
			// chest
			world.setBlock(posX - 1, posY + 1, posZ - 1, Block.chest.blockID,
					0, 2);
			TileEntityChest tileentitychest = (TileEntityChest) world
					.getBlockTileEntity(posX - 1, posY + 1, posZ - 1);

			if (tileentitychest != null) {
				ChestGenHooks info = ChestGenHooks
						.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH);
				WeightedRandomChestContent.generateChestContents(random,
						info.getItems(random), tileentitychest,
						info.getCount(random));
			}

		}

	}

	private String pickMobSpawner(Random par1Random) {
		return DungeonHooks.getRandomDungeonMob(par1Random);
	}

}