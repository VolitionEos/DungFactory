package org.vrealms.dungfactory.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.vrealms.dungfactory.init.ModItems;
import org.vrealms.dungfactory.reference.Reference;

public class CreativeTabDungFactory
{
    public static final CreativeTabs DUNGFACTORY_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.dungball;
        }
    };
}
