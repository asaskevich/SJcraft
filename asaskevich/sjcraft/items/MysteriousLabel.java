package asaskevich.sjcraft.items;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import asaskevich.sjcraft.gui.GuiMysteriousLabel;
import asaskevich.sjcraft.mod.ModInfo;

public class MysteriousLabel extends Item {
	private Minecraft			mc;
	private GuiMysteriousLabel	gui;

	public MysteriousLabel() {
		this.mc = Minecraft.getMinecraft();
		this.gui = new GuiMysteriousLabel();
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setMaxDamage(0);
		this.setTextureName(ModInfo.ID + ":mysterious_label");
		this.setMaxStackSize(32);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
		list.add(new ItemStack(item, 1, 2));
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9,
			float par10) {
		if (this.getDamage(stack) == 0) world.newExplosion(player, x + 0.0, y + 0.0, z + 0.0, 10f, true, true);
		if (this.getDamage(stack) == 1) world.spawnEntityInWorld(new EntityLightningBolt(world, x, y, z));
		if (this.getDamage(stack) == 2) {
			List<Entity> entities = world.getLoadedEntityList();
			for (Entity entity : entities)
				if (entity.isCreatureType(EnumCreatureType.monster, false)) entity.setFire(100000);
		}
		stack.stackSize--;
		return true;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return onItemUse(stack, player, player.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ, 0, 0, 0, 0);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World w, EntityPlayer player) {
		gui.setTextID(this.getDamage(stack));
		mc.displayGuiScreen(gui);
		return super.onItemRightClick(stack, w, player);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
		super.addInformation(stack, par2EntityPlayer, list, par4);
		list.add("Warning! It can kill you!");
		if (this.getDamage(stack) == 0) list.add("Explosions.");
		if (this.getDamage(stack) == 1) list.add("Lightning Bolts.");
		if (this.getDamage(stack) == 2) list.add("Setting Fire.");
	}
}
