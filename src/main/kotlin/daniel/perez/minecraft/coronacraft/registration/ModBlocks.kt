package daniel.perez.minecraft.coronacraft.registration

import daniel.perez.minecraft.coronacraft.MODID
import daniel.perez.minecraft.coronacraft.blocks.BaseBlock
import daniel.perez.minecraft.coronacraft.blocks.WoodBlock
import daniel.perez.minecraft.coronacraft.items.BaseBlockItem
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
object ModBlocks
{
    private val blocks: MutableList<BaseBlock> = mutableListOf()

    init
    {
        blocks.add(WoodBlock)
    }

    @SubscribeEvent
    fun registerBlocks(blockRegistryEvent: RegistryEvent.Register<Block>)
    {
        blocks.forEach {
            blockRegistryEvent.registry.register(it)
        }
    }

    @SubscribeEvent
    fun registerBlockItems(itemRegistryEvent: RegistryEvent.Register<Item>)
    {
        blocks.forEach {
            itemRegistryEvent.registry.register( BaseBlockItem( it, it.blockItemProperties() ) )
        }
    }
}