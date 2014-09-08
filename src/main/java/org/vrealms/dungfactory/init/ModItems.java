package org.vrealms.dungfactory.init;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import org.vrealms.dungfactory.item.ItemDungBall;
import org.vrealms.dungfactory.item.ItemDungFactory;
import org.vrealms.dungfactory.item.ItemRake;
import org.vrealms.dungfactory.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemDungFactory dungball = new ItemDungBall();
    public static final ItemDungFactory rake = new ItemRake(Item.ToolMaterial.WOOD);

    public static void init()
    {
        GameRegistry.registerItem(dungball, "dungball");
        GameRegistry.registerItem(rake, "rake");
    }
}
