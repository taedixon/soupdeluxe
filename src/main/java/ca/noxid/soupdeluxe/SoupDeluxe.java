package ca.noxid.soupdeluxe;

import ca.noxid.soupdeluxe.effect.SoupEffects;
import ca.noxid.soupdeluxe.loot.SoupFortuneModifier;
import ca.noxid.soupdeluxe.loot.SilkTouchModifier;
import ca.noxid.soupdeluxe.item.SoupItem;
import ca.noxid.soupdeluxe.loot.SoupLootingModifier;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
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
	/**
	 * Cancel the FOV decrease caused by the decreasing speed due to player penalties.
	 * Original FOV value given by the event is never used, we start from scratch 1.0F value.
	 * Edited from AbstractClientPlayer.getFovModifier()
	 * @param event
	 */
	//@SubscribeEvent
	public void onFOVUpdate(FOVUpdateEvent event) {
		//LOGGER.info("FOV update");
		PlayerEntity player = event.getEntity();
		if (player.getActiveEffects().stream().anyMatch((e) -> e.getEffect() == SoupEffects.SEA_SOUP)) {
			//LOGGER.info("Recalculating for soup");
			float f = 1.0F;

			if (player.abilities.flying) {
				f *= 1.1F;
			}

			if (player.isUsingItem() && player.getUseItem().getItem() == Items.BOW) {
				int i = player.getTicksUsingItem();
				float f1 = (float)i / 20.0F;
				if (f1 > 1.0F) {
					f1 = 1.0F;
				} else {
					f1 = f1 * f1;
				}

				f *= 1.0F - f1 * 0.15F;
			}
			event.setNewfov(f);
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
			eve.getRegistry().register(SoupItem.TestSoup());
			eve.getRegistry().register(SoupItem.BoneBroth());
			eve.getRegistry().register(SoupItem.VegeBroth());
			eve.getRegistry().register(SoupItem.CarrotCream());
			eve.getRegistry().register(SoupItem.Pottage());
			eve.getRegistry().register(SoupItem.Consomme());
			eve.getRegistry().register(SoupItem.SimpleStew());
			eve.getRegistry().register(SoupItem.Borscht());
		}

		@SubscribeEvent
		public static void onRegisterEffects(final RegistryEvent.Register<Effect> eve) {
			eve.getRegistry().register(SoupEffects.SILK_TOUCH);
			eve.getRegistry().register(SoupEffects.LUCKY_SOUP);
			eve.getRegistry().register(SoupEffects.SEA_SOUP);
		}

		@SubscribeEvent
		public static void onRegisterLootModifiers(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> eve) {
			eve.getRegistry().register(new SilkTouchModifier.Serializer().setRegistryName("loot_silk_soup"));
			eve.getRegistry().register(new SoupFortuneModifier.Serializer().setRegistryName("loot_soup_fortune"));
			eve.getRegistry().register(new SoupLootingModifier.Serializer().setRegistryName("loot_soup_looting"));
		}
	}
}
