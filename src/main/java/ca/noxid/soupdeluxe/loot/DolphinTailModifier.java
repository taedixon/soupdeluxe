package ca.noxid.soupdeluxe.loot;

import ca.noxid.soupdeluxe.item.SoupItems;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;

public class DolphinTailModifier extends LootModifier {
	private static final Logger LOGGER = LogManager.getLogger();
	/**
	 * Constructs a LootModifier.
	 *
	 * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
	 */
	protected DolphinTailModifier(ILootCondition[] conditionsIn) {
		super(conditionsIn);
	}

	// https://github.com/MinecraftForge/MinecraftForge/blob/1.15.x/src/test/java/net/minecraftforge/debug/gameplay/loot/GlobalLootModifiersTest.java
	@Nonnull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.add(new ItemStack(SoupItems.DolphinTail));
		LOGGER.info("dofin");
		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<DolphinTailModifier> {

		@Override
		public DolphinTailModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
			return new DolphinTailModifier(ailootcondition);
		}

		@Override
		public JsonObject write(DolphinTailModifier instance) {
			return new JsonObject();
		}
	}
}
