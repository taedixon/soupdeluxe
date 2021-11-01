package ca.noxid.soupdeluxe;

import ca.noxid.soupdeluxe.effect.SoupEffects;
import ca.noxid.soupdeluxe.item.SoupItem;
import ca.noxid.soupdeluxe.loot.DolphinTailModifier;
import ca.noxid.soupdeluxe.loot.SoupFortuneModifier;
import ca.noxid.soupdeluxe.loot.SilkTouchModifier;
import ca.noxid.soupdeluxe.item.SoupItems;
import ca.noxid.soupdeluxe.loot.SoupLootingModifier;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("soupdeluxe")
@SuppressWarnings("unused")
public class SoupDeluxe
{
	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();

	public SoupDeluxe() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		// Register the doClientStuff method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		// some preinit code
		LOGGER.info("HELLO FROM PREINIT");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		// do something that can only be done on the client
		LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
	}

	private void enqueueIMC(final InterModEnqueueEvent event)
	{
		// some example code to dispatch IMC to another mod
		InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
	}

	private void processIMC(final InterModProcessEvent event)
	{
		// some example code to receive and process InterModComms from other mods
		LOGGER.info("Got IMC {}", event.getIMCStream().
				map(m->m.getMessageSupplier().get()).
				collect(Collectors.toList()));
	}
	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// do something when the server starts
		LOGGER.info("HELLO from server starting");
	}

	@Mod.EventBusSubscriber(modid="soupdeluxe", bus= Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
	public static class ForgeEventRegistry {
		@SubscribeEvent
		public static void onGetTooltipDetail(ItemTooltipEvent eve) {
			ItemStack stack = eve.getItemStack();
			if (stack.getItem() instanceof SoupItem) {
				SoupItem soup = (SoupItem) stack.getItem();
				soup.setTooltip(eve.getToolTip());
			}
		}
	}

	// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
	// Event bus for receiving Registry Events)
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			// register a new block here
			LOGGER.info("HELLO from Register Block");
		}
		@SubscribeEvent
		public static void onRegisterItems(final RegistryEvent.Register<Item> eve) {
			LOGGER.info("Registering soup...");
			eve.getRegistry().register(SoupItems.TestSoup);

			eve.getRegistry().register(SoupItems.BoneBroth);
			eve.getRegistry().register(SoupItems.VegeBroth);

			eve.getRegistry().register(SoupItems.CarrotCream);
			eve.getRegistry().register(SoupItems.Pottage);
			eve.getRegistry().register(SoupItems.Consomme);
			eve.getRegistry().register(SoupItems.SimpleStew);
			eve.getRegistry().register(SoupItems.Borscht);

			eve.getRegistry().register(SoupItems.DeluxeBorscht);
			eve.getRegistry().register(SoupItems.SpookyBorscht);
			eve.getRegistry().register(SoupItems.RichStew);
			eve.getRegistry().register(SoupItems.LuckyStew);
			eve.getRegistry().register(SoupItems.DelicateConsomme);
			eve.getRegistry().register(SoupItems.ExoticConsomme);
			eve.getRegistry().register(SoupItems.Chottage);
			eve.getRegistry().register(SoupItems.FancyPottage);
			eve.getRegistry().register(SoupItems.HoneyCream);
			eve.getRegistry().register(SoupItems.FishChowder);

			eve.getRegistry().register(SoupItems.DolphinTail);
			eve.getRegistry().register(SoupItems.DolphinSoup);
		}

		@SubscribeEvent
		public static void onRegisterEffects(final RegistryEvent.Register<Effect> eve) {
			eve.getRegistry().register(SoupEffects.SILK_TOUCH);
			eve.getRegistry().register(SoupEffects.LUCKY_SOUP);
			eve.getRegistry().register(SoupEffects.SEA_SOUP);
			eve.getRegistry().register(SoupEffects.DOLPHIN_SOUP);
		}

		@SubscribeEvent
		public static void onRegisterLootModifiers(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> eve) {
			eve.getRegistry().register(new SilkTouchModifier.Serializer().setRegistryName("loot_silk_soup"));
			eve.getRegistry().register(new SoupFortuneModifier.Serializer().setRegistryName("loot_soup_fortune"));
			eve.getRegistry().register(new SoupLootingModifier.Serializer().setRegistryName("loot_soup_looting"));
			eve.getRegistry().register(new DolphinTailModifier.Serializer().setRegistryName("loot_dolphin_tail"));
		}
	}
}
