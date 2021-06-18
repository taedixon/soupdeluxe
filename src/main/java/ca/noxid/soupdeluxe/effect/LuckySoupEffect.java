package ca.noxid.soupdeluxe.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LuckySoupEffect extends Effect {

	private static final Logger LOGGER = LogManager.getLogger();

	public LuckySoupEffect() {
		super(EffectType.BENEFICIAL, 0xFF00FF);
		setRegistryName("effect_lucky_soup");
	}

	@Override
	public boolean isDurationEffectTick(int p_76397_1_, int p_76397_2_) {
		return false;
	}
}
