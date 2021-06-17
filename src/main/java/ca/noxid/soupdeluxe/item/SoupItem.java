package ca.noxid.soupdeluxe.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class SoupItem {
	public static Item TestSoup() {
		return new TestSoup();
	}

	public static Item BoneBroth() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(2)
						.saturationMod(1f)
						.build()
				);
		return new Item(prop).setRegistryName("bone_broth");
	}

	public static Item VegeBroth() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(2)
						.saturationMod(1f)
						.build()
				);
		return new Item(prop).setRegistryName("veg_broth");
	}
}
