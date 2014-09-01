package org.vrealms.dungfactory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHoeExtended extends ItemHoe
{
    public ItemHoeExtended(ToolMaterial p_i45343_1_) {
        super(p_i45343_1_);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        return itemStack;
    }
}
