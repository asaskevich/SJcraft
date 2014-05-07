package asaskevich.sjcraft.world;

import java.util.Random;

import asaskevich.sjcraft.lib.WorldEditor;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;
import cpw.mods.fml.common.IWorldGenerator;

public class IglooGenerator implements IWorldGenerator {
	// Block ID's
	final Block SNOW = Blocks.snow;
	final Block GLASS = Blocks.glass;
	final Block GRASS = Blocks.grass;
	final Block CHEST = Blocks.chest;
	final Block TORCH = Blocks.torch;
	// World Editor - I use it for fill custom area by some block
	private WorldEditor edit;

	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// Chance to generate structure is 1 : 30 per chunk
		// Generating structure only in overworld
		edit = new WorldEditor(world);
		int dimension = world.provider.dimensionId;
		if (rand.nextInt(30) == 0 && dimension == 0) generateStructure(world, rand, chunkX * 16, chunkZ * 16);
	}

	// TODO edit generator
	public void generateStructure(World world, Random rand, int chunkX, int chunkZ) {
		// Random position for generating structure
		int posX = chunkX + rand.nextInt(16);
		int posZ = chunkZ + rand.nextInt(16);
		int posY = rand.nextInt(100) + 1;
		String biomeName = world.getBiomeGenForCoords(posX, posZ).biomeName;
		while (!world.isAirBlock(posX, posY, posZ))
			posY++;
		world.getBlock(posX, posY - 1, posZ);
		// We can generate it only at blocks of grass and only at forests
		if (Block.isEqualTo(Blocks.air, world.getBlock(posX, posY - 1, posZ))) {
			return;
		} else if (!biomeName.equals("Ice Plains")) {
			return;
		}
		// Building bottom of igloo by blocks of snow
		edit.setToID(posX - 3, posY - 3, posZ - 3, 7, 4, 7, SNOW);
		// Building top of house by blocks of snow
		edit.setToID(posX - 2, posY + 1, posZ - 2, 5, 4, 5, SNOW);
		// Set to air blocks inside house
		edit.setToAir(posX - 1, posY + 1, posZ - 1, 3, 3, 3);
		// Windows
		world.setBlock(posX - 2, posY + 2, posZ, GLASS);
		world.setBlock(posX + 2, posY + 2, posZ, GLASS);
		world.setBlock(posX, posY + 2, posZ + 2, GLASS);
		world.setBlockToAir(posX, posY + 2, posZ - 2);
		world.setBlockToAir(posX, posY + 1, posZ - 2);
		world.setBlock(posX, posY + 3, posZ - 1, TORCH);
		// Pillars
		for (int y = posY + 1; y <= posY + 4; y++) {
			world.setBlockToAir(posX - 2, y, posZ - 2);
			world.setBlockToAir(posX + 2, y, posZ - 2);
			world.setBlockToAir(posX + 2, y, posZ + 2);
			world.setBlockToAir(posX - 2, y, posZ + 2);
		}
		// Roof of house
		world.setBlockToAir(posX - 2, posY + 4, posZ - 1);
		world.setBlockToAir(posX - 2, posY + 4, posZ);
		world.setBlockToAir(posX - 2, posY + 4, posZ + 1);
		world.setBlockToAir(posX + 2, posY + 4, posZ + 1);
		world.setBlockToAir(posX + 2, posY + 4, posZ - 1);
		world.setBlockToAir(posX + 2, posY + 4, posZ);
		world.setBlockToAir(posX + 1, posY + 4, posZ + 2);
		world.setBlockToAir(posX - 1, posY + 4, posZ + 2);
		world.setBlockToAir(posX, posY + 4, posZ + 2);
		world.setBlockToAir(posX + 1, posY + 4, posZ - 2);
		world.setBlockToAir(posX, posY + 4, posZ - 2);
		world.setBlockToAir(posX - 1, posY + 4, posZ - 2);
		// Door
		ItemDoor.placeDoorBlock(world, posX, posY + 1, posZ - 2, 1, Blocks.wooden_door);
		// Spawn villager inside house
		EntityVillager entityvillager = new EntityVillager(world, 0);
		entityvillager.setProfession(1);
		entityvillager.setLocationAndAngles((double) posX + 0.5D, (double) posY + 1, (double) posZ + 0.5D, 0.0F, 0.0F);
		world.spawnEntityInWorld(entityvillager);
		// Chest in house
		world.setBlock(posX - 1, posY + 1, posZ - 1, CHEST, 0, 2);
		TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(posX - 1, posY + 1, posZ - 1);

		if (tileentitychest != null) {
			ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);
			WeightedRandomChestContent.generateChestContents(rand, info.getItems(rand), tileentitychest, info.getCount(rand));
		}
	}
}