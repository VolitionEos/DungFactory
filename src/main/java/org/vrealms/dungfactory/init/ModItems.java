package org.vrealms.dungfactory.init;


import cpw.mods.fml.common.registry.GameRegistry;
import org.vrealms.dungfactory.item.ItemDungBall;
import org.vrealms.dungfactory.item.ItemDungFactory;
import org.vrealms.dungfactory.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemDungFactory dungball = new ItemDungBall();

    public static void init() { GameRegistry.registerItem(dungball, "dungball"); }
}
