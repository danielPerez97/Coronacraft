package daniel.perez.minecraft.coronacraft

import daniel.perez.minecraft.coronacraft.rx.events
import net.minecraft.block.Blocks
import net.minecraft.item.Items
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.living.LivingAttackEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.LogManager

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
        Items.STICK
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
}