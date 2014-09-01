package org.vrealms.dungfactory.handler;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import org.vrealms.dungfactory.reference.Reference;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;
    public static boolean dropDungBalls = true;

    public static void init(File configFile)
    {
        //Create the configuration object from the configuration file
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            //Resync configs
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        //Read in properties from configuration file
        dropDungBalls = configuration.get(Configuration.CATEGORY_GENERAL, "dropDungBalls", true, "Allow animals to drop Dung Balls").getBoolean(true);

        //Save the configuration file
        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
}
