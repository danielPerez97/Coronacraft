package daniel.perez.minecraft.coronacraft.base

import net.minecraft.block.Block
import net.minecraft.item.Item

abstract class BaseBlock(name: String, properties: Properties) : Block(properties)
{
    init
    {
        setRegistryName(name)
    }

    abstract fun blockItemProperties(): Item.Properties
}