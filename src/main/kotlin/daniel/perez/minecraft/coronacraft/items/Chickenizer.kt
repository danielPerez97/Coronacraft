package daniel.perez.minecraft.coronacraft.items

import daniel.perez.minecraft.coronacraft.base.BaseItem
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.passive.ChickenEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand

private const val name = "chickenizer"
private val properties = Item.Properties()
        .group(ItemGroup.TOOLS)
        .maxStackSize(1)

object Chickenizer: BaseItem(name, properties)
{
    override fun itemInteractionForEntity(stack: ItemStack, playerIn: PlayerEntity, target: LivingEntity, hand: Hand): Boolean
    {
        if(target is ChickenEntity)
        {
            // Set the chicken on fire and lower its health
            target.health = 2f
            target.setFire(10)
            return true
        }
        return false
    }
}