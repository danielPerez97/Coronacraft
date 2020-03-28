package daniel.perez.minecraft.coronacraft.registration

import daniel.perez.minecraft.coronacraft.MODID
import daniel.perez.minecraft.coronacraft.items.Chickenizer
import daniel.perez.minecraft.coronacraft.items.WoodItem
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

// 1) Register your item here
// 2) Give it a model json
// 3) Give it a texture

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
object ModItems
{
    @SubscribeEvent
    fun register(blockRegistryEvent: RegistryEvent.Register<Item>)
    {
        with(blockRegistryEvent.registry)
        {
            register(WoodItem)
            register(Chickenizer)
        }
    }
}