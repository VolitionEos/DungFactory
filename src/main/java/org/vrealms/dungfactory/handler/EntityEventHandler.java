package org.vrealms.dungfactory.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.vrealms.dungfactory.entity.passive.ExtendedAnimal;
import org.vrealms.dungfactory.init.ModItems;
import org.vrealms.dungfactory.reference.Reference;
import org.vrealms.dungfactory.utility.LogHelper;
import org.vrealms.dungfactory.utility.MathHelper;

public class EntityEventHandler
{
    private String NextDrop;

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        if (event.entity instanceof EntityAnimal && ExtendedAnimal.get((EntityAnimal) event.entity) == null)
            ExtendedAnimal.register((EntityAnimal) event.entity);

        if (event.entity instanceof EntityAnimal && event.entity.getExtendedProperties(Reference.EXTEND_ANIMAL) == null)
            event.entity.registerExtendedProperties(Reference.EXTEND_ANIMAL, new ExtendedAnimal((EntityAnimal) event.entity));
    }

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event)
    {
        if (event.entity instanceof EntityAnimal)
        {
            if (ConfigurationHandler.dropDungBalls == true)
            {
                ExtendedAnimal animal = ExtendedAnimal.get((EntityAnimal) event.entity);
                animal.decrementTimeUntilNextDungBall();

                if (!event.entity.worldObj.isRemote && animal.getTimeUntilNextDungBall() <= 0)
                {
                    event.entity.playSound("mob.chicken.plop", 1.0F, (MathHelper.rand.nextFloat() - MathHelper.rand.nextFloat()) * 0.2F + 1.0F);
                    event.entity.dropItem(ModItems.dungball, 1);
                    animal.ResetNextDrop();
                }
            }
        }
    }
}