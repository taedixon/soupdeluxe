package ca.noxid.soupdeluxe.loot;

import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;

public class SilkTouchModifier extends LootModifier {
	private static final Logger LOGGER = LogManager.getLogger();
	/**
	 * Constructs a LootModifier.
	 *
	 * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
	 */
	protected SilkTouchModifier(ILootCondition[] conditionsIn) {
		super(conditionsIn);
	}

	// https://github.com/MinecraftForge/MinecraftForge/blob/1.15.x/src/test/java/net/minecraftforge/debug/gameplay/loot/GlobalLootModifiersTest.java
	@Nonnull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		ItemStack ctxTool = context.getParamOrNull(LootParameters.TOOL);
		if (ctxTool != null) {
			//return early if silk-touch is already applied (otherwise we'll get stuck in an infinite loop).
			if (EnchantmentHelper.getEnchantments(ctxTool).containsKey(Enchantments.SILK_TOUCH)) return generatedLoot;
			ItemStack fakeTool = ctxTool.copy();
			fakeTool.enchant(Enchantments.SILK_TOUCH, 1);
			LootContext.Builder builder = new LootContext.Builder(context);
			builder.withParameter(LootParameters.TOOL, fakeTool);
			LootContext ctx = builder.create(LootParameterSets.BLOCK);
			BlockState maybeState = context.getParamOrNull(LootParameters.BLOCK_STATE);
			if (maybeState != null) {
				LootTable loottable = context
						.getLevel()
						.getServer()
						.getLootTables()
						.get(maybeState.getBlock().getLootTable());
				return loottable.getRandomItems(ctx);
			}
		}
		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<SilkTouchModifier> {

		@Override
		public SilkTouchModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
			return new SilkTouchModifier(ailootcondition);
		}

		@Override
		public JsonObject write(SilkTouchModifier instance) {
			return new JsonObject();
		}
	}
}
