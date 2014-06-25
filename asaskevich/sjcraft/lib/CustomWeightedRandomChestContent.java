package asaskevich.sjcraft.lib;

import java.util.Random;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class CustomWeightedRandomChestContent extends WeightedRandomChestContent {
	public ItemStack	theItemId;
	public int			theMinimumChanceToGenerateItem;
	public int			theMaximumChanceToGenerateItem;

	public CustomWeightedRandomChestContent(Item item, int p_i45311_2_, int minChance, int maxChance, int p_i45311_5_, int damage) {
		super(item, p_i45311_2_, minChance, maxChance, p_i45311_5_);
		this.theItemId = new ItemStack(item, 1, damage);
		this.theMinimumChanceToGenerateItem = minChance;
		this.theMaximumChanceToGenerateItem = maxChance;
	}

	/**
	 * Generates the Chest contents.
	 */
	public static void generateChestContents(Random par0Random, WeightedRandomChestContent[] par1ArrayOfWeightedRandomChestContent,
			IInventory par2IInventory, int par3) {
		for (int j = 0; j < par3; ++j) {
			CustomWeightedRandomChestContent weightedrandomchestcontent = (CustomWeightedRandomChestContent) WeightedRandom.getRandomItem(par0Random,
					par1ArrayOfWeightedRandomChestContent);
			ItemStack[] stacks = weightedrandomchestcontent.generateChestContent(par0Random, par2IInventory);
			for (ItemStack item : stacks) {
				par2IInventory.setInventorySlotContents(par0Random.nextInt(par2IInventory.getSizeInventory()), item);
			}
		}
	}

	public static void generateDispenserContents(Random p_150706_0_, WeightedRandomChestContent[] p_150706_1_,
			TileEntityDispenser p_150706_2_, int p_150706_3_) {
		for (int j = 0; j < p_150706_3_; ++j) {
			CustomWeightedRandomChestContent weightedrandomchestcontent = (CustomWeightedRandomChestContent) WeightedRandom.getRandomItem(p_150706_0_,
					p_150706_1_);
			int k = weightedrandomchestcontent.theMinimumChanceToGenerateItem
					+ p_150706_0_.nextInt(weightedrandomchestcontent.theMaximumChanceToGenerateItem
							- weightedrandomchestcontent.theMinimumChanceToGenerateItem + 1);
			ItemStack[] stacks = weightedrandomchestcontent.generateChestContent(p_150706_0_, p_150706_2_);
			for (ItemStack item : stacks) {
				p_150706_2_.setInventorySlotContents(p_150706_0_.nextInt(p_150706_2_.getSizeInventory()), item);
			}
		}
	}

	public static WeightedRandomChestContent[] func_92080_a(WeightedRandomChestContent[] par0ArrayOfWeightedRandomChestContent,
			WeightedRandomChestContent... par1ArrayOfWeightedRandomChestContent) {
		WeightedRandomChestContent[] aweightedrandomchestcontent1 = new WeightedRandomChestContent[par0ArrayOfWeightedRandomChestContent.length
				+ par1ArrayOfWeightedRandomChestContent.length];
		int i = 0;
		for (int j = 0; j < par0ArrayOfWeightedRandomChestContent.length; ++j) {
			aweightedrandomchestcontent1[i++] = par0ArrayOfWeightedRandomChestContent[j];
		}
		WeightedRandomChestContent[] aweightedrandomchestcontent2 = par1ArrayOfWeightedRandomChestContent;
		int k = par1ArrayOfWeightedRandomChestContent.length;
		for (int l = 0; l < k; ++l) {
			WeightedRandomChestContent weightedrandomchestcontent1 = aweightedrandomchestcontent2[l];
			aweightedrandomchestcontent1[i++] = weightedrandomchestcontent1;
		}
		return aweightedrandomchestcontent1;
	}

	// -- Forge hooks
	/**
	 * Allow a mod to submit a custom implementation that can delegate item stack generation beyond simple stack lookup
	 * 
	 * @param random The current random for generation
	 * @param newInventory The inventory being generated (do not populate it, but you can refer to it)
	 * @return An array of {@link ItemStack} to put into the chest
	 */
	protected ItemStack[] generateChestContent(Random random, IInventory newInventory) {
		return ChestGenHooks.generateStacks(random, theItemId, theMinimumChanceToGenerateItem, theMaximumChanceToGenerateItem);
	}
}