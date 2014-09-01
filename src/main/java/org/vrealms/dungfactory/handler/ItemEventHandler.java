package org.vrealms.dungfactory.handler;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import org.vrealms.dungfactory.DungFactory;
import org.vrealms.dungfactory.init.ModBlocks;

public class ItemEventHandler
{
    /*
    @SubscribeEvent
    public boolean UseHoeEvent(EntityItem event)
    {
        private static event.entityPlayer, event.entityPlayer.getItemInUse(), event.entityPlayer.getEntityWorld(), )
        if (MinecraftForge.EVENT_BUS.post(event))
        {
            return false;
        }

        if (event.getResult() == Event.Result.ALLOW)
        {
            event. p_77648_1_.damageItem(1, p_77648_2_);
            return true;
        }

        //Block block = Block.getBlockFromName("farmland");

        Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);

        if (p_77648_7_ != 0 && p_77648_3_.getBlock(p_77648_4_, p_77648_5_ + 1, p_77648_6_).isAir(p_77648_3_, p_77648_4_, p_77648_5_ + 1, p_77648_6_) && block == ModBlocks.fertiledirt)
        {
            Block block1 = Blocks.farmland;
            p_77648_3_.playSoundEffect((double)((float)p_77648_4_ + 0.5F), (double)((float)p_77648_5_ + 0.5F), (double)((float)p_77648_6_ + 0.5F), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0F) / 2.0F, block1.stepSound.getPitch() * 0.8F);

            if (p_77648_3_.isRemote)
            {
                return true;
            }
            else
            {
                p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, block1);
                p_77648_1_.damageItem(1, p_77648_2_);
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    */
}
