package daniel.perez.minecraft.coronacraft.base

import net.minecraft.item.Item

abstract class BaseItem(name: String, properties: Properties): Item(properties)
{
    init
    {
        setRegistryName(name)
    }
}