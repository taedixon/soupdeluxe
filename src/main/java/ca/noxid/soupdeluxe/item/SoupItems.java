package ca.noxid.soupdeluxe.item;

import ca.noxid.soupdeluxe.effect.SoupEffects;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.function.Supplier;

public class SoupItems {
	public static Item TestSoup = new TestSoup();

	public static Item DolphinTail = new Item(
			new Item.Properties()
			.tab(ItemGroup.TAB_MISC)
			.stacksTo(64)).setRegistryName("dolphin_tail");

	public static Item BoneBroth = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(2)
					.saturationMod(1f)
					.build()
			)).setRegistryName("bone_broth");

	public static Item VegeBroth = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(2)
					.saturationMod(1f)
					.build()
			)).setRegistryName("veg_broth");

	public static Item CarrotCream = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1f)
					.build()
			)).setRegistryName("cream_soup");

	public static Item Pottage = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1f)
					.build()
			)).setRegistryName("pottage");

	public static Item Consomme = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1f)
					.build()
			)).setRegistryName("consomme");

	public static Item SimpleStew = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1f)
					.build()
			)).setRegistryName("simple_stew");

	public static Item Borscht = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1f)
					.build()
			)).setRegistryName("borscht");

	public static Item DeluxeBorscht = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1.5f)
					.effect(new SoupEffectProvider(Effects.DIG_SPEED), 1f)
					.build()
			)).setRegistryName("deluxe_borscht");

	public static Item SpookyBorscht = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1.5f)
					.effect(new SoupEffectProvider(Effects.GLOWING), 1f)
					.effect(new SoupEffectProvider(Effects.NIGHT_VISION), 1f)
					.build()
			)).setRegistryName("spooky_borscht");

	public static Item RichStew = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(8)
					.saturationMod(1.5f)
					.build()
			)).setRegistryName("rich_stew");

	public static Item LuckyStew = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1.5f)
					.effect(new SoupEffectProvider(SoupEffects.LUCKY_SOUP), 1f)
					.build()
			)).setRegistryName("lucky_stew");

	public static Item DelicateConsomme = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1.5f)
					.effect(new SoupEffectProvider(SoupEffects.SILK_TOUCH), 1f)
					.effect(new SoupEffectProvider(Effects.WEAKNESS), 1f)
					.build()
			)).setRegistryName("delicate_consomme");

	public static Item ExoticConsomme = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1.5f)
					.effect(new SoupEffectProvider(Effects.FIRE_RESISTANCE), 1f)
					.build()
			)).setRegistryName("exotic_consomme");

	public static Item Chottage = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1.5f)
					.effect(new SoupEffectProvider(Effects.JUMP), 1f)
					.effect(new SoupEffectProvider(Effects.SLOW_FALLING), 1f)
					.build()
			)).setRegistryName("chottage");

	public static Item FancyPottage = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1.5f)
					.effect(new SoupEffectProvider(Effects.MOVEMENT_SPEED), 1f)
					.build()
			)).setRegistryName("fancy_pottage");

	public static Item HoneyCream = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1.5f)
					.build()
			)).setRegistryName("honey_cream");

	public static Item FishChowder = new Item(new Item.Properties()
			.tab(ItemGroup.TAB_FOOD)
			.stacksTo(64)
			.food(new Food.Builder()
					.nutrition(4)
					.saturationMod(1.5f)
					.effect(new SoupEffectProvider(SoupEffects.SEA_SOUP), 1f)
					.build()
			)).setRegistryName("fish_chowder");

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
