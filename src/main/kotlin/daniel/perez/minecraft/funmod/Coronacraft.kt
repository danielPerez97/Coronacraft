package daniel.perez.minecraft.funmod

import net.alexwells.kottle.FMLKotlinModLoadingContext
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent.Register
import net.minecraftforge.event.entity.living.LivingAttackEvent
import net.minecraftforge.eventbus.api.Event
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.InterModComms
import net.minecraftforge.fml.InterModComms.IMCMessage
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent
import net.minecraftforge.fml.event.server.FMLServerStartingEvent
import org.apache.logging.log4j.LogManager
import java.util.stream.Collectors

@Mod(MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
object Coronacraft
{
    private val LOGGER = LogManager.getLogger()

    @SubscribeEvent
    fun setup(event: FMLCommonSetupEvent)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT")
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.registryName)
    }

    @SubscribeEvent
    fun doClientStuff(event: FMLClientSetupEvent)
    {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.minecraftSupplier.get().gameSettings)
        val disposable = MinecraftForge.EVENT_BUS.events<LivingAttackEvent>()
                .filter { it.source.isCreativePlayer }
                .subscribe {
                    LOGGER.info("LIVING ATTACK EVENT")
                    LOGGER.info("Source: ${it.source.damageType} Amount: ${it.amount}")
                }
    }

    @SubscribeEvent
    fun enqueueIMC(event: InterModEnqueueEvent)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("funmod", "helloworld") {
            LOGGER.info("Hello world from the MDK")
            "Hello world"
        }

    }

    @SubscribeEvent
    fun processIMC(event: InterModProcessEvent)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.imcStream.map { m: IMCMessage -> m.getMessageSupplier<Any>().get() }.collect(Collectors.toList()).toString())
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    fun onServerStarting(event: FMLServerStartingEvent?)
    {
        // do something when the server starts
        LOGGER.info("HELLO from server starting")
        FMLKotlinModLoadingContext.get().modEventBus.addListener { newEvent: Event? -> }
    }

    @SubscribeEvent
    fun onBlocksRegistry(blockRegistryEvent: Register<Block>)
    {
        LOGGER.info("HELLO from Register Block")
    }
}