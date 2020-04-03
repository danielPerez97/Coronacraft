package daniel.perez.minecraft.coronacraft

import daniel.perez.minecraft.coronacraft.rx.events
import net.minecraft.block.Blocks
import net.minecraft.item.Items
import net.minecraft.util.text.StringTextComponent
import net.minecraft.world.dimension.DimensionType
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.living.LivingAttackEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.server.FMLServerStartedEvent
import org.apache.logging.log4j.LogManager
import java.util.concurrent.TimeUnit

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

        MinecraftForge.EVENT_BUS.events<TickEvent.WorldTickEvent>()
                .filter { it.world.dayTime == 12542L }
                .debounce(300, TimeUnit.MILLISECONDS)
                .map { it.world.players.filter { it.dimension == DimensionType.OVERWORLD } }
                .subscribe { players ->
                    LOGGER.info("TIME TO SEND BEDTIME")
                    players.forEach {
                        it.sendMessage( StringTextComponent("ITS BEDTIME") )
                    }
                }
    }

    @SubscribeEvent
    fun serverReady(event: FMLServerStartedEvent)
    {

        if(event.server.serverIsInRunLoop())
        {
            event.server.playerList.players.forEach {
                event.server.playerList.sendMessageToTeamOrAllPlayers(it, StringTextComponent("ITS FUCKING BEDTIME"))
            }
        }
    }
}