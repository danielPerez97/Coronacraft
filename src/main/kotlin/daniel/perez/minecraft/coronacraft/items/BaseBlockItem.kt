package daniel.perez.minecraft.coronacraft.items

import net.minecraft.block.Block
import net.minecraft.item.BlockItem

class BaseBlockItem(block: Block, properties: Properties): BlockItem(block, properties)
{
    init
    {
        registryName = block.registryName
    }
}