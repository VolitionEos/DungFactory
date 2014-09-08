package org.vrealms.dungfactory.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import static org.vrealms.dungfactory.init.ModItems.*;

public class Recipes
{
    public static void init()
    {
        //GameRegistry.addRecipe();
        //GameRegistry.addShapedRecipe();

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.fertiledirt), "udu", "dbd", "udu", 'u', new ItemStack(dungball), 'd', new ItemStack(Blocks.dirt), 'b', new ItemStack(Items.bone)));
        //GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.fertiledirt), new ItemStack(ModItems.dungball, 4), new ItemStack(Blocks.dirt, 4), new ItemStack(Items.bone)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.rake), "SSS", " S ", " S ", 'S', new ItemStack(Items.stick)));
    }
}
