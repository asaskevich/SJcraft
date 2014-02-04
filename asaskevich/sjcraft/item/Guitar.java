package asaskevich.sjcraft.item;

import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import asaskevich.sjcraft.lib.ItemInfo;
import asaskevich.sjcraft.lib.ModInfo;

public class Guitar extends Item {
	private String[] names = { "harp", "bd", "snare", "hat", "bassattack" };
	private Random r = new Random();

	public Guitar(int id) {
		super(id);
		this.setFull3D();
		setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.ID + ':'
				+ ItemInfo.guitar_name);
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer,
			World world, int x, int y, int z, int par7, float par8, float par9,
			float par10) {
		if (par7 == 0)
			y--;
		if (par7 == 1)
			y++;
		if (par7 == 2)
			z--;
		if (par7 == 3)
			z++;
		if (par7 == 4)
			x--;
		if (par7 == 5)
			x++;
		String s = "harp";
		s = names[r.nextInt(names.length)];
		double d0 = (double) ((float) x + 0.5F);
		double d1 = (double) ((float) y + 0.7F);
		double d2 = (double) ((float) z + 0.5F);
		double d3 = 0.2199999988079071D;
		double d4 = 0.27000001072883606D;

		world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "note." + s, 3.0F,
				world.rand.nextFloat() * 0.1F + 0.9F);
		world.spawnParticle("note", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		return true;

	}
}