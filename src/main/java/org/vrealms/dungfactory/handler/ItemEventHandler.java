package org.vrealms.dungfactory.handler;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import org.vrealms.dungfactory.DungFactory;
import org.vrealms.dungfactory.init.ModBlocks;
import org.vrealms.dungfactory.utility.LogHelper;

public class ItemEventHandler
{
    /*
    @SubscribeEvent
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        LogHelper.info("DungFactory - onItemRightClick");

        return p_77659_1_;
    }

    @SubscribeEvent
    public void UseHoeEvent(entityPlayer, itemStack, world, X, Y, Z)
    {
        Block block = world.getBlock(X, Y, Z);

        if (face != 0 && world.getBlock(X, Y + 1, Z).isAir(world, X, Y + 1, Z) && block == ModBlocks.fertiledirt)//(block == Blocks.grass || block == Blocks.dirt))
        {
            Block block1 = Blocks.farmland;
            world.playSoundEffect((double) ((float) X + 0.5F), (double) ((float) Y + 0.5F), (double) ((float) Z + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

            if (world.isRemote)
            {
                return true;
            }
            else
            {
                world.setBlock(X, Y, Z, block1);
                itemStack.damageItem(1, entityPlayer);
                return true;
            }
        }
    }

    public void onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int X, int Y, int Z, int face, float part1, float part2, float part3)
    {
        //LogHelper.info("DungFactory - onItemUse");

        //temp
        //return false;
        //temp


        if (!entityPlayer.canPlayerEdit(X, Y, Z, face, itemStack))
        {
            return false;
        }
        else
        {
            UseHoeEvent event = new UseHoeEvent(entityPlayer, itemStack, world, X, Y, Z);
            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return false;
            }

            if (event.getResult() == Event.Result.ALLOW)
            {
                itemStack.damageItem(1, entityPlayer);
                return true;
            }

            Block block = world.getBlock(X, Y, Z);

            if (face != 0 && world.getBlock(X, Y + 1, Z).isAir(world, X, Y + 1, Z) && block == ModBlocks.fertiledirt)//(block == Blocks.grass || block == Blocks.dirt))
            {
                Block block1 = Blocks.farmland;
                world.playSoundEffect((double) ((float) X + 0.5F), (double) ((float) Y + 0.5F), (double) ((float) Z + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

                if (world.isRemote)
                {
                    return true;
                }
                else
                {
                    world.setBlock(X, Y, Z, block1);
                    itemStack.damageItem(1, entityPlayer);
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }
    */
}
