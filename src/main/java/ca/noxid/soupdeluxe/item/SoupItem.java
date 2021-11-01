package ca.noxid.soupdeluxe.item;

import ca.noxid.soupdeluxe.TooltipHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.List;

public class SoupItem extends Item {
	public SoupItem(Properties prop) {
		super(prop);
	}

	public void setTooltip(List<ITextComponent> toolTip) {
		TooltipHelper.addTooltip(toolTip, getDescriptionId());
	}
}
