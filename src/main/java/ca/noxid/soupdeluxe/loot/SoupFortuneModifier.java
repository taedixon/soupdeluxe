package ca.noxid.soupdeluxe.loot;

import ca.noxid.soupdeluxe.effect.SoupEffects;
import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;

public class SoupFortuneModifier extends LootModifier {
	private static final Logger LOGGER = LogManager.getLogger();
	/**
	 * Constructs a LootModifier.
	 *
	 * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
	 */
	protected SoupFortuneModifier(ILootCondition[] conditionsIn) {
		super(conditionsIn);
	}

	// https://github.com/MinecraftForge/MinecraftForge/blob/1.15.x/src/test/java/net/minecraftforge/debug/gameplay/loot/GlobalLootModifiersTest.java
	@Nonnull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		BlockState maybeBlock = context.getParamOrNull(LootParameters.BLOCK_STATE);
		if (maybeBlock != null) {
			ItemStack ctxTool = context.getParamOrNull(LootParameters.TOOL);
			Entity holder = context.getParamOrNull(LootParameters.THIS_ENTITY);
			LOGGER.info("Applying fortune modifier. Holder is " + holder);
			if (ctxTool != null && holder instanceof PlayerEntity) {
				PlayerEntity pHolder = (PlayerEntity) holder;
				int toolFortune = EnchantmentHelper.getEnchantments(ctxTool).getOrDefault(Enchantments.BLOCK_FORTUNE, -1);
				EffectInstance soupEffect = pHolder.getEffect(SoupEffects.LUCKY_SOUP);
				int soupLevel = -1;
				if (soupEffect != null) {
					soupLevel = soupEffect.getAmplifier() + 1;
				}
				//return early if effect is already applied (otherwise we'll get stuck in an infinite loop).
				if (toolFortune >= soupLevel) {
					return generatedLoot;
				}
				LOGGER.info(String.format("Applying levels. Fortune: %d; Soup: %d", toolFortune, soupLevel));
				ItemStack fakeTool = ctxTool.copy();
				fakeTool.enchant(Enchantments.BLOCK_FORTUNE, soupLevel);
				LootContext.Builder builder = new LootContext.Builder(context);
				builder.withParameter(LootParameters.TOOL, fakeTool);
				LootContext ctx = builder.create(LootParameterSets.BLOCK);
				LootTable loottable = context
						.getLevel()
						.getServer()
						.getLootTables()
						.get(maybeBlock.getBlock().getLootTable());
				return loottable.getRandomItems(ctx);
			}
		}
		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<SoupFortuneModifier> {

		@Override
		public SoupFortuneModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
			return new SoupFortuneModifier(ailootcondition);
		}

		@Override
		public JsonObject write(SoupFortuneModifier instance) {
			return new JsonObject();
		}
	}
}
