package daniel.perez.minecraft.coronacraft.items

import net.minecraft.item.Item

abstract class BaseItem(name: String, properties: Properties): Item(properties)
{
    init
    {
        setRegistryName(name)
    }
}