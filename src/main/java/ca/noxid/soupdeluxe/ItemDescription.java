package ca.noxid.soupdeluxe;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.LinkedList;
import java.util.List;

public class ItemDescription {
	public final List<ITextComponent> lines;
	public final List<ITextComponent> extraLines;

	ItemDescription(String descId) {
		lines = new LinkedList<>();
		extraLines = new LinkedList<>();
		String maybeId = descId + ".tooltip";
		LanguageMap lmap = LanguageMap.getInstance();
		if (lmap.has(maybeId)) {
			String[] langstring = lmap.getOrDefault(maybeId).split("\n");
			for (String s : langstring) {
				lines.add(new StringTextComponent(s).withStyle(TextFormatting.BLUE));
			}
		}
		String maybeExtraId = maybeId + ".shift";
		if (lmap.has((maybeExtraId))) {
			String[] langstring = lmap.getOrDefault(maybeExtraId).split("\n");
			for (String s : langstring) {
				extraLines.add(new StringTextComponent(s).withStyle(TextFormatting.BLUE));
			}
		}
	}

	public boolean hasExtraDetail() {
		return extraLines.size() > 0;
	}
}
