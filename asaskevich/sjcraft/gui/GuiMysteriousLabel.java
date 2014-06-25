package asaskevich.sjcraft.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import asaskevich.sjcraft.data.MysteriousLabelData;

public class GuiMysteriousLabel extends GuiScreen {
	private int id = 0;
	private Minecraft mc;
	private ResourceLocation texture = new ResourceLocation("textures/gui/mysterious_label.png");
	private int imageWidth = 192;
	private int imageHeight = 192;

	public GuiMysteriousLabel() {}

	@Override
	public void initGui() {
		if (this.mc == null) this.mc = Minecraft.getMinecraft();
		super.initGui();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.imageWidth) / 2;
		int b0 = (this.height - this.imageHeight) / 2;
		this.drawTexturedModalRect(k, b0, 0, 0, this.imageWidth, this.imageHeight);
		this.fontRendererObj.drawSplitString(MysteriousLabelData.data[id], k + 15, b0 + 15, this.imageWidth - 30, 0);
		super.drawScreen(par1, par2, par3);
	}

	public void setTextID(int id) {
		this.id = id;
	}
}
