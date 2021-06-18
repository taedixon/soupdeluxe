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

	public static Item CarrotCream() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1f)
						.build()
				);
		return new Item(prop).setRegistryName("cream_soup");
	}

	public static Item Pottage() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1f)
						.build()
				);
		return new Item(prop).setRegistryName("pottage");
	}

	public static Item Consomme() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1f)
						.build()
				);
		return new Item(prop).setRegistryName("consomme");
	}

	public static Item SimpleStew() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1f)
						.build()
				);
		return new Item(prop).setRegistryName("simple_stew");
	}

	public static Item Borscht() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1f)
						.build()
				);
		return new Item(prop).setRegistryName("borscht");
	}
}
