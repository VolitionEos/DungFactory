package org.vrealms.dungfactory;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import org.vrealms.dungfactory.client.handler.KeyInputEventHandler;
import org.vrealms.dungfactory.handler.ConfigurationHandler;
import org.vrealms.dungfactory.handler.EntityEventHandler;
import org.vrealms.dungfactory.init.ModBlocks;
import org.vrealms.dungfactory.init.ModItems;
import org.vrealms.dungfactory.init.Recipes;
import org.vrealms.dungfactory.proxy.IProxy;
import org.vrealms.dungfactory.reference.Reference;
import org.vrealms.dungfactory.utility.LogHelper;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class DungFactory
{
    @Mod.Instance("DungFactory")
    public static DungFactory instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
        FMLCommonHandler.instance().bus().register(new EntityEventHandler());

        //proxy.registerKeyBindings();
        ModItems.init();
        ModBlocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());

        Recipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        //LogHelper.info("Post Initialization Complete!");

        for (String oreName : OreDictionary.getOreNames())
        {
            //LogHelper.info(oreName);

                //Example of use
                //OreDictionary.getOres("stickWood");
        }
    }
}
