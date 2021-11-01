package ca.noxid.soupdeluxe;

import com.mojang.bridge.game.Language;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TooltipHelper {
	public static Language cachedLanguage;
	private static ITextComponent shiftText = null;
	private static final Map<String, ItemDescription> cachedTooltips = new HashMap<>();

	private static void checkLocale() {
		Language currentLanguage = Minecraft.getInstance()
				.getLanguageManager()
				.getSelected();
		if (cachedLanguage != currentLanguage) {
			cachedTooltips.clear();
			cachedLanguage = currentLanguage;
			LanguageMap lmap = LanguageMap.getInstance();
			shiftText = new TranslationTextComponent(
					"soupdeluxe.tooltip.holdfordetail",
					new TranslationTextComponent("soupdeluxe.tooltip.keyshift").withStyle(TextFormatting.GRAY))
					.withStyle(TextFormatting.DARK_GRAY);
		}
	}

	public static void addTooltip(List<ITextComponent> toolTip, String descId) {
		checkLocale();

		ItemDescription desc = getOrLoadDesc(descId);
		if (Screen.hasShiftDown() && desc.hasExtraDetail()) {
			toolTip.addAll(desc.extraLines);
		} else {
			if (desc.hasExtraDetail()) {
				toolTip.add(shiftText);
			}
			toolTip.addAll(desc.lines);
		}
	}

	private static ItemDescription getOrLoadDesc(String descId) {
		if (cachedTooltips.containsKey(descId)) {
			return cachedTooltips.get(descId);
		} else {
			ItemDescription desc = new ItemDescription(descId);
			cachedTooltips.put(descId, desc);
			return desc;
		}
	}
}
