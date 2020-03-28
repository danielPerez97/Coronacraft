package daniel.perez.minecraft.coronacraft.blocks

import daniel.perez.minecraft.coronacraft.base.BaseBlock
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

private const val name: String = "wood_block"
private val woodBlockProperties: Block.Properties = Block.Properties.create(Material.WOOD)


object WoodBlock: BaseBlock( name, woodBlockProperties )
{
    override fun blockItemProperties(): Item.Properties
    {
        return Item.Properties().group(ItemGroup.MISC)
    }
}