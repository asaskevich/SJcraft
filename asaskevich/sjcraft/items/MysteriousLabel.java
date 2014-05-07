package asaskevich.sjcraft.items;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import asaskevich.sjcraft.gui.GuiMysteriousLabel;
import asaskevich.sjcraft.mod.ModInfo;

public class MysteriousLabel extends Item {
	private Minecraft mc;
	private GuiMysteriousLabel gui;

	public MysteriousLabel() {
		this.mc = Minecraft.getMinecraft();
		this.gui = new GuiMysteriousLabel();
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setMaxDamage(0);
		this.setTextureName(ModInfo.ID + ":mysterious_label");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
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
		list.add("Be sure, that you use it in right way!");
	}
}
