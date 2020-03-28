package daniel.perez.minecraft.coronacraft.items

import daniel.perez.minecraft.coronacraft.base.BaseItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

/**
 *
 */

private const val name: String = "wood_item"
private val properties: Item.Properties = Item.Properties()
        .group(ItemGroup.MISC)


object WoodItem: BaseItem( name, properties )