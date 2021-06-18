package ca.noxid.soupdeluxe.item;

import ca.noxid.soupdeluxe.effect.SoupEffects;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.function.Supplier;

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

	public static Item DeluxeBorscht() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1.5f)
						.effect(new SoupEffectProvider(Effects.DIG_SPEED), 1f)
						.build()
				);
		return new Item(prop).setRegistryName("deluxe_borscht");
	}

	public static Item SpookyBorscht() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1.5f)
						.effect(new SoupEffectProvider(Effects.GLOWING), 1f)
						.effect(new SoupEffectProvider(Effects.NIGHT_VISION), 1f)
						.build()
				);
		return new Item(prop).setRegistryName("spooky_borscht");
	}

	public static Item RichStew() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(8)
						.saturationMod(1.5f)
						.build()
				);
		return new Item(prop).setRegistryName("rich_stew");
	}

	public static Item LuckyStew() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1.5f)
						.effect(new SoupEffectProvider(SoupEffects.LUCKY_SOUP), 1f)
						.build()
				);
		return new Item(prop).setRegistryName("lucky_stew");
	}

	public static Item DelicateConsomme() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1.5f)
						.effect(new SoupEffectProvider(SoupEffects.SILK_TOUCH), 1f)
						.effect(new SoupEffectProvider(Effects.WEAKNESS), 1f)
						.build()
				);
		return new Item(prop).setRegistryName("delicate_consomme");
	}

	public static Item ExoticConsomme() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1.5f)
						.effect(new SoupEffectProvider(Effects.FIRE_RESISTANCE), 1f)
						.build()
				);
		return new Item(prop).setRegistryName("exotic_consomme");
	}

	public static Item Chottage() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1.5f)
						.effect(new SoupEffectProvider(Effects.JUMP), 1f)
						.effect(new SoupEffectProvider(Effects.SLOW_FALLING), 1f)
						.build()
				);
		return new Item(prop).setRegistryName("chottage");
	}

	public static Item FancyPottage() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1.5f)
						.effect(new SoupEffectProvider(Effects.MOVEMENT_SPEED), 1f)
						.build()
				);
		return new Item(prop).setRegistryName("fancy_pottage");
	}

	public static Item HoneyCream() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1.5f)
						.build()
				);
		return new Item(prop).setRegistryName("honey_cream");
	}

	public static Item FishChowder() {
		final Item.Properties prop = new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(4)
						.saturationMod(1.5f)
						.effect(new SoupEffectProvider(SoupEffects.SEA_SOUP), 1f)
						.build()
				);
		return new Item(prop).setRegistryName("fish_chowder");
	}

	public static class SoupEffectProvider implements Supplier<EffectInstance> {
		Effect fx;
		SoupEffectProvider(Effect fx) {
			this.fx = fx;
		}
		@Override
		public EffectInstance get() {
			return new EffectInstance(fx, 5000, 0);
		}
	}
}
