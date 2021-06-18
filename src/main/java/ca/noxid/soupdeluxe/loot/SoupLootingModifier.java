package ca.noxid.soupdeluxe.loot;

import ca.noxid.soupdeluxe.effect.SoupEffects;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;

public class SoupLootingModifier extends LootModifier {
	private static final Logger LOGGER = LogManager.getLogger();
	/**
	 * Constructs a LootModifier.
	 *
	 * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
	 */
	protected SoupLootingModifier(ILootCondition[] conditionsIn) {
		super(conditionsIn);
	}

	// https://github.com/MinecraftForge/MinecraftForge/blob/1.15.x/src/test/java/net/minecraftforge/debug/gameplay/loot/GlobalLootModifiersTest.java
	@Nonnull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		PlayerEntity pKiller = context.getParamOrNull(LootParameters.LAST_DAMAGE_PLAYER);
		Entity killed = context.getParamOrNull(LootParameters.THIS_ENTITY);
		LOGGER.info("Applying looting modifier. Killed is " + killed);
		LOGGER.info(pKiller);
		if (pKiller != null && killed instanceof MobEntity) {
			int toolLooting = context.getLootingModifier();
			EffectInstance soupEffect = pKiller.getEffect(SoupEffects.LUCKY_SOUP);
			int soupLevel = -1;
			if (soupEffect != null) {
				soupLevel = soupEffect.getAmplifier() + 1;
			}
			if (toolLooting >= soupLevel) {
				return generatedLoot;
			}
			LOGGER.info(String.format("Applying levels. Looting: %d; Soup: %d", toolLooting, soupLevel));
			LivingEntity fakeEntity = new FakePlayer(context.getLevel(), pKiller.getGameProfile());
			ItemStack fakeWeapon = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:stick")));
			fakeWeapon.enchant(Enchantments.MOB_LOOTING, soupLevel);
			fakeEntity.setItemInHand(Hand.MAIN_HAND, fakeWeapon);
			LootContext ctx = new LootContext.Builder(context)
					.withParameter(LootParameters.KILLER_ENTITY, fakeEntity)
					.create(LootParameterSets.ENTITY);
			LootTable loottable = context
					.getLevel()
					.getServer()
					.getLootTables()
					.get(((MobEntity) killed).getLootTable());
			return loottable.getRandomItems(ctx);
		}
		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<SoupLootingModifier> {

		@Override
		public SoupLootingModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
			return new SoupLootingModifier(ailootcondition);
		}

		@Override
		public JsonObject write(SoupLootingModifier instance) {
			return new JsonObject();
		}
	}
}
