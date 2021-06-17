package ca.noxid.soupdeluxe.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SilkTouchEffect extends Effect {

	private static final Logger LOGGER = LogManager.getLogger();

	public SilkTouchEffect() {
		super(EffectType.BENEFICIAL, 0xFF00FF);
		setRegistryName("effect_silk_touch");
	}

	@Override
	public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
		return false;
	}
}
