package ca.noxid.soupdeluxe.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.ForgeMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class DolphinSoupEffect extends Effect {
	private static final Logger LOGGER = LogManager.getLogger();
	boolean effectOver = false;
	private static final UUID modUUID = UUID.fromString("3353dad4-7bbd-4eae-9f56-6be527b4b753");

	public DolphinSoupEffect() {
		super(EffectType.BENEFICIAL, 0xFF00FF);
		setRegistryName("effect_dolphin_soup");
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		if (duration <= 1) {
			effectOver = true;
			return true;
		}
		return duration % 10 == 0;
	}

	@Override
	public void applyEffectTick(LivingEntity ent, int amplifier) {
		ModifiableAttributeInstance speedAttrib = ent.getAttribute(ForgeMod.SWIM_SPEED.get());
		if (speedAttrib != null) {
			speedAttrib.removeModifier(modUUID);
			if (!effectOver && ent.isInWater()) {
				ent.setAirSupply(Math.min(ent.getMaxAirSupply(), ent.getAirSupply() + 3 + amplifier));
				final AttributeModifier mod = new AttributeModifier(modUUID,
						"Soup swimming boost",
						8 + amplifier * 0.5,
						AttributeModifier.Operation.MULTIPLY_BASE);
				speedAttrib.addTransientModifier(mod);
			}
		}
	}
}
