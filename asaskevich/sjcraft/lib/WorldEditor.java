package asaskevich.sjcraft.lib;

import net.minecraft.world.World;

public class WorldEditor {
	World world;

	public WorldEditor(World world) {
		this.world = world;
	}

	public void setToAir(int posX, int posY, int posZ, int lengthX,
			int lengthY, int lengthZ) {
		for (int x = posX; x < posX + lengthX; x++)
			for (int y = posY; y < posY + lengthY; y++)
				for (int z = posZ; z < posZ + lengthZ; z++)
					world.setBlockToAir(x, y, z);
	}

	public void setToID(int posX, int posY, int posZ, int lengthX, int lengthY,
			int lengthZ, int id) {
		for (int x = posX; x < posX + lengthX; x++)
			for (int y = posY; y < posY + lengthY; y++)
				for (int z = posZ; z < posZ + lengthZ; z++)
					world.setBlock(x, y, z, id);
	}
}
