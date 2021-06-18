package ca.noxid.soupdeluxe.effect;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.ForgeMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class SeaSoupEffect extends Effect {
	private static final Logger LOGGER = LogManager.getLogger();
	boolean effectOver = false;
	private static final UUID modUUID = UUID.fromString("f8acb429-0dc1-4bad-994f-1dd6dcbf9059");

	public SeaSoupEffect() {
		super(EffectType.BENEFICIAL, 0xFF00FF);
		setRegistryName("effect_sea_soup");
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
						1.5 + amplifier * 0.5,
						AttributeModifier.Operation.MULTIPLY_BASE);
				speedAttrib.addTransientModifier(mod);
			}
		}
	}
}
