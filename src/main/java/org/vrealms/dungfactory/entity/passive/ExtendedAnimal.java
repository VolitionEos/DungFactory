package org.vrealms.dungfactory.entity.passive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import org.vrealms.dungfactory.reference.Reference;
import org.vrealms.dungfactory.utility.MathHelper;

public class ExtendedAnimal implements IExtendedEntityProperties
{
    private final EntityAnimal animal;
    private int timeUntilNextDungBall;

    public ExtendedAnimal(EntityAnimal animal)
    {
        this.animal = animal;
        this.timeUntilNextDungBall = MathHelper.rand.nextInt(3000)+3000;
    }

    public static final void register(EntityAnimal animal)
    {
        animal.registerExtendedProperties(Reference.EXTEND_ANIMAL, new ExtendedAnimal(animal));
    }

    public static final ExtendedAnimal get(EntityAnimal animal)
    {
        return (ExtendedAnimal) animal.getExtendedProperties(Reference.EXTEND_ANIMAL);
    }

    public int getTimeUntilNextDungBall()
    {
        return this.timeUntilNextDungBall;
    }

    public void decrementTimeUntilNextDungBall()
    {
        this.timeUntilNextDungBall -= 1;
    }

    public void ResetNextDrop()
    {
        this.timeUntilNextDungBall = MathHelper.rand.nextInt(3000)+3000;
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();
        properties.setInteger(Reference.NEXT_DUNGBALL, this.timeUntilNextDungBall);
        compound.setTag(Reference.EXTEND_ANIMAL, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(Reference.EXTEND_ANIMAL);
        this.timeUntilNextDungBall = properties.getInteger(Reference.NEXT_DUNGBALL);
    }

    @Override
    public void init(Entity entity, World world) {}
}
