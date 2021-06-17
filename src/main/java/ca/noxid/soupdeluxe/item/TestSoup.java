package ca.noxid.soupdeluxe.item;

import ca.noxid.soupdeluxe.effect.EnchantEffects;
import ca.noxid.soupdeluxe.effect.SilkTouchEffect;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.function.Supplier;

public class TestSoup extends Item {
	public TestSoup() {
		super(new Item.Properties()
				.tab(ItemGroup.TAB_FOOD)
				.stacksTo(64)
				.food(new Food.Builder()
						.nutrition(8)
						.alwaysEat()
						.saturationMod(1f)
						.effect(new TestSoupEffectProvider(0), 1f)
						.effect(new TestSoupEffectProvider(1), 1f)
						.effect(new TestSoupEffectProvider(2), 1f)
						.build()
				));
		setRegistryName("test_soup");
	}

	static class TestSoupEffectProvider implements Supplier<EffectInstance> {
		int fx;
		TestSoupEffectProvider(int idx) {
			fx = idx;
		}
		@Override
		public EffectInstance get() {
			final Effect[] effects = {
					Effects.MOVEMENT_SPEED,
					Effects.DAMAGE_RESISTANCE,
					EnchantEffects.SILK_TOUCH,
			};
			return new EffectInstance(effects[fx], 2500, 1);
		}
	}
}
